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
            <label>Model(string): </label><input type="text" name="model" required><br>
            <label>Brand(string): </label><input type="text" name="brand" required><br>
            <label>Baseprice(double): </label><input type="text" name="baseprice" required><br>
            <label>Color(string): </label><input type="text" name="color" required><br>
            <label>Power(double): </label><input type="text" name="power" required><br>
            <label>Productionyear(int): </label><input type="text" name="productionyear" required><br>
            <label>Trunkvolume(double): </label><input type="text" name="trunkvolume" required><br>
            <label>Foldingrearseats(yes/no): </label><input type="text" name="foldingrearseats" required><br>
            <label>Seats(int): </label><input type="text" name="seats" required><br>
            <label>Consumption(double): </label><input type="text" name="consumption" required><br>
            <label>Description(string): </label><input type="text" name="description" required><br>
            <input type="submit"><br>
        </form>
        <p>${carAddedMessage}</p>
    </body>
</html>
