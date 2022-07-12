<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/news"/>
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
                    <h1 class="title-single">${model.title}</h1>
                </div>
            </div>
            <div class="col-md-12 col-lg-4">
                <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="<c:url value='/trang-chu'/>">Trang chủ</a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">
                            ${model.categoryName}
                        </li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</section>
<!--/ Intro Single End /-->

<!--/ News Single Star /-->
<section class="news-single nav-arrow-b">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="news-img-box d-flex justify-content-center">
                    <img src="${model.thumbnail}" alt="" class="img-fluid">
                </div>
            </div>
            <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-2">
                <div class="post-information">
                    <ul class="list-inline text-center color-a">
                        <li class="list-inline-item mr-2">
                            <strong>Tác giả: </strong>
                            <span class="color-text-a">${model.author}</span>
                        </li>
                        <li class="list-inline-item mr-2">
                            <strong>Thể loại: </strong>
                            <span class="color-text-a">${model.categoryName}</span>
                        </li>
                        <li class="list-inline-item">
                            <strong>Ngày viết: </strong>
                            <span class="color-text-a">${model.createdDateString}</span>
                        </li>
                    </ul>
                </div>
                <div>
                    ${model.description}
                </div>
                <br />
                <div>
                    ${model.content}
                </div>
                <div class="post-footer">
                    <div class="post-share">
                        <span>Chia sẻ: </span>
                        <ul class="list-inline socials">
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
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--/ News Single End /-->
<script>
    document.querySelectorAll('oembed[url]').forEach(element => {
        // Create the <a href="..." class="embedly-card"></a> element that Embedly uses
        // to discover the media.
        const anchor = document.createElement('a');

        anchor.setAttribute('href', element.getAttribute('url'));
        anchor.className = 'embedly-card';

        element.appendChild(anchor);
    });

    // update active navbar
    let header = document.getElementById("navbarDefault");
    let current = header.getElementsByClassName("active");
    current[0].className = current[0].className.replace(" active", "");
</script>
</body>