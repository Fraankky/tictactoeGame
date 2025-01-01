import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Pemain {
    private String nama;
    private String tanda;

    public Pemain(String nama, String tanda) {
        this.nama = nama;
        this.tanda = tanda;
    }

    public String getNama() {
        return nama;
    }

    public String getTanda() {
        return tanda;
    }
}