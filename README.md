Wykrywanie i korekcja błędu w macierzy za pomocą słowa kodowego
Wstęp
Ten projekt demonstruje, jak wykryć i naprawić pojedynczy błąd w 8x8 macierzy danych za pomocą kodu Hamminga.
Kod Hamminga pozwala na efektywne wykrywanie i korygowanie pojedynczych błędów w danych binarnych.
Proces opiera się na wyliczaniu bitów parzystości, które tworzą słowo kodowe, i wykorzystaniu ich do identyfikacji pozycji błędu.

Poniżej znajdziesz szczegółowy przewodnik po krokach, które wyjaśniają, jak zidentyfikować i poprawić błędny bit w macierzy.

Co to jest macierz i jak jest tworzona?
Macierz to prostokątny układ danych ułożonych w wierszach i kolumnach. W tym projekcie macierz danych to 8x8 tablica binarna, składająca się z 64 bitów (0 lub 1).

Struktura macierzy
Macierz w tym projekcie ma 8 wierszy i 8 kolumn, co oznacza, że w każdym wierszu znajduje się 8 bitów.
Całkowita liczba elementów w macierzy to:
8 wierszy × 8 kolumn = 64 bity
Jak jest tworzona macierz?
Automatyczna inicjalizacja danych:

Program generuje dane binarne (0 i 1) w taki sposób, że wartości w macierzy są ułożone naprzemiennie:


0, 1, 0, 1, 0, 1, 0, 1, ...
Dzięki temu uzyskujemy regularny wzór, co ułatwia wykrywanie błędów w danych.
Zapis danych w macierzy:

Wygenerowane dane są układane w 8 wierszach po 8 elementów, tworząc układ:


0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
...


Przykład macierzy początkowej:

0 1 0 1 0 1 0 1 
0 1 0 1 0 1 0 1 
0 1 0 1 0 1 0 1 
0 1 0 1 0 1 0 1 
0 1 0 1 0 1 0 1 
0 1 0 1 0 1 0 1 
0 1 0 1 0 1 0 1 
0 1 0 1 0 1 0 1 
Czym są bity parzystości?
Bity parzystości to dodatkowe bity dodawane do danych binarnych w celu wykrywania (a czasem korekcji) błędów.
Ich zadaniem jest kontrola liczby bitów 1 w zbiorze danych, aby określić, czy liczba ta jest parzysta, czy nieparzysta.

Typy parzystości
Parzystość parzysta (even parity):

Bit parzystości ustawiany jest tak, aby całkowita liczba 1 (łącznie z bitem parzystości) była parzysta.

Przykład:

Dane: 1011 (3 jedynki, nieparzysta)
Bit parzystości: 1
Dane z bitem parzystości: 10111
Parzystość nieparzysta (odd parity):

Bit parzystości ustawiany jest tak, aby całkowita liczba 1 (łącznie z bitem parzystości) była nieparzysta.

Przykład:

Dane: 1011 (3 jedynki, nieparzysta)
Bit parzystości: 0
Dane z bitem parzystości: 10110
Co to jest maska binarna?
Maska binarna to liczba binarna używana do wybierania określonych bitów spośród większego zbioru danych.
W projekcie maski binarne służą do wyznaczania, które bity z macierzy są brane pod uwagę przy obliczaniu konkretnych bitów parzystości.

Jak działa maska binarna?
Działanie:

Maska binarna działa poprzez zastosowanie operacji AND na pozycji każdego bitu w danych.
Jeśli bit w masce wynosi 1, to bit danych w tej pozycji jest uwzględniany.
Jeśli bit w masce wynosi 0, to bit danych w tej pozycji jest ignorowany.
Przykłady:

Maska: 0b000001 (dziesiętnie: 1)
Uwzględnia wszystkie pozycje, w których pierwszy bit (najmniej znaczący) wynosi 1.
Maska: 0b000010 (dziesiętnie: 2)
Uwzględnia wszystkie pozycje, w których drugi bit wynosi 1.

Przykład użycia maski binarnej:

Dane:

0 1 0 1 0 1 0 1 (pozycje: 0, 1, 2, 3, 4, 5, 6, 7)
Maska binarna: 0b000001 (pierwszy bit = 1).
Sprawdzane pozycje: 1, 3, 5, 7.

Dla tych pozycji sprawdzana jest liczba 1.
Jeśli liczba 1 jest nieparzysta, bit parzystości wynosi 1.
Jeśli liczba 1 jest parzysta, bit parzystości wynosi 0.
Jak działa korekcja błędów?
1. Słowo kodowe
Słowo kodowe składa się z bitów parzystości:
(p0, p1, p2, ..., p6).
Każdy bit parzystości obliczany jest na podstawie określonych pozycji bitów w macierzy danych, zgodnie z maską binarną.
2. Syndrom błędu
Jak działa syndrom?
Syndrom powstaje poprzez porównanie:
Odebranego słowa kodowego (z potencjalnym błędem).
Nowo obliczonego słowa kodowego (na podstawie aktualnych danych).
Znaczenie syndromu
Wykrywanie błędu:

Jeśli syndrom wynosi 0 → brak błędów w danych.
Jeśli syndrom różni się od 0 → wystąpił błąd.
Lokalizacja błędu:

Wartość syndromu wskazuje pozycję błędnego bitu w danych lub słowie kodowym.
Przykład:
Oryginalne słowo kodowe: p0=0, p1=0, p2=0, ..., p6=0.
Nowe słowo kodowe (z błędem): p0=1, p1=1, p2=0, ..., p6=0.

Syndrom:

Syndrom = Nowe słowo kodowe XOR Oryginalne słowo kodowe
Syndrom = 1100000 (binarnie) → 10 (dziesiętnie).
3. Korekcja błędu
Jeśli syndrom różni się od zera:

Wartość syndromu wskazuje dokładną pozycję błędnego bitu.
Błędny bit zostaje naprawiony przez zmianę jego wartości (0 → 1 lub 1 → 0).
Krok po kroku: Jak znaleźć i naprawić błąd w macierzy?
Krok 1: Generowanie macierzy danych
Macierz danych jest generowana automatycznie jako 8x8 macierz binarna (64 bity), z naprzemiennymi wartościami 0 i 1.

Krok 2: Obliczanie słowa kodowego
Na podstawie danych program oblicza bity parzystości, tworząc słowo kodowe (p0, ..., p6).

Krok 3: Wprowadzenie błędu
Celowo wprowadzamy błąd w macierzy danych, zmieniając wartość jednego bitu.

Krok 4: Wykrycie błędu za pomocą syndromu
Program oblicza nowe słowo kodowe.
Porównuje je z oryginalnym słowem kodowym.
Oblicza syndrom, który wskazuje dokładną pozycję błędu.
Krok 5: Naprawa błędnego bitu
Na podstawie syndromu program lokalizuje i koryguje błędny bit w macierzy danych.

Podsumowanie
Dzięki kodowi Hamminga oraz zastosowaniu masek binarnych projekt umożliwia:

Organizację danych w macierzy.
Wykrycie pojedynczych błędów.
Ich automatyczną korekcję, gwarantując integralność danych.
