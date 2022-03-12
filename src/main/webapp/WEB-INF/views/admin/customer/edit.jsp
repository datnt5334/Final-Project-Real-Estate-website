<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/customer"/>

<!DOCTYPE html>
<html>
<head>
    <c:if test="${not empty model.id}">
        <title>Cập nhật khách hàng mới</title>
    </c:if>
    <c:if test="${empty model.id}">
        <title>Thêm khách hàng mới</title>
    </c:if>
</head>
<body>
<div class="container-fluid" id="container-wrapper">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <c:if test="${not empty model.id}">
            <h1 class="h3 mb-0 text-gray-800">Cập nhật khách hàng</h1>
        </c:if>
        <c:if test="${empty model.id}">
            <h1 class="h3 mb-0 text-gray-800">Thêm khách hàng mới</h1>
        </c:if>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
            <li class="breadcrumb-item">Khách hàng cho thuê</li>
            <c:if test="${not empty model.id}">
                <li class="breadcrumb-item active" aria-current="page">Cập nhật khách hàng</li>
            </c:if>
            <c:if test="${empty model.id}">
                <li class="breadcrumb-item active" aria-current="page">Thêm khách hàng mới</li>
            </c:if>
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
                    <h6 class="m-0 font-weight-bold text-primary">Thông tin khách hàng</h6>
                </div>
                <div class="card-body">
                    <form:form id="editCustomerForm" modelAttribute="model" name="editCustomerForm">
                        <div class="form-group">
                            <label for="name">
                                <strong>Tên khách hàng</strong>
                            </label>
                            <form:input path="name" id="name" cssClass="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="phone">
                                <strong>Số điện thoại</strong>
                            </label>
                            <form:input path="phone" id="phone" cssClass="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="email">
                                <strong>Email</strong>
                            </label>
                            <form:input path="email" cssClass="form-control" id="email"/>
                        </div>
                        <div class="form-group">
                            <label for="company">
                                <strong>Tên công ty</strong>
                            </label>
                            <form:input path="company" cssClass="form-control" id="company"/>
                        </div>
                        <div class="form-group">
                            <label for="demand">
                                <strong>Nhu cầu</strong>
                            </label>
                            <form:input path="demand" cssClass="form-control" id="demand"/>
                        </div>
                        <div class="form-group">
                            <label for="note">
                                <strong>Ghi chú</strong>
                            </label>
                            <form:input path="note" cssClass="form-control" id="note"/>
                        </div>
                        <div class="form-group">
                            <label for="status">
                                <strong>Trạng thái khách hàng</strong>
                            </label>
                            <form:select path="status" id="status" cssClass="form-control mb-3 col-lg-3">
                                <form:option value="" label="--- Chọn trạng thái ---"/>
                                <form:options items="${customerStatusMap}"/>
                            </form:select>
                        </div>
                        <c:if test="${not empty model.id}">
                            <button type="submit" class="btn btn-primary" id="addOrUpdateCustomerBtn">Cập nhật khách hàng</button>
                        </c:if>
                        <c:if test="${empty model.id}">
                            <button type="submit" class="btn btn-primary" id="addOrUpdateCustomerBtn">Thêm mới khách hàng</button>
                        </c:if>
                        <form:hidden path="id" id="customerId"/>
                    </form:form>
                </div>
            </div>
            <c:if test="${not empty model.id}">
                <c:if test="${not empty transactionTypesMap}">
                     <c:forEach var="item1" items="${transactionTypesMap}">
                         <hr>
                         <div class="card">
                             <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                 <h6 class="m-0 font-weight-bold text-primary">${item1.value}</h6>
                                 <span>
                                    <button type="button" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                            onclick="addTransaction('${item1.key}')" title="Thêm giao dịch">
                                        <i class="fa fa-plus-circle"></i>
                                    </button>
                                 </span>
                             </div>
                             <div class="table-responsive">
                                 <table class="table align-items-center table-flush"">
                                      <thead class="thead-light">
                                          <tr>
                                              <th class="col-3">Ngày tạo</th>
                                              <th class="col-6">Ghi chú</th>
                                              <th class="col-3">Nhân viên thực hiện</th>
                                          </tr>
                                      </thead>
                                      <tbody>
                                          <c:if test="${not empty transactionOfCustomer}">
                                              <c:forEach var="item2" items="${transactionOfCustomer}">
                                                  <c:if test="${item2.transactionCode == item1.key}">
                                                      <tr>
                                                          <th>${item2.createdDate}</th>
                                                          <th>${item2.note}</th>
                                                          <th>${item2.staffName}</th>
                                                      </tr>
                                                  </c:if>
                                              </c:forEach>
                                          </c:if>
                                          <tr>
                                              <td class="col-3"></td>
                                              <td class="col-6">
                                                  <input type="text" name="note" value="" class="form-control input-sm"
                                                         id="note_${item1.key}" placeholder="Điền thêm ghi chú mới ...">
                                              </td>
                                              <td class="col-3"></td>
                                          </tr>
                                      </tbody>
                                 </table>
                             </div>
                         </div>
                     </c:forEach>
                </c:if>
            </c:if>
        </div>
    </div>
</div>
<script>
    $(function() {
        $("form[name='editCustomerForm']").validate({
            rules: {
                name: "required",
                email: "required",
                phone: "required",
                status: "required"
            },
            messages: {
                name: "Không được bỏ trống",
                email: "Không được bỏ trống",
                phone: "Không được bỏ trống",
                status: "Chưa được lựa chọn"
            },
            submitHandler: function(form) {
                let dataArray = {};
                let formData = $("#editCustomerForm").serializeArray();
                $.each(formData, function (i, v) {
                    dataArray["" + v.name + ""] = v.value;
                });
                if ($('#customerId').val() != "") {
                    let customerId = $('#customerId').val();
                    updateBuilding(dataArray, customerId);
                }
                else {
                    addBuilding(dataArray);
                }
            }
        });
    });

    function addBuilding(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/customer/edit-"+res.id+"?message=insert_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/customer/edit?message=error_system'/>";
            }
        });
    }

    function updateBuilding(data, id) {
        $.ajax({
            url: '${formUrl}/' + id,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/customer/edit-"+res.id+"?message=update_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/customer/edit-"+id+"?message=error_system'/>";
            }
        });
    }

    function addTransaction(transactionCode) {
        event.preventDefault();
        let dataArray = {};
        let noteId = '#note_' + transactionCode;
        let customerId = $('#customerId').val();
        let note = $(noteId).val();
        dataArray['note'] = note;
        dataArray['transactionCode'] = transactionCode;
        if (dataArray['note'] != '') {
            addTransactionForCustomer(dataArray);
        } else {
            window.location.href = "<c:url value='/admin/customer/edit-"+customerId+"'/>";
        }
    }

    function addTransactionForCustomer(data) {
        let customerId = $('#customerId').val();

        $.ajax({
            url: '${formUrl}/' + customerId + '/transaction',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function () {
                window.location.href = "<c:url value='/admin/customer/edit-"+customerId+"?message=insert_success'/>";
            },
            error: function () {
                window.location.href = "<c:url value='/admin/customer/edit-"+customerId+"?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
