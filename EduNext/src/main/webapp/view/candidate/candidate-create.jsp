<%-- 
    Document   : candidate-create
    Created on : Apr 6, 2024, 4:46:35 PM
    Author     : chun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Candidate Create</title>
        <link rel="icon" type="image/x-icon" href="Image/Logo/ims-logo.png">
        <!-- link to css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/candidate-create.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
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
                            <li class="breadcrumb-item active" aria-current="page">
                                <a>Create Candidate</a>
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
                            <div class="card-body">
                                <form action="candidate-create" method="post" enctype="multipart/form-data"> 
                                    <h5 style="font-weight: bold;">I. Personal information</h5>
                                    <div class="part1 mt-3">
                                        <!-- row1 -->
                                        <div class="row mb-3">
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Full name <span style="color: red;">* </span>                                                  
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <!-- full name -->
                                                        <input type="text" class="form-control"
                                                               placeholder="type a name"  name="fullName" required="" onkeyup="checkAll()" id="fullName" data-bs-toggle="tooltip" data-bs-title="Default tooltip">
                                                    </div>
                                                    <small id="fullNameAlert" style="color: #FF6B6B">Fill full name</small>
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
                                                               placeholder="type an email..."  name="email" required="" id="email" onkeyup="checkAll()">
                                                    </div>
                                                    <small id="emailAlert" style="color: #FF6B6B">Fill an email. Ex:vietanhdeptrai@example.com</small>
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
                                                        <input type="date" class="form-control" name="dob" required="" id="dob" onchange="checkAll()">
                                                    </div>
                                                    <small id="dobAlert" style="color: #FF6B6B">Fill date of birth</small>
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
                                                               placeholder="type an address..."  name="address" required="" id="address" onkeyup="checkAll()">
                                                    </div>
                                                    <small id="addressAlert" style="color: #FF6B6B">Fill address</small>
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
                                                               placeholder="type a number..." name="phone" required="" id="phone" onkeyup="checkAll()">
                                                    </div>
                                                    <small id="phoneAlert" style="color: #FF6B6B">Fill phone number</small>
                                                </div>
                                            </div>
                                            <div class="col-md-1"></div>
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Gender <span style="color: red;">*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <select class="form-select"  name="gender" required="" id="gender" onchange="checkAll()">
                                                            <option value="" selected disabled>Female, Male or Other</option>
                                                            <option value="0">Female</option>
                                                            <option value="1">Male</option>
                                                            <option value="2">Other</option>
                                                        </select>
                                                    </div>
                                                    <small id="genderAlert" style="color: #FF6B6B">Choose gender</small>
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
                                                        <input type="file" class="form-control" accept=".pdf,.docx" name="file" id="file" onchange="checkAll()">
                                                    </div>
                                                    <small id="fileAlert" style="color: #FF6B6B">add candidate'cv</small>
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
                                                        <input type="text" class="form-control" placeholder="N/A" name="note">
                                                    </div>
                                                    <small id="noteAlert">You can skip this field</small>
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
                                                        <select class="form-select" name="position" required="" id="position" onchange="checkAll()">
                                                            <option value="" selected="" disabled="">Position</option>
                                                            <c:forEach var="p" items="${position}">
                                                                <option value="${p.positionId}">${p.positionName}</option>
                                                            </c:forEach>                                                           
                                                        </select>
                                                    </div>
                                                    <small id="positionAlert" style="color: #FF6B6B">choose position</small>
                                                </div>
                                            </div>
                                            <div class="col-md-1"></div>
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Status <span style="color: red;">*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <select class="form-select" name="status" required="" id="status" onchange="checkAll()">
                                                            <option value="" selected="" disabled="">Status</option>
                                                            <c:forEach var="s" items="${status}">                                                           
                                                                <option value="${s.candidateStatusId}">${s.statusName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <small id="statusAlert" style="color: #FF6B6B">Choose status</small>
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
                                                                id="skill" multiple  name="skill" required="" onchange="checkAll()">
                                                            <c:forEach var="sk" items="${skill}">                                                         
                                                                <option value="${sk.skillId}">${sk.skillName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <small id="skillAlert" style="color: #FF6B6B">Choose skill</small>
                                                </div>
                                            </div>
                                            <div class="col-md-1"></div>
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Year of Experience
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <input type="number" min="1" class="form-control"
                                                               placeholder="type a number..." name="yoe" required="" onchange="checkAll()" id="yoe">
                                                    </div>
                                                    <small id="yoeAlert" style="color: #FF6B6B">Fill yoe</small>
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
                                                        <select class="form-select"  name="recruiter" required="" id="recruiter" onchange="checkAll()">
                                                            <option value="" selected="" disabled="">Recruiter</option>
                                                            <c:forEach var="r" items="${recruiter}">                                                         
                                                                <option value="${r.userId}">${r.useName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <small id="recruiterAlert" style="color: #FF6B6B">Choose recruiter</small>
                                                </div>
                                                <div id="selectedSkills" style="color: #FF6B6B"></div>
                                            </div>
                                            <div class="col-md-1"></div>
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Highest level<span style="color: red;">*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <select class="form-select" name="level" required="" id="level" onchange="checkAll()">
                                                            <option value="" selected="" disabled="">Highest Level</option>
                                                            <c:forEach var="l" items="${level}">                                                         
                                                                <option value="${l.levelId}">${l.levelName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <small id="levelAlert" style="color: #FF6B6B">Choose level</small>
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
                                        <button class="button-2" type="submit" id="submitBtn" disabled=""
                                                style="background-color: #9b9b9b; color: #fff;">Submit</button>
                                        <button class="button-2" type="reset"
                                                style="background-color: #EFA9AE; color: #fff; margin-left: 3em;">Reset</button>
                                    </div>
                                </form>
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
            const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
            const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
        </script>
        <script>
            //skill multi choice
            $('#skill').select2({
                theme: "bootstrap-5",
                width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
                placeholder: $(this).data('placeholder'),
                closeOnSelect: false,
                selectionCssClass: 'select2--small',
                dropdownCssClass: 'select2--small'
            });
        </script>
        <script>
            var today = new Date().toISOString().split('T')[0];
            document.getElementById("dob").setAttribute("max", today);
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Lúc này, trang web đã được tải, chúng ta có thể thực hiện các thao tác DOM
                document.getElementById("submitBtn").disabled = true;
            });
        </script>

        <script>
            var mess = '${sessionScope.mess}';
        </script>
        <script src="${pageContext.request.contextPath}/JS/toast.js"></script>
        <script src="${pageContext.request.contextPath}/JS/Candidate/check-validate.js"></script>
    </body>
</html>
