//查看学期成绩以及学分统计
package presentation.studentui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
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
import businesslogiccontroller.studentcontroller.StudentController;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Score extends JPanel {
	private StudentController studentController;
	JLabel jlb1;
	JButton jb1;
	JComboBox<String> jcb;
	JPanel jp1, jp2, jp3;
	JTable table;
	JScrollPane scrollPane;

	JButton jb, jstat, jsc;

	public Score(StudentController studentController) {
		this.studentController = studentController;
		// jlb1=new JLabel("请选择学期：");
		// String[] grades={"大一上","大一下","大二上","大二下","大三上","大三下","大四上","大四下",};
		// jcb=new JComboBox<String>(grades);
		// jb1=new JButton("查看详细信息");
		// jp1=new JPanel();
		// jp1.setLayout(new BoxLayout(jp1,BoxLayout.X_AXIS));
		// jp1.add(Box.createHorizontalStrut(10));
		// jp1.add(jlb1);
		// jp1.add(Box.createHorizontalStrut(10));
		// jp1.add(jcb);
		// jp1.add(Box.createHorizontalStrut(430));
		// jp1.add(jb1);
		// jp1.add(Box.createHorizontalStrut(10));
		// jp1.setBorder(BorderFactory.createEmptyBorder (10, 10, 0, 10));

		String[] columnName = { "课程号", "课程名", "学分", "成绩", "排名" };
		String[][] rowData = {};
		table = new JTable(new DefaultTableModel(rowData, columnName));
		scrollPane = new JScrollPane(table);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp2 = new JPanel();
		setTable();
		jp2.setLayout(new BorderLayout());
		jp2.add(scrollPane, BorderLayout.CENTER);
		jp2.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

		jsc = new JButton("为该课程打分");
		jsc.addActionListener(new Listener3());

		jstat = new JButton("查看成绩统计信息");
		jstat.addActionListener(new Listener2());

		jb = new JButton("刷新");
		jb.addActionListener(new Listener1());
		jp1 = new JPanel();
		jp1.setLayout(new BoxLayout(jp1, BoxLayout.X_AXIS));
		jp1.add(Box.createHorizontalStrut(10));
		jp1.add(jp3);
		jp1.add(Box.createVerticalGlue());
		jp1.add(jb);
		jp1.add(Box.createVerticalStrut(10));
		jp1.add(jsc);
//		jp1.add(Box.createVerticalStrut(10));
		jp1.add(jstat);




		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// this.add(jp1);
		this.add(Box.createVerticalStrut(10));
		this.add(jp2);
		this.add(Box.createVerticalStrut(10));
		this.add(jp1);
		this.add(Box.createVerticalStrut(10));
		this.setVisible(true);

		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
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

	void setTable() {
		// 显示成绩列表
		ArrayList<SelectionRecordVO> scores = studentController
				.showMyScores(studentController.getcurrentStudent().getID());
		String[][] scoreData = new String[scores.size()][5];
		String[] columnName = { "课程号", "课程名", "学分", "成绩","排名" };
		int GPA=0;
		int allCredits=0;
		for (int i = 0; i < scores.size(); i++) {
			CourseVO courseVO = studentController.showCourseInfo(scores.get(i)
					.getCourseID());
			int currentscore=scores.get(i).getScore();
			int currentcredit= courseVO.getCredit();
			scoreData[i][0] = courseVO.getID();
			scoreData[i][1] = courseVO.getName();
			scoreData[i][2] = "" +currentcredit;
			scoreData[i][3] = "" + currentscore;
			//排名
			ArrayList<SelectionRecordVO> selectionRecordVOs = studentController.showCourseScores(courseVO.getID());
			int rank=1;
			for (SelectionRecordVO srvo:selectionRecordVOs){
				if (srvo.getScore()<currentscore)
					rank++;
			}
			scoreData[i][4] = ""+rank+"/"+selectionRecordVOs.size();
			
			//GPA均分计算
			GPA+=currentscore*courseVO.getCredit();
			allCredits+=currentcredit;
		}
		GPA=GPA/allCredits;
		// 下面就是将这些数据显示在列表上就可以了
		table = new JTable(scoreData, columnName) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		scrollPane = new JScrollPane(table);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		table.setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		// 学分统计信息
		ArrayList<Integer> credits = studentController
				.showMyCredits(studentController.getcurrentStudent().getID());
		JLabel lb1 = new JLabel("专业课：                " + credits.get(0));
		JLabel lb2 = new JLabel("跨专业选修课：    " + credits.get(1));
		JLabel lb3 = new JLabel("14学分课程：       " + credits.get(2));
		JLabel lb4 = new JLabel("公共必修课：        " + credits.get(3));
		JLabel lb5 = new JLabel("学分绩：		" + GPA);

		jp3 = new JPanel();
		jp3.setBorder(new TitledBorder("学分统计信息"));
		jp3.setPreferredSize(new Dimension(800, 160));

		jp3.setLayout(new BoxLayout(jp3, BoxLayout.Y_AXIS));
		jp3.add(lb1);
		jp3.add(lb2);
		jp3.add(lb3);
		jp3.add(lb4);
		jp3.add(lb5);
		jp3.repaint();
	}


	// 刷新
	class Listener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			jp2.removeAll();
			setTable();
			jp2.setLayout(new BorderLayout());
			jp2.add(scrollPane, BorderLayout.CENTER);
			jp2.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		}

	}

	// 统计信息
	class Listener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			JFreeChart chart =ChartFactory.createBarChart3D("成绩统计", "成绩", "数量",
					getDataset(), PlotOrientation.VERTICAL, false, false, false);
			chart.setBackgroundPaint(Color.white);
			Font font = new Font("微软雅黑", 5, 15);// 设置字体，否则会显示乱码
			TextTitle title = chart.getTitle();
			title.setFont(font);			// 设置标题字体
			CategoryPlot plot = (CategoryPlot) chart.getPlot();			// 得到一个参考
			plot.setBackgroundPaint(Color.white);		// 生成图片的背景色
			plot.setRangeGridlinePaint(Color.BLACK);			// 行线的颜色
			plot.getDomainAxis().setTickLabelFont(font);			// 刻度字体
			plot.getDomainAxis().setLabelFont(font);			// X轴名称字体
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();		// 设置显示整数
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setUpperMargin(0.15);// 设置上部空白
			rangeAxis.setLabelFont(font);// 设置y轴名称字体
//			chart.getLegend().setItemFont(font);
			ChartFrame chartFrame = new ChartFrame("成绩统计", chart);
			// chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
			chartFrame.pack(); // 以合适的大小展现图形
			chartFrame.setVisible(true);// 图形是否可见
			chartFrame.setLocationRelativeTo(null);

		}

		public CategoryDataset getDataset() {
			
			ArrayList<SelectionRecordVO> scores = studentController
					.showMyScores(studentController.getcurrentStudent().getID());
			int[] numbers = new int[5];
			for(int i=0;i<numbers.length;i++){
				numbers[i]=0;
			}
			for (SelectionRecordVO srvo:scores) {
				if (srvo.getScore()<60){
					numbers[0]++;
				}else if(srvo.getScore()<70){
					numbers[1]++;
				}else if(srvo.getScore()<80){
					numbers[2]++;
				}else if(srvo.getScore()<90){
					numbers[3]++;
				}else if(srvo.getScore()<=100){
					numbers[4]++;
				}
			}
			String series1 = "主修";
//			String series2 = "选修";

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

			//http://www.open-open.com/lib/view/open1365997415828.html参考


			return dataset;
		}

	}
	class Listener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "请选择一门课");
			} else {
				String courseid = (String) table.getValueAt(table.getSelectedRow(), 0);
				String resultString=JOptionPane.showInputDialog("请为《"+table.getValueAt(table.getSelectedRow(), 1)+"》课程打分：");
				int score=Integer.parseInt(resultString);
				if(score<=5&&score>=0){
					studentController.assessCourse(courseid,score);
				}else{
					System.out.println("打分无效");
				}
				
			}
		}
	}

}
