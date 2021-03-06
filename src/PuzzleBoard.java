import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PuzzleBoard {
    // Deklarasi atribut
    private String[][] board;
    private int emptyRowLoc, emptyColLoc;

    // Konstruktor kosong (assign nomor secara random)
    public PuzzleBoard() {
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
    public PuzzleBoard(String filename) {
        this.board = new String[4][4];
        String path = new File("").getAbsolutePath();
        if(path.endsWith("bin")) {
            path = path.substring(0, path.length() - 4);
        }
        String filepath = path + "/test/" + filename;
        System.out.print("Reading from " + filepath + "...\n");
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
    public PuzzleBoard(File file) {
        try {

            this.board = new String[4][4];
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
    // Konstruktor dari Puzzleboard lain
    public PuzzleBoard(PuzzleBoard other) {
        this.board = new String[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(other.board[i], 0, this.board[i], 0, 4);
        }
        this.emptyRowLoc = other.emptyRowLoc;
        this.emptyColLoc = other.emptyColLoc;
    }

    // Getter
    public String getValueString(int row, int col) {
        return this.board[row][col];
    }
    public int getValue(int row, int col) {
        if (this.board[row][col].equals("-")) {
            return 16;
        }
        return Integer.parseInt(this.board[row][col]);
    }
    public int getEmptyRowIdx() {
        return this.emptyRowLoc;
    }
    public int getEmptyColIdx() {
        return this.emptyColLoc;
    }

    // Setter
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

    // Mengembalikan board dengan posisi kotak kosong ("-") pada posisi baru
    public PuzzleBoard movedUp() {
        PuzzleBoard newBoard = new PuzzleBoard(this);
        if (newBoard.getEmptyRowIdx() > 0) {
            newBoard.setValue(newBoard.getEmptyRowIdx(), newBoard.getEmptyColIdx(), newBoard.getValueString(newBoard.getEmptyRowIdx() - 1, newBoard.getEmptyColIdx()));
            newBoard.setValue(newBoard.getEmptyRowIdx() - 1, newBoard.getEmptyColIdx(), "-");
            newBoard.setEmptyRowLoc(newBoard.getEmptyRowIdx() - 1);
        }
        return newBoard;
    }
    public PuzzleBoard movedDown() {
        PuzzleBoard newBoard = new PuzzleBoard(this);
        if (newBoard.getEmptyRowIdx() < 3) {
            newBoard.setValue(newBoard.getEmptyRowIdx(), newBoard.getEmptyColIdx(), newBoard.getValueString(newBoard.getEmptyRowIdx() + 1, newBoard.getEmptyColIdx()));
            newBoard.setValue(newBoard.getEmptyRowIdx() + 1, newBoard.getEmptyColIdx(), "-");
            newBoard.setEmptyRowLoc(newBoard.getEmptyRowIdx() + 1);
        }
        return newBoard;
    }
    public PuzzleBoard movedLeft() {
        PuzzleBoard newBoard = new PuzzleBoard(this);
        if (newBoard.getEmptyColIdx() > 0) {
            newBoard.setValue(newBoard.getEmptyRowIdx(), newBoard.getEmptyColIdx(), newBoard.getValueString(newBoard.getEmptyRowIdx(), newBoard.getEmptyColIdx() - 1));
            newBoard.setValue(newBoard.getEmptyRowIdx(), newBoard.getEmptyColIdx() - 1, "-");
            newBoard.setEmptyColLoc(newBoard.getEmptyColIdx() - 1);
        }
        return newBoard;
    }
    public PuzzleBoard movedRight() {
        PuzzleBoard newBoard = new PuzzleBoard(this);
        if (newBoard.getEmptyColIdx() < 3) {
            newBoard.setValue(newBoard.getEmptyRowIdx(), newBoard.getEmptyColIdx(), newBoard.getValueString(newBoard.getEmptyRowIdx(), newBoard.getEmptyColIdx() + 1));
            newBoard.setValue(newBoard.getEmptyRowIdx(), newBoard.getEmptyColIdx() + 1, "-");
            newBoard.setEmptyColLoc(newBoard.getEmptyColIdx() + 1);
        }
        return newBoard;
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

    // Method untuk mengecek apakah board dapat diselesaikan
    public boolean isSolvable() {
        // Menentukan x
        // x = 1 jika sel kosong berada pada posisi row genap kolom ganjil / row ganjil kolom genap

        int x = x();
        int kurangCount = SigmaKurang();

        // Bisa diselesaikan kalau hasilnya genap
        return (kurangCount + x) % 2 == 0;
    }
    public int x() {
        if (((this.getEmptyRowIdx() % 2 == 0) && (this.getEmptyColIdx() % 2 == 1))
                || ((this.getEmptyRowIdx() % 2 == 1) && (this.getEmptyColIdx() % 2 == 0))
        ) {
            return 1;
        } else {
            return 0;
        }
    }
    public int SigmaKurang() {
        int kurangCount = 0;
        // Iterasi tiap kotak, hitung banyak kotak-kotak
        // setelahnya yang nilainya lebih kecil dari kotak tersebut
        for(int it = 0; it < 4; it++){
            for (int jt = 0; jt < 4; jt++) {
                kurangCount += Kurang(it, jt);
            }
        }
        return kurangCount;
    }
    public int Kurang(int row, int col){
        // Fungsi antara
        // KURANG(i) = banyaknya ubin bernomor j sedemikian sehingga j < i dan POSISI(j) > POSISI(i).
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
    public int Kurang(int value){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if (this.getValue(i, j) == value){
                    return Kurang(i, j);
                }
            }
        }
        return 0;
    }

    // Method untuk mencari jumlah kotak yang tidak sesuai tempatnya
    public int countNotInPlace() {
        int counter = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if (this.getValue(i, j) != 4 * i + j + 1){
                    counter++;
                }
            }
        }
        return counter;
    }

    // Method untuk mengecek apakah seluruh kotak dalam board sudah sesuai
    public boolean isSolved() {
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if (this.getValue(i, j) != 4 * i + j + 1){
                    return false;
                }
            }
        }
        return true;
    }

    // Memanggil solve() dari class Solver
    public void solve() {
        Solver.solve(this);
    }
}
