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
            <form:form modelAttribute="doctor" method="post" action="doctor.htm">
                <form:hidden path="id"/>
                <div class="form-group row">
                    <label for="name" class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-10">
                        <form:input path="name" cssClass="form-control" placeholder="Doctor's name"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="code" class="col-sm-2 col-form-label">Code</label>
                    <div class="col-sm-10">
                        <form:input path="code" cssClass="form-control" placeholder="Doctor's code"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="control-label col-sm-2" for="speciality">Speciality</label>
                    <div class="col-sm-10">
                        <form:select path="speciality" cssClass="form-control">
                            <form:option value="NA">Select</form:option>
                            <c:forEach items="${specialities}" var="speciality">
                                <form:option value="${speciality.key}"><c:out value="${speciality.value}"/></form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="category" class="col-sm-2 col-form-label">Category</label>
                    <div class="col-sm-10">
                        <div class="form-check-inline">
                            <form:radiobutton cssClass="form-check-inline"  path="category" value="A"/>A
                        </div>
                        <div class="form-check-inline">
                            <form:radiobutton cssClass="form-check-inline"  path="category" value="B"/>B
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="place" class="col-sm-2 col-form-label">Place</label>
                    <div class="col-sm-10">
                        <form:input path="place" cssClass="form-control" placeholder="Doctor's place"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone1" class="col-sm-2 col-form-label">Phone</label>
                    <div class="col-sm-10">
                        <form:input path="phone1" cssClass="form-control" maxlength="13"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone2" class="col-sm-2 col-form-label"></label>
                    <div class="col-sm-10">
                        <form:input path="phone1" cssClass="form-control" maxlength="13"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="control-label col-sm-2" for="user">Sales Manager</label>
                    <div class="col-sm-10">
                        <form:select path="user.code" cssClass="form-control">
                            <form:option value="-1">Select</form:option>
                            <c:forEach items="${users}" var="user">
                                <form:option value="${user.id}"><c:out value="${user.name}"/></form:option>
                            </c:forEach>
                        </form:select>
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