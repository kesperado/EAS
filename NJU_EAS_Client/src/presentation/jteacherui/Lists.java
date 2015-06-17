//查看相应列表
package presentation.jteacherui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import vo.CourseVO;
import vo.SelectionRecordVO;
import vo.StudentVO;
import vo.TeacherVO;
import businesslogiccontroller.jteachercontroller.jTeacherController;

@SuppressWarnings("serial")
public class Lists extends JPanel {
	private jTeacherController jTeacherController;
	JPanel jp1, jp2, jp3;
	JTable table1;
	JScrollPane scrollpane1;

	JLabel jlb1, jlb2, jlb3;
	JComboBox<String> jcb1, jcb2, jcb3;
	JButton jb1, jstat1, jstat2;

	public Lists(jTeacherController jTeacherController) {
		this.jTeacherController = jTeacherController;
		jlb1 = new JLabel("选择列表类型：");
		jlb2 = new JLabel("选择院系部门:");
		jlb3 = new JLabel("选择年级:");
		String[] lists = { "课程", "教师", "学生" };
		jcb1 = new JComboBox<String>(lists);
		jcb1.addActionListener(new Listener0());
		String[] departments = { "软件学院", "商学院", "文学院", "现代工程学院", "数学系", "物理系",
				"天文系", "化学系", "体育部" };
		jcb2 = new JComboBox<String>(departments);
		String[] grade = { "大一", "大二", "大三", "大四", "研一", "研二", "博一", "博二" };
		jcb3 = new JComboBox<String>(grade);
		jb1 = new JButton("查看");
		jb1.addActionListener(new Listener1());

		jp1 = new JPanel();
		jp1.setLayout(new BoxLayout(jp1, BoxLayout.X_AXIS));
		jp1.add(jlb1);
		jp1.add(jcb1);
		jp1.add(Box.createHorizontalStrut(5));
		jp1.add(jlb2);
		jp1.add(jcb2);
		jp1.add(Box.createHorizontalStrut(5));

		jstat1 = new JButton("成绩统计");
		jstat1.addActionListener(new Listener3());
		jstat2 = new JButton("类型统计");
		jstat2.addActionListener(new Listener4());
		// jp1.add(jlb3);
		// jp1.add(jcb3);
		jp1.add(Box.createHorizontalStrut(5));
		jp1.add(jb1);

		jp1.add(Box.createHorizontalGlue());
		jp1.add(jstat1);
		jp1.add(Box.createHorizontalStrut(5));
		jp1.add(jstat2);

		jp1.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		jp2 = new JPanel();
		table1 = new JTable();
		scrollpane1 = new JScrollPane(table1);
		scrollpane1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
		jp2.add(scrollpane1);
		jp2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		jp3 = new JPanel();
		jp3.setBorder(new TitledBorder("详细信息"));
		jp3.setPreferredSize(new Dimension(800, 200));

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createVerticalStrut(10));
		this.add(jp1);
		this.add(Box.createVerticalStrut(10));
		this.add(jp2);
		this.add(Box.createVerticalStrut(10));
		this.add(jp3);

		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		table1.setOpaque(false);
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

	// 对JComboBox做监听
	class Listener0 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!jcb1.getSelectedItem().equals("课程")) {
				jstat1.setVisible(false);
				jstat2.setVisible(false);
			} else {
				jstat1.setVisible(true);
				jstat2.setVisible(true);
			}
		}

	}

	// 列表信息
	class Listener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] row1 = {};
			String[][] data1 = {};
			String type = (String) jcb1.getSelectedItem();
			String department = (String) jcb2.getSelectedItem();
			if (type.equals("课程")) {
				ArrayList<CourseVO> courseVOs = jTeacherController
						.showCourseList(department);
				row1 = new String[] { "课程号", "课程名", "课程类型", "时间", "地点", "学分", };
				data1 = new String[courseVOs.size()][9];
				for (int i = 0; i < courseVOs.size(); i++) {
					data1[i][0] = courseVOs.get(i).getID();
					data1[i][1] = courseVOs.get(i).getName();
					data1[i][2] = courseVOs.get(i).getCourseType();
					data1[i][3] = courseVOs.get(i).getTime();
					data1[i][4] = courseVOs.get(i).getClassroom();
					data1[i][5] = courseVOs.get(i).getCredit() + "";
				}
			} else if (type.equals("教师")) {
				ArrayList<TeacherVO> teacherVOs = jTeacherController
						.showTeacherList(department);
				row1 = new String[] { "工号", "姓名", "类型" };
				data1 = new String[teacherVOs.size()][9];
				for (int i = 0; i < teacherVOs.size(); i++) {
					data1[i][0] = teacherVOs.get(i).getID();
					data1[i][1] = teacherVOs.get(i).getName();
					data1[i][2] = teacherVOs.get(i).getTeacherType();
				}
			} else if (type.equals("学生")) {
				ArrayList<StudentVO> studentVOs = jTeacherController
						.showStudentList(department);
				row1 = new String[] { "学号", "姓名", "年级" };
				data1 = new String[studentVOs.size()][9];
				for (int i = 0; i < studentVOs.size(); i++) {
					data1[i][0] = studentVOs.get(i).getID();
					data1[i][1] = studentVOs.get(i).getName();
					data1[i][2] = studentVOs.get(i).getGrade();
				}
			}
			table1 = new JTable(data1, row1) {
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return false;
				}
			};

			table1.addMouseListener(new Listener2());
			scrollpane1 = new JScrollPane(table1);
			scrollpane1
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jp2.removeAll();
			jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
			jp2.add(scrollpane1);
			jp2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

			table1.setOpaque(false);
			scrollpane1.setOpaque(false);
			scrollpane1.getViewport().setOpaque(false);

			if (data1.length == 0) {
				JOptionPane.showMessageDialog(null, "==信息暂缺==");
			}
		}

	}

	// 详细信息
	class Listener2 implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			String type = (String) jcb1.getSelectedItem();
			String id = (String) table1.getValueAt(table1.getSelectedRow(), 0);
			String info = null;
			if (type.equals("课程")) {
				CourseVO VO = jTeacherController.showCourseInfo(id);
				String l1 = "课程号：" + VO.getID() + "\t" + "课程名：" + VO.getName();
				String l2 = "学分：" + VO.getCredit() + "\t" + "\t" + "课程种类:"
						+ VO.getCourseType();
				String l3 = "上课时间：" + "\t" + "\t" + test(VO.getTime());
				String l4 = "上课地点：" + "\t" + "\t" + test(VO.getClassroom());
				String s1 = "课程大纲：" + "\t" + "\t" + test(VO.getOutline());
				String s2 = "参考书目：" + "\t" + "\t" + test(VO.getReferenceBook());
				String s3 = "教科书目：" + "\t" + "\t" + test(VO.getTextbook());
				info = l1 + "\n" + l2 + "\n" + l3 + "\n" + l4 + "\n" + s1
						+ "\n" + s2 + "\n" + s3;
			} else if (type.equals("教师")) {
				TeacherVO VO = jTeacherController.showTeacherInfo(id);
				String l1 = "工号" + "\t" + VO.getID();
				String l2 = "姓名" + "\t" + VO.getName();
				String l3 = "身份" + "\t" + VO.getTeacherType();
				String l4 = "单位" + "\t" + VO.getDepartment();
				String s1 = "年龄" + "\t" + test(VO.getAge());
				String s2 = "籍贯" + "\t" + test(VO.getHometown());
				String s3 = "联系方式" + "\t" + test(VO.getContact());
				info = l1 + "\n" + l2 + "\n" + l3 + "\n" + l4 + "\n" + s1
						+ "\n" + s2 + "\n" + s3;
			} else if (type.equals("学生")) {
				StudentVO VO = jTeacherController.showStudentInfo(id);
				String l1 = "学号" + "\t" + VO.getID();
				String l2 = "姓名" + "\t" + VO.getName();
				String l3 = "年级" + "\t" + VO.getGrade();
				String l4 = "院系" + "\t" + VO.getDepartment();
				String s1 = "年龄" + "\t" + test(VO.getAge());
				String s2 = "籍贯" + "\t" + test(VO.getHometown());
				String s3 = "联系方式" + "\t" + test(VO.getContact());
				info = l1 + "\n" + l2 + "\n" + l3 + "\n" + l4 + "\n" + s1
						+ "\n" + s2 + "\n" + s3;
			}
			JTextArea jta = new JTextArea();
			jta.setPreferredSize(new Dimension(690, 150));
			jta.setText(info);
			jta.setEditable(false);
			// jta.setOpaque(false);
			jp3.removeAll();
			jp3.add(jta, BorderLayout.CENTER);
		}

		public String test(String s) {
			if (s == null || s.equals("")) {
				return "==信息暂缺==";
			}
			return s;
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	// 学生学分统计信息
	class Listener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CategoryDataset dataset = getDataset();

			JFreeChart chart = ChartFactory.createStackedBarChart("院系课程成绩统计",
					"课程均分分数段", "课程数量", dataset, PlotOrientation.VERTICAL, false,
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
	
			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis
					.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);

			BarRenderer renderer = (BarRenderer) plot
					.getRenderer();
			renderer.setDrawBarOutline(true);
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);

			ChartFrame chartFrame = new ChartFrame("院系所有课程成绩统计", chart);
			// chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
			chartFrame.pack(); // 以合适的大小展现图形
			chartFrame.setVisible(true);// 图形是否可见
			chartFrame.setLocationRelativeTo(null);

		}

		public CategoryDataset getDataset() {

			DefaultCategoryDataset result = new DefaultCategoryDataset();
			int[] numbers = new int[5];
			for(int i=0;i<numbers.length;i++){
				numbers[i]=0;
			}
			for (int i = 0; i < table1.getRowCount(); i++) {
				String courseid = table1.getValueAt(i, 0).toString();
				ArrayList<SelectionRecordVO> scores = jTeacherController.showCourseScores(courseid);
				int averagescore=0;
				for (SelectionRecordVO srvo:scores){
					averagescore+=srvo.getScore();
				}
				if(scores.size()!=0){
					averagescore/=scores.size();
				}
				//将一门课的平均成绩归类到5档中的一档
				if (averagescore<60){
					numbers[0]++;
				}else if(averagescore<70){
					numbers[1]++;
				}else if(averagescore<80){
					numbers[2]++;
				}else if(averagescore<90){
					numbers[3]++;
				}else if(averagescore<=100){
					numbers[4]++;
				}
			}
			String series1 = "";
			// 列
			String category1 = "<60";
			String category2 = "60~69";
			String category3 = "70~79";
			String category4 = "80~89";
			String category5 = "90~100";

			result.addValue(numbers[0], series1, category1);
			result.addValue(numbers[1], series1, category2);
			result.addValue(numbers[2], series1, category3);
			result.addValue(numbers[3], series1, category4);
			result.addValue(numbers[4], series1, category5);
			return result;
		}

	}

	// 学生学分统计信息
	class Listener4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			DefaultPieDataset dataset = getDataset();

			JFreeChart chart = ChartFactory.createPieChart("课程类型统计", dataset, true, true, false);

			chart.setBackgroundPaint(Color.white);
			Font font = new Font("微软雅黑", 5, 15);// 设置字体，否则会显示乱码
			TextTitle title = chart.getTitle();
			title.setFont(font); // 设置标题字体
			PiePlot pieplot = (PiePlot) chart.getPlot(); // 得到一个参考
			pieplot.setBackgroundPaint(Color.white); // 生成图片的背景色
			// 没有数据的时候显示的内容  
	        pieplot.setNoDataMessage("No data available");   
	        // 设置无数据时的信息显示颜色  
	        pieplot.setNoDataMessagePaint(Color.red);  
	        //把Lable 为”Two” 的那一块”挖”出来10%  
	        pieplot.setExplodePercent("Two", 0.3D);  
	        //设置背景透明度  
	        pieplot.setBackgroundAlpha(0.9f);  
	        //设置前景透明度  
	        pieplot.setForegroundAlpha(0.6f);  
	        // 指定饼图轮廓线的颜色  
	        pieplot.setBaseSectionOutlinePaint(Color.white);  
	        pieplot.setBaseSectionPaint(Color.BLACK);  
	        // 指定显示的饼图上圆形(true)还椭圆形(false)  
	        pieplot.setCircular(true);  
	        //设置扇区分离显示  
	        pieplot.setExplodePercent("通识研讨", 0.2D);  
	        //设置扇区边框不可见  
	        pieplot.setSectionOutlinesVisible(false);  
	        
	        //设置扇区标签显示格式：关键字：值(百分比)  
	        pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(  
	                "{0}：{1}({2} percent)"));  
	        //设置扇区标签颜色  
	        pieplot.setLabelBackgroundPaint(new Color(220, 220, 220));  
	        
	        pieplot.setLabelFont((new Font("宋体", Font.PLAIN, 12)));  
	        pieplot.setLabelFont(font);  
	        chart.getLegend().setItemFont(font);
			ChartFrame chartFrame = new ChartFrame("学生学分统计", chart);
			// chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
			chartFrame.pack(); // 以合适的大小展现图形
			chartFrame.setVisible(true);// 图形是否可见
			chartFrame.setLocationRelativeTo(null);

		}

		public DefaultPieDataset getDataset() {
			DefaultPieDataset result=new DefaultPieDataset();
			int[] numbers = new int[4];
			for(int i=0;i<numbers.length;i++){
				numbers[i]=0;
			}
			for (int i = 0; i < table1.getRowCount(); i++) {
				if(table1.getValueAt(i, 2).equals("专业指选课")){
					numbers[0]++;
				}else if(table1.getValueAt(i, 2).equals("专业选修课")){
					numbers[1]++;
				}else if(table1.getValueAt(i, 2).equals("通识课")||table1.getValueAt(i, 2).equals("研讨课")){
					numbers[2]++;
				}else if(table1.getValueAt(i, 2).equals("公共必修课")||table1.getValueAt(i, 2).equals("体育课")){
					numbers[3]++;
				}
				
			}
			result.setValue("专业指选", numbers[0]);
			result.setValue("专业选修", numbers[1]);
			result.setValue("通识研讨", numbers[2]);
			result.setValue("公共必修", numbers[3]);
			return result;
		}

	}
}
