<?php
ini_set("display_errors", "1");
error_reporting(E_ALL);
include 'db_access.php';

$response['success'] = 1;
$response['message'] = '';

function fail_response($message) {
  global $response;

  $response['success'] = 0;
  $response['message'] .= $message;
}

if (empty($_POST['username'])) {
  fail_response('Email is required\n');

} else if (filter_var(empty($_POST['username']), FILTER_VALIDATE_EMAIL)) {
  fail_response('Email is not valid\n');

} else {
  $query = $conn->prepare('SELECT user_email
                           FROM `ALLEN_EATON_AUTO.USER`
                           WHERE user_email=?');

  $query->bind_param('s', $_POST['username']);
  $query->execute();

  $query->bind_result($result_array);

  $query->fetch();

  if (!empty($result_array)) {
    fail_response('Email is in use.\n');
  }
}

if (empty($_POST['password'])) {
  fail_response('Password is required.\n');

} else if ($_POST['repassword'] != $_POST['password']) {
  fail_response('The entered passwords need to match.\n');

} else {
  // Hash the password
  $options = ['cost' => 12];
  $password = password_hash($_POST['password'], PASSWORD_BCRYPT, $options);
}

if (empty($_POST['firstname'])) {
  fail_response('First name is required.\n');
}

if (empty($_POST['lastname'])) {
  fail_response('Last name is required.\n');
}

if (empty($_POST['address'])) {
  fail_response('Address is required.\n');
}

if (empty($_POST['city'])) {
  fail_response('City is required.\n');
}

if (empty($_POST['state'])) {
  fail_response('State is required.\n');
}

if (empty($_POST['zip'])) {
  fail_response('Zip is required.\n');
}

if (empty($_POST['phone'])) {
  fail_response('Phone number is required.\n');
}

if (empty($_POST['license'])) {
  fail_response('License is required.\n');
}

if (empty($_POST['birthdate'])) {
  fail_response('Date of birth is required.\n');

} else if (!preg_match("/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/", $_POST['birthdate'])) {
  // XXX: This shouldn't happen
  fail_response('Invalid date of birth.\n');
}

$middleName = '';
$address2 = '';

if (array_key_exists('middlename', $_POST)) {
  $middleName = $_POST['middlename'];
}

if (array_key_exists('address2', $_POST)) {
  $address2 = $_POST['address2'];
}

if ($response['success'] == 1) {
  $stmt = $conn->prepare('INSERT INTO `ALLEN_EATON_AUTO.USER`
                           (user_email
                           ,user_password
                           ,user_first_name
                           ,user_middle_name
                           ,user_last_name
                           ,user_address_street
                           ,user_address_street_2
                           ,user_address_city
                           ,user_address_state
                           ,user_address_zip
                           ,user_telephone
                           )
                           VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)');

  if (!$stmt) {
    die('MySQL error: ' . $conn->error);
  }

  $stmt->bind_param('sssssssssss', $_POST['username']
                                 , $password
                                 , $_POST['firstname']
                                 , $middleName
                                 , $_POST['lastname']
                                 , $_POST['address']
                                 , $address2
                                 , $_POST['city']
                                 , $_POST['state']
                                 , $_POST['zip']
                                 , $_POST['phone']
                   );

  if (!$stmt->execute()) {
    $conn->rollback();
    $response['success'] = 0;
    $response['message'] = 'Error inserting user.\n' . $stmt->error;
    die(json_encode($response));
  }

  $stmt->close();

  $stmt = $conn->prepare('INSERT INTO `ALLEN_EATON_AUTO.CUSTOMER`
                            (user_email
                            ,customer_card_number
                            ,customer_card_exp_date
                            ,customer_driver_license
                            ,customer_driver_license_state
                            )
                            VALUES (?, ?, ?, ?, ?)');

  $stmt->bind_param('sssss', $_POST['username']
                           , $_POST['cardnumber']
                           , $_POST['cardexp']
                           , $_POST['license']
                           , $_POST['licensestate']
                         );

  if (!$stmt->execute()) {
    $conn->rollback();
    $response['success'] = 0;
    $response['message'] = 'Error inserting user\n' . $stmt->error;
    die(json_encode($response));
  }

  $response['success'] = 1;
  $response['message'] = 'Registration is successful!\n';

  die(json_encode($response));

  $conn->close();

} else {
  die(json_encode($response));
  $conn->close();
}

?>

