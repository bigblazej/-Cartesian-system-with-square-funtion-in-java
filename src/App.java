import java.awt.*;
import java.awt.event.*;

public class App extends Frame {
    Color color;
    Color color2;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                App app = new App();
                app.init();
            }
        });
    }
//obsługa X w prawym górnym
    public App() {
        addWindowListener(new WindowAdapter() {
            //@Override to nadpisywanie metody superklasy
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); 
            }
        });
    }
    public void init() {
        //wielkość ekranu = 4*okno
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenwidth = screenSize.getWidth();
        double screenheight = screenSize.getHeight();
        int widthint = (int) screenwidth;
        int heightint = (int) screenheight;
        setSize(widthint / 2, heightint / 2);
        setBackground(Color.WHITE);
        color = new Color(0, 0, 0);
        setVisible(true);
        repaint();//przywołuje metodę paint albo update tak szybko jak to tylko możliwe
    }
    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g2 = (Graphics2D) gr;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gr.setColor(color);
        Point p = new Point(0, 0);
        Dimension r = this.getSize();
        //rysowanie osi
        gr.drawLine(p.x, p.y + r.height / 2, r.width, p.y + r.height / 2);
        gr.drawLine(p.x + r.width / 2, p.y, p.x + r.width / 2, r.height);
        //rysowanie linii na osiach *4
        for (int i = r.width/2; i < r.width; i += r.width / 40) {
            double subheight = r.height/1.96;
            int subheightint = (int)subheight;
            double subheight2 = r.height/2.04;
            int subheightint2 = (int)subheight2;
            gr.drawLine(i, r.height - subheightint, i, r.height - subheightint2);
        }
        for (int i = r.width/2; i > 0; i -= r.width / 40) 
        {
            double subheight = r.height/1.96;
            int subheightint = (int)subheight;
            double subheight2 = r.height/2.04;
            int subheightint2 = (int)subheight2;
            gr.drawLine(i, r.height - subheightint, i, r.height - subheightint2);
        }
        for(int i = r.height/2; i < r.height; i+= r.height/40)
        {
            double subwidth = r.width/1.96;
            int subwidthint = (int)subwidth;
            double subwidth2 = r.width/2.04;
            int subwidthint2 = (int)subwidth2;
            gr.drawLine(r.width - subwidthint, i, r.width - subwidthint2, i);
        }
        for(int i = r.height/2; i > 0; i-= r.height/40)
        {
            double subwidth = r.width/1.96;
            int subwidthint = (int)subwidth;
            double subwidth2 = r.width/2.04;
            int subwidthint2 = (int)subwidth2;
            gr.drawLine(r.width - subwidthint, i, r.width - subwidthint2, i);
        }
        //obliczanie srodka okna
        int windowCenterX = r.width/2;
        int windowCenterY = r.height/2;
        int lastX = windowCenterX;
        int lastY = windowCenterY;
        //rysowanie funkcji
        for (int x = -windowCenterX; x <= windowCenterX; x++) {
            color2 = new Color(245,143,9);
            gr.setColor(color2);
            int pointX = windowCenterX + x;
            double functionScaleX = (double) x / (double) windowCenterX; // Scale x-coordinate
            double y = (functionScaleX * functionScaleX) * windowCenterY / 0.1 - (r.height / 10); // Scale y-coordinate
            int pointY = windowCenterY - (int) y;      
            if (pointY >= 0 && pointY <= r.height) {
                //rysowanie punktów
                gr.drawLine(pointX, pointY, pointX, pointY);
            }
            if (x != -windowCenterX) {
                //łączenie punktów
                gr.drawLine(lastX, lastY, pointX, pointY);
            }
            lastX = pointX;
            lastY = pointY;
        }
        //rysowanie trójkątów na końcu osi
        int x[] = { r.width-5, r.width-25, r.width-25};  
        int y[] = { r.height/2, r.height/2+10, r.height/2-10}; 
        int numberofpoints = 3; 
        gr.setColor(color);  
        gr.fillPolygon(x, y, numberofpoints); 

        int x1[] = { r.width/2, r.width/2-10, r.width/2+10};  
        int y1[] = { 30, 50, 50}; 
        int numberofpoints1 = 3; 
        gr.setColor(color);  
        gr.fillPolygon(x1, y1, numberofpoints1);
    }
}
