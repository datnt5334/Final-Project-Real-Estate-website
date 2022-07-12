<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/news"/>
<html>
<head>
    <title>${category.name}</title>
</head>
<body>
<!--/ Intro Single star /-->
<section class="intro-single">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-lg-8">
                <div class="title-single-box">
                    <h1 class="title-single">${category.name}</h1>
                    <span class="color-text-a">Danh sách bài viết</span>
                </div>
            </div>
            <div class="col-md-12 col-lg-4">
                <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="#">Home</a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">
                            ${category.name}
                        </li>
                    </ol>
                </nav>
            </div>
            <form:form modelAttribute="model" action="${formUrl}" id="newsSearchForm" method="GET">
                <form:hidden path="page" id="page"/>
                <form:hidden path="categoryCode" id="categoryCode"/>
            </form:form>
        </div>
    </div>
</section>
<!--/ Intro Single End /-->

<!--/ News Grid Star /-->
<section class="news-grid grid">
    <div class="container">
        <div class="row">
            <c:forEach var="item" items="${model.listResult}">
                <div class="col-md-4">
                    <div class="card-box-b card-shadow news-box">
                        <div class="img-box-b">
                            <img src="${item.thumbnail}" alt="" class="img-b img-fluid" style="height: 350px">
                        </div>
                        <div class="card-overlay">
                            <div class="card-header-b">
                                <div class="card-category-b">
                                    <a href="<c:url value='/news'>
                                   <c:param name="categoryCode" value="${item.categoryCode}"/>
                                </c:url>" class="category-b">${category.name}</a>
                                </div>
                                <div class="card-title-b">
                                    <h2 class="title-2">
                                        <a href="<c:url value='/news-detail-${item.id}'/>">${item.title}</a>
                                    </h2>
                                </div>
                                <div class="card-date">
                                    <span class="date-b">${item.createdDate}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
<!--/ News Grid End /-->

<div class="container">
    <nav aria-label="Page navigation">
        <ul class="pagination" id="pagination"></ul>
    </nav>
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
                    $('#newsSearchForm').submit();
                }
            }
        });
    });

    // update active navbar
    let header = document.getElementById("navbarDefault");
    let current = header.getElementsByClassName("active");
    current[0].className = current[0].className.replace(" active", "");
</script>

</body>
</html>
