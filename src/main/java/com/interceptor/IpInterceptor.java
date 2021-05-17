package com.interceptor;

import com.dao.IIpFilterDao;
import com.domain.IpFilter;
import com.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * IP拦截器
 */
@Slf4j
public class IpInterceptor implements HandlerInterceptor {

    @Autowired
    private IIpFilterDao ipFilterDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("IP拦截器preHandle拦截的请求路径是: {}",requestURI);
        //过滤IP,若用户在白名单内，则放行
        String ipAddress = IpUtil.getRealIP(request); //全是127.0.0.1 ?
        log.info("user IP address: "+ipAddress);
        if(StringUtils.isBlank(ipAddress))
            return false;
        IpFilter ipFilter=new IpFilter();
        ipFilter.setModule("user-login");//模块
        ipFilter.setIp(ipAddress);//IP地址
        ipFilter.setMark(0);//白名单
        List<IpFilter> ips=ipFilterDao.selectList(ipFilter);
        if(ips.isEmpty()){
            response.getWriter().append("<h1 style=\"text-align:center;\">Not allowed!</h1>");
            log.info("Not allowed!");
            return false;
        }
        log.info("Allowed!");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("postHandle执行{}",modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("afterCompletion执行异常{}",ex);
    }
}
