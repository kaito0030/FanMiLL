"use strict";

document.addEventListener("DOMContentLoaded", function () {
    const menuButton = document.getElementById("menuButton");
    const closeButton = document.getElementById("closeButton");
    const sideMenu = document.getElementById("sideMenu");
    const overlay = document.getElementById("overlay");

    function openMenu() {
        sideMenu.classList.add("open");
        overlay.classList.add("show");
    }

    function closeMenu() {
        sideMenu.classList.remove("open");
        overlay.classList.remove("show");
    }

    menuButton.addEventListener("click", function () {
        if (sideMenu.classList.contains("open")) {
            closeMenu();
        } else {
            openMenu();
        }
    });

    closeButton.addEventListener("click", closeMenu);
    overlay.addEventListener("click", closeMenu);
});