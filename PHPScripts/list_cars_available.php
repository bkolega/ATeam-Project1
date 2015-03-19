<?php
	include("db_access.php");
	
	$listCarQuery = "SELECT DISTINCT C.*
			 FROM `ALLEN_EATON_AUTO.CAR` C, `ALLEN_EATON_AUTO.RESERVATION` R
			 WHERE C.car_id NOT IN 
			       (SELECT RE.car_id FROM `ALLEN_EATON_AUTO.RESERVATION` RE)
			 OR (C.car_id=R.car_id
			    AND checked_out=0)";
	
	$listCarResult = mysqli_query($c, $listCarQuery);
	
	if (!$listCarResult) {
	   $response["success"] = 0;
	   $response["cars"] = array();
	   die(json_encode($response));
	}

	if (mysqli_num_rows($listCarResult) > 0) {
	   $response["cars"] = array();

	   while ($row = mysqli_fetch_array($listCarResult)) {
	   	 $car = array();

		 $car["id"] = $row["car_id"];
		 $car["type"] = $row["car_type"];
		 $car["make"] = $row["car_make"];
		 $car["model"] = $row["car_model"];
		 $car["year"] = $row["car_year"];
		 $car["license_state"] = $row["car_license_state"];
		 $car["license_plate"] = $row["car_license_plate"];
		 $car["miles"] = $row["car_miles"];
		 $car["oil_change_date"] = $row["car_oil_change_date"];
		 $car["oil_change_mile"] = $row["car_oil_change_mile"];
		 $car["tire_rotation_date"] = $row["car_tire_rotation_date"];
		 $car["tire_rotation_mile"] = $row["car_tire_rotation_mile"];
		 $car["air_filters_date"] = $row["car_air_filters_date"];
		 $car["air_filters_mile"] = $row["car_air_filters_mile"];
		 $car["location"] = $row["car_location"];

		 array_push($response["cars"], $car);
	   }

	   $response["success"] = 1;
	}
	else {
	   $response["success"] = 1;
	   $response["cars"] = array();
	}

	die(json_encode($response));
?>