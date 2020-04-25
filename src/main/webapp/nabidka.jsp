<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tygri Pujcovna</title>

        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/cf89d56701.js" crossorigin="anonymous"></script>
        <%--  STYLY NA FORMULAR--%>
        <link rel="stylesheet"
              href="https://demos.jquerymobile.com/1.4.2/css/themes/default/jquery.mobile-1.4.2.min.css">
        <!-- Potreba ve vsech .jsp kde pouzivame security:authorize tagy -->
        <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    </head>

    <body>
        <%@include file="components/header.jsp" %>

        <!-- MAIN SECTION -->
        <main class="main">
            <div class="container main_description">
                <div class="filter">
                    <div class="horni-filter">
                        <form class="filter-form" action="filterOffers">
                            <div class="filter-inside">
                                <input name="modelsearch" type="search">
                            </div>
                            <div class="filter-inside">
                                <select name="carcompany" id="cars">
                                    <option value="vsechny">Vsechny</option>
                                    <option value="Volvo">Volvo</option>
                                    <option value="Saab">Saab</option>
                                    <option value="Opel">Opel</option>
                                    <option value="Audi">Audi</option>
                                    <option value="BMW">BMW</option>
                                    <option value="Skoda">Skoda</option>
                                    <option value="Jaguar">Jaguar</option>
                                    <option value="Citroen">Citroen</option>
                                    <option value="Vw">Volkswagen</option>
                                    <option value="Hyundai">Hyundai</option>
                                </select>
                            </div>
                            <label for="start">Zacatek pujcky:</label>
                            <div class="filter-inside">
                                <input type="date" id="start" name="tripstart" value="" min="2020-01-01" max="2021-12-31">
                            </div>
                            <label for="end">Konec pujcky:</label>
                            <div class="filter-inside">
                                <input type="date" id="end" name="tripend" value="" min="2020-01-01" max="2021-12-31">
                            </div>
                            <div data-role="rangeslider" class="filter-inside">
                                <label for="range1a">Cena za den:</label>
                                <input type="range" name="range1a" id="range1a" min="0" max="3500" value="0"
                                       data-popup-enabled="true" data-show-value="true">
                                <label for="range1b">Rangeslider:</label>
                                <input type="range" name="range1b" id="range1b" min="0" max="3500" value="3500"
                                       data-popup-enabled="true" data-show-value="true">
                            </div>
                            <div class="radio">
                                <div class="filter-auto-container">
                                    <label>
                                        <input type="radio" id="all" name="type" value="all" checked>
                                        <img class="car-icon" src="css/images/star.png">
                                    </label>
                                </div>
                                <div class="filter-auto-container">
                                    <label>
                                        <input type="radio" id="CABRIOLET" name="type" value="CABRIOLET">
                                        <img class="car-icon" src="css/images/cabrio_kupe.png">
                                    </label>
                                </div>

                                <div class="filter-auto-container">
                                    <label>
                                        <input type="radio" id="COMBI" name="type" value="COMBI">
                                        <img class="car-icon" src="css/images/Combi.png">
                                    </label>
                                </div>

                                <div class="filter-auto-container">
                                    <label>
                                        <input type="radio" id="VAN" name="type" value="VAN">
                                        <img class="car-icon" src="css/images/dodavka.png">
                                    </label>
                                </div>

                                <div class="filter-auto-container">
                                    <label>
                                        <input type="radio" id="HATCHBACK" name="type" value="HATCHBACK">
                                        <img class="car-icon" src="css/images/Hatchback.png">
                                    </label>
                                </div>

                                <div class="filter-auto-container ">
                                    <label>
                                        <input type="radio" id="PICKUP" name="type" value="PICKUP">
                                        <img class="car-icon" src="css/images/pickup.png">
                                    </label>
                                </div>
                                <div class="filter-auto-container ">
                                    <label>
                                        <input type="radio" id="SEDAN" name="type" value="SEDAN">
                                        <img class="car-icon" src="css/images/sedan.png">
                                    </label>
                                </div>
                                <div class="filter-auto-container ">
                                    <label>
                                        <input type="radio" id="SUV" name="type" value="SUV">
                                        <img class="car-icon" src="css/images/SUV.png">
                                    </label>
                                </div>
                            </div>
                            <input type="submit" value="Hledej!">
                        </form>
                    </div>
                </div>
                <h2 class="nabidka-nadpis">Nabidka vozidel:</h2>
                <div class="nabidka-container">
                    <div class="nabidka-row">
                        ${offers}
                    </div>
                </div>
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


</body>
</html>
