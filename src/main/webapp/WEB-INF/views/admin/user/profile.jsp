<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/user"/>

<!DOCTYPE html>
<html>
<head>
    <title>Thông tin cá nhân</title>
</head>
<body>
<div class="container-fluid" id="container-wrapper">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Thông tin cá nhân</h1>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
            <li class="breadcrumb-item active" aria-current="page">Thông tin cá nhân</li>
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
    <div class="row">
        <div class="col-lg-12 mb-4">
            <div class="card">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Thông tin cá nhân</h6>
                </div>
                <div class="card-body">
                    <form:form id="editForm" modelAttribute="model">
                        <div class="form-group">
                            <label for="userName">Tên đăng nhập</label>
                            <form:input path="userName" id="userName" cssClass="form-control" disabled="true"/>
                        </div>
                        <div class="form-group">
                            <label for="fullName">Họ tên đầy đủ</label>
                            <form:input path="fullName" cssClass="form-control" id="fullName"/>
                        </div>
                        <div class="form-group">
                            <label for="email">Địa chỉ email</label>
                            <form:input path="email" cssClass="form-control" id="email"/>
                        </div>
                        <div class="form-group">
                            <label for="phone">Số điện thoại</label>
                            <form:input path="phone" cssClass="form-control" id="phone"/>
                        </div>
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
                        <div class="form-group">
                            <div class="col-sm-4 img-rps">
                                <input type="file" class="custom-file-input" onchange="previewFile()">
                                <label class="custom-file-label" for="customFile">Chọn ảnh đại diện</label>
                                <hr>
                                <img class="img-thumbnail" id="avatar" src="${model.profilepicture}" height="200" width="300" alt="Chưa có ảnh">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary" id="UpdateUserBtn">Cập nhật</button>
                        <form:hidden path="id" id="userId"/>
                        <form:hidden path="profilepicture" id="profilepicture"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    function previewFile() {
        const preview = document.querySelector('#avatar');
        const file = document.querySelector('input[type=file]').files[0];
        const reader = new FileReader();

        reader.addEventListener("load", function () {
            // convert image file to base64 string
            preview.src = reader.result;
            if (reader.result != null) {
                $('#profilepicture').val(reader.result);
            }
        }, false);

        if (file) {
            reader.readAsDataURL(file);
        }
    }

    $("#UpdateUserBtn").click(function (event) {
        event.preventDefault();
        let formData = $("#editForm").serializeArray();
        let dataArray = {};
        $.each(formData, function (i, v) {
            dataArray["" + v.name + ""] = v.value;
        });
        if ($('#userName').val() != "") {
            let userName = $('#userName').val();
            updateInfo(dataArray, userName);
        }
    });

    function updateInfo(data, username) {
        $.ajax({
            url: '${formUrl}/profile/' + username,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/user/profile-"+res.userName+"?message=update_success'/>";
            },
            error: function (res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/user/profile-"+username+"?message=error_system'/>";
            }
        });
    }

</script>
</body>
</html>
