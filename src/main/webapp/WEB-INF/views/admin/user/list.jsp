<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/admin/user/list"/>
<c:url var="formAjax" value="/api/user"/>

<!DOCTYPE html>
<html>
<head>
    <title>Danh sách tài khoản</title>
</head>
<body>
<div class="container-fluid" id="container-wrapper">

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Danh sách tài khoản</h1>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý tài khoản</li>
            <li class="breadcrumb-item active" aria-current="page">Danh sách tài khoản</li>
        </ol>
    </div>

    <div class="row">
        <div class="col-lg-6 mb-4">
            <form:form cssClass="navbar-search" action="${formUrl}" id="listForm" modelAttribute="model"  method="GET">
                <div class="input-group">
                    <form:input path="searchValue" cssClass="form-control bg-light border-1 small"
                                placeholder = "Nhập thông tin tìm kiếm" cssStyle="border-color: #3f51b5;"/>
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="button" id="btnSearch">
                            <i class="fas fa-search fa-sm"></i>
                        </button>
                    </div>
                </div>
                <form:hidden path="page" id="page"/>
            </form:form>
        </div>
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
                    <h6 class="m-0 font-weight-bold text-primary">Danh sách tài khoản</h6>
                    <span>
                        <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                           data-toggle="tooltip" title="Thêm tài khoản" href="<c:url value='/admin/user/edit'/>">
                        <i class="fa fa-plus-circle"></i>
                        </a>
                        <button id="deleteBtn" type="button" disabled
                                 class="dt-button buttons-html5 btn btn-white btn-danger btn-bold" href="javascript:void(0);"
                                 data-toggle="modal" data-target="#removeWarningModal" title="Xóa tài khoản">
                        <i class="fa fa-trash"></i>
                        </button>
                    </span>
                </div>
                <div class="table-responsive">
                    <table class="table align-items-center table-flush">
                        <thead class="thead-light">
                        <tr>
                            <th>
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="checkAll">
                                    <label class="custom-control-label" for="checkAll"/>
                                </div>
                            </th>
                            <th>Tên đăng nhập</th>
                            <th>Họ tên đầy đủ</th>
                            <th>Email</th>
                            <th>Tình trạng</th>
                            <th>Vai trò</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${model.listResult}">
                            <tr>
                                <td>
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="checkbox_${item.id}"
                                               value="${item.id}">
                                        <label class="custom-control-label" for="checkbox_${item.id}"/>
                                    </div>
                                </td>
                                <td>${item.userName}</td>
                                <td>${item.fullName}</td>
                                <td>${item.email}</td>
                                <c:if test="${item.status == 1}">
                                    <td><span class="badge badge-success">Hoạt động</span></td>
                                </c:if>
                                <c:if test="${item.status == 0}">
                                    <td><span class="badge badge-danger">Không hoạt động</span></td>
                                </c:if>
                                <td>${item.roleName}</td>
                                <td><a href="<c:url value="/admin/user/edit-${item.id}"/>" class="btn btn-sm btn-primary">
                                    Chỉnh sửa
                                </a></td>
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
                    <p>Bạn có chắc là muốn xóa dữ liệu tài khoản chứ ?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Hủy bỏ</button>
                    <button type="button" class="btn btn-danger" id="acceptRemoveBtn">Xác nhận xóa</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#btnSearch').click(function () {
            $('#listForm').submit();
        });
    });
</script>
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
                    $('#listForm').submit();
                }
            }
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
        let dataArray = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        deleteUser(dataArray);
    });

    //delete api
    function deleteUser(data) {
        $.ajax({
            url: '${formAjax}',
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/user/list?message=delete_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/user/list?message=error_system'/>";
            }
        });
    }

</script>
</body>
</html>
