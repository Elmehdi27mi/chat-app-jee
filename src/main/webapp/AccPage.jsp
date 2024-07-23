 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Connexion Utilisateur</title>
    
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="login.css ">
    
    <!-- Add your custom styles if needed -->
    <style>
        /* Add your custom styles here */
    </style>
</head>
<body>

<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->
    <h2 class="active"> Sign In </h2>
    <a href="inscription.jsp" class="inactive underlineHover"><h2>Sign Up </h2></a>

     

    <!-- Icon -->
    <div class="fadeIn first">
      <img src="3.png" id="icon" alt="User Icon" />
    </div>

    <!-- Login Form -->
    <form action="verification.do" method="post">
      <input type="text" id="login" class="fadeIn second" name="id" placeholder="login">
      <input type="text" id="password" class="fadeIn third" name="motpass" placeholder="password">
      <input type="submit" class="fadeIn fourth" value="Log In">
    </form>

    <!-- Remind Passowrd -->
    <div id="formFooter">
      <a class="underlineHover" href="#">Forgot Password?</a>
    </div>

  </div>
</div>


<script src="jquery.slim.min.js"></script>
<script src="bootstrap.bundle.min.js"></script>
</body>
</html>








<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Connexion Utilisateur</title>
    
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap.min.css">
    
    <!-- Add your custom styles if needed -->
    <style>
        /* Add your custom styles here */
    </style>
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white">
                    <h3 class="text-center">Connexion Utilisateur</h3>
                </div>
                <div class="card-body">
                    <form action="verification.do" method="post">
                        <div class="form-group">
                            <label for="id">Identifiant</label>
                            <input type="text" class="form-control" id="id" name="id" required>
                        </div>
                        <div class="form-group">
                            <label for="motpass">Mot de passe</label>
                            <input type="password" class="form-control" id="motpass" name="motpass" required>
                        </div>
                        <div class="d-grid mt-4">
                            <button type="submit" class="btn btn-primary btn-block">Se connecter</button>
                        </div>
                        <div class="mt-3 text-danger">
                            ${mess}
                        </div>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <p class="mb-0">Vous n'avez pas de compte ? <a href="inscription.jsp">Créer un compte</a></p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="jquery.slim.min.js"></script>
<script src="bootstrap.bundle.min.js"></script>
</body>
</html>
 --%>




























































<%--Version final groups ,et un seul --%>

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion Utilisateur</title>
    <link rel="stylesheet" href="bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3 class="text-center">Connexion Utilisateur</h3>
                </div>
                <div class="card-body">
                    <form action="verification.do" method="post">
                        <div class="mb-3">
                            <label for="id" class="form-label">Identifiant</label>
                            <input type="text" class="form-control" id="id" name="id" required>
                        </div>
                        <div class="mb-3">
                            <label for="motpass" class="form-label">Mot de passe</label>
                            <input type="password" class="form-control" id="motpass" name="motpass" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Se connecter</button>
                        </div>
                        <div class="mt-3 text-danger">
                            ${mess}
                        </div>
                    </form>
                    
                </div>
            </div>
        </div>
    </div>
</div>

<script src="bootstrap.bundle.min.js"></script>
</body>
</html>
 --%>