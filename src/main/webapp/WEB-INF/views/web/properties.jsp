<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/properties"/>
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
          <h1 class="title-single">Tòa nhà văn phòng</h1>
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
              Danh sách tòa nhà
            </li>
          </ol>
        </nav>
      </div>
      <form:form modelAttribute="model" action="${formUrl}" id="buildingsForm" method="GET">
        <form:hidden path="page" id="page"/>
      </form:form>
    </div>
  </div>
</section>
<!--/ Intro Single End /-->

<!--/ Property Grid Star /-->
<section class="property-grid grid">
  <div class="container">
    <div class="row">
      <c:forEach var="item" items="${model.listResult}">
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
<!--/ Property Grid End /-->

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
          $('#buildingsForm').submit();
        }
      }
    });
  });

  let header = document.getElementById("navbarDefault");
  let selectedItem = document.getElementById("navProperties")
  let current = header.getElementsByClassName("active");
  current[0].className = current[0].className.replace(" active", "");
  selectedItem.className += " active";
</script>

</body>
</html>
