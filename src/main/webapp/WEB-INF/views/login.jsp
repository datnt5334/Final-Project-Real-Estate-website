<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title>Trang đăng nhập</title>
</head>
<body>
<div class="container-login">
    <div class="row justify-content-center">
        <div class="col-xl-6 col-lg-12 col-md-9">
            <div class="card shadow-sm my-5">
                <div class="card-body p-0">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="login-form">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Trang đăng nhập</h1>
                                </div>
                                <c:if test="${param.incorrectAccount != null}">
                                    <div class="alert alert-danger" role="alert">
                                        Tên tài khoản huặc mật khẩu không chính xác!
                                    </div>
                                </c:if>
                                <c:if test="${param.accessDenied != null}">
                                    <div class="alert alert-danger" role="alert">
                                        Bạn không có quyền truy cập!
                                    </div>
                                </c:if>
                                <c:if test="${param.sessionTimeout != null}">
                                    <div class="alert alert-danger" role="alert">
                                        Hết phiên đăng nhập!
                                    </div>
                                </c:if>
                                <form class="user" action="j_spring_security_check" id="formLogin" method="post">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="userName" name="j_username"
                                               placeholder="Tên đăng nhập">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" id="password" name="j_password"
                                               placeholder="Mật khẩu">
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary btn-block" >Đăng nhập</button>
                                    </div>
                                    <hr>
                                </form>
                                <div class="text-center">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
