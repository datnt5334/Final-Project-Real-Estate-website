<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>

    <title><dec:title default="Trang bất động sản"/></title>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicons -->
    <link href="img/favicon.png" rel="icon">
    <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">

    <!-- Bootstrap CSS File -->
    <link href="<c:url value="/template/web/lib/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Libraries CSS Files -->
    <link href="<c:url value="/template/web/lib/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/web/lib/animate/animate.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/web/lib/ionicons/css/ionicons.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/web/lib/owlcarousel/assets/owl.carousel.min.css"/>" rel="stylesheet">

    <!-- Main Stylesheet File -->
    <link href="<c:url value="/template/web/css/style.css"/>" rel="stylesheet">

</head>
<body>
<div class="click-closed"></div>
<!-- Form Search -->
<%@ include file="/common/web/search.jsp" %>

<!--- Nav Bar -->
<%@ include file="/common/web/header.jsp" %>

<dec:body/>

<!-- Footer -->
<%@ include file="/common/web/footer.jsp" %>

<a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
<div id="preloader"></div>

<!-- JavaScript Libraries -->
<script src="<c:url value="/template/web/lib/jquery/jquery.min.js"></c:url>"></script>
<script src="<c:url value="/template/web/lib/jquery/jquery-migrate.min.js"/>"></script>
<script src="<c:url value="/template/web/lib/popper/popper.min.js"/>"></script>
<script src="<c:url value="/template/web/lib/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/template/web/lib/easing/easing.min.js"/>"></script>
<script src="<c:url value="/template/web/lib/owlcarousel/owl.carousel.min.js"/>"></script>
<script src="<c:url value="/template/web/lib/scrollreveal/scrollreveal.min.js"/>"></script>

<!-- Contact Form JavaScript File -->
<script src="<c:url value="/template/web/contactform/contactform.js"/>"></script>

<!-- Template Main Javascript File -->
<script src="<c:url value="/template/web/js/main.js"/>"></script>
</body>
</html>
