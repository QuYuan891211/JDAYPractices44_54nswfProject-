<%--
  Created by IntelliJ IDEA.
  User: qy
  Date: 2017/2/22
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  response.sendRedirect(path+"/sys/login_toLoginUI.action");
%>

