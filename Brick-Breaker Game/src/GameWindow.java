import javax.swing.*;

public class GameWindow extends JFrame {
    private MainPanel panel;
    public GameWindow(){
        super("Brick Breaker");
        panel = new MainPanel();
        this.setBounds(10,10,700,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
    }

    public static void main(String [] args){
           GameWindow gameWindow = new GameWindow();
           gameWindow.setVisible(true);
           gameWindow.setResizable(false);
    }
}
