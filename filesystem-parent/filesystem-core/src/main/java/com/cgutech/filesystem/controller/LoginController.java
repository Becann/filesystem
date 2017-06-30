package com.cgutech.filesystem.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cgutech.filesystem.entity.FileEntity;
import com.cgutech.filesystem.entity.TempFileEntity;
import com.cgutech.filesystem.entity.UserEntity;
import com.cgutech.filesystem.service.UserService;
import com.cgutech.filesystem.utils.AjaxDone;
import com.cgutech.filesystem.utils.HttpUtil;
import com.cgutech.filesystem.utils.IPUtil;
import com.cgutech.filesystem.utils.JsonUtil;
import com.cgutech.filesystem.utils.MD5;
import com.cgutech.filesystem.utils.Page;
import com.cgutech.filesystem.utils.ReadProperties;


@Controller
@RequestMapping("/admin")
public class LoginController {
	@Autowired
	private UserService userService;
	

	private Logger log = LoggerFactory.getLogger(LoginController.class);


	@RequestMapping("/login")
	public String login(@RequestParam(value = "username", defaultValue = "") String name,
			@RequestParam(value = "password", defaultValue = "") String password,
			/*@RequestParam(value="platform",defaultValue="terminal") String platfrom,*/
			@RequestParam(value = "uuid", defaultValue = "") String uuid, Model model, HttpServletRequest request)
			throws UnknownHostException {
		if (!"".equals(uuid)) {
			UserEntity userEntity = userService.getUserByUUid(uuid);
			name = userEntity.getName();
			password = userEntity.getPassword();
			if (name == null) {
				request.setAttribute("message", "用户uuid不正确,请尝试密码登录");
				return "login";
			}
		} else if (password != null && !password.isEmpty()) {
			password = MD5.encode(password);
		}
		Page page = userService.getUsers(null,null, 0, 1);
		//InetAddress addr = InetAddress.getLocalHost();
		//String localip = addr.getHostAddress().toString();// 获得本机IP
		if (page.getList() == null || page.getList().size() == 0) {
			UserEntity user = new UserEntity();
			user.setName("admin");
			user.setPassword("admin");
			user.setRole("admin");
			user.setRole_id(0);
			user.setStatus("ACTIVE");
			user.setDd_admin(ReadProperties.read("admin"));
			userService.createUser(user);
			log.info("已创建用户admin，密码为admin");
			request.setAttribute("message", "已创建用户admin，密码为admin");
			return "login";
		}
		String userip = IPUtil.getIpAddress(request);
		log.info("客户端ip" + userip);
		UserEntity userEntity = userService.getUser(name);
		if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
			request.setAttribute("message", "账号密码不能为空");
		} else {
			if (userEntity == null || !userEntity.getPassword().equals(password)) {
				request.setAttribute("message", "用户名或密码错误，请重新输入");
			} else {
				if (userEntity.getStatus().equals("INACTIVE")) {
					request.setAttribute("message", "该用户未激活");
				} else {
					request.getSession().setAttribute("admin", userEntity);
					//model.addAttribute("admin", userEntity);
					//用户端访问地址为本地
					boolean localb = userip.equals("0:0:0:0:0:0:0:1")?true:false;
					if(localb){
						log.info("platform: remote，此为项目部署服务器登录客户端访问，默认为远程访问");
						model.addAttribute("platform", "remote");
						return "manager/admin";
					}
					
					int localipint=Integer.parseInt(userip.substring(userip.lastIndexOf(".")+1));
					//int localipint=128;
					log.info("localint :"+localipint);
					if(localipint>=128){
						log.info("platform: terminal");
						model.addAttribute("platform", "terminal");
						if (userService.getUser(name).getRole_id() == 0) {
							log.info(" return \"manager/admin\";");
							return "manager/admin";
						}
						if (userService.getUser(name).getRole_id() == 1) {
							log.info(" return \"upload\";");
							return "upload";
						}
					}
					if(localipint<128){
						log.info("platform: remote");
						model.addAttribute("platform", "remote");
						return "manager/admin";
					}
				
			}
		}
	}
		return "login";
	}
	@ResponseBody
	@RequestMapping(value = "/upload")
	public String fileUpload(@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request,
			@RequestParam(value = "user") String user, HttpServletResponse response) throws IOException {
		try {
			String filename = file.getOriginalFilename();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			String dirOfDate= sf.format(new Date());
			// filename=UUID.randomUUID().toString().substring(0,
			// 16)+filename.substring(filename.lastIndexOf("."),
			// filename.length());
			String realPath = ReadProperties.read("uploadpath") + user + 
					File.separator + dirOfDate+File.separator+ filename;
			log.info("上传路径：" + realPath);
			File target = new File(realPath);
			if (!target.getParentFile().exists()) {
				if(!target.getParentFile().mkdirs()){
					log.info("创建目录失败");
					response.setStatus(500);
					return "{\"error\":\"Failed to create directory\"}";
				}	
			}
			
			if (!target.exists()) {
				FileEntity fileEntity = new FileEntity();
				System.out.println(filename);
				fileEntity.setF_name(filename);
				fileEntity.setF_path(realPath);
				fileEntity.setF_user(user);
				fileEntity.setCtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				userService.saveFileInfo(fileEntity);
				file.transferTo(target);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			// response.sendError(400,"{ \"error\": \"system error\" }");
			 response.setStatus(500); 
			return "{ \"error\": \"system error\" }";
		}

		return null;
	}

	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public String changeAdminPass(@RequestParam("oldpassword") String oldpassword,
			@RequestParam("newpassword") String newpassword, @RequestParam("adminname") String adminname,
			HttpServletRequest request) {
		AjaxDone ajaxDone = new AjaxDone();
		String pass = userService.getPassword(adminname);
		if (pass.equals("")) {
			ajaxDone.setStatusCode(300);
			ajaxDone.setMessage("用户不存在");
		} else if (!pass.equals(MD5.encode(oldpassword))) {
			ajaxDone.setStatusCode(300);
			ajaxDone.setMessage("旧密码不正确");
		} else {
			log.info("开始修改用户" + adminname + "的密码");
			userService.changePassword(adminname, newpassword);
			ajaxDone.setMessage("密码修改成功");
			ajaxDone.setCallbackType("closeCurrent");
		}
		request.setAttribute("ajaxDone", ajaxDone);
		return "ajaxDone";
	}

	/**
	 * 用户注销
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		if (null != request.getSession().getAttribute("admin")) {
			log.info("用户" + request.getSession().getAttribute("admin") + "已退出");
			request.getSession().invalidate();
		}
		return "login";
	}

	@RequestMapping(value = "/adminupload")
	public String adminupload(@RequestParam("username") String username, Model model) {
		log.info("username:" + username);
		model.addAttribute("username", username);
		return "adminupload";

	}

	@RequestMapping(value = "/admindownload")
	public String admindownload(@RequestParam("username") String username, Model model) {
		log.info("username:" + username);
		model.addAttribute("username", username);
		return "manager/file/admindownload";

	}
	
	@RequestMapping("/download")
	@ResponseBody
	public String download(@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request,
			@RequestParam(value = "user") String user,
			@RequestParam(value = "shareOrdown") String shareOrdown,
			HttpServletResponse response) {
		if("".equals(shareOrdown)){
			response.setStatus(500); 
			return "{ \"error\": \"Has been canceled Upload\" }";
		}
		if(shareOrdown.equals("share")){
			try {
				String filename = file.getOriginalFilename();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
				String dirOfDate= sf.format(new Date());
				String realPath = ReadProperties.read("uploadpath") + user + 
						File.separator + dirOfDate+File.separator+ filename;
				log.info("上传路径：" + realPath);
				File target = new File(realPath);
				if (!target.getParentFile().exists()) {
					if(!target.getParentFile().mkdirs()){
						log.info("创建目录失败");
						response.setStatus(500);
						return "{\"error\":\"Failed to create directory\"}";
					}	
				}
				if (!target.exists()) {
					FileEntity fileEntity = new FileEntity();
					fileEntity.setF_name(filename);
					fileEntity.setF_path(realPath);
					fileEntity.setF_user(user);
					fileEntity.setCtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					userService.saveFileInfo(fileEntity);
					file.transferTo(target);
				}
			} catch (Exception e) {
				log.info(e.getMessage());
				 response.setStatus(500); 
				return "{ \"error\": \"system error\" }";
			}
			return null;
		}

		
		String filename = file.getOriginalFilename();
		// filename=UUID.randomUUID().toString().substring(0,
		// 16)+filename.substring(filename.lastIndexOf("."), filename.length());
		String tempPath = ReadProperties.read("temppath");
		tempPath = tempPath + File.separator + user + File.separator + filename;
		log.info("文件暂存目录" + tempPath);
		File target = new File(tempPath);

		try {
			if (!target.getParentFile().exists()) {
				target.getParentFile().mkdirs();
			}
			//if (!target.exists()) {
				
				// 通知审核钉钉管理员
				String admin = userService.getUser(user).getDd_admin();
				log.info("用户名admin："+admin);
				log.info("userService.getUser(admin):"+JsonUtil.toJson(userService.getUser(admin)));
				/*****分时段推送方式*******/
				
				//计时器
				Date date = new Date();
				long nt=date.getTime();
				Object otr;
				long ot=((otr=request.getSession().getAttribute("ddtimer"))==null)?0:Long.parseLong(otr.toString());
				log.info("request.getSession().getAttribute(\"ddtimer\")"+otr);
				log.info("newtime:"+nt+"   oldtime:"+ot+"  time have gone(s):"+((nt-ot)/1000));
				if((nt-ot)>=1000*60){
					//钉钉推送
					String text1 = "管理员用户您好，有新的文件下载审核信息，请前往审核平台进行审核 " + ReadProperties.read("downurl")
					+ request.getContextPath() + "/ws/admin/login?uuid=" + userService.getUser(admin).getUuid()+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					text1=Base64.getUrlEncoder().encodeToString(text1.getBytes("UTF8"));
					String res=HttpUtil.doGet(ReadProperties.read("dd_push_server")+"?text="+text1+"&dname="+admin);
					request.getSession().setAttribute("ddtimer", nt);
				
				
				/*******************/
				
				//String text = "用户" + user + "申请下载文件 " + filename + " 请点击url进行审核" + ReadProperties.read("downurl")
				//		+ request.getContextPath() + "/ws/admin/login?uuid=" + userService.getUser(admin).getUuid();
				//String userId = userService.getUidByName(admin);
				//text=Base64.getUrlEncoder().encodeToString(text.getBytes("UTF8"));
				log.info("钉钉推送服务url："+ReadProperties.read("dd_push_server")+"?text="+text1+"&dname="+admin);
				//String res=HttpUtil.doGet(ReadProperties.read("dd_push_server")+"?text="+text+"&dname="+admin);
				log.info("钉钉推送结果："+res);
				
				if(!res.startsWith("success")){				
					response.setStatus(500);
					return "{\"error\":\"DingDing push message failed, please inform the administrator\"}";
					}
				}
				
				TempFileEntity fileEntity = new TempFileEntity();
				fileEntity.setFname(filename);
				fileEntity.setPath(tempPath);
				fileEntity.setAuditstatus(0);
				fileEntity.setAuser(user);
				fileEntity.setDstatus("no");
				fileEntity.setDtime("0000-00-00 00:00:00");
				fileEntity.setAtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
				userService.tempFileSaveOrUpdate(fileEntity);
				file.transferTo(target);
				return "success";
		} catch (Exception e) {
			log.error("上传文件失败",e);
			response.setStatus(500);
			return "{\"error\":\"system error\"}";
		}

	}
	static class Test{
		public static void main(String[] args) throws UnsupportedEncodingException, UnknownHostException {
			/*String text="用户becan申请下载文件 openjdk-7u40-fcs-src-b43-26_aug_2013.zip";
			text=Base64.getUrlEncoder().encodeToString(text.getBytes("UTF8"));
			System.out.println(text);
			text="xOPJ6sfrz8LU2LXEzsS8_iDXwMPm0OnE4rDsuavGvcyoLnBwdHgg0tG-rc2ouf3J87rLo6y147v3wbS908_C1NhodHRwOi8vMTkyLjE2OC4xLjY6ODA4MC9maWxlc3lzdGVtLXdlYi93cy9maWxlL2Rvd25sb2FkRmlsZT9wYXRoPVJEcGNkR1Z0Y0Z4aVpXTmhibHptb1l6cG5hTG9tWnJtaTVfbGlwN2xoYXpsdWJQbGo3QXVjSEIwZUE9PSZpZD01";
			byte[] bs=Base64.getUrlDecoder().decode(text.getBytes());
			text=new String(bs, "UTF8");
			System.err.println(text);*/
			InetAddress addr = InetAddress.getLocalHost();
			String localip = addr.getHostAddress().toString();// 获得本机IP
			int localipint=Integer.parseInt(localip.substring(localip.lastIndexOf(".")+1));
			
			System.out.println(localipint);
			System.out.println(localip);
			/*Date date = new Date();
			long nt=date.getTime();
			long ot=new Long("1478831783121");
			System.out.println(nt);
			System.out.println((nt-ot)/1000/60);*/
			//1478831783121
			//1478831818326
		}
	}
	
}
