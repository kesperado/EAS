//管理模式界面
package presentation.jteacherui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import businesslogiccontroller.jteachercontroller.jTeacherController;
import presentation.mytools.myButton;

@SuppressWarnings("serial")
public class Admin extends JPanel{
	private jTeacherController jTeacherController;
	JPanel jp1,jp2,jp3;
	
	JLabel reg;
	JPanel info1,info2,info3,info4,info5;
	JLabel jlb1,jlb2,jlb3,jlb4,jlb5;
	JTextField jtf1,jtf2;
	JComboBox<String> jcb1,jcb2,jcb3;
	JButton register,cancle1;
	
	JLabel mess;
	JTextArea jta;
	JButton send,cancle2;
	
	JButton show;
	JPanel buttons;
	JButton state1,state2,state3,state4;
	
	public Admin(jTeacherController jTeacherController){
		this.jTeacherController=jTeacherController;
		jlb1=new JLabel("学工号：");
		jlb2=new JLabel("姓   名：");
		jlb3=new JLabel("身   份：");
		jlb4=new JLabel("单   位：");
		jlb5=new JLabel("年   级：");
		
		jtf1=new JTextField(10);
		jtf2=new JTextField(9);
		String[] roles={"院系教务老师","任课老师","学生"};
		jcb1=new JComboBox<String>(roles);
		jcb1.addActionListener(new select());
		String[] departments={"软件学院","商学院","文学院","现代工程学院","数学系","物理系","天文系","化学系","体育部"};
		jcb2=new JComboBox<String>(departments);
		String[] grades={"本科一年级   ","本科二年级   ","本科三年级   ","本科四年级   "};
		jcb3=new JComboBox<String>(grades);
		
//		info1=new JPanel();
//		info1.add(jlb1);
//		info1.add(jtf1);
		info2=new JPanel();
		info2.add(jlb2);
		info2.add(jtf2);
		info3=new JPanel();
		info3.add(jlb3);
		info3.add(jcb1);
		info4=new JPanel();
		info4.add(jlb4);
		info4.add(jcb2);
		info5=new JPanel();
		info5.add(jlb5);
		info5.add(jcb3);
		info5.setVisible(false);
		
//		info1.setBounds(100,30,200,30);
		info2.setBounds(100,45,200,30);
		info3.setBounds(100,75,200,35);
		info4.setBounds(100,105,200,35);
		info5.setBounds(100,135,200,35);
		
		register=new JButton("注册");
		register.setBounds(100,190,100,30);
		register.addActionListener(new register());
		cancle1=new JButton("取消");
		cancle1.setBounds(200,190,100,30);
		cancle1.addActionListener(new cancel1());
		
		jp1=new JPanel();
		jp1.setBorder(new TitledBorder("注册成员"));
		jp1.setLayout(null);
//		jp1.add(info1);
		jp1.add(info2);
		jp1.add(info3);
		jp1.add(info4);
		jp1.add(info5);
		jp1.add(register);
		jp1.add(cancle1);
		
		jta=new JTextArea();
		jta.setBounds(20,30,600,160);
		send=new JButton("发布");
		send.setBounds(640,50,80,30);
		send.addActionListener(new send());
		cancle2=new JButton("取消");
		cancle2.setBounds(640,100,80,30);
		cancle2.addActionListener(new cancel2());
		
		jp2=new JPanel();
		jp2.setBorder(new TitledBorder("发布通知"));
		jp2.setLayout(null);
		jp2.add(jta);
		jp2.add(send);
		jp2.add(cancle2);
		
		show=new myButton("Icons/north_1.png");
		show.setSelectedIcon(new ImageIcon("Icons/north_2.png"));
		show.addActionListener(new move1());
		buttons=new JPanel();
		state1=new myButton("Icons/state1_1.png");
		state2=new myButton("Icons/state2_1.png");
		state3=new myButton("Icons/state3_1.png");
//		state4=new myButton("Icons/state4_1.png");
		state1.setSelectedIcon(new ImageIcon("Icons/state1_2.png"));
		state2.setSelectedIcon(new ImageIcon("Icons/state2_2.png"));
		state3.setSelectedIcon(new ImageIcon("Icons/state3_2.png"));
//		state4.setSelectedIcon(new ImageIcon("Icons/state4_2.png"));
		state1.addActionListener(new state1());
		state2.addActionListener(new state2());
		state3.addActionListener(new state3());
//		state4.addActionListener(new state4());
		state1.setEnabled(false);
		state2.setEnabled(false);
		state3.setEnabled(false);
//		state4.setEnabled(false);
		String currentstatus=jTeacherController.getSystemStatus();
		if(currentstatus.equals("-1")){         //现在还未开始选课
			state1.setEnabled(true);
		}else if(currentstatus.equals("0")){ //处于已经开始选课的状态下，所以第二个按钮是亮的
			state2.setEnabled(true);
		}else if(currentstatus.equals("1")){  //处于补退选阶段，所以第三课按钮是亮的，意味着可以结束补退选
			state3.setEnabled(true);
		}
//		state4.setEnabled(false);
		buttons.add(state1);
		buttons.add(state2);
		buttons.add(state3);
//		buttons.add(state4);
		jp3=new JPanel();
		jp3.setLayout(null);
		show.setBounds(0,0,740,50);
		buttons.setBounds(0,50,735,100);
		buttons.setBorder(new TitledBorder(new LineBorder(Color.GRAY),"更改系统状态",TitledBorder.LEFT,TitledBorder.TOP));
		jp3.add(show);
		jp3.add(buttons);
		
		this.setLayout(null);
		jp1.setBounds(0,0,735,255);
		jp2.setBounds(0,255,735,205);
		jp3.setBounds(0,462,740,255);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		info2.setOpaque(false);
		info3.setOpaque(false);
		info4.setOpaque(false);
		info5.setOpaque(false);
		buttons.setOpaque(false);
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
	
	//注册成员(除教务处老师)
	class register implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean issucceed=false;
			if(!jtf2.getText().equals("")){
				if(jcb1.getSelectedItem().equals("学生")){
					issucceed=jTeacherController.RegisterStudent(jtf2.getText(), (String)jcb3.getSelectedItem(), (String)jcb2.getSelectedItem());
				}else{
					issucceed=jTeacherController.RegisterTeacher(jtf2.getText(), (String)jcb1.getSelectedItem(), (String)jcb2.getSelectedItem());
				}
			}
			if(issucceed){
				JOptionPane.showMessageDialog(null, "注册成功！");
			}else{
				JOptionPane.showMessageDialog(null, "注册失败！");
			}
			jtf2.setText(null);
			jcb1.setSelectedIndex(0);
			jcb2.setSelectedIndex(0);
			jcb3.setSelectedIndex(0);
			
		}
		
	}
	
	class cancel1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			jtf2.setText("");
			jcb1.setSelectedIndex(0);
			jcb2.setSelectedIndex(0);
			jcb3.setSelectedIndex(0);
		}
		
	}
	
	//发布通知
	class send implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String message=jta.getText();
			boolean OK=true;
			if(message.equals("")||message==null){
				OK=false;
			}
			if(OK){
				if(jTeacherController.addNotice(message)){
					JOptionPane.showMessageDialog(null, "发布通知成功！");
					jta.setText("");
				}else{
					JOptionPane.showMessageDialog(null, "发布通知失败！");
				}
			}else{
				JOptionPane.showMessageDialog(null, "请输入通知内容！");
			}

		}
		
	}
	
	class cancel2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			jta.setText("");
			
		}
		
	}
	
	//设置系统状态为开始选课
	class state1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int res=JOptionPane.showConfirmDialog(null, "确定要将系统状态更改为“开始选课”么？");
			if(res==JOptionPane.YES_OPTION){
				jTeacherController.startSelectCourse();
				state1.setEnabled(false);
				state2.setEnabled(true);
			}
		}
		
	}
	
	//结束初次选课并开始补退选
	class state2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int res=JOptionPane.showConfirmDialog(null, "确定要将系统状态更改为“结束选课并开始补退选”么？");
			if(res==JOptionPane.YES_OPTION){
				jTeacherController.startQuitAndReselectCourse();
				state2.setEnabled(false);
				state3.setEnabled(true);
			}
		}
		
	}
	
	//结束补退选
	class state3 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int res=JOptionPane.showConfirmDialog(null, "确定要将系统状态更改为“结束补退选”么？");
			if(res==JOptionPane.YES_OPTION){
				jTeacherController.finishSelectAndQuitCourse();
			state3.setEnabled(false);
			}
		}
		
	}
	
//	class state4 implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			state4.setEnabled(false);
//		}
//		
//	}
	
	class select implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(jcb1.getSelectedItem().equals("学生")){
				info5.setVisible(true);
			}else{info5.setVisible(false);}
		}
		
	}
	
	class move1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			new moveNorth().start();
			show.setIcon(new ImageIcon("Icons/south_1.png"));
			show.setSelectedIcon(new ImageIcon("Icons/south_2.png"));
			show.removeActionListener(this);
			show.addActionListener(new move2());
		}
		
	}
	
	class move2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			new moveSouth().start();
			show.setIcon(new ImageIcon("Icons/north_1.png"));
			show.setSelectedIcon(new ImageIcon("Icons/north_2.png"));
			show.removeActionListener(this);
			show.addActionListener(new move1());
		}
		
	}
	
	class moveNorth extends Thread{
		public void run(){
			jp2.setVisible(false);
			int x1=jp3.getX();
			int y1=jp3.getY();
			for(int i=0;i<100;i+=2){
				jp3.setLocation(x1, y1-i);
				try {
					sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	class moveSouth extends Thread{
		public void run(){
			int x1=jp3.getX();
			int y1=jp3.getY();
			for(int i=0;i<100;i+=2){
				jp3.setLocation(x1, y1+i);
				try {
					sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			jp2.setVisible(true);
		}
	}
}
