//´ø±³¾°µÄJPanel
package presentation.mytools;

import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class myPanel extends JPanel{
	Image im;
	
	public myPanel(String s){
		try {
			this.im=ImageIO.read(new File(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
	}
}