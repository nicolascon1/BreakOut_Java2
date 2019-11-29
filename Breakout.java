import java.awt.EventQueue;
import javax.swing.JFrame;

public class Breakout extends JFrame{
    private static final long serialVersionUID = 1L;

    public Breakout() {
        initUI();
    }
    
    private void initUI() {
        add(new Game());
        setTitle("BreakOut Felipe && Nicolas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Constantes.WIDTH, Constantes.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Breakout game = new Breakout();
                game.setVisible(true);
            }
        });
    }
}