<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<c:url var="formUrl" value="/admin/customer/list"/>
<c:url var="formAjaxCustomer" value="/api/customer"/>

<!DOCTYPE html>
<html>
<head>
    <title>Khách hàng cho thuê</title>
</head>
<body>
<div class="container-fluid" id="container-wrapper">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Danh sách khách hàng</h1>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý khách hàng</li>
            <li class="breadcrumb-item active" aria-current="page">Khách hàng cho thuê</li>
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

    <form:form modelAttribute="model" action="${formUrl}" id="customerSearchForm" method="GET">
        <div class="card">
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Form tìm kiếm</h6>
            </div>
            <div class="row mx-3 mt-2">
                <div class="col-lg-6">
                    <div class="fg-line">
                        <label class="control-label">
                            <strong>Tên khách hàng</strong>
                        </label>
                        <form:input path="name" cssClass="form-control input-sm"/>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="fg-line">
                        <label class="control-label">
                            <strong>Di động</strong>
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
                            <strong>Trạng thái khách hàng</strong>
                        </label>
                        <form:select path="statusCode" id="statusCode" cssClass="form-control mb-3">
                            <form:option value="" label="--- Chọn trạng thái ---"/>
                            <form:options items="${customerStatusMap}"/>
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
                    <h6 class="m-0 font-weight-bold text-primary">Danh sách khách hàng</h6>
                    <span>
                        <security:authorize access="hasRole('MANAGER')">
                            <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                               data-toggle="tooltip" title="Thêm khách hàng" href="<c:url value='/admin/customer/edit'/>">
                               <i class="fa fa-plus-circle"></i>
                            </a>
                        </security:authorize>
                        <button id="deleteBtn" type="button" disabled
                                class="dt-button buttons-html5 btn btn-white btn-danger btn-bold" href="javascript:void(0);"
                                data-toggle="modal" data-target="#removeWarningModal" title="Xóa khách hàng">
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
                            <th class="col-2">Tên</th>
                            <th class="col-1">Di động</th>
                            <th class="col-1">Email</th>
                            <th class="col-2">Nhu cầu</th>
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
                                        <input type="checkbox" class="custom-control-input" id="checkboxCustomer_${item.id}"
                                               value="${item.id}">
                                        <label class="custom-control-label" for="checkboxCustomer_${item.id}"/>
                                    </div>
                                </td>
                                <td class="col-2">${item.name}</td>
                                <td class="col-1">${item.phone}</td>
                                <td class="col-1">${item.email}</td>
                                <td class="col-2">${item.demand}</td>
                                <td class="col-2">${item.status}</td>
                                <td class="col-1">${item.createdBy}</td>
                                <td class="col-2">
                                    <span>
                                    <a flag="info" class="dt-button buttons-colvis btn btn-white btn-success btn-bold"
                                       data-toggle="tooltip" title="Chỉnh sửa k̀hách hàng" href="<c:url value='/admin/customer/edit-${item.id}'/>">
                                        <i class="fa fa-edit"></i>
                                    </a>
                                    <security:authorize access="hasRole('MANAGER')">
                                        <button flag="info" class="dt-button buttons-colvis btn btn-white btn-secondary btn-bold"
                                                data-toggle="tooltip" title="Giao tòa nhà" onclick="assignmentCustomer(${item.id})">
                                                <i class="fa fa-bars"></i>
                                        </button>
                                    </security:authorize>
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
                    <p>Bạn có chắc là muốn xóa dữ liệu tài khoản chứ?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Hủy bỏ</button>
                    <button type="button" class="btn btn-danger" id="acceptRemoveBtn">Xác nhận xóa</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="assignmentCustomerModel" tabindex="-1" role="dialog" aria-labelledby="modalLabelAssignmentCustomer"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-danger" id="staffAssignmentTitle">Danh sách nhân viên</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="table-responsive">
                        <table class="table align-items-center table-flush" id="staffLists">
                            <thead class="thead-light">
                            <tr>
                                <th class="text-center">Chọn nhân viên</th>
                                <th class="text-center">Tên nhân viên</th>
                                <th class="text-center">Email</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                    <input type="hidden" id="customerId" name="customerId" value="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Hủy bỏ</button>
                    <button type="button" class="btn btn-success" id="assignCustomerBtn">Giao khách hàng</button>
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
                    $('#customerSearchForm').submit();
                }
            }
        });
    });

    //submit search
    $(document).ready(function () {
        $('#btnSearch').click(function () {
            $('#customerSearchForm').submit();
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
        deleteCustomer(dataArray);
    });

    //delete api
    function deleteCustomer(data) {
        $.ajax({
            url: '${formAjaxCustomer}',
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/customer/list?message=delete_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/customer/list?message=error_system'/>";
            }
        });
    }

    function assignmentCustomer(customerId) {
        event.preventDefault();
        openModelAssignmentCustomer();
        loadStaffs(customerId);
        $('#customerId').val(customerId);
    }

    function openModelAssignmentCustomer() {
        $('#assignmentCustomerModel').modal();
    }

    function loadStaffs(customerId) {
        $.ajax({
            url: '${formAjaxCustomer}/'+customerId+'/staffs',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (res) {
                let row = '';
                $.each(res, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><div class="custom-control custom-checkbox">' +
                        '<input type="checkbox" class="custom-control-input" id="checkboxStaff_'+item.id+'"' +
                        ' value="'+item.id+'" '+item.checked+'>' +
                        '<label class="custom-control-label" for="checkboxStaff_'+item.id+'"/></div></td>';
                    row += '<td class="text-center">'+item.fullName+'</td>';
                    row += '<td class="text-center">'+item.email+'</td>';
                    row += '</tr>';
                });
                $('#staffLists tbody').html(row);
            },
            error: function (res) {
                console.log('fail');
            }
        });
    }

    $('#assignCustomerBtn').click(function (e) {
        e.preventDefault();
        let dataArray = {};
        dataArray['customerId'] = $('#customerId').val();
        let staffs = $('#staffLists').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        dataArray['staffIds'] = staffs;
        assignStaff(dataArray);
    });

    function assignStaff(data) {
        $.ajax({
            url: '${formAjaxCustomer}/assignment',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function () {
                window.location.href = "<c:url value='/admin/customer/list?message=update_success'/>";
            },
            error: function () {
                console.log('fail');
            }
        });
    }
</script>
</body>
</html>
