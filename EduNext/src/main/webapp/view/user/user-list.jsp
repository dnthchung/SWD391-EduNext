<%-- 
    Document : user-list 
    Created on : Apr 6, 2024, 4:09:46 PM 
    Author : chun 
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
        <link rel="icon" type="image/x-icon" href="Image/Logo/ims-logo.png">
        <!-- link to css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/user-list.css">
        <link rel="stylesheet" type="text/css"
              href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
    </head>
    <body>
        <!-- side bar -->
        <div class="d-flex flex-row">
            <%@include file="../../Component/side-bar.jsp" %>
            <!-- navigation bar + main content-->
            <div class="main-content">
                <!-- navigation bar -->
                <%@include file = "../../Component/nav-bar.jsp" %>
                <!-- body -->
                <div class="container-fluid mt-3">
                    <!-- content-card -->
                    <div class="card mt-3 my-card">
                        <div class="content">
                            <div>
                                <h4>User List</h4>
                                <div>
                                    <div class="row mt-5">
                                        <form id="user-search" action="user-search" method="post">
                                            <div class="col-md-8">
                                                <div class="col-md-3">
                                                    <!-- Search Area -->
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <input value ="${txtname}" id="txtname" name="txtname" type="text" class="form-control" placeholder="Search" aria-label="Username" aria-describedby="basic-addon1">
                                                        <span class="input-group-text" id="basic-addon1">
                                                            <i data-lucide="search"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="col-md-10"></div>
                                                <div class="col-md-3 mt-3">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <select id="roleSelected" class="form-select" name="roleSelected">
                                                            <option selected value="all">Role</option>
                                                            <c:forEach items="${userRole}" var="role" >
                                                                <option value="${role.userRoleId}">${role.roleName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-10"></div>
                                                <div class="col-md-3 mt-3">
                                                    <div class="input-group" style="padding: 0px !important; ; ">
                                                        <button class="button-2" style="background-color: #1E96FC; color: #fff">Search</button>
                                                    </div>
                                                </div>
                                                <div class="col-md-3"></div>
                                            </div>
                                        </form>
                                        <div class="col-md-1"></div>
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-6"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="d-flex">
                                <div class="me-auto p-2 "></div>
                                <div class="p-2 mt-3">
                                    <a href="user-create?flag=1" style="text-decoration: none;" class="button3">
                                        <span class="button-text">Add New</span>
                                        <span class="button-icon"><svg xmlns="http://www.w3.org/2000/svg" width="24"
                                                                       viewBox="0 0 24 24" stroke-width="2" stroke-linejoin="round"
                                                                       stroke-linecap="round" stroke="currentColor" height="24" fill="none"
                                                                       class="svg">
                                            <line y2="19" y1="5" x2="12" x1="12"></line>
                                            <line y2="12" y1="12" x2="19" x1="5"></line>
                                            </svg>
                                        </span>
                                    </a>
                                </div>
                            </div>
                            <c:if test="${errorMessage != null}">
                                <div class="d-flex justify-content-center">
                                    <strong style="color: red">
                                        ${errorMessage}
                                    </strong>
                                </div>
                            </c:if>
                            <div class="card mt-3">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table text-center table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col"> <strong>Usename</strong> </th>
                                                    <th scope="col"> <strong>Email</strong> </th>
                                                    <th scope="col"> <strong>Phone No.</strong> </th>
                                                    <th scope="col"> <strong>Role</strong> </th>
                                                    <th scope="col"> <strong>Status</strong> </th>
                                                    <th scope="col"> <strong>Action</strong> </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${listU}" var="user">
                                                    <tr>
                                                        <td>${user.useName}</td>
                                                        <td>${user.email}</td>
                                                        <td>${user.phoneNumber}</td>
                                                        <c:forEach items="${userRole}" var="role">
                                                            <c:if test="${user.userRoleId == role.userRoleId}">
                                                                <td>${role.roleName}</td>
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:forEach items="${userStatus}" var="status">
                                                            <c:if test="${user.userStatusId == status.userStatusId}">
                                                                <td>${status.statusName}</td>
                                                            </c:if>
                                                        </c:forEach>
                                                        <td>
                                                            <a style="margin-right: 5px;text-decoration: none; color: black; "
                                                               href="user-details?userId=${user.userId}" class="icon-button">
                                                                <i data-lucide="eye"></i>
                                                            </a>
                                                            <a style="margin-right: 5px;text-decoration: none; color: black;"
                                                               href="user-edit?userId=${user.userId}&flag=1" class="icon-button">
                                                                <i data-lucide="file-pen-line"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="container-fluid row">
                                            <div class="col-md-10">
                                                <!-- Your content -->
                                            </div>
                                            <div class="col-md-2">
                                                <!-- Pagination -->
                                                <ul id="pagination">
                                                    <li>
                                                        <span>page ${page}</span> / <span>${requestScope.num}</span>
                                                    </li>
                                                    <c:choose>
                                                        <c:when test="${page == 1}">
                                                            <li class="disabled">
                                                                <span>&lt;</span>
                                                            </li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li><a href="user-list?page=${page - 1}">&lt;</a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${page == requestScope.num}">
                                                            <li class="disabled">
                                                                <span>&gt;</span>
                                                            </li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li>
                                                                <a href="user-list?page=${page + 1}">&gt;</a>
                                                            </li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

        <!--lucide-->
        <script src="https://unpkg.com/lucide@latest"></script>
        <script>
            //icon lucide
            lucide.createIcons();
        </script>
        <script>
            //skill multi choice
            $('#small-select2-options-multiple-field').select2({
                theme: "bootstrap-5",
                width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
                placeholder: $(this).data('placeholder'),
                closeOnSelect: true,
                selectionCssClass: 'select2--small',
                dropdownCssClass: 'select2--small'
            });
        </script>
        <!--sweet alert2-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
            document.querySelector('#user-search').addEventListener('submit', function (e) {
                e.preventDefault();
        
                // Get the values of the inputs
                var txtname = document.getElementById('txtname').value;
                var userRoleId = document.getElementById('roleSelected').value;
        
                // Check if both inputs are empty
                if (!txtname && userRoleId === 'all') {
                    // If both inputs are empty, show an error message
                    Swal.fire({
                        title: 'Error!',
                        text: 'Please enter a name or select a role to search.',
                        icon: 'error'
                    });
                } else {
                    // If at least one input is selected, submit the form
                    document.getElementById("user-search").submit();
                }
            });
        </script>
    </body>
</html>