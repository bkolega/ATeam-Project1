<?php
	include ("db_access.php");
	
	$response["success"] = 1;
	$response["message"] = "";

	// Check if email field is empty
	if (empty($_POST['username'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Email is required\n";
	}
	// Check if email is valid
	else if (filter_var(empty($_POST['username']), FILTER_VALIDATE_EMAIL)) {
	   $response["success"] = 0;
	   $response["message"] .= "Email is not valid\n";
	} 
	// Check if user already exists
	else {
	   $userQuery = "SELECT user_email
	     		 FROM `ALLEN_EATON_AUTO.USER`
			 WHERE user_email='".$_POST['username']."'";

	   $userResult = mysqli_query($c, $userQuery);
	   $userArray = mysqli_fetch_array($userResult);

	   if (!empty($userArray)) {
	      $response["success"] = 0;
	      $response["message"] .= "Email is in use\n";
	   } 
	}

	
	// Check if password field is empty
	if (empty($_POST['password'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Password is required\n";
	}
	// Check if passwords match
	else if ($_POST['repassword'] != $_POST['password']) {
	   $response["success"] = 0;
	   $response["message"] .= "The entered passwords need to match\n";
	}
	// Hash password for security
	else {
	     $options = [ 'cost' => 12 ];
	     $password = password_hash($_POST['password'], PASSWORD_BCRYPT, $options);
	}
	
	// Check if first name field is empty
	if (empty($_POST['firstname'])) {
	   $response["success"] = 0;
	   $response["message"] .= "First name is required\n";
	}
	
	// Check if last name field is empty
	if (empty($_POST['lastname'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Last name is required\n";
	}
	
	// Check if birth date field is empty
	if (empty($_POST['birthdate'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Birth date is required\n";
	} 
	else if (!preg_match("/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/", $_POST['birthdate'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Birth date needs to be in 'YYYY-MM-DD' format\n";
	} 
	
	// Check if street address field is empty
	if (empty($_POST['street'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Street address is required\n";
	}
	
	// Check if city field is empty
	if (empty($_POST['city'])) {
	   $response["success"] = 0;
	   $response["message"] .= "City is required\n";
	}
	
	// Check if state field is empty
	if (empty($_POST['state'])) {
	   $response["success"] = 0;
	   $response["message"] .= "State is required\n";
	}

	// Check if zip code field is empty
	if (empty($_POST['zip'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Zip code is required\n";
	}
	
	// Check if employee role field is empty
	if (empty($_POST['role'])) {
	   $response["success"] = 0;
	   $response["message"] .= "Employee role is required\n";
	}
	else if ($_POST['role'] != "Sales" && $_POST['role'] != "Service" && $_POST['role'] != "Manager") {
	     $response["success"] = 0;
	     $response["message"] .= "Sales, Service, or Manager for role only\n";
	}

	// If there is no error, run INSERT query
	if ($response["success"] != 0) {
	   // Start a transaction
	   mysqli_query($c, "START TRANSACTION");

	   // Insert into user table
	   $registerQuery = "INSERT INTO `ALLEN_EATON_AUTO.USER`
			     	    (`user_email`,
				     `user_password`,
				     `user_first_name`,
				     `user_middle_name`,
				     `user_last_name`,
				     `user_birthday`,
				     `user_telephone`,
				     `user_address_street`,
				     `user_address_street_2`,
				     `user_address_city`,
				     `user_address_state`,
				     `user_address_zip` )
			      VALUES (
			      	     '".$_POST['username']."',
				     '".$password."',
				     '".$_POST['firstname']."',
				     '".$_POST['middlename']."',
				     '".$_POST['lastname']."',
				     '".$_POST['birthdate']."',
				     '".$_POST['phone']."',
				     '".$_POST['street']."',
				     '".$_POST['street2']."',
				     '".$_POST['city']."',
				     '".$_POST['state']."',
				     '".$_POST['zip']."' )";

	   if (!mysqli_query($c, $registerQuery)) {
	      mysqli_query($c, "ROLLBACK");
	      $response["success"] = 0;
	      $response["message"] = "Error in entering user.";
	      die(json_encode($response));
	   }

	   // Insert into employee table
	   $registerQuery = "INSERT INTO `ALLEN_EATON_AUTO.EMPLOYEE`
			      	    (`user_email`,
				     `supervisor_email`,
				     `employee_role` )
			      VALUES (
			      	     '".$_POST['username']."',
				     '".$_POST['supemail']."',
				     '".$_POST['role']."' )";
	   
	   if (!mysqli_query($c, $registerQuery)) {
	      mysqli_query($c, "ROLLBACK");
	      $response["success"] = 0;
	      $response["message"] = "Error in entering employee.";
	      die(json_encode($response));
	   }

	   // Commit transaction
	   if (mysqli_query($c, "COMMIT")) {
	     	$response["success"] = 1;
		$response["message"] = "Register Successful! Please log in.";
	   }
	   else {
	     	$response["success"] = 0;
		$response["message"] = "Error in registering.";
	   }
	} 

	// Encode JSON and close MySQL connection
	die(json_encode($response));
	mysqli_close($c);
?>