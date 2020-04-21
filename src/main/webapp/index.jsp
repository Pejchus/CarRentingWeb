<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tygri Pujcovna</title>

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
            <a class="navbar-brand" href="/index.jsp"><img class="logo_icon" src="css/images/logo_icon.png"></a>
            <h1 class="header_title">Tygri Pujcovna</h1>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/index.jsp">Domu</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Nabidka aut</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Kontakty</a>
                    </li>
                    <security:authorize access="hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="/profile.jsp">Muj Ucet</a>
                        </li>
                    </security:authorize>
                    <li class="nav-item">
                        <security:authorize access="!hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')">
                            <a class="nav-link" href="login.jsp">Prihlasit</a>
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
    <img class="index-car-image" src="css/images/index_car.jpg">
    <div class="container main_description">
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec rutrum dui eget dignissim posuere. Integer tempor enim id enim mattis dapibus. Vestibulum non feugiat orci, sit amet congue lacus. Aenean at purus quis purus laoreet egestas. Praesent felis urna, rutrum eu molestie ac, lobortis quis ex. Suspendisse pellentesque sagittis tellus. Nullam non nisi a lacus vehicula sollicitudin. Donec sed consectetur sapien. Maecenas egestas ullamcorper ex, id auctor tellus molestie quis. Suspendisse sollicitudin, diam sagittis luctus volutpat, nisl neque posuere mi, ut pulvinar risus eros at libero. Suspendisse suscipit sollicitudin enim ac tincidunt. Nunc viverra imperdiet massa sed porttitor. Mauris eu diam lobortis, viverra eros in, consectetur risus. Curabitur et posuere risus. Mauris auctor varius placerat. Mauris et libero facilisis, cursus enim vitae, consectetur dolor.
            Sed gravida enim a pretium convallis. Vivamus vel nunc non risus fringilla suscipit vitae eget odio. Pellentesque vel auctor massa, ac dignissim dolor. In ac eros dui. Nullam scelerisque odio suscipit malesuada elementum. Morbi sed odio tortor. Curabitur tincidunt malesuada purus nec semper. Quisque consequat augue ac justo iaculis, ac lacinia purus tristique. Nam et leo sagittis, ornare purus at, vestibulum lectus. Phasellus et sapien nec elit volutpat efficitur. Sed varius in tellus in bibendum. Integer tincidunt, justo at sollicitudin efficitur, sapien odio eleifend lacus, rhoncus auctor nisi velit in est. Proin augue lorem, viverra at porttitor et, consequat vel neque. Aliquam dictum lectus eu magna scelerisque, id lobortis tellus faucibus.</p>
    </div>
    <h1>Auta V databazi</h1>
    ${carData}

    <security:authorize access="hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')">
        <h3>Add Car</h3>
        <form method="POST" enctype="multipart/form-data" action="addCar">
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
            <input type="submit"><br>
        </form>
        ${carAddedMessage}
    </security:authorize>

    <h1>Users V databazi</h1>
    <p>${userData}</p>

    <h3>Add User</h3>
    <form action="addUser">
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
    ${userAddedMessage}
</main>

<!-- END OF MAIN SECTION -->

<!-- FOOTER -->
<footer>
    <div class="footer container-fluid bg-dark">
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