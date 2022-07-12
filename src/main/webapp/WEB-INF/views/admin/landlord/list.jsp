<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/admin/landlord/list"/>
<c:url var="formAjaxLandlord" value="/api/landlord"/>

<!DOCTYPE html>
<html>
<head>
    <title>Chủ bất động sản</title>
</head>
<body>
<div class="container-fluid" id="container-wrapper">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Danh sách chủ bất động sản</h1>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý chủ bất động sản</li>
            <li class="breadcrumb-item active" aria-current="page">Chủ bất động sản</li>
        </ol>
    </div>
    <form:form modelAttribute="model" action="${formUrl}" id="landlordSearchForm" method="GET">
        <div class="card">
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Form tìm kiếm</h6>
            </div>
            <div class="row mx-3 mt-2">
                <div class="col-lg-6">
                    <div class="fg-line">
                        <label class="control-label">
                            <strong>Họ và tên</strong>
                        </label>
                        <form:input path="fullName" cssClass="form-control input-sm"/>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="fg-line">
                        <label class="control-label">
                            <strong>Số điện thoại</strong>
                        </label>
                        <form:input path="phone" cssClass="form-control input-sm"/>
                    </div>
                </div>
            </div>
            <div class="row mx-3 mt-2">
                <div class="col-lg-4">
                    <label class="control-label">
                        <strong>Email</strong>
                    </label>
                    <form:input path="email" cssClass="form-control input-sm"/>
                </div>
                <security:authorize access="hasRole('MANAGER')">
                    <div class="col-lg-4">
                        <div class="fg-line">
                            <label class="control-label">
                                <strong>Nhân viên phụ trách</strong>
                            </label>
                            <form:select path="staffId" id="staffId" cssClass="form-control mb-3">
                                <form:option value="" label="--- Chọn nhân viên phụ trách ---"/>
                                <form:options items="${staffsMap}"/>
                            </form:select>
                        </div>
                    </div>
                </security:authorize>
                <div class="col-lg-4">
                    <div class="fg-line">
                        <label class="control-label">
                            <strong>Trạng thái</strong>
                        </label>
                        <form:select path="statusCode" id="statusCode" cssClass="form-control mb-3">
                            <form:option value="" label="--- Chọn trạng thái ---"/>
                            <form:options items="${landlordStatusMap}"/>
                        </form:select>
                    </div>
                </div>
            </div>
            <div class="row mx-4 my-3">
                <button id="btnSearch" type="button" class="btn btn-sm btn-success p-2">
                    Tìm kiếm
                    <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                </button>
            </div>
            <form:hidden path="page" id="page"/>
        </div>
    </form:form>

    <hr>
    <div class="row">
        <div class="col-lg-12 mb-4">
            <div class="card">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Danh sách chủ bất động sản</h6>
                    <span>
                        <security:authorize access="hasRole('MANAGER')">
                            <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                               data-toggle="tooltip" title="Thêm chủ bất động sản" href="<c:url value='/admin/landlord/edit'/>">
                               <i class="fa fa-plus-circle"></i>
                            </a>
                        </security:authorize>
                        <button id="deleteBtn" type="button" disabled
                                class="dt-button buttons-html5 btn btn-white btn-danger btn-bold" href="javascript:void(0);"
                                data-toggle="modal" data-target="#removeWarningModal" title="Xóa chủ bất động sản">
                                <i class="fa fa-trash"></i>
                        </button>
                    </span>
                </div>
                <div class="table-responsive">
                    <table class="table align-items-center table-flush" id="tableList">
                        <thead class="thead-light">
                        <tr>
                            <th class="col-1">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="checkAll">
                                    <label class="custom-control-label" for="checkAll"/>
                                </div>
                            </th>
                            <th class="col-2">Họ tên</th>
                            <th class="col-1">Di động</th>
                            <th class="col-1">Email</th>
                            <th class="col-2">Công ty</th>
                            <th class="col-2">Trạng thái</th>
                            <th class="col-1">Người tạo</th>
                            <th class="col-2">Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${model.listResult}">
                            <tr>
                                <td class="col-1">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="checkboxLandlord_${item.id}"
                                               value="${item.id}">
                                        <label class="custom-control-label" for="checkboxLandlord_${item.id}"/>
                                    </div>
                                </td>
                                <td class="col-2">${item.fullName}</td>
                                <td class="col-1">${item.phone}</td>
                                <td class="col-1">${item.email}</td>
                                <td class="col-2">${item.company}</td>
                                <td class="col-2">${item.status}</td>
                                <td class="col-1">${item.createdBy}</td>
                                <td class="col-2">
                                    <span>
                                        <a flag="info" class="dt-button buttons-colvis btn btn-white btn-success btn-bold"
                                           data-toggle="tooltip" title="Chỉnh sửa k̀hách hàng" href="<c:url value='/admin/landlord/edit-${item.id}'/>">
                                        <i class="fa fa-edit"></i>
                                    </a>
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <hr>
                <div class="card-footer">
                    <span>${model.totalItems} kết quả được tìm thấy</span>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-5 mb-4 justify-content-center">
            <nav aria-label="Page navigation">
                <ul class="pagination" id="pagination"></ul>
            </nav>
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
                    <p>Bạn có chắc là muốn xóa dữ liệu khách hàng chứ?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Hủy bỏ</button>
                    <button type="button" class="btn btn-danger" id="acceptRemoveBtn">Xác nhận xóa</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    // pagination event
    let totalItems = ${model.totalItems};
    let currentPage = ${model.page};
    let maxPageItems = ${model.maxPageItems};
    let totalPage = Math.ceil(totalItems/maxPageItems);
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPage,
            visiblePages: 4,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#page').val(page);
                    $('#landlordSearchForm').submit();
                }
            }
        });
    });

    //submit search
    $(document).ready(function () {
        $('#btnSearch').click(function () {
            $('#landlordSearchForm').submit();
        });
    });

    //checkbox all event
    $("#checkAll").click(function(){
        $("input[type='checkbox']").not(this).prop('checked', this.checked);
    });

    //button disable with checkbox event
    let checkboxes = $("input[type='checkbox']")
    checkboxes.change(function() {
        $('#deleteBtn').attr("disabled", !checkboxes.is(":checked"));
    });

    //button delete event
    $("#acceptRemoveBtn").click(function (event) {
        event.preventDefault();
        let dataArray = $('#tableList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        deleteLandLord(dataArray);
    });

    //delete api
    function deleteLandLord(data) {
        $.ajax({
            url: '${formAjaxLandlord}',
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/landlord/list?message=delete_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/landlord/list?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
