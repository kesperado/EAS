//完善课程信息
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
		
		jlb1=new JLabel("选择上课时间:");
//		String[] day={"星期一","星期二","星期三","星期四","星期五"};
//		String[] time={"1-2","3-4","5-6","7-8","9-11"};
//		jcb1=new JComboBox<String>(day);
//		jcb2=new JComboBox<String>(time);
		jtf1=new JTextField(10);
		mes1=new JLabel("例如：星期一1-2节");
		jp1=new JPanel();
		jp1.setLayout(new BorderLayout());
		jp1.add(jlb1,BorderLayout.WEST);
		jp1.add(jtf1,BorderLayout.CENTER);
		jp1.add(mes1,BorderLayout.EAST);
//		jp1.add(jcb1,BorderLayout.CENTER);
//		jp1.add(jcb2,BorderLayout.EAST);
		
		jlb2=new JLabel("输入上课地点:");
		jtf2=new JTextField(10);
		jp2=new JPanel();
		jp2.setLayout(new BorderLayout());
		jp2.add(jlb2,BorderLayout.WEST);
		jp2.add(jtf2,BorderLayout.CENTER);
		
		jlb3=new JLabel("输入上限人数:");
		mes2=new JLabel("请输入整数");
		jtf3=new JTextField(10);
		jp3=new JPanel();
		jp3.setLayout(new BorderLayout());
		jp3.add(jlb3,BorderLayout.WEST);
		jp3.add(jtf3,BorderLayout.CENTER);
		jp3.add(mes2,BorderLayout.EAST);
		
		String[] column={"工号","姓名"};
		String[][] row={};
		model1=new DefaultTableModel(row, column);
		table1 = new JTable(model1){
			public boolean isCellEditable(int rowIndex,int columnIndex) {
				return false;
			}
		};
		scrollpane1= new JScrollPane(table1);
		scrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jlb4=new JLabel("请选择教师：");
		jp4=new JPanel();
		jp4.setLayout(new BorderLayout());
		jp4.add(jlb4,BorderLayout.NORTH);
		jp4.add(scrollpane1,BorderLayout.CENTER);
		
		String[] column2={"工号","姓名"};
		String[][] row2={};
		model2=new DefaultTableModel(row2, column2);
		table2 = new JTable(model2){
			public boolean isCellEditable(int rowIndex,int columnIndex) {
				return false;
			}
		};
		scrollpane2= new JScrollPane(table2);
		scrollpane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jlb5=new JLabel("已选择教师：");
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
		confirm=new JButton("确认并保存");
		confirm.addActionListener(new Listener3());
		if(courseVO.getCourseType().equals("体育课")){
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
	
	//预填充相关信息
	private void init(){
		jtf1.setText(courseVO.getTime());
		jtf2.setText(courseVO.getClassroom());
		jtf3.setText(""+courseVO.getMemberLimit());
		ArrayList<TeacherVO> departmentteachers = yjTeacherController.showTeacherList("任课老师", yjTeacherController.getcurrentyjTeacherVO().getDepartment());
		ArrayList<TeacherVO> courseteachers = yjTeacherController.showCourserTeachers(courseVO.getID());
		
		String[] row1 ={"工号","姓名"};
		String[][] data1 = new String[departmentteachers.size()][2];
		for(int i=0;i<departmentteachers.size();i++){
			data1[i][0]=departmentteachers.get(i).getID();
			data1[i][1]=departmentteachers.get(i).getName();
		}
		
		String[] row2={"工号","姓名"};
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
	
	//添加课程任课老师
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
				JOptionPane.showMessageDialog(null,"请选择教师……");
				OK=false;
			}
			if(OK){
				if(!yjTeacherController.addCourserTeacher(courseVO.getID(), teacherid)){
					JOptionPane.showMessageDialog(null,"添加教师失败，您选择的教师可能已经在已选教师列表中");
				}
				else{
					String teachername = table1.getValueAt(table1.getSelectedRow(), 1).toString();
					String[] data={teacherid,teachername};
					model2.addRow(data);
				}
			}
			
		}
		
	}
	
	//删除课程教师
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
				JOptionPane.showMessageDialog(null,"请选择教师……");
				OK=false;
			}
			if(OK){
				yjTeacherController.deleteCourserTeacher(courseVO.getID(), teacherid);
				model2.removeRow(table2.getSelectedRow());
			}
		}
	}
	
	//确认安排课程
	class Listener3 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean OK=true;
			String courseid = courseVO.getID();
			String time = jtf1.getText();
			String classroom =jtf2.getText();
			if(time.equals("")||classroom.equals("")){
				JOptionPane.showMessageDialog(null, "课程信息不完整！");
				OK=false;
			}
			
			int memberlimit=0;
			if(OK){
				try {
					memberlimit = Integer.parseInt(jtf3.getText());
					if(memberlimit>500||memberlimit<=0){
						JOptionPane.showMessageDialog(null, "请输入合理的学生人数！");
						OK=false;
					}
				} catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "学生人数为整数！");
					OK=false;
				}
			}
			
			if(OK){
				if(yjTeacherController.arrangeCourse(courseid, time, classroom, memberlimit)){
					JOptionPane.showMessageDialog(null, "安排课程成功！");
				}else{
					JOptionPane.showMessageDialog(null, "安排课程失败！您安排的课程时间地点可能与其他课程有冲突，请咨询教务处老师！");
				}
			}			
		}
		
	}
	
	

}
