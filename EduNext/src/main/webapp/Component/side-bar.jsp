<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : side-bar
    Created on : Apr 7, 2024, 9:38:51 PM
    Author     : Vanhle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sidebar</title>

    </head>
    <body>
        <div class="d-flex flex-column flex-shrink-0 sidebar-wrap" id="sidebar">
            <a href="#" class="text-decoration-none logo-wrap">
                <div class="icon-wrap">
                    <img src="${pageContext.request.contextPath}/Image/Logo/FPT-logo-2010.png" class="mlogo" />
                </div>
                <span style="font-weight: bolder ;font-size: 20px; color: #000;">EduNext</span>
            </a>
            <hr>
            <ul class="nav nav-pills flex-column mb-auto mt-5">
                <li class="nav-item">
                    <a href="home" class="nav-link " aria-current="page">
                        <div class="icon-wrap">
                            <i data-lucide="home"></i>
                        </div>
                        <span> Home</span>
                    </a>
                </li>
                <li>
                    <a href="candidate-list" class="nav-link">
                        <div class="icon-wrap">
                            <i data-lucide="users"></i>
                        </div>
                        <span>Candidate</span>
                    </a>
                </li>
                <li>
                    <a href="job-list" class="nav-link">
                        <div class="icon-wrap">
                            <i data-lucide="briefcase-business"></i>
                        </div>
                        <span>Job</span>
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link">
                        <div class="icon-wrap">
                            <i data-lucide="message-circle-code"></i>
                        </div>
                        <span>Interview</span>
                    </a>
                </li>
                <c:if test="${sessionScope.loggedInUser != null && sessionScope.loggedInUser.userRoleId != 3}">
                    <li>
                        <a href="offer-list" class="nav-link">
                            <div class="icon-wrap">
                                <i data-lucide="file-check-2"></i>
                            </div>
                            <span>Offer</span>
                        </a>
                    </li>
                </c:if>
                <li>
                    <a href="user-list" class="nav-link">
                        <div class="icon-wrap">
                            <i data-lucide="user-plus"></i>
                        </div>
                        <span>User</span>
                    </a>
                </li>
            </ul>
            <hr>
        </div>
        <!--lucide-->
        <script src="https://unpkg.com/lucide@latest/dist/umd/lucide.js"></script>
        <script src="https://unpkg.com/lucide@latest"></script>
        <script>
            //icon lucide
            lucide.createIcons();
        </script>

    </body>
</html>
