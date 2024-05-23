<%-- 
    Document   : user-details
    Created on : Apr 7, 2024, 6:21:22 PM
    Author     : chun
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Details</title>
        <link rel="icon" type="image/x-icon" href="Image/Logo/ims-logo.png">
        <!-- link to css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/user-details.css">
    </head>
    <body>
        <!-- side bar -->
        <div class="d-flex flex-row">
            <%@include file="../../Component/side-bar.jsp" %>
            <!-- navigation bar + main content-->
            <div class="main-content">
                <!-- navigation bar -->
                <%@include file="../../Component/nav-bar.jsp" %>
                <!-- body -->
                <div class="container-fluid mt-3">
                    <nav aria-label="breadcrumb" style="margin-left: 1em">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="user-list">User List</a></li>
                            <li class="breadcrumb-item" aria-current="page">
                                <a >User Details</a>
                            </li>
                        </ol>
                    </nav>
                    <!-- content-card -->
                    <div class="card my-card">
                        <div class="content">
                            <!-- class="content-1" -->
                            <!-- class="content-2" -->
                            <div class="row mb-3">
                                <div class="col-md-6">
                                </div>
                                <c:if test="${user.userRoleId != 1}">
                                <div class="col-md-6">
                                    <!--manager-->
                                    <c:if test="${user.userStatusId == 2}">
                                        <button class="button33blue" style="margin-right: 10px; width: 180px !important" onclick="changeUserStatus(2)">
                                            <span class="button-text">Activate User</span>
                                            <span class="button-icon">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-check">
                                                <path d="M20 6 9 17l-5-5"/>
                                                </svg>
                                            </span>
                                        </button>
                                    </c:if>
                                    <c:if test="${user.userStatusId == 1}">
                                        <button class="button33orange" style="margin-right: 10px; width: 180px !important" onclick="changeUserStatus(1)">
                                            <span class="button-text">De-active User</span>
                                            <span class="button-icon">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-x">
                                                <path d="M18 6 6 18"/>
                                                <path d="m6 6 12 12"/>
                                                </svg>
                                            </span>
                                        </button>
                                    </c:if>
                                </div>                                 
                                </c:if>
                            </div>
                            <form action="">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <table class="table table-striped">
                                                <thead>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="part-title">Full Name</td>
                                                        <td>${user.fullName}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">D.O.B</td>
                                                        <td id="dob1">${user.dob}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Phone Number</td>
                                                        <td>${user.phoneNumber}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Role</td>
                                                        <c:forEach items="${userRole}" var="role">
                                                            <c:if test="${user.userRoleId == role.userRoleId}">
                                                                <td>${role.roleName}</td>
                                                            </c:if>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Status</td>
                                                        <c:forEach items="${userStatus}" var="status">
                                                            <c:if test="${user.userStatusId == status.userStatusId}">
                                                                <td>${status.statusName}</td>
                                                            </c:if>
                                                        </c:forEach>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col-md-1"></div>
                                        <div class="col-md-5">
                                            <table class="table table-striped">
                                                <thead>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="part-title">Email</td>
                                                        <td>
                                                            <p>${user.email}</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Address</td>
                                                        <td>
                                                            ${user.address}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Gender</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${user.gender == 1}">
                                                                    <p>Male</p>
                                                                </c:when>
                                                                <c:when test="${user.gender == 2}">
                                                                    <p>Female</p>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <p>Unknown</p>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>

                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Department</td>
                                                        <c:forEach items="${departmentList}" var="department">
                                                            <c:if test="${department.departmentId == user.departmentId}">
                                                                <td>
                                                                    <p>${department.departmentName}</p>
                                                                </td>
                                                            </c:if>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Note</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${not empty user.note}">
                                                                    ${user.note}
                                                                </c:when>
                                                                <c:otherwise>
                                                                    N/A
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="d-flex justify-content-center mt-5">
                                        <a type="button" href="user-edit?userId=${user.userId}&flag=1" class="button-2" style="background-color: #1e96fc; color: #fff; margin-right: 2em">Edit</a>
                                        <button type="button" id="cancelButton" class="button-2" style="background-color: #EFA9AE; color: #fff; margin-left: 3em;">Cancel</button>                                    </div>
                                </div>
                            </form>
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
        <script>
            //dob type solve
            var dobElement = document.getElementById("dob1");
            var dob = dobElement.innerText;

            var parts = dob.split('-');
            var year = parseInt(parts[0]);
            var month = parseInt(parts[1]);
            var day = parseInt(parts[2]);

            var formattedDOB = day.toString().padStart(2, '0') + '-' + month.toString().padStart(2, '0') + '-' + year;
            dobElement.innerText = formattedDOB;

            document.getElementById('cancelButton').addEventListener('click', function() {
                Swal.fire({
                    title: 'Are you sure?',
                    text: 'You will lose unsaved changes!',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = '${pageContext.request.contextPath}/user-list'; 
                    }
                });
            });
            
            function changeUserStatus(flag) {
                var userId = ${user.userId};
                
                Swal.fire({
                    title: flag === 1 ? 'De-activate User' : 'Activate User',
                    text: flag === 1 ? 'Do you want to de-activate this user?' : 'Do you want to activate this user?',
                    icon: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: flag === 1 ? 'Yes, de-activate it!' : 'Yes, activate it!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        var xhr = new XMLHttpRequest();
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState === XMLHttpRequest.DONE) {
                                if (xhr.status === 200) {
                                    var response = xhr.responseText;
                                    Swal.fire({
                                        title: 'Success!',
                                        text: flag === 1 ? 'User has been de-activated successfully' : 'User has been activated successfully',
                                        icon: 'success'
                                    }).then(() => {
                                        location.reload();
                                    });
                                } else {
                                    console.error('Request failed.');
                                }
                            }
                        };

                        xhr.open('GET', 'user-status-control?flag=' + flag + '&userId=' + userId, true);
                        xhr.send();
                    }
                });
            }
        </script>

    </body>
</html>
