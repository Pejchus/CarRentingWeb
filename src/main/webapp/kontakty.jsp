<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kontakty</title>

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
        <div class="kontakty-container">
            <div class="kontakty-text">
                <h3>Kontakty</h3>
                <ul>
                    <li>Info linka: 800 66 55 66</li>
                    <li>Adresa: Tygri 888, Praha</li>
                    <li>Email: tygri@pujcovna.cz</li>
                    <li>Telefon: 123 456 789</li>
                    <li>ja nevim</li>
                </ul>
            </div>
            <div class="kontakty-mapa">

                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d21811.23643349214!2d14.420611050876747!3d50.057841422507764!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x470b94673d77dd09%3A0x4c9f4f645f532866!2zVnnFoWVocmFk!5e0!3m2!1scs!2scz!4v1588853514032!5m2!1scs!2scz"
                        width="450" height="350" frameborder="0" style="border:0;" allowfullscreen=""
                        aria-hidden="false" tabindex="0"></iframe>
            </div>
        </div>
        <h2>Kontaktni formular</h2>
        <form action="mailto:shornada@fel.cvut.cz" method="post" enctype="text/plain">
            Jmeno:<br>
            <input type="text" name="name" class="active-cyan-4 mb-4"><br>
            E-mail:<br>
            <input type="text" name="mail" class="active-cyan-4 mb-4"><br>
            Komentar:<br>
            <textarea rows = "5" cols = "60" name = "komentar" class="active-cyan-4 mb-4">
            Napiste nam
         </textarea><br>
            <input type="submit" value="Odeslat">
            <input type="reset" value="Resetovat">
        </form>

    </div>
</main>

<!-- END OF MAIN SECTION -->

<!-- FOOTER -->
<footer>
    <div class="footer bottom container-fluid bg-dark kontakty-footer">
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