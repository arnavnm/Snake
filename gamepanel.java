package Snake;

import java.awt.*;
import java.awt.event.*;

import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;


public class gamepanel extends JPanel implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final int SCREEN_WIDTH = 600;
    private final int SCREEN_HEIGHT = 600;
    private final int UNIT_SIZE = 25;
    private final int GAME_UNITS = SCREEN_HEIGHT * SCREEN_WIDTH / 25;
    private int DELAY = 90;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int BodyParts = 3; // starting no of body parts
    int ApplesEaten = 0;
    int AppleX;
    int AppleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    public gamepanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener((KeyListener) new MyKeyAdapter());
        this.startGame();

    }

    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }
    public void draw (Graphics g){

        if(running){

        for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i ++){
            g.setColor(Color.RED);
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
        }
        g.setColor(Color.GREEN);
        g.fillOval(AppleX, AppleY, UNIT_SIZE, UNIT_SIZE);

        for(int i = 0; i < BodyParts; i ++){
            if(i == 0 ){
                //Head of snake
                g.setColor(Color.BLUE);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
            else{
                g.setColor(Color.CYAN);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Helvetica", Font.BOLD, 30));
        FontMetrics m = getFontMetrics(g.getFont());
        g.drawString("Score " + ApplesEaten, (SCREEN_WIDTH - m.stringWidth("Score " + ApplesEaten))/2, g.getFont().getSize());
    }

    

    else{

        gameOver(g);
    }

    }

    public void move(){

        for(int i = BodyParts; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction){
            case 'U' :
                y[0] = y[0] - UNIT_SIZE; 
                break;

            case 'D' :
                y[0] = y[0] + UNIT_SIZE; 
                break; 
                
            case 'L' :
                x[0] = x[0] - UNIT_SIZE;  
                break;
                
            case 'R' :
                x[0] = x[0] +UNIT_SIZE;    
                break;


        }

    }

    public void newApple(){

        AppleX = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
        AppleY = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;

    }

    public void checkApple(){

        if(x[0] == AppleX && y[0] == AppleY){
            ApplesEaten ++;
            BodyParts ++;
            newApple();
        }

    }

    public void checkCollisions(){

        for(int i = BodyParts; i >0; i --){
            if(x[0] == x[i] && y[0] == y[i]){

                running = false;

            }
            if(x[0] < 0){
                running =false;
            }
            if(x[0] > SCREEN_WIDTH){
                running =false;
            }
            if(y[0] < 0){
                running =false;
            }
            if(y[0] > SCREEN_HEIGHT){
                running =false;
            }

            if(!running){
                timer.stop();
            }
        }

    }

    public void gameOver(Graphics g){

        g.setColor(Color.RED);
        g.setFont(new Font("Helvetica", Font.BOLD, 70));
        FontMetrics m = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - m.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Helvetica", Font.BOLD, 30));
        FontMetrics m2 = getFontMetrics(g.getFont());
        g.drawString("Score " + ApplesEaten, (SCREEN_WIDTH - m2.stringWidth("Score " + ApplesEaten))/2, g.getFont().getSize());

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(running){

            move();
            checkApple();
            checkCollisions();
        }

        repaint();

    }

    public class MyKeyAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e){

            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                
                if(direction != 'R'){
                direction = 'L';


                }
                break;

                case KeyEvent.VK_RIGHT:
                if(direction != 'L'){
                    direction = 'R';
                    
    
                    }
                    break;

                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
        
                        }  
                        break;  
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                         direction = 'D';
            
                            }  
                            break; 

            }

        }
    }
}
