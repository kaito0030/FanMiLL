package jp.co.fanmill.servlet.account;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.fanmill.dto.PostDTO;
import jp.co.fanmill.dto.UserDTO;
import jp.co.fanmill.service.account.ProfileService;
import jp.co.fanmill.service.post.PostListService;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private final ProfileService profileService =
            new ProfileService();

    private final PostListService postListService =
            new PostListService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String userId =
                request.getParameter("userId");

        UserDTO accountUser =
                profileService.getAccount(userId);

        if (accountUser == null) {

            request.setAttribute(
                    "errorMessage",
                    "ユーザが存在しません。"
            );

            request.getRequestDispatcher(
                    "/WEB-INF/jsp/error/error.jsp"
            ).forward(request, response);

            return;
        }

        List<PostDTO> postList =
                postListService.getUserPosts(userId);

        request.setAttribute(
                "accountUser",
                accountUser
        );

        request.setAttribute(
                "postList",
                postList
        );

        request.getRequestDispatcher(
                "/WEB-INF/jsp/account/account.jsp"
        ).forward(request, response);
    }
}