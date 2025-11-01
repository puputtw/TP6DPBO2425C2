import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Random;

public class Logic implements ActionListener, KeyListener {
    // ukuran frame
    int frameWidth = 360;
    int frameHeight = 640;

    // posisi & ukuran player
    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 3;
    int playerWidth = 34;
    int playerHeight = 24;

    // posisi & ukuran pipa
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    // objek
    View view;
    Image birdImage;
    Player player;
    Image lowerPipeImage;
    Image upperPipeImage;

    // kumpulan pipa
    ArrayList<Pipe> pipes;

    // timer
    Timer gameLoop;
    Timer pipesCooldown;

    // variabel logika game
    int gravity = 1;          // gaya gravitasi
    int pipeVelocityX = -2;   // kecepatan pipa bergerak ke kiri
    boolean isGameOver = false;
    boolean gameStarted = false;
    Random random = new Random();
    int score = 0;
    JLabel scoreLabel;

    // ---------------- Constructor ----------------
    public Logic() {
        isGameOver = false;
        // gambar burung
        birdImage = new ImageIcon(getClass().getResource("/assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        // gambar pipa atas & bawah
        lowerPipeImage = new ImageIcon(getClass().getResource("/assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("/assets/upperPipe.png")).getImage();

        // list pipa
        pipes = new ArrayList<>();
        placePipes();
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.BLACK);


        // timer untuk spawn pipa baru setiap 1.5 detik
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isGameOver) {
                    placePipes(); // tambahkan pipa baru
                }
            }
        });
        pipesCooldown.start();

        // timer utama game (loop 60 fps)
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

   //seeter dan getter
    public void setView(View view) {
        this.view = view;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    // Membuat pipa baru
    public void placePipes() {
        // posisi pipa acak secara vertikal
        int randomPipeY = -200 - random.nextInt(150); // pipa atas sedikit di luar layar
        int openingSpace = 150; // jarak antar pipa

        // buat pipa atas &f bawah
        Pipe upperPipe = new Pipe(pipeStartPosX, randomPipeY, pipeWidth, pipeHeight, upperPipeImage);
        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPipeY + pipeHeight + openingSpace, pipeWidth, pipeHeight, lowerPipeImage);

        pipes.add(upperPipe);
        pipes.add(lowerPipe);
    }

    // Gerakan Player & Pipa
    public void move() {
        // Gerakkan burung
        if(isGameOver || !gameStarted)
            return;
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());

        // Batas bawah agar tidak jatuh keluar layar = game over
        if (player.getPosY() > frameHeight - player.getHeight()) {
            player.setPosY(frameHeight - player.getHeight());
            gameOver(); //kalah
        }

        // Batas atas agar tidak terbang keluar layar
        if (player.getPosY() < 0) {
            player.setPosY(0);
        }

        // Gerakkan semua pipa ke kiri
        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);
        }
        // cek apakah player melewati pipa
       for(int i = 0; i < pipes.size(); i += 2){ //ambil hanya pipa bawah
           Pipe lowerPipe = pipes.get(i + 1); // pipa bawah selalu dibuat setelah pipa atas

           //kalau burung sudah melewati pipa bawah danbelum ditandai
           if(!lowerPipe.isPassed() && lowerPipe.getPosX() + lowerPipe.getWidth() < player.getPosX()) {
               lowerPipe.setPassed(true);
               score++; //tambah 1 per pasangan
               scoreLabel.setText("Score: " + score);
           }
       }

        //cek tabrakan dengan pipa
        for(Pipe pipe : pipes) {
            if(checkCollision(player, pipe)) {
                gameOver();
                break;

            }
        }
    }

    public boolean getIsGameOver(){
        return  isGameOver;
    }

    // Cek tabrakan
    public boolean checkCollision(Player player, Pipe pipe) {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
        return playerRect.intersects(pipeRect);
    }

    // Game Over
    public void gameOver() {
        isGameOver = true;
        gameLoop.stop();
        pipesCooldown.stop();

    }

    //restart game
    public void restartGame(){
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        player.setVelocityY(0); //reset  kecepatan
        pipes.clear();
        placePipes();
        isGameOver = false;
        score = 0; // reset akor
        scoreLabel.setText("Score: 0");

        gameLoop.start();
        pipesCooldown.start();
    }

    public JLabel getScoreLabel(){
        return scoreLabel;
    }


    //  ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        if (view != null) {
            view.repaint();
        }
    }

    // KeyListener
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isGameOver) {
            gameStarted = true;
            player.setVelocityY(-12); // burung terbang ke atas
        }
        if(e.getKeyCode() == KeyEvent.VK_R && isGameOver){
            restartGame();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}
}