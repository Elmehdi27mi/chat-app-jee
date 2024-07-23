<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Your Chat App</title>
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
        <h1>Welcome to Your Chat App</h1>
    </header>
    <nav>
        <a href="AjouterUtilisateur.do">Add Contact</a>
        <a href="conexion.do">Chat</a>
        <a href="out.do">Logout</a>
        <a href="#">About</a>
        <a href="#">Contact</a>
         <a href="status.do">Status</a>
    </nav>
    <main>
        <p><i class="icon fas fa-comments"></i> This is a modern and user-friendly chat application. Choose an option from the navigation above.</p>
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
