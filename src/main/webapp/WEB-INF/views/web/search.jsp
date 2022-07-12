<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Danh sách tòa nhà văn phòng</title>
</head>
<body>
<!--/ Intro Single star /-->
<section class="intro-single">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-lg-8">
                <div class="title-single-box">
                    <h1 class="title-single">Kết quả tìm kiếm</h1>
                    <span class="color-text-a">Danh sách tòa nhà</span>
                </div>
            </div>
            <div class="col-md-12 col-lg-4">
                <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="#">Home</a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">
                            Kết quả tìm kiếm
                        </li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</section>
<!--/ Intro Single End /-->

<!--/ Property Grid Star /-->
<section class="property-grid grid">
    <div class="container">
        <div class="row">
            <c:forEach var="item" items="${modelSearch.listResult}">
                <div class="col-md-4">
                    <div class="card-box-a card-shadow">
                        <div class="img-box-a">
                            <img src="${item.image}" alt="" class="img-a img-fluid" style="height: 350px">
                        </div>
                        <div class="card-overlay">
                            <div class="card-overlay-a-content">
                                <div class="card-header-a">
                                    <h2 class="card-title-a">
                                        <a href="<c:url value='/building-detail-${item.id}'/>">${item.name}</a>
                                    </h2>
                                </div>
                                <div class="card-body-a">
                                    <div class="price-box d-flex">
                                        <span class="price-a">Giá thuê | ${item.rentPrice}$/m<sup>2</sup></span>
                                    </div>
                                    <a href="<c:url value='/building-detail-${item.id}'/>" class="link-a">Nhấn để xem chi tiết
                                        <span class="ion-ios-arrow-forward"></span>
                                    </a>
                                </div>
                                <div class="card-footer-a">
                                    <ul class="card-info d-flex justify-content-around">
                                        <li>
                                            <h4 class="card-info-title">Diện tích thuê</h4>
                                            <span>${item.rentArea} m<sup>2</sup></span>
                                        </li>
                                        <li>
                                            <h4 class="card-info-title">Hướng</h4>
                                            <span>${item.direction}</span>
                                        </li>
                                        <li>
                                            <h4 class="card-info-title">Hạng</h4>
                                            <span>${item.level}</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
<!--/ Property Grid End /-->

<div class="container">
    <nav aria-label="Page navigation">
        <ul class="pagination" id="pagination"></ul>
    </nav>
</div>

<script type="text/javascript">
    // pagination event
    let totalItems = ${modelSearch.totalItems};
    let currentPage = ${modelSearch.page};
    let maxPageItems = ${modelSearch.maxPageItems};
    let totalPage = Math.ceil(totalItems/maxPageItems);
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPage,
            visiblePages: 4,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#pageSearch').val(page);
                    $('#buildingSearchForm').submit();
                }
            }
        });
    });

    let header = document.getElementById("navbarDefault");
    let current = header.getElementsByClassName("active");
    current[0].className = current[0].className.replace(" active", "");
</script>

</body>
</html>
