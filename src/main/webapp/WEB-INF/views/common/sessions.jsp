<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/15/2025
  Time: 10:57 PM
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
            <h1 class="text-2xl font-bold text-gray-900 mb-6">Training Sessions List</h1>
            <c:if test="${sessionScope.user.role == 'TRAINER'}" >
                <button class="bg-blue-500 hover:bg-blue-700 text-white py-2 px-4 rounded">
                    <a href="add-form.jsp" >
                        Add Session
                    </a>
                </button>
            </c:if>
        </div>
        <%@ include file="../components/alert.jsp" %>
        <div class="bg-white rounded-lg shadow mb-8">
    <div class="px-6 py-4 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">Training Sessions List</h2>
    </div>
    <div class="overflow-x-auto">
        <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
            <tr>
                <th class="px-6 py-3" />
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Title</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Description</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Category</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Created By</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Created At</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Last Update</th>

            </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">

            <c:forEach var="session" items="${requestScope.sessions}" >
                <tr  class="hover:bg-gray-50 transition-colors">
                    <!-- Offer Id -->
                    <td class="px-6 py-4 whitespace-nowrap"><c:out value="${session.sessionId}" /></td>
                    <!-- Title -->
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm text-gray-500"><c:out value="${session.sessionName}" /></div>
                    </td>
                    <!-- Desc -->
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm text-gray-500"><c:out value="${session.sessionDescription}" /></div>
                    </td>
                    <!-- Category -->
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm text-gray-500"><c:out value="${session.sessionDomain}" /></div>
                    </td>
                    <!--Created by-->
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm text-gray-500"><c:out value="${session.trainer.firstName} ${session.trainer.lastName}" /></div>
                    </td>

                    <!-- offer Created at-->
                    <td class="px-6 py-4 whitespace-nowrap">
                      <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                        <c:out value="${session.createdAt}" />
                      </span>
                    </td>
                    <!-- offer Updated at-->
                    <td class="px-6 py-4 whitespace-nowrap">
                      <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                        <c:out value="${session.updatedAt}" />
                      </span>
                    </td>
                    <!-- acttions -->
                    <td>
                        <a href="delete-session?id=<c:out value="${session.sessionId}" />">Delete</a>
                        <a class="ml-3" href="edit-form.jsp?sessionId=<c:out value="${session.sessionId}" />">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
    </div>
</body>
</html>
