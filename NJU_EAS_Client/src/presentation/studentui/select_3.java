//����ѡ��
package presentation.studentui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import vo.CourseVO;
import vo.StudentVO;
import vo.TeacherVO;

import businesslogiccontroller.studentcontroller.StudentController;

@SuppressWarnings("serial")
public class select_3 extends JPanel{
	private StudentController studentController;
	JPanel jp1;
	JLabel jlb1;
	JButton jb1;
	
	JScrollPane scrollpane1;
	JTable table1;
	DefaultTableModel model1;
	
	JLabel result;
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
//		jf.add(new select_3(),BorderLayout.CENTER);
//		jf.setSize(740,505);
//		jf.setLocationRelativeTo(null);
//		jf.setVisible(true);
//	}
	
	public select_3(StudentController studentController){
		this.studentController=studentController;
		result=new JLabel("����ѡ���Ѿ�������");
		result.setFont(new Font("΢���ź�",Font.BOLD, 50));
		result.setBounds(100,70,600,300);
		
		jlb1=new JLabel("����ѡ�Σ�");
		jb1=new JButton("ѡ��γ�");
		jb1.addActionListener(new Listener1());
		//δѡ��γ�
		init();
		jp1=new JPanel();
		jp1.setLayout(new BorderLayout());
		jp1.add(jlb1,BorderLayout.NORTH);
		jp1.add(scrollpane1,BorderLayout.CENTER);

		this.setLayout(null);
		jp1.setBounds(20,30,680,400);
		jb1.setBounds(600,20,80,25);
		this.add(result);
		this.add(jb1);
		this.add(jp1);
		result.setVisible(false);
		
		//�Ѿ�ѡ���˿γ�
		CourseVO PEClass=null;
		boolean hasPEClass=false;
		ArrayList<CourseVO> courseVOs = studentController.showMyCourseList(studentController.getcurrentStudent().getID());
		for(CourseVO courseVO:courseVOs){
			if(courseVO.getCourseType().equals("������")){
				hasPEClass=true;
				PEClass=courseVO;
				break;
			}
		}
		
		if(hasPEClass){
//			jb1.setVisible(false);
//			jp1.setVisible(false);
			this.remove(jb1);
			this.remove(jp1);
			result.setText("����ѡ���ˣ�"+PEClass.getName());
			result.setVisible(true);
		}
		
		this.repaint();
		jp1.setOpaque(false);
	}
	
	//��ʼ���б�
	void init(){
		String[] row1={"�γ̺�","�γ���","ѧ��","��ʦ","ʱ��","�ص�","����","��ѡ"};
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		courseVOs.addAll(studentController.showCourseList("������"));
		String[][] data1 = new String[courseVOs.size()][9];
		for (int i = 0; i < courseVOs.size(); i++) {
			data1[i][0] = courseVOs.get(i).getID();
			data1[i][1] = courseVOs.get(i).getName();
			data1[i][2] = courseVOs.get(i).getCredit() + "";
			data1[i][4] = courseVOs.get(i).getTime();
			data1[i][5] = courseVOs.get(i).getClassroom();
			data1[i][6] = courseVOs.get(i).getMemberLimit() + "";
			ArrayList<TeacherVO> teacherVOs = studentController
					.showCourserTeachers(courseVOs.get(i).getID());
			for (TeacherVO teacherVO : teacherVOs) {
				if (data1[i][3] == null) {
					data1[i][3] = (teacherVO.getName() + " ");
				} else {
					data1[i][3] += (teacherVO.getName() + " ");
				}
			}
			ArrayList<StudentVO> studentVOs = new ArrayList<StudentVO>();
			studentVOs = studentController.showCourseStudents(courseVOs.get(i).getID());
			int hasselect = studentVOs.size();
			data1[i][7] = hasselect + ""; // �����ѡ���ڼ䣬��ô��ʾ����ѡ������Ԥѡ�б��еģ�����ǲ���ѡ�ڼ䣬��ô��ʾ�ľ�����ʽѧ��������
		}
		model1=new DefaultTableModel(data1,row1);
		table1=new JTable(model1){
			public boolean isCellEditable(int rowIndex,int columnIndex) {
				return false;
			}
		};
		scrollpane1=new JScrollPane(table1);
		scrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
		table1.setOpaque(false);
		scrollpane1.setOpaque(false);
		scrollpane1.getViewport().setOpaque(false);
	}
	
	//ѡ��
	class Listener1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (table1.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "��ѡ��һ�ſΣ�");
			}else {
				// ����ɹ����������ݿ��ͬʱ������ѡ�б�
				String courseid = table1.getValueAt(table1.getSelectedRow(), 0)
						.toString();
				if (studentController.selectSportsCourse(courseid,
						studentController.getcurrentStudent().getID())) {
					jb1.setVisible(false);
					jp1.setVisible(false);
					result.setText("����ѡ��"+table1.getValueAt(table1.getSelectedRow(), 1));
					result.setVisible(true);
				}
			}
		}
		
	}
}