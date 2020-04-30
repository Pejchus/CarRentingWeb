<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profil A</title>

        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/cf89d56701.js" crossorigin="anonymous"></script>
        <!-- Potreba ve vsech .jsp kde pouzivame security:authorize tagy -->
        <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    </head>
    <body>
        <%@include file="components/header.jsp" %>

        <!-- MAIN SECTION -->
        <main class="main">

            <div class="container main_description">
                <div class="profile">
                    <table>
                        <tr>
                            <td>Jmeno a Prijmeni</td>
                            <td>${firstnameA} ${lastnameA}</td>
                        </tr>
                        <tr>
                            <td>Telefon</td>
                            <td>${phoneA}</td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td>${emailA}</td>
                        </tr>
                        <tr>
                            <td>Adresa</td>
                            <td>${StateA} ${cityA} ${streetA} ${streetnoA}</td>
                        </tr>
                        <tr>
                            <td>Profile Photo</td>
                            <td>${profilePhotoA}</td>
                        </tr>
                    </table>
                </div>

                <div class="history">
                    <h4>Historie objednavek</h4>
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
                </div>

                <fieldset ${disabled}>
                    <form method="POST" enctype="multipart/form-data" action="setProfilePhoto">
                        <label>Zmenit profilovou fotku: </label><input type="file" name="photo" required><br>
                        <input type="submit" value="Vybrat foto"><br>
                    </form>
                    ${profilePhotoChangeMsg}
                </fieldset>
                <fieldset ${disabledAdminButtons}>
                    <fieldset ${disableDisableUser}>
                        <a href="disableUser?id=${userId}">Zablokovat uzivatele</a>
                    </fieldset>
                    <fieldset ${disableEnableUser}>
                        <a href="enableUser?id=${userId}">Odblokovat uzivatele</a>
                    </fieldset>
                    <a href="deleteUser?id=${userId}">Smazat uzivatele (nezrusi jeho objednavky)</a>
                    ${userEnabledMsg}
                </fieldset>
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

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

        <script src="js/ordersHistory.js"></script>
    </body>
</html>