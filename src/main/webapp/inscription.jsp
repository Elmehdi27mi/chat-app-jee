<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Inscription Utilisateur</title>
    
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="login.css">

    <!-- Add your custom styles if needed -->
    <style>
        /* Custom styles for the sign-up page */
        body {
            font-family: "Poppins", sans-serif;
            background-color: #56baed;
            height: 100vh;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .sign-up-form {
            background: #fff;
            padding: 30px;
            width: 90%;
            max-width: 450px;
            border-radius: 10px;
            box-shadow: 0 30px 60px 0 rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        .sign-up-form h2 {
            color: #0d0d0d;
            font-size: 24px;
            font-weight: 600;
            margin-bottom: 30px;
        }

        .form-input {
            width: 100%;
            padding: 15px;
            margin-bottom: 20px;
            border: 2px solid #f6f6f6;
            border-radius: 5px;
            background-color: #f6f6f6;
            font-size: 16px;
            outline: none;
            box-sizing: border-box; /* Ensure padding is included in the width */
        }

        .form-input:focus {
            background-color: #fff;
            border-bottom: 2px solid #5fbae9;
        }

        .submit-btn {
            background-color: #56baed;
            border: none;
            color: white;
            padding: 15px;
            width: 100%;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease-in-out;
        }

        .submit-btn:hover {
            background-color: #39ace7;
        }

        #icon {
            width: 18%;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="sign-up-form">
    <!-- Icon -->
    <div class="fadeIn first">
        <img src="3.png" id="icon" alt="User Icon" />
    </div>

    <!-- Sign Up Form -->
    <h2>Sign Up</h2>
    <form action="registration.do" method="post">
        <input type="text" class="form-input" name="firstName" placeholder="First Name" required>
        <input type="text" class="form-input" name="lastName" placeholder="Login" required>
        <input type="date" class="form-input" name="dob" placeholder="Date of Birth" required>
        <input type="password" class="form-input" name="password" placeholder="Password" required>
        <button type="submit" class="submit-btn">Sign Up</button>
    </form>
    <!-- Link to Sign In page -->
    <p>Already have an account? <a href="AccPage.jsp">Sign In</a></p>
</div>

<script src="jquery.slim.min.js"></script>
<script src="bootstrap.bundle.min.js"></script>
</body>
</html>
