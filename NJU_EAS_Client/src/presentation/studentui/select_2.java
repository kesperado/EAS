//跨专业选课
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
		lb1 = new JLabel("请选择院系：");
		lb2 = new JLabel("请选择年级：");
		String[] departments = { "无", "软件学院", "商学院", "文学院", "现代工程学院", "数学系",
				"物理系", "天文系", "化学系" };
		String[] grade = { "无", "本科一年级   ", "本科二年级   ", "本科三年级   ", "本科四年级   " };
		jcb1 = new JComboBox<String>(departments);
		jcb2 = new JComboBox<String>(grade);
		jb = new JButton("显示课程");
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

		jlb1 = new JLabel("跨专业选课：");
		jlb2 = new JLabel("您已经选择：");
		jb1 = new JButton("选择课程");
		jb1.addActionListener(new Listener1());
		jb2 = new JButton("移除课程");
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
		// 跨专业选课备选列表
		String[] row1 = { "课程号", "课程名", "教师", "时间", "地点", "学分", "上限", "已选" };
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

		// 已选跨专业课
		ArrayList<CourseVO> myCourseVOs0 = studentController
				.showMypreCourseList(studentController.getcurrentStudent()
						.getID()); // 所有的预选课表，将其中的跨专业选修选出来
		ArrayList<CourseVO> myCourseVOs = new ArrayList<CourseVO>();
		for (CourseVO courseVO : myCourseVOs0) {
			if (courseVO.getCourseType().equals("专业选修课")) {
				myCourseVOs.add(courseVO);
			}
		}
		String[] row2 = { "课程号", "课程名", "教师", "时间", "地点", "学分" };
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

	//选课
	class Listener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (studentController.getSystemStatus().equals("1")) {
				if (table1.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "请选中一门课！");
				} else {
					String courseid = table1.getValueAt(
							table1.getSelectedRow(), 0).toString();
					if (studentController.reselectOptionalCourse(courseid,
							studentController.getcurrentStudent().getID())) {
						JOptionPane.showMessageDialog(null, "成功选择该课程！");
					} else {
						JOptionPane.showMessageDialog(null,
								"选课失败，请确认您的课表中没有该课程并且选课人数未达到上限！");
					}
				}
			} else if (studentController.getSystemStatus().equals("0")) {
				if (table1.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "请选中一门课！");
				} else if (table2.getRowCount() == 4) {
					JOptionPane.showMessageDialog(null, "您选择的课程数量已达到上限！");
				} else {
					// 如果成功，更新数据库的同时更新已选列表
					String courseid = table1.getValueAt(
							table1.getSelectedRow(), 0).toString();
					if (studentController.selectOptionalCourse(courseid,
							studentController.getcurrentStudent().getID())) {
						JOptionPane.showMessageDialog(null, "您已成功选择该课程！");
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

	//退选
	class Listener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (table2.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "请选中一门课！");
			} else {
				// 如果成功，更新数据库的同时更新已选列表
				String courseid = table2.getValueAt(table2.getSelectedRow(), 0)
						.toString();
				if (studentController.quitpreCourse(courseid, studentController
						.getcurrentStudent().getID())) {
					JOptionPane.showMessageDialog(null, "您已成功退选该课程！");
					model2.removeRow(table2.getSelectedRow());
				}
			}

		}

	}

	//显示相关列表
	class Listener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 跨专业选课备选列表
			String[] row1 = { "课程号", "课程名", "教师", "时间", "地点", "学分", "上限", "已选" };
			ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
			courseVOs.addAll(studentController.showCourseList(
					(String) jcb2.getSelectedItem(),
					(String) jcb1.getSelectedItem(), "专业选修课"));
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
				data1[i][7] = hasselect + ""; // 如果是选课期间，那么显示的已选人数是预选列表中的，如果是补退选期间，那么显示的就是正式学生的人数
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
				JOptionPane.showMessageDialog(null, "该跨专业暂时没有课程发布！");
			}
		}

	}

}
