<%@page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="./common/include.jsp"%>
        <script type="text/javascript">
            $(function () {
                $("#name").focus();
                $("#startTime").datetimepicker({
                    format: 'd-MMM-YYYY h:mm a',
                    date:new Date(),
                    sideBySide: true
                });

                $("#alert").click(function () {
                    $.bsAlert.alert('Alert Message');
                });

                $("#confirm").click(function () {
                    $.bsAlert.confirm("Are You Sure?", function () {
                        $.bsAlert.alert('Confirmed!');
                    });
                });
            });
        </script>
    </head>
    <body>
        <label id="alert">Alert</label>
        <label id="confirm">Confirm</label>
        <div class="container-fluid" style="width: 70%; margin-top: 5px;">
            <form:form modelAttribute="event" method="post" action="event.htm">
                <form:hidden path="id"/>
                <div class="form-group row">
                    <label for="name" class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-10">
                        <form:input path="name" cssClass="form-control" placeholder="Event name"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="orgniserName" class="col-sm-2 col-form-label">Organiser Name</label>
                    <div class="col-sm-10">
                        <form:input path="orgniserName" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="location" class="col-sm-2 col-form-label">Location</label>
                    <div class="col-sm-10">
                        <form:input path="location" cssClass="form-control" placeholder="Event's place"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="startTime" class="col-sm-2 col-form-label">Start Time</label>
                    <div class="col-sm-10">
                        <form:input path="startTime" cssClass="form-control" maxlength="13"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="control-label col-sm-2" for="remark">Remark</label>
                    <div class="col-sm-10">
                        <form:textarea cssClass="form-control" path="remark" placeholder="Remark" maxlength="200"/>
                    </div>
                </div>
                <div class="form-group row text-center">
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-primary">Save</button>
                        <button type="Reset" class="btn btn-primary" onclick="window.location.href = ''">Cancel</button>
                    </div>
                </div>
            </form:form>
        </div>
    </body>
</html>