<?php

$connection = null;

 try {

    //config
    $host = "localhost";
    $username = "root";
    $password = "";
    $dbname = "rupbasan";

    //connect
    $database = "mysql:dbname=$dbname;host=$host";
    $connection = new PDO($database, $username, $password);
    $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

 } catch (PDOException $e) {
     echo "Error | ". $e->getmessage;
     die;
 }
