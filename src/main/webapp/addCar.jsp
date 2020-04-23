<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pridat auto</title>

        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <!-- Potreba ve vsech .jsp kde pouzivame security:authorize tagy -->
        <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    </head>
    <body>
        <!-- HEADER -->
        <header>
            <div class="info_bar">
                <security:authorize access="hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')">
                    <p>${userName}</p>
                </security:authorize>
            </div>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/"><img class="logo_icon" src="css/images/logo_icon.png"></a>
                    <h1 class="header_title">Tygri Pujcovna</h1>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarResponsive">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="/">Domu</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/offers">Nabidka aut</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Kontakty</a>
                            </li>
                            <security:authorize access="hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')">
                                <li class="nav-item">
                                    <a class="nav-link" href="/profile">Muj Ucet</a>
                                </li>
                            </security:authorize>
                            <li class="nav-item">
                                <security:authorize access="!hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')">
                                    <a class="nav-link" href="/login">Prihlasit</a>
                                </security:authorize>
                                <security:authorize access="hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')">
                                    <a class="nav-link" href="/doLogout">Odhlasit</a>
                                </security:authorize>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <!-- END OF HEADER -->

        <!-- MAIN SECTION -->
        <main class="main">
            <div class="container main_description">
                <h1>Auta V databazi</h1>
                ${carData}
                <security:authorize access="hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')">
                    <form method="POST" enctype="multipart/form-data" action="doAddCar">
                        <label>Model(string): </label><input type="text" name="model" required><br>
                        <label>Brand(string): </label><input type="text" name="brand" required><br>
                        <label>Baseprice(double): </label><input type="text" name="baseprice" required><br>
                        <label>Color(string): </label><input type="text" name="color" required><br>
                        <label>Power(double): </label><input type="text" name="power" required><br>
                        <label>Productionyear(int): </label><input type="text" name="productionyear" required><br>
                        <label>Trunkvolume(double): </label><input type="text" name="trunkvolume" required><br>
                        <label>Foldingrearseats(yes/no): </label><input type="text" name="foldingrearseats" required><br>
                        <label>Seats(int): </label><input type="text" name="seats" required><br>
                        <label>Consumption(double): </label><input type="text" name="consumption" required><br>
                        <label>Description(string): </label><input type="text" name="description" required><br>
                        <label>Car photo: </label><input type="file" name="photo" required><br>
                        <label>Car category(SEDAN/COMBI/SUV/HATCHBACK/CABRIOLET/PICKUP/VAN): </label><input type="string" name="carcategory" required><br>
                        <input type="submit" value="Pridat auto"><br>
                    </form>
                </security:authorize>
            </div>
        </main>

        <!-- END OF MAIN SECTION -->



        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>