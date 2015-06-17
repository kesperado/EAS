//查看列表
package presentation.yjteacherui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


import vo.CourseVO;
import vo.SelectionRecordVO;
import vo.StudentVO;
import vo.TeacherVO;
import businesslogiccontroller.yjteachercontroller.yjTeacherController;

@SuppressWarnings("serial")
public class List extends JPanel {
	private yjTeacherController yjTeacherController;
	JPanel jp1, jp2, jp3;
	JTable table;
	JScrollPane scrollpane1;

	JLabel jlb1, jlb2, jlb3;
	JComboBox<String> jcb1, jcb2, jcb3;
	JButton jb1, jb2;
	JButton jstatb1, jstatb2, jstatb3, jstatb4, jstatb5;// 统计信息

	public List(yjTeacherController yjTeacherController) {
		this.yjTeacherController = yjTeacherController;
		jlb1 = new JLabel("选择列表：");
		jlb2 = new JLabel("选择年级:");
		jlb3 = new JLabel("选择教师类型：");
		String[] lists = { "课程", "教师", "学生" };
		jcb1 = new JComboBox<String>(lists);
		jcb1.addActionListener(new Listener0());
		String[] grade = { "无", "本科一年级", "本科二年级", "本科三年级", "本科四年级" };
		jcb2 = new JComboBox<String>(grade);
		String[] type = { "任课老师", "院系老师" };
		jcb3 = new JComboBox<String>(type);
		jb1 = new JButton("查看");
		jb1.addActionListener(new Listener1());
		jb2 = new JButton("查看详细信息");
		// jb2.addActionListener(new Listener2());

		jp1 = new JPanel();
		jp1.setLayout(new BoxLayout(jp1, BoxLayout.X_AXIS));
		jp1.add(jlb1);
		jp1.add(jcb1);
		jp1.add(Box.createHorizontalStrut(10));
		jp1.add(jlb2);
		jp1.add(jcb2);
		// jp1.setPreferredSize(new Dimension(800,30));

		jp1.add(jlb3);
		jp1.add(jcb3);
		jlb3.setVisible(false);
		jcb3.setVisible(false);
		jp1.add(Box.createHorizontalGlue());
		jp1.add(jb1);
		jp1.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		jp2 = new JPanel();
		table = new JTable();
		// table.addMouseListener(new Listener2());
		scrollpane1 = new JScrollPane(table);
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
		jp2.add(scrollpane1);
		jp2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		jstatb1 = new JButton("课程均分及及格率");
		jstatb1.addActionListener(new Listener2());
		jstatb2 = new JButton("课程选课分布");
		jstatb2.addActionListener(new Listener3());
		jstatb3 = new JButton("学生学分分布");
		jstatb3.addActionListener(new Listener5());
		jstatb4 = new JButton("老师授课课时分布");
		jstatb4.addActionListener(new Listener4());
		jstatb5 = new JButton("课程评分统计");
		jstatb5.addActionListener(new Listener6());
		// jp2.add(jstatb3);
		// jp2.add(Box.createHorizontalStrut(10));
		// jp2.add(jstatb4);
		// jstatb3.setVisible(false);
		// jstatb4.setVisible(false);

		jp3 = new JPanel();
		jp3.setLayout(new BoxLayout(jp3, BoxLayout.Y_AXIS));
		jp3.setLayout(new FlowLayout(FlowLayout.CENTER));

		jp3.add(Box.createHorizontalGlue());
		jp3.add(jstatb1);
		jp3.add(Box.createHorizontalStrut(10));
		jp3.add(jstatb2);
		jp3.add(Box.createHorizontalStrut(10));
		jp3.add(jstatb5);
		jp3.add(Box.createHorizontalStrut(10));
		jp3.add(jstatb3);
		jp3.add(Box.createHorizontalStrut(10));
		jp3.add(jstatb4);
		jstatb3.setVisible(false);
		jstatb4.setVisible(false);

		// this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		// this.add(Box.createVerticalStrut(10));
		// this.add(jp1);
		// this.add(Box.createVerticalStrut(10));
		// this.add(jp2);
		// this.add(Box.createVerticalStrut(10));
		// this.add(jp3);
		this.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		this.setLayout(new BorderLayout());
		this.add(jp1, BorderLayout.NORTH);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);
		jp3.setVisible(true);

		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		table.setOpaque(false);
		scrollpane1.setOpaque(false);
		scrollpane1.getViewport().setOpaque(false);
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

	// 对JComboBox做监听（教师选择其教师类型）
	class Listener0 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (jcb1.getSelectedItem().equals("教师")) {
				jlb2.setVisible(false);
				jcb2.setVisible(false);

				jlb3.setVisible(true);
				jcb3.setVisible(true);
				// 以上为jp1头部的实时切换，以下为jp3尾部按钮的实时切换
				jstatb4.setVisible(true);
				jstatb1.setVisible(false);
				jstatb2.setVisible(false);
				jstatb3.setVisible(false);
				jstatb5.setVisible(false);
			} else {
				jlb3.setVisible(false);
				jcb3.setVisible(false);
				jlb2.setVisible(true);
				jcb2.setVisible(true);
				if (jcb1.getSelectedItem().equals("课程")) {

					jstatb1.setVisible(true);
					jstatb2.setVisible(true);
					jstatb5.setVisible(true);
					jstatb3.setVisible(false);
					jstatb4.setVisible(false);
				} else if (jcb1.getSelectedItem().equals("学生")) {
					jstatb3.setVisible(true);
					jstatb1.setVisible(false);
					jstatb2.setVisible(false);
					jstatb4.setVisible(false);
					jstatb5.setVisible(false);
				}
			}
		}

	}

	// 显示列表
	class Listener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String type = (String) jcb1.getSelectedItem();
			String grade = (String) jcb2.getSelectedItem();
			String teachertype = (String) jcb3.getSelectedItem();
			if (type.equals("课程")) {
				ArrayList<CourseVO> courseVOs = yjTeacherController
						.showCourseList(grade, yjTeacherController
								.getcurrentyjTeacherVO().getDepartment());
				String[][] courseData = new String[courseVOs.size()][6];
				for (int i = 0; i < courseVOs.size(); i++) {
					courseData[i][0] = courseVOs.get(i).getID();
					courseData[i][1] = courseVOs.get(i).getName();
					courseData[i][2] = courseVOs.get(i).getCourseType();
					courseData[i][4] = courseVOs.get(i).getTime();
					courseData[i][5] = courseVOs.get(i).getClassroom();
					ArrayList<TeacherVO> teacherVOs = yjTeacherController
							.showCourserTeachers(courseVOs.get(i).getID());
					for (TeacherVO teacherVO : teacherVOs) {
						if (courseData[i][3] == null) {
							courseData[i][3] = (teacherVO.getName() + " ");
						} else {
							courseData[i][3] += (teacherVO.getName() + " ");
						}
					}
				}
				String[] columnName = { "课程号", "课程名", "课程类型", "教师", "时间", "地点" };
				table = new JTable(
						new DefaultTableModel(courseData, columnName)) {
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return false;
					}
				};
				scrollpane1 = new JScrollPane(table);
				jp2.removeAll();
				jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
				jp2.add(scrollpane1);
				jp2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
				jp2.repaint();

			} else if (type.equals("学生")) {
				// 通过年级和院系筛选，所以只需要两个属性，id和name
				ArrayList<StudentVO> studentVOs = yjTeacherController
						.showStudentList(grade, yjTeacherController
								.getcurrentyjTeacherVO().getDepartment());
				String[][] studentData = new String[studentVOs.size()][2];
				for (int i = 0; i < studentVOs.size(); i++) {
					studentData[i][0] = studentVOs.get(i).getID();
					studentData[i][1] = studentVOs.get(i).getName();
				}
				String[] columnName = { "学生学号", "学生姓名" };
				table = new JTable(new DefaultTableModel(studentData,
						columnName)) {
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return false;
					}
				};
				scrollpane1 = new JScrollPane(table);
				jp2.removeAll();
				jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
				jp2.add(scrollpane1);
				jp2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
				jp2.repaint();

			} else if (type.equals("教师")) {
				// 需要把jcb3设置一下。
				// 需要吧teachertype改成三种
				if (teachertype.equals("任课老师")) {
					teachertype = "任课老师";
				} else if (teachertype.equals("院系老师")) {
					teachertype = "院系教务老师";
				} else if (teachertype.equals("教务处老师")) {
					teachertype = "教务处老师";
				}

				ArrayList<TeacherVO> teacherVOs = yjTeacherController
						.showTeacherList(teachertype, yjTeacherController
								.getcurrentyjTeacherVO().getDepartment());
				String[][] teacherData = new String[teacherVOs.size()][2];
				for (int i = 0; i < teacherVOs.size(); i++) {
					teacherData[i][0] = teacherVOs.get(i).getID();
					teacherData[i][1] = teacherVOs.get(i).getName();
				}
				String[] columnName = { "教师工号", "教师姓名" };
				table = new JTable(new DefaultTableModel(teacherData,
						columnName)) {
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return false;
					}
				};
				scrollpane1 = new JScrollPane(table);
				jp2.removeAll();
				jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
				jp2.add(scrollpane1);
				jp2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
				jp2.repaint();
			}

			table.setOpaque(false);
			scrollpane1.setOpaque(false);
			scrollpane1.getViewport().setOpaque(false);

			if (table.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "==信息暂缺==");
			}
		}

	}

	// 课程成绩均分和及格率统计信息
	class Listener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<CategoryDataset> datasets = getDataset();

			JFreeChart chart = ChartFactory.createBarChart("成绩统计", "课程名称",
					"分数", datasets.get(0), PlotOrientation.VERTICAL, false,
					false, false);

			CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();// 图本身
			categoryplot.setDataset(1, datasets.get(1));// 放折线图数据

			categoryplot.mapDatasetToRangeAxis(1, 1);
			CategoryAxis categoryaxis = categoryplot.getDomainAxis();
			categoryaxis
					.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
			NumberAxis numberaxis = new NumberAxis("及格率");
			categoryplot.setRangeAxis(1, numberaxis);

			LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
			lineandshaperenderer
					.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
			categoryplot.setRenderer(1, lineandshaperenderer);
			categoryplot
					.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);// 折线在柱面前面显示

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

		public ArrayList<CategoryDataset> getDataset() {
			ArrayList<CategoryDataset> result = new ArrayList<CategoryDataset>();
			DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
			// TableModel tm = table.getModel();
			// 对列表中的每门课进行平均分和及格率统计
			for (int i = 0; i < table.getRowCount(); i++) {
				String courseid = table.getValueAt(i, 0).toString();
				ArrayList<SelectionRecordVO> scores = yjTeacherController
						.showCourseScores(courseid);

				int averagescore = 0;
				int fail = 0;// 不及格人数
				int passrate = 0;// 及格率
				for (SelectionRecordVO srvo : scores) {
					averagescore += srvo.getScore();
					if (srvo.getScore() < 60)
						fail++;
				}

				if (scores.size() != 0) {
					averagescore /= scores.size();
					passrate = (scores.size() - fail) * 100 / scores.size();
				}
				dataset1.addValue(averagescore, "", courseid);
				dataset2.addValue(passrate, "", courseid);
			}
			result.add(dataset1);
			result.add(dataset2);
			return result;
		}

	}

	// 课程选课人数统计信息
	class Listener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CategoryDataset dataset = getDataset();

			JFreeChart chart = ChartFactory.createStackedBarChart("课程选课统计",
					"课程号", "人数", dataset, PlotOrientation.VERTICAL, true,
					false, false);

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
			chart.getLegend().setItemFont(font);

			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis
					.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);

			StackedBarRenderer renderer = (StackedBarRenderer) plot
					.getRenderer();
			renderer.setDrawBarOutline(true);
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);

			ChartFrame chartFrame = new ChartFrame("课程选课统计", chart);
			// chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
			chartFrame.pack(); // 以合适的大小展现图形
			chartFrame.setVisible(true);// 图形是否可见
			chartFrame.setLocationRelativeTo(null);

		}

		public CategoryDataset getDataset() {
			String currentDepartment = yjTeacherController
					.getcurrentyjTeacherVO().getDepartment();
			DefaultCategoryDataset result = new DefaultCategoryDataset();
			for (int i = 0; i < table.getRowCount(); i++) {
				String courseid = table.getValueAt(i, 0).toString();
				ArrayList<StudentVO> students = yjTeacherController
						.showCourseStudents(courseid);
				int owndepart = 0;// 本院系选课人数
				int otherdepart = 0;// 跨院系
				for (StudentVO svo : students) {
					if (svo.getDepartment().equals(currentDepartment)) {
						owndepart++;
					} else {
						otherdepart++;
					}
				}
				result.addValue(owndepart, "本院系学生", courseid);
				result.addValue(otherdepart, "其他院系学生", courseid);
			}

			return result;
		}

	}

	// 教师授课课时统计信息
	class Listener4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CategoryDataset dataset = getDataset();

			JFreeChart chart = ChartFactory.createBarChart("教师授课统计",
					"教师工号", "周课时", dataset, PlotOrientation.VERTICAL, false,
					false, false);

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
//			chart.getLegend().setItemFont(font);

			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis
					.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);

	

			ChartFrame chartFrame = new ChartFrame("教师授课统计", chart);
			// chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
			chartFrame.pack(); // 以合适的大小展现图形
			chartFrame.setVisible(true);// 图形是否可见
			chartFrame.setLocationRelativeTo(null);

		}

		public CategoryDataset getDataset() {

			DefaultCategoryDataset result = new DefaultCategoryDataset();
			for (int i = 0; i < table.getRowCount(); i++) {
				String teacherid = table.getValueAt(i, 0).toString();
				ArrayList<CourseVO> courses = yjTeacherController.showTeacherCourseList(teacherid);
				int classtime=0;
				for (CourseVO cvo : courses) {
					classtime+=cvo.getCredit();
				}
				result.addValue(classtime, "", teacherid);

			}

			return result;
		}

	}

	// 学生学分统计信息
	class Listener5 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CategoryDataset dataset = getDataset();

			JFreeChart chart = ChartFactory.createStackedBarChart("学生学分统计",
					"学号", "学分数", dataset, PlotOrientation.VERTICAL, true,
					false, false);

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
			chart.getLegend().setItemFont(font);

			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis
					.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);

			StackedBarRenderer renderer = (StackedBarRenderer) plot
					.getRenderer();
			renderer.setDrawBarOutline(true);
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);

			ChartFrame chartFrame = new ChartFrame("学生学分统计", chart);
			// chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
			chartFrame.pack(); // 以合适的大小展现图形
			chartFrame.setVisible(true);// 图形是否可见
			chartFrame.setLocationRelativeTo(null);

		}

		public CategoryDataset getDataset() {

			DefaultCategoryDataset result = new DefaultCategoryDataset();
			for (int i = 0; i < table.getRowCount(); i++) {
				String studentid = table.getValueAt(i, 0).toString();
				ArrayList<Integer> credits= yjTeacherController.showStudentCredits(studentid);
				result.addValue(credits.get(0)+credits.get(3), "必修", studentid);
				result.addValue(credits.get(1), "选修", studentid);
				result.addValue(credits.get(2), "14学分", studentid);
			}

			return result;
		}

	}
	// 课程成绩均分和及格率统计信息
	class Listener6 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CategoryDataset dataset = getDataset();

			JFreeChart chart = ChartFactory.createBarChart("课程评分统计", "课程号",
					"分数", dataset, PlotOrientation.VERTICAL, false,
					false, false);

			CategoryPlot plot = (CategoryPlot) chart.getPlot();// 图本身
			
			BarRenderer renderer = (BarRenderer) plot
					.getRenderer();
			renderer.setDrawBarOutline(true);
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);			
			
			chart.setBackgroundPaint(Color.white);
			Font font = new Font("微软雅黑", 5, 15);// 设置字体，否则会显示乱码
			TextTitle title = chart.getTitle();
			title.setFont(font); // 设置标题字体

			plot.setBackgroundPaint(Color.white); // 生成图片的背景色
			plot.setRangeGridlinePaint(Color.BLACK); // 行线的颜色
			plot.getDomainAxis().setTickLabelFont(font); // 刻度字体
			plot.getDomainAxis().setLabelFont(font); // X轴名称字体
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis(); // 设置显示整数
//			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setUpperMargin(0.15);// 设置上部空白
			rangeAxis.setLabelFont(font);// 设置y轴名称字体
			
			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis
					.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
			
			// chart.getLegend().setItemFont(font);
			ChartFrame chartFrame = new ChartFrame("成绩统计", chart);
			// chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
			chartFrame.pack(); // 以合适的大小展现图形
			chartFrame.setVisible(true);// 图形是否可见
			chartFrame.setLocationRelativeTo(null);

		}

		public CategoryDataset getDataset() {

			DefaultCategoryDataset result = new DefaultCategoryDataset();
			for (int i = 0; i < table.getRowCount(); i++) {
				String courseid = table.getValueAt(i, 0).toString();
				
				result.addValue(yjTeacherController.showCourseAssess(courseid), "", courseid);

			}

			return result;
		}


	}
}
