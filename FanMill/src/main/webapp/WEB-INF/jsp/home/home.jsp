<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="jp.co.fanmill.dto.PostDTO"%>
<%@ page import="jp.co.fanmill.util.TextUtil"%>
<%@ page import="jp.co.fanmill.dto.UserDTO"%>

<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">

<title>ホーム | FanMILL</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

	<!-- header -->
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />

	<div class="main-container">
		<%
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		%>

		<!-- 投稿フォーム -->
		<div class="post-form-container">

			<h2 class="page-title">ホーム</h2>

			<!-- エラーメッセージ -->
			<%
			String errorMessage = (String) request.getAttribute("errorMessage");
			%>

			<%
			if (errorMessage != null) {
			%>

			<div class="error-message">

				<%=errorMessage%>

			</div>

			<%
			}
			%>

			<!-- 投稿フォーム -->
			<form action="${pageContext.request.contextPath}/post" method="post">

				<div class="form-group">

					<label for="content"> 投稿内容 </label>

					<textarea id="content" name="content" class="form-textarea"
						rows="4" maxlength="200" placeholder="いま何してる？" required></textarea>

				</div>

				<button type="submit" class="submit-button">投稿する</button>

			</form>

		</div>

		<!-- 投稿一覧 -->
		<div class="timeline-container">

			<h2 class="page-title">タイムライン</h2>

			<%
			List<PostDTO> postList = (List<PostDTO>) request.getAttribute("postList");
			%>

			<%
			if (postList != null && !postList.isEmpty()) {
			%>

			<%
			for (PostDTO post : postList) {
			%>

			<div class="post-card">

				<div class="post-header">

					<!-- user -->
					<div class="post-user">

						<a
							href="${pageContext.request.contextPath}/account?userId=<%= post.getUserId() %>">

							<%=post.getUserName()%>

						</a>

					</div>

					<!-- date -->
					<div class="post-date">

						<%=post.getCreatedAt()%>

					</div>

				</div>

				<!-- content -->
				<div class="post-content"><%=TextUtil.toHtml(post.getContent())%></div>

				<%
				boolean isMyPost = ((loginUser != null)
						&& (loginUser.getUserId().equals(post.getUserId())));
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
			<%
			}
			%>

			<%
			} else {
			%>

			<div class="empty-message">投稿がありません。</div>

			<%
			}
			%>

		</div>

	</div>

	<!-- menu.js -->
	<script src="${pageContext.request.contextPath}/js/menu.js"></script>

</body>

</html>