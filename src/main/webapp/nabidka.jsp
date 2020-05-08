<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Nabidka</title>

        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/cf89d56701.js" crossorigin="anonymous"></script>

        <%--  STYLY NA FORMULAR--%>
        <%--        KURVI TO STYLY NEPOUZIVAT --%>
        <%--        <link rel="stylesheet"--%>
        <%--              href="https://demos.jquerymobile.com/1.4.2/css/themes/default/jquery.mobile-1.4.2.min.css">--%>

        <!-- Potreba ve vsech .jsp kde pouzivame security:authorize tagy -->

        <!-- Potreba ve vsech .jsp kde pouzivame security:authorize tagy -->
        <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    </head>

    <body>
        <%@include file="components/header.jsp" %>

        <!-- MAIN SECTION -->
        <main class="main">
            <div class="container main_description">


                <h2>Vyber kategorie: </h2>
                <div class="filter">
                    <div class="horni-filter">
                        <form class="filter-form" action="filterOffers">


                            <div class="radio">
                                <div class="filter-auto-container">
                                    <label>
                                        <input type="radio" id="all" name="type" value="all" ${allchecked}>
                                        <img class="car-icon" src="css/images/star.png">
                                    </label>
                                </div>
                                <div class="filter-auto-container">
                                    <label>
                                        <input type="radio" id="CABRIOLET" name="type" value="CABRIOLET" ${CABRIOLETchecked}>
                                        <img class="car-icon" src="css/images/cabrio_kupe.png">
                                    </label>
                                </div>

                                <div class="filter-auto-container">
                                    <label>
                                        <input type="radio" id="COMBI" name="type" value="COMBI" ${COMBIchecked}>
                                        <img class="car-icon" src="css/images/Combi.png">
                                    </label>
                                </div>

                                <div class="filter-auto-container">
                                    <label>
                                        <input type="radio" id="VAN" name="type" value="VAN" ${VANchecked}>
                                        <img class="car-icon" src="css/images/dodavka.png">
                                    </label>
                                </div>

                                <div class="filter-auto-container">
                                    <label>
                                        <input type="radio" id="HATCHBACK" name="type" value="HATCHBACK" ${HATCHBACKchecked}>
                                        <img class="car-icon" src="css/images/Hatchback.png">
                                    </label>
                                </div>

                                <div class="filter-auto-container ">
                                    <label>
                                        <input type="radio" id="PICKUP" name="type" value="PICKUP" ${PICKUPchecked}>
                                        <img class="car-icon" src="css/images/pickup.png">
                                    </label>
                                </div>
                                <div class="filter-auto-container ">
                                    <label>
                                        <input type="radio" id="SEDAN" name="type" value="SEDAN" ${SEDANchecked}>
                                        <img class="car-icon" src="css/images/sedan.png">
                                    </label>
                                </div>
                                <div class="filter-auto-container ">
                                    <label>
                                        <input type="radio" id="SUV" name="type" value="SUV" ${SUVchecked}>
                                        <img class="car-icon" src="css/images/SUV.png">
                                    </label>
                                </div>
                            </div>

                            <div class="filter-top">

                                <!-- Search form -->
                                <div class="active-cyan-4 mb-4">
                                    <input name="modelsearch" class="form-control" type="text" placeholder="Search" aria-label="Search" value="${modelsearchValue}">
                                </div>


                                <select name="carcompany" class="form-control">
                                    <option value="vsechny" ${vsechnychecked}>Vsechny</option>
                                    <option value="Volvo" ${Volvochecked}>Volvo</option>
                                    <option value="Saab" ${Saabchecked}>Saab</option>
                                    <option value="Opel" ${Opelchecked}>Opel</option>
                                    <option value="Audi" ${Audichecked}>Audi</option>
                                    <option value="BMW" ${BMWchecked}>BMW</option>
                                    <option value="Skoda" ${Skodachecked}>Skoda</option>
                                    <option value="Jaguar" ${Jaguarchecked}>Jaguar</option>
                                    <option value="Citroen" ${Citroenchecked}>Citroen</option>
                                    <option value="Vw" ${Vwchecked}>Volkswagen</option>
                                    <option value="Hyundai" ${Hyundaichecked}>Hyundai</option>
                                </select>
                            </div>

                            <div class="form-calendar-container">
                                <div class="form-calendar">

                                    <label for="start"><h3>Zacatek pujcky:</h3></label>

                                    <input type="date" id="start" name="tripstart" min="${minDate}" max="2021-12-31" value="${tripstartValue}">
                                </div>
                                <div class="form-calendar">

                                    <label for="end"><h3>Konec pujcky:</h3></label>
                                    <input type="date" id="end" name="tripend" min="${minDate}" max="2021-12-31" value="${tripendValue}">
                                </div>
                            </div>


                            <h3>Cena za den:</h3>


                            <div class="slider">
                                <div class="slider_lower_value_span">
                                    <span>Od</span>
                                    <span id="lower-value" disabled></span>
                                </div>

                                <div class="multi-range">
                                    <input name="range1a" type="range" min="${minSliderVal}" max="${maxSliderVal}" value="${range1aValue}" id="lower">
                                    <input name="range1b" type="range" min="${minSliderVal}" max="${maxSliderVal}" value="${range1bValue}" id="upper">
                                </div>

                                <div class="slider_higher_value_span">
                                    <span>Do</span>
                                    <span id="upper-value" disabled></span>
                                </div>
                            </div>

                            <input type="hidden" name="pagestart" value="0">
                            <input type="hidden" name="prefferedCars" value="false">
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
                <fieldset ${pagingNext}>
                    <form action="filterOffers" class="paging">
                        <input type="hidden" name="type" value="${typeValue}">
                        <input type="hidden" name="modelsearch" value="${modelsearchValue}">
                        <input type="hidden" name="carcompany" value="${carcompanyValue}">
                        <input type="hidden" name="tripstart" value="${tripstartValue}">
                        <input type="hidden" name="tripend" value="${tripendValue}">
                        <input type="hidden" name="range1a" value="${range1aValue}">
                        <input type="hidden" name="range1b" value="${range1bValue}">
                        <input type="hidden" name="pagestart" value="${nextpagestart}">
                        <input type="hidden" name="prefferedCars" value="false">
                        <input type="submit" value=">">
                        <span>Dalsi</span>
                    </form>

                </fieldset>
                <fieldset ${pagingPrevious}>
                    <form action="filterOffers" class="paging">
                        <input type="hidden" name="type" value="${typeValue}">
                        <input type="hidden" name="modelsearch" value="${modelsearchValue}">
                        <input type="hidden" name="carcompany" value="${carcompanyValue}">
                        <input type="hidden" name="tripstart" value="${tripstartValue}">
                        <input type="hidden" name="tripend" value="${tripendValue}">
                        <input type="hidden" name="range1a" value="${range1aValue}">
                        <input type="hidden" name="range1b" value="${range1bValue}">
                        <input type="hidden" name="pagestart" value="${previouspagestart}">
                        <input type="hidden" name="prefferedCars" value="false">
                        <input type="submit" value="<">
                        <span>Predchozi</span>
                    </form>

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

    <script src="js/slider.js"></script>

    <%--CO TO MA BEJT JAKO--%>
    <%--    <script>--%>
    <%--        $(document).ready(function () {--%>
    <%--            $('.mdb-select').materialSelect();--%>
    <%--        });--%>
    <%--    </script>--%>

</body>
</html>
