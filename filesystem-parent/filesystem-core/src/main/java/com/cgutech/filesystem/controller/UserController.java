package com.cgutech.filesystem.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgutech.filesystem.dao.UserDao;
import com.cgutech.filesystem.entity.UserEntity;
import com.cgutech.filesystem.service.UserService;
import com.cgutech.filesystem.utils.AjaxDone;
import com.cgutech.filesystem.utils.Page;
import com.cgutech.filesystem.utils.ReadProperties;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	private Logger log= LoggerFactory.getLogger(UserController.class);
		@RequestMapping(value="/list")
		public String userlist(@RequestParam(value="pageNum",defaultValue="1")String pageNum,
				@RequestParam(value="numPerPage",defaultValue="20")String numPerPage,
				@RequestParam(value="user",defaultValue="")String user,
				@RequestParam(value="role",defaultValue="")String role,
				Model model){
			if("".equals(user)||user==null||user.isEmpty()){
				user=null;
			}
			if("".equals(role)||role==null||role.isEmpty()){
				role=null;
			}
			Page page=userService.getUsers(user, role,Integer.parseInt(pageNum), Integer.parseInt(numPerPage));
			model.addAttribute("userlist",page);
			model.addAttribute("user",user);
			model.addAttribute("role",role);
			return "manager/user/users";
		}
		
		@RequestMapping("/uadd")
		public String add(Model model){
			List<UserEntity> admins = new ArrayList<>();
			admins=userService.getAdminUsers();
			if(admins.size()==0){
				UserEntity userEntity= new UserEntity();
				userEntity.setName(ReadProperties.read("admin"));
				admins.add(userEntity);
			}
			model.addAttribute("admins",admins);
			return "manager/user/adduser";
		}
		@RequestMapping("/adduser")
		public String  addUser(@RequestParam("name")String name,
				@RequestParam("status")String status,
				@RequestParam("role")String role,
				@RequestParam("ddadmin")String dd_admin,
				Model model,
				HttpServletRequest request) {
			AjaxDone ajaxDone = new AjaxDone();
			if(userService.getUser(name)!=null){
				ajaxDone.setStatusCode(300);
				ajaxDone.setMessage("用户"+name+"已经存在！");
				log.info("用户"+name+"已经存在！");
				request.setAttribute("ajaxDone", ajaxDone);
				return "ajaxDone";
			}
			UserEntity userEntity=new UserEntity();
			userEntity.setName(name);
			userEntity.setPassword(name);
			userEntity.setRole(role);
			userEntity.setDd_admin(dd_admin);
			if(role.equals("admin")){
				userEntity.setRole_id(0);
			}else {
				userEntity.setRole_id(1);
			}
			userEntity.setStatus(status);
			try {
				userService.createUser(userEntity);
				ajaxDone.setMessage("用户创建成功");
				ajaxDone.setCallbackType("closeCurrent");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				ajaxDone.setStatusCode(300);
				ajaxDone.setMessage("system error");
			}
			
			ajaxDone.setNavTabId("cguUser");
			request.setAttribute("ajaxDone", ajaxDone);
			return "ajaxDone";
		}
		
		@RequestMapping("/change")
		public String change(@RequestParam("user")String name,Model model){
			UserEntity userEntity=userService.getUser(name);
			model.addAttribute("id", userEntity.getId());
			model.addAttribute("user",name);
			model.addAttribute("role",userEntity.getRole());
			model.addAttribute("status",userEntity.getStatus());
			model.addAttribute("dd_admin",userEntity.getDd_admin());
			List<UserEntity> admins = new ArrayList<>();
			admins=userService.getAdminUsers();
			if(admins.size()==0){
				UserEntity user= new UserEntity();
				userEntity.setName(ReadProperties.read("admin"));
				admins.add(user);
			}
			model.addAttribute("admins",admins);
			return "manager/user/changestatus";
		}
		
		@RequestMapping("/changestatus")
		public String changeStatus(@RequestParam("user")String name,
				@RequestParam("status")String status,
				@RequestParam("role")String role,
				@RequestParam("ddadmin")String dd_admin,
				HttpServletRequest request){
			AjaxDone ajaxDone = new AjaxDone();
			try {
				userService.updateUser(name, role, status,dd_admin);
				ajaxDone.setMessage("修改成功");
				ajaxDone.setCallbackType("closeCurrent");
			} catch (Exception e) {
				e.printStackTrace();
				ajaxDone.setStatusCode(300);
				ajaxDone.setMessage("system error");
			}
			ajaxDone.setNavTabId("cguUser");
			request.setAttribute("ajaxDone", ajaxDone);
			return "ajaxDone";
		}
		
		@RequestMapping("/delete")
		public String deleteUser(@RequestParam("user")String name,
				HttpServletRequest request){
			AjaxDone ajaxDone = new AjaxDone();
			try {
				userService.deleteUser(name);
				ajaxDone.setMessage("删除成功");
			} catch (Exception e) {
				e.printStackTrace();
				ajaxDone.setStatusCode(300);
				ajaxDone.setMessage("system error");
			}
			ajaxDone.setNavTabId("cguUser");
			request.setAttribute("ajaxDone", ajaxDone);
			return "ajaxDone";
		}
		
		@RequestMapping("/auto")
		@ResponseBody
		public String auto(HttpServletResponse response){
			List<UserEntity> users = userDao.getUserNames();
			List<String > names = new ArrayList<>();
			if(users!=null&&users.size()>0){
				for (UserEntity user : users) {
					names.add(user.getName());
				}
			}
			log.info("names.toString():"+names.toString());
			return "{\"names\":\""+names.toString().replace("[", "").replace("]", "")+"\"}";
			//return names.toString();
		}
}

