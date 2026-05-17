package jp.co.fanmill.servlet.auth;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.fanmill.service.auth.PasswordResetService;

@WebServlet("/password/reset")
public class ResetPasswordServlet extends HttpServlet {

    private final PasswordResetService passwordResetService =
            new PasswordResetService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String userId =
                request.getParameter("userId");

        String newPassword =
                request.getParameter("newPassword");

        String newPasswordConfirm =
                request.getParameter("newPasswordConfirm");

        boolean result =
                passwordResetService.resetPassword(
                        userId,
                        newPassword,
                        newPasswordConfirm
                );

        if (!result) {

            request.setAttribute(
                    "userId",
                    userId
            );

            request.setAttribute(
                    "errorMessage",
                    "パスワードの入力内容を確認してください。"
            );

            request.getRequestDispatcher(
                    "/WEB-INF/jsp/auth/resetPassword.jsp"
            ).forward(request, response);

            return;
        }

        response.sendRedirect(
                request.getContextPath() + "/login"
        );
    }
}