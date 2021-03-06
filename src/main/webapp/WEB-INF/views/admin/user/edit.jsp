<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<c:url var="formUrl" value="/api/user"/>

<!DOCTYPE html>
<html>
<head>
    <c:if test="${not empty model.id}">
        <title>Cập nhật tài khoản</title>
    </c:if>
    <c:if test="${empty model.id}">
        <title>Thêm tài khoản mới</title>
    </c:if>
</head>
<body>
<div class="container-fluid" id="container-wrapper">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <c:if test="${not empty model.id}">
            <h1 class="h3 mb-0 text-gray-800">Cập nhật tài khoản</h1>
        </c:if>
        <c:if test="${empty model.id}">
            <h1 class="h3 mb-0 text-gray-800">Thêm tài khoản mới</h1>
        </c:if>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý tài khoản</li>
            <c:if test="${not empty model.id}">
                <li class="breadcrumb-item active" aria-current="page">Cập nhật tài khoản</li>
            </c:if>
            <c:if test="${empty model.id}">
                <li class="breadcrumb-item active" aria-current="page">Thêm tài khoản mới</li>
            </c:if>
        </ol>
    </div>
    <div class="row">
        <div class="col-lg-12 mb-4">
            <div class="card">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Thông tin tài khoản</h6>
                </div>
                <div class="card-body">
                    <form:form id="editUserForm" modelAttribute="model" name="editUserForm">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="userName">Tên đăng nhập</label>
                                    <c:if test="${not empty model.id}">
                                        <form:input path="userName" id="userName" cssClass="form-control" disabled="true"/>
                                    </c:if>
                                    <c:if test="${empty model.id}">
                                        <form:input path="userName" id="userName" cssClass="form-control"/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="fullName">Họ tên đầy đủ</label>
                                    <form:input path="fullName" cssClass="form-control" id="fullName"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="employeeCode">Mã nhân viên</label>
                                    <c:if test="${not empty model.id}">
                                        <form:input path="employeeCode" id="employeeCode" cssClass="form-control" disabled="true"/>
                                    </c:if>
                                    <c:if test="${empty model.id}">
                                        <form:input path="employeeCode" id="employeeCode" cssClass="form-control"/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="form-group" id="dayOfBirthPicker">
                                    <label for="dayOfBirth">Ngày sinh</label>
                                    <div class="input-group date">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="fas fa-calendar"></i></span>
                                        </div>
                                        <form:input path="dayOfBirth" id="dayOfBirth" cssClass="form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="email">Địa chỉ email</label>
                                    <form:input path="email" cssClass="form-control" id="email"/>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="phone">Số điện thoại</label>
                                    <form:input path="phone" cssClass="form-control" id="phone"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="userName">Vai trò</label>
                                    <form:select path="roleCode" id="roleCode" cssClass="form-control col-lg-4">
                                        <form:option value="" label="--- Chọn vai trò ---"/>
                                        <form:options items="${model.roleDTOs}"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <label for="userName">Giới tính</label>
                                <div class="form-group">
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <form:radiobutton path="gender" id="maleGender" value="M" cssClass="custom-control-input"/>
                                        <label class="custom-control-label" for="maleGender">Nam</label>
                                    </div>
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <form:radiobutton path="gender" id="femaleGender" value="F" cssClass="custom-control-input"/>
                                        <label class="custom-control-label" for="femaleGender">Nữ</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6"></div>
                            <div class="col-lg-6">
                                <c:if test="${not empty model.id}">
                                    <label for="userName">Trạng thái</label>
                                    <div class="form-group">
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <form:radiobutton path="status" id="active" value="1" cssClass="custom-control-input"/>
                                            <label class="custom-control-label" for="active">Đang hoạt động</label>
                                        </div>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <form:radiobutton path="status" id="noneActive" value="0" cssClass="custom-control-input"/>
                                            <label class="custom-control-label" for="noneActive">Không hoạt động</label>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <br />
                        <br />
                        <c:if test="${not empty model.id}">
                            <div class="d-flex justify-content-center">
                                <button type="submit" class="btn btn-primary p-2 mr-3" id="addOrUpdateUserBtn">Cập nhật</button>
                                <button type="button" class="btn btn-warning p-2" id="resetPasswordBtn">Reset mật khẩu</button>
                            </div>
                        </c:if>
                        <c:if test="${empty model.id}">
                            <div class="justify-content-center">
                                <button type="submit" class="btn btn-primary p-2" id="addOrUpdateUserBtn">Thêm mới</button>
                            </div>
                        </c:if>
                        <form:hidden path="id" id="userId"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    $(document).ready(function () {
        // Bootstrap Date Picker
        $('#dayOfBirthPicker .input-group.date').datepicker({
            format: 'dd/mm/yyyy',
            todayBtn: 'linked',
            todayHighlight: true,
            autoclose: true,
        });

        //default date
        // let today = new Date();
        // let date = today.getDate()+'/'+(today.getMonth()+1)+'/'+today.getFullYear();
        // $('#dayOfBirth').val(date);
    });

    $(function() {
        $("form[name='editUserForm']").validate({
            rules: {
                userName: "required",
                fullName: "required",
                roleCode: "required"
            },
            messages: {
                userName: "Không được bỏ trống",
                fullName: "Không được bỏ trống",
                roleCode: "Chưa được lựa chọn"
            },
            submitHandler: function(form) {
                let formData = $("#editUserForm").serializeArray();
                let dataArray = {};
                $.each(formData, function (i, v) {
                    dataArray["" + v.name + ""] = v.value;
                });
                if ($('#userId').val() != "") {
                    let userId = $('#userId').val();
                    updateUser(dataArray, userId);
                }
                else {
                    addUser(dataArray);
                }
            }
        });
    });

    function addUser(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/user/edit-"+res.id+"?message=insert_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/user/edit?message=error_system'/>";
            }
        });
    }

    function updateUser(data, id) {
        $.ajax({
            url: '${formUrl}/' + id,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/user/edit-"+res.id+"?message=update_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/user/edit-"+id+"?message=error_system'/>";
            }
        });
    }

    $('#resetPasswordBtn').click(function (event) {
        event.preventDefault();
        resetPassword($('#userId').val());
    });

    function resetPassword(id) {
        $.ajax({
            url: '${formUrl}/password/'+id+'/reset',
            type: 'PUT',
            dataType: 'json',
            success: function (res) {
                window.location.href = "<c:url value='/admin/user/edit-"+res.id+"?message=reset_password_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/user/edit-"+id+"?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
