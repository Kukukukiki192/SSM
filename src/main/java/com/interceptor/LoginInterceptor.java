package com.interceptor;

import com.dao.IUserDao;
import com.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.*;
import java.util.List;

/**
 * 拦截器应用处理流程
 *
 * 有一个登录页面，需要写一个Controller访问登录页面
 * 登录页面有一提交表单的动作。需要在Controller中处理。
 * a) 判断用户名密码是否正确（在控制台打印）
 * b) 如果正确,向session中写入用户信息（写入用户名username）
 * c) 跳转到商品列表
 *
 * 拦截器
 * a) 拦截用户请求，判断用户是否登录（登录请求不能拦截）
 * b) 如果用户已经登录。放行
 * c) 如果用户未登录，跳转到登录页面
 */

/**
 * 登录验证拦截器
 * 1、配置好拦截器要拦截哪些请求
 * 2、把这些配置放在容器中
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserDao userDao;

    /**
     * 目标方法执行之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("登录拦截器preHandle拦截的请求路径是: {}",requestURI);

        //session.getAttribute("user")和(User) request.getAttribute("user")取不到值,但可以取到参数值
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("用户名: {},密码: {}", username, password);
        User user = new User(username, password);

        //设置编码格式
//        response.setContentType("text/html;charset=utf-8");
//        response.setCharacterEncoding("utf-8");//不设置这个返回页面response.getWriter().append("<h1 style=\"text-align:center;\">未登录!请先登录</h1>");会出现这样的中文乱码：’???‘

        if(StringUtils.isBlank(username)){  //未登录(回到登录页面)
            log.info("未登录");
            //重定向到登录页面request.getContextPath():全局路径 可以是页面也可以是controller方法
            response.sendRedirect(request.getContextPath()+"/user/toLogin");
//            response.sendRedirect(request.getContextPath()+"/index.jsp");
//            response.getWriter().append("<h1 style=\"text-align:center;\">未登录!请先登录</h1>");
            return false;
        } else if (userDao.selectOne(user) == null){ //未满足这个条件的：user.getUsername().equals("..") && user.getPassword().equals("..")
            log.info("用户名或密码错误!");
//            response.getWriter().append("<h1 style=\"text-align:center;\">用户名或密码错误!</h1>");
            response.sendRedirect(request.getContextPath()+"/user/toError");
            return false; //未通过
        }
        log.info("已登录");
        return true; //放行
    }

    /**
     * 目标方法执行完成以后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("postHandle执行{}",modelAndView);
    }

    /**
     * 页面渲染以后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("afterCompletion执行异常{}",ex);
    }
}