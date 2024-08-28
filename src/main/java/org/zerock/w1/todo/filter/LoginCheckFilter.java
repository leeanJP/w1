package org.zerock.w1.todo.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.zerock.w1.todo.dto.MemberDTO;
import org.zerock.w1.todo.service.MemberService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(urlPatterns = "/todo/*")
@Log4j2
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("Login check Filter...");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();

        if(session.getAttribute("loginInfo") == null) {
            //쿠키 체크
            Cookie cookie = findCookie(req.getCookies(), "remember-me");
            if(cookie != null){
                log.info("쿠키가 존재하는 상황");
                String uuid = cookie.getValue();

                try {
                    MemberDTO dto = MemberService.INSTANCE.getByUUID(uuid);

                    log.info("쿠키 값으로 조회한 사용자 정보: " +dto);

                    session.setAttribute("loginInfo", dto);
                }catch (Exception e){
                    e.printStackTrace();
                    resp.sendRedirect("/login");
                }

                filterChain.doFilter(req,resp);
                return;
            }
            resp.sendRedirect("/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }


    private Cookie findCookie(Cookie[] cookies, String name){
        if(cookies == null || cookies.length == 0){
            return null;
        }

        Optional<Cookie> result = Arrays.stream(cookies).filter(ck-> ck.getName().equals(name)).findFirst();
        /*Optional 클래스는 null 처리하는데 유용한 클래스
            isPresent > 값이 존재하면 true 존재하지 않으면 false 반환
        * */

        return result.isPresent() ? result.get() : null;


    }
}
