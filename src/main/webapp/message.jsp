<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Chat Moderne</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <link rel="stylesheet" href="all.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }

        .navbar {
            background-color: #34495E;
            color: #fff;
        }

        .navbar-brand {
            font-size: 24px;
            font-weight: bold;
        }

        .message-list {
            max-height: 500px;
            overflow-y: auto;
        }

        .card {
            border: none;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: box-shadow 0.3s ease-in-out;
        }

        .card-body {
            padding: 0;
        }

        .list-group-item {
            border: none;
            padding: 10px;
        }

        .btn-danger, .btn-outline-danger {
            color: #fff;
        }

        .bg-light {
            background-color: #fff;
        }

        .input-group {
            width: 100%;
        }

        .form-control {
            border-radius: 0;
        }

        .btn-primary {
            border-radius: 0;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-dark bg-secondary w-100">
        <!-- Ajout de l'icône pour sortir de la page à gauche -->
        <div class="text-start ms-2 mt-2">
            <a href="conexion.do" class="text-white"><i class="fas fa-arrow-left"></i></a>
        </div>

        <span class="navbar-brand mb-0 h1 mx-auto">Chat</span>

        <!-- Icône pour afficher les membres à droite -->
        <div class="text-end me-2 mt-2">
            <a href="group.do" class="text-white"><i class="fas fa-users"></i></a>
        </div> 
    </nav>

    <form action="envoyerMessage.do" method="post">
        <div class="container-fluid">
            
            <div class="row">
                <div class="col-md-8 offset-md-2 col-lg-6 offset-lg-3 mt-4">
                    <div class="card mb-4">
                        <div class="card-body message-list" id="messageList">
                            <ul class="list-group list-group-flush">
                                <c:forEach items="${model}" var="m">
                                    <script>
                                        // Utilisation de JavaScript pour conditionner l'affichage des messages
                                        function confirmDelete1(messageId) {
                                            var result = confirm("Êtes-vous sûr de vouloir supprimer ce message ?");
                                            if (result) {
                                                window.location.href = "supprimerMessageD.do?idm=" + messageId;
                                            }
                                        }
                                        function confirmDelete2(messageId) {
                                            var result = confirm("Êtes-vous sûr de vouloir supprimer ce message , pour vous?");
                                            if (result) {
                                                window.location.href = "supprimerMessageR.do?idm=" + messageId;
                                            }
                                        }
                                        
                                        if (${m.utilisateur.idUtilisateur eq UtilisateurId1}) {
                                            document.write('<li class="list-group-item text-end bg-light">${m.contenu} <span class="badge bg-info">${m.utilisateur.nom} </span> <a class="btn btn-outline-info" onclick="confirmDelete1(${m.idMessage})"></a></li>');
                                            document.write('  ');
                                        } else {
                                            if(${m.supprime eq false}){
                                                document.write('<li class="list-group-item text-start bg-light">  <a class="btn btn-outline-danger" onclick="confirmDelete2(${m.idMessage})"></a>     <span class="badge bg-danger">${m.utilisateur.nom} </span> ${m.contenu} </li>');
                                            }
                                        }
                                    </script>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="fixed-bottom bg-light p-3">
                        <div class="container">
                            <div class="input-group">
                            
                             <c:if test="${uc.bloque == false }">
				            	  <input type="text" name="message" class="form-control mb-5 me-2" placeholder="Écrire un message..." aria-label="Message" aria-describedby="button-addon2">
				            	    <div class="input-group-append">
                                    <button class="btn btn-primary " type="submit" id="button-addon2">Envoyer</button>
                                </div>
					       </c:if>
					        <c:if test="${uc.bloque == true }">
				            	   <span class="badge bg-dark mx-auto"> Vous avez Bloqué</span>
					       </c:if>
                            
                              
                              
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script src="bootstrap.bundle.min.js"></script>
    <script src="all.min.js"></script>
    <script>
        // Fonction pour faire défiler la barre de défilement vers le bas
        function scrollToBottom() {
            const messageList = document.getElementById('messageList');
            messageList.scrollTop = messageList.scrollHeight;
        }

        // Appeler la fonction pour faire défiler vers le bas lors du chargement initial de la page
        document.addEventListener("DOMContentLoaded", () => {
            scrollToBottom();
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
     // we Check if the Page Visibility API is supported in the browser
        if (typeof document.hidden !== "undefined") {
            var visibilityChange = "visibilitychange";
            var hiddenProperty = "hidden";
        } else if (typeof document.msHidden !== "undefined") {
            visibilityChange = "msvisibilitychange";
            hiddenProperty = "msHidden";
        } else if (typeof document.webkitHidden !== "undefined") {
            visibilityChange = "webkitvisibilitychange";
            hiddenProperty = "webkitHidden";
        }

        // Listen for visibility change events
        document.addEventListener(visibilityChange, function() {
            if (!document[hiddenProperty]) {
                // Page is visible (tab is active), reload the page
                location.reload();
            }
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    </script>
</body>
</html>



































<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Chat Moderne</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }

        .navbar {
            background-color: #34495E;
            color: #fff;
        }

        .navbar-brand {
            font-size: 24px;
            font-weight: bold;
        }

        .message-list {
            max-height: 500px;
            overflow-y: auto;
        }

        .card {
            border: none;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: box-shadow 0.3s ease-in-out;
        }

        .card-body {
            padding: 0;
        }

        .list-group-item {
            border: none;
            padding: 10px;
        }

        .btn-danger, .btn-outline-danger {
            color: #fff;
        }

        .bg-light {
            background-color: #fff;
        }

        .input-group {
            width: 100%;
        }

        .form-control {
            border-radius: 0;
        }

        .btn-primary {
            border-radius: 0;
        }
    </style>
</head>
<body>
    <form action="envoyerMessage.do" method="post">
        <div class="container-fluid">
            <div class="row">
                <nav class="navbar navbar-dark bg-secondary w-100">
                    <span class="navbar-brand mb-0 h1 mx-auto">Chat</span>
                </nav>
            </div>

            <div class="row">
                <div class="col-md-8 offset-md-2 col-lg-6 offset-lg-3 mt-4">
                    <div class="card mb-4">
                        <div class="card-body message-list" id="messageList">
                            <ul class="list-group list-group-flush">
                                <c:forEach items="${model}" var="m">
                                    <script>
                                        // Utilisation de JavaScript pour conditionner l'affichage des messages
                                        function confirmDelete1(messageId) {
                                            var result = confirm("Êtes-vous sûr de vouloir supprimer ce message ?");
                                            if (result) {
                                                window.location.href = "supprimerMessageD.do?idm=" + messageId;
                                            }
                                        }
                                        function confirmDelete2(messageId) {
                                            var result = confirm("Êtes-vous sûr de vouloir supprimer ce message , pour vous?");
                                            if (result) {
                                                window.location.href = "supprimerMessageR.do?idm=" + messageId;
                                            }
                                        }
                                        
                                        if (${m.utilisateur.idUtilisateur eq UtilisateurId1}) {
                                            document.write('<li class="list-group-item text-end bg-light">${m.contenu} <a class="btn btn-outline-info" onclick="confirmDelete1(${m.idMessage})"><i class="fas fa-trash"></i></a></li>');
                                            document.write('  ');
                                        } else {
                                            if(${m.supprime eq false}){
                                                document.write('<li class="list-group-item text-start bg-light"><a class="btn btn-outline-danger" onclick="confirmDelete2(${m.idMessage})"><i class="fas fa-trash"></i></a> ${m.contenu}</li>');
                                            }
                                        }
                                    </script>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="fixed-bottom bg-light p-3">
                        <div class="container">
                            <div class="input-group">
                                <input type="text" name="message" class="form-control mb-5 me-2" placeholder="Écrire un message..." aria-label="Message" aria-describedby="button-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-primary " type="submit" id="button-addon2">Envoyer</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script src="bootstrap.bundle.min.js"></script>
    <script>
        // Fonction pour faire défiler la barre de défilement vers le bas
        function scrollToBottom() {
            const messageList = document.getElementById('messageList');
            messageList.scrollTop = messageList.scrollHeight;
        }

        // Appeler la fonction pour faire défiler vers le bas lors du chargement initial de la page
        document.addEventListener("DOMContentLoaded", () => {
            scrollToBottom();
        });
        
        
        
     // we Check if the Page Visibility API is supported in the browser
        if (typeof document.hidden !== "undefined") {
            var visibilityChange = "visibilitychange";
            var hiddenProperty = "hidden";
        } else if (typeof document.msHidden !== "undefined") {
            visibilityChange = "msvisibilitychange";
            hiddenProperty = "msHidden";
        } else if (typeof document.webkitHidden !== "undefined") {
            visibilityChange = "webkitvisibilitychange";
            hiddenProperty = "webkitHidden";
        }

        // Listen for visibility change events
        document.addEventListener(visibilityChange, function() {
            if (!document[hiddenProperty]) {
                // Page is visible (tab is active), reload the page
                location.reload();
            }
        });
        
        
    </script>
</body>
</html>


 --%>





















<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Chat Moderne</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <style>
        .message-list {
            max-height: 500px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
    <form action="envoyerMessage.do" method="post">
        <div class="container-fluid">
            <div class="row">
                <nav class="navbar navbar-dark bg-secondary w-100">
                    <span class="navbar-brand mb-0 h1">Chat Moderne</span>
                </nav>
            </div>

            <div class="row">
                <div class="col-md-8 offset-md-2 col-lg-6 offset-lg-3 mt-4">
                    <div class="card mb-4">
                        <div class="card-body message-list" id="messageList">
                            <ul class="list-group list-group-flush">
                                <c:forEach items="${model}" var="m">
                                    <script>
                                        // Utilisation de JavaScript pour conditionner l'affichage des messages
                                        if (${m.utilisateur.idUtilisateur eq UtilisateurId1}) {
                                            document.write('<li class="list-group-item text-end"> <a class="btn btn-outline-info" href="supprimerMessageD.do?idm=${m.idMessage}">supprimer</a>  ${m.contenu} </li>');
                                            document.write('  ');
                                        } else {
                                        	if(${m.supprime eq false}){
                                                document.write('<li class="list-group-item">${m.contenu}<a class="btn btn-outline-danger" href="supprimerMessageR.do?idm=${m.idMessage}">supprimer</a></li>');

                                        	}
                                        }
                                    </script>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="fixed-bottom bg-light p-3">
                        <div class="container">
                            <div class="input-group">
                                <input type="text" name="message" class="form-control mb-5 me-2" placeholder="Écrire un message..." aria-label="Message" aria-describedby="button-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="submit" id="button-addon2">Envoyer</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script src="bootstrap.bundle.min.js"></script>
    <script>
        // Fonction pour faire défiler la barre de défilement vers le bas
        function scrollToBottom() {
            const messageList = document.getElementById('messageList');
            messageList.scrollTop = messageList.scrollHeight;
        }

        // Appeler la fonction pour faire défiler vers le bas lors du chargement initial de la page
        document.addEventListener("DOMContentLoaded", () => {
            scrollToBottom();
        });
    </script>
</body>
</html>

 
  --%>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 <%-- utilisateur à utilisateur<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Chat Moderne</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <style>
        .message-list {
            max-height: 500px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
    <form action="envoyerMessage.do" method="post">
        <div class="container-fluid">
            <div class="row">
                <nav class="navbar navbar-dark bg-secondary w-100">
                    <span class="navbar-brand mb-0 h1">Chat Moderne</span>
                </nav>
            </div>

            <div class="row">
                <div class="col-md-8 offset-md-2 col-lg-6 offset-lg-3 mt-4">
                    <div class="card mb-4">
                        <div class="card-body message-list" id="messageList">
                            <ul class="list-group list-group-flush">
                                <c:forEach items="${model}" var="m">
                                    <c:choose>
                                        <c:when test="${m.utilisateur.idUtilisateur eq UtilisateurId1}">
                                            <li class="list-group-item text-end">${m.contenu}</li>
                                        </c:when>
                                        <c:when test="${m.utilisateur.idUtilisateur eq UtilisateurId2}">
                                            <li class="list-group-item">${m.contenu}</li>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Gérer le cas par défaut si nécessaire -->
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="fixed-bottom bg-light p-3">
                        <div class="container">
                            <div class="input-group">
                                <input type="text" name="message" class="form-control mb-5 me-2" placeholder="Écrire un message..." aria-label="Message" aria-describedby="button-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="submit" id="button-addon2">Envoyer</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script src="bootstrap.bundle.min.js"></script>
    <script>
        // Fonction pour faire défiler la barre de défilement vers le bas
        function scrollToBottom() {
            const messageList = document.getElementById('messageList');
            messageList.scrollTop = messageList.scrollHeight;
        }

        // Appeler la fonction pour faire défiler vers le bas lors du chargement initial de la page
        document.addEventListener("DOMContentLoaded", () => {
            scrollToBottom();
        });
    </script>
</body>
</html>
 
 
 
 
 
  --%>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 <%-- webSocket<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Chat Moderne</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <style>
        /* Ajoutez du CSS pour la barre de défilement */
        .message-list {
            max-height: 500px; /* Ajustez la hauteur maximale selon vos besoins */
            overflow-y: auto; /* Ajoute une barre de défilement verticale si nécessaire */
        }
    </style>
</head>
<body>
<form action="envoyerMessage.do" method="post">
    <div class="container-fluid">
        <div class="row">
            <!-- Barre en haut avec la description -->
            <nav class="navbar navbar-dark bg-dark w-100">
                <span class="navbar-brand mb-0 h1">Chat Moderne</span>
            </nav>
        </div>

        <div class="row">
            <!-- Section principale de la page -->
            <div class="col-md-8 offset-md-2 col-lg-6 offset-lg-3 mt-4">
                <!-- Zone de messages -->
                <div class="card mb-4">
                    <div class="card-body message-list"> <!-- Ajoutez la classe message-list ici -->
                        <!-- Affichage des messages -->
                        <ul class="list-group list-group-flush">
                            <c:forEach items="${model}" var="m">
                                <c:choose>
                                    <c:when test="${m.utilisateur.idUtilisateur eq UtilisateurId1}">
                                        <li class="list-group-item text-end">${m.contenu}</li>
                                    </c:when>
                                    <c:when test="${m.utilisateur.idUtilisateur eq UtilisateurId2}">
                                        <li class="list-group-item">${m.contenu}</li>
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Gérer le cas par défaut si nécessaire -->
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

                <!-- Zone de saisie de message en bas de la page -->
                <div class="fixed-bottom bg-light p-3">
                    <div class="container">
                        <div class="input-group">
                            <input type="text" name="message" class="form-control mb-5 me-2" placeholder="Écrire un message..." aria-label="Message"
                                   aria-describedby="button-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="submit" id="button-addon2">Envoyer</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!-- Bootstrap JS and dependencies -->
<script src="bootstrap.bundle.min.js"></script>
</body>
</html>
 --%>















<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Chat Moderne</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <style>
        .message-list {
            max-height: 500px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
    <form action="envoyerMessage.do" method="post">
        <div class="container-fluid">
            <div class="row">
                <nav class="navbar navbar-dark bg-dark w-100">
                    <span class="navbar-brand mb-0 h1">Chat Moderne</span>
                </nav>
            </div>

            <div class="row">
                <div class="col-md-8 offset-md-2 col-lg-6 offset-lg-3 mt-4">
                    <div class="card mb-4">
                        <div class="card-body message-list">
                            <ul class="list-group list-group-flush" id="messageList">
                                <c:forEach items="${model}" var="m">
                                    <c:choose>
                                        <c:when test="${m.utilisateur.idUtilisateur eq UtilisateurId1}">
                                            <li class="list-group-item text-end">${m.contenu}</li>
                                        </c:when>
                                        <c:when test="${m.utilisateur.idUtilisateur eq UtilisateurId2}">
                                            <li class="list-group-item">${m.contenu}</li>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Gérer le cas par défaut si nécessaire -->
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="fixed-bottom bg-light p-3">
                        <div class="container">
                            <div class="input-group">
                                <input type="text" name="message" class="form-control mb-5 me-2" placeholder="Écrire un message..." aria-label="Message"
                                    aria-describedby="button-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="submit" id="button-addon2">Envoyer</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script src="bootstrap.bundle.min.js"></script>
   <!-- ... (autres balises HTML) -->

<script>
    const conversationId = ${conversationId};
    const userId = ${UtilisateurId1};
    const socket = new WebSocket(`ws://localhost:8080/TPCHAT/websocket/${conversationId}/${userId}`);

    socket.addEventListener("message", (event) => {
        const data = event.data;
        // Traitez le message reçu en temps réel (mise à jour de l'interface utilisateur, etc.)
    });
</script>

</body>
</html>

 --%>

































