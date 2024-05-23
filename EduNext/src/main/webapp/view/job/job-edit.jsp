<%-- 
    Document   : job-create
    Created on : Apr 7, 2024, 12:33:21 AM
    Author     : chun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Job</title>
        <link rel="icon" type="image/x-icon" href="Image/Logo/ims-logo.png">
        <!-- link to css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/job-edit.css">
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
                            <li class="breadcrumb-item"><a href="#">Job List</a></li>
                            <li class="breadcrumb-item active" aria-current="page">
                                <a  href="candidate-create.jsp">Edit Job</a>
                            </li>
                        </ol>
                    </nav>
                    <!-- content-card -->
                    <div class="card my-card">
                        <div class="content">
                            <!-- class="content-1" -->
                            <div class="content-1">

                            </div>
                            <!-- class="content-2" -->
                            <div class="card-body">
                                <form action="">
                                    <div class="part1 mt-3">
                                        <!-- row1 -->
                                        <div class="row mb-3">
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Job title <span style="color: red;">*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <!-- full name -->
                                                        <input type="text" class="form-control"
                                                               placeholder="Type a title..." required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-1"></div>
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Skills<span style="color: red;">*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <select class="form-select"
                                                                data-placeholder="Choose skills ..."
                                                                id="small-select2-options-multiple-field-skills" multiple>
                                                            <option>Java</option>
                                                            <option>JavaScript</option>
                                                            <option>Python</option>
                                                            <option>C++</option>
                                                            <option>HTML</option>
                                                            <option>${pageContext.request.contextPath}/CSS</option>
                                                            <option>PHP</option>
                                                            <option>Ruby</option>
                                                            <option>Swift</option>
                                                            <option>Kotlin</option>
                                                            <option>SQL</option>
                                                            <option>Go</option>
                                                            <option>C#</option>
                                                            <option>Perl</option>
                                                            <option>Objective-C</option>
                                                            <option>Assembly</option>
                                                            <option>TypeScript</option>
                                                            <option>Rust</option>
                                                            <option>Shell</option>
                                                            <option>Scala</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- row2 -->
                                        <div class="row mb-3">
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Start Date<span style="color: red;">*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <input type="date" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-1"></div>
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    End Date<span style="color: red;">*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <input type="date" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- row3 -->
                                        <div class="row mb-3">
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Salary Range
                                                </div>
                                                <div class="col-md-8 row">
                                                    <div class="col-md-2">
                                                        <div class="d-flex align-items-center" style="font-size: 14px; margin-top: 10px;">
                                                            <span>From</span>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-1">
                                                        <div class="d-flex align-items-center" style="font-size: 14px; margin-top: 10px;">
                                                            <span>to</span>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-5">
                                                        <div class="input-group" style="padding: 0px !important;">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-1"></div>
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Benefits<span style="color: red;">*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <select class="form-select"
                                                                data-placeholder="Choose benefits ..."
                                                                id="small-select2-options-multiple-field-benefits" multiple>
                                                            <option>Lunch</option>
                                                            <option>25 days off</option>
                                                            <option>Team building</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="part2 mt-3">
                                        <!-- row1 -->
                                        <div class="row mb-3">
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Working address
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <!-- CV -->
                                                        <input type="text" class="form-control" placeholder="Type an address...">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-1"></div>
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Level<span style="color: red;">*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <select class="form-select"
                                                                data-placeholder="Choose levels ..."
                                                                id="small-select2-options-multiple-field-levels" multiple>
                                                            <option>Fresher</option>
                                                            <option>Junior</option>
                                                            <option>Intern</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- row2 -->
                                        <div class="row mb-3">
                                            <div class="col-md-5 row">

                                            </div>
                                            <div class="col-md-1"></div>
                                            <div class="col-md-5 row">
                                                <div class="col-md-3 part-title">
                                                    Description
                                                </div>
                                                <div class="col-md-8">
<!--                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <select class="form-select">
                                                            <option selected disabled>Select a status</option>
                                                            <option value="1">Active</option>
                                                            <option value="2">Inactive</option>
                                                        </select>
                                                    </div>-->
                                                    <div class="form-floating" style="padding: 0px !important;">
                                                        <textarea style="height: 200px" class="form-control" placeholder="Type description" id="floatingTextarea"></textarea>
                                                        <label for="floatingTextarea">Comments</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- row3 -->
                                        <div class="row mb-3">
                                            <div class="col-md-5 row">
                                                
                                            </div>
                                            <div class="col-md-1"></div>
                                            <div class="col-md-5 row">
                                                
                                            </div>
                                        </div>
                                        <!-- row4 -->
                                        <div class="row mb-3">
                                            <div class="col-md-5 row">

                                            </div>
                                            <div class="col-md-1"></div>
                                            <div class="col-md-5 row">
                                                
                                            </div>
                                        </div>
                                    </div>
                                    <br><br>
                                    <div class="d-flex justify-content-center">
                                        <button class="button-2"
                                                style="background-color: #ABDF75; color: #fff;">Submit</button>
                                        <button class="button-2"
                                                style="background-color: #EFA9AE; color: #fff; margin-left: 3em;">Cancel</button>
                                    </div>
                                </form>
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
            $('#small-select2-options-multiple-field-skills').select2({
                theme: "bootstrap-5",
                width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
                placeholder: $(this).data('placeholder'),
                closeOnSelect: false,
                selectionCssClass: 'select2--small',
                dropdownCssClass: 'select2--small'
            });
            
            //benefits multi choice
            $('#small-select2-options-multiple-field-benefits').select2({
                theme: "bootstrap-5",
                width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
                placeholder: $(this).data('placeholder'),
                closeOnSelect: false,
                selectionCssClass: 'select2--small',
                dropdownCssClass: 'select2--small'
            });
            
            //levels multi choice
            $('#small-select2-options-multiple-field-levels').select2({
                theme: "bootstrap-5",
                width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
                placeholder: $(this).data('placeholder'),
                closeOnSelect: false,
                selectionCssClass: 'select2--small',
                dropdownCssClass: 'select2--small'
            });
        </script>
    </body>
</html>
