<%--
  Created by IntelliJ IDEA.
  User: 34bur
  Date: 23.10.2025
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Kayıt Ol - Error Management Panel</title>
  <style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: Arial, sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 20px; }
    .register-container { background: white; padding: 40px; border-radius: 10px; box-shadow: 0 10px 25px rgba(0,0,0,0.2);
      width: 400px; }
    h2 { text-align: center; color: #333; margin-bottom: 30px; }
    .form-group { margin-bottom: 20px; }
    label { display: block; margin-bottom: 5px; color: #555; font-weight: bold; }
    input[type="text"], input[type="email"], input[type="password"] {
      width: 100%; padding: 12px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px;
    }
    input:focus { outline: none; border-color: #667eea; }
    .btn { width: 100%; padding: 12px; background: #667eea; color: white; border: none;
      border-radius: 5px; font-size: 16px; cursor: pointer; transition: 0.3s; }
    .btn:hover { background: #5568d3; }
    .error { color: #e74c3c; text-align: center; margin-bottom: 15px; }
    .login-link { text-align: center; margin-top: 20px; }
    .login-link a { color: #667eea; text-decoration: none; }
    .login-link a:hover { text-decoration: underline; }
  </style>
</head>
<body>
<div class="register-container">
  <h2>Kayıt Ol</h2>

  <% if(request.getAttribute("error") != null) { %>
  <p class="error">${error}</p>
  <% } %>

  <form action="${pageContext.request.contextPath}/register" method="post">
    <div class="form-group">
      <label for="firstName">Ad:</label>
      <input type="text" id="firstName" name="firstName" required>
    </div>

    <div class="form-group">
      <label for="lastName">Soyad:</label>
      <input type="text" id="lastName" name="lastName" required>
    </div>

    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" id="email" name="email" required>
    </div>

    <div class="form-group">
      <label for="phone">Telefon (10 haneli):</label>
      <input type="text" id="phone" name="phone" maxlength="10" pattern="[0-9]{10}" required>
    </div>

    <div class="form-group">
      <label for="password">Şifre:</label>
      <input type="password" id="password" name="password" required>
    </div>

    <button type="submit" class="btn">Kayıt Ol</button>
  </form>

  <div class="login-link">
    <p>Zaten hesabınız var mı? <a href="${pageContext.request.contextPath}/login">Giriş Yap</a></p>
  </div>
</div>
</body>
</html>

