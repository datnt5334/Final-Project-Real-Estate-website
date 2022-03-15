<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formSearchUrl" value="/properties-search"/>
<div class="box-collapse">
    <div class="title-box-d">
        <h3 class="title-d">Tìm kiếm văn phòng</h3>
    </div>
    <span class="close-box-collapse right-boxed ion-ios-close"></span>
    <div class="box-collapse-wrap form">
        <form:form modelAttribute="modelSearch" cssClass="form-a" action="${formSearchUrl}" id="buildingSearchForm" method="GET">
            <div class="row">
                <div class="col-md-12 mb-2">
                    <div class="form-group">
                        <label for="name">Tên tòa nhà</label>
                        <form:input path="name" id="name" cssClass="form-control form-control-lg form-control-a"/>
                    </div>
                </div>
                <div class="col-md-6 mb-2">
                    <div class="form-group">
                        <label for="areaRentFrom">Diện tích từ (m<sup>2</sup>)</label>
                        <form:input path="areaRentFrom" id="areaRentFrom" cssClass="form-control form-control-lg form-control-a"/>
                    </div>
                </div>
                <div class="col-md-6 mb-2">
                    <div class="form-group">
                        <label for="areaRentTo">Diện tích đến (m<sup>2</sup>)</label>
                        <form:input path="areaRentTo" id="areaRentTo" cssClass="form-control form-control-lg form-control-a"/>
                    </div>
                </div>
                <div class="col-md-6 mb-2">
                    <div class="form-group">
                        <label for="costRentFrom">Giá thuê từ ($/m<sup>2</sup>)</label>
                        <form:input path="costRentFrom" id="costRentFrom" cssClass="form-control form-control-lg form-control-a"/>
                    </div>
                </div>
                <div class="col-md-6 mb-2">
                    <div class="form-group">
                        <label for="costRentTo">Giá thuê đến ($/m<sup>2</sup>)</label>
                        <form:input path="costRentTo" id="costRentTo" cssClass="form-control form-control-lg form-control-a"/>
                    </div>
                </div>
                <div class="col-md-6 mb-2">
                    <div class="form-group">
                        <label for="level">Hạng</label>
                        <form:input path="level" id="level" cssClass="form-control form-control-lg form-control-a"/>
                    </div>
                </div>
                <div class="col-md-6 mb-2">
                    <div class="form-group">
                        <label for="direction">Hướng</label>
                        <form:input path="direction" id="direction" cssClass="form-control form-control-lg form-control-a"/>
                    </div>
                </div>
                <div class="col-md-6 mb-2">
                    <div class="form-group">
                        <label for="districtCode">Quận</label>
                        <form:select path="districtCode" id="districtCode" cssClass="form-control form-control-lg form-control-a">
                            <form:option value="" label="--- Chọn quận ---"/>
                            <form:options items="${districtsMap}"/>
                        </form:select>
                    </div>
                </div>
                <div class="col-md-12">
                    <button id="btnSearch" type="button" class="btn btn-b">Tìm kiếm kết quả</button>
                </div>
            </div>
            <form:hidden path="page" id="pageSearch"/>
        </form:form>
    </div>
</div>

<script type="text/javascript">
    //submit search
    $(document).ready(function () {
        $('#btnSearch').click(function () {
            $('#buildingSearchForm').submit();
        });
    });
</script>
