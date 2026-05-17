<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<header class="app-header">

    <!-- left -->
    <div class="header-left">

        <button type="button"
                class="menu-button"
                id="menuButton">

            ☰

        </button>

    </div>

    <!-- center -->
    <div class="header-center">

        <a href="${pageContext.request.contextPath}/home"
           class="header-logo-link">

            <img src="${pageContext.request.contextPath}/img/logo.png"
                 alt="FanMILL"
                 class="header-logo-image">

        </a>

    </div>

    <!-- right -->
    <div class="header-right">

        <span class="header-user">

            ${sessionScope.loginUser.userName}

        </span>

    </div>

</header>

<!-- overlay -->
<div class="overlay" id="overlay"></div>

<!-- side menu -->
<nav class="side-menu" id="sideMenu">

    <div class="side-menu-header">

        <span class=side-menu-title>MENU</span>

        <button type="button"
                class="close-button"
                id="closeButton">

            ×

        </button>

    </div>

    <ul class="side-menu-list">

        <li>
            <a href="${pageContext.request.contextPath}/home">
                ホーム
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/account?userId=${sessionScope.loginUser.userId}">
                マイページ
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/logout">
                ログアウト
            </a>
        </li>

    </ul>

</nav>