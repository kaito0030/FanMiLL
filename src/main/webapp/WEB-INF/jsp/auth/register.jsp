<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">

<title>ユーザ登録 | FanMILL</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="auth-body">

	<div class="auth-container">

		<div class="auth-card register-card">

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

			<!-- 登録フォーム -->
			<form action="${pageContext.request.contextPath}/register"
				method="post">

				<!-- userId -->
				<div class="form-group">

					<label for="userId"> ユーザID </label> <input type="text" id="userId"
						name="userId" class="form-input" maxlength="50" required>

				</div>

				<!-- password -->
				<div class="form-group">

					<label for="password"> パスワード </label> <input type="password"
						id="password" name="password" class="form-input" maxlength="100"
						required>

				</div>

				<!-- passwordConfirm -->
				<div class="form-group">

					<label for="passwordConfirm"> パスワード確認 </label> <input
						type="password" id="passwordConfirm" name="passwordConfirm"
						class="form-input" maxlength="100" required>

				</div>

				<!-- userName -->
				<div class="form-group">

					<label for="userName"> 表示名 </label> <input type="text"
						id="userName" name="userName" class="form-input" maxlength="50"
						required>

				</div>

				<!-- secretQuestion -->
				<div class="form-group">

					<label for="secretQuestion"> 秘密の質問 </label> <input type="text"
						id="secretQuestion" name="secretQuestion" class="form-input"
						maxlength="255" placeholder="例：好きな食べ物は？" required>

				</div>

				<!-- secretAnswer -->
				<div class="form-group">

					<label for="secretAnswer"> 秘密の質問の回答 </label> <input type="text"
						id="secretAnswer" name="secretAnswer" class="form-input"
						maxlength="255" required>

				</div>

				<!-- submit -->
				<button type="submit" class="submit-button auth-button">登録

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