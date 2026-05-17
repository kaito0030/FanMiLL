<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<title>ログイン | FanMILL</title>

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

			<!-- ログインフォーム -->
			<form action="${pageContext.request.contextPath}/login" method="post">

				<div class="form-group">

					<label for="userId"> ユーザID </label> <input type="text" id="userId"
						name="userId" class="form-input" maxlength="50" required>

				</div>

				<div class="form-group">

					<label for="password"> パスワード </label> <input type="password"
						id="password" name="password" class="form-input" maxlength="100"
						required>

				</div>

				<button type="submit" class="submit-button login-button">

					ログイン</button>

			</form>

			<!-- リンク -->
			<div class="auth-links">

				<a href="${pageContext.request.contextPath}/register"> 新規登録はこちら

				</a> <a href="${pageContext.request.contextPath}/password/forgot">

					パスワードを忘れた場合 </a>

			</div>

		</div>

	</div>

</body>

</html>