<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouveau patient</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <h1>Création d'un patient</h1>

    <form action="ImcServlet" method="post">
        <input type="hidden" name="action" value="creer">

        <div class="input-group mb-3">
            <span class="input-group-text">@</span>
            <input type="text" name="nom" class="form-control" placeholder="Nom" required>
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">@</span>
            <input type="text" name="prenom" class="form-control" placeholder="Prénom" required>
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">@</span>
            <input type="number" step="0.01" name="masse" class="form-control" placeholder="Masse (kg)" required>
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">@</span>
            <input type="number" step="0.01" name="taille" class="form-control" placeholder="Taille (m)" required>
        </div>

        <button type="submit" class="btn btn-primary">Créer patient</button>
        <button type="reset" class="btn btn-secondary">Réinitialiser</button>
    </form>

</body>
</html>