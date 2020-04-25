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
        <!-- Potreba ve vsech .jsp kde pouzivame security:authorize tagy -->
        <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    </head>
    <body>
        <%@include file="components/header.jsp" %>

        <!-- MAIN SECTION -->
        <main class="main">

            <div class="container main_description">
                <div class="car_profile">
                    <div class="car_foto">
                        ${foto}
                    </div>
                    <div class="car_information_table">
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
                    </div>
                    <div class="car_description">
                        ${description}
                    </div>
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


    </body>
</html>