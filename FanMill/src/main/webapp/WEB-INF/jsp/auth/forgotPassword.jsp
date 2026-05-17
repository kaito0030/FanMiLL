<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">

<title>パスワード再設定 | FanMILL</title>

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



			<p class="auth-description">登録済みのユーザIDを入力してください</p>

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

			<!-- userId入力フォーム -->
			<form action="${pageContext.request.contextPath}/password/forgot"
				method="post">

				<div class="form-group">

					<label for="userId"> ユーザID </label> <input type="text" id="userId"
						name="userId" class="form-input" maxlength="50" required>

				</div>

				<button type="submit" class="submit-button auth-button">次へ

				</button>

			</form>

			<!-- links -->
			<div class="auth-links">

				<a href="${pageContext.request.contextPath}/login"> ログイン画面へ戻る </a>

			</div>

		</div>

	</div>

</body>

</html>