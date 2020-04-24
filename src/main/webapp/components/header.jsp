<!-- HEADER -->
<header>
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

                    <security:authorize access="!hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="/login">Prihlasit</a>
                        </li>
                    </security:authorize>

                    <security:authorize access="hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="/profile">
                                <i class="fa fa-fw fa-user"></i>
                                ${userName}</a>
                            <div class="dropdown-menu">
                                <security:authorize access="hasRole('ROLE_CUSTOMER')">
                                    <a class="dropdown-item" href="/profile">Muj Ucet</a>
                                    <a class="dropdown-item" href="/doLogout">Odhlasit</a>
                                </security:authorize>

                                <security:authorize access="hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')">
                                    <a class="dropdown-item" href="/profile">Muj Ucet</a>
                                    <a class="dropdown-item" href="/adminPage">Sprava Aplikace</a>
                                    <a class="dropdown-item" href="/doLogout">Odhlasit</a>
                                </security:authorize>
                            </div>
                        </li>
                    </security:authorize>
                </ul>
            </div>
        </div>
    </nav>
</header>
<!-- END OF HEADER -->