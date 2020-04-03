<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Homepage</title>
    </head>
    <body>
        <h1>nadpis</h1>
        <p>${variable}</p>
        <p>${carData}</p>
        <h3>Add Car</h3>
        <form action="addCar">
            <label>Name: </label><input type="text" name="name" required><br>
            <label>Description: </label><input type="text" name="description" required><br>
            <label>Model: </label><input type="text" name="model" required><br>
            <input type="submit"><br>
        </form>
        <p>${carAddedMessage}</p>
    </body>
</html>
