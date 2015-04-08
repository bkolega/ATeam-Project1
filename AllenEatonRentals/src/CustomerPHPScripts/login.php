<?php
ini_set("display_errors", "1");
error_reporting(E_ALL);
include "db_access.php";

if (empty($_POST['username']) || empty($_POST['password'])) {
  $response['success'] = 0;
  $response['message'] = "Username or password field is blank";
  die(json_encode($response));
}

$query = $conn->prepare('SELECT E.user_email, user_password, customer_card_number
                                ,customer_card_exp_date, customer_driver_license
                                ,customer_driver_license_state
                         FROM `ALLEN_EATON_AUTO.USER` U,
                            (SELECT *
                             FROM `ALLEN_EATON_AUTO.CUSTOMER`
                             WHERE user_email=?) E
                         WHERE U.user_email=?');

if (!$query) {
  die('MySQL error: ' . $conn->error);
}

$query->bind_param('ss', $_POST['username'], $_POST['username']);

$query->execute();

$login_result = $query->get_result();

$query->fetch();

$login_array = $login_result->fetch_array(MYSQLI_ASSOC);

if (!empty($login_array) && password_verify($_POST['password'], $login_array['user_password'])) {
  $response['success'] = 1;
  $response['message'] = 'Login Successful!';

  foreach (array_keys($login_array) as $key) {
    $response[$key] = $login_array[$key];
  }

  die(json_encode($response));
} else {
  $response['success'] = 0;
  $response['message'] = 'Incorrect username or password.';
  die(json_encode($response));
}

$query->close();
$conn->close();

?>

