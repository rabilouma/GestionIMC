<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil - IMC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <h1>Liste des patients</h1>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Masse</th>
                <th>Taille</th>
                <th>IMC</th>
                <th>Interprétation</th>
                <th>Supprimer</th>
                <th>Modifier</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${listeIMC}">
                <tr>
                    <td>${p.nom}</td>
                    <td>${p.prenom}</td>
                    <td>${p.masse}</td>
                    <td>${p.taille}</td>
                    <td>${p.imc}</td>
                    <td>${p.interpretation}</td>
                    <td>
                        <a href="ImcServlet?action=supprimer&id=${p.id}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Supprimer ce patient ?');">Supprimer</a>
                    </td>
                    <td>
                        <a href="modifier_patient.jsp?id=${p.id}"
                           class="btn btn-warning btn-sm">Modifier</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="new_patient.jsp" class="btn btn-primary">Ajouter un patient</a>

</body>
</html>