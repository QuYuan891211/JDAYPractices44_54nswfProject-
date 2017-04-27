package cn.qy.core.filter;

import cn.qy.core.constant.Constant;
import cn.qy.core.permission.PermissionCheck;
import cn.qy.nswf.user.entity.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by qy on 2017/4/21.
 */
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session =request.getSession();
        String uri = request.getRequestURI().replaceAll(request.getContextPath(),"");
        if (uri.length() > 0 && !uri.contains("sys/login")){
            //判断是否已经登录过了
            if(session.getAttribute(Constant.User)!=null){
                if(uri.contains("/nsfw/")){

                    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
                    PermissionCheck permissionCheck = (PermissionCheck)wac.getBean("permissionCheck");
                    User user = (User)session.getAttribute(Constant.User);
                    if (permissionCheck.isAccessible("nsfw",user)){
                        filterChain.doFilter(request,response);
                    }else {
                        response.sendRedirect(request.getContextPath()+"/sys/login_toNoPermissionUI.action");
                    }

                }else {
                    filterChain.doFilter(request,response);
                }
            }else{
                //没有登录，需要重新登录
                response.sendRedirect(request.getContextPath()+"/sys/login_toLoginUI.action");
            }
        }else {
            //登录页面，直接放行
            filterChain.doFilter(servletRequest,response);
        }
    }

    @Override
    public void destroy() {

    }
}
