<%@ page import="java.sql.Statement" %>
<%@ page import="utils.DBUtil" %>
<%@ page import="java.sql.ResultSet" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login Page</title>
  <style>
    body {
      background-color: #f2f2f2;
      font-family: Arial, sans-serif;
    }
    .container {
      margin: 0 auto;
      max-width: 400px;
      padding: 20px;
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0,0,0,0.3);
    }
    h2 {
      text-align: center;
      margin-bottom: 20px;
    }
    form {
      display: flex;
      flex-direction: column;
    }
    label {
      margin-bottom: 10px;
      font-weight: bold;
    }
    input[type="text"], input[type="password"] {
      padding: 10px;
      margin-bottom: 20px;
      border-radius: 5px;
      border: none;
      box-shadow: 0 0 5px rgba(0,0,0,0.1);
    }
    input[type="submit"] {
      background-color: #4CAF50;
      color: #fff;
      padding: 10px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    input[type="submit"]:hover {
      background-color: #3e8e41;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>用户登录</h2>
  <form action="index.jsp" method="post" >
    <label for="username">Username</label>
    <input type="text" id="username" name="username" required>
    <label for="password">Password</label>
    <input type="password" id="password" name="password" required>
    <input type="submit" value="login" onclick="userLogin()">
  </form>
</div>
</body>
<script>
  function userLogin() {
    <%
    String username= request.getParameter("username");
    String password= request.getParameter("password");
    //获取数据库
    Statement statement=DBUtil.getStatement();
    String sql="select * from user";
    ResultSet rs=statement.executeQuery(sql);
    if (rs!=null){
      //跳转登录
      System.out.println("登录成功");
    }
    %>
  }
</script>

</html>
