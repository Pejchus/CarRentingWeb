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
                                    <td>${baseprice}</td>
                                </tr>
                            </div>
                        </table>


<%--                        <form action="makeOrder">--%>
<%--                            <label for="start">Zacatek pujcky:</label>--%>
<%--                            <div class="filter-inside">--%>
<%--                                <input type="date" id="start" name="tripstart" value="" min="${minDate}" max="2021-12-31">--%>
<%--                            </div>--%>
<%--                            <label for="end">Konec pujcky:</label>--%>
<%--                            <div class="filter-inside">--%>
<%--                                <input type="date" id="end" name="tripend" value="" min="${minDate}" max="2021-12-31">--%>
<%--                            </div>--%>
<%--                            <input type="hidden" name="carId" value="${carId}">--%>
<%--                            <input type="submit" value="objednat na zvolene datum">--%>
<%--                        </form>--%>
                        <form action="makeOrder">
                            <label for="tripstart">Zacatek pujcky:</label>
                            <div class="filter-inside">
                                <%--                        poslat datumy kdy auto neni volny --%>
                                <label id="startLabel" hidden>${dates}</label>
                                <input type="text" id="tripstart" name="tripstart" placeholder="Vyberte datum" readonly required>

                            </div>
                            <label for="tripend">Konec pujcky:</label>
                            <div class="filter-inside">
                                <input type="text" id="tripend" name="tripend" placeholder="Vyberte datum" readonly required>
                            </div>
                            <input type="hidden" name="carId" value="${carId}">
                            <input type="submit" value="Rezervovat">
                        </form>
                    </div>
                    <div class="car-profile">
                        ${foto}
                    </div>



                    ${createOrderMsg}

                    <fieldset ${disabled}>
                        <form method="POST" enctype="multipart/form-data" action="setCarProfilePhoto">
                            <label>Zmenit fotku auta: </label><input type="file" name="photo" required><br>
                            <input type="hidden" name="carid" value="${carId}">
                            <input type="submit" value="Vybrat foto"><br>
                        </form>
                        ${carProfilePhotoChangeMsg}

                        <td>Objednavky kde figuruje toto auto</td>
                        <br>
                        <td>${orders}</td>

                        <fieldset ${disableDisableCar}>
                            <a href="disableCar?id=${carId}">Zablokovat auto</a>
                        </fieldset>
                        <fieldset ${disableEnableCar}>
                            <a href="enableCar?id=${carId}">Odblokovat auto</a>
                        </fieldset>
                        <a href="deleteCar?id=${carId}">Smazat auto (nezrusi jeho objednavky)</a>
                        ${carEnabledMsg}
                    </fieldset>
                </div>
                <div class="car-description">
                    ${description}
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

    </body>
</html>