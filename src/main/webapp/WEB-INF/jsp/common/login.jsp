<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="./include.jsp"%>
    </head>
    <body>
        <div class="container" style="height: 80%; display: table;">
            <div style="width: 40%; display: table-cell; vertical-align: middle;">
                <form action="login.htm" method="post" class="form-signin" name="login" id="login">
                    <fieldset style="border-radius: 15px;">
                        <legend style="font-weight: bold; margin-bottom: 40px;">Login</legend>
                        <div class="form-group row">
                            <label for="userName" class="col-sm-2 col-form-label">Email</label>
                            <div class="col-sm-6">
                                <input type="text" name="userName" class="form-control" placeholder="User Name" value="test"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="passWord" class="col-sm-2 col-form-label">Password</label>
                            <div class="col-sm-6">
                                <input type="password" name="passWord" class="form-control" placeholder="Password" value="test2"/>
                            </div>
                        </div>
                        <label><a href="">Forgot Password</a></label>
                        <div class="form-group row">
                            <div class="col-sm-8">
                                <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
                            </div>
                        </div>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </fieldset>
                </form>
            </div>
        </div>
        <%@include file="./footer.jsp"%>
    </body>
</html>
