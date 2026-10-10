<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, modele.MonIMC" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    MonIMC patient = null;

    String url  = "jdbc:mysql://localhost:3306/listeimc?useSSL=false&serverTimezone=UTC";
    String user = "root";
    String pass = "";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cn = DriverManager.getConnection(url, user, pass);
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM patient WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            patient = new MonIMC();
            patient.setId(rs.getInt("id"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setMasse(rs.getDouble("masse"));
            patient.setTaille(rs.getDouble("taille"));
        }
        rs.close(); ps.close(); cn.close();
    } catch (Exception e) { e.printStackTrace(); }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier patient</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <h1>Modifier un patient</h1>

    <% if (patient != null) { %>
    <form action="ImcServlet" method="post">
        <input type="hidden" name="action" value="modifier">
        <input type="hidden" name="id" value="<%= patient.getId() %>">

        <div class="input-group mb-3">
            <span class="input-group-text">@</span>
            <input type="text" name="nom" class="form-control" value="<%= patient.getNom() %>" required>
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">@</span>
            <input type="text" name="prenom" class="form-control" value="<%= patient.getPrenom() %>" required>
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">@</span>
            <input type="number" step="0.01" name="masse" class="form-control" value="<%= patient.getMasse() %>" required>
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">@</span>
            <input type="number" step="0.01" name="taille" class="form-control" value="<%= patient.getTaille() %>" required>
        </div>

        <button type="submit" class="btn btn-primary">Enregistrer les modifications</button>
        <a href="accueil.jsp" class="btn btn-secondary">Annuler</a>
    </form>
    <% } else { %>
        <p class="text-danger">Patient introuvable.</p>
        <a href="accueil.jsp" class="btn btn-secondary">Retour</a>
    <% } %>

</body>
</html>