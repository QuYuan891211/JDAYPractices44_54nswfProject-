package cn.qy.nswf.user.action;

import cn.qy.core.Action.BaseAction;
import cn.qy.core.exception.ActionException;
import cn.qy.core.exception.ServiceException;
import cn.qy.core.exception.SysException;
import cn.qy.nswf.role.service.IRoleService;
import cn.qy.nswf.user.entity.User;
import cn.qy.nswf.user.entity.UserRole;
import cn.qy.nswf.user.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by qy on 2017/2/27.
 */

public class UserAction extends BaseAction{
    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    private List<User> userList;
    private User user;
    private File headImg;
    private File userExcel;
    private String userExcelFileName;


    private String userExcelContentType;
    private String headImgFileName;
    private String headImgContentType;



    private String[] userRoleIds;

    public String TestUI(){
        return "TestUI";
    }
    //列表页面
    public String listUI(){
        userList = userService.findAll();

        return "listUI";
    }
    //跳转到新增页面
    public String addUI(){
        ActionContext.getContext().getContextMap().put("rolelist",roleService.findAll());
        return "addUI";
    }
    //保存新增
    public String add(){

        if(user != null) {
            if (headImg != null) {
                try {
                    String path = ServletActionContext.getServletContext().getRealPath("/upload/user");
                    String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf('.'));
                    FileUtils.copyFile(headImg, new File(path, newFileName));
                    user.setHeadImg("user/" + newFileName);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                userService.saveUserAndRole(user,userRoleIds);
            }
        }
            return "list";
    }
    //跳转到编辑页面
    public String editUI(){
        ActionContext.getContext().getContextMap().put("rolelist",roleService.findAll());
        if (user != null && user.getId() != null) {
            user = userService.findById(user.getId());
            List<UserRole> list = userService.getUserRolesById(user.getId());

            if(list != null && list.size()>0){
                userRoleIds = new String[list.size()];
                for(int i=0;i<list.size();i++){
                    userRoleIds[i] = list.get(i).getId().getRole().getRoleId();
                    }
            }
        }
        return "editUI";
    }
    //保存编辑
    public String edit(){
        try {
            if(user != null){
                //处理头像
                if(headImg != null){
                    //1、保存头像到upload/user
                    //获取保存路径的绝对地址
                    String filePath = ServletActionContext.getServletContext().getRealPath("/upload/user");
                    String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
                    //复制文件
                    FileUtils.copyFile(headImg, new File(filePath, fileName));

                    //2、设置用户头像路径
                    user.setHeadImg("user/" + fileName);
                }

                userService.updateUserAndRole(user, userRoleIds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }
    //删除
    public String delete(){
        if(user != null && user.getId() != null){
            userService.delete(user.getId());
        }
        return "list";
    }
    //批量删除
    public String deleteSelected(){
        if (selectedRow.length>0){
            for (String id: selectedRow) {
                userService.delete(id);
            }
        }
        return  "list";
    }
    //导出
    public void exportExcel(){
        try{

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/x-excel");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Disposition","attachment;filename="+new String("用户列表.xls".getBytes(),"UTF-8"));
            ServletOutputStream outputStream = response.getOutputStream();

            userService.exportExcel(userService.findAll(),outputStream);

            if(outputStream != null){
                outputStream.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void doValidate(){
        //user不为空说明至少传回了account
        if (user != null){
            List<User> list = userService.findUsersByIdAndAccount(user.getId(),user.getAccount());
            String result = "true";
            if(list!=null && list.size()>0){
                result = "false";
            }
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html");
            try {
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(result.getBytes());
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String importExcel(){
        if(userExcel != null){

                userService.importExcel(userExcel,userExcelFileName);

        }
        return "list";
    }
    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String[] getSelectedRow() {
        return selectedRow;
    }
    public void setSelectedRow(String[] selectedRow) {
        this.selectedRow = selectedRow;
    }
    public File getHeadImg() {
        return headImg;
    }

    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }
    public String getHeadImgFileName() {
        return headImgFileName;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }

    public String getHeadImgContentType() {
        return headImgContentType;
    }

    public void setHeadImgContentType(String headImgContentType) {
        this.headImgContentType = headImgContentType;
    }
    public File getUserExcel() {
        return userExcel;
    }

    public void setUserExcel(File userExcel) {
        this.userExcel = userExcel;
    }

    public String getUserExcelFileName() {
        return userExcelFileName;
    }

    public void setUserExcelFileName(String userExcelFileName) {
        this.userExcelFileName = userExcelFileName;
    }

    public String getUserExcelContentType() {
        return userExcelContentType;
    }

    public void setUserExcelContentType(String userExcelContentType) {
        this.userExcelContentType = userExcelContentType;
    }
    public String[] getUserRoleIds() {
        return userRoleIds;
    }

    public void setUserRoleIds(String[] userRoleIds) {
        this.userRoleIds = userRoleIds;
    }
}
