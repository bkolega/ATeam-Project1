<?php
	ini_set("display_errors", "1");
	error_reporting(E_ALL);
	include 'db_access.php';
	$response["success"] = 1;
	$response["message"] = "";
	
	/* not really necessary, since we have checks
	 * in the java code
	 */
	if (empty ($_POST['username'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Cannot find customer email.\n";
	}  
	if (empty ($_POST['carid'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Cannot find car ID.\n";
	}
	if (empty ($_POST['gps'])) {
	   $gps = 0;
	}
	else {
	     $gps = $_POST['gps'];
	}
	if (empty ($_POST['childseat'])) {	   
	   $childseat = 0;
	}	
	else {
	     $childseat = $_POST['childseat'];
	}
	if (empty ($_POST['ktag'])) {
	   $ktag = 0;
	}
	else {
	     $ktag = $_POST['ktag'];
	}
	if (empty ($_POST['assistance'])) {
	   $assistance = 0;
	}
	else {
	     $assistance = $_POST['assistance'];
	}
	if (empty ($_POST['dinsurance'])) {
	   $dinsurance = 0;
	}
	else {
	     $dinsurance = $_POST['dinsurance'];
	}
	if (empty ($_POST['ainsurance'])) {
	   $ainsurance = 0;
	}
	else {
	     $ainsurance = $_POST['ainsurance'];
	}
	if (empty ($_POST['start'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Please fill out the start date.\n";
	}
	if (empty ($_POST['end'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Please fill out the end date.\n";
	}
	if (empty ($_POST['city'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Please fill out city.\n";
	}
	if (empty ($_POST['state'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Please fill out state.\n";
	}
	if (empty ($_POST['empemail'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Cannot find employee email.\n";
	}
	if ($_POST['perweek'] == "") {
	   $response["success"] = 0;
	   $response["message"] .= "Please pick rent per day or rent per week.\n";
	}

  if (empty($_POST['empemail'])) {
    $empemail = "";
  } else {
    $empemail = $_POST['empemail'];
  }
	 mysqli_query($conn, "START TRANSACTION");
	 $insertResQuery = "INSERT INTO `ALLEN_EATON_AUTO.RESERVATION`
						   (`user_email`,
					`car_id`,
				`reservation_GPS`,
				`reservation_child_seat`,
				`reservation_k_tag`,
				`reservation_assistance`,
				`reservation_damage_insurance`,
				`reservation_accident_insurance`,
				`reservation_start_date`,
				`reservation_end_date`,
				`reservation_city`,
				`reservation_state`,
				`employee_email`,
				`per_week`,
				`checked_out` )
			VALUES (
					'".$_POST['username']."',
				'".$_POST['carid']."',
				'".$gps."',
				'".$childseat."',
				'".$ktag."',
				'".$assistance."',
				'".$dinsurance."',
				'".$ainsurance."',
				'".$_POST['start']."',
				'".$_POST['end']."',
				'".$_POST['city']."',
				'".$_POST['state']."',
				'".$empemail."',
				'".$_POST['perweek']."',
				0 )"; //set to 0, bc it is not checked out
   if (!mysqli_query($conn, $insertResQuery)) {
     $err = mysqli_error($conn);
		mysqli_query($conn, "ROLLBACK");
		$response["success"] = 0;
	$response["message"] = "Error in inserting into database:\n" . $err;
	 }
	 else if (mysqli_query($conn, "COMMIT")) {
		  $response["success"] = 1;
	  $response["message"] = "Car reservation created!";
	 }
	 else {
		  $response["success"] = 0;
	  $response["message"] = "Error in committing (insert).";
	 }
		
		
	 
	
	
	die(json_encode($response));
	mysqli_close($conn);
?>
