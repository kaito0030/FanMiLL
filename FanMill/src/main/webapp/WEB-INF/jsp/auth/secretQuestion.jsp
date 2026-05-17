<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">

<title>秘密の質問 | FanMILL</title>

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

			<!-- secret question -->
			<div class="secret-question-box">

				<p class="secret-question-label">秘密の質問</p>

				<p class="secret-question-text">${secretQuestion}</p>

			</div>

			<!-- answer form -->
			<form action="${pageContext.request.contextPath}/password/question"
				method="post">

				<!-- hidden -->
				<input type="hidden" name="userId" value="${userId}">

				<!-- answer -->
				<div class="form-group">

					<label for="secretAnswer"> 回答 </label> <input type="text"
						id="secretAnswer" name="secretAnswer" class="form-input"
						maxlength="255" required>

				</div>

				<!-- submit -->
				<button type="submit" class="submit-button auth-button">確認

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