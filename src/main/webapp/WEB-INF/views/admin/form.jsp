<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/15/2025
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <script async defer src="https://unpkg.com/@tailwindcss/browser@4"></script>
</head>
<body class="bg-gray-50">
    <%@ include file="../components/aside.jsp" %>
    <div class="ml-[300px] mr-[16px] my-[16px]">
        <div class="flex items-center justify-between">
            <h1 class="text-2xl font-bold text-gray-900 mb-6">Add User</h1>
        </div>

<%@ include file="../components/alert.jsp" %>



    <div class="mt-10 w-[600px]">
        <c:if test="${requestScope.user == null}">
        <form class="space-y-6 w-full" action="add-user" method="POST">
            </c:if>
            <c:if test="${requestScope.user != null}">
            <form class="space-y-6 w-full" action="edit-user" method="POST">
                </c:if>
                <div class="flex gap-2 items-center">
                    <div class="grow">
                        <label for="fname" class="block text-sm/6 font-medium text-gray-900">First Name</label>
                        <div class="mt-2">
                            <input value="<c:out value="${requestScope.user.firstName}" />" type="text" name="fname" id="fname"  required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                        </div>
                    </div>
                    <div class="grow">
                        <label for="lname" class="block text-sm/6 font-medium text-gray-900">Last Name</label>
                        <div class="mt-2">
                            <input value="<c:out value="${requestScope.user.lastName}" />" type="text" name="lname" id="lname"  required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                        </div>
                    </div>
                </div>
                <div>
                    <label for="bdate" class="block text-sm/6 font-medium text-gray-900">Birth Date</label>
                    <div class="mt-2">
                        <input value="<c:out value="${requestScope.user.birthDate}" />" type="date" name="bdate" id="bdate" required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                    </div>
                </div>
<%--                <c:if test="${requestScope.user.role == 'TRAINER'}">--%>
<%--                    <div>--%>
<%--                        <label for="domain" class="block text-sm/6 font-medium text-gray-900">Domain Name</label>--%>
<%--                        <div class="mt-2">--%>
<%--                            <input value="<c:out value="${requestScope.user.domain}"  />" type="text" name="domain" id="domain" required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </c:if>--%>
                <div>
                    <label for="email" class="block text-sm/6 font-medium text-gray-900">Email address</label>
                    <div class="mt-2">
                        <input value="<c:out value="${requestScope.user.email}" />" type="email" name="email" id="email" required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                    </div>
                </div>
                <c:if test="${requestScope.user != null}">
                    <input hidden="hidden" name="userId" value="<c:out value="${requestScope.user.userId}" />" />
                </c:if>
                <c:if test="${requestScope.user == null}">
                    <input hidden="hidden" name="type" value="<c:out value="${requestScope.type}" />" />

                </c:if>
                <div>
                    <button type="submit" class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm/6 font-semibold text-white shadow-xs hover:bg-indigo-500 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
                        <c:if test="${requestScope.user == null}">
                            Add User
                        </c:if>
                        <c:if test="${requestScope.user != null}">
                            Edit User
                        </c:if>
                    </button>
                </div>
            </form>
    </div>
    </div>
</body>
</html>
