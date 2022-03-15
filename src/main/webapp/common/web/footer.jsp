<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<section class="section-footer">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-md-4">
                <div class="widget-a">
                    <div class="w-header-a">
                        <h3 class="w-title-a text-brand">Sami Estate</h3>
                    </div>
                    <div class="w-body-a">
                        <p class="w-text-a color-text-a">
                            Sami Estate - Đơn Vị Cho Thuê Văn Phòng Hàng Đầu Tại Việt Nam
                        </p>
                    </div>
                    <div class="w-footer-a">
                        <ul class="list-unstyled">
                            <li class="color-a">
                                <span class="color-text-a">Phone .</span> samiestate@gmail.com</li>
                            <li class="color-a">
                                <span class="color-text-a">Email .</span> +54 356 945234</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-4 section-md-t3">
                <div class="widget-a">
                    <div class="w-header-a">
                        <h3 class="w-title-a text-brand">Văn phòng cho thuê</h3>
                    </div>
                    <div class="w-body-a">
                        <div class="w-body-a">
                            <ul class="list-unstyled">
                                <c:forEach var="item" items="${districtMap}">
                                    <li class="item-list-a">
                                        <i class="fa fa-angle-right"></i> <a href="#">Văn phòng hạng A</a>
                                    </li>
                                </c:forEach>
                                <li class="item-list-a">
                                    <i class="fa fa-angle-right"></i> <a href="#">Văn phòng hạng A</a>
                                </li>
                                <li class="item-list-a">
                                    <i class="fa fa-angle-right"></i> <a href="#">Văn phòng hạng B</a>
                                </li>
                                <li class="item-list-a">
                                    <i class="fa fa-angle-right"></i> <a href="#">Văn phòng hạng C</a>
                                </li>
                                <li class="item-list-a">
                                    <i class="fa fa-angle-right"></i> <a href="#">Văn phòng giá rẻ</a>
                                </li>
                                <li class="item-list-a">
                                    <i class="fa fa-angle-right"></i> <a href="#">Văn phòng trọn gói</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-4 section-md-t3">
                <div class="widget-a">
                    <div class="w-header-a">
                        <h3 class="w-title-a text-brand">Theo Quận</h3>
                    </div>
                    <div class="w-body-a">
                        <ul class="list-unstyled">
                            <c:forEach var="item" items="${districtsMap}">
                                <li class="item-list-a">
                                    <i class="fa fa-angle-right"></i> <a href="#">${item.value}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <nav class="nav-footer">
                    <ul class="list-inline">
                        <li class="list-inline-item">
                            <a href="#">Trang chủ</a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#">Về chúng tôi</a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#">Tòa nhà văn phòng</a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#">Liên hệ</a>
                        </li>
                    </ul>
                </nav>
                <div class="socials-a">
                    <ul class="list-inline">
                        <li class="list-inline-item">
                            <a href="#">
                                <i class="fa fa-facebook" aria-hidden="true"></i>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#">
                                <i class="fa fa-twitter" aria-hidden="true"></i>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#">
                                <i class="fa fa-instagram" aria-hidden="true"></i>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#">
                                <i class="fa fa-pinterest-p" aria-hidden="true"></i>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#">
                                <i class="fa fa-dribbble" aria-hidden="true"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</footer>
