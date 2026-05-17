package jp.co.fanmill.servlet.auth;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.fanmill.dto.UserDTO;
import jp.co.fanmill.service.auth.AuthService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/auth/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        UserDTO loginUser = authService.login(userId, password);

        if (loginUser == null) {

            request.setAttribute(
                    "errorMessage",
                    "ユーザIDまたはパスワードが正しくありません。"
            );

            request.getRequestDispatcher("/WEB-INF/jsp/auth/login.jsp")
                    .forward(request, response);

            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loginUser);

        response.sendRedirect(
                request.getContextPath() + "/home"
        );
    }
}