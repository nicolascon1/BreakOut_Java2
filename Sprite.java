import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {
    
    protected int posicaoAtual_X = 0;
    protected int posicaoAtual_Y = 0;
    protected int image_width = 0;
    protected int image_height = 0;
    
    protected Image imagem;

    public int getPosicaoAtual_X() {
        return posicaoAtual_X;
    }

    public void setPosicaoAtual_X(int posicaoAtual_X) {
        this.posicaoAtual_X = posicaoAtual_X;
    }

    public int getPosicaoAtual_Y() {
        return posicaoAtual_Y;
    }

    public void SetPosicaoAtual_Y(int posicaoAtual_Y) {
        this.posicaoAtual_Y = posicaoAtual_Y;
    }

    public int getI_width() {
        return image_width;
    }

    public void setI_width(int i_width) {
        this.image_width = i_width;
    }

    public int getI_height() {
        return image_height;
    }

    public void setI_height(int i_height) {
        this.image_height = i_height;
    }

    public Image getImage() {
        return imagem;
    }

    public void setImage(Image image) {
        this.imagem = image;
    }
    
    public Rectangle GetRect() {
        return new Rectangle(posicaoAtual_X, posicaoAtual_Y, (imagem.getWidth(null) / 10), (imagem.getHeight(null) / 10));
    }
}
