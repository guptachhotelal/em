<%@page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="./common/include.jsp"%>
        <script type="text/javascript">
            $(function () {
                $("#place").focus();
                $("#arrivalDate").datetimepicker({
                    date: new Date(),
                    sideBySide: true
                });
                $("#departureDate").datetimepicker({
                    date: new Date(),
                    sideBySide: true
                });
            });
        </script>
    </head>
    <body>
        <div class="container-fluid" style="width: 70%; margin-top: 5px;">
            <form:form modelAttribute="scheduler" action="scheduler.htm" method="post">
                <form:hidden path="id"/>
                <div class="form-group row">
                    <label class="control-label col-sm-2" for="doctor.id">Doctor</label>
                    <div class="col-sm-10">
                        <form:select path="doctor.id" cssClass="form-control">
                            <form:option value="-1">Select</form:option>
                            <c:forEach items="${doctors}" var="doctor">
                                <form:option value="${doctor.id}"><c:out value="${doctor.name}"/></form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>             

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Event</label>
                    <div class="col">
                        <form:select path="event.id" cssClass="form-control">
                            <form:option value="-1">Select</form:option>
                            <c:forEach items="${events}" var="event">
                                <form:option value="${event.id}"><c:out value="${event.name}"/></form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="col">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="place" class="col-sm-2 col-form-label">Place</label>
                    <div class="col-sm-10">
                        <form:input path="place" cssClass="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="arrivalDate" class="col-sm-2 col-form-label">Arrival Date</label>
                    <div class="col-sm-10">
                        <form:input path="arrivalDate" cssClass="form-control"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="control-label col-sm-2" for="arrivalMode">Arrival Mode</label>
                    <div class="col-sm-10">
                        <form:select path="arrivalMode" cssClass="form-control">
                            <form:option value="NA">Select</form:option>
                            <c:forEach items="${modes}" var="mode">
                                <form:option value="${mode.key}"><c:out value="${mode.value}"/></form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="arrivalRefNo" class="col-sm-2 col-form-label">Arrival Reference Number</label>
                    <div class="col-sm-10">
                        <form:input path="arrivalRefNo" cssClass="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="hotelName" class="col-sm-2 col-form-label">Hotel</label>
                    <div class="col">
                        <form:input class="form-control" path="hotelName" placeholder="Name"/>
                    </div>
                    <div class="col">
                        <form:input class="form-control" path="hotelRoomNumber" placeholder="Room Number"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="departureDate" class="col-sm-2 col-form-label">Departure Date</label>
                    <div class="col-sm-10">
                        <form:input path="departureDate" cssClass="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="control-label col-sm-2" for="departureMode">Departure Mode</label>
                    <div class="col-sm-10">
                        <form:select path="departureMode" cssClass="form-control">
                            <form:option value="NA">Select</form:option>
                            <c:forEach items="${modes}" var="mode">
                                <form:option value="${mode.key}"><c:out value="${mode.value}"/></form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="departureRefNo" class="col-sm-2 col-form-label">Departure Reference Number</label>
                    <div class="col-sm-10">
                        <form:input path="departureRefNo" cssClass="form-control"/>
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
