<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/15/2025
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>

</head>
<body>
<!--
This example requires updating your template:

```
<html class="h-full bg-white">
<body class="h-full">
```
-->
<div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-6">
    <div class="sm:mx-auto sm:w-full sm:max-w-sm">
        <h2 class="mt-10 text-center text-2xl/9 font-bold tracking-tight text-gray-900">Join Us</h2>
    </div>

    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <form class="space-y-6" action="/auth/register" method="POST" enctype="multipart/form-data">
            <div class="flex gap-2 items-center">
                <div>
                    <label for="fname" class="block text-sm/6 font-medium text-gray-900">First Name</label>
                    <div class="mt-2">
                        <input type="text" name="fname" id="fname"  required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                    </div>
                </div>

                <div>
                    <label for="lname" class="block text-sm/6 font-medium text-gray-900">Last Name</label>
                    <div class="mt-2">
                        <input type="text" name="lname" id="lname"  required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                    </div>
                </div>
            </div>
            <div>
                <label for="bdate" class="block text-sm/6 font-medium text-gray-900">Birth Date</label>
                <div class="mt-2">
                    <input type="date" name="bdate" id="bdate" required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                </div>
            </div>

            <div>
                <label for="email" class="block text-sm/6 font-medium text-gray-900">Email address</label>
                <div class="mt-2">
                    <input type="email" name="email" id="email" required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                </div>
            </div>

            <div>
                <label for="password" class="block text-sm/6 font-medium text-gray-900">Password</label>
                <div class="mt-2">
                    <input type="password" name="password" id="password" required class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6">
                </div>
            </div>
            <div>
                <button type="submit" class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm/6 font-semibold text-white shadow-xs hover:bg-indigo-500 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Sign in</button>
            </div>
        </form>

        <p class="mt-10 text-center text-sm/6 text-gray-500">
            already have an account?
            <a href="/login-form" class="font-semibold text-indigo-600 hover:text-indigo-500">Try to login</a>
        </p>
    </div>
</div>

</body>
</html>

</body>
</html>
