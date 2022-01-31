<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="changePasswordURL" value="/api/user/change-password"/>

<!DOCTYPE html>
<html>
<head>
    <title>Thay đổi mật khẩu</title>
</head>
<body>
<div class="container-fluid" id="container-wrapper">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Thay đổi mật khẩu</h1>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
            <li class="breadcrumb-item active" aria-current="page">Thay đổi mật khẩu</li>
        </ol>
    </div>
    <div class="row">
        <div class="col-lg-12 mb4">
            <c:if test="${not empty messageResponse}">
                <div class="alert alert-block alert-${alert}">
                    <button type="button" class="close" data-dismiss="alert">
                        <i class="ace-icon fa fa-times"></i>
                    </button>
                        ${messageResponse}
                </div>
            </c:if>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-lg-8 mb-4">
            <div class="card">
                <div class="login-form">
                    <div class="card-body">
                        <form:form id="formChangePassword" modelAttribute="model" name="formChangePassword">
                            <div class="form-group">
                                <label for="userName">Tên đăng nhập</label>
                                <form:input path="userName" id="userName" cssClass="form-control" disabled="true"/>
                            </div>
                            <div class="form-group">
                                <label for="oldPassword">Mật khẩu cũ</label>
                                <input type="password" class="form-control" id="oldPassword" name="oldPassword"/>
                            </div>
                            <div class="form-group">
                                <label for="newPassword">Mật khẩu mới</label>
                                <input type="password" class="form-control" id="newPassword" name="newPassword"/>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword">Nhập lại mật khẩu mới</label>
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"/>
                            </div>
                            <button type="submit" class="btn btn-primary" id="changePasswordBtn">Thay đổi mật khẩu</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#changePasswordBtn').click(function (event) {
            $('#changePasswordBtn').submit();
        });
    });
    $(function() {
        $("form[name='formChangePassword']").validate({
            rules: {
                oldPassword: "required",
                newPassword: {
                    required: true,
                    minlength: 6
                },
                confirmPassword: "required"
            },
            messages: {
                oldPassword: "Không được bỏ trống",
                newPassword: {
                    required: "Không được bỏ trống",
                    minlength: "Mật khẩu tối thiểu 6 kí tự"
                },
                confirmPassword: "Không được bỏ trống"
            },
            submitHandler: function(form) {
                let formData = $('#formChangePassword').serializeArray();
                let dataArray = {};
                $.each(formData, function (i, v) {
                    dataArray["" + v.name + ""] = v.value;
                });
                changePassword(dataArray, $('#userName').val());
            }
        });
    });

    function changePassword(data, username) {
        $.ajax({
            url: '${changePasswordURL}/'+username,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                if (res == 'update_success') {
                    window.location.href = "<c:url value='/admin/user/password-"+username+"?message=update_success'/>";
                } else {
                    window.location.href = "<c:url value='/admin/user/password-"+username+"?message=change_password_fail'/>";
                }
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/user/password-"+username+"?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
