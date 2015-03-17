<?php
	include ("db_access.php");

	$searchCustomerQuery = "SELECT C.user_email, customer_card_number,
			       	       customer_card_exp_date, customer_driver_license,
				       customer_driver_license_state, user_first_name,
				       user_last_name, user_telephone, user_address_street,
				       user_address_street_2, user_address_city,
				       user_address_state, user_address_zip
				FROM `ALLEN_EATON_AUTO.USER` U, 
				     `ALLEN_EATON_AUTO.CUSTOMER` C
				WHERE U.user_email=C.user_email
				AND (
				    U.user_email='".$_POST['keyword']."'
				    OR user_first_name='".$_POST['keyword']."'
				    OR user_middle_name='".$_POST['keyword']."'
				    OR user_last_name='".$_POST['keyword']."'
				    OR CONCAT_WS(' ', user_first_name, user_last_name)='".$_POST['keyword']."'
				    OR user_birthday like '".$_POST['keyword']."%'
				    OR user_telephone='".$_POST['keyword']."'
				    OR user_address_street='".$_POST['keyword']."'
				    OR user_address_city='".$_POST['keyword']."'
				    OR user_address_state='".$_POST['keyword']."'
				    OR user_address_zip='".$_POST['keyword']."'
				    OR customer_driver_license='".$_POST['keyword']."'
				    )";
	$searchCustomerResult = mysqli_query($c, $searchCustomerQuery);

	if (!searchCustomerResult) {
	   $response["success"] = 0;
	   $response["users"] = array();
	   $response["message"] = "Something wrong with database";
	   die(json_encode($response));
	}	

	if (mysqli_num_rows($searchCustomerResult) > 0) {
	   $response["users"] = array();

	   while ($row = mysqli_fetch_array($searchCustomerResult)) {
	   $user = array();
	
	   $user["email"] = $row["user_email"];
	   $user["card_number"] = $row["customer_card_number"];
	   $user["license"] = $row["customer_driver_license"];
	   $user["license_state"] = $row["customer_driver_license_state"];
	   $user["name"] = $row["user_first_name"] . " " . $row["user_last_name"];
	   $user["phone"] = $row["user_telephone"];
	   $user["address"] = $row["user_address_street"];
	   $user["address2"] = $row["user_address_street_2"];
	   $user["city"] = $row["user_address_city"];
	   $user["state"] = $row["user_address_state"];
	   $user["zip"] = $row["user_address_zip"];

	   array_push($response["users"], $user);
	}

	$response["success"] = 1;

	}
	else {
	     $response["success"] = 1;
	     $response["users"] = array();
	}
	

	die(json_encode($response));
?>