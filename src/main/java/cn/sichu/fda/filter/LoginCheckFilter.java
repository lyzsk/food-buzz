package cn.sichu.fda.filter;

import cn.sichu.fda.common.BaseContext;
import cn.sichu.fda.common.Result;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sichu
 * @date 2022/12/20
 **/
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    /**
     * 路径匹配器
     */
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest,
        ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        // 获取request的URI
        String requestURI = request.getRequestURI();
        log.info("拦截到请求 {}", requestURI);
        // 白名单url
        String[] urls =
            new String[] {"/employee/login", "/employee/logout", "/backend/**",
                "/front/**", "/common/**"};
        // 判断本次请求是否需要处理
        boolean check = check(urls, requestURI);
        if (check) {
            log.info("本次请求 {} 不需要处理", requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        // 判断登陆状态,如果已登录,直接放行
        if (request.getSession().getAttribute("employee") != null) {
            log.info("用户已登录,用户id为: {}",
                request.getSession().getAttribute("employee"));
            long id = Thread.currentThread().getId();
            log.info("login filter 的线程id为:{}", id);
            // 从session获取userId, set 到 LocalThread 中
            Long userId = (Long)request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request, response);
            return;
        }
        log.info("用户尚未登陆");
        response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
        return;
    }

    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
