<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isErrorPage="true" %>

<!DOCTYPE html>
<html lang="ja">

<head>

    <meta charset="UTF-8">

    <title>エラー | FanMILL</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="error-body">

<div class="error-container">

    <div class="error-card">

        <h1 class="error-title">
            ERROR
        </h1>

        <p class="error-message-text">

            システムエラーが発生しました。

        </p>

        <p class="error-description">

            時間をおいて再度お試しください。

        </p>

        <!-- 開発用 -->
        <%
            if (exception != null) {
        %>

        <div class="error-detail">

            <p>

                <strong>Exception :</strong>

            </p>

            <pre><%= exception.getMessage() %></pre>

        </div>

        <%
            }
        %>

        <!-- links -->
        <div class="error-links">

            <a href="${pageContext.request.contextPath}/login"
               class="submit-button">

                ログイン画面へ戻る

            </a>

        </div>

    </div>

</div>

</body>

</html>