import javax.swing.ImageIcon;

public class Bloco extends Sprite{
    private boolean bDestruiu = false;
    
    public Bloco(int x, int y) {
        this.posicaoAtual_X = x;
        this.posicaoAtual_Y = y;
        
        ImageIcon iconBloco = new ImageIcon(getClass().getResource("imagens/bloco.png"));
        imagem = iconBloco.getImage();
        image_width = imagem.getWidth(null) / 10;
        image_height = imagem.getHeight(null) / 10;
        
        bDestruiu = false;
    }
    
    public boolean isDestruido() {
        return bDestruiu;
    }
    
    public void setDestruido(boolean bDestruiu) {
        this.bDestruiu = bDestruiu;
    }
}
