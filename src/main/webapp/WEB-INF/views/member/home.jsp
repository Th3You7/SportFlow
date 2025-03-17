<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/15/2025
  Time: 11:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <script async defer src="https://unpkg.com/@tailwindcss/browser@4"></script>

</head>
<body class="bg-gray-50">

<%@ include file="../components/aside.jsp" %>
<div class="ml-[300px] mr-[16px] my-[16px]">

    <h1 class="text-2xl font-bold text-gray-900 mb-6">Home</h1>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <!-- Cards-->
        <c:forEach var="session" items="${requestScope.sessions}">
            <div class="max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow-sm dark:bg-gray-800 dark:border-gray-700">
                <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white"><c:out value="${session.sessionName}" /></h5>
                <p class="mb-3 font-normal text-gray-700 dark:text-gray-400"><c:out value="${session.sessionDescription}" /></p>
                <span class="block text-gray-500"><c:out value="${session.sessionDomain}" /></span>
                <p class="mt-3 mb-3 white">Trainer: <c:out value="${session.trainer.firstName} ${session.trainer.lastName}" /></p>
                <p class="mt-3 mb-3 white">Created on: <c:out value="${session.createdAt}" /></p>
                <a href="enroll?sessionId=<c:out value="${session.sessionId}" />" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                    Apply
                    <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
                    </svg>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

