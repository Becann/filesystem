package com.cgutech.filesystem.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.cgutech.filesystem.entity.FileEntity;
import com.cgutech.filesystem.entity.ShareFileEntity;
import com.cgutech.filesystem.entity.TempFileEntity;
import com.cgutech.filesystem.entity.UserEntity;
import com.cgutech.filesystem.exception.BizException;
import com.cgutech.filesystem.service.UserService;
import com.cgutech.filesystem.utils.AjaxDone;
import com.cgutech.filesystem.utils.HttpUtil;
import com.cgutech.filesystem.utils.Page;
import com.cgutech.filesystem.utils.ReadProperties;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private UserService userService;
    private Logger log = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
                       @RequestParam(value = "numPerPage", defaultValue = "20") String numPerPage,
                       @RequestParam(value = "user", defaultValue = "") String user,
                       @RequestParam(value = "filename", defaultValue = "") String filename,
                       @RequestParam(value = "before", defaultValue = "") String before,
                       @RequestParam(value = "after", defaultValue = "") String after,
                       HttpServletRequest request,
                       Model model) {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("admin");
        if (userEntity.getRole_id() == 1) {
            user = userEntity.getName();
        }
        if ("".equals(user) || user == null || user.isEmpty()) {
            user = null;
        }
        if ("".equals(before) || before == null || before.isEmpty()) {
            before = null;
        } else {
            before = before + " 00:00:00";
        }
        if ("".equals(after) || after == null || after.isEmpty()) {
            after = null;
        } else {
            after = after + " 00:00:00";
        }
        filename = "".equals(filename) ? null : filename;
        int currentPage = Integer.parseInt(pageNum);
        int pageSize = Integer.parseInt(numPerPage);
        Page page = userService.getFiles(user, filename, before, after, currentPage, pageSize);
        model.addAttribute("filelist", page);
        model.addAttribute("user", user);
        model.addAttribute("filename", filename);
        model.addAttribute("before", before);
        model.addAttribute("after", after);
        return "manager/file/files";
    }

    @RequestMapping("/audit")
    public String fileAudit(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
                            @RequestParam(value = "numPerPage", defaultValue = "20") String numPerPage,
                            @RequestParam(value = "applyuser", defaultValue = "") String user,
                            @RequestParam(value = "auditStatus", defaultValue = "") String auditStatus,
                            @RequestParam(value = "applyDate", defaultValue = "") String applyDate,
                            @RequestParam(value = "downDate", defaultValue = "") String downDate,
                            @RequestParam(value = "filename", defaultValue = "") String filename,
                            @RequestParam(value = "downStatus", defaultValue = "") String downStatus,
                            Model model) {
        if ("".equals(user) || user == null || user.isEmpty()) {
            user = null;
        }
        if ("".equals(filename) || filename == null || filename.isEmpty()) {
            filename = null;
        }
        if ("".equals(downStatus)) {
            downStatus = null;
        }
        if ("".equals(applyDate) || applyDate == null || applyDate.isEmpty()) {
            applyDate = null;
        } else {
            applyDate = applyDate + " 00:00:00";
        }
        if ("".equals(downDate) || downDate == null || downDate.isEmpty()) {
            downDate = null;
        } else {
            downDate = downDate + " 00:00:00";
        }
        int currentPage = Integer.parseInt(pageNum);
        int pageSize = Integer.parseInt(numPerPage);
        int aStatus = "".equals(auditStatus) ? 3 : Integer.parseInt(auditStatus);

        try {
            Page page = userService.getAuditFiles(user, applyDate, downDate, aStatus,
                    downStatus, filename, currentPage, pageSize);
            model.addAttribute("applyuser", user);
            model.addAttribute("auditStatus", auditStatus);
            model.addAttribute("applyDate", applyDate);
            model.addAttribute("downDate", downDate);
            model.addAttribute("filename", filename);
            model.addAttribute("downStatus", downStatus);
            model.addAttribute("filelist", page);
        } catch (Exception e) {
            log.error(e.getMessage());
        }


        return "manager/file/audit";
    }

    @RequestMapping("retrieval")
    public String retrieval() {
        return "manager/file/retrieval";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "id", defaultValue = "") String id,
                         @RequestParam(value = "ids", defaultValue = "") String ids,
                         HttpServletRequest request) {
        AjaxDone ajaxDone = new AjaxDone();
        log.info("ids:" + ids);
        try {
            if ("".equals(id) && !"".equals(ids)) {
                String[] fids = ids.split(",");
                for (String fid : fids) {
                    userService.tempFileDelete(Integer.parseInt(fid));
                }
            } else {
                userService.tempFileDelete(Integer.parseInt(id));
            }
            ajaxDone.setMessage("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxDone.setStatusCode(300);
            ajaxDone.setMessage("系统错误，请联系管理员");
        }
        request.getSession().setAttribute("ajaxDone", ajaxDone);
        return "ajaxDone";
    }

    @RequestMapping("/deleteupload")
    public String deleteupload(
            @RequestParam(value = "ids", defaultValue = "") String ids,
            HttpServletRequest request) {
        AjaxDone ajaxDone = new AjaxDone();
        try {
            if (!"".equals(ids)) {
                String[] fids = ids.split(",");
                for (String fid : fids) {
                    userService.uploadDelete(Integer.parseInt(fid));
                }
            }
            ajaxDone.setMessage("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxDone.setStatusCode(300);
            ajaxDone.setMessage("系统错误，请联系管理员");
        }
        request.getSession().setAttribute("ajaxDone", ajaxDone);
        return "ajaxDone";
    }

    @RequestMapping("/editAudit")
    public String editAudit(@RequestParam(value = "id") String id,
                            @RequestParam(value = "status") String status,
                            HttpServletRequest request) {
        AjaxDone ajaxDone = new AjaxDone();
        TempFileEntity tempFileEntity = userService.getTempFile(Integer.parseInt(id));
        //String path=tempFileEntity.getPath();
        String text = "";
        try {
            //InetAddress addr = InetAddress.getLocalHost();
            //String localip = addr.getHostAddress().toString();// 获得本机IP
            if (status.equals("1")) {
                //通知审核钉钉用户
                String url = ReadProperties.read("downurl") + request.getContextPath() + "/ws/file/downloadFile?id=" + id;
                text = "你申请下载的文件 " + tempFileEntity.getFname() + " 已经通过审核，点击链接下载" + url;
            }
            if (status.equals("2")) {
                text = "你申请下载的文件 " + tempFileEntity.getFname() + " 审核未通过，如有疑问请联系管理员 " + userService.getUser(tempFileEntity.getAuser()).getDd_admin();
            }
            //String userId =userService.getUidByName(tempFileEntity.getAuser());
            //钉钉推送单元
            text = Base64.getUrlEncoder().encodeToString(text.getBytes("UTF8"));
            log.info("钉钉推送服务url：" + ReadProperties.read("dd_push_server") + "?text=" + text + "&dname=" + tempFileEntity.getAuser());
            String res = HttpUtil.doGet(ReadProperties.read("dd_push_server") + "?text=" + text + "&dname=" + tempFileEntity.getAuser());
            log.info("钉钉推送结果：" + res);

            if (res.startsWith("success")) {
                tempFileEntity.setAuditstatus(Integer.parseInt(status));
                userService.tempFileSaveOrUpdate(tempFileEntity);
                ajaxDone.setMessage("审核操作成功，已通知相关用户");
            } else {
                ajaxDone.setMessage("审核消息通知用户失败，请检查钉钉推送服务器后重新审核");
                ajaxDone.setStatusCode(300);
            }
            //ajaxDone.setCallbackType("closeCurrent");
            ajaxDone.setNavTabId("cguAudit");
        } catch (Exception e) {
            ajaxDone.setStatusCode(300);
            ajaxDone.setMessage("系统错误，请联系管理员");
            e.printStackTrace();
        }
        request.getSession().setAttribute("ajaxDone", ajaxDone);
        return "ajaxDone";
    }

    @RequestMapping("/downloadFile")
    public void downloadFile(
            @RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "flag", defaultValue = "") String flag,
            HttpServletResponse response) {


        FileInputStream inputStream = null;

        try {
            String path = "";
            String filename = "";
            if ("share".equals(flag)) {
                ShareFileEntity shareFileEntity = userService.getShareFileEntityById(Integer.parseInt(id));
                if (shareFileEntity == null) {
                    throw new BizException("该分享文件信息为空");
                }
                path = shareFileEntity.getFilepath();
                filename = shareFileEntity.getFilename();
            } else if ("upd".equals(flag)) {
                FileEntity fileEntity = userService.getFileById(Integer.parseInt(id));
                if (fileEntity == null) {
                    throw new BizException("该文件信息为空");
                }
                path = fileEntity.getF_path();
                filename = fileEntity.getF_name();
            } else {
                TempFileEntity tempFileEntity = userService.getTempFile(Integer.parseInt(id));
                if (tempFileEntity == null) {
                    throw new BizException("该临时文件信息为空");
                }
                path = tempFileEntity.getPath();
                filename = tempFileEntity.getFname();
            }
            log.info("文件路径：" + path);
            if (path == null) {
                throw new BizException("文件路径为空，请从管理员处重新获取");
            }

            log.info("下载文件名filename:" + filename);
            response.setHeader("Content-Disposition", "attchment;filename="
                    + new String(filename.getBytes("utf-8"), "ISO-8859-1"));
            response.setContentType("application/octet-stream; charset=utf-8");
            File file = new File(path);
            if (!file.exists()) {
                log.warn("未能找到磁盘文件");
                throw new BizException("未能找到磁盘文件，请联系管理员，确认文件是否已经被删除");
            }
            inputStream = new FileInputStream(file);
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            //对于审核通过文件的下载    更新下载时间
            if (!"".equals(id) && "".equals(flag)) {
                TempFileEntity tempFileEntity = userService.getTempFile(Integer.parseInt(id));
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                tempFileEntity.setDtime(sf.format(new Date()));
                tempFileEntity.setDstatus("yes");
                userService.tempFileSaveOrUpdate(tempFileEntity);
            }

        } catch (Exception e) {
            log.error("文件下载失败", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("关闭流资源失败", e);
                }
            }
        }
    }


    @RequestMapping("/tshare")
    public String tshare(
            @RequestParam("id") String id,
            Model model) {
        model.addAttribute("fid", id);
        return "/manager/file/share";
    }

    @RequestMapping("/share")
    public String share(
            @RequestParam("id") String id,
            @RequestParam("beshareman") String beshareman,
            @RequestParam("shareman") String shareman,
            HttpServletRequest request) {
        AjaxDone ajaxDone = new AjaxDone();
        try {
            FileEntity fileEntity = userService.getFileById(Integer.parseInt(id));
            String filename = fileEntity.getF_name();
            String path = fileEntity.getF_path();
            userService.saveShareFile(filename, shareman, beshareman.trim(), path);
            /******分享钉钉推送*******/
            String text = "用户" + shareman + "给你分享了文件（" + filename + "），前往web文件系统http://192.168.11.4:8080/filesystem-web查看下载。";
            text = Base64.getUrlEncoder().encodeToString(text.getBytes("UTF8"));
            log.info("钉钉推送服务url：" + ReadProperties.read("dd_push_server") + "?text=" + text + "&dname=" + beshareman.trim());
            String res = HttpUtil.doGet(ReadProperties.read("dd_push_server") + "?text=" + text + "&dname=" + beshareman.trim());
            log.info("钉钉推送结果：" + res);

            ajaxDone.setMessage("分享成功");
            ajaxDone.setCallbackType("closeCurrent");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            ajaxDone.setStatusCode(300);
            ajaxDone.setMessage("分享失败，系统错误");
        }
        request.setAttribute("ajaxDone", ajaxDone);
        return "ajaxDone";
    }

    @RequestMapping("/sharelist")
    public String sharelist(
            @RequestParam(value = "pageNum", defaultValue = "1") String pageNum,
            @RequestParam(value = "numPerPage", defaultValue = "20") String numPerPage,
            @RequestParam(value = "filename", defaultValue = "") String filename,
            @RequestParam(value = "shareman", defaultValue = "") String shareman,
            @RequestParam(value = "besharedman", defaultValue = "") String besharedman,
            Model model,
            HttpServletRequest request) {
        //普通用户
        UserEntity user = (UserEntity) request.getSession().getAttribute("admin");
        log.info("user" + user.getName());
        if (user.getRole_id() == 1) {
            besharedman = user.getName();
        }
        ShareFileEntity shareFileEntity = new ShareFileEntity();
        if (!"".equals(filename) && filename != null) {
            shareFileEntity.setFilename(filename);
        }
        if (!"".equals(shareman) && shareman != null) {
            shareFileEntity.setShareman(shareman);
        }
        if (!"".equals(besharedman) && besharedman != null) {
            shareFileEntity.setBesharedman(besharedman);
        }
        Page page = userService.getShareList(shareFileEntity, Integer.parseInt(pageNum), Integer.parseInt(numPerPage));
        model.addAttribute("sharelist", page);
        model.addAttribute("filename", filename);
        model.addAttribute("shareman", shareman);
        model.addAttribute("besharedman", besharedman);
        return "/manager/file/sharefiles";
    }

    @RequestMapping("/deleteshare")
    public String sharedelete(
            @RequestParam(value = "ids", defaultValue = "") String ids,
            HttpServletRequest request) {
        AjaxDone ajaxDone = new AjaxDone();
        log.info("ids:" + ids);
        try {
            if (!"".equals(ids)) {
                String[] fids = ids.split(",");
                for (String fid : fids) {
                    userService.shareDelete(Integer.parseInt(fid));
                }
            }
            ajaxDone.setMessage("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxDone.setStatusCode(300);
            ajaxDone.setMessage("系统错误，请联系管理员");
        }
        request.getSession().setAttribute("ajaxDone", ajaxDone);
        return "ajaxDone";
    }
}

