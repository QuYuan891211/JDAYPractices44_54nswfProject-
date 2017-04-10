<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: qy
  Date: 2017/3/6
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<s:if test="exception.errorMessage != '' && exception.errorMessage != null">
    <s:property value="exception.errorMessage"></s:property>
</s:if>
<s:else>
    不是我们抛出的异常，<s:property value="exception.message"></s:property>
</s:else>
</body>
</html>
