import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGUI extends JFrame {
    private JButton[][] kotak;
    private JLabel labelStatus;
    private boolean giliranPemain1;
    private boolean permainanSelesai;
    private int jumlahGerakan;
    private Pemain pemain1, pemain2;

    public TicTacToeGUI(Pemain p1, Pemain p2) {
        pemain1 = p1;
        pemain2 = p2;
        setTitle("PERMAINAN: Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Atur grid tombol
        JPanel panelGrid = new JPanel(new GridLayout(3, 3));
        kotak = new JButton[3][3];

        for (int baris = 0; baris < 3; baris++) {
            for (int kolom = 0; kolom < 3; kolom++) {
                kotak[baris][kolom] = new JButton("");
                kotak[baris][kolom].setFont(new Font("Arial", Font.BOLD, 50));
                kotak[baris][kolom].setBackground(new Color(47, 104, 121));
                kotak[baris][kolom].setForeground(new Color(124, 221, 210));
                final int b = baris, k = kolom;
                kotak[baris][kolom].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        tombolDiklik(b, k);
                    }
                });
                panelGrid.add(kotak[baris][kolom]);
            }
        }
        add(panelGrid);

        // Atur label status
        labelStatus = new JLabel(pemain1.getNama() + " giliran (X)");
        labelStatus.setFont(new Font("Arial", Font.BOLD, 35));
        getContentPane().setBackground(new Color(172, 198, 197));
        labelStatus.setForeground(new Color(47, 104, 121));
        labelStatus.setPreferredSize(
                new Dimension(labelStatus.getPreferredSize().width + 150, labelStatus.getPreferredSize().height + 6));
        labelStatus.setHorizontalAlignment(JLabel.CENTER);
        add(labelStatus, BorderLayout.SOUTH);

        setSize(650, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void tombolDiklik(int baris, int kolom) {
        if (permainanSelesai) {
            JOptionPane.showMessageDialog(this, "Permainan telah selesai. Silakan mulai permainan baru.");
            return;
        }

        // Periksa apakah kotak kosong
        if (!kotak[baris][kolom].getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Kotak sudah terisi. Pilih kotak lain.");
            return;
        }

        // Lakukan gerakan
        if (giliranPemain1) {
            kotak[baris][kolom].setText("X");
            labelStatus.setText(pemain2.getNama() + " giliran (O)");
        } else {
            kotak[baris][kolom].setText("O");
            labelStatus.setText(pemain1.getNama() + " giliran (X)");
        }
        jumlahGerakan++;

        // Periksa pemenang atau seri
        if (jumlahGerakan >= 5) {
            if (periksaPemenang(baris, kolom)) {
                permainanSelesai = true;
                String pemenang = giliranPemain1 ? pemain2.getNama() : pemain1.getNama();
                labelStatus.setText("SELAMAT " + pemenang + "!!");
                JOptionPane.showMessageDialog(this, "Selamat " + pemenang + ", Anda menang!");

                int pilihan = JOptionPane.showConfirmDialog(this, "Ingin bermain lagi?", "Bermain Lagi?",
                        JOptionPane.YES_NO_OPTION);
                if (pilihan == JOptionPane.YES_OPTION) {
                    resetPermainan();
                } else {
                    dispose();
                }
            } else if (jumlahGerakan == 9) {
                permainanSelesai = true;
                labelStatus.setText("SERIII!!");
                JOptionPane.showMessageDialog(this, "Permainan seri.");
                int pilihan = JOptionPane.showConfirmDialog(this, "Ingin bermain lagi?", "Bermain Lagi?",
                        JOptionPane.YES_NO_OPTION);
                if (pilihan == JOptionPane.YES_OPTION) {
                    resetPermainan();
                } else {
                    dispose();
                }
            }
        }

        giliranPemain1 = !giliranPemain1;
    }

    private void resetPermainan() {
        giliranPemain1 = true;
        permainanSelesai = false;
        jumlahGerakan = 0;

        for (int baris = 0; baris < 3; baris++) {
            for (int kolom = 0; kolom < 3; kolom++) {
                kotak[baris][kolom].setText("");
                kotak[baris][kolom].setBackground(new Color(88, 204, 121));
            }
        }

        labelStatus.setText(pemain1.getNama() + " giliran");
    }

    private boolean periksaPemenang(int baris, int kolom) {
        String tanda = giliranPemain1 ? "X" : "O";

        // Periksa baris
        if (kotak[baris][0].getText().equals(tanda) && kotak[baris][1].getText().equals(tanda)
                && kotak[baris][2].getText().equals(tanda)) {
            highlightKotakPemenang(baris, 0, baris, 1, baris, 2);
            return true;
        }

        // Periksa kolom
        if (kotak[0][kolom].getText().equals(tanda) && kotak[1][kolom].getText().equals(tanda)
                && kotak[2][kolom].getText().equals(tanda)) {
            highlightKotakPemenang(0, kolom, 1, kolom, 2, kolom);
            return true;
        }

        // Periksa diagonal
        if (baris == kolom && kotak[0][0].getText().equals(tanda) && kotak[1][1].getText().equals(tanda)
                && kotak[2][2].getText().equals(tanda)) {
            highlightKotakPemenang(0, 0, 1, 1, 2, 2);
            return true;
        }

        // Periksa diagonal lain
        if (baris + kolom == 2 && kotak[0][2].getText().equals(tanda) && kotak[1][1].getText().equals(tanda)
                && kotak[2][0].getText().equals(tanda)) {
            highlightKotakPemenang(0, 2, 1, 1, 2, 0);
            return true;
        }

        return false;
    }

    private void highlightKotakPemenang(int b1, int k1, int b2, int k2, int b3, int k3) {
        kotak[b1][k1].setBackground(new Color(98, 140, 88));
        kotak[b2][k2].setBackground(new Color(98, 140, 88));
        kotak[b3][k3].setBackground(new Color(98, 140, 88));
    }
}