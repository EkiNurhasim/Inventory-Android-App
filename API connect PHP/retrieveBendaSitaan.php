<?php

require("connectionMysqli.php");

$response = array();
    
if($_SERVER['REQUEST_METHOD'] == "POST"){

    $loginregis_id = $_POST['loginregis_id'];       
 
    $query = "SELECT bendasitaan.id, bendasitaan.no_regis, bendasitaan.nama_barang, bendasitaan.tersangka, 
    bendasitaan.instansi_penitip, bendasitaan.tanggal_masuk, bendasitaan.gudang, bendasitaan.loginregis_id, 
    bendasitaan.created_at, bendasitaan.jumlah_barang, bendasitaan.kondisi_barang  FROM bendasitaan INNER JOIN loginregis 
        ON bendasitaan.loginregis_id = loginregis.id 
        WHERE loginregis.id = $loginregis_id";

    $eksekusi = mysqli_query($connect, $query);
    $cek = mysqli_affected_rows($connect);

    if($cek > 0){
        $response["status"] = true;
        $response["message"] = "Data available";
        $response["data"] = array();

        while($ret = mysqli_fetch_object($eksekusi)){
            $F["id"] = $ret->id;
            $F["no_regis"] = $ret->no_regis;
            $F["nama_barang"] = $ret->nama_barang;
            $F["tersangka"] = $ret->tersangka;
            $F["instansi_penitip"] = $ret->instansi_penitip;
            $F["tanggal_masuk"] = $ret->tanggal_masuk;
            $F["gudang"] = $ret->gudang;
            $F["loginregis_id"] = $ret->loginregis_id;
            $F["created_at"] = $ret->created_at;
            $F["jumlah_barang"] = $ret->jumlah_barang;
            $F["kondisi_barang"] = $ret->kondisi_barang;

            array_push($response["data"], $F);        
        }
    }else{
        $response["status"] = false;
        $response["message"] = "Data not available";
    }
}else{
    $response["status"] = false;
    $response["message"] = "POST DATA NULL";
}
echo json_encode($response, JSON_PRETTY_PRINT);
mysqli_close($connect);