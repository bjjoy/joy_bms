package cn.bjjoy.web.bms.auth.service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bjjoy on 2017/11/27
 **/
@WebFilter(filterName="sessionFilter",urlPatterns="/test/*")
public class SessionFilter implements Filter {

    @Autowired
    AuthUserService authUserService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        CurrentUser currentUser = authUserService.getCurrentUser();
        if (currentUser != null){
            //存在用户刷新redis用户过期时间
            authUserService.updateUserTime();
        }else {
            httpServletResponse.sendRedirect("/user/error");
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
