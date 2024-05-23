<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : signIn
    Created on : Apr 5, 2024, 11:50:52 PM
    Author     : chun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
        <link rel="icon" type="image/x-icon" href="Image/Logo/ims-logo.png">

        <!-- Bootstrap 5  -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <!-- multi select -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" />
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css" />
        <!-- link to css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/password-reset.css">

    </head>
    <body>
        <div class="card">
            <div class="card-title d-flex justify-content-center ">
                <div class="d-flex align-items-center">
                    <img src="Image/Logo/ims-logo.png" class="logo" />
                    <p class="mbrand-name">IMS Recruitment</p>
                </div>
            </div>
            <hr>
            <c:if test="${requestScope.tokenExpired != null}">
                <div class="mb-3">
                    <div class="">
                        <p class="mnoti">
                            <span id="mtitle2">${requestScope.tokenExpired}</span> 
                        </p>
                    </div>
                    <div class="d-flex justify-content-center" style="margin-top: 30px;">
                        <a class="button-2 btn btn-secondary" href="login">Cancel</a>
                    </div>
                </div>
            </c:if>
            <c:if test="${requestScope.tokenExpired == null}">
                <form action="reset-password" method="POST">
                    <div class="">
                        <div class="mb-3">
                            <div class="">
                                <p class="mnoti">
                                    <span id="mtitle1" class="d-flex justify-content-center">Reset Password </span>
                                    <span id="mtitle2">Please set your new password</span> 
                                </p>
                                <br>
                            </div>
                            <div>
                                <div class="d-flex justify-content-between">
                                    <label for="password">Password:</label>
                                    <input type="password" id="password" name="password" class="form-control" placeholder="Enter password"
                                           required>
                                </div>
                                <div>
                                    <p class="mtip">"Use at least 1 letter, 1 number and 7 characters"</p>
                                    <c:if test="${requestScope.passwordErr != null}">
                                        <div class="d-flex">
                                            <p class="mwrong-password">${requestScope.passwordErr}</p> 
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <div>
                                <div class="d-flex justify-content-between">
                                    <label for="re-password">Re-Password:</label>
                                    <input type="password" id="re-password" name="re-password" class="form-control" placeholder="Confirm password"
                                           required>
                                </div>
                                <c:if test="${requestScope.rePasswordErr != null}">
                                    <div class="d-flex mt-3">
                                        <p class="mwrong-password">${requestScope.rePasswordErr}</p> 
                                    </div>
                                </c:if> 
                            </div>

                            <input type="hidden" name="token" value="${requestScope.token}">
                            <div class="d-flex justify-content-center" style="margin-top: 30px;">
                                <button type="submit" class="button-2" style="background-color: #1E96FC; color: #fff">Submit</button>
                            </div>
                        </div>
                    </div>
                </form>
            </c:if>
        </div>

        <!-- Bootstrap Bundle JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    </body>
</html>
