<?php
// KONTROLER strony kalkulatora
require_once dirname(__FILE__) . '/../config.php';

// W kontrolerze niczego nie wysyła się do klienta.
// Wysłaniem odpowiedzi zajmie się odpowiedni widok.
// Parametry do widoku przekazujemy przez zmienne.

// 1. pobranie parametrów

$x = $_REQUEST['x'];
$y = $_REQUEST['y'];
$z = $_REQUEST['z'];

// 2. walidacja parametrów z przygotowaniem zmiennych dla widoku

// sprawdzenie, czy parametry zostały przekazane
if (!(isset($x) && isset($y) && isset($z))) {
	//sytuacja wystąpi kiedy np. kontroler zostanie wywołany bezpośrednio - nie z formularza
	$messages[] = 'Błędne wywołanie aplikacji. Brak jednego z parametrów.';
}

// sprawdzenie, czy potrzebne wartości zostały przekazane
if ($x == "" || $y == "" || $z == "") {
	$messages[] = 'Nie podano wartosci';
}

//nie ma sensu walidować dalej gdy brak parametrów
if (empty($messages)) {
	if (!is_numeric($x) || !is_numeric($y) || !is_numeric($z)) {
		$messages[] = 'Wartość nie jest liczbą całkowitą';
	}
}

// 3. wykonaj zadanie jeśli wszystko w porządku

if (empty($messages)) { // gdy brak błędów

	//konwersja parametrów na int
	$x = intval($x);
	$y = intval($y);
	$z = intval($z);

	$result = $y * 1 + $y / 100;
	$monthly = $result / $x;
}

// 4. Wywołanie widoku z przekazaniem zmiennych
// - zainicjowane zmienne ($messages,$x,$y...)
//   będą dostępne w dołączonym skrypcie
include './credit_view.php';
