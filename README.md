# 15-Puzzle-Solver

Dibuat oleh: Alifia Rahmah (13520122)

Implementasi Branch & Bound untuk menyelesaikan permainan 15-Puzzle
dalam program Java
dalam rangka memenuhi Tugas Kecil 3 IF2211 Strategi Algoritma

Spesifikasi Tugas Kecil 3: <br>
[Tugas Kecil 3 IF2211 Strategi Algoritma Semester II 2021/2022](https://informatika.stei.itb.ac.id/~rinaldi.munir/Stmik/2021-2022/Tugas-Kecil-3-(2022).pdf)

Laporan: <br>
[Laporan Tugas Kecil 3 - Alifia Rahmah (13520122)](https://github.com/alifiarahmah/15-Puzzle-Solver/blob/master/doc/Tucil3_13520122.pdf)

## Deskripsi singkat

Program 15-Puzzle Solver menggunakan algoritma Branch and Bound untuk menyelesaikan persoalan 15-Puzzle,
yang awalnya dibangkitkan secara acak atau mengambil input dari file, 
sehingga susunan kotak dalam puzzle terurut dari nomor 1 sampai 15. 
Pendekatan Branch and Bound dilakukan dengan membangkitkan langkah yang memiliki cost terkecil
hingga mencapai solusi dari puzzle.

Dalam mencari langkah-langkah menuju susunan akhir puzzle, 
program menggunakan pendekatan Tree dengan struktur data Priority Queue 
untuk menyimpan kumpulan puzzle yang dimasukkan dalam Node.


## Requirement

Java SE 17 / OpenJDK JRE 17.0.2

Untuk kompilasi: <br>
Java SE Development Kit (JDK) 17.0.1 / OpenJDK JDK 17.0.2.

## Struktur Folder

```
.
├── README.md
├── bin
│   └── Tucil3_13520122.jar
├── doc
│   └── Tucil3_13520122.pdf
├── src
│   ├── META-INF
│   │   └── MANIFEST.MF
│   ├── Main.java
│   ├── Node.java
│   ├── PuzzleBoard.java
│   └── Solver.java
└── test
    ├── tc1.txt
    ├── tc2.txt
    ├── tc3.txt
    ├── tc4.txt
    └── tc5.txt
```

`bin`: folder untuk menyimpan file hasil build program (executable)

`doc`: folder untuk menyimpan laporan program

`src`: folder untuk menyimpan source code program dan manifest

`test`: folder untuk menyimpan file teks instansiasi persoalan

## Langkah kompilasi program

### Kompilasi dan menjalankan langsung dari class (.class)
```shell
javac src/*
cd src
java Main
```

### Build binary (.jar)

Menggunakan IDE (IntelliJ IDEA):
1. Klik menu `File` -> `Project Structure`
2. Klik `+` untuk menambahkan Artifacts, lalu pilih `JAR` -> `From modules with dependencies`
3. Pilih `Main` sebagai Main class. Klik OK.
4. Ganti output directory menjadi `...Tucil3_13520122/bin`. Klik OK.
5. Klik menu `Build` -> `Build Artifacts...`.
6. Klik `Build`.
7. File binary sudah tersedia pada `...Tucil3_13520122/bin` dan siap dijalankan.

## Cara menggunakan program

Menjalankan binary dari folder `/bin`
```shell
git clone https://github.com/alifiarahmah/Tucil3_13520122
cd Tucil3_13520122
java -jar bin/Tucil3_13520122.jar
```
