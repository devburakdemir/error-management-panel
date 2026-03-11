<%--
  Created by IntelliJ IDEA.
  User: 34bur
  Date: 23.10.2025
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>CCT Response Codes Yönetimi</title>
  <style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: Arial, sans-serif; background: #f4f4f4; }
    .navbar { background: #2c3e50; color: white; padding: 15px 30px; display: flex;
      justify-content: space-between; align-items: center; }
    .navbar h1 { font-size: 24px; }
    .navbar a { color: white; text-decoration: none; margin-left: 20px; }
    .container { max-width: 1200px; margin: 30px auto; padding: 20px; background: white;
      border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
    .btn { display: inline-block; padding: 10px 20px; background: #3498db; color: white;
      text-decoration: none; border-radius: 5px; margin-bottom: 20px; border: none; cursor: pointer; }
    .btn:hover { background: #2980b9; }
    .btn-danger { background: #e74c3c; }
    .btn-danger:hover { background: #c0392b; }

    .search-box { margin-bottom: 20px; display: flex; gap: 10px; align-items: center; }
    .search-box input { padding: 10px; border: 1px solid #ddd; border-radius: 5px; flex: 1; }
    .search-box select { padding: 10px; border: 1px solid #ddd; border-radius: 5px; }

    .table-container { max-height: 500px; overflow-y: auto; border: 1px solid #ddd; }
    table { width: 100%; border-collapse: collapse; }
    th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
    th { background: #34495e; color: white; position: sticky; top: 0; z-index: 10; }
    tr:hover { background: #f5f5f5; }

    .table-container::-webkit-scrollbar { width: 10px; }
    .table-container::-webkit-scrollbar-track { background: #f1f1f1; }
    .table-container::-webkit-scrollbar-thumb { background: #888; border-radius: 5px; }
    .table-container::-webkit-scrollbar-thumb:hover { background: #555; }
  </style>
</head>
<body>
<div class="navbar">
  <h1>CCT Response Codes</h1>
  <div>
    <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/logout">Çıkış</a>
  </div>
</div>

<div class="container">
  <a href="${pageContext.request.contextPath}/admin/codes/create" class="btn">Yeni Kod Ekle</a>

  <div class="search-box">
    <input type="text" id="searchInput" placeholder="Ara..." onkeyup="searchTable()">
    <select id="filterColumn" onchange="searchTable()">
      <option value="all">Tüm Alanlar</option>
      <option value="0">Response Code</option>
      <option value="1">Açıklama</option>
      <option value="2">Kategori</option>
    </select>
    <button class="btn" onclick="searchTable()">Ara</button>
  </div>

  <div class="table-container">
    <table id="codeTable">
      <thead>
      <tr>
        <th>Response Code</th>
        <th>Açıklama</th>
        <th>Kategori</th>
        <th>İşlemler</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="code" items="${codes}">
        <tr>
          <td>${code.responseCode}</td>
          <td>${code.responseDescription}</td>
          <td>${code.responseCategory}</td>
          <td>
            <a href="${pageContext.request.contextPath}/admin/codes/edit/${code.responseCode}" class="btn">Düzenle</a>
            <a href="${pageContext.request.contextPath}/admin/codes/delete/${code.responseCode}"
               class="btn btn-danger" onclick="return confirm('Silmek istediğinize emin misiniz?')">Sil</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<script>
  function searchTable() {
    var input = document.getElementById("searchInput");
    var filter = input.value.toUpperCase();
    var table = document.getElementById("codeTable");
    var tr = table.getElementsByTagName("tr");
    var filterColumn = document.getElementById("filterColumn").value;

    for (var i = 1; i < tr.length; i++) {
      var row = tr[i];
      var found = false;

      if (filterColumn === "all") {
        var tds = row.getElementsByTagName("td");
        for (var j = 0; j < 3; j++) {
          if (tds[j]) {
            var txtValue = tds[j].textContent || tds[j].innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
              found = true;
              break;
            }
          }
        }
      } else {
        var td = row.getElementsByTagName("td")[filterColumn];
        if (td) {
          var txtValue = td.textContent || td.innerText;
          if (txtValue.toUpperCase().indexOf(filter) > -1) {
            found = true;
          }
        }
      }

      row.style.display = found ? "" : "none";
    }
  }
</script>
</body>
</html>

