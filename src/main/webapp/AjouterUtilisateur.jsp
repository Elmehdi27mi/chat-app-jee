<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat App - Add Contacts</title>
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
            background-color: #34495E;
            color: #fff;
            font-weight: bold;
            padding: 10px;
            display: flex;
            justify-content: space-between;
        }

        .list-group-item {
            border: none;
            padding: 10px;
        }

        .btn-outline-info {
            color: #3498db;
            border-color: #3498db;
        }

        .btn-outline-info:hover {
            background-color: #3498db;
            color: #fff;
        }

        .user-list {
            max-height: 300px; /* Ajustez la hauteur maximale en fonction de vos besoins */
            overflow-y: auto;
        }

        footer {
            background-color: #34495E;
            color: #fff;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        .icon {
            font-size: 20px;
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Add Other Users</h1>
    </header>
    <nav>
        <a href="AjouterUtilisateur.do">Add Contact</a>
        <a href="conexion.do">Chat</a>
        <a href="AccPage.jsp">Logout</a>
        <a href="#">About</a>
         <a href="status.do">Status</a>
        <a href="#">Contact</a>
    </nav>
    <div class="container">
       <!--  <h2 class="text-center mb-4">Add Other Users</h2> -->
        <div class="card">
            <div class="card-header">
                <span>Friend Requests</span>
                <i class="fas fa-users"></i>
            </div>
            <div class="card-body user-list">
                <ul class="list-group">
                    <c:forEach items="${model}" var="u">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            ${u.nom} ${u.prenom}
                            <a class="btn btn-outline-info" href="chat.do?id=${u.idUtilisateur}">Add</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <footer>
        <p>This is a simple chat application created for demonstration purposes.</p>
        <p>
            <i class="icon fas fa-info-circle"></i> About &nbsp;&nbsp;&nbsp;
            <i class="icon fas fa-envelope"></i> Contact &nbsp;&nbsp;&nbsp;
            <i class="icon fas fa-privacy"></i> Privacy Policy
        </p>
    </footer>
    <!-- Ajoutez le script Bootstrap (assurez-vous que le chemin est correct) -->
    <script src="jquery.slim.min.js"></script>
    <script src="popper.min.js"></script>
    <script src="bootstrap.bundle.min.js"></script>
    <script src="all.min.js"></script>
</body>
</html>












































<%-- 



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat App - Add Contacts</title>
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
        }

        nav a {
            color: #fff;
            text-decoration: none;
            margin: 0 15px;
        }

        nav a:hover {
            text-decoration: underline;
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
            background-color: #34495E;
            color: #fff;
            font-weight: bold;
            padding: 10px;
            display: flex;
            justify-content: space-between;
        }

        .list-group-item {
            border: none;
            padding: 10px;
        }

        .btn-outline-info {
            color: #3498db;
            border-color: #3498db;
        }

        .btn-outline-info:hover {
            background-color: #3498db;
            color: #fff;
        }

        footer {
            background-color: #34495E;
            color: #fff;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        .icon {
            font-size: 20px;
            margin-right: 5px;
        }

        .user-list {
            max-height: 300px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to Your Chat App</h1>
    </header>
    <nav>
        <a href="AjouterUtilisateur.do" class="nav-link">Add Contact</a>
        <a href="conexion.do" class="nav-link">Chat</a>
        <a href="AccPage.jsp" class="nav-link">Logout</a>
        <a href="#" class="nav-link">About</a>
        <a href="#" class="nav-link">Contact</a>
    </nav>
    <div class="container">
        <h2 class="text-center mb-4">Add Other Users</h2>
        <div class="card">
            <div class="card-header">
                <span>Friend Requests</span>
                <i class="fas fa-users"></i>
            </div>
            <div class="card-body user-list">
                <ul class="list-group">
                    <c:forEach items="${model}" var="u">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            ${u.nom} ${u.prenom}
                            <a class="btn btn-outline-info" href="chat.do?id=${u.idUtilisateur}">Add</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <footer>
        <p>This is a simple chat application created for demonstration purposes.</p>
        <p>
            <i class="icon fas fa-info-circle"></i> About &nbsp;&nbsp;&nbsp;
            <i class="icon fas fa-envelope"></i> Contact &nbsp;&nbsp;&nbsp;
            <i class="icon fas fa-privacy"></i> Privacy Policy
        </p>
    </footer>
    <!-- Ajoutez le script Bootstrap (assurez-vous que le chemin est correct) -->
    <script src="jquery.slim.min.js"></script>
    <script src="popper.min.js"></script>
    <script src="bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
</body>
</html>
 --%>
