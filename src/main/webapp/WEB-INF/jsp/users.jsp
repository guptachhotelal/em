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
            });
        </script>
    </head>
    <body>
        <%@include file="./common/header.jsp"%>
        <div class="container-fluid" style="width: 70%; margin-top: 5px;">
            <form:form modelAttribute="user">
                <form:hidden path="id"/>
                <div class="form-group row">
                    <label for="name" class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-10">
                        <form:input path="name" cssClass="form-control" placeholder="Executive's name"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="code" class="col-sm-2 col-form-label">Code</label>
                    <div class="col-sm-10">
                        <form:input path="code" cssClass="form-control" placeholder="Executive's code"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="region" class="col-sm-2 col-form-label">Region</label>
                    <div class="col-sm-10">
                        <form:input path="region" cssClass="form-control" placeholder="Executive's region"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="control-label col-sm-2" for="division">Division</label>
                    <div class="col-sm-10">
                        <form:select path="division" cssClass="form-control">
                            <form:option value="NA">Select</form:option>
                            <c:forEach items="${divisions}" var="division">
                                <form:option value="${division.key}"><c:out value="${division.value}"/></form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="place" class="col-sm-2 col-form-label">Phone</label>
                    <div class="col">
                        <form:input class="form-control" path="phone1" placeholder="Phone 1" maxlength="13"/>
                    </div>
                    <div class="col">
                        <form:input class="form-control" path="phone1" placeholder="Phone 2" maxlength="13"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="control-label col-sm-2" for="remark">Remark</label>
                    <div class="col-sm-10">
                        <form:textarea cssClass="form-control" path="remark" placeholder="Remark" maxlength="200"/>
                    </div>
                </div>
                <div class="form-group row text-center">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Save</button>
                        <button type="Reset" class="btn btn-primary" onclick="window.location.href = ''">Cancel</button>
                    </div>
                </div>
            </form:form>
        </div>
    </body>
</html>