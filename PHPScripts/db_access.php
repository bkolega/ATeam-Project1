<?php

	$target = "db_access.config";
	if (!file_exists($target)) {
	   echo "Cannot find db_access.config";
	   exit;
	}

	$file_contents = file_get_contents("./db_access.config");
	$rows = explode(',', $file_contents);

	$c = mysqli_connect(preg_replace('/\s+/','',$rows[0]), preg_replace('/\s+/','',$rows[1]), preg_replace('/\s+/','',$rows[2]), preg_replace('/\s+/','',$rows[3]));
	if (mysqli_connect_errno($c)) {
	   echo "Failed to connect to MySQL" . mysqli_connect_error();
	   exit;
	}
?>