<?php
   include ("db_access.php");

   if (empty($_POST['username']) || empty($_POST['password'])) {
      $response["success"] = 0;
      $response["message"] = "Please fill in the email and password field.";
      die (json_encode($response));
   }

   $loginEmail = mysqli_real_escape_string($c, $_POST['username']);
   $loginPassword = mysqli_real_escape_string($c, $_POST['password']);
   
   $loginQuery = "SELECT E.user_email, user_password, employee_role
   	       	 	 FROM `ALLEN_EATON_AUTO.USER` U,
			      (SELECT user_email, employee_role
			       FROM `ALLEN_EATON_AUTO.EMPLOYEE`
			       WHERE user_email='".$loginEmail."') E
			 WHERE U.user_email='".$loginEmail."' 
			 AND employee_role IS NOT NULL";

  $loginResult = mysqli_query($c, $loginQuery);
  $loginArray = mysqli_fetch_array($loginResult);

  if (!empty($loginArray) && /*$loginPassword == $loginArray['user_password']*/ password_verify($loginPassword, $loginArray['user_password'])) {
     $response["success"] = 1;
     $response["message"] = "Login Successful!";
     die(json_encode($response));
  }
  else {
     $response["success"] = 0;
     $response["message"] = "Username or password is incorrect.";
     die(json_encode($response));
  }

  mysqli_close($c);

?>