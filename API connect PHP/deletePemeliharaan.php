<?php

require("connectionMysqli.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == "POST"){

    $id = $_POST['id'];

    $query = "DELETE FROM pemeliharaan WHERE id = '$id'";
    $eksekusi = mysqli_query($connect, $query);
    $cek = mysqli_affected_rows($connect);

    if($cek > 0){
        $response['status'] = true;
        $response['message'] = "Data deleted successfully";
    }else{
        $response['status'] = false;
        $response['message'] = "Data failed to delete";
    }
}else{
    $response['status'] = false;
    $response['message'] = "NO POST DATA";
}

echo json_encode($response);
mysqli_close($connect);