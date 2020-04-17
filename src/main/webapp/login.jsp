<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Login Page</title>
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