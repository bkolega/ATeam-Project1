<?php
	include("db_access.php");
	$listResQuery = "SELECT R.car_id, reservation_GPS, reservation_child_seat,
		      	       	reservation_k_tag, reservation_assistance,
			       	reservation_damage_insurance, 
			       	reservation_accident_insurance, reservation_start_date,
			       	reservation_end_date, reservation_city, reservation_state,
			       	per_week, car_type, car_make, car_model, car_license_plate
			 FROM `ALLEN_EATON_AUTO.RESERVATION` R, `ALLEN_EATON_AUTO.CAR` C 
			 WHERE checked_out=1
			 AND user_email='".$_POST['username']."'
			 AND R.car_id=C.car_id
			 AND (C.car_id NOT IN
			   (SELECT US.car_id FROM `ALLEN_EATON_AUTO.USED_SALES` US))";

	$listResResult = mysqli_query($c, $listResQuery);

	if (!$listResResult) {		 
	   $response["success"] = 0;
	   $response["reservations"] = array();
	   die(json_encode($response));
	}

	if (mysqli_num_rows($listResResult) > 0) {
	   $response["reservations"] = array();

	   while ($row = mysqli_fetch_array($listResResult)) {
	   	 $reservation = array();

		 $reservation["car_id"] = $row["car_id"]; 
		 $reservation["GPS"] = $row["reservation_GPS"];
		 $reservation["child_seat"] = $row["reservation_child_seat"];
		 $reservation["k_tag"] = $row["reservation_k_tag"];
		 $reservation["assistance"] = $row["reservation_assistance"];
		 $reservation["damage_insurance"] = $row["reservation_damage_insurance"];
		 $reservation["accident_insurance"] = $row["reservation_accident_insurance"];
		 $reservation["start_date"] = $row["reservation_start_date"];
		 $reservation["end_date"] = $row["reservation_end_date"];
		 $reservation["city"] = $row["reservation_city"];
		 $reservation["state"] = $row["reservation_state"];
		 $reservation["per_week"] = $row["per_week"];
		 $reservation["car_type"] = $row["car_type"];
		 $reservation["car_make"] = $row["car_make"];
		 $reservation["car_model"] = $row["car_model"];
		 $reservation["car_license_plate"] = $row["car_license_plate"];
		 
		 array_push($response["reservations"], $reservation);
	   }
	   
	   $response["success"] = 1;
	}
	else {
	     $response["success"] = 1;
	     $response["reservations"] = array();
	}

	die(json_encode($response));
?>