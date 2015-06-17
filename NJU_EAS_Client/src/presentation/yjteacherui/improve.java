//���ƿγ���Ϣ
package presentation.yjteacherui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import businesslogiccontroller.yjteachercontroller.yjTeacherController;
import presentation.mytools.myButton;
import vo.CourseVO;
import vo.TeacherVO;

@SuppressWarnings("serial")
public class improve extends JPanel{
	private yjTeacherController yjTeacherController;
	private CourseVO courseVO;
	JLabel title;
	
	JLabel mes1,mes2;
	JLabel jlb1,jlb2,jlb3;
	JComboBox<String> jcb1,jcb2;
	JTextField jtf1,jtf2,jtf3;
	
	JLabel jlb4,jlb5;
	JButton jb1,jb2;
	JTable table1,table2;
	JScrollPane scrollpane1,scrollpane2;
	DefaultTableModel model1,model2;
	
	JButton confirm,cancle;
	JPanel jp1,jp2,jp3,jp4,jp5;
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		try {
//			UIManager
//					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		JFrame jf=new JFrame();
//		jf.add(new improve(),BorderLayout.CENTER);
//		jf.setSize(690,515);
//		jf.setLocationRelativeTo(null);
//		jf.setVisible(true);
//	}
	
	public improve(){
		this.setBorder(new TitledBorder(new EtchedBorder()));
		this.setVisible(true);
		this.setSize(690,515);
	}
	
	public improve(yjTeacherController yjTeacherController,CourseVO courseVO){
		this.yjTeacherController=yjTeacherController;
		this.courseVO=courseVO;
		title=new JLabel(courseVO.getID()+"    "+courseVO.getName());
		
		jlb1=new JLabel("ѡ���Ͽ�ʱ��:");
//		String[] day={"����һ","���ڶ�","������","������","������"};
//		String[] time={"1-2","3-4","5-6","7-8","9-11"};
//		jcb1=new JComboBox<String>(day);
//		jcb2=new JComboBox<String>(time);
		jtf1=new JTextField(10);
		mes1=new JLabel("���磺����һ1-2��");
		jp1=new JPanel();
		jp1.setLayout(new BorderLayout());
		jp1.add(jlb1,BorderLayout.WEST);
		jp1.add(jtf1,BorderLayout.CENTER);
		jp1.add(mes1,BorderLayout.EAST);
//		jp1.add(jcb1,BorderLayout.CENTER);
//		jp1.add(jcb2,BorderLayout.EAST);
		
		jlb2=new JLabel("�����Ͽεص�:");
		jtf2=new JTextField(10);
		jp2=new JPanel();
		jp2.setLayout(new BorderLayout());
		jp2.add(jlb2,BorderLayout.WEST);
		jp2.add(jtf2,BorderLayout.CENTER);
		
		jlb3=new JLabel("������������:");
		mes2=new JLabel("����������");
		jtf3=new JTextField(10);
		jp3=new JPanel();
		jp3.setLayout(new BorderLayout());
		jp3.add(jlb3,BorderLayout.WEST);
		jp3.add(jtf3,BorderLayout.CENTER);
		jp3.add(mes2,BorderLayout.EAST);
		
		String[] column={"����","����"};
		String[][] row={};
		model1=new DefaultTableModel(row, column);
		table1 = new JTable(model1){
			public boolean isCellEditable(int rowIndex,int columnIndex) {
				return false;
			}
		};
		scrollpane1= new JScrollPane(table1);
		scrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jlb4=new JLabel("��ѡ���ʦ��");
		jp4=new JPanel();
		jp4.setLayout(new BorderLayout());
		jp4.add(jlb4,BorderLayout.NORTH);
		jp4.add(scrollpane1,BorderLayout.CENTER);
		
		String[] column2={"����","����"};
		String[][] row2={};
		model2=new DefaultTableModel(row2, column2);
		table2 = new JTable(model2){
			public boolean isCellEditable(int rowIndex,int columnIndex) {
				return false;
			}
		};
		scrollpane2= new JScrollPane(table2);
		scrollpane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jlb5=new JLabel("��ѡ���ʦ��");
		jp5=new JPanel();
		jp5.setLayout(new BorderLayout());
		jp5.add(jlb5,BorderLayout.NORTH);
		jp5.add(scrollpane2,BorderLayout.CENTER);
		
		jb1=new myButton("Icons/addtea_1.png");
		jb1.setSelectedIcon(new ImageIcon("Icons/addtea_2.png"));
		jb1.addActionListener(new Listener1());
		jb2=new myButton("Icons/removetea_1.png");
		jb2.setSelectedIcon(new ImageIcon("Icons/removetea_2.png"));
		jb2.addActionListener(new Listener2());
		confirm=new JButton("ȷ�ϲ�����");
		confirm.addActionListener(new Listener3());
		if(courseVO.getCourseType().equals("������")){
			jb1.setEnabled(false);
			jb2.setEnabled(false);
		}

		
		title.setBounds(40,10,200,30);
		jp1.setBounds(40,45,350,30);
		jp2.setBounds(40,80,250,30);
		jp3.setBounds(40,115,310,30);
		jp4.setBounds(40,155,200,300);
		jp5.setBounds(400,155,200,300);
		jb1.setBounds(270,240,100,40);
		jb2.setBounds(270,320,100,40);
		confirm.setBounds(550,20,100,30);

		this.setLayout(null);
		this.add(title);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jb1);
		this.add(jb2);
		this.add(confirm);

		this.setBorder(new TitledBorder(new EtchedBorder()));
		this.setVisible(true);
		this.setSize(690,515);

		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		jp5.setOpaque(false);
		this.setOpaque(false);
		
		init();
	}
	
//	public void paintComponent(Graphics g){
//		Image im = null;
//		try {
//			im = ImageIO.read(new File("Images/back.jpg"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		super.paintComponent(g);
//		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
//	}
	
	//Ԥ��������Ϣ
	private void init(){
		jtf1.setText(courseVO.getTime());
		jtf2.setText(courseVO.getClassroom());
		jtf3.setText(""+courseVO.getMemberLimit());
		ArrayList<TeacherVO> departmentteachers = yjTeacherController.showTeacherList("�ο���ʦ", yjTeacherController.getcurrentyjTeacherVO().getDepartment());
		ArrayList<TeacherVO> courseteachers = yjTeacherController.showCourserTeachers(courseVO.getID());
		
		String[] row1 ={"����","����"};
		String[][] data1 = new String[departmentteachers.size()][2];
		for(int i=0;i<departmentteachers.size();i++){
			data1[i][0]=departmentteachers.get(i).getID();
			data1[i][1]=departmentteachers.get(i).getName();
		}
		
		String[] row2={"����","����"};
		String[][] data2 = new String[courseteachers.size()][2];
		for(int i=0;i<courseteachers.size();i++){
			data2[i][0]=courseteachers.get(i).getID();
			data2[i][1]=courseteachers.get(i).getName();
		}
		
		model1=new DefaultTableModel(data1,row1);
		table1=new JTable(model1){
			public boolean isCellEditable(int rowIndex,int columnIndex) {
				 return false;
			}
		};
		scrollpane1=new JScrollPane(table1);
		jp4.removeAll();
		jp4.setLayout(new BorderLayout());
		jp4.add(jlb4,BorderLayout.NORTH);
		jp4.add(scrollpane1,BorderLayout.CENTER);
		jp4.repaint();
		
		model2=new DefaultTableModel(data2,row2);
		table2=new JTable(model2){
			public boolean isCellEditable(int rowIndex,int columnIndex) {
				 return false;
			}
		};
		scrollpane2=new JScrollPane(table2);
		jp5.removeAll();
		jp5.setLayout(new BorderLayout());
		jp5.add(jlb5,BorderLayout.NORTH);
		jp5.add(scrollpane2,BorderLayout.CENTER);
		jp5.repaint();
		
		table1.setOpaque(false);
		scrollpane1.setOpaque(false);
		scrollpane1.getViewport().setOpaque(false);
		table2.setOpaque(false);
		scrollpane2.setOpaque(false);
		scrollpane2.getViewport().setOpaque(false);
	}
	
	//��ӿγ��ο���ʦ
	class Listener1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String teacherid=null;
			boolean OK=true;
			try {
				teacherid = table1.getValueAt(table1.getSelectedRow(), 0)
						.toString();
			} catch (Exception e1) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,"��ѡ���ʦ����");
				OK=false;
			}
			if(OK){
				if(!yjTeacherController.addCourserTeacher(courseVO.getID(), teacherid)){
					JOptionPane.showMessageDialog(null,"��ӽ�ʦʧ�ܣ���ѡ��Ľ�ʦ�����Ѿ�����ѡ��ʦ�б���");
				}
				else{
					String teachername = table1.getValueAt(table1.getSelectedRow(), 1).toString();
					String[] data={teacherid,teachername};
					model2.addRow(data);
				}
			}
			
		}
		
	}
	
	//ɾ���γ̽�ʦ
	class Listener2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String teacherid=null;
			boolean OK=true;
			try {
				teacherid = table2.getValueAt(table2.getSelectedRow(), 0)
						.toString();
			} catch (Exception e1) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,"��ѡ���ʦ����");
				OK=false;
			}
			if(OK){
				yjTeacherController.deleteCourserTeacher(courseVO.getID(), teacherid);
				model2.removeRow(table2.getSelectedRow());
			}
		}
	}
	
	//ȷ�ϰ��ſγ�
	class Listener3 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean OK=true;
			String courseid = courseVO.getID();
			String time = jtf1.getText();
			String classroom =jtf2.getText();
			if(time.equals("")||classroom.equals("")){
				JOptionPane.showMessageDialog(null, "�γ���Ϣ��������");
				OK=false;
			}
			
			int memberlimit=0;
			if(OK){
				try {
					memberlimit = Integer.parseInt(jtf3.getText());
					if(memberlimit>500||memberlimit<=0){
						JOptionPane.showMessageDialog(null, "����������ѧ��������");
						OK=false;
					}
				} catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "ѧ������Ϊ������");
					OK=false;
				}
			}
			
			if(OK){
				if(yjTeacherController.arrangeCourse(courseid, time, classroom, memberlimit)){
					JOptionPane.showMessageDialog(null, "���ſγ̳ɹ���");
				}else{
					JOptionPane.showMessageDialog(null, "���ſγ�ʧ�ܣ������ŵĿγ�ʱ��ص�����������γ��г�ͻ������ѯ������ʦ��");
				}
			}			
		}
		
	}
	
	

}
