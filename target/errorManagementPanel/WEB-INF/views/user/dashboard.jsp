<%--
  Created by IntelliJ IDEA.
  User: 34bur
  Date: 23.10.2025
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Kullanıcı Dashboard</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f4f4f4; }
        .navbar { background: #16a085; color: white; padding: 15px 30px; display: flex;
            justify-content: space-between; align-items: center; }
        .navbar h1 { font-size: 24px; }
        .navbar a { color: white; text-decoration: none; margin-left: 20px; }
        .container { max-width: 1200px; margin: 30px auto; padding: 20px; }
        .dashboard-cards { display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px; margin-top: 30px; }
        .card { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            text-align: center; }
        .card h3 { color: #2c3e50; margin-bottom: 15px; }
        .card a { display: inline-block; margin-top: 15px; padding: 10px 20px; background: #16a085;
            color: white; text-decoration: none; border-radius: 5px; }
        .card a:hover { background: #138d75; }
    </style>
</head>
<body>
<div class="navbar">
    <h1>Kullanıcı Paneli</h1>
    <div>
        <span>Hoş geldiniz, ${sessionScope.loggedInUser.firstName}!</span>
        <a href="${pageContext.request.contextPath}/logout">Çıkış Yap</a>
    </div>
</div>

<div class="container">
    <h2>Dashboard</h2>

    <div class="dashboard-cards">
        <div class="card">
            <h3>Simax Errors</h3>
            <p>Hata kodlarını görüntüleyin</p>
            <a href="${pageContext.request.contextPath}/user/errors">Görüntüle</a>
        </div>

        <div class="card">
            <h3>CCT Response Codes</h3>
            <p>Yanıt kodlarını görüntüleyin</p>
            <a href="${pageContext.request.contextPath}/user/codes">Görüntüle</a>
        </div>

        <div class="card">
            <h3>Excel Yükle</h3>
            <p>Dosya yükleyip görüntüleyin</p>
            <form action="${pageContext.request.contextPath}/user/upload" method="post" enctype="multipart/form-data">
                <input type="file" name="file" accept=".xlsx,.xls" required>
                <button type="submit" style="margin-top: 10px; padding: 10px 20px; background: #27ae60;
                           color: white; border: none; border-radius: 5px; cursor: pointer;">Yükle</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

