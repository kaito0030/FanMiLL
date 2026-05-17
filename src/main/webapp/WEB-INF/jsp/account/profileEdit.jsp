<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="jp.co.fanmill.dto.UserDTO" %>

<!DOCTYPE html>
<html lang="ja">

<head>

    <meta charset="UTF-8">

    <title>プロフィール編集 | FanMILL</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<jsp:include page="/WEB-INF/jsp/common/header.jsp" />

<div class="main-container">

    <%
        UserDTO loginUser =
                (UserDTO) request.getAttribute("loginUser");
    %>

    <div class="profile-edit-container">

        <h2 class="page-title">

            プロフィール編集

        </h2>

        <!-- error -->
        <%
            String errorMessage =
                    (String) request.getAttribute("errorMessage");
        %>

        <% if (errorMessage != null) { %>

            <div class="error-message">

                <%= errorMessage %>

            </div>

        <% } %>

        <!-- form -->
        <form action="${pageContext.request.contextPath}/profile/edit"
              method="post">

            <!-- userName -->
            <div class="form-group">

                <label for="userName">

                    表示名

                </label>

                <input type="text"
                       id="userName"
                       name="userName"
                       class="form-input"
                       maxlength="50"
                       value="<%= loginUser.getUserName() %>"
                       required>

            </div>

            <!-- profileText -->
            <div class="form-group">

                <label for="profileText">

                    プロフィール

                </label>

                <textarea id="profileText"
                          name="profileText"
                          class="form-textarea"
                          rows="6"
                          maxlength="1000"><%= 
                              loginUser.getProfileText() == null
                              ? ""
                              : loginUser.getProfileText()
                          %></textarea>

            </div>

            <!-- submit -->
            <button type="submit"
                    class="submit-button">

                更新する

            </button>

        </form>

    </div>

</div>

<script src="${pageContext.request.contextPath}/js/menu.js"></script>

</body>

</html>