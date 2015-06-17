//ѧ���鿴�Լ��Ŀγ��б�
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
//		String[] columnName = {"�γ̺�","�γ���","�γ�����","��ʦ","ʱ��","�ص�" }; 
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
		
		jb1=new JButton("ˢ��");
		jb1.addActionListener(new Listener3());
		jb2=new JButton("��ѡ�γ�");
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
		jp3.setBorder(new TitledBorder("�γ���ϸ��Ϣ"));
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
	
	//��ʾ�γ��б�
	void setTable(){
		String[] columnName = {"�γ̺�","�γ���","�γ�����","�ον�ʦ","ѧ��","ʱ��","�ص�" }; 
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
		
		//����Ƿ��пγ̳�ͻ
		int max=table.getRowCount();
		boolean OK=true;
		for(int i=0;i<max&&OK;i++){
			for(int j=i+1;j<max;j++){
				if(table.getValueAt(i,5)!=null&&table.getValueAt(j,5)!=null){
					if(table.getValueAt(i,5).toString().equals(table.getValueAt(j,5).toString())){
						JOptionPane.showMessageDialog(null,"��ѡ�γ̵�ʱ����ڳ�ͻ��");
						OK=false;
						break;
					}
				}
			}
		}

	}
	
	
	//�鿴��ϸ��Ϣ  ���������б�ĳ�о���ʾ���пγ���ϸ��Ϣ��Ч������Ҫ����ĳ����ť��̫ɵX��
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
				
//				lb1=new JLabel("�γ̺�:"+id);
//				lb2=new JLabel("�γ���:"+name);
//				lb3=new JLabel("ѧ��:"+credit+"  ����:"+type);
//				lb4=new JLabel("Ŀ���꼶:"+grade+"  ����Ժϵ:"+targetdepartment);
//				lb5=new JLabel("ʱ��:"+time+"  �ص�:"+location);
//				lb6=new JLabel("�γ̴��:"+outline+"  �̿���:"+tbook+"  �ο���:"+rbook);
				
				String s1="�γ̺ţ�"+id+"\t"+"�γ���:"+name;
				String s2="ѧ�֣�"+credit+"\t\t"+"�γ�����:"+type;
				String s3="Ŀ���꼶��"+test(grade)+"\t"+"����Ժϵ��"+department;
				String s4="�Ͽ�ʱ�䣺"+test(time)+"\t"+"�Ͽεص�:"+test(location);
				String s5="�γ̴�٣�"+test(outline);
				String s6="�̿���Ŀ��"+test(tbook);
				String s7="�ο���Ŀ��"+test(rbook);
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
				if(s==null||s.equals("")){return "==��Ϣ��ȱ==";}
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
	
		
	//��ѡ�γ�
	class Listener2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String courseid=table.getValueAt(table.getSelectedRow(), 0).toString();
			int res=JOptionPane.showConfirmDialog(null, "ȷ��Ҫ��ѡ�ÿγ�ô��");
			if(res==JOptionPane.YES_OPTION){
				boolean isSucceed = studentController.quitCourse(courseid, studentController.getcurrentStudent().getID());
				if(isSucceed){
					JOptionPane.showMessageDialog(null, "�˿γɹ�");
					model.removeRow(table.getSelectedRow());
					jp3.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, "�˿�ʧ�ܣ��ÿγ������޷���ѡ�Ŀγ�����");
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
	
	//ˢ�½���
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
