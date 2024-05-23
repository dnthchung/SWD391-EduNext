<%-- 
    Document   : interview-details
    Created on : Apr 7, 2024, 1:21:22 AM
    Author     : chun
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interview Details</title>
        <link rel="icon" type="image/x-icon" href="Image/Logo/ims-logo.png">
        <!-- link to css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/interview-details.css">
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
                            <li class="breadcrumb-item">
                                <a href="#">Interview Schedule List</a>
                            </li>
                            <li class="breadcrumb-item active" aria-current="page">
                                <!--for interview-->
                                <a href="#">Interview Schedule Details</a>
                                <!--for manager-->
                                <a href="#">New Interview Schedule</a>
                            </li>
                        </ol>
                    </nav>
                    <div class="d-flex">
                        <div class="me-auto p-2 "></div>
                        <div class="p-2"> 
                            <button class="button33">
                                <span class="button-text">Send Remember</span>
                                <span class="button-icon">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-send">
                                    <path d="m22 2-7 20-4-9-9-4Z"/>
                                    <path d="M22 2 11 13"/>
                                    </svg>
                                </span>
                            </button>
                        </div>
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
                                                    <tr>
                                                        <td class="part-title">Schedule title</td>
                                                        <td>Interview Junior Business Analyst</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Candidate Name</td>
                                                        <td>Le Viet Anh</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Schedule Time</td>
                                                        <td class="">
                                                            <p style="margin-bottom: 3px">22/05/2022</p>
                                                            From
                                                            <strong>09:00 am</strong>
                                                            To
                                                            <strong>10:30 am</strong>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Note</td>
                                                        <td>N/A</td>
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
                                                        <td class="part-title">Job</td>
                                                        <td>Senior BA</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Interviewer</td>
                                                        <td class="interviewer-names">
                                                            <div class="row row-cols-4">
                                                                <div class="col">
                                                                    <p><i>ChungDT1</i></p>
                                                                </div>
                                                                <div class="col">
                                                                    <p><i>ChungDT1</i></p>
                                                                </div>
                                                                <div class="col">
                                                                    <p><i>ChungDT1</i></p>
                                                                </div>
                                                                <div class="col">
                                                                    <p><i>ChungDT1</i></p>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Location</td>
                                                        <td>Online</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Meeting ID</td>
                                                        <td>
                                                            <a href="https://meet.google.com/">https://meet.google.com/</a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Recruiter Owner</td>
                                                        <td>AnhLV2</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Result</td>
                                                        <td>
                                                            <p>N/A</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="part-title">Status</td>
                                                        <td>
                                                            <p>Open</p>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="d-flex justify-content-center mt-5">
                                        <!--interview button-->
                                        <button class="button-2" style="background-color: #ABDF75; color: #fff; margin-right: 2em">Submit Result</button>
                                        <!--manager button-->
                                        <a type="button" href="interview-edit.jsp" class="button-2" style="background-color: #1e96fc; color: #fff; margin-right: 2em">Edit</a>

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
