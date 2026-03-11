<%--
  Created by IntelliJ IDEA.
  User: 34bur
  Date: 23.10.2025
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Hata Kodları</title>
  <style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: Arial, sans-serif; background: #f4f4f4; }
    .navbar { background: #16a085; color: white; padding: 15px 30px; display: flex;
      justify-content: space-between; align-items: center; }
    .navbar h1 { font-size: 24px; }
    .navbar a { color: white; text-decoration: none; margin-left: 20px; }
    .container { max-width: 1200px; margin: 30px auto; padding: 20px; background: white;
      border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }

    /* Arama Alanı */
    .search-box { margin-bottom: 20px; display: flex; gap: 10px; align-items: center; }
    .search-box input { padding: 10px; border: 1px solid #ddd; border-radius: 5px; flex: 1; }
    .search-box select { padding: 10px; border: 1px solid #ddd; border-radius: 5px; }
    .btn { padding: 10px 20px; background: #16a085; color: white; border: none;
      border-radius: 5px; cursor: pointer; }
    .btn:hover { background: #138d75; }

    /* Tablo Container (scrollbar için) */
    .table-container { max-height: 500px; overflow-y: auto; border: 1px solid #ddd; }
    table { width: 100%; border-collapse: collapse; }
    th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
    th { background: #16a085; color: white; position: sticky; top: 0; z-index: 10; }
    tr:hover { background: #f5f5f5; }

    /* Scrollbar stil */
    .table-container::-webkit-scrollbar { width: 10px; }
    .table-container::-webkit-scrollbar-track { background: #f1f1f1; }
    .table-container::-webkit-scrollbar-thumb { background: #16a085; border-radius: 5px; }
    .table-container::-webkit-scrollbar-thumb:hover { background: #138d75; }
  </style>
</head>
<body>
<div class="navbar">
  <h1>Hata Kodları</h1>
  <div>
    <a href="${pageContext.request.contextPath}/user/dashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/logout">Çıkış</a>
  </div>
</div>

<div class="container">
  <h2>Simax Errors Listesi</h2>

  <!-- Arama ve Filtreleme -->
  <div class="search-box">
    <input type="text" id="searchInput" placeholder="Ara..." onkeyup="searchTable()">
    <select id="filterColumn" onchange="searchTable()">
      <option value="all">Tüm Alanlar</option>
      <option value="0">Hata Kodu</option>
      <option value="1">Açıklama</option>
      <option value="2">Kategori</option>
    </select>
    <button class="btn" onclick="searchTable()">Ara</button>
  </div>

  <div class="table-container">
    <table id="errorTable">
      <thead>
      <tr>
        <th>Hata Kodu</th>
        <th>Açıklama</th>
        <th>Kategori</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="error" items="${errors}">
        <tr>
          <td>${error.errorCode}</td>
          <td>${error.errorDescription}</td>
          <td>${error.errorCategory}</td>
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
    var table = document.getElementById("errorTable");
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


