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

@WebServlet("/post/delete")
public class DeletePostServlet
        extends HttpServlet {

    private final PostService postService =
            new PostService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        UserDTO loginUser =
                (UserDTO) session.getAttribute("loginUser");

        int postId =
                Integer.parseInt(
                        request.getParameter("postId")
                );

        postService.deletePost(
                postId,
                loginUser.getUserId()
        );

        response.sendRedirect(
                request.getContextPath() + "/home"
        );
    }
}