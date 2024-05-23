<%-- 
    Document : offer-list 
    Created on : Apr 6, 2024, 4:09:46 PM 
    Author : chun 
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Offer List</title>
        <link rel="icon" type="image/x-icon" href="Image/Logo/ims-logo.png">
        <!-- link to css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/offer-list.css">
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
                                <h4>Offer list</h4>
                                <div>
                                    <div class="row mt-5">
                                        <form action="offer-list" method="POST">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <!-- Search Area -->
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <input type="text" class="form-control" value="${requestScope.searchedValue}" name="searchValue" placeholder="Search">
                                                        <div class="input-group-append">
                                                            <span class="input-group-text">
                                                                <i data-lucide="search"></i>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <select class="form-select" name="dept">
                                                            <option <c:if test="${deptSelected.isEmpty() || deptSelected == ''}">selected
                                                                                                           </c:if>>Department</option>
                                                            <c:forEach var="dept" items="${requestScope.departments}">
                                                                <option value="${dept.departmentId}" 
                                                                        <c:if test="${deptSelected == dept.departmentId && !deptSelected.isEmpty()}">selected
                                                                        </c:if>>${dept.departmentName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="input-group" style="padding: 0px !important;">
                                                        <select class="form-select" name="status">
                                                            <option <c:if test="${statusSelected.isEmpty() || statusSelected == ''}">selected
                                                                                                             </c:if>>Status</option>
                                                            <c:forEach var="offerStatus" items="${requestScope.offerStatuses}">
                                                                <option value="${offerStatus.offerStatusId}" 
                                                                        <c:if test="${statusSelected == offerStatus.offerStatusId && !statusSelected.isEmpty()}">selected
                                                                        </c:if>
                                                                        >${offerStatus.statusName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="input-group" style="padding: 0px !important; ; ">
                                                        <button type="submit" class="button-2 btn btn-secondary" style="max-height: 38px">Search</button>
                                                    </div>
                                                </div>
                                            </div>
                                            <c:if test="${requestScope.isEmptySearch == true}">
                                                <div class="row">
                                                    <span>No item matches with your search data.</span>
                                                </div>
                                            </c:if>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="d-flex">
                                <div class="me-auto p-2 "></div>
                                <div class="p-2 mt-3">
                                    <a href="create-offer" style="text-decoration: none;" type="button" class="button3">
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
                                <div class="p-2 mt-3"> 
                                    <button class="button31">
                                        <span class="button-text">Export</span>
                                        <span class="button-icon">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-arrow-up-to-line">
                                            <path d="M5 3h14"/>
                                            <path d="m18 13-6-6-6 6"/>
                                            <path d="M12 7v14"/>
                                            </svg>

                                        </span>
                                    </button>
                                </div>
                            </div>

                            <div class="card mt-3">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table text-center table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col"> <strong>Candidate Name</strong> </th>
                                                    <th scope="col"> <strong>Email</strong> </th>
                                                    <th scope="col"> <strong>Approver</strong> </th>
                                                    <th scope="col"> <strong>Department</strong> </th>
                                                    <th scope="col"> <strong>Notes</strong> </th>
                                                    <th scope="col"> <strong>Status</strong> </th>
                                                    <th scope="col"> <strong>Action</strong> </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!--  table data rows  -->
                                                <c:forEach var="offer" items="${requestScope.offers}">
                                                    <tr>
                                                        <td>${offer.candidateName}</td>
                                                        <td>${offer.email}</td>
                                                        <td>${offer.approverName}</td>
                                                        <td>${offer.departmentName}</td>
                                                        <c:if test="${offer.note.isEmpty()}">
                                                            <td>N/A</td>
                                                        </c:if>
                                                        <c:if test="${!offer.note.isEmpty()}">
                                                            <td>${offer.note}</td>
                                                        </c:if>
                                                        <td>${offer.statusName}</td>
                                                        <td>
                                                            <a style="margin-right: 5px;text-decoration: none; color: black; "
                                                               href="offer-details?offerId=${offer.offerId}" class="icon-button">
                                                                <i data-lucide="eye"></i>
                                                            </a>
                                                            <c:if test="${sessionScope.loggedInUser != null && offer.statusName == 'Waiting for Approval'}">
                                                                <a style="margin-right: 5px;text-decoration: none; color: black;"
                                                                   href="edit-offer?offerId=${offer.offerId}" class="icon-button">
                                                                    <i data-lucide="file-pen-line"></i>
                                                                </a>
                                                            </c:if>

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
                                                <form action="offer-list" method="POST">
                                                    <input type="hidden" value="${requestScope.searchedValue}" name="searchValue">
                                                    <input type="hidden" value="${requestScope.deptSelected}" name="dept">
                                                    <input type="hidden" value="${requestScope.statusSelected}" name="status">
                                                    <input type="hidden" value="${requestScope.currentPage}" name="currentPage">
                                                    <!-- Pagination -->
                                                    <ul id="pagination">
                                                        <li>
                                                            <span>
                                                                <span>Page ${requestScope.currentPage}</span> / <span>${requestScope.totalPage}</span>
                                                            </span>
                                                        </li>
                                                        <li><button <c:if test="${requestScope.currentPage == 1}">disabled=""</c:if> style="border: 0px" type="submit" value="prev" name="btnPage" />&lt;</button></li>
                                                        <li><button <c:if test="${requestScope.totalPage == requestScope.currentPage}">disabled=""</c:if> style="border: 0px" type="submit" value="next" name="btnPage"/>&gt;</button></li>
                                                    </ul>
                                                </form>
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