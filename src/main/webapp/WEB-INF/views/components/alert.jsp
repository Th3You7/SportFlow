<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/16/2025
  Time: 2:11 AM
  To change this template use File | Settings | File Templates.
--%>
<c:if test="${not empty sessionScope.message}" >
    <c:choose>
        <c:when test="${sessionScope.type == 'success'}">
            <div class="bg-green-200 px-6 py-4 mx-2 my-4 rounded-md text-lg flex items-center mx-auto w-full max-w-lg">
                <svg viewBox="0 0 24 24" class="text-green-600 w-5 h-5 sm:w-5 sm:h-5 mr-3">
                    <path fill="currentColor"
                          d="M12,0A12,12,0,1,0,24,12,12.014,12.014,0,0,0,12,0Zm6.927,8.2-6.845,9.289a1.011,1.011,0,0,1-1.43.188L5.764,13.769a1,1,0,1,1,1.25-1.562l4.076,3.261,6.227-8.451A1,1,0,1,1,18.927,8.2Z">
                    </path>
                </svg>
                <span class="text-green-800"><c:out value="${sessionScope.message}" /></span>
            </div>
        </c:when>
        <c:when test="${sessionScope.type == 'error'}">
            <div class="bg-red-200 px-6 py-4 mx-2 my-4 rounded-md text-lg flex items-center w-full mx-auto max-w-lg">
                <svg viewBox="0 0 24 24" class="text-red-600 w-5 h-5 sm:w-5 sm:h-5 mr-3">
                    <path fill="currentColor"
                          d="M11.983,0a12.206,12.206,0,0,0-8.51,3.653A11.8,11.8,0,0,0,0,12.207,11.779,11.779,0,0,0,11.8,24h.214A12.111,12.111,0,0,0,24,11.791h0A11.766,11.766,0,0,0,11.983,0ZM10.5,16.542a1.476,1.476,0,0,1,1.449-1.53h.027a1.527,1.527,0,0,1,1.523,1.47,1.475,1.475,0,0,1-1.449,1.53h-.027A1.529,1.529,0,0,1,10.5,16.542ZM11,12.5v-6a1,1,0,0,1,2,0v6a1,1,0,1,1-2,0Z">
                    </path>
                </svg>
                <span class="text-red-800"><c:out value="${sessionScope.message}" /></span>
            </div>
        </c:when>
    </c:choose>
    <c:remove var="message" scope="session"/>
    <c:remove var="type" scope="session"/>
</c:if>
