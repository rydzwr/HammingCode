#Wykrywanie i korekcja błędu w macierzy za pomocą słowa kodowego

#Wstęp
Ten projekt demonstruje, jak wykryć i naprawić pojedynczy błąd w 8x8 macierzy danych za pomocą kodu Hamminga. Kod Hamminga pozwala na efektywne wykrywanie i korygowanie pojedynczych błędów w danych binarnych. Proces opiera się na wyliczaniu bitów parzystości, które tworzą słowo kodowe, i wykorzystaniu ich do identyfikacji pozycji błędu.

Poniżej znajdziesz szczegółowy przewodnik po krokach, które wyjaśniają, jak zidentyfikować i poprawić błędny bit w macierzy.

Jak działa korekcja błędów?
Słowo kodowe: Słowo kodowe składa się z bitów parzystości (p0, p1, p2, ..., p6). Każdy bit parzystości oblicza się na podstawie specyficznych pozycji bitów w macierzy, zgodnie z maską binarną.

Syndrom błędu: Syndrom to wynik porównania odebranego słowa kodowego z obliczonym słowem kodowym na podstawie danych (po ich odebraniu). Syndrom wskazuje, czy wystąpił błąd, oraz jego dokładną pozycję w macierzy.

Korekcja błędu: Jeśli syndrom różni się od zera, jego wartość binarna wskazuje pozycję błędnego bitu w macierzy danych lub w samym słowie kodowym. Błędny bit można naprawić, zmieniając jego wartość z 0 na 1 lub z 1 na 0.

Krok po kroku: Jak znaleźć i naprawić błąd w macierzy
Krok 1: Generowanie macierzy danych
Macierz danych jest generowana automatycznie w programie jako 8x8 macierz (64 bity), gdzie wartości na przemian wynoszą 0 i 1.

#Przykład macierzy początkowej:

0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
Krok 2: Obliczanie słowa kodowego
Słowo kodowe oblicza się na podstawie bitów parzystości (p0, p1, p2, ..., p6). Każdy bit parzystości jest obliczany według następującego schematu:

p0 (maska 0b000001): Oblicza parzystość dla pozycji, gdzie pierwszy bit (najmniej znaczący) jest ustawiony na 1 (np. pozycje 1, 3, 5, 7, ...).
p1 (maska 0b000010): Oblicza parzystość dla pozycji, gdzie drugi bit jest ustawiony na 1 (np. pozycje 2, 3, 6, 7, ...).
p2 (maska 0b000100): Oblicza parzystość dla pozycji, gdzie trzeci bit jest ustawiony na 1 (np. pozycje 4, 5, 6, 7, ...).
Analogicznie dla kolejnych bitów (p3, p4, p5, p6).

Przykład słowa kodowego:
Dla powyższej macierzy początkowej program wylicza słowo kodowe:

p0=0, p1=0, p2=0, p3=0, p4=0, p5=0, p6=0

# Krok 3: Wprowadzenie błędu
W tym kroku celowo wprowadzamy błąd w macierzy danych. W naszym przypadku bit na pozycji 10 (indeks 10, liczony od 0) zostaje zmieniony (z 0 na 1 lub odwrotnie).

Macierz po wprowadzeniu błędu:
0 1 0 1 0 1 0 1
0 1 {1} 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1

# Krok 4: Wykrycie błędu za pomocą syndromu
Po wprowadzeniu błędu, program oblicza syndrom, który wskazuje pozycję błędu. Syndrom oblicza się jako różnicę między:

słowem kodowym wyliczonym z aktualnych danych, aoryginalnym słowem kodowym.

#Przykład:
Nowo wyliczone słowo kodowe: p0=1, p1=1, p2=0, p3=0, p4=0, p5=0, p6=0
Odebrane słowo kodowe: p0=0, p1=0, p2=0, p3=0, p4=0, p5=0, p6=0

Syndrom (różnica): syndrom = 10
Syndrom w systemie binarnym to 0001010, co odpowiada pozycji 10 w macierzy danych.

Krok 5: Naprawa błędnego bitu
Na podstawie syndromu program lokalizuje błędny bit w macierzy danych (pozycja 10). Następnie koryguje błąd, zmieniając jego wartość.

#Macierz po korekcji:

0 1 0 1 0 1 0 1
0 0 [1] 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
0 1 0 1 0 1 0 1
Krok 6: Walidacja poprawności
Po korekcji błędu program:

Ponownie oblicza słowo kodowe na podstawie poprawionej macierzy.
Sprawdza, czy nowe słowo kodowe zgadza się z oryginalnym. Jeśli tak, oznacza to, że błąd został skutecznie naprawiony.
#Wynik walidacji:

Revalidation successful! No errors detected in the corrected data.

#Krok 7: Porównanie z macierzą początkową
Na koniec program porównuje macierz poprawioną z macierzą początkową. Jeśli wszystkie bity są zgodne, proces zakończył się sukcesem.

#Struktura projektu

Main: Główna klasa zawierająca logikę generowania macierzy, wprowadzania błędu, korekcji i walidacji.
HammingCode: Klasa odpowiedzialna za obliczanie słowa kodowego, wykrywanie i naprawę błędów.

Uruchamianie projektu
Skompiluj kod w dowolnym IDE wspierającym Javę (np. IntelliJ IDEA).
Uruchom klasę Main.
Przeanalizuj wyniki na konsoli, zwracając uwagę na proces korekcji błędu i finalną macierz.
Wymagania systemowe
Java 17 lub nowsza
IDE wspierające Javę (np. IntelliJ IDEA)
Podsumowanie
Dzięki kodowi Hamminga możliwe jest:

Wykrycie pojedynczego błędu w danych binarnych.
Dokładna identyfikacja pozycji błędu.
Automatyczna korekcja błędu.
Proces ten jest kluczowy w systemach przesyłania danych, gdzie integralność informacji jest priorytetem.
