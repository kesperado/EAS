//整合整体框架和教学计划
package presentation.jteacherui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import businesslogiccontroller.jteachercontroller.jTeacherController;

@SuppressWarnings("serial")
public class combine extends JPanel{
	private jTeacherController jTeacherController;
	JTabbedPane jtp;
	JPanel jp1,jp2;
	
	public combine(jTeacherController jTeacherController){
		this.jTeacherController=jTeacherController;
		jtp=new JTabbedPane();
		jp1=new Outline(this.jTeacherController);
		jp2=new TeaPlans(this.jTeacherController);
		jtp.add("整体框架",jp1);
		jtp.add("院系教学计划",jp2);
		
		jtp.setOpaque(false);
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		this.setOpaque(false);
		
		this.setLayout(new BorderLayout());
		this.add(jtp,BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g){
		Image im = null;
		try {
			im = ImageIO.read(new File("Images/back.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
	}
}
