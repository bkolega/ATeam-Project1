<?php
	include("db_access.php");
	$listContractsQuery = "SELECT R.car_id, reservation_GPS, reservation_child_seat,
			     	     reservation_k_tag, reservation_assistance,
				     reservation_damage_insurance,
				     reservation_accident_insurance, 
				     reservation_start_date,
				     reservation_end_date, reservation_city,
				     reservation_state, employee_email, 
				     per_week, car_type, car_make, car_model, 
				     car_license_plate, car_license_state, car_year, 
				     checked_in_date, customer_card_number
			     FROM `ALLEN_EATON_AUTO.RESERVATION` R, 
			     	  `ALLEN_EATON_AUTO.CAR` C,
			     	  `ALLEN_EATON_AUTO.CUSTOMER` CUST
			     WHERE R.user_email='".$_POST['username']."'
			     AND R.car_id=C.car_id
			     AND R.user_email=CUST.user_email";
	$listContractsResult = mysqli_query($c, $listContractsQuery);

	if (!$listContractsResult) {
	   $response["success"] = 0;
	   $response["contracts"] = array();
	   die(json_encode($response));
	}

	if (mysqli_num_rows($listContractsResult) > 0) {
	   $response["contracts"] = array();

	   while ($row = mysqli_fetch_array($listContractsResult)) {
	   	 $contract = array();

		 $contract["car_id"] = $row["car_id"];
 		 $contract["GPS"] = $row["reservation_GPS"];
		 $contract["child_seat"] = $row["reservation_child_seat"];
		 $contract["k_tag"] = $row["reservation_k_tag"];
		 $contract["assistance"] = $row["reservation_assistance"];
		 $contract["damage_insurance"] = $row["reservation_damage_insurance"];
		 $contract["accident_insurance"] = $row["reservation_accident_insurance"];
		 $contract["start_date"] = $row["reservation_start_date"];
		 $contract["end_date"] = $row["reservation_end_date"];
		 $contract["city"] = $row["reservation_city"];
		 $contract["state"] = $row["reservation_state"];
		 $contract["per_week"] = $row["per_week"];
		 $contract["employee_email"] = $row["employee_email"];
		 $contract["car_type"] = $row["car_type"];
		 $contract["car_make"] = $row["car_make"];
		 $contract["car_model"] = $row["car_model"];
		 $contract["car_license_plate"] = $row["car_license_plate"];
		 $contract["car_license_state"] = $row["car_license_state"];
		 $contract["car_year"] = $row["car_year"];
		 $contract["card_number"] = $row["customer_card_number"];
		 
		 array_push($response["contracts"], $contract);
	   }
	   
	   $response["success"] = 1;
	}
	else {
	     $response["success"] = 1;
	     $response["contractss"] = array();
	}
	die(json_encode($response));

?>