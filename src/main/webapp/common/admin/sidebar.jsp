<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<ul class="navbar-nav sidebar sidebar-light accordion" id="accordionSidebar">
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<c:url value='/trang-chu'/>">
        <div class="sidebar-brand-icon">
            <img class="logo-img" src="<c:url value="/template/admin/img/logo/logo.png"/>">
        </div>
        <div class="sidebar-brand-text mx-3">SamiEstate</div>
    </a>
    <hr class="sidebar-divider my-0">
    <security:authorize access="hasRole('MANAGER')">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/admin/home'/>">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Dashboard</span>
            </a>
        </li>
    </security:authorize>
    <hr class="sidebar-divider">
    <div class="sidebar-heading">
        Quản lý
    </div>
    <security:authorize access="hasRole('MANAGER')">
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
    </security:authorize>
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseRent" aria-expanded="true"
           aria-controls="collapseRent">
            <i class="fa fas fa-building"></i>
            <span>Quản lý cho thuê</span>
        </a>
        <div id="collapseRent" class="collapse" aria-labelledby="headingRent" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Quản lý cho thuê</h6>
                <a class="collapse-item" href="<c:url value='/admin/building/list'/>">Tòa nhà cho thuê</a>
                <security:authorize access="hasRole('MANAGER')">
                    <a class="collapse-item" href="<c:url value='/admin/district/list'/>">Quản lý quận</a>
                </security:authorize>
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
                <a class="collapse-item" href="<c:url value='/admin/customer/list'/>">Khách hàng cho thuê</a>
            </div>
        </div>
    </li>
    <security:authorize access="hasRole('MANAGER')">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/admin/transaction/list'/>">
                <i class="fas fa-exchange-alt"></i>
                <span>Quản lý loại giao dịch</span>
            </a>
        </li>
    </security:authorize>
</ul>
