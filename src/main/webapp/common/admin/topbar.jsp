<%@ page import="vn.edu.hust.samiestate.security.utils.SecurityUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<nav class="navbar navbar-expand navbar-light bg-navbar topbar mb-4 static-top">
    <button id="sidebarToggleTop" class="btn btn-link rounded-circle mr-3">
        <i class="fa fa-bars"></i>
    </button>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <img class="img-profile rounded-circle"
                     src="<%=SecurityUtils.getPrincipal().getProfilepicture()%>" style="max-width: 60px">
                <span class="ml-2 d-none d-lg-inline text-white small">
                    <%=SecurityUtils.getPrincipal().getFullName()%>
                </span>
            </a>
            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" id="userProfile" href="<c:url value='/admin/user/profile-'/>"
                   onclick="location.href=this.href+'<%=SecurityUtils.getPrincipal().getUsername()%>';return false;">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Thông tin cá nhân
                </a>
                <a class="dropdown-item" id="changePassword" href="<c:url value='/admin/user/password-'/>"
                   onclick="location.href=this.href+'<%=SecurityUtils.getPrincipal().getUsername()%>';return false;">
                    <i class="fa fa-key fa-sm fa-fw mr-2 text-gray-400"></i>
                    Thay đổi mật khẩu
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="javascript:void(0);" data-toggle="modal" data-target="#logoutModal">
                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                    Đăng xuất
                </a>
            </div>
        </li>
    </ul>
</nav>
