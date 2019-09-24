package demo;

import component.JPanelAnim;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author Leal
 */
public class Demo extends javax.swing.JFrame{
    
    public Demo(){
    initComponents();
    configJPanelAnimation();
    }
    
    
    private void configJPanelAnimation(){
    //Animation color
    jPanelAnim.setAnimColor(Color.yellow);
    //Duration of loading of animation (ms)
    jPanelAnim.setAnimDuration(10);
    //Heigth of Animation
    jPanelAnim.setAnimHeigth(40);
    
    }
    
    
    private void initComponents(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);
        setPreferredSize(new Dimension(400, 300));
        
        jPanelAnim = new JPanelAnim();
        
        
        JLabel jLabel = new JLabel("Exemple");
        
        jPanelAnim.add(jLabel);
        
        
        pack();
        setLocationRelativeTo(null);
        
        this.add(jPanelAnim);
    
  
    }
    
    
    
    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.setVisible(true);
    }
    
    private JPanelAnim jPanelAnim;
    
    
}
