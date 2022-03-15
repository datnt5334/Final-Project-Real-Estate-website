<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title>Trang bất động sản</title>
</head>
<body>
<!--/ Carousel Star /-->
<div class="intro intro-carousel">
    <div id="carousel" class="owl-carousel owl-theme">
        <c:forEach var="item" items="${buildingView}">
            <div class="carousel-item-a intro-item bg-image" style="background-image: url(${item.image})">
                <div class="overlay overlay-a"></div>
                <div class="intro-content display-table">
                    <div class="table-cell">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-8">
                                    <div class="intro-body">
                                        <h1 class="intro-title mb-4">${item.name}</h1>
                                        <p class="intro-subtitle intro-price">
                                            <a href="<c:url value='/chi-tiet-${item.id}'/>">
                                                <span class="price-a">Giá thuê | ${item.rentPrice}$/m<sup>2</sup></span>
                                            </a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<!--/ Carousel end /-->

<!--/ Property Star /-->
<section class="section-property section-t8">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="title-wrap d-flex justify-content-between">
                    <div class="title-box">
                        <h2 class="title-a">Mới cập nhật</h2>
                    </div>
                </div>
            </div>
        </div>
        <div id="property-carousel" class="owl-carousel owl-theme">
            <c:forEach var="item" items="${latestBuilding}">
                <div class="carousel-item-b">
                    <div class="card-box-a card-shadow">
                        <div class="img-box-a">
                            <img src="${item.image}" alt="" class="img-a img-fluid" style="height: 350px">
                        </div>
                        <div class="card-overlay">
                            <div class="card-overlay-a-content">
                                <div class="card-header-a">
                                    <h2 class="card-title-a">
                                        <a href="<c:url value='/chi-tiet-${item.id}'/>">${item.name}</a>
                                    </h2>
                                </div>
                                <div class="card-body-a">
                                    <div class="price-box d-flex">
                                        <span class="price-a">Giá thuê | ${item.rentPrice}$/m<sup>2</sup></span>
                                    </div>
                                    <a href="<c:url value='/chi-tiet-${item.id}'/>" class="link-a">Nhấn để xem chi tiết
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

<section class="section-property section-t8">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="title-wrap d-flex justify-content-between">
                    <div class="title-box">
                        <h2 class="title-a">Văn phòng hạng A</h2>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <c:forEach var="item" items="${buildingRankA}">
                <div class="col-md-4">
                    <div class="card-box-a card-shadow">
                        <div class="img-box-a">
                            <img src="${item.image}" alt="" class="img-a img-fluid" style="height: 350px">
                        </div>
                        <div class="card-overlay">
                            <div class="card-overlay-a-content">
                                <div class="card-header-a">
                                    <h2 class="card-title-a">
                                        <a href="<c:url value='/chi-tiet-${item.id}'/>">${item.name}</a>
                                    </h2>
                                </div>
                                <div class="card-body-a">
                                    <div class="price-box d-flex">
                                        <span class="price-a">Giá thuê | ${item.rentPrice}$/m<sup>2</sup></span>
                                    </div>
                                    <a href="<c:url value='/chi-tiet-${item.id}'/>" class="link-a">Nhấn để xem chi tiết
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

<section class="section-property section-t8">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="title-wrap d-flex justify-content-between">
                    <div class="title-box">
                        <h2 class="title-a">Văn phòng hạng B</h2>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <c:forEach var="item" items="${buildingRankB}">
                <div class="col-md-4">
                    <div class="card-box-a card-shadow">
                        <div class="img-box-a">
                            <img src="${item.image}" alt="" class="img-a img-fluid" style="height: 350px">
                        </div>
                        <div class="card-overlay">
                            <div class="card-overlay-a-content">
                                <div class="card-header-a">
                                    <h2 class="card-title-a">
                                        <a href="<c:url value='/chi-tiet-${item.id}'/>">${item.name}</a>
                                    </h2>
                                </div>
                                <div class="card-body-a">
                                    <div class="price-box d-flex">
                                        <span class="price-a">Giá thuê | ${item.rentPrice}$/m<sup>2</sup></span>
                                    </div>
                                    <a href="<c:url value='/chi-tiet-${item.id}'/>" class="link-a">Nhấn để xem chi tiết
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

<section class="section-property section-t8">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="title-wrap d-flex justify-content-between">
                    <div class="title-box">
                        <h2 class="title-a">Văn phòng hạng C</h2>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <c:forEach var="item" items="${buildingRankC}">
                <div class="col-md-4">
                    <div class="card-box-a card-shadow">
                        <div class="img-box-a">
                            <img src="${item.image}" alt="" class="img-a img-fluid" style="height: 350px">
                        </div>
                        <div class="card-overlay">
                            <div class="card-overlay-a-content">
                                <div class="card-header-a">
                                    <h2 class="card-title-a">
                                        <a href="<c:url value='/chi-tiet-${item.id}'/>">${item.name}</a>
                                    </h2>
                                </div>
                                <div class="card-body-a">
                                    <div class="price-box d-flex">
                                        <span class="price-a">Giá thuê | ${item.rentPrice}$/m<sup>2</sup></span>
                                    </div>
                                    <a href="<c:url value='/chi-tiet-${item.id}'/>" class="link-a">Nhấn để xem chi tiết
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
<!--/ Property End /-->

</body>
</html>