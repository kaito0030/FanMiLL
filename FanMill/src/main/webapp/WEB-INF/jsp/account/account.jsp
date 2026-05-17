<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="jp.co.fanmill.dto.UserDTO"%>
<%@ page import="jp.co.fanmill.dto.PostDTO"%>
<%@ page import="jp.co.fanmill.util.TextUtil"%>

<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<title>アカウント | FanMILL</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />

	<div class="main-container">

		<%
        UserDTO accountUser =
                (UserDTO) request.getAttribute("accountUser");

        UserDTO loginUser =
                (UserDTO) session.getAttribute("loginUser");

        List<PostDTO> postList =
                (List<PostDTO>) request.getAttribute("postList");

        boolean isMyPage =
                loginUser != null
                        && accountUser != null
                        && loginUser.getUserId().equals(accountUser.getUserId());
    %>

		<div class="account-profile-card">



			<div class="account-name">
				<%= accountUser.getUserName() %>
			</div>

			<div class="account-id">
				@<%= accountUser.getUserId() %>
			</div>

			<div class="account-profile-text">
				<%
                String profileText = accountUser.getProfileText();
                if (profileText == null || profileText.isEmpty()) {
            %>
				プロフィールは未設定です。
				<%
                } else {
            %>
				<%=TextUtil.toHtml(profileText)%>
				<%
                }
            %>
			</div>

			<% if (isMyPage) { %>

			<div class="account-point">
				保有MILLポイント：
				<%= accountUser.getMillPoint() %>
				pt
			</div>

			<div class="account-actions">
				<a class="submit-button"
					href="${pageContext.request.contextPath}/profile/edit">
					プロフィール編集 </a>
			</div>

			<% } %>

		</div>

		<div class="timeline-container">

			<h2 class="page-title">投稿一覧</h2>

			<% if (postList != null && !postList.isEmpty()) { %>

			<% for (PostDTO post : postList) { %>

			<div class="post-card">

				<div class="post-header">

					<div class="post-user">
						<%= post.getUserName() %>
					</div>

					<div class="post-date">
						<%= post.getCreatedAt() %>
					</div>

				</div>

				<div class="post-content">
					<%= TextUtil.toHtml(post.getContent()) %>
				</div>
				<%
			        boolean isMyPost = loginUser != null
					&& loginUser.getUserId()
					.equals(post.getUserId());
			     %>
				<%
			        if (isMyPost) {
			     %>

				<form action="${pageContext.request.contextPath}/post/delete"
					method="post" class="delete-form">

					<input type="hidden" name="postId" value="<%=post.getPostId()%>">

					<button type="submit" class="delete-button"
						onclick="return confirm('投稿を削除しますか？')">削除</button>

				</form>
				<%
			     }
			     %>

			</div>

			<% } %>

			<% } else { %>

			<div class="empty-message">投稿がありません。</div>

			<% } %>

		</div>

	</div>

	<script src="${pageContext.request.contextPath}/js/menu.js"></script>

</body>
</html>