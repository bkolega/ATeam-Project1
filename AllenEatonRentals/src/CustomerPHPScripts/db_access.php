<?php
	$file = file_get_contents("db_access.json");
	$sql_json = json_decode($file, true);
	
	$c = mysqli_connect($sql_json["host"], $sql_json["user"], $sql_json["password"], $sql_json["db"]);

	if (mysqli_connect_errno($c)) {
	   echo "Failed to connect to MySQL" . mysqli_connect_error();
	   exit;
	}
?>