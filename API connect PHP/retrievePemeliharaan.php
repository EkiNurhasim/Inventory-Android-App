<?php

require("connectionMysqli.php");

$response = array();
    
if($_SERVER['REQUEST_METHOD'] == "POST"){

    $bendasitaan_id = $_POST['bendasitaan_id'];              
 
    $query = "SELECT pemeliharaan.id, pemeliharaan.tanggal, pemeliharaan.pelaksana, pemeliharaan.kegiatan,
                    pemeliharaan.alat_bahan, pemeliharaan.nama_petugas, pemeliharaan.bendasitaan_id, pemeliharaan.eksternal
            FROM pemeliharaan INNER JOIN bendasitaan
        ON pemeliharaan.bendasitaan_id = bendasitaan.id
        WHERE bendasitaan.id = $bendasitaan_id";

    $eksekusi = mysqli_query($connect, $query);
    $cek = mysqli_affected_rows($connect);

    if($cek > 0){
        $response["status"] = true;
        $response["message"] = "Data available";
        $response["data"] = array();

        while($ret = mysqli_fetch_object($eksekusi)){
            $F["id"] = $ret->id;
            $F["tanggal"] = $ret->tanggal;
            $F["pelaksana"] = $ret->pelaksana;
            $F["kegiatan"] = $ret->kegiatan;
            $F["alat_bahan"] = $ret->alat_bahan;
            $F["nama_petugas"] = $ret->nama_petugas;
            $F["bendasitaan_id"] = $ret->bendasitaan_id;
            $F["eksternal"] = $ret->eksternal;

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