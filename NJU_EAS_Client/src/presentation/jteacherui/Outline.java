//整体框架
package presentation.jteacherui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import businesslogiccontroller.jteachercontroller.jTeacherController;

@SuppressWarnings("serial")
public class Outline extends JPanel{
	private jTeacherController jTeacherController;
	JPanel jp1,jp2;
	JButton jb1,jb2,jb3;
	
	JTextArea jta;
	
	public Outline(jTeacherController jTeacherController){	
		this.jTeacherController=jTeacherController;
		jp1=new JPanel();
		jp1.setBorder(new TitledBorder("教学整体框架详情"));
		jp1.setPreferredSize(new Dimension(800,600));
		jta=new JTextArea();
		jta.setEditable(false);
		JScrollPane jsp=new JScrollPane(jta);
		jsp.setPreferredSize(new Dimension(700,350));
		jp1.add(jsp,BorderLayout.CENTER);
		
		jb1=new JButton("输入/修改");//jta.setEditable(true);
		jb1.addActionListener(new Listener1());
		jb2=new JButton("取消");
		jb3=new JButton("保存并提交修改");
		jb3.addActionListener(new Listener2());
		jp2=new JPanel();
		jp2.setLayout(new BoxLayout(jp2,BoxLayout.X_AXIS));
		jp2.add(Box.createHorizontalGlue());
		jp2.add(jb1);
		jp2.add(Box.createHorizontalStrut(10));
//		jp2.add(jb2);
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(jb3);
		jp2.add(Box.createHorizontalStrut(10));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createVerticalStrut(10));
		this.add(jp1);
		this.add(Box.createVerticalStrut(10));
		this.add(jp2);
		this.add(Box.createVerticalStrut(10));
		this.setVisible(true);		
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		
		init();
	}
	public void init(){
		jta.setText(jTeacherController.showStructure());
	}
	
	class Listener1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			jta.setEditable(true);
			
		}
		
	}
	
	class Listener2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(jTeacherController.inputStructure(jta.getText())){
				JOptionPane.showMessageDialog(null, "更新整体框架成功！");
				jta.setEditable(false);
			}else{
				JOptionPane.showMessageDialog(null, "更新整体框架失败！");
			}
			
		}
		
	}
	
}