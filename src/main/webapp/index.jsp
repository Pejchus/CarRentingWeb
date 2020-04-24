<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tygri Pujcovna</title>

        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/cf89d56701.js" crossorigin="anonymous"></script>
        <!-- Potreba ve vsech .jsp kde pouzivame security:authorize tagy -->
        <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    </head>
    <body>
        <%@include file="components/header.jsp" %>

        <!-- MAIN SECTION -->
        <main class="main">
            <img class="index-car-image" src="css/images/index_car.jpg">
            <div class="container main_description">
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec rutrum dui eget dignissim posuere. Integer tempor enim id enim mattis dapibus. Vestibulum non feugiat orci, sit amet congue lacus. Aenean at purus quis purus laoreet egestas. Praesent felis urna, rutrum eu molestie ac, lobortis quis ex. Suspendisse pellentesque sagittis tellus. Nullam non nisi a lacus vehicula sollicitudin. Donec sed consectetur sapien. Maecenas egestas ullamcorper ex, id auctor tellus molestie quis. Suspendisse sollicitudin, diam sagittis luctus volutpat, nisl neque posuere mi, ut pulvinar risus eros at libero. Suspendisse suscipit sollicitudin enim ac tincidunt. Nunc viverra imperdiet massa sed porttitor. Mauris eu diam lobortis, viverra eros in, consectetur risus. Curabitur et posuere risus. Mauris auctor varius placerat. Mauris et libero facilisis, cursus enim vitae, consectetur dolor.
                    Sed gravida enim a pretium convallis. Vivamus vel nunc non risus fringilla suscipit vitae eget odio. Pellentesque vel auctor massa, ac dignissim dolor. In ac eros dui. Nullam scelerisque odio suscipit malesuada elementum. Morbi sed odio tortor. Curabitur tincidunt malesuada purus nec semper. Quisque consequat augue ac justo iaculis, ac lacinia purus tristique. Nam et leo sagittis, ornare purus at, vestibulum lectus. Phasellus et sapien nec elit volutpat efficitur. Sed varius in tellus in bibendum. Integer tincidunt, justo at sollicitudin efficitur, sapien odio eleifend lacus, rhoncus auctor nisi velit in est. Proin augue lorem, viverra at porttitor et, consequat vel neque. Aliquam dictum lectus eu magna scelerisque, id lobortis tellus faucibus.</p>
            </div>
        </main>

        <!-- END OF MAIN SECTION -->

        <!-- FOOTER -->
        <footer>
            <div class="footer bottom container-fluid bg-dark">
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