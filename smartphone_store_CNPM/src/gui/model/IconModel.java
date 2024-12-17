package gui.model;

import java.awt.Image;
import javax.swing.ImageIcon;

public class IconModel {
    private int width, height;
    private String srcIcon;
    
    public IconModel(int w, int h, String s) {
        this.width = w;
        this.height = h;
        this.srcIcon = s;
    }
    
    public ImageIcon createIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/" + this.srcIcon));
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(this.width, this.height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        
        return icon;
    }
}
