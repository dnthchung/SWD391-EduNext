<%-- 
    Document   : candidate-information
    Created on : Apr 6, 2024, 5:50:52 PM
    Author     : chun
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Candidate Information</title>
        <link rel="icon" type="image/x-icon" href="Image/Logo/ims-logo.png">
        <!-- link to css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/candidate-information.css">
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
                            <li class="breadcrumb-item"><a href="candidate-list">Candidate List</a></li>
                            <li class="breadcrumb-item" aria-current="page">
                                <a>Candidate Information</a>
                            </li>
                        </ol>
                    </nav>
                    <!-- content-card -->
                    <div class="card mt-3 my-card">
                        <div class="content">
                            <!-- class="content-1" -->
                            <div class="row">
                                <div class="col-md-8"></div>
                                <div class="col-md-3">
                                    <c:if test="${sessionScope.loggedInUser.userRoleId != 2 && c.candidateStatus.candidateStatusId != 2}">
                                        <a class="button31 link-offset-2 link-underline link-underline-opacity-0" role="button" href="candidate-update?id=${c.candidateId}">
                                            <span class="button-text">Ban</span>
                                            <span class="button-icon">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                                                     stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                                                     class="lucide lucide-ban">
                                                <circle cx="12" cy="12" r="10" />
                                                <path d="m4.9 4.9 14.2 14.2" />
                                                </svg>
                                                <line y2="19" y1="5" x2="12" x1="12"></line>
                                                <line y2="12" y1="12" x2="19" x1="5"></line>
                                                </svg>
                                            </span>
                                        </a>  
                                    </c:if>
                                </div>
                                <div class="col-md-1"></div>
                            </div>
                            <!-- class="content-2" -->

                            <div class="card-body">
                                <!-- =========I. Personal information ========================================================== -->
                                <h5 style="font-weight: bold;" class="mb-3">I. Personal information</h5>
                                <div class="row">
                                    <div class="col-md-3">
                                        <table class="table table-striped">
                                            <thead>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td class="part-title">Full name</td>
                                                    <td>${c.fullName}</td>

                                                </tr>
                                                <tr>
                                                    <td class="part-title">D.O.B</td>
                                                    <td>${c.dob}</td>
                                                </tr>
                                                <tr>
                                                    <td class="part-title">Phone number</td>
                                                    <td>${c.phoneNumber}</td>

                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-md-3"></div>
                                    <div class="col-md-4">
                                        <table class="table table-striped">
                                            <thead>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td class="part-title">Email</td>
                                                    <td>${c.email}</td>
                                                </tr>
                                                <tr>
                                                    <td class="part-title">Address</td>
                                                    <td>${c.address}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="part-title">Gender</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${c.gender == 1}">
                                                                Male
                                                            </c:when>
                                                            <c:when test="2">
                                                                Female
                                                            </c:when>
                                                            <c:otherwise>
                                                                Other
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!-- =========II. Professional information ========================================================== -->
                                <h5 style="font-weight: bold;" class="mb-3 mt-3">II. Professional information
                                </h5>
                                <div class="part2 mt-3">
                                    <!-- row1 -->
                                    <div class="row mb-3">
                                        <div class="col-md-5">
                                            <!-- <table class="table table-borderless"> -->
                                            <table class="table table-striped">
                                                <thead>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="part-title">CV attachment</td>
                                                        <td><a href="${c.cvAttachment}" target="_blank">${c.fullName}.pdf</a></td>

                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Position</td>
                                                        <td>${c.position.positionName}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Skills</td>
                                                        <td>
                                                            <div
                                                                class="d-flex flex-wrap-limit justify-content-evenly">
                                                                <c:forEach var="sk" items="${c.skill}">                                                                   
                                                                    <span class="badge text-bg-success">${sk.skillName}</span>
                                                                </c:forEach>

                                                            </div>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Recruiter</td>
                                                        <td>
                                                            <p>${c.recruiter.fullName}<span> (${c.recruiter.useName})</span></p>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col-md-1"></div>
                                        <div class="col-md-4">
                                            <table class="table table-striped">
                                                <thead>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="part-title">Status</td>
                                                        <td>${c.candidateStatus.statusName}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Year of Experience</td>
                                                        <td>${c.yearOfExperience}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Highest level</td>
                                                        <td>${c.highestLevel.levelName}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Note</td>
                                                        <td>${c.note}</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center">
                                    <c:if test="${sessionScope.loggedInUser.userRoleId != 2}">
                                        <a class="button-2" style="background-color: #1e96fc; color: #fff; margin-right: 2em" href="candidate-edit?id=${c.candidateId}">Edit</a>
                                        <!--                                    <button class="button-2" style="background-color: #EFA9AE; color: #fff">Cancel</button>-->
                                    </c:if>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../notification/notification.jsp" %>
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
            var mess = '${sessionScope.mess}';
        </script>
        <script src="${pageContext.request.contextPath}/JS/toast.js"></script>
    </body>
</html>
