<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pridat uzivatele</title>

        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/cf89d56701.js" crossorigin="anonymous"></script>
        <!-- Potreba ve vsech .jsp kde pouzivame security:authorize tagy -->
        <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    </head>
    <body>
        <%@include file="components/header.jsp" %>

        <!-- MAIN SECTION -->
        <main class="main">

            <div class="container main_description">
                <h1>Users V databazi</h1>
                <p>${userData}</p>
                <h3>Add User</h3>
                <form action="doAddUser">
                    <label>Username(string): </label><input type="text" name="username" required><br>
                    <label>Password(string): </label><input type="text" name="password" required><br>
                    <label>Email(string): </label><input type="text" name="email" required><br>
                    <label>Enabled(true/false): </label><input type="text" name="enabled" required><br>
                    <label>Phone(string): </label><input type="text" name="phone" required><br>
                    <label>CountryCode(string): </label><input type="text" name="countryCode" required><br>
                    <label>Firstname(string): </label><input type="text" name="firstname" required><br>
                    <label>Lastname(string): </label><input type="text" name="lastname" required><br>
                    <label>City(string): </label><input type="text" name="city" required><br>
                    <label>Street(string): </label><input type="text" name="street" required><br>
                    <label>StreetNo(string): </label><input type="text" name="streetNo" required><br>
                    <label>Authority(CUSTOMER/EMPLOYEE/ADMIN): </label><input type="text" name="authority" required><br>
                    <input type="submit" value="Pridat uzivatele"><br>
                </form>
                ${userAddedMessage}
            </div>

        </main>

        <!-- END OF MAIN SECTION -->




        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>