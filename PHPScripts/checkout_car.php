<?php
	include ("db_access.php");

	$response["success"] = 1;
	$response["message"] = "";

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

	if ($response["success"] != 0) {
	   // Find the reservation in database
	   $findResQuery = "SELECT user_email
		      	    FROM `ALLEN_EATON_AUTO.RESERVATION` 
			    WHERE checked_out=0
			    AND user_email='".$_POST['username']."'
			    AND car_id='".$_POST['carid']."'";

	   $findResResult = mysqli_query($c, $findResQuery);

	   if (!$findResResult) {
	      $response["success"] = 0;
	      $response["message"] = "Error in database (Find reservation).";
	   }
	   else {

	   	// UPDATE database if found reservation
		if (mysqli_num_rows($findResResult) > 0) {
		   mysqli_query($c, "START TRANSACTION");

		   $updateResQuery = "UPDATE `ALLEN_EATON_AUTO.RESERVATION`
		   		      SET reservation_GPS='".$gps."', 
				      	  reservation_child_seat='".$childseat."',
					  reservation_k_tag='".$ktag."',
					  reservation_assistance='".$assistance."',
					  reservation_damage_insurance='".$dinsurance."',
					  reservation_accident_insurance='".ainsurance."',
					  reservation_start_date=NOW(),
					  reservation_end_date='".$_POST['end']."',
					  reservation_city='".$_POST['city']."',
					  reservation_state='".$_POST['state']."',
					  employee_email='".$_POST['empemail']."',
					  per_week=1,
					  checked_out=1
				      WHERE user_email='".$_POST['username']."'
				      AND car_id='".$_POST['carid']."'";

		   if (!mysqli_query($c, $updateResQuery)) {
		      mysqli_query($c, "ROLLBACK");
		      $response["success"] = 0;
		      $response["message"] = "Error in updating database.";
		   }
		   else if (mysqli_query($c, "COMMIT")) {
		   	$response["success"] = 1;
			$response["message"] = "Car checked out!";
		   }
		   else {
		   	$response["success"] = 0;
			$response["message"] = "Error in committing (update).";
		   }
		}
		// INSERT database 
		else {
		     mysqli_query($c, "START TRANSACTION");

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
						NOW(),
						'".$_POST['end']."',
						'".$_POST['city']."',
						'".$_POST['state']."',
						'".$_POST['empemail']."',
						1,
						1 )";

		     if (!mysqli_query($c, $insertResQuery)) {
		     	mysqli_query($c, "ROLLBACK");
		     	$response["success"] = 0;
			$response["message"] = "Error in inserting into database.";
		     }
		     else if (mysqli_query($c, "COMMIT")) {
		     	  $response["success"] = 1;
			  $response["message"] = "Car checked out!";
		     }
		     else {
		     	  $response["success"] = 0;
			  $response["message"] = "Error in committing (insert).";
		     }
		
		}
	   } 
	}
	die(json_encode($response));
	mysqli_close($c);
?>