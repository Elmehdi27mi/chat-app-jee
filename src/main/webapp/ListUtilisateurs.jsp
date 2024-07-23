<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Liste des utilisateurs</title>
    <link rel="stylesheet" href="bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>
                <th>nom</th>
                <th>prenom</th>
                <th>message</th>
            </tr>
        </thead>
        <tbody>
           <c:forEach items="${model}" var="u">
               <c:if test="${u.idUtilisateur ne UtilisateurId1}">
                   <tr>
                       <td>${u.nom}</td>
                       <td>${u.prenom}</td>
                       <td class="lead">
                           <a class="btn btn-outline-info" href="message.do?UtilisateurId2=${u.idUtilisateur}">message</a>
                       </td>
                   </tr>
               </c:if>
           </c:forEach>
        </tbody>
    </table>
</div>

<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
