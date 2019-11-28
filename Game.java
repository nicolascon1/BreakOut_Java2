import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JPanel{
    private static final long serialVersionUID = 1L;
    
    private Timer timer;
    private String sMensagem = "GAME OVER";
    private Bola bola;
    private Raquete raquete;
    private Bloco[] blocos;
    private boolean ingame = true;
    
    public Game() {
        initGame();
    }
    
    private void initGame() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        
        blocos = new Bloco[Constantes.N_OF_BRICKS];
        setDoubleBuffered(true);;
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), Constantes.DELAY, Constantes.PERIOD);
    }
    
    public void addNotify() {
        super.addNotify();
        gameInit();
    }
    
    private void gameInit() {
        bola = new Bola();
        raquete = new Raquete();
        
        int k = 0;
        
        for(int idxi = 0; idxi < 5; ++idxi) {
            for (int idxj = 0; idxj < 6; ++idxj){
                blocos[k] = new Bloco(idxj * 40 + 30, idxi * 10 + 50);
                ++k;
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        
        if (ingame){
            drawObjects(g2d);
        }
        else{
            gameTerminou(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawObjects(Graphics2D g2d) {
        g2d.drawImage(bola.getImage(), bola.getPosicaoAtual_X(), bola.getPosicaoAtual_Y(), bola.getI_width(), bola.getI_height(), this);
        g2d.drawImage(raquete.getImage(), raquete.getPosicaoAtual_X(), raquete.getPosicaoAtual_Y(), raquete.getI_width(), raquete.getI_height(), this);
        
        for (int idx = 0; idx < Constantes.N_OF_BRICKS; ++idx) {
            if (!blocos[idx].isDestruido()){
                g2d.drawImage(blocos[idx].getImage(), blocos[idx].getPosicaoAtual_X(), blocos[idx].getPosicaoAtual_Y(), blocos[idx].getI_width(), blocos[idx].getI_height(), this);
            }
        }
    }
    
    private void gameTerminou(Graphics2D g2d) {
        Font font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics metr = getFontMetrics(font);
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(sMensagem, (Constantes.WIDTH - metr.stringWidth(sMensagem)) / 2, Constantes.WIDTH / 2);
    }
    
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            raquete.SoltouBotao(e);
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            raquete.ApertouBotao(e);
        }
    }
    
    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            bola.move();
            raquete.move();
            verificaColisao();
            repaint();
        }
    }
    
    private void stopGame() {
        ingame = false;
        timer.cancel();
    }
    
    private void verificaColisao() {
        if (bola.GetRect().getMaxY() > Constantes.BOTTO_EDGE)
            stopGame();
        
        for (int idxi = 0, idxj = 0; idxi < Constantes.N_OF_BRICKS; ++idxi) {
            if (blocos[idxi].isDestruido()){
                ++idxj;
            }

            if (idxj == Constantes.N_OF_BRICKS) {
                sMensagem = "VITORIA";
                stopGame();
            }
        }
        
        if (bola.GetRect().intersects(raquete.GetRect())) {
            int posicaoRaquete = (int)raquete.GetRect().getMinX();
            int posicaoBola    = (int)bola.GetRect().getMinX();
            
            int primeiro = posicaoRaquete + 4;
            int segundo = posicaoRaquete + 8;
            int terceiro = posicaoRaquete + 12;
            int quarto = posicaoRaquete + 16;
            
            if (posicaoBola < primeiro) {
                bola.setxDirecao(-1);
                bola.setyDirecao(-1);
            }
            
            if (posicaoBola >= primeiro && posicaoBola < segundo) {
                bola.setxDirecao(-1);
                bola.setyDirecao(-1 * bola.getyDirecao());
            }
            
            if (posicaoBola >= segundo && posicaoBola < terceiro) {
                bola.setxDirecao(0);
                bola.setyDirecao(-1);
            }
            
            if (posicaoBola >= terceiro && posicaoBola < quarto) {
                bola.setxDirecao(1);
                bola.setyDirecao(-1 * bola.getyDirecao());
            }
            
            if (posicaoBola > quarto) {
                bola.setxDirecao(1);
                bola.setyDirecao(-1);
            }
        }
        
        for (int idx = 0; idx < Constantes.N_OF_BRICKS; ++idx) {
            if (bola.GetRect().intersects(blocos[idx].GetRect())) {
                int bolaEsquerda = (int)bola.GetRect().getMinX();
                int bolaHeight   = (int)bola.GetRect().getHeight();
                int bolaWidth    = (int)bola.GetRect().getWidth();
                int bolaTopo     = (int)bola.GetRect().getMinY();
                
                Point pontoDireito  = new Point(bolaEsquerda + bolaWidth + 1, bolaTopo);
                Point pontoEsquerdo = new Point(bolaEsquerda - 1, bolaTopo);
                Point pontoTopo     = new Point(bolaEsquerda, bolaTopo - 1);
                Point pontoBottom   = new Point(bolaEsquerda, bolaTopo + bolaHeight + 1);
                
                if (!blocos[idx].isDestruido()) {
                    if(blocos[idx].GetRect().contains(pontoDireito)) {
                        bola.setxDirecao(-1);
                    } else if (blocos[idx].GetRect().contains(pontoEsquerdo)) {
                        bola.setxDirecao(1);
                    }
                    
                    if(blocos[idx].GetRect().contains(pontoTopo)) {
                        bola.setyDirecao(1);
                    } else if (blocos[idx].GetRect().contains(pontoBottom)) {
                        bola.setyDirecao(-1);
                    }
                    
                    blocos[idx].setDestruido(true);
                }
            }
        }
    }
}