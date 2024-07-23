<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Conversations</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="bootstrap.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }

        header {
            background-color: #4267B2;
            color: #fff;
            text-align: center;
            padding: 10px;
        }

        nav {
            background-color: #34495E;
            padding: 10px;
            text-align: center;
            margin-bottom: 20px; /* Ajout d'une marge en bas pour l'espace */
        }

        nav a {
            color: #fff;
            text-decoration: none;
            margin: 0 15px;
        }

        .container {
            margin-top: 50px;
        }

        .card {
            border: none;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: box-shadow 0.3s ease-in-out;
        }

        .card:hover {
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }

        .card-header {
            background-color: #34495E; /* Couleur changée pour correspondre à l'autre barre */
            color: #fff;
            font-weight: bold;
            padding: 10px;
            display: flex;
            justify-content: space-between; /* Alignement des éléments à l'extrémité opposée */
        }

        .conversation-link {
            color: #007bff;
            text-decoration: none;
        }

        .conversation-link:hover {
            text-decoration: underline;
        }

        .back-link {
            font-size: 20px;
            color: #007bff;
            text-decoration: none;
            display: inline-block;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        .description {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .comment-icon {
            margin-left: 10px; /* Ajustez la marge selon vos préférences */
            color: #007bff;
        }
    </style>
</head>
<body>
    <header>
        <h1>Conversations</h1>
    </header>
    <nav>
        <a href="AjouterUtilisateur.do">Add Contact</a>
        <a href="conexion.do">Chat</a>
        <a href="out.do">Logout</a>
        <a href="#">About</a>
         <a href="status.do">Status</a>
        <a href="#">Contact</a>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-md-6 mx-auto">
               <!--  <h1 class="text-center mb-4">Conversations</h1> -->
                <div class="card">
                    <div class="card-header">
                        <span>Chats !</span>
                        <i class="fas fa-comments"></i>
                    </div>
                    <div class="card-body">
                        <div class="list-group">
                            <c:forEach items="${model}" var="c">
                                <a href="message.do?idc=${c.idConversation}" class="list-group-item list-group-item-action">
                                    <h5 class="mb-1 description">${c.description}<i class="fas fa-comment comment-icon"></i></h5>
                                    <!-- You can add more information here if needed -->
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="javas/bootstrap.bundle.min.js"></script>
</body>
</html>




<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personne Par son nom</title>
     <link rel="stylesheet" href="<%= request.getContextPath() %>/lib/bootstrap/css/bootstrap.min.css">

 			<link rel="stylesheet" href="bootstrap.min.css">
 </head>
<body>
	<div class="container mt-2 ">
    <div class="card ">
        <div class="card-header bg-dark text-white p-3">Bonjour !</div>
        <div class="card-body">
            <table class="table  table-striped table-borderless">
                <thead class="thead-dark">
                    <tr>
                        <th >Id</th>
                        <th >Descréption</th>
                     <!--    <th >Prenom</th>
                        <th>Supprimer</th>
                        <th >Edit</th>
                        <th >Reunion</th>
                        <th >Ajouter Reunion</th> -->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${model}" var="c">
                        <tr>
                            <td class="lead">${c.idConversation}</td>
                            <td class="lead">${c.description}</td>
                            
                            <td class="lead">
                                <a class="btn btn-outline-info" href="message.do?idc=${c.idConversation}">members</a>
                                </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

       <!--  <a href="AjouterPersonne.jsp">
            <button class="btn btn-dark ms-1 mb-4 ">Ajouter Personne</button>
        </a> -->
    </div>
</div>

	
	<script src="javas/bootstrap.bundle.min.js"></script>
	
 </body>
</html>

 --%>




















<%-- 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Conversations</title>
    <link rel="stylesheet" href="bootstrap.min.css">
</head>
<body>
    <div class="container mt-2">
        <div class="card">
            <div class="card-header bg-dark text-white p-3">Liste des Conversations</div>
            <div class="card-body">
                <div class="list-group">
                    <c:forEach items="${model}" var="c">
                        <a href="conversation.do?idc=${c.idConversation}" class="list-group-item list-group-item-action">
                            ${c.description}
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <script src="javas/bootstrap.bundle.min.js"></script>
</body>
</html>
 --%>