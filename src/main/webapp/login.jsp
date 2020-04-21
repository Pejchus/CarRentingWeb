<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prihlaseni</title>

    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!-- Potreba ve vsech .jsp kde pouzivame security:authorize tagy -->
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
</head>
<body>
    <div class="panel-body">
        <form action="doLogin" method="post">
            <fieldset>
                <legend>Please sign in, admin credentials: 'admin', password: 'admin'</legend>
                ${errorMsg}
                <input placeholder="User Name" name='username' type="text">
                <input placeholder="Password" name='password' type="password" value="">
                <input type="submit" value="Login">
            </fieldset>
        </form>
    </div>
    <a href="/"> Back to Homepage</a>
</body>
</html>