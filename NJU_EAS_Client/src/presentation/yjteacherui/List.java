//�鿴�б�
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
	JButton jstatb1, jstatb2, jstatb3, jstatb4, jstatb5;// ͳ����Ϣ

	public List(yjTeacherController yjTeacherController) {
		this.yjTeacherController = yjTeacherController;
		jlb1 = new JLabel("ѡ���б�");
		jlb2 = new JLabel("ѡ���꼶:");
		jlb3 = new JLabel("ѡ���ʦ���ͣ�");
		String[] lists = { "�γ�", "��ʦ", "ѧ��" };
		jcb1 = new JComboBox<String>(lists);
		jcb1.addActionListener(new Listener0());
		String[] grade = { "��", "����һ�꼶", "���ƶ��꼶", "�������꼶", "�������꼶" };
		jcb2 = new JComboBox<String>(grade);
		String[] type = { "�ο���ʦ", "Ժϵ��ʦ" };
		jcb3 = new JComboBox<String>(type);
		jb1 = new JButton("�鿴");
		jb1.addActionListener(new Listener1());
		jb2 = new JButton("�鿴��ϸ��Ϣ");
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

		jstatb1 = new JButton("�γ̾��ּ�������");
		jstatb1.addActionListener(new Listener2());
		jstatb2 = new JButton("�γ�ѡ�ηֲ�");
		jstatb2.addActionListener(new Listener3());
		jstatb3 = new JButton("ѧ��ѧ�ֲַ�");
		jstatb3.addActionListener(new Listener5());
		jstatb4 = new JButton("��ʦ�ڿο�ʱ�ֲ�");
		jstatb4.addActionListener(new Listener4());
		jstatb5 = new JButton("�γ�����ͳ��");
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

	// ��JComboBox����������ʦѡ�����ʦ���ͣ�
	class Listener0 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (jcb1.getSelectedItem().equals("��ʦ")) {
				jlb2.setVisible(false);
				jcb2.setVisible(false);

				jlb3.setVisible(true);
				jcb3.setVisible(true);
				// ����Ϊjp1ͷ����ʵʱ�л�������Ϊjp3β����ť��ʵʱ�л�
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
				if (jcb1.getSelectedItem().equals("�γ�")) {

					jstatb1.setVisible(true);
					jstatb2.setVisible(true);
					jstatb5.setVisible(true);
					jstatb3.setVisible(false);
					jstatb4.setVisible(false);
				} else if (jcb1.getSelectedItem().equals("ѧ��")) {
					jstatb3.setVisible(true);
					jstatb1.setVisible(false);
					jstatb2.setVisible(false);
					jstatb4.setVisible(false);
					jstatb5.setVisible(false);
				}
			}
		}

	}

	// ��ʾ�б�
	class Listener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String type = (String) jcb1.getSelectedItem();
			String grade = (String) jcb2.getSelectedItem();
			String teachertype = (String) jcb3.getSelectedItem();
			if (type.equals("�γ�")) {
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
				String[] columnName = { "�γ̺�", "�γ���", "�γ�����", "��ʦ", "ʱ��", "�ص�" };
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

			} else if (type.equals("ѧ��")) {
				// ͨ���꼶��Ժϵɸѡ������ֻ��Ҫ�������ԣ�id��name
				ArrayList<StudentVO> studentVOs = yjTeacherController
						.showStudentList(grade, yjTeacherController
								.getcurrentyjTeacherVO().getDepartment());
				String[][] studentData = new String[studentVOs.size()][2];
				for (int i = 0; i < studentVOs.size(); i++) {
					studentData[i][0] = studentVOs.get(i).getID();
					studentData[i][1] = studentVOs.get(i).getName();
				}
				String[] columnName = { "ѧ��ѧ��", "ѧ������" };
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

			} else if (type.equals("��ʦ")) {
				// ��Ҫ��jcb3����һ�¡�
				// ��Ҫ��teachertype�ĳ�����
				if (teachertype.equals("�ο���ʦ")) {
					teachertype = "�ο���ʦ";
				} else if (teachertype.equals("Ժϵ��ʦ")) {
					teachertype = "Ժϵ������ʦ";
				} else if (teachertype.equals("������ʦ")) {
					teachertype = "������ʦ";
				}

				ArrayList<TeacherVO> teacherVOs = yjTeacherController
						.showTeacherList(teachertype, yjTeacherController
								.getcurrentyjTeacherVO().getDepartment());
				String[][] teacherData = new String[teacherVOs.size()][2];
				for (int i = 0; i < teacherVOs.size(); i++) {
					teacherData[i][0] = teacherVOs.get(i).getID();
					teacherData[i][1] = teacherVOs.get(i).getName();
				}
				String[] columnName = { "��ʦ����", "��ʦ����" };
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
				JOptionPane.showMessageDialog(null, "==��Ϣ��ȱ==");
			}
		}

	}

	// �γ̳ɼ����ֺͼ�����ͳ����Ϣ
	class Listener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<CategoryDataset> datasets = getDataset();

			JFreeChart chart = ChartFactory.createBarChart("�ɼ�ͳ��", "�γ�����",
					"����", datasets.get(0), PlotOrientation.VERTICAL, false,
					false, false);

			CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();// ͼ����
			categoryplot.setDataset(1, datasets.get(1));// ������ͼ����

			categoryplot.mapDatasetToRangeAxis(1, 1);
			CategoryAxis categoryaxis = categoryplot.getDomainAxis();
			categoryaxis
					.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
			NumberAxis numberaxis = new NumberAxis("������");
			categoryplot.setRangeAxis(1, numberaxis);

			LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
			lineandshaperenderer
					.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
			categoryplot.setRenderer(1, lineandshaperenderer);
			categoryplot
					.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);// ����������ǰ����ʾ

			chart.setBackgroundPaint(Color.white);
			Font font = new Font("΢���ź�", 5, 15);// �������壬�������ʾ����
			TextTitle title = chart.getTitle();
			title.setFont(font); // ���ñ�������
			CategoryPlot plot = (CategoryPlot) chart.getPlot(); // �õ�һ���ο�
			plot.setBackgroundPaint(Color.white); // ����ͼƬ�ı���ɫ
			plot.setRangeGridlinePaint(Color.BLACK); // ���ߵ���ɫ
			plot.getDomainAxis().setTickLabelFont(font); // �̶�����
			plot.getDomainAxis().setLabelFont(font); // X����������
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis(); // ������ʾ����
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setUpperMargin(0.15);// �����ϲ��հ�
			rangeAxis.setLabelFont(font);// ����y����������
			// chart.getLegend().setItemFont(font);
			ChartFrame chartFrame = new ChartFrame("�ɼ�ͳ��", chart);
			// chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣
			chartFrame.pack(); // �Ժ��ʵĴ�Сչ��ͼ��
			chartFrame.setVisible(true);// ͼ���Ƿ�ɼ�
			chartFrame.setLocationRelativeTo(null);

		}

		public ArrayList<CategoryDataset> getDataset() {
			ArrayList<CategoryDataset> result = new ArrayList<CategoryDataset>();
			DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
			// TableModel tm = table.getModel();
			// ���б��е�ÿ�ſν���ƽ���ֺͼ�����ͳ��
			for (int i = 0; i < table.getRowCount(); i++) {
				String courseid = table.getValueAt(i, 0).toString();
				ArrayList<SelectionRecordVO> scores = yjTeacherController
						.showCourseScores(courseid);

				int averagescore = 0;
				int fail = 0;// ����������
				int passrate = 0;// ������
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

	// �γ�ѡ������ͳ����Ϣ
	class Listener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CategoryDataset dataset = getDataset();

			JFreeChart chart = ChartFactory.createStackedBarChart("�γ�ѡ��ͳ��",
					"�γ̺�", "����", dataset, PlotOrientation.VERTICAL, true,
					false, false);

			chart.setBackgroundPaint(Color.white);
			Font font = new Font("΢���ź�", 5, 15);// �������壬�������ʾ����
			TextTitle title = chart.getTitle();
			title.setFont(font); // ���ñ�������
			CategoryPlot plot = (CategoryPlot) chart.getPlot(); // �õ�һ���ο�
			plot.setBackgroundPaint(Color.white); // ����ͼƬ�ı���ɫ
			plot.setRangeGridlinePaint(Color.BLACK); // ���ߵ���ɫ
			plot.getDomainAxis().setTickLabelFont(font); // �̶�����
			plot.getDomainAxis().setLabelFont(font); // X����������
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis(); // ������ʾ����
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setUpperMargin(0.15);// �����ϲ��հ�
			rangeAxis.setLabelFont(font);// ����y����������
			chart.getLegend().setItemFont(font);

			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis
					.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);

			StackedBarRenderer renderer = (StackedBarRenderer) plot
					.getRenderer();
			renderer.setDrawBarOutline(true);
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);

			ChartFrame chartFrame = new ChartFrame("�γ�ѡ��ͳ��", chart);
			// chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣
			chartFrame.pack(); // �Ժ��ʵĴ�Сչ��ͼ��
			chartFrame.setVisible(true);// ͼ���Ƿ�ɼ�
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
				int owndepart = 0;// ��Ժϵѡ������
				int otherdepart = 0;// ��Ժϵ
				for (StudentVO svo : students) {
					if (svo.getDepartment().equals(currentDepartment)) {
						owndepart++;
					} else {
						otherdepart++;
					}
				}
				result.addValue(owndepart, "��Ժϵѧ��", courseid);
				result.addValue(otherdepart, "����Ժϵѧ��", courseid);
			}

			return result;
		}

	}

	// ��ʦ�ڿο�ʱͳ����Ϣ
	class Listener4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CategoryDataset dataset = getDataset();

			JFreeChart chart = ChartFactory.createBarChart("��ʦ�ڿ�ͳ��",
					"��ʦ����", "�ܿ�ʱ", dataset, PlotOrientation.VERTICAL, false,
					false, false);

			chart.setBackgroundPaint(Color.white);
			Font font = new Font("΢���ź�", 5, 15);// �������壬�������ʾ����
			TextTitle title = chart.getTitle();
			title.setFont(font); // ���ñ�������
			CategoryPlot plot = (CategoryPlot) chart.getPlot(); // �õ�һ���ο�
			plot.setBackgroundPaint(Color.white); // ����ͼƬ�ı���ɫ
			plot.setRangeGridlinePaint(Color.BLACK); // ���ߵ���ɫ
			plot.getDomainAxis().setTickLabelFont(font); // �̶�����
			plot.getDomainAxis().setLabelFont(font); // X����������
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis(); // ������ʾ����
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setUpperMargin(0.15);// �����ϲ��հ�
			rangeAxis.setLabelFont(font);// ����y����������
//			chart.getLegend().setItemFont(font);

			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis
					.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);

	

			ChartFrame chartFrame = new ChartFrame("��ʦ�ڿ�ͳ��", chart);
			// chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣
			chartFrame.pack(); // �Ժ��ʵĴ�Сչ��ͼ��
			chartFrame.setVisible(true);// ͼ���Ƿ�ɼ�
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

	// ѧ��ѧ��ͳ����Ϣ
	class Listener5 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CategoryDataset dataset = getDataset();

			JFreeChart chart = ChartFactory.createStackedBarChart("ѧ��ѧ��ͳ��",
					"ѧ��", "ѧ����", dataset, PlotOrientation.VERTICAL, true,
					false, false);

			chart.setBackgroundPaint(Color.white);
			Font font = new Font("΢���ź�", 5, 15);// �������壬�������ʾ����
			TextTitle title = chart.getTitle();
			title.setFont(font); // ���ñ�������
			CategoryPlot plot = (CategoryPlot) chart.getPlot(); // �õ�һ���ο�
			plot.setBackgroundPaint(Color.white); // ����ͼƬ�ı���ɫ
			plot.setRangeGridlinePaint(Color.BLACK); // ���ߵ���ɫ
			plot.getDomainAxis().setTickLabelFont(font); // �̶�����
			plot.getDomainAxis().setLabelFont(font); // X����������
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis(); // ������ʾ����
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setUpperMargin(0.15);// �����ϲ��հ�
			rangeAxis.setLabelFont(font);// ����y����������
			chart.getLegend().setItemFont(font);

			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis
					.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);

			StackedBarRenderer renderer = (StackedBarRenderer) plot
					.getRenderer();
			renderer.setDrawBarOutline(true);
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);

			ChartFrame chartFrame = new ChartFrame("ѧ��ѧ��ͳ��", chart);
			// chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣
			chartFrame.pack(); // �Ժ��ʵĴ�Сչ��ͼ��
			chartFrame.setVisible(true);// ͼ���Ƿ�ɼ�
			chartFrame.setLocationRelativeTo(null);

		}

		public CategoryDataset getDataset() {

			DefaultCategoryDataset result = new DefaultCategoryDataset();
			for (int i = 0; i < table.getRowCount(); i++) {
				String studentid = table.getValueAt(i, 0).toString();
				ArrayList<Integer> credits= yjTeacherController.showStudentCredits(studentid);
				result.addValue(credits.get(0)+credits.get(3), "����", studentid);
				result.addValue(credits.get(1), "ѡ��", studentid);
				result.addValue(credits.get(2), "14ѧ��", studentid);
			}

			return result;
		}

	}
	// �γ̳ɼ����ֺͼ�����ͳ����Ϣ
	class Listener6 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CategoryDataset dataset = getDataset();

			JFreeChart chart = ChartFactory.createBarChart("�γ�����ͳ��", "�γ̺�",
					"����", dataset, PlotOrientation.VERTICAL, false,
					false, false);

			CategoryPlot plot = (CategoryPlot) chart.getPlot();// ͼ����
			
			BarRenderer renderer = (BarRenderer) plot
					.getRenderer();
			renderer.setDrawBarOutline(true);
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);			
			
			chart.setBackgroundPaint(Color.white);
			Font font = new Font("΢���ź�", 5, 15);// �������壬�������ʾ����
			TextTitle title = chart.getTitle();
			title.setFont(font); // ���ñ�������

			plot.setBackgroundPaint(Color.white); // ����ͼƬ�ı���ɫ
			plot.setRangeGridlinePaint(Color.BLACK); // ���ߵ���ɫ
			plot.getDomainAxis().setTickLabelFont(font); // �̶�����
			plot.getDomainAxis().setLabelFont(font); // X����������
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis(); // ������ʾ����
//			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setUpperMargin(0.15);// �����ϲ��հ�
			rangeAxis.setLabelFont(font);// ����y����������
			
			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis
					.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
			
			// chart.getLegend().setItemFont(font);
			ChartFrame chartFrame = new ChartFrame("�ɼ�ͳ��", chart);
			// chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣
			chartFrame.pack(); // �Ժ��ʵĴ�Сչ��ͼ��
			chartFrame.setVisible(true);// ͼ���Ƿ�ɼ�
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
