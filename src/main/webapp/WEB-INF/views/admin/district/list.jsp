<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/district"/>
<html>
<head>
    <title>Danh sách quận</title>
</head>
<body>
<div class="container-fluid" id="container-wrapper">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Quản lý quận</h1>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý quận</li>
            <li class="breadcrumb-item active" aria-current="page">Danh sách quận</li>
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
        <div class="col-lg-5 mb-4">
            <div class="card">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Danh sách quận</h6>
                    <span>
                        <button id="addDistrictBtn" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                data-toggle="modal" data-target="#addDistrictModal" title="Thêm quận mới">
                            <i class="fa fa-plus-circle"></i>
                        </button>
                        <button id="deleteDistrictBtn" type="button" disabled
                                class="dt-button buttons-html5 btn btn-white btn-danger btn-bold"
                                data-toggle="modal" data-target="#removeWarningModal" title="Xóa quận">
                                <i class="fa fa-trash"></i>
                        </button>
                    </span>
                </div>
                <div class="table-responsive">
                    <table class="table align-items-center table-flush" id="tableList">
                        <thead class="thead-light">
                        <tr>
                            <th class="col-4">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="checkAll">
                                    <label class="custom-control-label" for="checkAll"/>
                                </div>
                            </th>
                            <th class="col-4">Tên</th>
                            <th class="col-4">Mã Code</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${districtList}">
                            <tr>
                                <td class="col-4">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="checkboxCustomer_${item.id}"
                                               value="${item.id}">
                                        <label class="custom-control-label" for="checkboxCustomer_${item.id}"/>
                                    </div>
                                </td>
                                <td class="col-4">${item.name}</td>
                                <td class="col-4">${item.code}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="removeWarningModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabelRemoveWarning"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-danger" id="modal-title">Ôi không!</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>Bạn có chắc là muốn xóa dữ liệu quận chứ?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Hủy bỏ</button>
                    <button type="button" class="btn btn-danger" id="acceptRemoveBtn">Xác nhận xóa</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="addDistrictModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabelAddDistrict"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form id="addDistrictForm" name="addDistrictForm">
                    <div class="modal-header">
                        <h5 class="modal-title text-success" id="addModalTitle">Thêm quận mới</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="name">Tên quận</label>
                            <input type="text" id="name" class="form-control" name="name" value="">
                        </div>
                        <div class="form-group">
                            <label for="code">Mã quận</label>
                            <input type="text" id="code" class="form-control" name="code" value="">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Hủy bỏ</button>
                        <button type="submit" class="btn btn-success" id="submitAddBtn">Thêm</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function() {
        $("form[name='addDistrictForm']").validate({
            rules: {
                name: "required",
                code: "required"
            },
            messages: {
                name: "Không được bỏ trống",
                code: "Không được bỏ trống"
            },
            submitHandler: function(form) {
                let dataArray = {};
                let formData = $("#addDistrictForm").serializeArray();
                $.each(formData, function (i, v) {
                    dataArray["" + v.name + ""] = v.value;
                });
                addDistrict(dataArray)
            }
        });
    });

    function addDistrict(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function () {
                window.location.href = "<c:url value='/admin/district/list?message=insert_success'/>";
            },
            error: function () {
                window.location.href = "<c:url value='/admin/district/list?message=error_system'/>";
            }
        });
    }

    //checkbox all event
    $("#checkAll").click(function(){
        $("input[type='checkbox']").not(this).prop('checked', this.checked);
    });

    //button disable with checkbox event
    let checkboxes = $("input[type='checkbox']")
    checkboxes.change(function() {
        $('#deleteDistrictBtn').attr("disabled", !checkboxes.is(":checked"));
    });

    //button delete event
    $("#acceptRemoveBtn").click(function (event) {
        event.preventDefault();
        let dataArray = $('#tableList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        deleteDistrict(dataArray);
    });

    //delete api
    function deleteDistrict(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/district/list?message=delete_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/district/list?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
