<%-- 
    Document   : candidate-edit-info
    Created on : Apr 6, 2024, 10:26:40 PM
    Author     : chun
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Candidate Info</title>
        <link rel="icon" type="image/x-icon" href="Image/Logo/ims-logo.png">
        <!-- link to css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/candidate-edit-info.css">

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
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb" style="margin-left: 1em">
                            <li class="breadcrumb-item"><a href="candidate-list">Candidate List</a></li>
                            <li class="breadcrumb-item" aria-current="page">
                                <a>Edit Candidate Information</a>
                            </li>
                        </ol>
                    </nav>
                    <!-- content-card -->
                    <div class="card mt-3 my-card">
                        <div class="content">
                            <!-- class="content-1" -->
                            <div class="content-1">
                            </div>
                            <!-- class="content-2" -->
                            <div class="card mt-3">
                                <div class="card-body">
                                    <form action="candidate-edit" method="POST" enctype="multipart/form-data">
                                        <input type="hidden" name="candidateId" value="${c.candidateId}">
                                        <h5 style="font-weight: bold;">I. Personal information</h5>
                                        <div class="part1 mt-3">
                                            <!-- row1 -->
                                            <div class="row mb-3">
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        Full name <span style="color: red;">*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <!-- full name -->
                                                            <input type="text" class="form-control"
                                                                   placeholder="type a name" required value="${c.fullName}" name="fullName" onkeyup="checkAll()" id="fullName">
                                                        </div>
                                                        <small id="fullNameAlert" style="color: green">Fill full name</small>
                                                    </div>
                                                </div>
                                                <div class="col-md-1"></div>
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        Email <span style="color: red;">*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <!-- gmail -->
                                                            <input type="text" class="form-control"
                                                                   placeholder="type an email..." required name="email" value="${c.email}" id="email" onkeyup="checkAll()">
                                                        </div>
                                                        <small id="emailAlert" style="color: green">Fill an email. Ex:vietanhdeptrai@example.com</small>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- row2 -->
                                            <div class="row mb-3">
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        D.O.B
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <input type="date" class="form-control" value="${c.dob}" name="dob" required="" id="dob" onchange="checkAll()">
                                                        </div>
                                                        <small id="dobAlert" style="color: green">Fill date of birth before current day</small>
                                                    </div>
                                                </div>
                                                <div class="col-md-1"></div>
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        Address
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <input type="text" class="form-control"
                                                                   placeholder="type an address..." required="" name="address" value="${c.address}" id="address" onkeyup="checkAll()">
                                                        </div>
                                                        <small id="addressAlert" style="color: green">Fill address</small>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- row3 -->
                                            <div class="row mb-3">
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        Phone number
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <input type="text" class="form-control"
                                                                   placeholder="type a number..." required="" value="${c.phoneNumber}" name="phone" id="phone" onkeyup="checkAll()">
                                                        </div>
                                                        <small id="phoneAlert" style="color: green">Fill phone number</small>
                                                    </div>
                                                </div>
                                                <div class="col-md-1"></div>
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        Gender <span style="color: red;">*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">

                                                            <select class="form-select" required="" name="gender">
                                                                <option value="0" ${c.gender == 0 ? 'selected' : ''}>Female</option>
                                                                <option value="1" ${c.gender == 1 ? 'selected' : ''}>Male</option>
                                                                <option value="2" ${c.gender == 2 ? 'selected' : ''}>Other</option>
                                                            </select>
                                                        </div>
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <hr>
                                        <h5 style="font-weight: bold;">II. Professional information</h5>
                                        <div class="part2 mt-3">
                                            <!-- row1 -->
                                            <div class="row mb-3">
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        CV attachment
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <!-- CV -->
                                                            <input type="hidden" value="${c.cvAttachment}" name="oldFile">
                                                            <input type="file" class="form-control" name="file" id="fileInput" accept=".pdf,.docx">
                                                            <button type="button" onclick="clearFile()" class="btn btn-outline-danger">Clear File</button>
                                                        </div>
                                                        
                                                        <p>Current CV: <a href="${c.cvAttachment}" target="_blank">${c.fullName}.pdf</a></p>
                                                    </div>
                                                </div>
                                                <div class="col-md-1"></div>
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        Note
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <!-- note -->
                                                            <input type="text" class="form-control" placeholder="N/A" value="${c.note}" name="note">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- row2 -->
                                            <div class="row mb-3">
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        Position <span style="color: red;">*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <select class="form-select" name="position" required="">
                                                                <c:forEach var="p" items="${listPosition}">
                                                                    <option value="${p.positionId}" ${c.position.positionId == p.positionId ? 'selected' : ''}>${p.positionName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-1"></div>
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        Status <span style="color: red;">*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <select class="form-select" name="status" required="">
                                                                <c:forEach var="s" items="${status}">
                                                                    <option value="${s.candidateStatusId}" ${s.candidateStatusId == c.candidateStatus.candidateStatusId ? 'selected' : ''}>${s.statusName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- row3 -->
                                            <div class="row mb-3">
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        Skills <span style="color: red;">*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <select class="form-select"
                                                                    data-placeholder="Choose skills ..."
                                                                    id="skill" multiple name="skill" required="" onchange="checkAll()">
                                                                <c:forEach var="l" items="${listSkill}">
                                                                    <c:set var="check" value="false"></c:set>
                                                                    <c:forEach var="skillSelected" items="${c.skill}">
                                                                        <c:if test="${l.skillId == skillSelected.skillId}">
                                                                            <c:set var="check" value="true"></c:set>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                    <option value="${l.skillId}" ${check == true ? 'selected' : ''}>${l.skillName}</option>
                                                                </c:forEach>
                                                            </select>
                                                            
                                                        </div>
                                                        <small id="skillAlert" style="color: green">Choose skill</small>
                                                    </div>
                                                    <!--<div id="selectedSkills"></div>-->
                                                </div>
                                                <div class="col-md-1"></div>
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        Year of Experience
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <input type="number" min="1" class="form-control"
                                                                   placeholder="type a number..." name="yoe" value="${c.yearOfExperience}" required="" onchange="checkAll()" id="yoe">
                                                        </div>
                                                        <small id="yoeAlert" style="color: green">Fill yoe</small>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- row4 -->
                                            <div class="row mb-3">
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        Recruiter <span style="color: red;">*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <select class="form-select" name="recruiter" required="">
                                                                <c:forEach var="r" items="${listRecruiter}">
                                                                    <option value="${r.userId}" ${r.userId == c.recruiter.userId ? 'selected' : ''}>${r.useName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div id="selectedSkills"></div>
                                                </div>
                                                <div class="col-md-1"></div>
                                                <div class="col-md-5 row">
                                                    <div class="col-md-3 part-title">
                                                        Highest level<span style="color: red;">*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <select class="form-select" name="level" required="">
                                                                <c:forEach var="level" items="${listLevel}">
                                                                    <option value="${level.levelId}" ${level.levelId == c.highestLevel.levelId ? 'selected' : ''}>${level.levelName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row mt-2">
                                                <div class="col-md-2"></div>
                                                <div class="col-md-2">
                                                    <button
                                                        style="border: none; background-color: #ffffff; text-decoration: underline;">
                                                        Assign me
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <br><br>
                                        <div class="d-flex justify-content-center">
                                            <button class="button-2" type="submit"
                                                    style="background-color: green; color: #fff;" id="submitBtn">Submit</button>
                                            <button class="button-2" type="reset"
                                                    style="background-color: #EFA9AE; color: #fff; margin-left: 3em;">Cancel</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--            <button type="button" class="btn btn-primary" id="liveToastBtn">Show live toast</button>-->            
            <!--            <div class="toast-container position-fixed bottom-0 end-0 p-3">
                            <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                                <div class="toast-header">
                                    <img src="..." class="rounded me-2" alt="...">
                                    <strong class="me-auto">ISM Notification</strong>
                                    <small>Now</small>
                                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                                </div>
                                <div class="toast-body">
            
                                </div>
                            </div>
                        </div>-->
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
            $('#skill').select2({
                theme: "bootstrap-5",
                width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
                placeholder: $(this).data('placeholder'),
                closeOnSelect: true,
                selectionCssClass: 'select2--small',
                dropdownCssClass: 'select2--small'
            });
        </script>
        <script>
            function clearFile() {
                var fileInput = document.getElementById('fileInput');
                fileInput.value = ''; // Xóa giá trị của thẻ input
            }
        </script>
        <script>
            var mess = '${sessionScope.mess}';
        </script>
        <script src="${pageContext.request.contextPath}/JS/toast.js"></script>
        <script src="${pageContext.request.contextPath}/JS/Candidate/check-validate-edit.js"></script>
    </body>
</html>
