import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Raquete extends Sprite{
    
    private int direcaoX = 0;
    
    public Raquete() {
        ImageIcon iconRaquete = new ImageIcon(getClass().getResource("~/imagens/raquete.png"));
        imagem = iconRaquete.getImage();
        image_width = imagem.getWidth(null) / 10;
        image_height = imagem.getHeight(null) / 10;
        
        resetaEstado();
    }
    
    public void move() {
        posicaoAtual_X += direcaoX;
        
        if (posicaoAtual_X < 0){
            posicaoAtual_X = 0;
        }
        
        if (posicaoAtual_X >= Constantes.WIDTH - image_width){
            posicaoAtual_X = Constantes.WIDTH - image_width;
        }
    }
    
    public void ApertouBotao(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT){
            direcaoX = -1;
        }
        
        if (key == KeyEvent.VK_RIGHT){
            direcaoX = 1;
        }
    }
    
    public void SoltouBotao(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT){
            direcaoX = 0;
        }
    }
    
    private void resetaEstado() {
        posicaoAtual_X = Constantes.INIT_PADDLE_X;
        posicaoAtual_Y = Constantes.INIT_PADDLE_Y;
    }
}