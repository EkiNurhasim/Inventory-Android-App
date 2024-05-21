<?php

require("connectionMysqli.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == "POST"){
    
    $noRegis = $_POST['no_regis'];
    $namaBarang = $_POST['nama_barang'];
    $tersangka = $_POST['tersangka'];
    $instansiPenitip = $_POST['instansi_penitip'];
    $tanggalMasuk = $_POST['tanggal_masuk'];
    $gudang = $_POST['gudang'];
    $loginRegisId = $_POST['loginregis_id'];     
    $jumlah_barang = $_POST['jumlah_barang'];
    $kondisi_barang = $_POST['kondisi_barang'];

    $query = "INSERT INTO bendasitaan (no_regis, nama_barang, tersangka, instansi_penitip, tanggal_masuk, gudang, loginregis_id, jumlah_barang, kondisi_barang) 
                VALUES ('$noRegis', '$namaBarang','$tersangka', '$instansiPenitip', '$tanggalMasuk' , '$gudang', '$loginRegisId', '$jumlah_barang', '$kondisi_barang')";
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