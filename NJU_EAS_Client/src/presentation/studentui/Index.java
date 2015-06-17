//首页
package presentation.studentui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import vo.StudentVO;
import businesslogiccontroller.studentcontroller.StudentController;

@SuppressWarnings("serial")
public class Index extends JPanel {
	private StudentController studentController;
	JPanel jp1, jp2;

	JButton jb1, jb2;
	JPanel info1, info2, info3, info4, info5, info6, info7;
	JLabel jlb1, jlb2, jlb3, jlb4, jlb5, jlb6, jlb7;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7;

	JButton cb1, cb2, cb3;
	JPanel cp1, cp2, cp3;
	JLabel clb1, clb2, clb3;
	JTextField ctf1, ctf2, ctf3;
	
	JTextArea jta;

	public Index(StudentController studentController) {
		this.studentController = studentController;
		info1 = new JPanel();
		jlb1 = new JLabel("学号：");
		jtf1 = new JTextField(10);
		jtf1.setEnabled(false);
		info1.add(jlb1);
		info1.add(jtf1);
		info1.setBounds(70, 30, 200, 40);

		info2 = new JPanel();
		jlb2 = new JLabel("姓名：");
		jtf2 = new JTextField(10);
		jtf2.setEnabled(false);
		info2.add(jlb2);
		info2.add(jtf2);
		info2.setBounds(70, 65, 200, 35);

		info3 = new JPanel();
		jlb3 = new JLabel("年级：");
		jtf3 = new JTextField(10);
		jtf3.setEnabled(false);
		info3.add(jlb3);
		info3.add(jtf3);
		info3.setBounds(70, 100, 200, 40);

		info4 = new JPanel();
		jlb4 = new JLabel("所属院系：");
		jtf4 = new JTextField(10);
		jtf4.setEnabled(false);
		info4.add(jlb4);
		info4.add(jtf4);
		info4.setBounds(58, 135, 200, 40);

		info5 = new JPanel();
		jlb5 = new JLabel("年龄：");
		jtf5 = new JTextField(10);
		jtf5.setEditable(false);
		info5.add(jlb5);
		info5.add(jtf5);
		info5.setBounds(280, 30, 200, 40);

		info6 = new JPanel();
		jlb6 = new JLabel("籍贯：");
		jtf6 = new JTextField(10);
		jtf6.setEditable(false);
		info6.add(jlb6);
		info6.add(jtf6);
		info6.setBounds(280, 65, 200, 40);

		info7 = new JPanel();
		jlb7 = new JLabel("联系方式：");
		jtf7 = new JTextField(10);
		jtf7.setEditable(false);
		info7.add(jlb7);
		info7.add(jtf7);
		info7.setBounds(268, 100, 200, 40);

		jb1 = new JButton("修改信息");
		jb1.setBounds(280, 145, 100, 30);
		jb1.addActionListener(new Listener1());
		jb2 = new JButton("确认信息");
		jb2.setBounds(380, 145, 100, 30);
		jb2.addActionListener(new Listener2());

		clb1 = new JLabel("请输入原密码：");
		clb2 = new JLabel("请输入新密码：");
		clb3 = new JLabel("请确认新密码：");
		ctf1=new JPasswordField(10);
		ctf2=new JPasswordField(10);
		ctf3=new JPasswordField(10);
		cp1 = new JPanel();
		cp1.add(clb1);
		cp1.add(ctf1);
		cp1.setBounds(500, 30, 220, 40);
		cp2 = new JPanel();
		cp2.add(clb2);
		cp2.add(ctf2);
		cp2.setBounds(500, 65, 220, 40);
		cp3 = new JPanel();
		cp3.add(clb3);
		cp3.add(ctf3);
		cp3.setBounds(500, 100, 220, 40);
		cb1 = new JButton("修改密码");
		cb1.setBounds(500, 145, 100, 30);
		cb1.addActionListener(new change());
		cb2 = new JButton("确认");
		cb2.setBounds(600, 145, 60, 30);
		cb3 = new JButton("取消");
		cb3.setBounds(660, 145, 60, 30);
		cb3.addActionListener(new cancle());
		cb2.addActionListener(new confirm());
		cp1.setVisible(false);
		cp2.setVisible(false);
		cp3.setVisible(false);
		cb2.setVisible(false);
		cb3.setVisible(false);

		jp1 = new JPanel();
		jp1.setBorder(new TitledBorder("个人信息"));
		jp1.setLayout(null);
		jp1.add(info1);
		jp1.add(info2);
		jp1.add(info3);
		jp1.add(info4);
		jp1.add(info5);
		jp1.add(info6);
		jp1.add(info7);
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(cp1);
		jp1.add(cp2);
		jp1.add(cp3);
		jp1.add(cb1);
		jp1.add(cb2);
		jp1.add(cb3);

		jp2=new JPanel();
		jp2.setBorder(new TitledBorder("系统消息"));
		jta=new JTextArea();
		jta.setEditable(false);
		JScrollPane jsp=new JScrollPane(jta);
		jp2.setLayout(new BorderLayout());
		jp2.add(jsp,BorderLayout.CENTER);
		
		this.setLayout(null);
		jp1.setBounds(0,10,730,200);
		jp2.setBounds(0,220,730,260);
		this.add(jp1);
		this.add(Box.createVerticalStrut(10));
		this.add(jp2);
		this.setVisible(true);
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		info1.setOpaque(false);
		info2.setOpaque(false);
		info3.setOpaque(false);
		info4.setOpaque(false);
		info5.setOpaque(false);
		info6.setOpaque(false);
		info7.setOpaque(false);
		cp1.setOpaque(false);
		cp2.setOpaque(false);
		cp3.setOpaque(false);
		this.setBackground(Color.WHITE);
		
		//填充个人信息
		fiiInfo();
		getNotices();
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

	//填充必要信息
	public void fiiInfo() {
		StudentVO currentUser = studentController.getcurrentStudent();
		jtf1.setText(currentUser.getID());
		jtf2.setText(currentUser.getName());
		jtf3.setText(currentUser.getGrade());
		jtf4.setText(currentUser.getDepartment());
		jtf5.setText(currentUser.getAge());
		jtf6.setText(currentUser.getHometown());
		jtf7.setText(currentUser.getContact());
	}
	
	public void getNotices(){
		ArrayList<String> mynotices=studentController.getMyNotices();
		String notices="";
		for(String notice:mynotices){
			notices+=notice+"\n";
		}
		jta.setText(notices);
	}

	class Listener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			jtf5.setEditable(true);
			jtf6.setEditable(true);
			jtf7.setEditable(true);
		}

	}

	class Listener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			boolean OK=true;
			try {
				int age = Integer.parseInt(jtf5.getText());
				if(age<=0){
					JOptionPane.showMessageDialog(null,"年龄请输入正数");
					OK=false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,"请正确输入年龄");
				OK=false;
			}
			if(OK){
				jtf5.setEditable(false);
				jtf6.setEditable(false);
				jtf7.setEditable(false);
				studentController.completeMyInfo(jtf5.getText(), jtf6.getText(), jtf7.getText());
				JOptionPane.showMessageDialog(null,"个人信息修改成功");
			}
		}

	}

	class change implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			cp1.setVisible(true);
			cp2.setVisible(true);
			cp3.setVisible(true);
			cb2.setVisible(true);
			cb3.setVisible(true);
		}

	}

	//修改密码
	class confirm implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(ctf2.getText().equals("")){
				JOptionPane.showMessageDialog(null,"请输入新密码！");
			}else if(ctf2.getText().equals(ctf3.getText())){
					if(studentController.changeMyPassword(ctf1.getText(), ctf2.getText())){
						JOptionPane.showMessageDialog(null,"密码修改成功！");
						//最后cancle
						cp1.setVisible(false);
						cp2.setVisible(false);
						cp3.setVisible(false);
						cb2.setVisible(false);
						cb3.setVisible(false);
	
					}else{
						JOptionPane.showMessageDialog(null,"密码修改失败！请确认您输入的旧密码是否正确");
					}
				}else{
					JOptionPane.showMessageDialog(null,"请确保您输入的新密码一致！谢谢！");
			}
			ctf1.setText("");
			ctf2.setText("");
			ctf3.setText("");
		}

	}

	class cancle implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			cp1.setVisible(false);
			cp2.setVisible(false);
			cp3.setVisible(false);
			cb2.setVisible(false);
			cb3.setVisible(false);
			ctf1.setText("");
			ctf2.setText("");
			ctf3.setText("");
		}

	}
}
