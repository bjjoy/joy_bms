//package cn.bjjoy.web.joy_bms.auth.service;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Created by GXM on 2017/11/27
// **/
//public class SessionFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
//        //TODO 通过sessionId获取当前用户信息
//        String sessionId = httpServletRequest.getSession().getId();
//        //
//        filterChain.doFilter(httpServletRequest,httpServletResponse);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
