<%--
  Created by IntelliJ IDEA.
  User: 34bur
  Date: 23.10.2025
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Giriş Yap - Error Management Panel</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            height: 100vh; display: flex; align-items: center; justify-content: center; }
        .login-container { background: white; padding: 40px; border-radius: 10px; box-shadow: 0 10px 25px rgba(0,0,0,0.2);
            width: 400px; }
        h2 { text-align: center; color: #333; margin-bottom: 30px; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 5px; color: #555; font-weight: bold; }
        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%; padding: 12px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px;
        }
        input[type="text"]:focus, input[type="email"]:focus, input[type="password"]:focus {
            outline: none; border-color: #667eea;
        }
        .btn { width: 100%; padding: 12px; background: #667eea; color: white; border: none;
            border-radius: 5px; font-size: 16px; cursor: pointer; transition: 0.3s; }
        .btn:hover { background: #5568d3; }
        .error { color: #e74c3c; text-align: center; margin-bottom: 15px; }
        .register-link { text-align: center; margin-top: 20px; }
        .register-link a { color: #667eea; text-decoration: none; }
        .register-link a:hover { text-decoration: underline; }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Giriş Yap</h2>

    <% if(request.getParameter("registered") != null) { %>
    <p style="color: green; text-align: center; margin-bottom: 15px;">Kayıt başarılı! Giriş yapabilirsiniz.</p>
    <% } %>

    <% if(request.getAttribute("error") != null) { %>
    <p class="error">${error}</p>
    <% } %>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Şifre:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <button type="submit" class="btn">Giriş Yap</button>
    </form>

    <div class="register-link">
        <p>Hesabınız yok mu? <a href="${pageContext.request.contextPath}/register">Kayıt Ol</a></p>
    </div>
</div>
</body>
</html>

