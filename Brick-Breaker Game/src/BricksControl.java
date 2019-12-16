import java.awt.*;

public class BricksControl {
    public int map[][];
    public int brick_width;
    public int brick_height;
    public BricksControl(int row , int col){
        map = new int[row][col];
        for(int i = 0;i < row ; i++){
            for(int j =0 ;j<col;j++){
                map[i][j] = 1;     // 1 means brick is there and not destroyed by the ball
            }
        }
        brick_width = 540 / col;
        brick_height = 150 / row;
    }

    public void draw(Graphics2D g){
        for(int i =0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]!=0){
                    g.setColor(Color.WHITE);
                    g.fillRect(j*brick_width+80,i*brick_height+50,brick_width,brick_height);

                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j*brick_width+80,i*brick_height+50,brick_width,brick_height);
                }
            }
        }
    }

    public void setBrickState(int val,int row,int col){
        map[row][col] = val;
    }
}
