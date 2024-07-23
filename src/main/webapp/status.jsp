<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Chat App - Status</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
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

        main {
            padding: 20px;
            text-align: center;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input {
            width: 100%;
            padding: 15px;
            margin-bottom: 20px;
            border: 2px solid #f6f6f6;
            border-radius: 5px;
            background-color: #f6f6f6;
            font-size: 16px;
            outline: none;
            box-sizing: border-box;
        }

        input:focus {
            background-color: #fff;
            border-bottom: 2px solid #5fbae9;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #39ace7;
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
        <h1>Your Chat App - Status</h1>
    </header>
    <nav>
        <a href="AjouterUtilisateur.do">Add Contact</a>
        <a href="conexion.do">Chat</a>
        <a href="out.do">Logout</a>
        <a href="#">About</a>
         <a href="status.do">Status</a>
        <a href="#">Contact</a>
    </nav>
    <main>
        <p>Nom: ${user.nom }</p>
        <p>Prénom: ${user.prenom }</p>
        <p>Rôle: ${user.role }</p>
       
    </main>
    <footer>
        <p>This is a simple chat application created for demonstration purposes.</p>
        <p>
            <i class="icon fas fa-info-circle"></i> About &nbsp;&nbsp;&nbsp;
            <i class="icon fas fa-envelope"></i> Contact &nbsp;&nbsp;&nbsp;
            <i class="icon fas fa-privacy"></i> Privacy Policy
        </p>
    </footer>
</body>
</html>
