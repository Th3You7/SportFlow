<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/17/2025
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trainer</title>
    <script async defer src="https://unpkg.com/@tailwindcss/browser@4"></script>
</head>
<body class="bg-gray-50">
<%@ include file="../components/aside.jsp" %>
<div class="ml-[300px] mr-[16px] my-[16px]">
    <div class="flex items-center justify-between">
        <h1 class="text-2xl font-bold text-gray-900 mb-6">Add Training Session</h1>
    </div>

    <%@ include file="../components/alert.jsp" %>



    <div class="mt-10 w-[600px]">
        <c:if test="${requestScope.session == null}">
        <form class="space-y-6 w-full" action="add-session" method="POST">
            </c:if>
            <c:if test="${requestScope.session != null}">
            <form class="space-y-6 w-full" action="edit-session" method="POST">
                </c:if>
                <div class="flex gap-2 items-center">
                    <c:if test="${requestScope.session != null}">
                        <input name="sessionId" hidden="hidden" required value="${requestScope.session.sessionId}"  />
                    </c:if>
                    <div class="grow">
                        <label for="name" class="block text-sm/6 font-medium text-gray-900">Session Name</label>
                        <div class="mt-2">
                            <input value="<c:out value="${requestScope.session.sessionName}" />" type="text" name="name" id="name"  required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                        </div>
                    </div>
                    <div class="grow">
                        <label for="description" class="block text-sm/6 font-medium text-gray-900">Session Description</label>
                        <div class="mt-2">
                            <input value="<c:out value="${requestScope.session.sessionDescription}" />" type="text" name="description" id="description"  required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                        </div>
                    </div>
                </div>
                <div>
                    <label for="domain" class="block text-sm/6 font-medium text-gray-900">Domain</label>
                    <div class="mt-2">
                        <select id="domain" name="domain" required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                            <c:forEach var="domain" items="${requestScope.domains}">
                                <option value="<c:out value="${domain}" />" >
                                    <c:out value="${domain}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>


                <div>
                    <button type="submit" class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm/6 font-semibold text-white shadow-xs hover:bg-indigo-500 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
                        <c:if test="${requestScope.session == null}">
                            Add Session
                        </c:if>
                        <c:if test="${requestScope.session != null}">
                            Edit Session
                        </c:if>
                    </button>
                </div>
            </form>
    </div>
</div>
</body>
</html>
