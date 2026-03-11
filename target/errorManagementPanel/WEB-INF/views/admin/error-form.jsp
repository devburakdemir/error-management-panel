<%--
  Created by IntelliJ IDEA.
  User: 34bur
  Date: 23.10.2025
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Hata Formu</title>
  <style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: Arial, sans-serif; background: #f4f4f4; }
    .navbar { background: #2c3e50; color: white; padding: 15px 30px; }
    .container { max-width: 600px; margin: 30px auto; padding: 40px; background: white;
      border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
    .form-group { margin-bottom: 20px; }
    label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
    input, textarea { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; }
    textarea { resize: vertical; min-height: 100px; }
    .btn { padding: 12px 30px; background: #3498db; color: white; border: none;
      border-radius: 5px; cursor: pointer; }
    .btn:hover { background: #2980b9; }
  </style>
</head>
<body>
<div class="navbar">
  <h1>Hata Formu</h1>
</div>

<div class="container">
  <form action="${error.errorCode == null ? pageContext.request.contextPath.concat('/admin/errors/save') :
                      pageContext.request.contextPath.concat('/admin/errors/update')}" method="post">

    <div class="form-group">
      <label for="errorCode">Hata Kodu:</label>
      <input type="number" id="errorCode" name="errorCode" value="${error.errorCode}"
      ${error.errorCode != null ? 'readonly' : ''} required>
    </div>

    <div class="form-group">
      <label for="errorDescription">Açıklama:</label>
      <textarea id="errorDescription" name="errorDescription" required>${error.errorDescription}</textarea>
    </div>

    <div class="form-group">
      <label for="errorCategory">Kategori:</label>
      <input type="text" id="errorCategory" name="errorCategory" value="${error.errorCategory}">
    </div>

    <button type="submit" class="btn">Kaydet</button>
    <a href="${pageContext.request.contextPath}/admin/errors" style="margin-left: 10px;">İptal</a>
  </form>
</div>
</body>
</html>

