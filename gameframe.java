package Snake;
import javax.swing.JFrame;

public class gameframe  extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public gameframe() {
this.add(new gamepanel());   
this.setTitle("SnakeGame");
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setResizable(false);
this.pack();

this.setVisible(true);
this.setLocationRelativeTo(null);;


}
    
}
