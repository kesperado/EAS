//自定义按钮
package presentation.mytools;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class myButton extends JButton{
	ImageIcon im;
	
	public myButton(String s){
		im=new ImageIcon(s);
		this.setIcon(im);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setBorder(new TitledBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK)));
	}
}
