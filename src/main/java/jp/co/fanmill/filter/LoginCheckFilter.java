package jp.co.fanmill.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({
        "/home",
        "/post",
        "/account",
        "/profile/edit",
        "/post/delete"
})
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        HttpServletResponse res =
                (HttpServletResponse) response;

        HttpSession session =
                req.getSession(false);

        boolean isLoggedIn =
                session != null
                        && session.getAttribute("loginUser") != null;

        if (!isLoggedIn) {

            res.sendRedirect(
                    req.getContextPath() + "/login"
            );

            return;
        }

        chain.doFilter(request, response);
    }
}