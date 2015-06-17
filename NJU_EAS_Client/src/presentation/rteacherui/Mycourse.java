//教师查看课程&学生
package presentation.rteacherui;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import vo.CourseVO;
import vo.SelectionRecordVO;
import vo.StudentVO;
import businesslogiccontroller.rteachercontroller.rTeacherController;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Mycourse extends JPanel {
	private rTeacherController rTeacherController;
	JLabel jlb1, jlb2, jlb3;
	JComboBox<String> jcb1;
	JButton jb1, jb2, jb3, jbexcel, jbstat, jhistory;
	JPanel jp1, jp2, jp3, jp4;
	JTable table1, table2;
	JScrollPane scrollPane1, scrollPane2;

	public Mycourse(rTeacherController rTeacherController) {
		this.rTeacherController = rTeacherController;
		jlb1 = new JLabel("我的课程：");
		// String[] columnName = {"课程号","课程名","学分","时间","地点" };
		// String[][] rowData = {};
		// JTable table = new JTable(new DefaultTableModel(rowData,
		// columnName));
		// table.getColumnModel().getColumn(4).setPreferredWidth(100);
		// JScrollPane scrollPane = new JScrollPane(table);
		// scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp1 = new JPanel();
		setTabel();
		jp1.setPreferredSize(new Dimension(800, 250));
		jp1.setLayout(new BorderLayout());
		jp1.add(jlb1, BorderLayout.NORTH);
		jp1.add(scrollPane1, BorderLayout.CENTER);
		jp1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// jlb2=new JLabel("请选择课程：");
		// jcb1=new JComboBox();
		jlb3 = new JLabel();
		jb1 = new JButton("显示学生列表/登记成绩");
		jb1.addActionListener(new Listener1());
		jhistory = new JButton("查看历史成绩");
		jhistory.addActionListener(new Listener6());
		jp2 = new JPanel();
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
		jp2.add(Box.createHorizontalStrut(10));
		// jp2.add(jlb2);
		// jp2.add(Box.createHorizontalStrut(10));
		// jp2.add(jcb1);
		// jp2.add(Box.createHorizontalGlue());
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(jlb3);
		jp2.add(Box.createHorizontalGlue());
		jp2.add(jb1);
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(jhistory);
		jp2.add(Box.createHorizontalStrut(10));

		String[] columnStu = { "院系", "学号", "姓名", "分数" };
		String[][] rowStu = {};
		JTable tableStu = new JTable(new DefaultTableModel(rowStu, columnStu));
		JScrollPane scrollPaneStu = new JScrollPane(tableStu);
		scrollPaneStu
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp3 = new JPanel();
		jp3.setPreferredSize(new Dimension(800, 720));
		jp3.setLayout(new BorderLayout());
		jp3.add(scrollPaneStu, BorderLayout.CENTER);
		jp3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		jb2 = new JButton("开始填写成绩");
		jb2.addActionListener(new Listener2());
		jb3 = new JButton("确认完毕并提交");
		jb3.addActionListener(new Listener3());
		jbexcel = new JButton("从Excel导入成绩");
		jbexcel.addActionListener(new Listener4());
		jbstat = new JButton("查看统计信息");
		jbstat.addActionListener(new Listener5());
		jp4 = new JPanel();
		jp4.setLayout(new BoxLayout(jp4, BoxLayout.X_AXIS));
		jp4.add(Box.createHorizontalGlue());
		jp4.add(jb2);
		jp4.add(Box.createHorizontalStrut(10));
		jp4.add(jb3);
		jp4.add(Box.createHorizontalStrut(10));
		jp4.add(jbexcel);
		jp4.add(Box.createHorizontalStrut(10));
		jp4.add(jbstat);
		jp4.add(Box.createHorizontalStrut(10));

		jlb3.setVisible(false);
		jp3.setVisible(false);
		jp4.setVisible(false);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(Box.createVerticalStrut(10));
		this.setVisible(true);

		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		Image im = null;
		try {
			im = ImageIO.read(new File("Images/back.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	// 显示课程列表
	void setTabel() {
		String[] columnName = { "课程号", "课程名", "学分", "时间", "地点" };
		ArrayList<CourseVO> courseVOs = rTeacherController
				.showMyCourseList(rTeacherController.getcurrentrTeacherVO()
						.getID());

		String[][] courseData = new String[courseVOs.size()][6];
		for (int i = 0; i < courseVOs.size(); i++) {
			courseData[i][0] = courseVOs.get(i).getID();
			courseData[i][1] = courseVOs.get(i).getName();
			courseData[i][2] = courseVOs.get(i).getCredit() + "";
			courseData[i][3] = courseVOs.get(i).getTime();
			courseData[i][4] = courseVOs.get(i).getClassroom();
		}

		table1 = new JTable(courseData, columnName) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		scrollPane1 = new JScrollPane(table1);
		scrollPane1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		table1.setOpaque(false);
		scrollPane1.setOpaque(false);
		scrollPane1.getViewport().setOpaque(false);

		// if(table1.getRowCount()==0){
		// JOptionPane.showMessageDialog(null, "暂时尚未收到课程安排");
		// }
	}

	// //得到教师的课程列表
	// class Listener1 implements ActionListener{
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// String[] columnName = {"课程号","课程名","学分","上课时间及地点","备注" };
	// ArrayList<CourseVO> myCourseVOs =
	// rTeacherController.showMyCourseList(rTeacherController.getcurrenrTeacherVO().getID());
	// //接下来就是把课程信息从VO里面提取出来存在表里
	// }
	//
	// }

	// 显示某课程的学生列表
	class Listener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (table1.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "请选择一门课");
			} else {
				String[] columnStu = { "院系", "学号", "姓名", "分数" };
				String courseid = table1.getValueAt(table1.getSelectedRow(), 0)
						.toString();// 这个要改为从列表里面读取选中的课程的课程号
				ArrayList<SelectionRecordVO> courseScores = rTeacherController
						.showCourseScores(courseid);
				ArrayList<StudentVO> courseStudentVOs = rTeacherController
						.showCourseStudents(courseid);
				String[][] scoreData = new String[courseScores.size()][4];
				for (int i = 0; i < courseScores.size(); i++) {
					scoreData[i][0] = courseStudentVOs.get(i).getDepartment();
					scoreData[i][1] = courseStudentVOs.get(i).getID();
					scoreData[i][2] = courseStudentVOs.get(i).getName();
					scoreData[i][3] = "" + courseScores.get(i).getScore();
				}

				table2 = new JTable();
				DefaultTableModel model = new DefaultTableModel(scoreData,
						columnStu) {
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return false;
					}
				};
				table2.setModel(model);

				if (table2.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "暂时尚未有学生选课");
				} else {
					scrollPane2 = new JScrollPane(table2);
					scrollPane2
							.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

					jlb3.setText("总人数有：" + table2.getRowCount());
					jp2.repaint();

					jp3.removeAll();
					jp3.setPreferredSize(new Dimension(800, 720));
					jp3.setLayout(new BorderLayout());
					jp3.add(scrollPane2, BorderLayout.CENTER);
					jp3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,
							10));
					jp3.repaint();

					jlb3.setVisible(true);
					jp3.setVisible(true);
					jp4.setVisible(true);

					table2.setOpaque(false);
					scrollPane2.setOpaque(false);
					scrollPane2.getViewport().setOpaque(false);
				}

			}
		}

	}

	// 这个只是将第四列成绩列改为可编辑的，没必要刷新列表吧，这样会有闪一下的效果，不太好。
	class Listener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String[] columnStu = { "院系", "学号", "姓名", "分数" };
			String courseid = table1.getValueAt(table1.getSelectedRow(), 0)
					.toString();// 这个要改为从列表里面读取选中的课程的课程号
			ArrayList<SelectionRecordVO> courseScores = rTeacherController
					.showCourseScores(courseid);
			ArrayList<StudentVO> courseStudentVOs = rTeacherController
					.showCourseStudents(courseid);
			String[][] scoreData = new String[courseScores.size()][4];
			for (int i = 0; i < courseScores.size(); i++) {
				scoreData[i][0] = courseStudentVOs.get(i).getDepartment();
				scoreData[i][1] = courseStudentVOs.get(i).getID();
				scoreData[i][2] = courseStudentVOs.get(i).getName();
				scoreData[i][3] = "" + courseScores.get(i).getScore();
			}

			DefaultTableModel model = new DefaultTableModel(scoreData,
					columnStu) {
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					if (columnIndex != 3)
						return false;// 这个是可以编辑的列
					else
						return true;
				}
			};
			table2.setModel(model);
			table2.setEditingColumn(3);

		}

	}

	// 登记成绩
	class Listener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ArrayList<SelectionRecordVO> scores = new ArrayList<SelectionRecordVO>();
			// 这个VO 只需要获取三项数据： courseid, studentid,score
			// 然后把每个VO都放进这个Arraylist里面就可以了。
			// 下面这个方法就会登记成绩
			boolean OK = true;
			String courseid = table1.getValueAt(table1.getSelectedRow(), 0)
					.toString();// 这个要改为从列表里面读取选中的课程的课程号

			for (int i = 0; i < table2.getRowCount() && OK; i++) {
				String studentid = table2.getValueAt(i, 1).toString();
				int score = 0;
				try {
					score = Integer
							.parseInt(table2.getValueAt(i, 3).toString());
				} catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "第" + (i + 1)
							+ "行数据格式有误");
					OK = false;
				}
				if (score > 100 || score < 0) {
					JOptionPane.showMessageDialog(null, "第" + (i + 1)
							+ "行数据超出范围");
					OK = false;
				}
				// 此处添加方法将成绩写入！
				SelectionRecordVO sr = new SelectionRecordVO(courseid,
						studentid, score);
				scores.add(sr);
				System.out.println(courseid + studentid + score);
			}

			if (OK) {
				if (rTeacherController.recordScore(scores)) {
					JOptionPane.showMessageDialog(null, "登记成功！");
					jlb3.setVisible(false);
					jp3.setVisible(false);
					jp4.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "登记失败！");
				}
			}

		}

	}

	class Listener4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean success = false;
			String courseid = table1.getValueAt(table1.getSelectedRow(), 0)
					.toString();
			String path = null;
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("文件选择");
			jfc.setApproveButtonText("选择");
			jfc.setApproveButtonToolTipText("文件选择");
			jfc.setSelectedFile(new File("*.xls"));
			jfc.setPreferredSize(new Dimension(600, 480));
			String dirName = jfc.getCurrentDirectory().toString().trim();
			if (dirName == null || dirName.trim().length() == 0) {
				jfc.setCurrentDirectory(new File("."));
			} else {
				jfc.setCurrentDirectory(new File(dirName));
			}
			int rtVal = jfc.showOpenDialog(new JDialog());
			if (rtVal == JFileChooser.APPROVE_OPTION) {
				path = jfc.getSelectedFile().getAbsolutePath();
			}
			if (path != null) {
				success = rTeacherController.recordScoreByExcel(path, courseid);
			}
			if (success) {
				JOptionPane.showMessageDialog(null, "登记成功！");
				jlb3.setVisible(false);
				jp3.setVisible(false);
				jp4.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "登记失败！");
			}

		}

	}

	// 统计信息
	class Listener5 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			JFreeChart chart = ChartFactory.createBarChart3D("成绩统计", "成绩",
					"数量", getDataset(), PlotOrientation.VERTICAL, false, false,
					false);
			chart.setBackgroundPaint(Color.white);
			Font font = new Font("微软雅黑", 5, 15);// 设置字体，否则会显示乱码
			TextTitle title = chart.getTitle();
			title.setFont(font); // 设置标题字体
			CategoryPlot plot = (CategoryPlot) chart.getPlot(); // 得到一个参考
			plot.setBackgroundPaint(Color.white); // 生成图片的背景色
			plot.setRangeGridlinePaint(Color.BLACK); // 行线的颜色
			plot.getDomainAxis().setTickLabelFont(font); // 刻度字体
			plot.getDomainAxis().setLabelFont(font); // X轴名称字体
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis(); // 设置显示整数
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setUpperMargin(0.15);// 设置上部空白
			rangeAxis.setLabelFont(font);// 设置y轴名称字体
			// chart.getLegend().setItemFont(font);
			ChartFrame chartFrame = new ChartFrame("成绩统计", chart);
			// chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
			chartFrame.pack(); // 以合适的大小展现图形
			chartFrame.setVisible(true);// 图形是否可见
			chartFrame.setLocationRelativeTo(null);

		}

		public CategoryDataset getDataset() {
			String courseid = table1.getValueAt(table1.getSelectedRow(), 0)
					.toString();// 这个要改为从列表里面读取选中的课程的课程号
			ArrayList<SelectionRecordVO> scores = rTeacherController
					.showCourseScores(courseid);

			int[] numbers = new int[5];
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = 0;
			}
			for (SelectionRecordVO srvo : scores) {
				if (srvo.getScore() < 60) {
					numbers[0]++;
				} else if (srvo.getScore() < 70) {
					numbers[1]++;
				} else if (srvo.getScore() < 80) {
					numbers[2]++;
				} else if (srvo.getScore() < 90) {
					numbers[3]++;
				} else if (srvo.getScore() <= 100) {
					numbers[4]++;
				}
			}
			String series1 = "主修";
			// String series2 = "选修";

			// 列
			String category1 = "<60";
			String category2 = "60~69";
			String category3 = "70~79";
			String category4 = "80~89";
			String category5 = "90~100";

			// 创建数据源
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			// 放入数据

			dataset.addValue(numbers[0], series1, category1);
			dataset.addValue(numbers[1], series1, category2);
			dataset.addValue(numbers[2], series1, category3);
			dataset.addValue(numbers[3], series1, category4);
			dataset.addValue(numbers[4], series1, category5);

			// http://www.open-open.com/lib/view/open1365997415828.html参考

			return dataset;
		}

	}

	class Listener6 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (table1.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "请选择一门课");
			} else {
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				dataset.addValue(69, "", "2009");
				dataset.addValue(81, "", "2010");
				dataset.addValue(76, "", "2011");
				dataset.addValue(77, "", "2012");
				dataset.addValue(83, "", "2013");
				dataset.addValue(78, "", "2014");
				JFreeChart chart = ChartFactory.createLineChart("课程历史均分曲线", // 标题
						"年份", // 横坐标
						"分数", // 纵坐标
						dataset, // 数据
						PlotOrientation.VERTICAL, // 竖直图表
						false, // 是否显示legend
						false, // 是否显示tooltip
						false // 是否使用url链接
						);
				chart.setBackgroundPaint(Color.white);
				Font font = new Font("微软雅黑", 5, 15);// 设置字体，否则会显示乱码
				TextTitle title = chart.getTitle();
				title.setFont(font); // 设置标题字体
				CategoryPlot plot = (CategoryPlot) chart.getPlot(); // 得到一个参考
				plot.setBackgroundPaint(Color.white); // 生成图片的背景色
				plot.setRangeGridlinePaint(Color.BLACK); // 行线的颜色
				plot.getDomainAxis().setTickLabelFont(font); // 刻度字体
				plot.getDomainAxis().setLabelFont(font); // X轴名称字体
				NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis(); // 设置显示整数
				rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				rangeAxis.setUpperMargin(0.15);// 设置上部空白
				rangeAxis.setLabelFont(font);// 设置y轴名称字体
				// 设置字体
				ChartFrame chartFrame = new ChartFrame("历史均分统计", chart);
				// chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
				chartFrame.pack(); // 以合适的大小展现图形
				chartFrame.setVisible(true);// 图形是否可见
				chartFrame.setLocationRelativeTo(null);
			}
		}

	}
}
