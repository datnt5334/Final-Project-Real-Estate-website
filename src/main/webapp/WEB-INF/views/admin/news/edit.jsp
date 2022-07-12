<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/news"/>

<!DOCTYPE html>
<html>
<head>
    <c:if test="${not empty model.id}">
        <title>Cập nhật bài viết</title>
    </c:if>
    <c:if test="${empty model.id}">
        <title>Thêm mới bài viết</title>
    </c:if>
</head>
<body>
<div class="container-fluid" id="container-wrapper">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <c:if test="${not empty model.id}">
            <h1 class="h3 mb-0 text-gray-800">Cập nhật bài viết</h1>
        </c:if>
        <c:if test="${empty model.id}">
            <h1 class="h3 mb-0 text-gray-800">Thêm mới bài viết</h1>
        </c:if>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
            <li class="breadcrumb-item">Bài viết</li>
            <c:if test="${not empty model.id}">
                <li class="breadcrumb-item active" aria-current="page">Cập nhật bài viết</li>
            </c:if>
            <c:if test="${empty model.id}">
                <li class="breadcrumb-item active" aria-current="page">Thêm mới bài viết</li>
            </c:if>
        </ol>
    </div>
    <div class="row">
        <div class="col-lg-12 mb-4">
            <div class="card">
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Thông tin bài viết</h6>
                </div>
                <div class="card-body">
                    <form:form id="editNewsForm" modelAttribute="model" name="editNewsForm">
                        <div class="form-group">
                            <label for="name">
                                <strong>Tiêu đề bài viết</strong>
                            </label>
                            <form:input path="title" id="name" cssClass="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="categoryCode">
                                <strong>Thể loại</strong>
                            </label>
                            <form:select path="categoryCode" id="categoryCode" cssClass="form-control mb-3 col-lg-3">
                                <form:option value="" label="--- Chọn thể loại---"/>
                                <form:options items="${categoriesMap}"/>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <label for="description">
                                <strong>Mô tả ngắn</strong>
                            </label>
                            <form:textarea path="description" rows = "5" cols = "30" cssClass="form-control"/>
                        </div>
                        <br />
                        <div class="form-group">
                            <div class="col-sm-4 img-rps">
                                <input type="file" class="custom-file-input" id="customFile" onchange="previewFile()">
                                <label class="custom-file-label" for="customFile">Thumbnail</label>
                                <hr>
                                <c:if test="${empty model.thumbnail}">
                                    <img class="img-thumbnail" id="avatar" src="<c:url value="/template/admin/img/no_image.jpg"/>"
                                         height="200" width="300" alt="Chưa có ảnh">
                                </c:if>
                                <c:if test="${not empty model.thumbnail}">
                                    <img class="img-thumbnail" id="avatar" src="${model.thumbnail}" height="200" width="300" alt="thumbnail image">
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="content">
                                <strong>Nội dung</strong>
                            </label>
                            <form:textarea path = "content" rows = "5" cols = "30" id="contentEditor" cssClass="form-control"/>
                        </div>
                        <br />
                        <br />
                        <div class="d-flex justify-content-center">
                            <button type="button" onclick="cancel()" class="btn btn-danger p-2 mr-3">Hủy bỏ</button>
                            <c:if test="${not empty model.id}">
                                <button type="submit" class="btn btn-primary p-2" id="addOrUpdateNewsBtn">Cập nhật bài viết</button>
                            </c:if>
                            <c:if test="${empty model.id}">
                                <button type="submit" class="btn btn-primary p-2" id="addOrUpdateNewsBtn">Thêm mới bài viết</button>
                            </c:if>
                        </div>
                        <form:hidden path="id" id="newsId"/>
                        <form:hidden path="thumbnail" id="thumbnail"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    // for content input
    ClassicEditor.create(document.querySelector('#contentEditor'))
        .then(editor => {
            window.editor = editor;
        })
        .catch(error => {
            console.error('There was a problem initializing the editor.', error);
        });

    function previewFile() {
        const preview = document.querySelector('#avatar');
        const file = document.querySelector('#customFile').files[0];
        const reader = new FileReader();

        reader.addEventListener("load", function () {
            // convert image file to base64 string
            preview.src = reader.result;
            if (reader.result != null) {
                $('#thumbnail').val(reader.result);
            }
        }, false);

        if (file) {
            reader.readAsDataURL(file);
        }
    }

    $(function() {
        $("form[name='editNewsForm']").validate({
            rules: {
                title: "required",
                categoryCode: "required",
                description: "required",
                content: "required"
            },
            messages: {
                title: "Không được bỏ trống",
                categoryCode: "Không được lựa chọn",
                description: "Không được bỏ trống",
                content: "Không được bỏ trống"
            },
            submitHandler: function(form) {
                let dataArray = {};
                let formData = $("#editNewsForm").serializeArray();
                $.each(formData, function (i, v) {
                    dataArray["" + v.name + ""] = v.value;
                });
                if ($('#newsId').val() != "") {
                    let newsId = $('#newsId').val();
                    updateNews(dataArray, $('#newsId').val());
                }
                else {
                    addNews(dataArray);
                }
            }
        });
    });

    function cancel() {
        window.location.href = "<c:url value='/admin/news/list'/>";
    }

    function addNews(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/news/edit-"+res.id+"?message=insert_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/news/edit?message=error_system'/>";
            }
        });
    }

    function updateNews(data, id) {
        $.ajax({
            url: '${formUrl}/' + id,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/news/edit-"+res.id+"?message=update_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/news/edit-"+id+"?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
