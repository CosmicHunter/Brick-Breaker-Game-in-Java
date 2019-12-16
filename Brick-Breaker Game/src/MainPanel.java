import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;

public class MainPanel extends JPanel implements KeyListener, ActionListener {

    private BricksControl bc;

    private Boolean isGameOver = false;
    private int game_score = 0;
    private int no_of_bricks = 21;
    private int sx = 320;  // slider x position
    private int bx = 120;  // ball x and y position
    private int by = 350;

    private int bx_dir = -3;
    private int by_dir = -6;

    private Timer timer;
    private int delay = 2;

    public MainPanel(){
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
        bc = new BricksControl(3,7);
    }

    public void paint(Graphics g){
        // BackGround
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);

        // Borders
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(682,0,3,592);

        // Draw Bricks
        bc.draw((Graphics2D) g);

        // score
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Score : "+game_score,590,30);

        // Bat
        g.setColor(Color.CYAN);
        g.fillRect(sx,545,100,8);

        // Ball
        g.setColor(Color.YELLOW);
        g.fillOval(bx,by,20,20);

        if(no_of_bricks<=0){
            isGameOver = true;
            by_dir = 0;
            bx_dir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,32));
            g.drawString("You Won    Game Score: "+game_score,140,300);

            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Reset Game",200,350);
        }

        if(by>570){
            isGameOver = true;
            by_dir = 0;
            bx_dir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,32));
            g.drawString("Game Over ! , Game Score : "+game_score,140,300);

            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Reset Game",200,350);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(!isGameOver){
              if(new Rectangle(bx,by,20,20).intersects(new Rectangle(sx,545,100,8))){
                  by_dir = -by_dir;
              }

             A: for(int i=0; i <bc.map.length;i++){
                  for(int j=0;j<bc.map[0].length;j++){
                      if(bc.map[i][j] !=0){
                             int brick_x_cordinate = j * bc.brick_width + 80;
                             int brick_y_cordinate = i * bc.brick_height + 50;
                             Rectangle brick_rectangle = new Rectangle(brick_x_cordinate,brick_y_cordinate,bc.brick_width,bc.brick_height);
                             Rectangle ball_rectangle = new Rectangle(bx,by,20,20);
                             if(brick_rectangle.intersects(ball_rectangle)){
                                 bc.setBrickState(0,i,j);
                                 no_of_bricks--;
                                 game_score+=10;

                                 if(bx+19 <= brick_rectangle.x || bx+1  >=brick_rectangle.x + brick_rectangle.width){
                                     bx_dir = -bx_dir;
                                 }
                                 else{
                                     by_dir = -by_dir;
                                 }
                                 break A;
                             }
                      }
                  }
              }

              bx = bx + bx_dir;
              by = by + by_dir;
              if(bx < 0){
                  bx_dir = -bx_dir;
              }
              if(by < 0){
                  by_dir = -by_dir;
              }
              if(bx >662){
                  bx_dir = -bx_dir;
              }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Dummy Functions
    }

    @Override
    public void keyPressed(KeyEvent e) {
          if(e.getKeyCode()==KeyEvent.VK_LEFT){
               if(sx<4){
                   sx = 4;
               }
               else{
                   moveLeft();
               }
            }
           if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                if(sx>581){
                    sx = 581;
                }
                else{
                    moveRight();
                }
        }
           if(e.getKeyCode()==KeyEvent.VK_ENTER){
                if(isGameOver){
                     isGameOver = false;
                     game_score = 0;
                     no_of_bricks = 21;
                     sx = 320;  // slider x position
                     bx = 120;  // ball x and y position
                     by = 350;
                     bx_dir = -3;
                     by_dir = -6;
                     bc = new BricksControl(3,7);
                     repaint();

                }
           }
    }

    public void moveLeft(){
        if(!isGameOver){
            sx-=16;
        }
    }
    public void moveRight(){
        if(!isGameOver){
            sx+=16;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // Dummy Functions
    }
}
