package view;

import java.util.ArrayList;
import models.*;

public class FilmView {
//Menampilkan menu utama
    public void tampilkanMenuUtama() {
        System.out.println("\n+ ================ SISTEM TRACKER FILM & REVIEW ================ +");
        System.out.println("| (1) Tambah Film                                                |"); 
        System.out.println("| (2) Tambah Review                                              |");
        System.out.println("| (3) Lihat Film & Review                                        |");
        System.out.println("| (4) Update Review                                              |");
        System.out.println("| (5) Hapus Review                                               |");
        System.out.println("| (6) Keluar                                                     |");
        System.out.println("+ ============================================================== +");
    }
    
    public void tampilkanRingkasanFilm(ArrayList<Film> daftarFilm, ArrayList<Review> daftarReview) {
        System.out.println("\n+----------------------------------------------------------------+");
        System.out.println("|                           DAFTAR FILM                          |");
        System.out.println("+----------------------------------------------------------------+");
        for (Film f : daftarFilm) {
            f.tampilkanInfo(true); 

            Review reviewFilm = null;
            for (Review r : daftarReview) {
                if (r.getIdFilm() == f.getIdFilm()) {
                    reviewFilm = r;
                    break;
                }
            }

            if (reviewFilm != null) {
                System.out.println("    > Rating: " + reviewFilm.getRating() + "/5.0");
            } else {
                System.out.println("   [Belum ada review]");
            }
            System.out.println("------------------------------------------------------------------");
        }
    }

//READ (Menampilkan Film dan Review)
    public void tampilkanFilm(ArrayList<Film> daftarFilm, ArrayList<Review> daftarReview) {
        System.out.println("\n==================================================================");
        System.out.println("                          FILM & REVIEW                           ");
        System.out.println("==================================================================");

        if (daftarFilm.isEmpty()) {
            System.out.println("Belum ada film yang dimasukkan!");
            return;
        }

        for (Film f : daftarFilm) {
            f.tampilkanInfo(false);

            boolean adaReview = false;
            for (Review r : daftarReview) {
                if (r.getIdFilm() == f.getIdFilm()) {
                    r.tampilkanInfo(false);
                    adaReview = true;
                }
            }
            if (!adaReview) {
                System.out.println("      Belum ada review untuk film ini.");
            }
            System.out.println("==================================================================");
          }
        }
    
    public void tampilkanTambahFilm() {
        System.out.println("\n+----------------------------------------------------------------+");
        System.out.println("|                           TAMBAH FILM                          |");
        System.out.println("+----------------------------------------------------------------+");
        System.out.println("Pilih Jenis Film");
        System.out.println("1. Feature Film (Live-Action)");
        System.out.println("2. Animated Film");
    }
    
    public void tampilkanTambahReview() {
        System.out.println("|                           TAMBAH REVIEW                        |");
        System.out.println("+----------------------------------------------------------------+");
    }
    
    public void tampilkanUpdateReview() {
        System.out.println("|                          UPDATE REVIEW                         |");
        System.out.println("+----------------------------------------------------------------+");
    }
    
    public void tampilkanHapusReview() {
        System.out.println("|                          HAPUS REVIEW                          |");
        System.out.println("+----------------------------------------------------------------+");
    }
}
