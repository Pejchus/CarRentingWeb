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
                <div class="filter">
                    <div class="horni-filter">


                        <!-- formular -->
                        <link rel="stylesheet"
                              href="https://demos.jquerymobile.com/1.4.2/css/themes/default/jquery.mobile-1.4.2.min.css">
                        <!-- CO TO JE, rozmrda to vzdycky vsechno ostatni -->
                        <!-- <script src="https://demos.jquerymobile.com/1.4.2/js/jquery.js"></script> -->

                        <!-- <script src="https://demos.jquerymobile.com/1.4.2/js/jquery.mobile-1.4.2.min.js"></script> -->
                        <form class="filter-form" action="filterOffers">
                            <div class="filter-inside">
                                <input name="modelsearch" type="search">
                            </div>



                            <div class="filter-inside">
                                <select name="carcompany" id="cars">
                                    <option value="vsechny">Vsechny</option>
                                    <option value="volvo">Volvo</option>
                                    <option value="saab">Saab</option>
                                    <option value="opel">Opel</option>
                                    <option value="audi">Audi</option>
                                    <option value="BMW">BMW</option>
                                    <option value="skoda">Skoda</option>
                                    <option value="jaguar">Jaguar</option>
                                    <option value="citroen">Citroen</option>
                                    <option value="vw">Volkswagen</option>
                                    <option value="hyundai">Hyundai</option>
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


                            <input type="submit" value="Hledej!">
                        </form>
                    </div>


                    <div class="spodni-filter">

                        <div class="filter-auto-container">
                            <img class="car-icon" src="css/images/cabrio_kupe.png">


                        </div>

                        <div class="filter-auto-container">
                            <img class="car-icon" src="css/images/Combi.png">


                        </div>

                        <div class="filter-auto-container">
                            <img class="car-icon" src="css/images/dodavka.png">


                        </div>

                        <div class="filter-auto-container">
                            <img class="car-icon" src="css/images/Hatchback.png">


                        </div>

                        <div class="break">

                        </div>

                        <div class="filter-auto-container second-row-filter-auto-container">
                            <img class="car-icon" src="css/images/pickup.png">


                        </div>

                        <div class="filter-auto-container second-row-filter-auto-container sedan">
                            <img class="car-icon" src="css/images/sedan.png">


                        </div>

                        <div class="filter-auto-container second-row-filter-auto-container">
                            <img class="car-icon" src="css/images/SUV.png">


                        </div>




                    </div>




                </div>
                <h2 class="nabidka-nadpis">Nabidka vozidel:</h2>

                <div ng-model="carsHtml" id="carsHtml">

                    <div class="row">
                        <p>${offers}</p>
                        <div class="col-md-3 col-md-offset-1" >
                            <div class="cclient">
                                <a href="/DetailAuta/3/Audi-A4-Avant">
                                    <h3>Audi - <strong>A4 Avant</strong></h3>
                                    <div class="cimage">
                                        <img >
                                    </div>
                                </a>
                                <div class="cmatter">
                                    <a href="/DetailAuta/3/Audi-A4-Avant">
                                        <div class="row">
                                            <div class="col-sm-5 detailLeftTd">Počet míst: <b>5</b>
                                            </div>
                                            <div class="col-sm-7">Třída: <b>St&#x159;edn&#xED; t&#x159;&#xED;da</b>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-5 detailLeftTd">Palivo: <b>Nafta</b>
                                            </div>
                                            <div class="col-sm-7">Převodovka: <b>Automat</b>
                                            </div>

                                        </div>
                                        <div class="btn btn-price rezervovatDetailBtn">
                                            od <strong>799,- Kč / den</strong>
                                        </div>
                                    </a>

                                    <div class="button">
                                        <a href="/DetailAuta/3/Audi-A4-Avant">Prohlédnout vůz</a>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 " >
                            <div class="cclient">
                                <a href="/DetailAuta/5/Audi-A6-Allroad">
                                    <h3>Audi - <strong>A6 Allroad</strong></h3>
                                    <div class="cimage">
                                        <img>
                                    </div>
                                </a>
                                <div class="cmatter">
                                    <a href="/DetailAuta/5/Audi-A6-Allroad">
                                        <div class="row">
                                            <div class="col-sm-5 detailLeftTd">Počet míst: <b>5</b>
                                            </div>
                                            <div class="col-sm-7">Třída: <b>St&#x159;edn&#xED; t&#x159;&#xED;da</b>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-5 detailLeftTd">Palivo: <b>Nafta</b>
                                            </div>
                                            <div class="col-sm-7">Převodovka: <b>Manu&#xE1;l</b>
                                            </div>

                                        </div>
                                        <div class="btn btn-price rezervovatDetailBtn">
                                            od <strong>899,- Kč / den</strong>
                                        </div>
                                    </a>

                                    <div class="button">
                                        <a href="/DetailAuta/5/Audi-A6-Allroad">Prohlédnout vůz</a>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 " >
                            <div class="cclient">
                                <a href="/DetailAuta/51/Audi-A6-Avant">
                                    <h3>Audi - <strong>A6 Avant</strong></h3>
                                    <div class="cimage">
                                        <img >
                                    </div>
                                </a>
                                <div class="cmatter">
                                    <a href="/DetailAuta/51/Audi-A6-Avant">
                                        <div class="row">
                                            <div class="col-sm-5 detailLeftTd">Počet míst: <b>5</b>
                                            </div>
                                            <div class="col-sm-7">Třída: <b>St&#x159;edn&#xED; t&#x159;&#xED;da</b>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-5 detailLeftTd">Palivo: <b>Nafta</b>
                                            </div>
                                            <div class="col-sm-7">Převodovka: <b>Automat</b>
                                            </div>

                                        </div>
                                        <div class="btn btn-price rezervovatDetailBtn">
                                            od <strong>999,- Kč / den</strong>
                                        </div>
                                    </a>

                                    <div class="button">
                                        <a href="/DetailAuta/51/Audi-A6-Avant">Prohlédnout vůz</a>

                                    </div>
                                </div>
                            </div>
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