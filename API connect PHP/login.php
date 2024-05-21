<?php

require("connectionPDO.php");

if($_POST){

    // Data
    $username = $_POST['username'] ?? '';
    $password = $_POST['password'] ?? '';

    $response = []; // data response (penampungan data)

    // Check username di dalam database
    $userquery = $connection->prepare("SELECT * FROM loginregis WHERE username = ?");
    $userquery->execute(array($username));
    $query = $userquery->fetch();

    if($userquery->rowCount() == 0){
        $response['status'] = false;
        $response['message']  = "Username is not registered";
    }else{
        // ambil password di database
        $passwordDB = $query['password'];

        if(strcmp(md5($password), $passwordDB) === 0){
            $response['status'] = true;
            $response['message'] = "Logged in";
            $response['data'] = [
                'user_id' => $query['id'],
                'username' => $query['username'],
                'email' => $query['email']
            ];
        }else{
            $response['status'] = false;
            $response['message'] = "Invalid password";
        }
    }

    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;
}
