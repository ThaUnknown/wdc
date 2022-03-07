<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">

<head>
	<meta charset="utf-8" />
	<title>Kalkulator</title>
	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
</head>

<body>
	<div style="width:90%; margin: 2em auto;">
		<a href="<?php print(_APP_ROOT); ?>/app/inna_chroniona.php" class="pure-button">kolejna chroniona strona</a>
		<a href="<?php print(_APP_ROOT); ?>/app/security/logout.php" class="pure-button pure-button-active">Wyloguj</a>
	</div>
	<div style="width:90%; margin: 2em auto;">

		<form action="<?php print(_APP_URL); ?>/app/credit.php" method="post" class="pure-form pure-form-stacked">
			<label for="id_x">Okres splaty: </label>
			<input id="id_x" type="number" name="x" value="<?php isset($x) ? print($x) : null; ?>" />miesiecy<br />
			<label for="id_y">Kwota kredytu: </label>
			<input id="id_y" type="number" name="y" value="<?php isset($y) ? print($y) : null; ?>" /><br />
			<label for="id_z">Oprocentowanie: </label>
			<input id="id_z" type="number" name="z" value="<?php isset($z) ? print($z) : null; ?>" />%<br />
			<input type="submit" value="Oblicz" />
		</form>
	</div>

	<?php
	//wyświeltenie listy błędów, jeśli istnieją
	if (isset($messages)) {
		if (count($messages) > 0) {
			echo '<ol style="margin: 20px; padding: 10px 10px 10px 30px; border-radius: 5px; background-color: #f88; width:300px;">';
			foreach ($messages as $key => $msg) {
				echo '<li>' . $msg . '</li>';
			}
			echo '</ol>';
		}
	}
	?>

	<?php if (isset($result) && isset($monthly)) { ?>
		<div style="margin: 20px; padding: 10px; border-radius: 5px; background-color: #ff0; width:300px;">
			<?php echo 'Kwota: ' . $result; ?>
		</div>
		<div style="margin: 20px; padding: 10px; border-radius: 5px; background-color: #ff0; width:300px;">
			<?php echo 'Rata miesieczna: ' . $monthly; ?>
		</div>
	<?php } ?>

</body>

</html>