/**
 * Created by qy on 2017/3/5.
 */

    //添加方法
    function doAdd(actionName){
        document.forms[0].action="${basePath}nswf/"+actionName+".action";
        document.forms[0].submit();
    }
    function doEdit(actionName,id){
        document.forms[0].action="${basePath}nswf/"+actionName+".action?user.id="+id;
        document.forms[0].submit();
}
function doDelete(actionName,id){
    document.forms[0].action="${basePath}nswf/"+actionName+".action?user.id="+id;
    document.forms[0].submit();
}
function doDeleteAll(actionName){
    document.forms[0].action="${basePath}nswf/"+actionName+".action";
    document.forms[0].submit();
}

