//��רҵѡ��
package presentation.studentui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import vo.CourseVO;
import vo.StudentVO;
import vo.TeacherVO;

import businesslogiccontroller.studentcontroller.StudentController;

@SuppressWarnings("serial")
public class select_2 extends JPanel {
	private StudentController studentController;
	JPanel jp;
	JLabel lb1, lb2;
	JComboBox<String> jcb1, jcb2;
	JButton jb;

	JPanel jp1, jp2;
	JLabel jlb1, jlb2;
	JButton jb1, jb2;

	JScrollPane scrollpane1, scrollpane2;
	JTable table1, table2;
	DefaultTableModel model1, model2;

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	//
	// try {
	// UIManager
	// .setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	//
	// } catch (Exception e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	// JFrame jf=new JFrame();
	// jf.add(new select_2(),BorderLayout.CENTER);
	// jf.setSize(740,505);
	// jf.setLocationRelativeTo(null);
	// jf.setVisible(true);
	// }

	public select_2(StudentController studentController) {
		this.studentController = studentController;
		lb1 = new JLabel("��ѡ��Ժϵ��");
		lb2 = new JLabel("��ѡ���꼶��");
		String[] departments = { "��", "���ѧԺ", "��ѧԺ", "��ѧԺ", "�ִ�����ѧԺ", "��ѧϵ",
				"����ϵ", "����ϵ", "��ѧϵ" };
		String[] grade = { "��", "����һ�꼶   ", "���ƶ��꼶   ", "�������꼶   ", "�������꼶   " };
		jcb1 = new JComboBox<String>(departments);
		jcb2 = new JComboBox<String>(grade);
		jb = new JButton("��ʾ�γ�");
		jb.addActionListener(new Listener3());
		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
		jp.add(Box.createHorizontalStrut(10));
		jp.add(lb1);
		jp.add(jcb1);
		jp.add(Box.createHorizontalStrut(10));
		jp.add(lb2);
		jp.add(jcb2);
		jp.add(Box.createHorizontalGlue());
		jp.add(jb);
		jp.add(Box.createHorizontalStrut(10));
		jp.setBorder(new TitledBorder(new EtchedBorder()));

		jlb1 = new JLabel("��רҵѡ�Σ�");
		jlb2 = new JLabel("���Ѿ�ѡ��");
		jb1 = new JButton("ѡ��γ�");
		jb1.addActionListener(new Listener1());
		jb2 = new JButton("�Ƴ��γ�");
		jb2.addActionListener(new Listener2());
		init();
		jp1 = new JPanel();
		jp1.setLayout(new BorderLayout());
		jp1.add(jlb1, BorderLayout.NORTH);
		jp1.add(scrollpane1, BorderLayout.CENTER);
		jp2 = new JPanel();
		jp2.setLayout(new BorderLayout());
		jp2.add(jlb2, BorderLayout.NORTH);
		jp2.add(scrollpane2, BorderLayout.CENTER);

		this.setLayout(null);
		jp.setBounds(15, 10, 680, 33);
		jp1.setBounds(20, 70, 680, 220);
		jb1.setBounds(600, 60, 80, 25);
		jp2.setBounds(20, 320, 680, 130);
		jb2.setBounds(600, 310, 80, 25);
		this.add(jp);
		this.add(jb1);
		this.add(jb2);
		this.add(jp1);
		this.add(jp2);

		String status = studentController.getSystemStatus();
		if (status.equals("1")) {
			jb2.setVisible(false);
			jp2.setVisible(false);
		}
		
		jp.setOpaque(false);
		jp1.setOpaque(false);
		jp2.setOpaque(false);
	}

	void init() {
		// ��רҵѡ�α�ѡ�б�
		String[] row1 = { "�γ̺�", "�γ���", "��ʦ", "ʱ��", "�ص�", "ѧ��", "����", "��ѡ" };
		String[][] data1 = {};
		model1 = new DefaultTableModel(data1, row1);
		table1 = new JTable(model1) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		scrollpane1 = new JScrollPane(table1);
		scrollpane1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// ��ѡ��רҵ��
		ArrayList<CourseVO> myCourseVOs0 = studentController
				.showMypreCourseList(studentController.getcurrentStudent()
						.getID()); // ���е�Ԥѡ�α������еĿ�רҵѡ��ѡ����
		ArrayList<CourseVO> myCourseVOs = new ArrayList<CourseVO>();
		for (CourseVO courseVO : myCourseVOs0) {
			if (courseVO.getCourseType().equals("רҵѡ�޿�")) {
				myCourseVOs.add(courseVO);
			}
		}
		String[] row2 = { "�γ̺�", "�γ���", "��ʦ", "ʱ��", "�ص�", "ѧ��" };
		String[][] data2 = new String[myCourseVOs.size()][7];
		for (int i = 0; i < myCourseVOs.size(); i++) {
			data2[i][0] = myCourseVOs.get(i).getID();
			data2[i][1] = myCourseVOs.get(i).getName();
			data2[i][3] = myCourseVOs.get(i).getTime();
			data2[i][4] = myCourseVOs.get(i).getClassroom();
			data2[i][5] = myCourseVOs.get(i).getCredit() + "";
			ArrayList<TeacherVO> teacherVOs = studentController
					.showCourserTeachers(myCourseVOs.get(i).getID());
			for (TeacherVO teacherVO : teacherVOs) {
				if (data2[i][2] == null) {
					data2[i][2] = (teacherVO.getName() + " ");
				} else {
					data2[i][2] += (teacherVO.getName() + " ");
				}
			}
		}
		model2 = new DefaultTableModel(data2, row2);
		table2 = new JTable(model2) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		scrollpane2 = new JScrollPane(table2);
		scrollpane2
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table1.setOpaque(false);
		scrollpane1.setOpaque(false);
		scrollpane1.getViewport().setOpaque(false);
		table2.setOpaque(false);
		scrollpane2.setOpaque(false);
		scrollpane2.getViewport().setOpaque(false);
	}

	//ѡ��
	class Listener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (studentController.getSystemStatus().equals("1")) {
				if (table1.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "��ѡ��һ�ſΣ�");
				} else {
					String courseid = table1.getValueAt(
							table1.getSelectedRow(), 0).toString();
					if (studentController.reselectOptionalCourse(courseid,
							studentController.getcurrentStudent().getID())) {
						JOptionPane.showMessageDialog(null, "�ɹ�ѡ��ÿγ̣�");
					} else {
						JOptionPane.showMessageDialog(null,
								"ѡ��ʧ�ܣ���ȷ�����Ŀα���û�иÿγ̲���ѡ������δ�ﵽ���ޣ�");
					}
				}
			} else if (studentController.getSystemStatus().equals("0")) {
				if (table1.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "��ѡ��һ�ſΣ�");
				} else if (table2.getRowCount() == 4) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ŀγ������Ѵﵽ���ޣ�");
				} else {
					// ����ɹ����������ݿ��ͬʱ������ѡ�б�
					String courseid = table1.getValueAt(
							table1.getSelectedRow(), 0).toString();
					if (studentController.selectOptionalCourse(courseid,
							studentController.getcurrentStudent().getID())) {
						JOptionPane.showMessageDialog(null, "���ѳɹ�ѡ��ÿγ̣�");
						String s1 = null, s2 = null, s3 = null;
						if (table1.getValueAt(table1.getSelectedRow(), 3) != null) {
							s1 = table1.getValueAt(table1.getSelectedRow(), 3)
									.toString();
						}
						if (table1.getValueAt(table1.getSelectedRow(), 4) != null) {
							s2 = table1.getValueAt(table1.getSelectedRow(), 4)
									.toString();
						}
						if (table1.getValueAt(table1.getSelectedRow(), 5) != null) {
							s3 = table1.getValueAt(table1.getSelectedRow(), 5)
									.toString();
						}

						String data[] = {
								table1.getValueAt(table1.getSelectedRow(), 0)
										.toString(),
								table1.getValueAt(table1.getSelectedRow(), 1)
										.toString(),
								table1.getValueAt(table1.getSelectedRow(), 2)
										.toString(),
								s1,
								s2,
								s3,
								table1.getValueAt(table1.getSelectedRow(), 6)
										.toString() };
						model2.addRow(data);
					}
				}
			}
		}

	}

	//��ѡ
	class Listener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (table2.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "��ѡ��һ�ſΣ�");
			} else {
				// ����ɹ����������ݿ��ͬʱ������ѡ�б�
				String courseid = table2.getValueAt(table2.getSelectedRow(), 0)
						.toString();
				if (studentController.quitpreCourse(courseid, studentController
						.getcurrentStudent().getID())) {
					JOptionPane.showMessageDialog(null, "���ѳɹ���ѡ�ÿγ̣�");
					model2.removeRow(table2.getSelectedRow());
				}
			}

		}

	}

	//��ʾ����б�
	class Listener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// ��רҵѡ�α�ѡ�б�
			String[] row1 = { "�γ̺�", "�γ���", "��ʦ", "ʱ��", "�ص�", "ѧ��", "����", "��ѡ" };
			ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
			courseVOs.addAll(studentController.showCourseList(
					(String) jcb2.getSelectedItem(),
					(String) jcb1.getSelectedItem(), "רҵѡ�޿�"));
			String[][] data1 = new String[courseVOs.size()][9];
			for (int i = 0; i < courseVOs.size(); i++) {
				data1[i][0] = courseVOs.get(i).getID();
				data1[i][1] = courseVOs.get(i).getName();
				data1[i][3] = courseVOs.get(i).getTime();
				data1[i][4] = courseVOs.get(i).getClassroom();
				data1[i][5] = courseVOs.get(i).getCredit() + "";
				data1[i][6] = courseVOs.get(i).getMemberLimit() + "";
				ArrayList<TeacherVO> teacherVOs = studentController
						.showCourserTeachers(courseVOs.get(i).getID());
				for (TeacherVO teacherVO : teacherVOs) {
					if (data1[i][2] == null) {
						data1[i][2] = (teacherVO.getName() + " ");
					} else {
						data1[i][2] += (teacherVO.getName() + " ");
					}
				}
				ArrayList<StudentVO> studentVOs = new ArrayList<StudentVO>();
				if (studentController.getSystemStatus().equals("0")) {
					studentVOs = studentController
							.showCoursepreStudents(courseVOs.get(i).getID());
				} else if (studentController.getSystemStatus().equals("1")) {
					studentVOs = studentController.showCourseStudents(courseVOs
							.get(i).getID());
				}
				int hasselect = studentVOs.size();
				data1[i][7] = hasselect + ""; // �����ѡ���ڼ䣬��ô��ʾ����ѡ������Ԥѡ�б��еģ�����ǲ���ѡ�ڼ䣬��ô��ʾ�ľ�����ʽѧ��������
			}
			model1 = new DefaultTableModel(data1, row1);
			table1 = new JTable(model1) {
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return false;
				}
			};
			scrollpane1 = new JScrollPane(table1);
			scrollpane1
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

			jp1.removeAll();
			jp1.setLayout(new BorderLayout());
			jp1.add(jlb1, BorderLayout.NORTH);
			jp1.add(scrollpane1, BorderLayout.CENTER);
			jp1.repaint();
			select_2.this.repaint();
			
			table1.setOpaque(false);
			scrollpane1.setOpaque(false);
			scrollpane1.getViewport().setOpaque(false);
			
			if(table1.getRowCount()==0){
				JOptionPane.showMessageDialog(null, "�ÿ�רҵ��ʱû�пγ̷�����");
			}
		}

	}

}
