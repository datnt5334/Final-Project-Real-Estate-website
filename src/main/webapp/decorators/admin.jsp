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
    <link href="<c:url value="/template/admin/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/template/admin/css/ruang-admin.css"/>" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
    <script src="<c:url value="/template/admin/js/jquery.twbsPagination.js"/>" type="text/javascript"></script>

</head>
<body id="page-top">

<div id="wrapper">
    <!-- Sidebar -->
    <%@ include file="/common/admin/sidebar.jsp" %>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <!-- TopBar -->
            <%@ include file="/common/admin/topbar.jsp" %>
            <dec:body/>
        </div>
        <!-- Footer -->
        <%@ include file="/common/admin/footer.jsp" %>
    </div>
</div>

<!-- Scroll to top -->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<script src="<c:url value="/template/admin/vendor/jquery-easing/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/template/admin/js/ruang-admin.min.js"/>"></script>
</body>
</html>
