
## *Tugas Besar 1* - IF2123 Aljabar Linear dan Geometri
### **Teknik Informatika Institut Teknologi Bandung**
Tahun ajaran 2021/2022

**Dibuat oleh:** <br>
13520051 &nbsp; Flavia Beatrix Leoni A. S.

13520115 &nbsp; Maria Khelli

13520119 &nbsp; Marchotridyo

# Struktur Direktori Program
* bin : file berekstensi .class (java bytecode)
* doc : laporan tugas besar
* src : source code utama
* test : soal dan solusi dari uji kasus
* lib : file berekstensi .jar, berfunsi sebagai library

# Cara Penggunaan Program
Pastikan Anda memiliki Java di komputer Anda! Cek menggunakan perintah `java -version` dan `javac -version` pada terminal.

1. Unduh *repository* dan *extract* ke suatu tempat.
2. Alternatif 1: Arahkan terminal ke folder ./bin menggunakan perintah `cd <pathName>/bin` lalu jalankan `java Main`. Ingat bahwa huruf M harus kapital.
3. Alternatif 2: Arahkan terminal ke folder ./lib menggunakan perintah `cd <pathName>/lib` lalu jalankan `java -jar Libgeo.jar`. Ingat bahwa huruf L harus kapital.

## Menu yang tersedia pada program
Pada saat menjalankan program, Anda akan menerima menu seperti berikut:
```
MENU
1. Sistem Persamaan Linier
2. Determinan
3. Matriks balikan
4. Interpolasi polinom
5. Regresi linier berganda
6. Keluar
```
Apabila Anda memilih nomor 1, Anda akan menerima menu seperti berikut:
```
1. Metode eliminasi Gauss
2. Metode eliminasi Gauss-Jordan
3. Metode matriks balikan
4. Kaidah Cramer
```
Apabila Anda memilih nomor 2, Anda akan menerima menu seperti berikut:
```
1. Metode reduksi baris
2. Metode ekspansi kofaktor
```
Apabila Anda memilih nomor 3, Anda akan menerima menu seperti berikut:
```
1. Metode reduksi baris
2. Metode matriks adjoin
```

## Masukan Program
Untuk setiap aksi, Anda diminta untuk menjelaskan jenis input yang akan digunakan: melalui suatu file atau langsung pada program.
```
Masukan teks (0) atau input (1)? (0/1): 1
```
Contoh input di atas menyatakan bahwa pengguna akan menggunakan input langsung pada program.

### Masukan untuk SPL
#### SPL: masukan dari file
Masukan dari file berupa matrix augmented. Contoh adalah file .txt yang berisikan matriks augmented berikut:
```
4 0 0 -2 0 0 1
-2 0 0 4 -2 0 0
0 0 0 -2 4 0 1
1 -1 0 -1 0 0 0
0 0 -1 1 -1 0 0
0 0 0 1 0 -1 0
```
#### SPL: masukan dari program
Untuk keempat metode pemecahan SPL, program menerima input berupa banyak baris, banyak kolom, dan masukan matriks yang mengikuti format masukan seperti berikut (beserta *prompt*-nya):
```
Masukkan banyak baris: 6
Masukkan banyak kolom: 7
Masukkan matriks: 
4 0 0 -2 0 0 1
-2 0 0 4 -2 0 0
0 0 0 -2 4 0 1
1 -1 0 -1 0 0 0
0 0 -1 1 -1 0 0
0 0 0 1 0 -1 0
```

### Masukan untuk determinan dan matriks balikan
#### Determinan dan matriks balikan: masukan dari file
Masukan dari file berupa matrix persegi. Contoh adalah file .txt yang berisikan matriks persegi berikut:
```
1 2 3
4 5 6
9 9 1
```
#### Determinan dan matriks balikan: masukan dari program
Untuk kedua metode pencarian determinan, program menerima input berupa ordo matriks dan masukan matriks yang mengikuti format masukan seperti berikut (beserta *prompt*-nya):
```
Masukkan ordo matriks persegi: 3
Masukkan matriks: 
1 2 3
4 5 6
9 9 1
```

### Masukan untuk interpolasi polinom
Untuk interpolasi polinom, masukan dari file dan masukan langsung pada program sama saja. Program menerima input n berupa orde polinom dan pasangan (x, y) yang dipisahkan dengan spasi seperti contoh input berikut.
```
9
6.567 12624
7 21807
7.258 38391
7.451 54517
7.548 51952
7.839 28228
8.161 35764
8.484 20813
8.709 12408
9 10534
```
Berikutnya, pengguna akan diminta untuk memasukkan nilai x yang ingin ditaksir nilai y-nya. Berikut adalah contohnya.
```
Masukkan nilai x yang ingin ditaksir: 7.335
```

### Masukan untuk regresi linier berganda
#### Regresi linier berganda: masukan dari file
Masukan dari file berupa matrix augmented (x1, x2, x3, ..., xn, y). Contoh adalah file .txt yang berisikan matriks augmented berikut:
```
72.4 76.3 29.18 0.90
41.6 70.3 29.35 0.91
34.3 77.1 29.24 0.96
35.1 68.0 29.27 0.89
10.7 79.0 29.78 1.00
12.9 67.4 29.39 1.10
8.3 66.8 29.69 1.15
20.1 76.9 29.48 1.03
72.2 77.7 29.09 0.77
24.0 67.7 29.60 1.07
23.2 76.8 29.38 1.07
47.4 86.6 29.35 0.94
31.5 76.9 29.63 1.10
10.6 86.3 29.56 1.10
11.2 86.0 29.48 1.10
73.3 76.3 29.40 0.91
75.4 77.9 29.28 0.87
96.6 78.7 29.29 0.78
107.4 86.8 29.03 0.82
54.9 70.9 29.37 0.95
```
Lalu, pengguna akan diminta untuk memasukkan nilai-nilai x yang ingin ditaksir.
```
Masukkan nilai x1 x2 ... xn (yang ingin ditaksir): 
56.0 70.0 29.0
```
#### Regresi linier berganda: masukan dari program
Input langsung dari program untuk regresi linier berganda mengikuti *prompt* berikut:
```
Masukkan nilai n (banyak variabel): 3
Masukkan nilai m (banyak sampel): 20
Masukkan nilai x1i x2i ... xni yi: 
72.4 76.3 29.18 0.90
41.6 70.3 29.35 0.91
34.3 77.1 29.24 0.96
35.1 68.0 29.27 0.89
10.7 79.0 29.78 1.00
12.9 67.4 29.39 1.10
8.3 66.8 29.69 1.15
20.1 76.9 29.48 1.03
72.2 77.7 29.09 0.77
24.0 67.7 29.60 1.07
23.2 76.8 29.38 1.07
47.4 86.6 29.35 0.94
31.5 76.9 29.63 1.10
10.6 86.3 29.56 1.10
11.2 86.0 29.48 1.10
73.3 76.3 29.40 0.91
75.4 77.9 29.28 0.87
96.6 78.7 29.29 0.78
107.4 86.8 29.03 0.82
54.9 70.9 29.37 0.95
Masukkan nilai x1 x2 ... xn (yang ingin ditaksir): 
56.0 70.0 29.0
```

## Keluaran Program
Keluaran yang ditampilkan pada layar dan pada *file* sama bentuknya.
### Keluaran SPL
```
Matriks masukan: 
4.0 0.0 0.0 -2.0 0.0 0.0 1.0 
-2.0 0.0 0.0 4.0 -2.0 0.0 0.0 
0.0 0.0 0.0 -2.0 4.0 0.0 1.0 
1.0 -1.0 0.0 -1.0 0.0 0.0 0.0 
0.0 0.0 -1.0 1.0 -1.0 0.0 0.0 
0.0 0.0 0.0 1.0 0.0 -1.0 0.0 
Solusi dari persamaan augmented yang Anda masukkan (dengan Cramer): 
x1 = 0.5
x2 = -0.0
x3 = -0.0
x4 = 0.5
x5 = 0.5
x6 = 0.5
```
### Keluaran determinan
```
Matriks masukan: 
1.0 2.0 3.0 
4.0 5.0 6.0 
9.0 9.0 1.0 
Determinan dari matriks tersebut adalah 24.0
```
### Keluaran balikan matriks
```
Matriks masukan: 
1.0 2.0 3.0 
4.0 5.0 6.0 
9.0 9.0 1.0 
Inversnya adalah: 
-2.0416666666666665 1.0416666666666665 -0.125 
2.083333333333333 -1.0833333333333333 0.25 
-0.375 0.375 -0.125 
```
### Keluaran interpolasi polinom
```
Matriks masukan: 
6.567 12624.0 
7.0 21807.0 
7.258 38391.0 
7.451 54517.0 
7.548 51952.0 
7.839 28228.0 
8.161 35764.0 
8.484 20813.0 
8.709 12408.0 
9.0 10534.0 
Persamaan interpolasinya adalah: 
7.190410416888292E12 + -9.350881209947121E12x + 5.336209640567323E12x^2 + -1.757413527578105E12x^3 + 3.6866728864166327E11x^4 + -5.114685068078558E10x^5 + 4.697088059029316E9x^6 + -2.7554498625218344E8x^7 + 9375105.143730488x^8 + -141025.78096584175x^9
Taksiran untuk titik x=7.516 adalah 53545.00390625
```
### Keluaran regresi linier berganda
```
Matriks masukan: 
72.4 76.3 29.18 0.9 
41.6 70.3 29.35 0.91 
34.3 77.1 29.24 0.96 
35.1 68.0 29.27 0.89 
10.7 79.0 29.78 1.0 
12.9 67.4 29.39 1.1 
8.3 66.8 29.69 1.15 
20.1 76.9 29.48 1.03 
72.2 77.7 29.09 0.77 
24.0 67.7 29.6 1.07 
23.2 76.8 29.38 1.07 
47.4 86.6 29.35 0.94 
31.5 76.9 29.63 1.1 
10.6 86.3 29.56 1.1 
11.2 86.0 29.48 1.1 
73.3 76.3 29.4 0.91 
75.4 77.9 29.28 0.87 
96.6 78.7 29.29 0.78 
107.4 86.8 29.03 0.82 
54.9 70.9 29.37 0.95 
Persamaan regresinya adalah: 
yi = -3.507778140863007 + -0.002624990745881574x1i + 7.989410472203326E-4x2i + 0.15415503019761254x3i + epsilon_i
Taksiran untuk x1=50.0 x2=76.0 x3=29.3 adalah 1.8768684524434143
```
