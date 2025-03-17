<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/15/2025
  Time: 10:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../components/alert.jsp" %>
<div class="bg-white rounded-lg shadow mb-8">
    <div class="px-6 py-4 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">Members</h2>
    </div>
    <div class="overflow-x-auto">
        <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
            <tr>
                <th class="px-6 py-3" />
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">User</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Birth Date</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Role</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Created At</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Last Update</th>
                <th class="relative px-6 py-3">
                    <span class="sr-only">Actions</span>
                </th>
            </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">

            <c:forEach var="user" items="${requestScope.users}" >
                <tr  class="hover:bg-gray-50 transition-colors">
                    <!-- User Id -->
                    <td class="px-6 py-4 whitespace-nowrap"><c:out value="${user.userId}" /></td>
                    <!-- User -->
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="flex items-center">
                            <div class="h-10 w-10 flex-shrink-0">
                                <img class="h-10 w-10 rounded-full object-cover"  src="https://placehold.co/600x400" alt="" />
                            </div>
                            <div class="ml-4">
                                <div class="text-sm font-medium text-gray-900"><c:out value="${user.lastName} ${user.firstName}" /></div>
                                <div class="text-sm text-gray-500"><c:out value="${user.email}" /></div>
                            </div>
                        </div>
                    </td>
                    <!-- User Birth Date-->
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="text-sm text-gray-500"><c:out value="${user.birthDate}" /></div>
                    </td>
                    <!-- User Role-->
                    <td class="px-6 py-4 whitespace-nowrap">
                        <div class="flex items-center">
                            <svg class="h-4 w-4 text-purple-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width={2} d="M20.618 5.984A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016zM12 9v3m0 0v3m0-3h3m-3 0H9" />
                            </svg>
                            <span class="text-sm 'text-purple-600"><c:out value="${user.role}" /></span>
                        </div>
                    </td>
                    <!-- User Created at-->
                    <td class="px-6 py-4 whitespace-nowrap">
                      <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                        <c:out value="${user.createdAt}" />
                      </span>
                    </td>
                    <!-- User Updated at-->
                    <td class="px-6 py-4 whitespace-nowrap">
                      <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                        <c:out value="${user.updatedAt}" />
                      </span>
                    </td>
                    <!-- Actions-->
                    <td class="px-6 py-4 whitespace-nowrap text-left text-sm font-medium">
                        <a href="delete?id=<c:out value="${user.userId}" />&redirect=<c:out value="${type}"/>s">Delete</a>
                        <a class="ml-3" href="edit-form.jsp?id=<c:out value="${user.userId}" />">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

