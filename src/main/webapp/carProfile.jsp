<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Car</title>

        <link rel="stylesheet" href="css/styles.css">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/cf89d56701.js" crossorigin="anonymous"></script>
        <script src="js/libraries/jquery-3.5.0.min.js"></script>
        <link rel="stylesheet" href="js/libraries/jquery-ui-1.12.1/jquery-ui.min.css">
        <script src="js/libraries/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
        <script src="js/libraries/jquery-ui-1.12.1/jquery-ui.min.js"></script>

        <!-- Potreba ve vsech .jsp kde pouzivame security:authorize tagy -->
        <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    </head>
    <body>
        <%@include file="components/header.jsp" %>

        <!-- MAIN SECTION -->
        <main class="main">

            <div class="container main_description">
                <div class="car-profile-container">

                    <div class="car-profile">
                        <table>
                            <tr>
                                <td><h2>${brand} ${name}</h2></td>
                            </tr>
                            <tr>
                                <td>Rok vyroby</td>
                                <td>${productionyear}</td>
                            </tr>
                            <tr>
                                <td>Pocet mist</td>
                                <td>${seats}</td>
                            </tr>
                            <tr>
                                <td>Spotreba na 1L/100km</td>
                                <td>${consumption}</td>
                            </tr>
                            <tr>
                                <td>Spotreba na 1L/100km</td>
                                <td>${consumption}</td>
                            </tr>
                            <tr>
                                <td>Vykon</td>
                                <td>${power}</td>
                            </tr>
                            <div class="car_price">
                                <tr>
                                    <td>Cena za den</td>
                                    <td id="priceDay">${baseprice}</td>
                                </tr>
                            </div>
                        </table>

                        <form action="makeOrder">
                            <label for="tripstart">Zacatek pujcky:</label>
                            <div class="filter-inside">
                                <%--                        poslat datumy kdy auto neni volny --%>
                                <label id="startLabel" hidden>${dates}</label>
                                <input type="text" id="tripstart" name="tripstart" placeholder="Vyberte datum" readonly required>

                            </div>
                            <label for="tripend">Konec pujcky:</label>
                            <div class="filter-inside end-calendar">
                                <input type="text" id="tripend" name="tripend" placeholder="Vyberte datum" readonly required>
                                <span id="tripendMsg"></span>
                            </div>
                            <security:authorize access="hasAnyRole('ROLE_CUSTOMER')">
                                <input type="hidden"  id="username" name="username" value ="" placeholder="Zadejte username" >
                            </security:authorize>
                            <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')">
                                <label for="username">Pro uzivatele:</label>
                                <input type="text" class="form-control" id="username" name="username" value ="" placeholder="Zadejte username" >
                            </security:authorize>
                            <input type="hidden" name="carId" value="${carId}">
                            <span id="totalPrice"></span>
                            <input type="submit" value="Rezervovat">
                        </form>
                        ${createOrderMsg}
                    </div>
                    <div class="car-profile">
                        ${foto}
                    </div>






                </div>
                <div class="car-description">
                    ${description}
                </div>
                <div class="car-profile-admin">
                    <fieldset ${disabled}>
                        <form method="POST" enctype="multipart/form-data" action="setCarProfilePhoto">
                            <label>Zmenit fotku auta: </label><input type="file" name="photo" required><br>
                            <input type="hidden" name="carid" value="${carId}">
                            <input type="submit" value="Vybrat foto"><br>
                        </form>
                        ${carProfilePhotoChangeMsg}

                        <h4>Objednavky</h4>
                        <table class="table table-condensed table-hover" id="ordersHistoryTable">
                            <thead>
                            <tr>
                                <th>Vytvoreno</th>
                                <th>Vozidlo</th>
                                <th>Od</th>
                                <th>Do</th>
                                <th>Cena</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${orders}" var="order">
                                <tr>
                                    <td><c:out value="${order.createDate}"/></td>
                                    <td><c:out value="${order.car.brand} ${order.car.model}"/></td>
                                    <td><c:out value="${order.begindate}"/></td>
                                    <td><c:out value="${order.enddate}"/></td>
                                    <td><c:out value="${order.price}"/></td>
                                    <td>
                                        <a class="cancelOrder" href="/deleteCarOrder?id=${order.id}">Odstranit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <fieldset ${disableDisableCar}>
                            <a href="disableCar?id=${carId}">Zablokovat auto</a>
                        </fieldset>
                        <fieldset ${disableEnableCar}>
                            <a href="enableCar?id=${carId}">Odblokovat auto</a>
                        </fieldset>

                        <fieldset ${disableAddToFrontPageCar}>
                            <a href="addCarToFrontPage?id=${carId}">Pridat auto na titulni stranku</a>
                        </fieldset>
                        <fieldset ${disableRemoveFromFrontPageCar}>
                            <a href="removeCarFromFrontPage?id=${carId}">Odebrat auto z titulni stranky</a>
                        </fieldset>

                        <a href="deleteCar?id=${carId}">Smazat auto (nezrusi jeho objednavky)</a>
                        ${carChangeMsg}
                    </fieldset>
                </div>
            </div>

        </main>

        <!-- END OF MAIN SECTION -->

        <!-- FOOTER -->
        <footer>
            <div class="container-fluid bg-dark">
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

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
                integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
                integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js" type="text/javascript"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="Stylesheet" type="text/css" />

        <script src="js/calendar.js"></script>
        <script src="js/ordersHistory.js"></script>
    </body>
</html>