<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/building"/>
<c:url var="formRentAreaUrl" value="/api/rentarea"/>

<!DOCTYPE html>
<html>
<head>
    <c:if test="${not empty model.id}">
        <title>Cập nhật tòa nhà</title>
    </c:if>
    <c:if test="${empty model.id}">
        <title>Thêm tòa nhà mới</title>
    </c:if>
</head>
<body>
<div class="container-fluid" id="container-wrapper">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <c:if test="${not empty model.id}">
            <h1 class="h3 mb-0 text-gray-800">Cập nhật tòa nhà</h1>
        </c:if>
        <c:if test="${empty model.id}">
            <h1 class="h3 mb-0 text-gray-800">Thêm tòa nhà mới</h1>
        </c:if>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
            <li class="breadcrumb-item">Tòa nhà cho thuê</li>
            <c:if test="${not empty model.id}">
                <li class="breadcrumb-item active" aria-current="page">Cập nhật tòa nhà</li>
            </c:if>
            <c:if test="${empty model.id}">
                <li class="breadcrumb-item active" aria-current="page">Thêm tòa nhà mới</li>
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
                    <h6 class="m-0 font-weight-bold text-primary">Thông tin tòa nhà</h6>
                </div>
                <div class="card-body">
                    <form:form id="editBuildingForm" modelAttribute="model" name="editBuildingForm">
                        <div class="form-group">
                            <label for="name">
                                <strong>Tên tòa nhà</strong>
                            </label>
                            <form:input path="name" id="name" cssClass="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="districtCode">
                                <strong>Quận</strong>
                            </label>
                            <form:select path="districtCode" id="districtCode" cssClass="form-control mb-3 col-lg-3">
                                <form:option value="" label="--- Chọn quận---"/>
                                <form:options items="${districtsMap}"/>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <label for="ward">
                                <strong>Phường</strong>
                            </label>
                            <form:input path="ward" cssClass="form-control" id="ward"/>
                        </div>
                        <div class="form-group">
                            <label for="street">
                                <strong>Đường</strong>
                            </label>
                            <form:input path="street" cssClass="form-control" id="street"/>
                        </div>
                        <div class="form-group">
                            <label for="structure">
                                <strong>Kết cấu</strong>
                            </label>
                            <form:textarea path="structure" rows = "5" cols = "30" id="structureEditor" cssClass="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="note">
                                <strong>Ghi chú</strong>
                            </label>
                            <form:textarea path = "note" rows = "5" cols = "30" id="noteEditor" cssClass="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="numberOfBasement">
                                <strong>Số tầng hầm</strong>
                            </label>
                            <form:input path="numberOfBasement" cssClass="form-control" id="numberOfBasement"/>
                        </div>
                        <div class="form-group">
                            <label for="floorArea">
                                <strong>Diện tích sàn</strong>
                            </label>
                            <form:input path="floorArea" cssClass="form-control" id="floorArea"/>
                        </div>
                        <div class="form-group">
                            <label for="direction">
                                <strong>Hướng</strong>
                            </label>
                            <form:input path="direction" cssClass="form-control" id="direction"/>
                        </div>
                        <div class="form-group">
                            <label for="level">
                                <strong>Hạng</strong>
                            </label>
                            <form:input path="level" cssClass="form-control" id="level"/>
                        </div>
                        <div class="form-group">
                            <label for="rentPrice">
                                <strong>Giá thuê</strong>
                            </label>
                            <form:input path="rentPrice" cssClass="form-control" id="rentPrice"/>
                        </div>
                        <div class="form-group">
                            <label for="rentPriceDescription">
                                <strong>Mô tả giá</strong>
                            </label>
                            <form:input path="rentPriceDescription" cssClass="form-control" id="rentPriceDescription"/>
                        </div>
                        <div class="form-group">
                            <label for="serviceFee">
                                <strong>Phí dịch vụ</strong>
                            </label>
                            <form:input path="serviceFee" cssClass="form-control" id="serviceFee"/>
                        </div>
                        <div class="form-group">
                            <label for="motorbikeFee">
                                <strong>Phí mô tô</strong>
                            </label>
                            <form:input path="motorbikeFee" cssClass="form-control" id="motorbikeFee"/>
                        </div>
                        <div class="form-group">
                            <label for="carFee">
                                <strong>Phí ô tô</strong>
                            </label>
                            <form:input path="carFee" cssClass="form-control" id="carFee"/>
                        </div>
                        <div class="form-group">
                            <label for="overtimeFee">
                                <strong>Phí ngoài giờ</strong>
                            </label>
                            <form:input path="overtimeFee" cssClass="form-control" id="overtimeFee"/>
                        </div>
                        <div class="form-group">
                            <label for="waterFee">
                                <strong>Tiền điện</strong>
                            </label>
                            <form:input path="waterFee" cssClass="form-control" id="waterFee"/>
                        </div>
                        <div class="form-group">
                            <label for="electricityFee">
                                <strong>Tiền nước</strong>
                            </label>
                            <form:input path="electricityFee" cssClass="form-control" id="electricityFee"/>
                        </div>
                        <div class="form-group">
                            <label for="deposit">
                                <strong>Đặt cọc</strong>
                            </label>
                            <form:input path="deposit" cssClass="form-control" id="deposit"/>
                        </div>
                        <div class="form-group">
                            <label for="payment">
                                <strong>Thanh toán</strong>
                            </label>
                            <form:input path="payment" cssClass="form-control" id="payment"/>
                        </div>
                        <div class="form-group">
                            <label for="rentTime">
                                <strong>Thời hạn thuê</strong>
                            </label>
                            <form:input path="rentTime" cssClass="form-control" id="rentTime"/>
                        </div>
                        <div class="form-group">
                            <label for="decorationTime">
                                <strong>Thời gian trang trí</strong>
                            </label>
                            <form:input path="decorationTime" cssClass="form-control" id="decorationTime"/>
                        </div>
                        <div class="form-group">
                            <label for="managerName">
                                <strong>Tên quản lý</strong>
                            </label>
                            <form:input path="managerName" cssClass="form-control" id="managerName"/>
                        </div>
                        <div class="form-group">
                            <label for="managerPhone">
                                <strong>SĐT quản lý</strong>
                            </label>
                            <form:input path="managerPhone" cssClass="form-control" id="managerPhone"/>
                        </div>
                        <div class="form-group">
                            <label for="brokerageFee">
                                <strong>Phí môi giới</strong>
                            </label>
                            <form:input path="brokerageFee" cssClass="form-control" id="brokerageFee"/>
                        </div>
                        <div class="form-group">
                            <label for="note">
                                <strong>Bản đồ</strong>
                            </label>
                            <form:textarea path = "map" rows = "5" cols = "30" id="mapEditor" cssClass="form-control"/>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 img-rps">
                                <input type="file" class="custom-file-input" id="customFile" onchange="previewFile()">
                                <label class="custom-file-label" for="customFile">Chọn ảnh đại diện tòa nhà</label>
                                <hr>
                                <c:if test="${empty model.image}">
                                    <img class="img-thumbnail" id="avatar" src="<c:url value="/template/admin/img/no_image.jpg"/>"
                                         height="200" width="300" alt="Chưa có ảnh">
                                </c:if>
                                <c:if test="${not empty model.image}">
                                    <img class="img-thumbnail" id="avatar" src="${model.image}" height="200" width="300" alt="Chưa có ảnh">
                                </c:if>
                            </div>
                        </div>
                        <c:if test="${not empty model.id}">
                            <button type="submit" class="btn btn-primary" id="addOrUpdateBuildingBtn">Cập nhật tòa nhà</button>
                        </c:if>
                        <c:if test="${empty model.id}">
                            <button type="submit" class="btn btn-primary" id="addOrUpdateBuildingBtn">Thêm mới tòa nhà</button>
                        </c:if>
                        <form:hidden path="id" id="buildingId"/>
                        <form:hidden path="image" id="image"/>
                    </form:form>
                </div>
            </div>
            <c:if test="${not empty model.id}">
                <div class="card">
                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Thông tin diện tích thuê</h6>
                        <span>
                        <button id="addBtn" type="button"
                                class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" href="javascript:void(0);"
                                data-toggle="modal" data-target="#addRentAreaModal" title="Thêm diện tích thuê">
                        <i class="fa fa-plus-circle"></i>
                        </button>
                        <button id="deleteBtn" type="button" disabled
                                class="dt-button buttons-html5 btn btn-white btn-danger btn-bold" href="javascript:void(0);"
                                data-toggle="modal" data-target="#removeWarningModal" title="Xóa diện tích thuê">
                        <i class="fa fa-trash"></i>
                        </button>
                    </span>
                    </div>
                    <div class="table-responsive">
                        <table class="table align-items-center table-flush" id="tableList">
                            <thead class="thead-light">
                            <tr>
                                <th>
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="checkAll">
                                        <label class="custom-control-label" for="checkAll"/>
                                    </div>
                                </th>
                                <th>Diện tích</th>
                                <th>Mô tả diện tích</th>
                                <th>Ngày bắt đầu cho thuê</th>
                                <th>Ngày kết thúc cho thuê</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${model.rentAreas}">
                                <tr>
                                    <td>
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="checkbox_${item.id}"
                                                   value="${item.id}">
                                            <label class="custom-control-label" for="checkbox_${item.id}"/>
                                        </div>
                                    </td>
                                    <td>${item.value}</td>
                                    <td>${item.description}</td>
                                    <td>${item.rentDayFrom}</td>
                                    <td>${item.rentDayTo}</td>
                                    <td>
                                        <button class="btn btn-sm btn-primary" onclick="editRentArea(${item.id})">
                                            Chỉnh sửa
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <hr>
                    <div class="card-footer">
                        <span>${model.rentAreas.size()} kết quả được tìm thấy</span>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <div class="modal fade" id="removeWarningModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabelRemoveWarning"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-danger" id="removeModalTitle">Ôi không!</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>Bạn có chắc là muốn xóa dữ liệu diện tích thuê chứ?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Hủy bỏ</button>
                    <button type="button" class="btn btn-danger" id="acceptRemoveBtn">Xác nhận xóa</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="addRentAreaModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabelAddRentArea"
         aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <form id="addRentAreaForm" name="addRentAreaForm">
                    <div class="modal-header">
                        <h5 class="modal-title text-success" id="addModalTitle">Thêm diện tích thuê</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="value">Diện tích thuê</label>
                            <input type="text" id="value" class="form-control" name="value" value="">
                        </div>
                        <div class="form-group">
                            <label for="description">Mô tả diện tích thuê</label>
                            <textarea class="form-control" id="description" rows="3" name="description" value=""></textarea>
                        </div>
                        <div class="form-group" id="rentFromDatePicker">
                            <label for="rentDayFrom">Ngày bắt đầu cho thuê</label>
                            <div class="input-group date">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-calendar"></i></span>
                                </div>
                                <input type="text" class="form-control" value="" id="rentDayFrom" name="rentDayFrom">
                            </div>
                        </div>
                        <div class="form-group" id="rentToDatePicker">
                            <label for="rentDayTo">Ngày kết thúc cho thuê</label>
                            <div class="input-group date">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-calendar"></i></span>
                                </div>
                                <input type="text" class="form-control" value="" id="rentDayTo" name="rentDayTo">
                            </div>
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

    <div class="modal fade" id="updateRentAreaModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabelUpdateRentArea"
         aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <form id="updateRentAreaForm" name="updateRentAreaForm">
                    <div class="modal-header">
                        <h5 class="modal-title text-success" id="updateModalTitle">Cập nhật diện tích thuê</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="RentAreaFormBody">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Hủy bỏ</button>
                        <button type="submit" class="btn btn-success" id="submitUpdateBtn">Cập nhật</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    // For structure input
    ClassicEditor.create(document.querySelector('#structureEditor'))
        .then(editor => {
            window.editor = editor;
        })
        .catch(error => {
            console.error('There was a problem initializing the editor.', error);
        });

    // For note input
    ClassicEditor.create(document.querySelector('#noteEditor'))
        .then(editor => {
            window.editor = editor;
        })
        .catch(error => {
            console.error('There was a problem initializing the editor.', error);
        });

    // For map input
    ClassicEditor.create(document.querySelector('#mapEditor'))
        .then(editor => {
            window.editor = editor;
        })
        .catch(error => {
            console.error('There was a problem initializing the editor.', error);
        });

    function previewFile() {
        const preview = document.querySelector('#avatar');
        const file = document.querySelector('input[type=file]').files[0];
        const reader = new FileReader();

        reader.addEventListener("load", function () {
            // convert image file to base64 string
            preview.src = reader.result;
            if (reader.result != null) {
                $('#image').val(reader.result);
            }
        }, false);

        if (file) {
            reader.readAsDataURL(file);
        }
    }

    $(function() {
        $("form[name='editBuildingForm']").validate({
            rules: {
                name: "required",
                districtCode: "required"
            },
            messages: {
                name: "Không được bỏ trống",
                districtCode: "Không được lựa chọn"
            },
            submitHandler: function(form) {
                let dataArray = {};
                let formData = $("#editBuildingForm").serializeArray();
                $.each(formData, function (i, v) {
                    dataArray["" + v.name + ""] = v.value;
                });
                if ($('#buildingId').val() != "") {
                    let buildingId = $('#buildingId').val();
                    updateBuilding(dataArray, $('#buildingId').val());
                }
                else {
                    addBuilding(dataArray);
                }
            }
        });
    });

    $(function() {
        $("form[name='addRentAreaForm']").validate({
            rules: {
                value: "required",
            },
            messages: {
                value: "Không được bỏ trống",
            },
            submitHandler: function(form) {
                let dataArray = {};
                let formData = $("#addRentAreaForm").serializeArray();
                $.each(formData, function (i, v) {
                    dataArray["" + v.name + ""] = v.value;
                });
                let buildingId = $('#buildingId').val();
                dataArray['buildingId'] = buildingId;
                addRentArea(dataArray, buildingId)
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
                window.location.href = "<c:url value='/admin/building/edit-"+res.id+"?message=insert_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/building/edit?message=error_system'/>";
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
                window.location.href = "<c:url value='/admin/building/edit-"+res.id+"?message=update_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/building/edit-"+id+"?message=error_system'/>";
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
        $('#deleteBtn').attr("disabled", !checkboxes.is(":checked"));
    });

    //button delete event
    $("#acceptRemoveBtn").click(function (event) {
        event.preventDefault();
        let buildingId = $('#buildingId').val();
        let dataArray = $('#tableList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        deleteBuilding(dataArray, buildingId);
    });

    //delete api
    function deleteBuilding(data, id) {
        $.ajax({
            url: '${formRentAreaUrl}',
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/building/edit-"+id+"?message=delete_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/building/edit-"+id+"?message=error_system'/>";
            }
        });
    }

    function editRentArea(rentAreaId) {
        event.preventDefault();
        $('#updateRentAreaModal').modal();
        loadRentArea(rentAreaId);
    }

    function loadRentArea(rentAreaId) {
        $.ajax({
            url: '${formRentAreaUrl}/'+ rentAreaId,
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (res) {
                let row = '';
                row += '<div class="form-group"><label for="valueUpdate">Diện tích thuê</label>' +
                    '<input type="text" id="valueUpdate" class="form-control" name="value" value="'+res.value+'"></div>';
                row += '<div class="form-group"><label for="descriptionUpdate">Mô tả diện tích thuê</label>' +
                    '<textarea class="form-control" id="descriptionUpdate" rows="3" name="description" value="">'+res.description+'</textarea></div>';
                row += '<div class="form-group" id="rentFromUpdateDatePicker"><label for="rentDayFromUpdate">Ngày bắt đầu cho thuê</label>' +
                    '<div class="input-group date"><div class="input-group-prepend"><span class="input-group-text">' +
                    '<i class="fas fa-calendar"></i></span></div>' +
                    '<input type="text" class="form-control" value="'+res.rentDayFrom+'" id="rentDayFromUpdate" name="rentDayFrom"></div></div>';
                row += '<div class="form-group" id="rentToUpdateDatePicker"><label for="rentDayToUpdate">Ngày kết thúc cho thuê</label>' +
                    '<div class="input-group date"> <div class="input-group-prepend"><span class="input-group-text">' +
                    '<i class="fas fa-calendar"></i></span></div>' +
                    '<input type="text" class="form-control" value="'+res.rentDayTo+'" id="rentDayToUpdate" name="rentDayTo"></div></div>';
                row += '<input type="hidden" id="rentAreaId" name="id" value="'+res.id+'">';

                $('#RentAreaFormBody').html(row);

                $('#rentFromUpdateDatePicker .input-group.date').datepicker({
                    format: 'dd/mm/yyyy',
                    todayBtn: 'linked',
                    todayHighlight: true,
                    autoclose: true,
                });
                $('#rentToUpdateDatePicker .input-group.date').datepicker({
                    format: 'dd/mm/yyyy',
                    todayBtn: 'linked',
                    todayHighlight: true,
                    autoclose: true,
                });

                $(function() {
                    $("form[name='updateRentAreaForm']").validate({
                        rules: {
                            value: "required",
                        },
                        messages: {
                            value: "Không được bỏ trống",
                        },
                        submitHandler: function(form) {
                            let dataArray = {};
                            let formData = $("#updateRentAreaForm").serializeArray();
                            $.each(formData, function (i, v) {
                                dataArray["" + v.name + ""] = v.value;
                            });
                            let buildingId = $('#buildingId').val();
                            dataArray['buildingId'] = buildingId;
                            updateRentArea(dataArray, buildingId)
                        }
                    });
                });

            },
            error: function (res) {
                console.log('error');
            }
        });
    }

    function addRentArea(data, id) {
        $.ajax({
            url: '${formRentAreaUrl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function () {
                window.location.href = "<c:url value='/admin/building/edit-"+id+"?message=update_success'/>";
            },
            error: function () {
                window.location.href = "<c:url value='/admin/building/edit-"+id+"?message=error_system'/>";
            }
        });
    }

    function updateRentArea(data, id) {
        $.ajax({
            url: '${formRentAreaUrl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function () {
                window.location.href = "<c:url value='/admin/building/edit-"+id+"?message=update_success'/>";
            },
            error: function () {
                window.location.href = "<c:url value='/admin/building/edit-"+id+"?message=error_system'/>";
            }
        });
    }

    $(document).ready(function () {
        // Bootstrap Date Picker
        $('#rentFromDatePicker .input-group.date').datepicker({
            format: 'dd/mm/yyyy',
            todayBtn: 'linked',
            todayHighlight: true,
            autoclose: true,
        });
        $('#rentToDatePicker .input-group.date').datepicker({
            format: 'dd/mm/yyyy',
            todayBtn: 'linked',
            todayHighlight: true,
            autoclose: true,
        });

        //default date
        let today = new Date();
        let date = today.getDate()+'/'+(today.getMonth()+1)+'/'+today.getFullYear();
        $('#rentDayFrom').val(date);
        $('#rentDayTo').val(date);
    });

</script>
</body>
</html>
