<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<ul class="navbar-nav sidebar sidebar-light accordion" id="accordionSidebar">
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon">
            <img class="logo-img" src="<c:url value="/template/admin/img/logo/logo.png"/>">
        </div>
        <div class="sidebar-brand-text mx-3">SamiEstate</div>
    </a>
    <hr class="sidebar-divider my-0">
    <li class="nav-item">
        <a class="nav-link" href="index.html">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>
    <hr class="sidebar-divider">
    <div class="sidebar-heading">
        Quản lý
    </div>
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUser"
           aria-expanded="true" aria-controls="collapseUser">
            <i class="fa fas fa-users"></i>
            <span>Quản lý tài khoản</span>
        </a>
        <div id="collapseUser" class="collapse" aria-labelledby="headingUser" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Quản lý tài khoản</h6>
                <a class="collapse-item" href="<c:url value='/admin/user/list'/>">Danh sách tài khoản</a>
            </div>
        </div>
    </li>
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseRent" aria-expanded="true"
           aria-controls="collapseRent">
            <i class="fa fas fa-building"></i>
            <span>Quản lý cho thuê</span>
        </a>
        <div id="collapseRent" class="collapse" aria-labelledby="headingRent" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Quản lý tòa nhà</h6>
                <a class="collapse-item" href="<c:url value='/admin/building/list'/>">Tòa nhà cho thuê</a>
                <a class="collapse-item" href="form_basics.html">Quản lý quận</a>
            </div>
        </div>
    </li>
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseCustomer" aria-expanded="true"
           aria-controls="collapseCustomer">
            <i class="fas fa-fw fa-table"></i>
            <span>Quản lý khách hàng</span>
        </a>
        <div id="collapseCustomer" class="collapse" aria-labelledby="headingCustomer" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Quản lý khách hàng</h6>
                <a class="collapse-item" href="simple-tables.html">Khách hàng cho thuê</a>
                <a class="collapse-item" href="simple-tables.html">Khách hàng cộng tác</a>
            </div>
        </div>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="ui-colors.html">
            <i class="fas fa-fw fa-palette"></i>
            <span>Quản lý giao dịch</span>
        </a>
    </li>
<%--    <li class="nav-item">--%>
<%--        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePage" aria-expanded="true"--%>
<%--           aria-controls="collapsePage">--%>
<%--            <i class="fas fa-fw fa-table"></i>--%>
<%--            <span>Quản lý giao dịch</span>--%>
<%--        </a>--%>
<%--        <div id="collapsePage" class="collapse" aria-labelledby="headingPage" data-parent="#accordionSidebar">--%>
<%--            <div class="bg-white py-2 collapse-inner rounded">--%>
<%--                <h6 class="collapse-header">Quản lý giao dịch</h6>--%>
<%--                <a class="collapse-item" href="simple-tables.html">Danh sách giao dịch</a>--%>
<%--                <a class="collapse-item" href="simple-tables.html">Các loại giao dịch</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </li>--%>
</ul>
