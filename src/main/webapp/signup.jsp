<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Prihlasit</title>

        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="css/loginForm.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/cf89d56701.js" crossorigin="anonymous"></script>
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

        <!-- MAIN -->
        <main class="formBackground">
            <div class="container">
                <div class="d-flex justify-content-center h-100">
                    <div class="card-signup">
                        <div class="card-header">
                            <h3>Vytvorit ucet</h3>
                        </div>
                        <div class="card-body">
                            <form action="doSignUp" method="get">
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name="username" placeholder="Uzivatelske jmeno" required>
                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name="firstname" placeholder="Jmeno" required>
                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name="lastname" placeholder="Prijmeni" required>
                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-city"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name='city' placeholder="Mesto" required>
                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-road"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name='street' placeholder="Ulice" required>
                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-road"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name='streetNo' placeholder="Cislo popisne" required>
                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name="email" placeholder="E-mail" required>
                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-phone-square-alt"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name="phone" placeholder="Telefonni cislo" required>
                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-globe"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name="countryCode" placeholder="Mezinarodni predvolba" required>
                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-key"></i></span>
                                    </div>
                                    <input type="password" class="form-control" name='password' placeholder="Heslo" required>
                                </div>
                                <div class="row align-items-center remember">
                                    <input type="checkbox" required>Souhlasim s obchodnim podminkami
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Zaregistrovat" class="float-right signup_btn">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <!-- END OF MAIN -->
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