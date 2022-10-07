<?php

require_once dirname(__FILE__) . '/../config.php';

// KONTROLER strony kalkulatora

// W kontrolerze niczego nie wysyła się do klienta.
// Wysłaniem odpowiedzi zajmie się odpowiedni widok.
// Parametry do widoku przekazujemy przez zmienne.

//ochrona kontrolera - poniższy skrypt przerwie przetwarzanie w tym punkcie gdy użytkownik jest niezalogowany
include _ROOT_PATH . '/app/security/check.php';

if ($_SESSION['role'] == 'admin' || $_SESSION['role'] == 'user') {
  include 'calc_view.php';
} elseif ($_SESSION['role'] == 'credit') {
  include 'credit_view.php';
}
