import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

/* Class FifteenPuzzle */

public class FifteenPuzzle {
    // Deklarasi atribut
    private String[][] board;
    private int emptyRowLoc, emptyColLoc;

    // Konstruktor kosong (assign nomor secara random)
    public FifteenPuzzle() {
        // Inisialisasi atribut
        board = new String[4][4];
        emptyRowLoc = emptyColLoc = 3;

        // Kocok daftar kotak
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "-"};
        List<String> list = Arrays.asList(numbers);
        Collections.shuffle(list);
        list.toArray(numbers);

        // Assign nomor ke board
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = String.valueOf(numbers[i * 4 + j]);
            }
        }
    }
    // Konstructor dari File
    public FifteenPuzzle(String filename) {
        this.board = new String[4][4];
        String filepath = "test/" + filename;
        // Membaca file
        try {
            File file = new File(filepath);
            Scanner input = new Scanner(file);
            int i = 0;
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] tokens = line.split(" ");
                for (int j = 0; j < 4; j++) {
                    this.board[i][j] = tokens[j];
                    if (tokens[j].equals("-")) { // Inisialisasi posisi kotak kosong
                        this.emptyRowLoc = i;
                        this.emptyColLoc = j;
                    }
                }
                i++;
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    // Getter
    public String[][] getBoard() {
        return this.board;
    }
    public String getValueString(int row, int col) {
        return this.board[row][col];
    }
    public int getValue(int row, int col) {
        if (this.board[row][col].equals("-")) {
            return 16;
        }
        return Integer.parseInt(this.board[row][col]);
    }
    public int[] getValueIdx(String value){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equals(value)) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    } // getValueIdx(value) -> [row, col]
    public int getEmptyRowIdx() {
        return this.emptyRowLoc;
    }
    public int getEmptyColIdx() {
        return this.emptyColLoc;
    }

    // Setter
    public void setBoard(String[][] board) {
        this.board = board;
    }
    public void setValue(int row, int col, String value) {
        this.board[row][col] = value;
    }
    public void setEmptyRowLoc(int row) {
        // ! jangan dipakai tanpa move
        this.emptyRowLoc = row;
    }
    public void setEmptyColLoc(int col) {
        // ! jangan dipakai tanpa move
        this.emptyColLoc = col;
    }

    // Memindahkan kotak kosong ("-")
    public void moveUp() {
        // Memindahkan kotak kosong ("-") 1 kotak ke atas
        if (this.getEmptyRowIdx() > 0) {
            this.setValue(this.getEmptyRowIdx(), this.getEmptyColIdx(), this.getValueString(this.getEmptyRowIdx() - 1, this.getEmptyColIdx()));
            this.setValue(this.getEmptyRowIdx() - 1, this.getEmptyColIdx(), "-");
            this.setEmptyRowLoc(this.getEmptyRowIdx() - 1);
        }
    }
    public void moveDown() {
        // Memindahkan kotak kosong ("-") 1 kotak ke bawah
        if (this.getEmptyRowIdx() < 3) {
            this.setValue(this.getEmptyRowIdx(), this.getEmptyColIdx(), this.getValueString(this.getEmptyRowIdx() + 1, this.getEmptyColIdx()));
            this.setValue(this.getEmptyRowIdx() + 1, this.getEmptyColIdx(), "-");
            this.setEmptyRowLoc(this.getEmptyRowIdx() + 1);
        }
    }
    public void moveLeft() {
        // Memindahkan kotak kosong ("-") 1 kotak ke kiri
        if (this.getEmptyColIdx() > 0) {
            this.setValue(this.getEmptyRowIdx(), this.getEmptyColIdx(), this.getValueString(this.getEmptyRowIdx(), this.getEmptyColIdx() - 1));
            this.setValue(this.getEmptyRowIdx(), this.getEmptyColIdx() - 1, "-");
            this.setEmptyColLoc(this.getEmptyColIdx() - 1);
        }
    }
    public void moveRight() {
        // Memindahkan kotak kosong ("-") 1 kotak ke kanan
        if (this.getEmptyColIdx() < 3) {
            this.setValue(this.getEmptyRowIdx(), this.getEmptyColIdx(), this.getValueString(this.getEmptyRowIdx(), this.getEmptyColIdx() + 1));
            this.setValue(this.getEmptyRowIdx(), this.getEmptyColIdx() + 1, "-");
            this.setEmptyColLoc(this.getEmptyColIdx() + 1);
        }
    }

    // Method untuk mencetak board sebagai matriks, dipisah oleh tab ("\t")
    public void printBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(this.board[i][j] + "\t");
            }
            System.out.println();
        }
    }
    public void printBoardStyled() {
        System.out.println("+--+--+--+--+");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.board[i][j].length() == 1) {
                    System.out.print("| " + this.board[i][j]);
                } else {
                    System.out.print("|" + this.board[i][j]);
                }
            }
            System.out.println("|");
            System.out.println("+--+--+--+--+");
        }
    }

    // Method untuk mengecek apakah board dapat diselesaikan
    public boolean isSolvable() {
        // Menentukan x
        // x = 1 jika sel kosong berada pada posisi row genap kolom ganjil / row ganjil kolom genap
        int x;
        if (((this.getEmptyRowIdx() % 2 == 0) && (this.getEmptyColIdx() % 2 == 1))
            || ((this.getEmptyRowIdx() % 2 == 1) && (this.getEmptyColIdx() % 2 == 0))
        ) {
            x = 1;
        } else {
            x = 0;
        }

        int kurangCount = 0;
        // Iterasi tiap kotak, hitung banyak kotak-kotak
        // setelahnya yang nilainya lebih kecil dari kotak tersebut
        for(int it = 0; it < 4; it++){
            for (int jt = 0; jt < 4; jt++) {
                kurangCount += Kurang(it, jt);
            }
        }

        // Bisa diselesaikan kalau hasilnya genap
        return (kurangCount + x) % 2 == 0;
    }
    public int Kurang(int row, int col){
        // Fungsi antara
        // KURANG(i) = banyaknya ubin bernomor j
        // sedemikian sehingga j < i dan POSISI(j) > POSISI(i).
        // POSISI(i) = posisi ubin bernomor i pada susunan yang diperiksa.
        int counter = 0;
        // Cek sebelah kanan board[row][col]
        int i, j = col + 1;
        while (j < 4){
            if (this.getValue(row, j) < this.getValue(row, col)){
                counter++;
            }
            j++;
        }
        // Baris selanjutnya
        for(i = row + 1; i < 4; i++){
            for(j = 0; j < 4; j++){
                if (this.getValue(i, j) < this.getValue(row, col)){
                    counter++;
                }
            }
        }

        return counter;
    }

    public boolean isSolved() {
        return true;
    }
}