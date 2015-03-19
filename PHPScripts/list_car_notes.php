<?php
	include("db_access.php");
	$listNotesQuery = "SELECT car_history, user_email
			   FROM `ALLEN_EATON_AUTO.CAR_NOTES`
			   WHERE car_id='".$_POST["id"]."'";

	$listNotesResult = mysqli_query($c, $listNotesQuery);

	if (!$listNotesResult) {
	   $response["success"] = 0;
	   $response["notes"] = array();
	   die(json_encode($response));
	}

	if (mysqli_num_rows($listNotesResult) > 0) {
	   $response["notes"] = array();

	   while ($row = mysqli_fetch_array($listNotesResult)) {
	   	 $note = array();

		 $note["history"] = $row["car_history"];
		 $note["user_email"] = $row["user_email"];

		 array_push($response["notes"], $note);
	   }

	   $response["success"] = 1;
	}
	else {
	     $response["success"] = 1;
	     $response["notes"] = array();
	}
	
	die(json_encode($response));
?>