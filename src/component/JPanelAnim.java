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
    private int heightAnim;
    private int durationAnim;
    private int index;
    private Color colorAnim;
    
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
        this.heightAnim = 0;
        this.durationAnim = 0;
        this.index = 0;
        this.colorAnim = Color.WHITE;
        this.addMouseAdapter();
    }
    
    public JPanelAnim(final int heightAnim, final int durationAnim, final Color colorAnim) {
        this.insideComp = false;
        this.heightAnim = 0;
        this.durationAnim = 0;
        this.index = 0;
        this.colorAnim = Color.WHITE;
        this.heightAnim = heightAnim;
        this.durationAnim = durationAnim;
        this.colorAnim = colorAnim;
        this.addMouseAdapter();
    }
    
    public int getHeightAnim() {
        return this.heightAnim;
    }
    
    public void setHeightAnim(final int heightAnim) {
        this.heightAnim = heightAnim;
    }
    
    public Color getColorAnim() {
        return this.colorAnim;
    }
    
    public void setColorAnim(final Color colorAnim) {
        this.colorAnim = colorAnim;
    }
    
    public int getDurationAnim() {
        return this.durationAnim;
    }
    
    public void setDurationAnim(final int durationAnim) {
        this.durationAnim = durationAnim;
    }
    
    public void doAnimation() {
        this.insideComp = true;
        new Thread() {
            @Override
            public void run() {
                while (JPanelAnim.this.index < JPanelAnim.this.heightAnim) {
                    if (!JPanelAnim.this.insideComp) {
                        return;
                    }
                    final JPanelAnim this$0 = JPanelAnim.this;
                    ++this$0.index;
                    JPanelAnim.this.repaint();
                    try {
                        Thread.sleep(JPanelAnim.this.durationAnim);
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
                    final JPanelAnim this$0 = JPanelAnim.this;
                    --this$0.index;
                    JPanelAnim.this.repaint();
                    try {
                        Thread.sleep(JPanelAnim.this.durationAnim);
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
        g2.setColor(this.colorAnim);
        g2.fill(rec);
        g2.dispose();
    }
}