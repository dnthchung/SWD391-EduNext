<%-- Document : candidate-list Created on : Apr 6, 2024, 12:47:46 AM Author : chun --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Candidate List</title>
        <link rel="icon" type="image/x-icon" href="Image/Logo/ims-logo.png">
        <!-- link to css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/candidate-list.css">

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
                    <!-- content-card -->
                    <div class="card mt-3 my-card">
                        <div class="content">
                            <div>
                                <h4>Candidate list</h4>
                                <div>
                                    <div class="row mt-5">
                                        <form action="candidate-list" method="GET">
                                            <div class="col-md-3">
                                                <!-- Search Area -->
                                                <div class="input-group" style="padding: 0px !important;">
                                                    <input type="text" class="form-control" placeholder="search" name="search" value="${param.search}">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text">
                                                            <i data-lucide="search"></i> <!-- Icon kính lúp -->
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-10"></div>
                                            <div class="col-md-3 mt-3">
                                                <div class="input-group" style="padding: 0px !important;">
                                                    <select class="form-select" name="status">
                                                        <option value="" >Status</option>
                                                        <c:forEach var="ls" items="${listStatus}">
                                                            <option value="${ls.candidateStatusId}" ${param.status == ls.candidateStatusId ? 'selected' : ''}>${ls.statusName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-10"></div>
                                            <div class="col-md-3 mt-3">
                                                <div class="input-group" style="padding: 0px !important; ; ">
                                                    <button class="button-2"
                                                            style="background-color: #1E96FC; color: #fff">Search</button>
                                                </div>
                                            </div>
                                            <div class="col-md-3"></div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="container row">
                                <div class="col-md-10"></div>
                                <div class="col-md-2">
                                    <c:if test="${sessionScope.loggedInUser.userRoleId != 2}">
                                        <a href="candidate-create" style="text-decoration: none;" class="button3">
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
                                    </c:if>
                                </div>
                            </div>
                            <div class="card mt-3">                                
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table text-center table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col"> <strong>Name</strong> </th>
                                                    <th scope="col"> <strong>Email</strong> </th>
                                                    <th scope="col"> <strong>Phone No.</strong> </th>
                                                    <th scope="col"> <strong>Current Position</strong> </th>
                                                    <th scope="col"> <strong>Owner HR</strong> </th>
                                                    <th scope="col"> <strong>Status</strong> </th>
                                                    <th scope="col"> <strong>Action</strong> </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!--  table data rows  -->
                                                <c:choose>
                                                    <c:when test="${listCandidate.isEmpty()}">
                                                        <tr>
                                                            <td colspan="7" style="text-align: center"><h1>Not Found</h1></td>
                                                        </tr>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:forEach var="lc" items="${listCandidate}">
                                                            <tr>
                                                                <td>${lc.fullName}</td>
                                                                <td>${lc.email}</td>
                                                                <td>${lc.phoneNumber}</td>
                                                                <td>${lc.position.positionName}</td>
                                                                <td>${lc.recruiter.useName}</td>
                                                                <td>${lc.candidateStatus.statusName}</td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${sessionScope.loggedInUser.userRoleId == 2}">
                                                                            <a style="margin-right: 5px;text-decoration: none; color: black; "
                                                                               href="candidate-info?id=${lc.candidateId}" class="icon-button">
                                                                                <i data-lucide="eye"></i>
                                                                            </a>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <a style="margin-right: 5px;text-decoration: none; color: black; "
                                                                               href="candidate-info?id=${lc.candidateId}" class="icon-button">
                                                                                <i data-lucide="eye"></i>
                                                                            </a>
                                                                            <a style="margin-right: 5px;text-decoration: none; color: black;"
                                                                               href="candidate-edit?id=${lc.candidateId}" class="icon-button">
                                                                                <i data-lucide="file-pen-line"></i>
                                                                            </a>                     
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:otherwise>
                                                </c:choose>


                                            </tbody>
                                        </table>
                                        <c:if test="${!listCandidate.isEmpty()}">
                                            <div class="container row">
                                                <div class="col-md-10">
                                                    <!-- Your content -->
                                                </div>
                                                <div class="col-md-2">
                                                    <!-- Pagination -->
                                                    <ul id="pagination">
                                                        <li>
                                                            <span>
                                                                Page:
                                                            </span>
                                                            <span>
                                                                <span>${p}</span>
                                                                /
                                                                <span>${totalPage}</span>
                                                            </span>

                                                        </li>
                                                        <li><a href="${pageContext.request.contextPath}/candidate-list?search=${search}&status=${status}&p=${p-1}" ${p == 1 ? 'hidden' : ''}>&lt;</a></li>
                                                        <li><a href="${pageContext.request.contextPath}/candidate-list?search=${search}&status=${status}&p=${p+1}" ${p == totalPage ? 'hidden' : ''}>&gt;</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </c:if>
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
    </body>

</html>