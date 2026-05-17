package jp.co.fanmill.servlet.post;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.fanmill.dto.PostDTO;
import jp.co.fanmill.dto.UserDTO;
import jp.co.fanmill.service.post.PostListService;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private final PostListService postService =
            new PostListService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
    	
        HttpSession session =
                request.getSession(false);

        UserDTO loginUser =
                (UserDTO) session.getAttribute("loginUser");

        List<PostDTO> postList =
                postService.getTimeline(loginUser.getUserId());

        request.setAttribute(
                "postList",
                postList
        );

        request.getRequestDispatcher(
               "/WEB-INF/jsp/home/home.jsp"
        ).forward(request, response);
    }
}