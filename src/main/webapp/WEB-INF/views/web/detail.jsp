<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/customer-form"/>
<html>
<head>
    <title>Thông tin chi tiết</title>
</head>
<body>
<!--/ Intro Single star /-->
<section class="intro-single">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-lg-8">
                <div class="title-single-box">
                    <h1 class="title-single">${model.name}</h1>
                    <span class="color-text-a">Thông tin chi tiết</span>
                </div>
            </div>
            <div class="col-md-12 col-lg-4">
                <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="index.html">Home</a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">
                            ${model.name}
                        </li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</section>
<!--/ Intro Single End /-->

<!--/ Property Single Star /-->
<section class="property-single nav-arrow-b">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div id="property-single-carousel" class="owl-carousel owl-arrow gallery-property">
                    <div class="carousel-item-b">
                        <img src="${model.image}" alt="">
                    </div>
                </div>
                <div class="row justify-content-between">
                    <div class="col-md-6 col-lg-6">
                        <div class="property-price d-flex justify-content-center foo">
                            <div class="card-header-c d-flex">
                                <div class="card-box-ico">
                                    <span class="ion-money">$</span>
                                </div>
                                <div class="card-title-c align-self-center">
                                    <h5 class="title-c">${model.rentPrice}/m<sup>2</sup></h5>
                                </div>
                            </div>
                        </div>
                        <div class="property-summary">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="title-box-d section-t4">
                                        <h3 class="title-d">Thông tin</h3>
                                    </div>
                                </div>
                            </div>
                            <div class="summary-list">
                                <ul class="list">
                                    <li class="d-flex justify-content-between">
                                        <strong>Địa điểm:</strong>
                                        <span>${model.address}</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Hướng:</strong>
                                        <span>${model.direction}</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Diện tích thuê:</strong>
                                        <span>${model.rentArea} m <sup>2</sup></span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Diện tích sàn:</strong>
                                        <span>${model.floorArea} m <sup>2</sup></span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Số tầng hầm:</strong>
                                        <span>${model.numberOfBasement}</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Hạng tòa nhà:</strong>
                                        <span>${model.level}</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Thời hạn cho thuê:</strong>
                                        <span>${model.rentTime}</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Đặt cọc trước:</strong>
                                        <span>${model.deposit}</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Thanh toán theo:</strong>
                                        <span>${model.payment}</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Phí gửi ô tô:</strong>
                                        <span>${model.carFee}</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Phí gửi xe máy:</strong>
                                        <span>${model.motorbikeFee}</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Phí ngoài giờ:</strong>
                                        <span>${model.overtimeFee}</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Tiền điện:</strong>
                                        <span>${model.electricityFee}</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <strong>Tiền nước:</strong>
                                        <span>${model.waterFee}</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 section-md-t3">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="title-box-d">
                                    <h3 class="title-d">Giới thiệu tòa nhà</h3>
                                </div>
                            </div>
                        </div>
                        <div class="property-description">
                            ${model.note}
                        </div>
                        <br />
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="title-box-d">
                                    <h3 class="title-d">Kết cấu tòa nhà</h3>
                                </div>
                            </div>
                        </div>
                        <div class="property-description">
                            ${model.structure}
                        </div>
                    </div>
                </div>
                <div class="row justify-content-between">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="title-box-d section-t4">
                                <h3 class="title-d">Vị trí tòa nhà</h3>
                            </div>
                        </div>
                        <div class="contact-map">
                            ${model.map}
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="row section-t3">
                    <div class="col-sm-12">
                        <div class="title-box-d">
                            <h3 class="title-d">Liên hệ chúng tôi</h3>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 col-lg-7 ">
                        <c:forEach var="item" items="${staffs}">
                            <div class="row mb-5">
                                <div class="col-md-6 col-lg-6">
                                    <img src="${item.profilepicture}" alt="" class="img-fluid">
                                </div>
                                <div class="col-md-6 col-lg-6">
                                    <div class="property-agent">
                                        <h4 class="title-agent">${item.fullName}</h4>
                                        <ul class="list-unstyled">
                                            <li class="d-flex justify-content-between">
                                                <strong>Phone:</strong>
                                                <span class="color-text-a">${item.phone}</span>
                                            </li>
                                            <li class="d-flex justify-content-between">
                                                <strong>Email:</strong>
                                                <span class="color-text-a">${item.email}</span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-md-12 col-lg-5">
                        <div class="property-contact">
                            <form class="form-a" id="customerSubmitForm" method="POST" onsubmit="return validateForm()">
                                <div class="row">
                                    <div class="col-md-12 mb-1">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-lg form-control-a" id="customerName"
                                                   name="name" placeholder="Tên *" required>
                                        </div>
                                    </div>
                                    <div class="col-md-12 mb-1">
                                        <div class="form-group">
                                            <input type="email" class="form-control form-control-lg form-control-a" id="customerEmail"
                                                   name=email placeholder="Email *" required>
                                        </div>
                                    </div>
                                    <div class="col-md-12 mb-1">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-lg form-control-a" id="customerPhone"
                                                   name="phone" placeholder="Số điện thoại *" required>
                                        </div>
                                    </div>
                                    <div class="col-md-12 mb-1">
                                        <div class="form-group">
                                             <textarea id="textMessage" class="form-control" placeholder="Ghi chú *" id="customerNote"
                                                       name="note" cols="45" rows="5" required>
                                             </textarea>
                                        </div>
                                    </div>
                                    <div class="col-md-12 mb-1">
                                        <div class="g-recaptcha form-group" data-sitekey="6LeKxNweAAAAANhCY8VUlwGaX7SyjyGXJfBeMRiZ"></div>
                                    </div>
                                    <div class="col-md-12">
                                        <button type="submit" id="buttonSubmitForm" class="btn btn-a">Gửi yêu cầu</button>
                                    </div>
                                    <input type="hidden" name="demand" value="${model.name}, ${model.address}">
                                    <input type="hidden" name="status" value="CHUA_XU_LY">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--/ Property Single End /-->
<script>
    
    function validateForm() {
        if (grecaptcha.getResponse()) {
            event.preventDefault();
            let dataArray = {};
            let formData = $("#customerSubmitForm").serializeArray();
            $.each(formData, function (i, v) {
                dataArray["" + v.name + ""] = v.value;
            });
            addCustomerForm(dataArray);
            return true;
        } else {
            alert("Please prove that you're not robot!!!")
            return false;
        }
    }

    function addCustomerForm(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                alert("Yêu cầu của bạn đã được gửi. Vui lòng đợi phản hồi!!!")
                window.location.href = "<c:url value='/chi-tiet-${model.id}'/>";
            },
            error: function (res) {
                console.log("error");
            }
        });
    }

    let header = document.getElementById("navbarDefault");
    let current = header.getElementsByClassName("active");
    current[0].className = current[0].className.replace(" active", "");

</script>
<script>
    document.querySelectorAll('oembed[url]').forEach(element => {
        // Create the <a href="..." class="embedly-card"></a> element that Embedly uses
        // to discover the media.
        const anchor = document.createElement('a');

        anchor.setAttribute('href', element.getAttribute('url'));
        anchor.className = 'embedly-card';

        element.appendChild(anchor);
    });
</script>
</body>
</html>
