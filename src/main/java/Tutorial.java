import java.awt.*;
import javax.swing.*;

public class Tutorial extends JFrame{

    private JPanel Tcontentpane;
    private JLabel Tdrawpane;
    
    private MyImageIcon bgImg;

    private int frameWidth = 800, frameHeight = 700;

    

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
        
        AddComponents();
    }

    public void AddComponents(){
        //bgImg = new ImagePanel_gif("pokemon/ILTQq.png");
        // Tdrawpane = new JLabel();
        // Tdrawpane.setIcon(bgImg);
        // Tdrawpane.setLayout(null);
        // Tcontentpane.add(Tdrawpane,BorderLayout.CENTER);

        
        Tcontentpane.add(new ImagePanel_gif());



        validate();
    
    }



    class MyImageIcon extends ImageIcon {
        public MyImageIcon(String fname) {
            super(fname);
        }
    
        public MyImageIcon(Image image) {
            super(image);
        }
    
        public MyImageIcon resize(int width, int height) {
            Image oldimg = this.getImage();
            Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            return new MyImageIcon(newimg);
        }
    }

    class ImagePanel_gif extends JPanel{
        Image img;
        public ImagePanel_gif(){
            img = Toolkit.getDefaultToolkit().createImage("pokemon/train.gif");
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(img != null){
                g.drawImage(img,0,0,frameWidth,frameHeight,this);
            }
        }
    }

}