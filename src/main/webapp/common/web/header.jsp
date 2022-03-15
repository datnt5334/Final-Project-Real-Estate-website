<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<nav class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
    <div class="container">
        <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarDefault"
                aria-controls="navbarDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span></span>
            <span></span>
            <span></span>
        </button>
        <a class="navbar-brand text-brand" href="<c:url value='/trang-chu'/>">Sami<span class="color-b">Estate</span></a>
        <button type="button" class="btn btn-link nav-search navbar-toggle-box-collapse d-md-none" data-toggle="collapse"
                data-target="#navbarTogglerDemo01" aria-expanded="false">
            <span class="fa fa-search" aria-hidden="true"></span>
        </button>
        <div class="navbar-collapse collapse justify-content-center" id="navbarDefault">
            <ul class="navbar-nav">
                <li class="nav-item" id="navHome">
                    <a class="nav-link active" href="<c:url value='/trang-chu'/>">Trang chủ</a>
                </li>
                <li class="nav-item" id="navAbout">
                    <a class="nav-link" href="#">Về chúng tôi</a>
                </li>
                <li class="nav-item" id="navProperties">
                    <a class="nav-link" href="<c:url value='/properties'/>">Tòa nhà văn phòng</a>
                </li>
                <li class="nav-item" id="navContact">
                    <a class="nav-link" href="#">Liên hệ</a>
                </li>
            </ul>
        </div>
        <button type="button" class="btn btn-b-n navbar-toggle-box-collapse d-none d-md-block" data-toggle="collapse"
                data-target="#navbarTogglerDemo01" aria-expanded="false">
            <span class="fa fa-search" aria-hidden="true"></span>
        </button>
    </div>
</nav>
