package jp.co.fanmill.servlet.account;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.fanmill.dto.UserDTO;
import jp.co.fanmill.service.account.ProfileService;

@WebServlet("/profile/edit")
public class ProfileEditServlet extends HttpServlet {

    private final ProfileService profileService =
            new ProfileService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        UserDTO loginUser =
                (UserDTO) session.getAttribute("loginUser");

        request.setAttribute(
                "loginUser",
                loginUser
        );

        request.getRequestDispatcher(
                "/WEB-INF/jsp/account/profileEdit.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session =
                request.getSession(false);

        UserDTO loginUser =
                (UserDTO) session.getAttribute("loginUser");

        String userName =
                request.getParameter("userName");

        String profileText =
                request.getParameter("profileText");

        boolean result =
                profileService.updateProfile(
                        loginUser.getUserId(),
                        userName,
                        profileText
                );

        if (!result) {

            request.setAttribute(
                    "errorMessage",
                    "プロフィールの入力内容を確認してください。"
            );

            request.getRequestDispatcher(
                    "/WEB-INF/jsp/account/profileEdit.jsp"
            ).forward(request, response);

            return;
        }

        loginUser.setUserName(userName);
        loginUser.setProfileText(profileText);

        session.setAttribute(
                "loginUser",
                loginUser
        );

        response.sendRedirect(
                request.getContextPath()
                        + "/account?userId="
                        + loginUser.getUserId()
        );
    }
}