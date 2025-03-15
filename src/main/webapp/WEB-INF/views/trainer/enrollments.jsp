<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/15/2025
  Time: 11:24 PM
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
<div class="bg-white rounded-lg shadow mb-8">
    <div class="px-6 py-4 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">Enrollments List</h2>
    </div>
    <div class="overflow-x-auto">
        <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
            <tr>
                <th class="px-6 py-3" />
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Member</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Trainer</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Domain</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Date</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>


            </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">

            <c:forEach var="enrollment" items="${requestScope.enrollments}" >
                <tr  class="hover:bg-gray-50 transition-colors">
                    <!-- Offer Id -->
                    <td class="px-6 py-4 whitespace-nowrap"><c:out value="${enrollment.id}" /></td>
                    <!-- Member -->
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm text-gray-500"><c:out value="${enrollment.member.firstName} ${enrollment.member.lastName}" /></div>
                    </td>
                    <!-- Trainer -->
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm text-gray-500"><c:out value="${enrollment.session.trainer.firstName} ${enrollment.session.trainer.lastName}" /></div>
                    </td>
                    <!-- Domain -->
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm text-gray-500"><c:out value="${enrollment.domain}" /></div>
                    </td>
                    <!-- Status -->
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm text-gray-500"><c:out value="${enrollment.status}" /></div>
                    </td>
                    <!--Created by-->
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm text-gray-500"><c:out value="${enrollment.createdBy.firstName} ${enrollment.createdBy.lastName}" /></div>
                    </td>
                    <!-- Created at-->
                    <td class="px-6 py-4 whitespace-nowrap">
                      <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                        <c:out value="${enrollment.enrollmentDate}" />
                      </span>
                    </td>
                    <!-- Actions-->
                    <td class="px-6 py-4 whitespace-nowrap text-left text-sm font-medium">
                        <a href="/enrollment/accept?id=<c:out value="${enrollment.userId}" />">Delete</a>
                        <a class="ml-3" href=/enrollment/cancel?id=<c:out value="${enrollment.userId}" />">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
