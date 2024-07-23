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
        .user-info {
        max-width: 300px; /* Ajoutez la largeur maximale souhaitée en pixels */
        overflow: hidden;
        text-overflow: ellipsis;
    }
    </style>
</head>
<body>
     <header>
        <!-- Ajout de l'icône pour sortir de la page à gauche -->
        <div class="text-start ms-2 mt-2">
            <a href="message.do" class="text-white"><i class="fas fa-arrow-left"></i></a>
        </div>

    </header>
    
    
    <div class="container">
       <!--  <h2 class="text-center mb-4">Add Other Users</h2> -->
        <div class="card">
            <div class="card-header">
                <span>Members</span>
                <i class="fas fa-users"></i>
            </div>
            <div class="card-body user-list">
                <ul class="list-group">
                
                
                     <c:forEach items="${model}" var="u">
					    <li class="list-group-item d-flex justify-content-between align-items-center">
					      <div class="user-info">
					          <span class="badge bg-dark">  ${u.nom} ${u.prenom} </span>
					          <span class="badge bg-success mx-auto"> ${u.etat}</span>
					           <%--  <c:if test="${u.etat eq 'online'}">
											        <span class="badge bg-success mx-auto">a </span>
											    </c:if>
					            <c:if test="${u.etat  eq 'offline'}">
											        <span class="badge bg-danger mx-auto"> a</span>
											    </c:if>
											    
							    <c:if test="${u.etat eq 'await'}">
							        <span class="badge bg-danger mx-auto"> await</span>
							    </c:if> --%>
					            
					            
					             <%-- <span class="badge bg-dark mx-auto"> ${u.lastConnectionTime}</span> --%>
					       </div>
					        
					<c:forEach var="uc" items="${u.utilisateurConversations}">
											    <c:if test="${uc.bloque == true}">
											        <span class="badge bg-danger">Bloqué</span>
											    </c:if>
											</c:forEach>
											
					        <c:if test="${(user.role eq 'admin' and user.idUtilisateur != u.idUtilisateur) || user.role eq 'mode' }">
					        <c:forEach var="uc" items="${u.utilisateurConversations}">
											    <c:if test="${uc.bloque == true}">  
					            			<a class="btn btn-outline-info btn-sm" href="unbloc.do?id=${u.idUtilisateur}">unbloc</a>
					            			
											    </c:if>
											       <c:if test="${uc.bloque == false}">  
											       <span class="badge bg-white">  </span>
					            			<a class="btn btn-outline-info btn-sm" href="bloc.do?id=${u.idUtilisateur}">bloc</a>
					            			
											    </c:if>
											</c:forEach>
					        
					        </c:if>
					
					        <c:if test="${user.role eq 'admin' and user.idUtilisateur != u.idUtilisateur }">
					        
					        <c:if test="${u.role eq 'mode'}">  
					            			  <a class="btn btn-outline-info btn-sm" href="unmode.do?id=${u.idUtilisateur}">unmode</a>
					            			
											    </c:if>
											       <c:if test="${u.role eq 'classique'}">  
											      
					            			  <a class="btn btn-outline-info btn-sm" href="mode.do?id=${u.idUtilisateur}">mod</a>
					            			
											    </c:if>
					        
					        </c:if>

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
    
    <script src="jquery.slim.min.js"></script>
    <script src="popper.min.js"></script>
    <script src="bootstrap.bundle.min.js"></script>
    <script src="all.min.js"></script>
</body>
</html>




