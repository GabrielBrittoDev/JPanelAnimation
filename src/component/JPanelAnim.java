package component;

import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import javax.swing.JPanel;

public class JPanelAnim extends JPanel
{
    private boolean insideComp;
    private int AnimHeigth;
    private int AnimDuration;
    private Color AnimColor;
    private int index;
    
    private void addMouseAdapter() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                JPanelAnim.this.doAnimation();
            }
            
            @Override
            public void mouseExited(final MouseEvent e) {
                JPanelAnim.this.undoAnimation();
            }
        });
    }
    
    public JPanelAnim() {
        this.insideComp = false;
        this.AnimHeigth = 0;
        this.AnimDuration = 0;
        this.index = 0;
        this.AnimColor = Color.WHITE;
        this.addMouseAdapter();
    }
    
    public JPanelAnim(final int heightAnim, final int durationAnim, final Color colorAnim) {
        this.insideComp = false;
        this.index = 0;
        this.AnimHeigth = heightAnim;
        this.AnimDuration = durationAnim;
        this.AnimColor = colorAnim;
        this.addMouseAdapter();
    }

    public int getAnimHeigth() {
        return AnimHeigth;
    }

    public void setAnimHeigth(int AnimHeigth) {
        this.AnimHeigth = AnimHeigth;
    }

    public int getAnimDuration() {
        return AnimDuration;
    }

    public void setAnimDuration(int AnimDuration) {
        this.AnimDuration = AnimDuration;
    }

    public Color getAnimColor() {
        return AnimColor;
    }

    public void setAnimColor(Color AnimColor) {
        this.AnimColor = AnimColor;
    }
    
  
    
    public void doAnimation() {
        this.insideComp = true;
        new Thread() {
            @Override
            public void run() {
                while (JPanelAnim.this.index < JPanelAnim.this.AnimHeigth) {
                    if (!JPanelAnim.this.insideComp) {
                        return;
                    }
                    ++index;
                    JPanelAnim.this.repaint();
                    try {
                        Thread.sleep(JPanelAnim.this.AnimDuration);
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }
    
    public void undoAnimation() {
        this.insideComp = false;
        new Thread() {
            @Override
            public void run() {
                final int i = 0;
                while (JPanelAnim.this.index > 0) {
                    if (JPanelAnim.this.insideComp) {
                        return;
                    }
                    --index;
                    repaint();
                    try {
                        Thread.sleep(JPanelAnim.this.AnimDuration);
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        final Graphics2D g2 = (Graphics2D)g;
        g2.rotate(Math.toRadians(180.0), this.getWidth() / 2 + 1, this.getHeight() - this.getHeight() / 3);
        final Rectangle rec = new Rectangle();
        rec.setBounds(0, this.getHeight() - this.getHeight() / 3 * 2, this.getWidth() + 10, this.index);
        g2.setColor(this.AnimColor);
        g2.fill(rec);
        g2.dispose();
    }
}