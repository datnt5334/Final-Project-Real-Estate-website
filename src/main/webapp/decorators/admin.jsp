<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title><dec:title default="Trang admin"/></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="<c:url value="/template/admin/img/logo/logo.png"/>" rel="icon">
    <link href="<c:url value="/template/admin/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <script src="https://kit.fontawesome.com/e7580bebbc.js" crossorigin="anonymous"></script>
    <link href="<c:url value="/template/admin/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/template/admin/vendor/bootstrap-datepicker/css/bootstrap-datepicker.min.css"/>" rel="stylesheet" >
    <link href="<c:url value="/template/admin/css/ruang-admins.css"/>" rel="stylesheet">
    <script src="<c:url value="/template/admin/vendor/jquery/jquery.min.js"/>"></script>
    <script src="<c:url value="/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
    <script src="<c:url value="/template/admin/js/jquery.twbsPagination.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/template/admin/ckeditor5/ckeditor.js"/>"></script>
    <link href="<c:url value="/template/admin/css/toast.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/template/admin/vendor/select2/dist/css/select2.min.css"/>" rel="stylesheet" type="text/css">
</head>
<body id="page-top">
<div id="wrapper" class="position-relative">
    <c:if test="${not empty messageResponse}">
        <c:if test="${alert == 'success'}">
            <div class="mtoast">
                <div class="mtoast-content">
                    <i class="fa fa-check-circle mcheck-success"></i>
                    <div class="mmessage">
                        <span class="mtext text-1">Success</span>
                        <span class="mtext text-2">${messageResponse}</span>
                    </div>
                </div>
                <i class="fa fa-times mclose"></i>
                <div class="mprogress"></div>
            </div>
        </c:if>
        <c:if test="${alert == 'danger'}">
            <div class="mtoast">
                <div class="mtoast-content">
                    <i class="fa fa fa-times mcheck-error"></i>
                    <div class="mmessage">
                        <span class="mtext text-1">Error</span>
                        <span class="mtext text-2">${messageResponse}</span>
                    </div>
                </div>
                <i class="fa fa-times mclose"></i>
                <div class="mprogress"></div>
            </div>
        </c:if>
    </c:if>
    <c:if test="${empty messageResponse}">
        <div class="mtoast" hidden>
            <div class="mtoast-content">
                <i class="fa fa-check-circle mcheck-success"></i>
                <div class="mmessage">
                    <span class="mtext text-1">Success</span>
                    <span class="mtext text-2">${messageResponse}</span>
                </div>
            </div>
            <i class="fa fa-times mclose"></i>
            <div class="mprogress"></div>
        </div>
    </c:if>
    <!-- Sidebar -->
    <%@ include file="/common/admin/sidebar.jsp" %>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <!-- TopBar -->
            <%@ include file="/common/admin/topbar.jsp" %>
            <dec:body/>
            <!-- Modal Logout -->
            <%@ include file="/common/admin/model.jsp" %>
        </div>
        <!-- Footer -->
        <%@ include file="/common/admin/footer.jsp" %>
    </div>
</div>

<!-- Scroll to top -->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<script src="<c:url value="/template/admin/js/toast.js"/>"></script>
<script src="<c:url value="/template/admin/vendor/jquery-easing/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/template/admin/js/ruang-admin.min.js"/>"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
<script src="<c:url value="/template/admin/vendor/bootstrap-datepicker/js/bootstrap-datepicker.min.js"/>"></script>
<script src="<c:url value="/template/admin/vendor/select2/dist/js/select2.min.js"/>"></script>
</body>
</html>
