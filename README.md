# TP6DPBO2425C2

## Janji
Saya Putri Ramadhani dengan NIM 2410975 mengerjakan TP 6 dalam mata kuliah Desain Pemrograman Berbasis Objek(DPBO), untuk itu saya tidak akan
melakukan kecurangan seperti yang telah dispesifikasikakn,aamiin.

## Desain Program
Struktur program dibuat berbasis objek dengan beberapa class utama yaitu:
1. ## App.java
      -yaitu class utama untuk menjalankan program yang mana menjadi entry point
      program untuk menampilkan JFrame
      -penghubung awal antara logika permainan dan tampilan visual.

2. ## Logic.java
      logic adalah otak permainan yang mengatur semua interaksi antar objek di layar yang
      mana mengatur semua mekanisme permainan:
      -gerakan player(gravitasi)
      -pergerakan pipia
      -skor game
      -kondisi game over
      -deteksi tabrakan
      -menyimpan objel player dan daftar pipe

3. ## View.java
      menampilkan seluruh elemen permainan di layar yang mana
      -menangani tampilan game(JPanel)
      -menggambar player, teks skor, dan teks 'Game Over'

4. ## Player.java
      menyimpan dan mengatur properti dari burung yaitu menyimpan posisi, ukuran,
      keceparan,dan gambar burung
      -posX, posY → posisi burung di layar
      -width, height → ukuran sprite burung
      -velocityY → kecepatan vertikal burung dipengaruhi oleh gravitasi

5. ## Pipe.java
      mewakili objek pipa atas dan bawah yang menjadi rintangan bagi player
     -menyediakan getter dan setter untuk seluruh atribut
     -Posisinya terus diperbarui oleh Logic agar bergerak ke kiri
     -dapat menandai apakah sudah dilewati player untuk menambah skor

## Alur Program

   1. Program dimulai dari kelas App yang berfungsi sebagai awal program(main). Objec
      Logic dan View dibuat, lalu view ditambahkan ke dalam JFrame dan game akan
      ditampilkan di layar
      
   3. Saat objek logic dibuat, beberap hal dijalankan:
      -objek Player (burung) dibuat dan diletakkan di posisi tengah layar
      -pipe muncul dari sebelah kanan
      -ArrayList pipes dibuat untuk menyimpan semua pipa yang muncul di layar
      -timer pipesCooldown digunakan untuk memunculkan pipa baru setiap 1.5 detik
      -timer gameLoop berjalan 60 kali per detik (60 FPS) untuk mengupdate posisi
       burung dan pipa
      
   4. Burung bergerak sevara vertikal karena gravitasi
      -nilai velocityY dari objek Player terus bertambah karena pengaruh variabel
       gravity
      -posisi burung (posY) diperbarui setiap frame sesuai kecepatan tersebut
      -jika player menekan tombol Spasi, maka velocityY diset ke nilai negatif agar
       burung naik ke atas
      
   5. Sistem skor berjalan otmatis
      -setiap kali burung melewati pipa bawah (artinya posisi burung sudah melewati
       posisi pipa di sumbu X) maka pipa ditandai denganpassed = true dan skor
       bertambah 1
      -skor ditampilkan pada sudut kiri atas layar
      
   6. Deteksi tabarakan dan Game Over
      -setiap frame, program memeriksa apakah burung bersentuhan dengan salah satu pipa
      -jika terjadi tabrakan, atau jika burung jatuh menyentuh bagian bawah layar,
       maka gameOver() dijalankan
      -semua timer (gameLoop dan pipesCooldown) dihentikan dan status isGameOver
       dibah menjadi true
      -lalu muncul pesan “Game Over! Tekan R untuk restart.”

   7. Restart permainan
      -setelah Game Over, pemain dapat menekan tombol R untuk memulai ulang permainan
      -saat R ditekan, metode restartGame() akan:
        mengembalikan posisi burung ke posisi awal
        menghapus seluruh pipa yang ada di layar
        mengatur skor kembali menjadi 0
        menjalankan ulang gameLoop dan pipesCooldown
      Setelah itu permainan dapat dimulai ulang

      Dokumentasi Program:
      



      


      
      

      
      
      

 
    
      
      
      
   
      

