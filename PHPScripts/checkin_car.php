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


	if ($response["success"] != 0) {
	   // Find the reservation in database
	   $findResQuery = "SELECT user_email
		      	    FROM `ALLEN_EATON_AUTO.RESERVATION` 
			    WHERE checked_out=1
			    AND user_email='".$_POST['username']."'
			    AND car_id='".$_POST['carid']."'";

	   $findResResult = mysqli_query($c, $findResQuery);

	   if (!$findResResult) {
	      $response["success"] = 0;
	      $response["message"] = "Error in database (Find reservation).";
	   }
	   else if (mysqli_num_rows($findResResult) > 0) {
	   	mysqli_query($c, "START TRANSACTION");

		$updateResQuery = "UPDATE `ALLEN_EATON_AUTO.RESERVATION`
				   SET checked_out=0,
				       checked_in_date=NOW()
				  WHERE user_email='".$_POST['username']."'
				  AND car_id='".$_POST['carid']."'";

		if (!mysqli_query($c, $updateResQuery)) {
		   mysqli_query($c, "ROLLBACK");
		   $response["success"] = 0;
		   $response["message"] = "Error in updating database.";
		}
		else if (mysqli_query($c, "COMMIT")) {
		     $response["success"] = 1;
		     $response["message"] = "Car checked in!";
		}
		else {
		     $response["success"] = 0;
		     $response["message"] = "Error in committing (update).";
		}
	   }
	   else {
	   	$response["success"] = 0;
		$response["message"] = "Error finding reservation.";
	   }
        }
	die(json_encode($response));
	mysqli_close($c);
?>