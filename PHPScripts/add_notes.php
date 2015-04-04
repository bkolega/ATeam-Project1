<?php
	include ("db_access.php");

	$response["success"] = 1;
	$response["message"] = "";	

	if (empty($_POST['carid'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Could not get car ID\n";
	}

	if (empty($_POST['history'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Note cannot be empty\n";
	}

	if (empty($_POST['email'])) {
	   $response["success"] = 0;
	   $response["message"] .= "User email cannot be empty\n";
	}

	if ($response["success"] != 0) {
	   // Get the number of car notes for that car to get car_note_number
	   $noteNumberQuery = "SELECT car_id
	   		       FROM `ALLEN_EATON_AUTO.CAR_NOTES`
			       WHERE car_id='".$_POST['carid']."'";

	   $noteNumberResult = mysqli_query($c , $noteNumberQuery);
	   $noteCount = mysqli_num_rows($noteNumberResult);

	   $noteNumber = $noteCount + 1;

	   // Start a transaction
	   mysqli_query($c, "START TRANSACTION");

	   $addNoteQuery = "INSERT INTO `ALLEN_EATON_AUTO.CAR_NOTES`
	   		   	   (`car_id`,
				    `car_history`,
				    `car_note_number`,
				    `user_email` )
			    VALUES (
			    	   '".$_POST['carid']."',
				   '".$_POST['history']."',
				   '".$noteNumber."',
				   '".$_POST['email']."' )";
	   if (!mysqli_query($c, $addNoteQuery)) {
	      mysqli_query($c, "ROLLBACK");
	      $response["success"] = 0;
	      $response["message"] = "Error in entering car note";
	      die (json_encode($response));
	   }

	   if (mysqli_query($c, "COMMIT")) {
	      $response["success"] = 1;
	      $response["message"] = "Note added!";
	   }
	   else {
	   	$response["success"] = 0;
		$response["message"] = "Error in adding car note.";
	   }
	}

	die(json_encode($response));
	mysqli_close($c);
?>