<?php

require("connectionPDO.php");

if($_POST){

    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);
    $email = filter_input(INPUT_POST, 'email', FILTER_SANITIZE_STRING);
    $password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING);
    $response = [];

    // check uesrname di dalam database
    $userquery = $connection->prepare("SELECT * FROM loginregis WHERE username = ?");
    $userquery->execute(array($username));

    // check username apakah sudah terdaftar atau tidak
    if($userquery->rowCount() != 0){
        $response['status'] = false;
        $response['message']  = "Username already exists please try another username";
    }else{
        $insertAccount = "INSERT INTO loginregis (username, email, password) VALUES (:username, :email, :password)";
        $statement = $connection->prepare($insertAccount);

        try {
            // eksekusi statement database
            $statement->execute([
                ':username' => $username, 
                ':email' => $email,
                ':password' => md5($password)
            ]);

            // beri response 
            $response['status'] = true;
            $response['message'] = 'Registered';
            $response['data'] = [
                'username' => $username,
                'email' => $email
            ];

        } catch (Exception $e) {
            die($e->getMessage());
        }
    }

    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;
}