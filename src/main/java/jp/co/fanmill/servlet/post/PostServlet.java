package jp.co.fanmill.servlet.post;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.fanmill.dto.UserDTO;
import jp.co.fanmill.service.post.PostService;

@WebServlet("/post")
public class PostServlet extends HttpServlet {

    private final PostService postService =
            new PostService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session =
                request.getSession(false);

        if (session == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }

        UserDTO loginUser =
                (UserDTO) session.getAttribute("loginUser");

        if (loginUser == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }
        
        Integer parentPostId = null;

        String parentPostIdStr =
                request.getParameter(
                        "parentPostId"
                );

        if (parentPostIdStr != null
                && !parentPostIdStr.isBlank()) {

            try {

                parentPostId =
                        Integer.parseInt(
                                parentPostIdStr
                        );

            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/home"
                );

                return;
            }
        }
        
        String content =
                request.getParameter("content");

        boolean result =
                postService.createPost(
                		   parentPostId,
                        loginUser.getUserId(),
                        content
                );

        if (!result) {

            request.setAttribute(
                    "errorMessage",
                    "投稿内容を確認してください。"
            );

            request.getRequestDispatcher(
                    "/home"
            ).forward(request, response);

            return;
        }

        response.sendRedirect(
                request.getContextPath() + "/home"
        );
    }
}