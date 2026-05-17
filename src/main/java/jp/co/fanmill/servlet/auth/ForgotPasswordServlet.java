package jp.co.fanmill.servlet.auth;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.fanmill.service.auth.PasswordResetService;

@WebServlet("/password/forgot")
public class ForgotPasswordServlet extends HttpServlet {

    private final PasswordResetService passwordResetService =
            new PasswordResetService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/jsp/auth/forgotPassword.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String userId =
                request.getParameter("userId");

        String secretQuestion =
                passwordResetService.getSecretQuestion(userId);

        if (secretQuestion == null) {

            request.setAttribute(
                    "errorMessage",
                    "ユーザIDが存在しません。"
            );

            request.getRequestDispatcher(
                    "/WEB-INF/jsp/auth/forgotPassword.jsp"
            ).forward(request, response);

            return;
        }

        request.setAttribute(
                "userId",
                userId
        );

        request.setAttribute(
                "secretQuestion",
                secretQuestion
        );

        request.getRequestDispatcher(
                "/WEB-INF/jsp/auth/secretQuestion.jsp"
        ).forward(request, response);
    }
}