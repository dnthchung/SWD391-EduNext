<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : nav-bar
    Created on : Apr 7, 2024, 9:38:40 PM
    Author     : Vanhle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="icon" type="image/x-icon" href="Image/Logo/ims-logo.png">

        <!-- Bootstrap 5 CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <!-- multi select -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" />
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css" />
        <!-- link to css -->   
    </head>
    <body>
        <nav class="navbar navbar-expand-sm ">
            <div class="container-fluid">
                <div class="mpage-name">
                    <h2 class="" style="color: black;">${URL}</h2>
                </div>
                <div class="collapse navbar-collapse" id="mynavbar">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="#"></a>
                        </li>
                    </ul>
                    <!--đã login-->
                    <c:if test="${sessionScope.loggedInUser != null}">
                        <div class="d-flex user-area">
                            <div class="user-info d-flex flex-column align-items-center">
                                <span class="username">${sessionScope.loggedInUser.useName}</span>
                                <c:forEach var="role" items="${sessionScope.userRoles}">
                                    <c:if test="${role.userRoleId == sessionScope.loggedInUser.userRoleId}">
                                        <p>${role.roleName}</p>
                                    </c:if>
                                </c:forEach>

                            </div>
                            <div style="padding: 15px;">
                                <i style="height: 20px;" data-lucide="user"></i>
                            </div>
                            <div class="d-flex align-items-center">
                                <a href="#" onclick="confirmLogout()" style="font-style: italic; color: black; margin-right: 20px;">Logout</a>
                            </div>
                        </div>
                    </c:if>

                    <!--chưa login-->
                    <c:if test="${sessionScope.loggedInUser == null}">
                        <div class="d-flex" style="gap: 25px;">
                            <div class="button-2">
                                <a href="login" style="text-decoration: none; color: #000;">Login</a>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </nav>
        <!-- jQuery -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
        <!--Bootstrap 5-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
        <!--lucide icon-->
        <script src="https://unpkg.com/lucide@latest"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
                                    lucide.createIcons();
        </script>
        <!--skill multi choice-->
        <script>
            $('#small-select2-options-multiple-field').select2({
                theme: "bootstrap-5",
                width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
                placeholder: $(this).data('placeholder'),
                closeOnSelect: true,
                selectionCssClass: 'select2--small',
                dropdownCssClass: 'select2--small'
            });
        </script>

        <script>
            function confirmLogout() {
                const swalWithBootstrapButtons = Swal.mixin({
                    customClass: {
                        confirmButton: "btn btn-secondary",
                        cancelButton: "btn btn-secondary"
                    },
                    buttonsStyling: true
                });
                swalWithBootstrapButtons.fire({
                    title: "Log Out",
                    text: "Are you sure you want to log out?",
                    showCancelButton: true,
                    confirmButtonText: "OK",
                    cancelButtonText: "Cancel",
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = "logout";
                    }
                });
            }
        </script>
    </body>
</html>
