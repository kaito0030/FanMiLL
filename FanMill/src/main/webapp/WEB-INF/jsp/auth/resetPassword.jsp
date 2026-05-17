<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">

<title>新パスワード設定 | FanMILL</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="auth-body">

	<div class="auth-container">

		<div class="auth-card">

			<h1 class="auth-logo">
				<img src="${pageContext.request.contextPath}/img/logo.png"
					alt="FanMILL" class="auth-logo-image">
			</h1>


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

			<!-- パスワード更新フォーム -->
			<form action="${pageContext.request.contextPath}/password/reset"
				method="post">

				<!-- hidden -->
				<input type="hidden" name="userId" value="${userId}">

				<!-- newPassword -->
				<div class="form-group">

					<label for="newPassword"> 新しいパスワード </label> <input type="password"
						id="newPassword" name="newPassword" class="form-input"
						maxlength="100" required>

				</div>

				<!-- confirm -->
				<div class="form-group">

					<label for="newPasswordConfirm"> 新しいパスワード確認 </label> <input
						type="password" id="newPasswordConfirm" name="newPasswordConfirm"
						class="form-input" maxlength="100" required>

				</div>

				<!-- submit -->
				<button type="submit" class="submit-button auth-button">

					パスワード更新</button>

			</form>

			<!-- links -->
			<div class="auth-links">

				<a href="${pageContext.request.contextPath}/login"> ログイン画面へ戻る </a>

			</div>

		</div>

	</div>

</body>

</html>