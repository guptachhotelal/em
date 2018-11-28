<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="./common/include.jsp"%>
        <script type="text/javascript" src="resources/js/scheduler.js"></script>
    </head>
    <body>
        <%@include file="./common/header.jsp"%>
        <div class="container-fluid" style="width: 80%; margin-top: 10px;">
            <table id="tblList" class="table table-striped table-bordered dt-responsive nowrap">
                <thead>
                    <tr>
                        <th class="noWapColumn" style="text-align: center; width: 5%;">Sr. No</th>
                        <th class="noWapColumn" style="text-align: center; width: 30%;">Doctor Name</th>
                        <th class="noWapColumn" style="text-align: center; width: 20%;">Doctor's Mobile</th>
                        <th class="noWapColumn" style="text-align: center; width: 15%;">Arrival Reference No</th>
                        <th class="noWapColumn" style="text-align: center; width: 15%;">Arrival Time</th>
                        <th class="noWapColumn" style="text-align: center; width: 15%;">Hotel Name</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>
    <%@include file="./common/footer.jsp"%>
</body>
</html>