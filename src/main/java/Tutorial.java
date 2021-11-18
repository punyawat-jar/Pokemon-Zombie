import java.awt.*;
import javax.swing.*;

public class Tutorial extends JFrame{

    private JPanel Tcontentpane;
    private JLabel Tdrawpane;
    
    private int frameWidth = 600, frameHeight = 500;

    

    public static void main(String[] args){
        new Tutorial();
    }

    public Tutorial(){
        setTitle("Tutorial");
        setBounds(50,50,frameWidth,frameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Tcontentpane = (JPanel) getContentPane();
        Tcontentpane.setLayout(new BorderLayout());
        

    }
}