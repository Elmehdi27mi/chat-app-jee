<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Mbh Chat</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        .welcome-container {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: #3498db;
            color: #fff;
            text-align: center;
            display: flex;
            align-items: center;
            justify-content: center;
            opacity: 1;
            transition: opacity 1s ease-in-out;
            z-index: 2; /* Set a higher z-index to make sure it's on top */
        }

        .welcome-message {
            max-width: 80%;
        }

        .chat-container {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            display: none; /* Initially hide the chat container */
            z-index: 1; /* Set a lower z-index */
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .header {
            background-color: #333;
            color: #fff;
            padding: 15px;
            margin: 0;
            position: fixed;
            width: 100%; /* Make the header fill the width */
            top: 0;
            text-align: center; /* Center the text in the header */
        }

        .logo {
            width: 100px; /* Adjust the size as needed */
            height: 100px; /* Adjust the size as needed */
            margin-top: 20px;
        }

        .user-info {
            margin-top: 20px;
        }

        h1, h2 {
            margin: 0;
        }

        .chat-link {
            display: inline-block;
            margin-top: 20px;
            padding: 15px 30px;
            font-size: 18px;
            color: #fff;
            background-color: #3498db;
            text-decoration: none;
            border-radius: 5px;
        }

        .footer {
            background-color: #333;
            color: #fff;
            padding: 15px;
            position: fixed;
            width: 100%;
            bottom: 0;
            text-align: center; /* Center text in the footer */
        }
    </style>
    <script>
        // Hide the welcome message and show the chat container after 3 seconds
        window.onload = function() {
            var welcomeContainer = document.getElementById('welcomeContainer');
            var chatContainer = document.querySelector('.chat-container');

            setTimeout(function() {
                if (welcomeContainer && chatContainer) {
                    welcomeContainer.style.opacity = '0';
                    chatContainer.style.display = 'flex';
                }
            }, 3000);
        };
    </script>
</head>
<body>
    <div id="welcomeContainer" class="welcome-container">
        <div class="welcome-message">
            <h1>Welcome to Mbh Chat</h1>
            <p>Your go-to platform for seamless communication.</p>
        </div>
    </div>

    <div class="chat-container">
        <div class="header">
            <h1>Mbh Chat</h1>
        </div>

        <div class="user-info">
            <img src="1.png" alt="Application Logo" class="logo">
            <h2>${firstname} ${lastname}</h2>
        </div>

        <!-- <a href="AccPage.jsp" class="chat-link">Start Chatting</a>
 -->
 <button id="startChatting" class="chat-link">Start Chatting</button>

<script>
    document.getElementById('startChatting').addEventListener('click', function() {
        window.location.href = 'AccPage.jsp';
    });
</script>
 
        <div class="footer">
            <p>Connect with people around the world. Mbh Chat is your go-to platform for seamless communication.</p>
        </div>
    </div>
</body>
</html>