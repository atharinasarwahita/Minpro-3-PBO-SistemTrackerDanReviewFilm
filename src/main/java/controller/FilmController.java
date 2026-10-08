package controller;

import java.util.ArrayList;
import models.*;
import view.FilmView;

public class FilmController {
    private final ArrayList<Film> daftarFilm = new ArrayList<>(); 
    private final ArrayList<Review> daftarReview = new ArrayList<>(); 
    private final FilmView view = new FilmView();

    // auto-increment untuk ID
    private static int nextFilmId = 1;
    private static int nextReviewId = 1;

    public FilmController() {
        // Data Dummy
        daftarFilm.add(new FeatureFilm(nextFilmId++, "13 Bom di Jakarta", "Angga Dwimas Sasongko", 2023, "Action/Thriller", 144, "Chicco Kurniawan", "Oscar"));
        daftarFilm.add(new AnimatedFilm(nextFilmId++, "Si Juki the Movie", "Faza Meonk", 2017, "Animation/Comedy", 100, "Faza Meonk", "Si Juki", "2D Animation"));

        daftarReview.add(new Review(nextReviewId++, 1, 5.0, "Keren!"));
    }

    // CREATE FILM
    public void tambahFilm() {
        view.tampilkanTambahFilm();
        int jenis = ValidasiInput.inputAngka("Pilih jenis (1/2): ");
        if (jenis < 1 || jenis > 2) {
            System.out.println("Pilihan jenis film tidak valid!");
            return;
        }

        int idFilm = nextFilmId;
        System.out.println("ID Film: " + idFilm);

        String judul = ValidasiInput.inputNonKosong("Masukkan Judul Film: ");
        String sutradara = ValidasiInput.inputNonKosong("Masukkan Sutradara Film: ");
        String genre = ValidasiInput.inputNonKosong("Masukkan Genre Film: ");

        Film validator;
        if (jenis == 1){
            validator = new FeatureFilm(idFilm, judul, sutradara, 2000, genre, 120, "-", "-");
        } else { validator = new AnimatedFilm(idFilm, judul, sutradara, 2000, genre, 120, "-", "-", "-");
        }

        int tahunRilis;
        while (true) {
            tahunRilis = ValidasiInput.inputAngka("Masukkan Tahun Rilis Film: ");
            if (validator.setTahunRilis(tahunRilis)) {
                break;
            }
        }

        int durasi;
        while (true) {
            durasi = ValidasiInput.inputAngka("Masukkan Durasi Film (menit): ");
            if (validator.setDurasi(durasi)) {
                break;
            }
        }

        System.out.println("------------------------------------------------------------------");

        switch (jenis) {
            case 1 -> {
                String pemeranUtama = ValidasiInput.inputNonKosong("Masukkan Nama Aktor Utama: ");
                String karakter = ValidasiInput.inputNonKosong("Masukkan Nama Karakter/Tokoh: ");
                daftarFilm.add(new FeatureFilm(idFilm, judul, sutradara, tahunRilis, genre, durasi, pemeranUtama, karakter));
            }
            case 2 -> {
                String va = ValidasiInput.inputNonKosong("Masukkan Nama Voice Actor (VA): ");
                String karakter = ValidasiInput.inputNonKosong("Masukkan Nama Karakter Animasi: ");
                String gaya = ValidasiInput.inputNonKosong("Masukkan Gaya Animasi: ");
                daftarFilm.add(new AnimatedFilm(idFilm, judul, sutradara, tahunRilis, genre, durasi, va, karakter, gaya));
            }
        }

        nextFilmId++;
        System.out.println("------------------------------------------------------------------");
        System.out.println("\nYeayy, film kamu berhasil ditambahkan!");
        lihatFilm();
    }

    // CREATE REVIEW
    public void tambahReview() {
        if (daftarFilm.isEmpty()) {
            System.out.println("Belum ada film di sini!");
            return;
        }

        view.tampilkanRingkasanFilm(daftarFilm, daftarReview);
        view.tampilkanTambahReview();

        int cariFilm;
        while (true) {
            cariFilm = ValidasiInput.inputAngka("Masukkan ID Film yang mau di-review: ");

            boolean adaFilm = false;
            for (Film f : daftarFilm) {
                if (f.getIdFilm() == cariFilm) {
                    adaFilm = true;
                    break;
                }
            }

        if (!adaFilm) {
                System.out.println("ID film tidak ada di sistem! Silakan coba lagi.");
            } else {
                break; 
            }
        }

        boolean isRewatch = false;
        for (Review r : daftarReview) {
            if (r.getIdFilm() == cariFilm) {
                isRewatch = true;
                break;
            }
        }

        if (isRewatch) {
            System.out.println("\nFilm ini sudah pernah kamu ulas. Review baru akan disimpan sebagai [REWATCH].");
        }

        int idReview = nextReviewId;
        System.out.println("ID Review: " + idReview);

        Review reviewSemn = new Review(idReview, cariFilm, 5.0, "", isRewatch);

        double rating;
        while (true) {
            rating = ValidasiInput.inputRating("Masukkan Rating Film (1 - 5): ");
            if (reviewSemn.setRating(rating)) {
                break;
            }
        }

        String ulasan = ValidasiInput.inputNonKosong("Masukkan Ulasan Film: ");

        daftarReview.add(new Review(idReview, cariFilm, rating, ulasan, isRewatch));
        nextReviewId++;

        System.out.println("\nYeayy, ulasan berhasil ditambahkan!");
        lihatFilm();
    }

    // READ
    public void lihatFilm() {
        view.tampilkanFilm(daftarFilm, daftarReview);
    }

    // UPDATE REVIEW
    public void updateReview() {
        if (daftarReview.isEmpty()) {
            System.out.println("\nBelum ada review yang bisa diubah!");
            return;
        }

        lihatFilm();
        view.tampilkanUpdateReview();
        int updateIdReview = ValidasiInput.inputAngka("Masukkan ID review yang mau di-update: ");

        for (Review r : daftarReview) {
            if (r.getIdReview() == updateIdReview) {
                System.out.println("(1) Rating");
                System.out.println("(2) Ulasan");
                int pilihan = ValidasiInput.inputAngka("Pilih bagian yang ingin diubah: ");

                switch (pilihan) {
                    case 1 -> {
                        double ratingBaru;
                        while (true) {
                            ratingBaru = ValidasiInput.inputRating("Rating Film Baru (1 - 5): ");
                            if (r.setRating(ratingBaru)) {
                                break;
                            }
                        }
                        System.out.println("\nYeayy, rating baru kamu berhasil diubah!");
                        lihatFilm();
                        return;
                    }
                    case 2 -> {
                        String ulasanBaru = ValidasiInput.inputNonKosong("Ulasan Film Baru: ");
                        r.setUlasan(ulasanBaru);
                        System.out.println("\nYeayy, ulasan terbaru kamu berhasil diubah!");
                        lihatFilm();
                        return;
                    }
                    default -> System.out.println("Pilihan tidak valid!");
                }
                return;
            }
        }
        System.out.println("ID Review tidak ditemukan!");
    }

    // DELETE REVIEW
    public void hapusReview() {
        if (daftarReview.isEmpty()) {
            System.out.println("\nBelum ada review yang bisa dihapus!");
            return;
        }

        lihatFilm();
        view.tampilkanHapusReview();
        int hapusReview = ValidasiInput.inputAngka("Masukkan ID Review yang mau dihapus: ");

        for (Review r : daftarReview) {
            if (r.getIdReview() == hapusReview) {
                boolean konfirmasi = ValidasiInput.inputKonfirmasi("Apakah kamu benar ingin menghapus review ini? (Y/N): ");
                if (konfirmasi) {
                    daftarReview.remove(r);
                    System.out.println("\nYeayy, review berhasil dihapus!");
                    lihatFilm();
                } else {
                    System.out.println("Review kamu batal dihapus!");
                }
                return;
            }
        }
        System.out.println("ID Review tidak ditemukan!");
    }
}