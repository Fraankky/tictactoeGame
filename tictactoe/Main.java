import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String nama1 = JOptionPane.showInputDialog(null, "Masukkan nama untuk Pemain 1:");
        while (nama1.trim().equals("")) {
            nama1 = JOptionPane.showInputDialog("Harap masukkan nama yang valid:");
        }
        Pemain pemain1 = new Pemain(nama1.toUpperCase(), "X");
        String nama2 = JOptionPane.showInputDialog(null, "Masukkan nama untuk Pemain 2:");
        while (nama2.trim().equals("")) {
            nama2 = JOptionPane.showInputDialog("Harap masukkan nama yang valid:");
        }
        Pemain pemain2 = new Pemain(nama2.toUpperCase(), "O");

        TicTacToeGUI permainan = new TicTacToeGUI(pemain1, pemain2);
    }
}