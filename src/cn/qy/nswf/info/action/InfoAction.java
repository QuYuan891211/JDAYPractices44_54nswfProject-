package cn.qy.nswf.info.action;

import cn.qy.core.Action.BaseAction;
import cn.qy.core.exception.SysException;
import cn.qy.nswf.info.entity.Info;
import cn.qy.nswf.info.service.IInfoService;
import cn.qy.nswf.info.service.imp.InfoService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by qy on 2017/4/26.
 */
public class InfoAction extends BaseAction {
    @Resource
    private IInfoService infoService;
    private List<Info> infoList;
    private Info info;

    private String[] privilegeIds;


    public String TestUI(){
        return "TestUI";
    }
    //列表页面
    public String listUI() throws SysException {
        //put 方法仅用于一次请求，类似servlet里面的HttpServletRequest里面的set
        ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
        infoList = infoService.findAll();
        return "listUI";
    }
    //跳转到新增页面
    public String addUI(){
        ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
        info = new Info();
        info.setCreateTime(new Timestamp(new Date().getTime()));
        return "addUI";
    }
    //保存新增
    public String add(){
        infoService.save(info);
        return "list";
    }
    //跳转到编辑页面
    public String editUI(){
        //加载分类集合
        ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
        if (info != null && info.getInfoId() != null) {
            info = infoService.findById(info.getInfoId());
        }
        return "editUI";
    }
    //保存编辑
    public String edit(){
        if(info != null){
            infoService.update(info);
        }
        return "list";
    }
    //删除
    public String delete(){
        if(info != null && info.getInfoId() != null){
            infoService.delete(info.getInfoId());
        }
        return "list";
    }
    //批量删除
    public String deleteSelected(){
        if (selectedRow.length>0){
            for (String id: selectedRow) {
                infoService.delete(id);
            }
        }
        return  "list";
    }

    public void publicInfo() throws IOException {
        if(info!=null){
            Info tem = infoService.findById(info.getInfoId());
            tem.setState(info.getState());
            //不能直接update(info),因为会把其他的属性变为空
            infoService.update(tem);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write("update successful".getBytes());
            outputStream.close();
        }
    }

    public List<Info> getInfoList() {
        return infoList;
    }
    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }

    public Info getInfo() {
        return info;
    }
    public void setInfo(Info info) {
        this.info = info;
    }
    public IInfoService getInfoService() {
        return infoService;
    }

    public void setInfoService(IInfoService infoService) {
        this.infoService = infoService;
    }

    public String[] getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(String[] privilegeIds) {
        this.privilegeIds = privilegeIds;
    }
}
