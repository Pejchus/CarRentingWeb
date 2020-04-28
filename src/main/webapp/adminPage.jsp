<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sprava Aplikace</title>

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

                <security:authorize access="hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')">
                    <h3>Add Car</h3>
                    <form method="POST" enctype="multipart/form-data" action="doAddCar">
                        <label>Model(string): </label><input type="text" name="model" required><br>
                        <label>Brand(string): </label><input type="text" name="brand" required><br>
                        <label>Baseprice(double): </label><input type="text" name="baseprice" required><br>
                        <label>Color(string): </label><input type="text" name="color" required><br>
                        <label>Power(double): </label><input type="text" name="power" required><br>
                        <label>Productionyear(int): </label><input type="text" name="productionyear" required><br>
                        <label>Trunkvolume(double): </label><input type="text" name="trunkvolume" required><br>
                        <label>Seats(int): </label><input type="text" name="seats" required><br>
                        <label>Consumption(double): </label><input type="text" name="consumption" required><br>
                        <label>Transmissiontype(AUTOMATIC/MANUAL): </label><input type="text" name="transmissiontype" required><br>
                        <label>Enginetype(GAS/DIESEL/ELECTRIC): </label><input type="text" name="enginetype" required><br>
                        <label>Description(string): </label><input type="text" name="description" required><br>
                        <label>Car photo: </label><input type="file" name="photo"><br>
                        <label>Car category(SEDAN/COMBI/SUV/HATCHBACK/CABRIOLET/PICKUP/VAN): </label><input type="string" name="carcategory" required><br>
                        <input type="submit"><br>
                    </form>
                    ${carAddedMessage}
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
                        <input type="submit"><br>
                    </form>
                    ${changeMessage}
                </security:authorize>
                <table class="accounts">
                    <tr>
                        <td><h1>Auta V databazi</h1>
                            ${carData}</td>
                        <td><h1>Users V databazi</h1>
                            ${userData}</td>
                    </tr>
                </table>
            </div>
        </main>



        <!-- END OF MAIN SECTION -->

        <!-- FOOTER -->
        <footer>
            <div class="footer bottom container-fluid bg-dark">
                <div class="footer_content">
                    <h3 class="footer_title">Kontakty</h3>
                    <table class="footer_table">
                        <tr>
                            <td>Info linka: 800 66 55 66</td>
                        </tr>
                        <tr>
                            <td>Adresa: Tygri 888, Praha</td>
                        </tr>
                    </table>
                </div>
            </div>
        </footer>
        <!-- END OF FOOTER -->

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>