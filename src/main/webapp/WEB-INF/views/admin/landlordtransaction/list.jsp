<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/landlord-transactionType"/>
<html>
<head>
    <title>Danh sách loại giao dịch với chủ bất động sản</title>
</head>
<body>
<div class="container-fluid" id="container-wrapper">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Quản lý loại giao dịch với chủ bất động sản</h1>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý loại giao dịch</li>
            <li class="breadcrumb-item active" aria-current="page">Danh sách loại giao dịch với chủ bất động sản</li>
        </ol>
    </div>
    <div class="row justify-content-center">
        <div class="col-lg-5 mb-4">
            <div class="card">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Danh sách loại giao dịch</h6>
                    <span>
                        <button id="addTransactionTypeBtn" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                data-toggle="modal" data-target="#addTransactionTypeModal" title="Thêm loại giao dịch mới">
                            <i class="fa fa-plus-circle"></i>
                        </button>
                        <button id="deleteTransactionTypeBtn" type="button" disabled
                                class="dt-button buttons-html5 btn btn-white btn-danger btn-bold"
                                data-toggle="modal" data-target="#removeWarningModal" title="Xóa loại giao dịch">
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
                        <c:forEach var="item" items="${transactionTypeList}">
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
                    <p>Bạn có chắc là muốn xóa dữ liệu loại giao dịch chứ?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Hủy bỏ</button>
                    <button type="button" class="btn btn-danger" id="acceptRemoveBtn">Xác nhận xóa</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="addTransactionTypeModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabelAddTransactionType"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form id="addTransactionTypeForm" name="addTransactionTypeForm">
                    <div class="modal-header">
                        <h5 class="modal-title text-success" id="addModalTitle">Thêm loại giao dịch mới</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="name">Tên loại giao dịch</label>
                            <input type="text" id="name" class="form-control" name="name" value="">
                        </div>
                        <div class="form-group">
                            <label for="code">Mã loại giao dịch</label>
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
        $("form[name='addTransactionTypeForm']").validate({
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
                let formData = $("#addTransactionTypeForm").serializeArray();
                $.each(formData, function (i, v) {
                    dataArray["" + v.name + ""] = v.value;
                });
                addTransactionType(dataArray)
            }
        });
    });

    function addTransactionType(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function () {
                window.location.href = "<c:url value='/admin/landlord-transaction/list?message=insert_success'/>";
            },
            error: function () {
                window.location.href = "<c:url value='/admin/landlord-transaction/list?message=error_system'/>";
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
        $('#deleteTransactionTypeBtn').attr("disabled", !checkboxes.is(":checked"));
    });

    //button delete event
    $("#acceptRemoveBtn").click(function (event) {
        event.preventDefault();
        let dataArray = $('#tableList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        deleteTransactionType(dataArray);
    });

    //delete api
    function deleteTransactionType(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/landlord-transaction/list?message=delete_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/landlord-transaction/list?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
