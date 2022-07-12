<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formAjaxLandlord" value="/api/landlord"/>

<!DOCTYPE html>
<html>
<head>
    <c:if test="${not empty model.id}">
        <title>Cập nhật chủ bất động sản</title>
    </c:if>
    <c:if test="${empty model.id}">
        <title>Thêm chủ bất động sản mới</title>
    </c:if>
</head>
<body>
<div class="container-fluid" id="container-wrapper">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <c:if test="${not empty model.id}">
            <h1 class="h3 mb-0 text-gray-800">Cập nhật chủ bất động sản</h1>
        </c:if>
        <c:if test="${empty model.id}">
            <h1 class="h3 mb-0 text-gray-800">Thêm chủ bất động sản mới</h1>
        </c:if>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
            <li class="breadcrumb-item">Chủ bất động sản</li>
            <c:if test="${not empty model.id}">
                <li class="breadcrumb-item active" aria-current="page">Cập nhật chủ bất động sản</li>
            </c:if>
            <c:if test="${empty model.id}">
                <li class="breadcrumb-item active" aria-current="page">Thêm chủ bất động sản mới</li>
            </c:if>
        </ol>
    </div>
    <div class="row">
        <div class="col-lg-12 mb-4">
            <div class="card">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Thông tin chủ bất động sản</h6>
                </div>
                <div class="card-body">
                    <form:form id="editLandlordForm" modelAttribute="model" name="editLandlordForm">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="name">
                                        <strong>Họ và tên đầy đủ</strong>
                                    </label>
                                    <form:input path="fullName" id="fullName" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="phone">
                                        <strong>Số điện thoại</strong>
                                    </label>
                                    <form:input path="phone" id="phone" cssClass="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="email">
                                        <strong>Email</strong>
                                    </label>
                                    <form:input path="email" cssClass="form-control" id="email"/>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="company">
                                        <strong>Tên công ty</strong>
                                    </label>
                                    <form:input path="company" cssClass="form-control" id="company"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <c:if test="${not empty model.id}">
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <label for="select2Multiple">
                                            <strong>Chọn nhân viên phụ trách</strong>
                                        </label>
                                        <select class="select2-multiple form-control" name="staffIds" multiple="multiple"
                                                id="select2Multiple">
                                            <c:forEach var="item" items="${staffsAssignLandlord}">
                                                <option value=${item.id} ${item.selected}>${item.fullName} - ${item.employeeCode}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </c:if>
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="statusCode">
                                        <strong>Trạng thái</strong>
                                    </label>
                                    <form:select path="statusCode" id="statusCode" cssClass="form-control">
                                        <form:option value="" label="--- Chọn trạng thái ---"/>
                                        <form:options items="${landlordStatusMap}"/>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="note">
                                <strong>Ghi chú</strong>
                            </label>
                            <form:textarea path="note" rows = "5" cols = "30" id="note" cssClass="form-control"/>
                        </div>
                        <br />
                        <br />
                        <div class="d-flex justify-content-center">
                            <button type="button" onclick="cancel()" class="btn btn-danger p-2 mr-3">Hủy bỏ</button>
                            <c:if test="${not empty model.id}">
                                <button type="submit" class="btn btn-primary" id="addOrUpdateLandlordBtn">Cập nhật</button>
                            </c:if>
                            <c:if test="${empty model.id}">
                                <button type="submit" class="btn btn-primary" id="addOrUpdateLandlordBtn">Thêm mới</button>
                            </c:if>
                        </div>
                        <form:hidden path="id" id="landlordId"/>
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
                                 <table class="table align-items-center table-flush">
                                      <thead class="thead-light">
                                          <tr>
                                              <th class="col-3">Ngày tạo</th>
                                              <th class="col-6">Ghi chú</th>
                                              <th class="col-3">Nhân viên thực hiện</th>
                                          </tr>
                                      </thead>
                                      <tbody>
                                          <c:if test="${not empty transactionOfLandlord}">
                                              <c:forEach var="item2" items="${transactionOfLandlord}">
                                                  <c:if test="${item2.transactionCode == item1.key}">
                                                      <tr>
                                                          <th>${item2.createdDate}</th>
                                                          <th>${item2.note}</th>
                                                          <th>${item2.staffInfo}</th>
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
    $(document).ready(function () {
        // Select2 Multiple
        $('.select2-multiple').select2();
    });

    function cancel() {
        window.location.href = "<c:url value='/admin/landlord/list'/>";
    }

    $(function() {
        $("form[name='editLandlordForm']").validate({
            rules: {
                fullName: "required",
                phone: "required",
                email: "required",
                company: "required",
                statusCode: "required"
            },
            messages: {
                fullName: "Không được bỏ trống",
                phone: "Không được bỏ trống",
                email: "Không được bỏ trống",
                company: "Không được bỏ trống",
                statusCode: "Chưa được lựa chọn"
            },
            submitHandler: function(form) {
                let dataArray = {};
                let formData = $("#editLandlordForm").serializeArray();
                let staffIds = [];
                $.each(formData, function (i, v) {
                    if (v.name == 'staffIds') {
                        staffIds.push(v.value);
                    } else {
                        dataArray["" + v.name + ""] = v.value;
                    }
                });
                dataArray['staffIds'] = staffIds;
                if ($('#landlordId').val() != "") {
                    let landlordId = $('#landlordId').val();
                    updateLandlord(dataArray, landlordId);
                }
                else {
                    addLandlord(dataArray);
                }
            }
        });
    });

    function addLandlord(data) {
        $.ajax({
            url: '${formAjaxLandlord}',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/landlord/edit-"+res.id+"?message=insert_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/landlord/edit?message=error_system'/>";
            }
        });
    }

    function updateLandlord(data, id) {
        $.ajax({
            url: '${formAjaxLandlord}/' + id,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/landlord/edit-"+res.id+"?message=update_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/landlord/edit-"+id+"?message=error_system'/>";
            }
        });
    }

    function addTransaction(transactionCode) {
        event.preventDefault();
        let dataArray = {};
        let noteId = '#note_' + transactionCode;
        let landlordId = $('#landlordId').val();
        let note = $(noteId).val();
        dataArray['note'] = note;
        dataArray['transactionCode'] = transactionCode;
        if (dataArray['note'] != '') {
            addTransactionForLandLord(dataArray);
        } else {
            window.location.href = "<c:url value='/admin/landlord/edit-"+landlordId+"'/>";
        }
    }

    function addTransactionForLandLord(data) {
        let landlordId = $('#landlordId').val();

        $.ajax({
            url: '${formAjaxLandlord}/' + landlordId + '/transaction',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function () {
                window.location.href = "<c:url value='/admin/landlord/edit-"+landlordId+"?message=insert_success'/>";
            },
            error: function () {
                window.location.href = "<c:url value='/admin/landlord/edit-"+landlordId+"?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
