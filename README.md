*Mini Project (Minpro) 3 - Pemrograman Berbasis Objek (PBO)*  

---

*Nama: Atha Rina Sarwahita*  
*NIM: 2509116047*

# 🎬🍿 Sistem Tracker & Review Film  

> Sistem berbasis CLI (Java) untuk mencatat film yang ditonton dan menulis review, sistem ini dibangun dengan prinsip PBO dan arsitektur **MVC**.

## Deskripsi Singkat ─ ★ ˙ ̟ !!
Program ini membantu pengguna mengelola daftar film, baik **Feature Film** maupun **Animated Film**, sekaligus menambahkan **review** untuk tiap film. Terdapat juga fitur Create, Read, Update, dan Delete (CRUD) yang lengkap pada sistem ini, sperti menambah film, menambah review, lihat film dan review, update review, serta delete review. Seluruh input dicek oleh class validasi khusus agar data tetap konsisten.

## Struktur Package ─ ★ ˙ ̟ !!
```
src/
├── main/
│   └── Main.java              → Entry point program
├── models/
│   ├── Film.java              → Superclass dan abstract semua film
│   ├── FeatureFilm.java       → Subclass dari Superclass Film
│   ├── AnimatedFilm.java      → Subclass dari Superclass Film
│   ├── Review.java            → Data review sebuah film
│   └── Displayable.java       → Interface untuk tampilan data
├── view/
│   └── FilmView.java          → Menampilkan menu & tampilan
└── controller/
    ├── FilmController.java    → Mengatur logika & alur program
    └── ValidasiInput.java     → Memvalidasi seluruh input
```

Struktur package di atas masing-masing memiliki peran sebagai berikut:
| Package | Peran |
|---|---|
| `main` | Menjalankan program dan menghubungkan komponen |
| `models` | Data dan aturan bisnis (Film, Review, turunan, interface) |
| `view` | Antarmuka console, tanpa logika bisnis |
| `controller` | Jembatan view, mengolah logika bisnis, mengolah data dari model, dan validasi input |

---

## Alur Program ─ ★ ˙ ̟ !!

```text
┌──────────────────────────────┐
│         Main.java            │  Program dijalankan
│  (membuat View & Controller) │
└──────────────┬───────────────┘
               ▼
┌──────────────────────────────┐
│         FilmView             │◄─────────────────────────────┐
│      Menampilkan menu        │                              │
└──────────────┬───────────────┘                              │
               ▼                                              │
        ┌─────────────┐                                       │
        │ Pilih menu? │── 6. Keluar -> Program selesai        │
        └──────┬──────┘                                       │
               ▼                                              │
┌──────────────────────────────┐                              │
│        FilmView              │                              │
│    Membaca input user        │◄───────────┐                 │
└──────────────┬───────────────┘            │                 │
               ▼                            │                 │
┌──────────────────────────────┐            │                 │
│   ValidasiInput              │            │                 │
│   Memeriksa input            │            │                 │
└───────┬──────────────┬───────┘            │                 │
        │ Tidak valid  │ Valid              │                 │
        ▼              │                    │                 │
┌───────────────┐      │                    │                 │
│ Tampilkan     │──────┼────────────────────┘                 │
│ pesan error   │      │   (user mengulang input)             │
└───────────────┘      ▼                                      │
┌──────────────────────────────┐                              │
│         FilmController       │                              │
│  Memproses permintaan user   │                              │
└──────────────┬───────────────┘                              │
               ▼                                              │
┌──────────────────────────────┐                              │
│      Model                   │                              │
│ Film / FeatureFilm /         │                              │
│ AnimatedFilm / Review        │                              │
│ (data dibuat/diubah/dihapus) │                              │
└──────────────┬───────────────┘                              │
               ▼                                              │
┌──────────────────────────────┐                              │
│         FilmView             │                              │
│  Menampilkan hasil ke user   │──────────────────────────────┘
└──────────────────────────────┘     Kembali ke menu utama
```
1. `Main` membuat objek controller dan view, lalu menjalankan menu utama.
2. `FilmView` menampilkan menu dan menerima pilihan user.
3. Input diperiksa `ValidasiInput`; jika salah, user diminta mengulang.
4. `FilmController` meneruskan data valid ke model (`FeatureFilm` / `AnimatedFilm` / `Review`).
5. Hasil ditampilkan kembali lewat `FilmView`, lalu program kembali ke menu.

---

## Penerapan Encapsulation & Inheritance ─ ★ ˙ ̟ !!
**Encapsulation**  

Atribut pada `Film` (`idFilm`, `judul`, `sutradara`, `tahunRilis`, `genre`, `durasi`) dideklarasikan `protected`, sehingga tidak bisa diakses langsung dari luar package tetapi tetap bisa dipakai oleh subclass-nya. Atribut khas di `FeatureFilm` (`pemeranUtama`, `karakterLiveAction`), di `AnimatedFilm` (`pengisiSuara`, `karakterAnimasi`, `gayaAnimasi`), dan seluruh atribut di `Review` dideklarasikan `private`. Data tersebut hanya bisa dibaca dan diubah lewat getter dan setter.

Setter pada program ini tidak hanya mengisi nilai, tetapi memeriksa dulu apakah nilainya valid. Setter yang berisi validasi mengembalikan nilai `boolean` (`true` jika diterima, `false` jika ditolak), sehingga controller tahu kapan harus meminta user mengisi ulang. Validasi yang ada di dalam setter:
- `setTahunRilis()` di `Film`: tahun harus antara 1888 (tahun film pertama dibuat) sampai tahun sekarang. Batas atasnya diambil otomatis dari `Year.now()`, jadi tidak perlu diubah manual setiap tahun.
- `setDurasi()` di `Film`: durasi minimal 30 menit, mengikuti standar film pendek.
- `setRating()` di `Review`: rating harus antara 1.0 sampai 5.0.

Validasi ini juga berjalan saat objek dibuat, karena constructor `Film` memanggil `setTahunRilis()` dan `setDurasi()`, sedangkan constructor `Review` memanggil `setRating()`. Dengan begitu, objek tidak bisa dibuat dengan data yang melanggar aturan.

---

Keyword `final` dipakai pada nilai yang tidak boleh berubah setelah dibuat:
- `idFilm` di `Film`, serta `idReview`, `idFilm`, dan `isRewatch` di `Review`, sehingga ID dan status rewatch tetap sama selama objek hidup.
- Method `getIdFilm()` di `Film`, sehingga tidak bisa di-override oleh subclass.
- Parameter `idFilm` pada constructor `Film`, `FeatureFilm`, dan `AnimatedFilm`.
- Atribut `daftarFilm`, `daftarReview`, dan `view` di `FilmController`, sehingga referensinya tidak bisa diganti dan hanya isi list yang berubah.

ID film dan review juga dibuat otomatis oleh `FilmController` lewat `nextFilmId` dan `nextReviewId` (auto-increment). Jadi user tidak perlu, dan tidak dapat memasukkan ID sendiri.  

---

**Inheritance**
- `FeatureFilm extends Film` dan `AnimatedFilm extends Film`. Konsep pewarisan ini diterapkan pada Superclass `Film`, lalu diwariskan ke Subclass `FeatureFilm` dan `AnimatedFilm`
  
  ```java
  public class FeatureFilm extends Film {
    private String pemeranUtama;
    private String karakterLiveAction;
  ```
  ```java
  public class AnimatedFilm extends Film {
    private String pengisiSuara;
    private String karakterAnimasi;
    private String gayaAnimasi;
  ```

- Atribut dan method umum (judul, tahun, durasi, dll.) diwarisi dari `Film`; tiap subclass menambah atribut khasnya.
  ```java
      public FeatureFilm(final int idFilm, String judul, String sutradara, int tahunRilis, String genre, int durasi, String pemeranUtama, String karakterLiveAction) {
          super(idFilm, judul, sutradara, tahunRilis, genre, durasi);
          this.pemeranUtama = pemeranUtama;
          this.karakterLiveAction = karakterLiveAction;
      }
  ```
  Pada Subclass FeatureFilm, terdapat atribut khas yaitu Pemeran Utama (Aktor), dan Karakter Live Action (Karakter yg diperankan)

  ```java
      public AnimatedFilm(final int idFilm, String judul, String sutradara, int tahunRilis, String genre, int durasi, String pengisiSuara, String karakterAnimasi, String gayaAnimasi) {
          super(idFilm, judul, sutradara, tahunRilis, genre, durasi);
          this.pengisiSuara = pengisiSuara;
          this.karakterAnimasi = karakterAnimasi;
          this.gayaAnimasi = gayaAnimasi;
      }
  ```
  Pada Subclass AnimatedFilm, terdapat 3 atribut khas yaitu Pengisi Suara, Karakter Animasi, dan Gaya Animasi.

## Penerapan Polymorphism & Abstraction ─ ★ ˙ ̟ !!
**Abstraction**
- `Film` berupa `abstract class` dengan `abstract method` `tampilkanDetailSpesifik()`  yang wajib diimplementasikan subclass.
  - **Abstract Class**
  ```java
  public abstract class Film implements Displayable {
    protected final int idFilm;
    protected String judul;
    protected String sutradara;
    protected int tahunRilis;
    protected String genre;
    protected int durasi;
  ```
  - **Abstract Method**
  ```java
  public abstract void tampilkanDetailSpesifik();
  ```

**Polymorphism**
- *Overriding*: Karena `tampilkanDetailSpesifik()` bersifat abstract, `FeatureFilm` dan `AnimatedFilm` **wajib mengimplementasikannya** dengan `@Override`. Tiap subclass mengisinya sesuai atribut masing-masing, konsep Override ini memungkinkan nama method yang sama, tetapi hasilnya berbeda.
  - Subclass AnimatedFilm
    ```java
      @Override
      public void tampilkanDetailSpesifik() {
          System.out.println("     Voice     : " + pengisiSuara + " as " + karakterAnimasi);
          System.out.println("     Tipe      : Animated Film (" + gayaAnimasi + ")");
          System.out.println("------------------------------------------------------------------");
      }
    ```
  - Subclass FeatureFilm
    ```java
      @Override
      public void tampilkanDetailSpesifik() {
          System.out.println("     Cast      : " + pemeranUtama + " as " + karakterLiveAction);
          System.out.println("     Tipe      : Feature Film (Live-Action)");
          System.out.println("------------------------------------------------------------------");
      }
    ```
- *Overloading* adalah membuat beberapa method dengan **nama yang sama** tetapi **parameter berbeda**. Di program ini, method `tampilkanInfo()` diterapkan di dua class, yaitu `Film` dan `Review`, dengan dua versi:

  | Versi | Parameter | Fungsi |
  |---|---|---|
  | `tampilkanInfo()` | tanpa parameter | Menampilkan info **lengkap** (default) |
  | `tampilkanInfo(boolean ringkas)` | satu parameter `boolean` | Menampilkan info **ringkas** jika `true`, **lengkap** jika `false` |

  Versi tanpa parameter tidak menulis ulang logika tampilan, tetapi memanggil versi berparameter dengan nilai `false`. Dengan begitu logika tampilan hanya ditulis sekali dan tidak ada kode yang duplikat.

## ★ | Nilai Tambah

Nilai tambah yang diterapkan pada program ini adalah **Interface `Displayable`**.

| Fitur | Letak | Keterangan |
|---|---|---|
| Interface `Displayable` | `models/Displayable.java` | Kontrak tampilan data yang diimplementasikan oleh class `Film` dan `Review` |

### Penerapan Interface `Displayable`

✦. `models/Displayable.java`
```java
public interface Displayable {
    void tampilkanInfo();                    //tampilan lengkap
    void tampilkanInfo(boolean ringkas);     //tampilan ringkas / lengkap
}
```
`Displayable` merupakan sebuah **interface** pada package `models` yang berisi kontrak: setiap class yang mengimplementasikannya **wajib** menyediakan dua method `tampilkanInfo`. Interface hanya menentukan *apa* yang harus ada, sedangkan *cara* menampilkannya ditentukan oleh masing-masing class.

✦. `models/Film.java` 
```java
public abstract class Film implements Displayable {   //mengimplementasikan interface
```
```java
    @Override
    public void tampilkanInfo(boolean ringkas) {
        //...
    }
}
```
`Film` mengimplementasikan `Displayable` untuk menampilkan data sebuah film. Pada mode **ringkas**, film tampil dalam satu baris (ID, judul, tahun rilis, genre). Pada mode **lengkap**, film tampil beserta sutradara, genre, durasi, dan detail khas masing-masing jenis film (dari `tampilkanDetailSpesifik()`).


✦. `models/Review.java`
```java
public class Review implements Displayable {          //mengimplementasikan interface
```
```java
    @Override
    public void tampilkanInfo(boolean ringkas) {
        // ...
    }
}
```
`Review` juga mengimplementasikan `Displayable` untuk menampilkan ulasan. Pada mode **ringkas**, review tampil dalam satu baris (ID review, penanda rewatch, dan rating). Pada mode **lengkap**, review tampil beserta penanda `[REWATCH]` (jika ada), rating, dan isi ulasan.  

---

## Fitur Baru: Rewatch Log ─ ★ ˙ ̟ !!

Satu film bisa ditonton lebih dari sekali, dan kesan penonton di tiap tontonan bisa berbeda. Karena itu program ini mendukung **Rewatch Log**: sebuah film boleh punya lebih dari satu review, dan review yang dibuat setelah review pertama otomatis ditandai sebagai **REWATCH**. User tidak perlu memilih sendiri, karena sistem yang menentukannya.

### Cara Kerja
Saat user memilih menu **Tambah Review** dan memasukkan ID film, `FilmController` memeriksa daftar review yang sudah ada di `daftarReview`. Jika sudah ada review dengan `idFilm` yang sama, status `isRewatch` diatur menjadi `true`. Jika belum ada, statusnya `false` (review pertama). Jika film ternyata sudah pernah diulas, program menampilkan pemberitahuan bahwa review baru akan disimpan sebagai **[REWATCH]**.

✦. `controller/FilmController.java`
```java
boolean isRewatch = false;
for (Review r : daftarReview) {
    if (r.getIdFilm() == cariFilm) {
        isRewatch = true;      // film ini sudah pernah diulas
        break;
    }
}

if (isRewatch) {
    System.out.println("\nFilm ini sudah pernah kamu ulas. Review baru akan disimpan sebagai [REWATCH].");
}
```

Status tersebut dikirim ke constructor `Review`, lalu review baru ditambahkan ke `daftarReview`. Atribut `isRewatch` di class `Review` bersifat `private final`, sehingga statusnya hanya ditentukan satu kali saat review dibuat dan tidak bisa diubah lagi. Nilainya hanya bisa dibaca lewat method `isRewatch()`. Dengan begitu, satu film bisa memiliki beberapa review, dan setiap review tahu apakah ia review pertama atau hasil tontonan ulang.

### Tampilan Review Rewatch
Class `Review` menampilkan penanda rewatch pada kedua mode `tampilkanInfo()`:
- **Mode lengkap:** review diberi garis pemisah dan label `[REWATCH] [ID Review: ...]`, sehingga mudah dibedakan dari review pertama.
  
    <img width="474" height="422" alt="image" src="https://github.com/user-attachments/assets/fb0bcce2-c702-4f93-bbf3-7dce8f4f52fe" />
  
- **Mode ringkas:** ID review diberi tag `(Rewatch)`.
  
  <img width="475" height="162" alt="image" src="https://github.com/user-attachments/assets/46f3f7d4-7070-40eb-a2d4-a1638b278f71" />



✦. `models/Review.java`
```java
    @Override
    public void tampilkanInfo(boolean ringkas) {
            if (ringkas) {
                String tag = isRewatch ? " (Rewatch)" : "";
                System.out.println("   > Review " + idReview + tag + " | Rating: " + getRating() + "/5.0");
            } else {
                    if (isRewatch) {
                        System.out.println("     -------------------------------------------------------------");
                        System.out.println("     [REWATCH] [ID Review: " + idReview + "]");
                    } else {
                        System.out.println("     [ID Review: " + idReview + "]");
                    }
                    System.out.println("     Rating : " + getRating());
                    System.out.println("     Ulasan : " + ulasan);
                    }
    }
```
### Contoh Skenario
1. User menambah review untuk film **13 Bom di Jakarta**, yang sudah punya 1 review dari data awal.
2. Program menampilkan pemberitahuan **[REWATCH LOG]**.
3. User mengisi rating dan ulasan baru.
4. Pada menu **Lihat Film & Review**, film tersebut menampilkan dua review: review pertama biasa, review kedua dengan label `[REWATCH]`.

---

## Demonstrasi Program ─ ★ ˙ ̟ !!
### 1. Menu Utama

  <img width="481" height="162" alt="image" src="https://github.com/user-attachments/assets/83f6a1b2-690d-4e49-9540-6b3b12520f1a" />

### 2. Tambah Film
- **Berhasil**
  
  <img width="476" height="333" alt="image" src="https://github.com/user-attachments/assets/e4670fdc-30e9-4dfa-88f9-b9e8bbcf798d" />

  Hasil Input Film:
  
  <img width="474" height="491" alt="image" src="https://github.com/user-attachments/assets/07ace25d-9f58-4feb-973f-57845f0eb114" />

- **Uji validasi**
  - Judul kosong: Input tidak boleh kosong!
    
    <img width="469" height="36" alt="image" src="https://github.com/user-attachments/assets/dab01e64-c264-423c-8848-e921b127f0e7" />

  - Tahun rilis di masa depan atau 0: Tahun rilis harus antara 1888 hingga tahun 2026.
  
    <img width="478" height="40" alt="image" src="https://github.com/user-attachments/assets/b969e614-aea6-4fde-8acc-501311b6fe9f" />

  - Durasi negatif: Durasi film harus minimal 30 menit.
  
    <img width="470" height="36" alt="image" src="https://github.com/user-attachments/assets/699b2e3f-4c54-481c-9f8c-3e99ce1d5fa9" />

  - Pilihan menu tidak valid: Pilihan jenis film tidak valid!
  
    <img width="472" height="140" alt="image" src="https://github.com/user-attachments/assets/85eb0d13-b10e-4b6d-81ce-4fd7aee66a52" />


### 3. Lihat Daftar Film  

<img width="474" height="530" alt="image" src="https://github.com/user-attachments/assets/392797d1-57f1-4a34-909c-e8c3796e8681" />  


Menampilkan seluruh film yang tersimpan beserta review-nya. Film bertipe `FeatureFilm` dan `AnimatedFilm` tampil dalam satu daftar, tetapi detail spesifiknya berbeda. Ini bukti **polymorphism** dari `tampilkanDetailSpesifik()`.

### 4. Update Review  

  <img width="476" height="516" alt="image" src="https://github.com/user-attachments/assets/21f80d11-a489-430f-9f9a-8010449681ec" />  

Mengubah review yang sudah ada pada sebuah film. User memilih review yang ingin diubah melalui ID Review, lalu mengisi review baru.  

Hasil Update Review:  

  <img width="473" height="357" alt="image" src="https://github.com/user-attachments/assets/0c2054b4-6070-4b21-9597-a7ef9d720ff1" />  

Dapat terlihat pada gambar di atas bahwa review pada film menampilkan data terbaru yang telah di-update.  


### 5. Hapus Review  

<img width="476" height="473" alt="image" src="https://github.com/user-attachments/assets/f54c0e76-9a61-4b41-8ea9-79391f57578c" />  

Menghapus review dari sebuah film. User memilih review yang akan dihapus melalui ID Review.

Hasil Hapus Review:  
<img width="480" height="324" alt="image" src="https://github.com/user-attachments/assets/0fc85722-fa90-402d-9571-17489d22435c" />  

Dapat terlihat pada gambar di atas bahwa review yang dipilih untuk dihapus sudah tidak muncul pada daftar.

### 6. Keluar Program  
<img width="514" height="221" alt="image" src="https://github.com/user-attachments/assets/d0aed973-79df-4c55-91e9-80e8d9e6930d" />  

Memilih menu keluar menghentikan program dan menampilkan pesan penutup.

## Kesimpulan ─ ★ ˙ ̟ !!

Sistem Tracker & Review Film berhasil dibangun sebagai aplikasi CLI berbasis Java dengan arsitektur **MVC**. Pemisahan peran antara `models`, `view`, dan `controller` membuat kode lebih rapi, mudah dibaca, dan mudah dikembangkan, karena setiap bagian punya tanggung jawab yang jelas. Seluruh fitur CRUD berjalan dengan baik: menambah film, menambah review, melihat film beserta review, mengubah review, dan menghapus review.

Dari sisi konsep PBO, program ini menerapkan:
- **Encapsulation**, lewat atribut `protected` dan `private`, getter/setter berisi validasi, serta penggunaan keyword `final` agar data penting tidak berubah sembarangan.
- **Inheritance**, lewat class `Film` yang diwariskan ke `FeatureFilm` dan `AnimatedFilm`, sehingga kode umum cukup ditulis satu kali.
- **Abstraction**, lewat `Film` sebagai abstract class dengan abstract method `tampilkanDetailSpesifik()`.
- **Polymorphism**, lewat overriding `tampilkanDetailSpesifik()` pada tiap subclass dan overloading `tampilkanInfo()` pada `Film` dan `Review`.
- **Interface** `Displayable` sebagai nilai tambah, yang membuat `Film` dan `Review` punya cara menampilkan data yang seragam.

Validasi input yang dilakukan oleh `ValidasiInput` dan setter di dalam model juga membuat data yang tersimpan tetap konsisten, sehingga program tidak mudah error akibat input yang salah. Dengan begitu, program ini tidak hanya memenuhi ketentuan Mini Project 3, tetapi juga menjadi dasar yang baik untuk dikembangkan lebih lanjut, misalnya dengan penyimpanan data ke file atau database.

---
