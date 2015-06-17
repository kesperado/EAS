//整合学期选课
package presentation.studentui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import businesslogiccontroller.studentcontroller.StudentController;

@SuppressWarnings("serial")
public class Select extends JPanel{
	private StudentController studentController;
	JTabbedPane jtp;
	JPanel jp1,jp2,jp3;
	
	public Select(StudentController studentController){
		this.studentController=studentController;
		jtp=new JTabbedPane();
		jp1=new select_1(this.studentController);
		jp2=new select_2(this.studentController);
		jp3=new select_3(this.studentController);
		
		String status = studentController.getSystemStatus();
		if(status.equals("1")){
			jtp.add(jp1,"通识研讨课(补选)");
			jtp.add(jp2,"跨专业课(补选)");
			jtp.add(jp3,"体育选课");
		}else if(status.equals("0")){
			jtp.add(jp1,"通识研讨课");
			jtp.add(jp2,"跨专业课");
			jtp.add(jp3,"体育选课");
		}
		
		this.setLayout(new BorderLayout());
		this.add(jtp,BorderLayout.CENTER);
		
		jtp.setOpaque(false);
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		this.setOpaque(false);
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
