package cn.qy.nswf.user.action;

import cn.qy.nswf.user.Entity.User;
import cn.qy.nswf.user.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by qy on 2017/2/27.
 */

public class UserAction extends ActionSupport{
    @Resource
    private IUserService userService;
    private List<User> userList;
    private User user;
    private String[] selectedRow;
    private File headImg;


    private File userExcel;
    private String userExcelFileName;


    private String userExcelContentType;
    private String headImgFileName;
    private String headImgContentType;

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
                userService.save(user);
            }
        }
            return "list";
    }
    //跳转到编辑页面
    public String editUI(){
        if (user != null && user.getId() != null) {
            user = userService.findById(user.getId());
        }
        return "editUI";
    }
    //保存编辑
    public String edit(){
        if(user != null){
            userService.update(user);
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
}
