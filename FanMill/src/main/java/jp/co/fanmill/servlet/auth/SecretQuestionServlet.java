package jp.co.fanmill.servlet.auth;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.fanmill.service.auth.PasswordResetService;

@WebServlet("/password/question")
public class SecretQuestionServlet extends HttpServlet {

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

        String secretAnswer =
                request.getParameter("secretAnswer");

        boolean result =
                passwordResetService.checkSecretAnswer(
                        userId,
                        secretAnswer
                );

        if (!result) {

            String secretQuestion =
                    passwordResetService.getSecretQuestion(userId);

            request.setAttribute(
                    "userId",
                    userId
            );

            request.setAttribute(
                    "secretQuestion",
                    secretQuestion
            );

            request.setAttribute(
                    "errorMessage",
                    "秘密の質問の回答が正しくありません。"
            );

            request.getRequestDispatcher(
                    "/WEB-INF/jsp/auth/secretQuestion.jsp"
            ).forward(request, response);

            return;
        }

        request.setAttribute(
                "userId",
                userId
        );

        request.getRequestDispatcher(
                "/WEB-INF/jsp/auth/resetPassword.jsp"
        ).forward(request, response);
    }
}