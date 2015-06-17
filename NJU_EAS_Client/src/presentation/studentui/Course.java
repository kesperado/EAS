//学生查看自己的课程列表
package presentation.studentui;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import businesslogiccontroller.studentcontroller.StudentController;
import vo.CourseVO;
import vo.TeacherVO;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Course extends JPanel{
	private StudentController studentController;
	JLabel lb1,lb2,lb3,lb4,lb5,lb6;
	JButton jb1,jb2;
	JPanel jp1,jp2,jp3;
	JTable table;
	JScrollPane scrollPane;
	DefaultTableModel model;
	
	public Course(StudentController studentController){
		this.studentController = studentController;
//		String[] columnName = {"课程号","课程名","课程类型","教师","时间","地点" }; 
//		String[][] rowData = {}; 
//		table = new JTable(new DefaultTableModel(rowData, columnName)); 
//		table.getColumnModel().getColumn(4).setPreferredWidth(100);
//		scrollPane = new JScrollPane(table);
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp1=new JPanel();
		setTable();
		table.addMouseListener(new Listener1());
		jp1.setLayout(new BorderLayout());
		jp1.add(scrollPane,BorderLayout.CENTER);
		jp1.setBorder(BorderFactory.createEmptyBorder (10, 10, 10, 10)); 
		
		jb1=new JButton("刷新");
		jb1.addActionListener(new Listener3());
		jb2=new JButton("退选课程");
		jb2.addActionListener(new Listener2());
		jp2=new JPanel();
		jp2.setLayout(new BoxLayout(jp2,BoxLayout.X_AXIS));
		jp2.add(Box.createHorizontalGlue());
		jp2.add(jb1);
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(jb2);
		jp2.add(Box.createHorizontalStrut(10));
		jb2.setVisible(false);
		String status = studentController.getSystemStatus();
		if(status.equals("1")){
			jb2.setVisible(true);
		}
		
		jp3=new JPanel();
		jp3.setBorder(new TitledBorder("课程详细信息"));
		jp3.setPreferredSize(new Dimension(800, 160));
		jp3.setVisible(false);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(jp1);
		this.add(Box.createVerticalStrut(10));
		this.add(jp2);
		this.add(Box.createVerticalStrut(10));
		this.add(jp3);
		this.add(Box.createVerticalStrut(10));
		this.setVisible(true);
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
				
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
	
	//显示课程列表
	void setTable(){
		String[] columnName = {"课程号","课程名","课程类型","任课教师","学分","时间","地点" }; 
		ArrayList<CourseVO> courseVOs = studentController.showMyCourseList(studentController.getcurrentStudent().getID());
		
		String[][] courseData = new String [courseVOs.size()][7];
		for(int i=0;i<courseVOs.size();i++){
			courseData[i][0]=courseVOs.get(i).getID();
			courseData[i][1]=courseVOs.get(i).getName();
			courseData[i][2]=courseVOs.get(i).getCourseType();
			courseData[i][4]=courseVOs.get(i).getCredit()+"";
			courseData[i][5]=courseVOs.get(i).getTime();
			courseData[i][6]=courseVOs.get(i).getClassroom();
			ArrayList<TeacherVO> teacherVOs = studentController
					.showCourserTeachers(courseVOs.get(i).getID());
			for (TeacherVO teacherVO : teacherVOs) {
				if (courseData[i][3] == null) {
					courseData[i][3] = (teacherVO.getName() + " ");
				} else {
					courseData[i][3] += (teacherVO.getName() + " ");
				}
			}
		}
		
		model = new DefaultTableModel(courseData, columnName);
		table=new JTable(courseData,columnName){
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		scrollPane = new JScrollPane(table);
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table.setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		
		//检测是否有课程冲突
		int max=table.getRowCount();
		boolean OK=true;
		for(int i=0;i<max&&OK;i++){
			for(int j=i+1;j<max;j++){
				if(table.getValueAt(i,5)!=null&&table.getValueAt(j,5)!=null){
					if(table.getValueAt(i,5).toString().equals(table.getValueAt(j,5).toString())){
						JOptionPane.showMessageDialog(null,"已选课程的时间存在冲突！");
						OK=false;
						break;
					}
				}
			}
		}

	}
	
	
	//查看详细信息  做到单机列表某行就显示该行课程详细信息的效果，不要监听某个按钮，太傻X了
		class Listener1 implements MouseListener{
			String id,name,grade,targetdepartment,department,type,outline,tbook,rbook,time,location;
			int credit;
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				jp3.removeAll();
				jp3.setVisible(true);
				String courseid = table.getValueAt(table.getSelectedRow(), 0).toString();
				CourseVO courseVO = studentController.showCourseInfo(courseid);
				id=courseVO.getID();
				name=courseVO.getName();
				credit=courseVO.getCredit();
				time=courseVO.getTime();
				location=courseVO.getClassroom();
				grade=courseVO.getGrade();
				department=courseVO.getDepartment();
				targetdepartment=courseVO.getTargetDepartment();
				type=courseVO.getCourseType();
				outline=courseVO.getOutline();
				tbook=courseVO.getTextbook();
				rbook=courseVO.getReferenceBook();
				
//				lb1=new JLabel("课程号:"+id);
//				lb2=new JLabel("课程名:"+name);
//				lb3=new JLabel("学分:"+credit+"  种类:"+type);
//				lb4=new JLabel("目标年级:"+grade+"  开设院系:"+targetdepartment);
//				lb5=new JLabel("时间:"+time+"  地点:"+location);
//				lb6=new JLabel("课程大纲:"+outline+"  教科书:"+tbook+"  参考书:"+rbook);
				
				String s1="课程号："+id+"\t"+"课程名:"+name;
				String s2="学分："+credit+"\t\t"+"课程种类:"+type;
				String s3="目标年级："+test(grade)+"\t"+"开设院系："+department;
				String s4="上课时间："+test(time)+"\t"+"上课地点:"+test(location);
				String s5="课程大纲："+test(outline);
				String s6="教科书目："+test(tbook);
				String s7="参考书目："+test(rbook);
				String info=s1+"\n"+s2+"\n"+s3+"\n"+s4+"\n"+s5+"\n"+s6+"\n"+s7;
				JTextArea jta=new JTextArea(info);
				jta.setEditable(false);
				
//				jp3.setLayout(new BoxLayout(jp3, BoxLayout.Y_AXIS));
//				jp3.add(lb1);
//				jp3.add(lb2);
//				jp3.add(lb3);
//				jp3.add(lb4);
//				jp3.add(lb5);
//				jp3.add(lb6);
				jp3.setPreferredSize(new Dimension(740,200));
				jp3.setLayout(new BorderLayout());
				jp3.add(jta,BorderLayout.CENTER);
				jp3.repaint();
			}
			
			public String test(String s){
				if(s==null||s.equals("")){return "==信息暂缺==";}
				return s;
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
	
		
	//退选课程
	class Listener2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String courseid=table.getValueAt(table.getSelectedRow(), 0).toString();
			int res=JOptionPane.showConfirmDialog(null, "确定要退选该课程么？");
			if(res==JOptionPane.YES_OPTION){
				boolean isSucceed = studentController.quitCourse(courseid, studentController.getcurrentStudent().getID());
				if(isSucceed){
					JOptionPane.showMessageDialog(null, "退课成功");
					model.removeRow(table.getSelectedRow());
					jp3.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, "退课失败，该课程属于无法退选的课程类型");
				}
			}
			setTable();
			table.addMouseListener(new Listener1());
			jp1.removeAll();
			jp1.setLayout(new BorderLayout());
			jp1.add(scrollPane,BorderLayout.CENTER);
			jp1.setBorder(BorderFactory.createEmptyBorder (10, 10, 10, 10));
			jp1.repaint();
		}
	}
	
	//刷新界面
	class Listener3 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			setTable();
			table.addMouseListener(new Listener1());
			jp1.removeAll();
			jp1.setLayout(new BorderLayout());
			jp1.add(scrollPane,BorderLayout.CENTER);
			jp1.setBorder(BorderFactory.createEmptyBorder (10, 10, 10, 10));
			jp1.repaint();
			
		}
		
	}
}
