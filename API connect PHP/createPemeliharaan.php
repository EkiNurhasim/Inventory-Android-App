<?php

require("connectionMysqli.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == "POST"){
            
    $pelaksana = $_POST['pelaksana'];
    $kegiatan = $_POST['kegiatan'];
    $alat_bahan = $_POST['alat_bahan'];
    $nama_petugas = $_POST['nama_petugas'];
    $bendasitaan_id = $_POST['bendasitaan_id'];    
    $eksternal = $_POST["eksternal"];

    $query = "INSERT INTO pemeliharaan (pelaksana, kegiatan, alat_bahan, nama_petugas, bendasitaan_id, eksternal) 
                             VALUES ('$pelaksana', '$kegiatan','$alat_bahan', '$nama_petugas', '$bendasitaan_id', '$eksternal')";
    $eksekusi = mysqli_query($connect, $query);
    $cek = mysqli_affected_rows($connect);

    if($cek > 0){
        $response['status'] = true;
        $response['message'] = "Data added successfully";
    }else{
        $response['status'] = false;
        $response['message'] = "Failed to add data";
    }
}else{
    $response['status'] = false;
    $response['message'] = "NO POST DATA";
}

echo json_encode($response);
mysqli_close($connect);