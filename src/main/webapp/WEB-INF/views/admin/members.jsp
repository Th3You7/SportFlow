<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/15/2025
  Time: 10:42 PM
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
        <h1 class="text-2xl font-bold text-gray-900 mb-6">Members List</h1>
        <button class="bg-blue-500 hover:bg-blue-700 text-white py-2 px-4 rounded">
            <a href="add-form.jsp?type=MEMBER" >
                Add Member
            </a>
        </button>
    </div>
    <%@ include file="../components/user_list.jsp"%>
</div>
</body>
</html>
