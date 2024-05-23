<%-- 
    Document   : job-details
    Created on : Apr 7, 2024, 1:21:22 AM
    Author     : chun
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Job Details</title>
        <link rel="icon" type="image/x-icon" href="Image/Logo/ims-logo.png">
        <!-- link to css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/job-details.css">
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
                            <li class="breadcrumb-item"><a href="job-list">Job List</a></li>
                            <li class="breadcrumb-item active" aria-current="page">
                                <a href="#">Job Details</a>
                            </li>
                        </ol>
                    </nav>
                    <div class="d-flex justify-content-end" style="margin-right: 1em">
                        <p><i>Created on <strong>22/02/2022</strong> , last updated by <strong>chungdt1</strong> today</i></p>
                    </div>
                    <!-- content-card -->
                    <div class="card my-card">
                        <div class="content">
                            <!-- class="content-1" -->
                            <!-- class="content-2" -->
                            <form action="">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <table class="table table-striped">
                                                <thead>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="jd" items="${job}" varStatus="loop">
                                                        <c:if test="${loop.index == 0 || jd.jobTitle != job[loop.index - 1].jobTitle}">
                                                            <tr>
                                                                <td class="part-title">Job title</td>
                                                                <td>${jd.jobTitle}</td>  
                                                            </tr>
                                                            <tr>
                                                                <td class="part-title">Start Date</td>
                                                                <td>${jd.startDate}</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="part-title">Salary Range</td>
                                                                <td class="">
                                                                    <div class="d-flex justify-content-between">
                                                                        <p>From</p>
                                                                        <p>
                                                                            <strong>${jd.salaryFrom}</strong>
                                                                            <span> VND</span>
                                                                        </p>
                                                                        <p>To</p>
                                                                        <p>
                                                                            <strong>${jd.salaryTo}</strong>
                                                                            <span>VND</span>
                                                                        </p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="part-title">Working address</td>
                                                                <td>${jd.workAddress}</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="part-title">Status</td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${jd.status eq true}">
                                                                            Open
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            Closed
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
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
                                                        <td class="part-title">Skills</td>
                                                        <c:forEach var="jd" items="${job}">

                                                            <td>
                                                                <div class="d-flex flex-wrap-limit justify-content-between">
                                                                    <span class="badge text-bg-success">${jd.skill.skillName}</span>

                                                                </div>
                                                            </td>
                                                        </c:forEach>
                                                    </tr>


                                                    <tr>
                                                        <td class="part-title">End Date</td>
                                                        <c:set var="previousEndDate" value="" />
                                                        <c:forEach var="jd" items="${job}">
                                                            <c:if test="${jd.endDate != previousEndDate}">
                                                                <c:set var="previousEndDate" value="${jd.endDate}" />
                                                                <td>${jd.endDate}</td>
                                                            </c:if>
                                                        </c:forEach>
                                                    </tr>


                                                    <tr>
                                                        <td class="part-title">Benefits</td>
                                                        <c:forEach var="jd" items="${job}">
                                                            <td>
                                                                <div class="d-flex flex-wrap-limit justify-content-between">
                                                                    <span class="badge text-bg-success">${jd.benefit.benefitName}</span>

                                                                </div>
                                                            </td>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>

                                                        <td class="part-title">Level</td>
                                                        <c:forEach var="jd" items="${job}">
                                                            <td>
                                                                <div
                                                                    class="d-flex flex-wrap-limit justify-content-between">
                                                                    <span class="badge text-bg-success">${jd.level.levelName}</span>

                                                                </div>
                                                            </td>
                                                        </c:forEach>
                                                    </tr>

                                                    <tr>
                                                        <td class="part-title">Description</td>
                                                        <c:set var="previousDescription" value="" />
                                                        <c:forEach var="jd" items="${job}">
                                                            <c:if test="${jd.description ne previousDescription}">
                                                                <c:set var="previousDescription" value="${jd.description}" />
                                                                <td>
                                                                    <p>${jd.description}</p>
                                                                </td>
                                                            </c:if>
                                                        </c:forEach>
                                                    </tr>   
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="d-flex justify-content-center mt-5">
                                        <a type="button" href="job-edit.jsp" class="button-2" style="background-color: #1e96fc; color: #fff; margin-right: 2em">Edit</a>
                                        <button class="button-2" style="background-color: #EFA9AE; color: #fff">Cancel</button>
                                    </div>
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
    </body>
</html>
