<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div style="position: fixed; bottom: 0px; width: 100%; text-align: center; font-size: 10px;"><jsp:useBean id="now" class="java.util.Date" />&copy; <fmt:formatDate pattern="yyyy" value="${now}" /> IT World. All rights reserved.</div>