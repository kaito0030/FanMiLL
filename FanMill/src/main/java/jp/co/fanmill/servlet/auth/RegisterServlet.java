package jp.co.fanmill.servlet.auth;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.fanmill.service.auth.RegisterService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final RegisterService registerService =
            new RegisterService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/jsp/auth/register.jsp"
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

        String password =
                request.getParameter("password");

        String passwordConfirm =
                request.getParameter("passwordConfirm");

        String userName =
                request.getParameter("userName");

        String secretQuestion =
                request.getParameter("secretQuestion");

        String secretAnswer =
                request.getParameter("secretAnswer");

        boolean result =
                registerService.register(
                        userId,
                        password,
                        passwordConfirm,
                        userName,
                        secretQuestion,
                        secretAnswer
                );

        if (!result) {

            request.setAttribute(
                    "errorMessage",
                    "入力内容を確認してください。"
            );

            request.getRequestDispatcher(
                    "/WEB-INF/jsp/auth/register.jsp"
            ).forward(request, response);

            return;
        }

        response.sendRedirect(
                request.getContextPath() + "/login"
        );
    }
}
           