import javax.swing.ImageIcon;

public class Bola extends Sprite{

    private int xDirecao = 1;
    private int yDirecao = -1;
    
    public int getxDirecao() {
        return xDirecao;
    }

    public void setxDirecao(int xDirecao) {
        this.xDirecao = xDirecao;
    }

    public int getyDirecao() {
        return yDirecao;
    }

    public void setyDirecao(int yDirecao) {
        this.yDirecao = yDirecao;
    }
    
    private void resetaEstado() {
        posicaoAtual_X = Constantes.INIT_BALL_X;
        posicaoAtual_Y = Constantes.INIT_BALL_Y;
    }
    
    public Bola() {
        ImageIcon iconBola = new ImageIcon(getClass().getResource("~/imagens/bola.png"));
        imagem = iconBola.getImage();
        image_width = imagem.getWidth(null) / 10;
        image_height = imagem.getHeight(null) / 10;
        
        resetaEstado();
    }
    
    public void move() {
        posicaoAtual_X += getxDirecao();
        posicaoAtual_Y += getyDirecao();
        
        if (posicaoAtual_X == 0){
            setxDirecao(1);
        }
        
        if (posicaoAtual_X == Constantes.WIDTH - image_width){
            setxDirecao(-1);
        }
        
        if (posicaoAtual_Y == 0){
            setyDirecao(1);
        }
    }
}
