//�鿴��Ӧ�б�
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
		jlb1 = new JLabel("ѡ���б����ͣ�");
		jlb2 = new JLabel("ѡ��Ժϵ����:");
		jlb3 = new JLabel("ѡ���꼶:");
		String[] lists = { "�γ�", "��ʦ", "ѧ��" };
		jcb1 = new JComboBox<String>(lists);
		jcb1.addActionListener(new Listener0());
		String[] departments = { "���ѧԺ", "��ѧԺ", "��ѧԺ", "�ִ�����ѧԺ", "��ѧϵ", "����ϵ",
				"����ϵ", "��ѧϵ", "������" };
		jcb2 = new JComboBox<String>(departments);
		String[] grade = { "��һ", "���", "����", "����", "��һ", "�ж�", "��һ", "����" };
		jcb3 = new JComboBox<String>(grade);
		jb1 = new JButton("�鿴");
		jb1.addActionListener(new Listener1());

		jp1 = new JPanel();
		jp1.setLayout(new BoxLayout(jp1, BoxLayout.X_AXIS));
		jp1.add(jlb1);
		jp1.add(jcb1);
		jp1.add(Box.createHorizontalStrut(5));
		jp1.add(jlb2);
		jp1.add(jcb2);
		jp1.add(Box.createHorizontalStrut(5));

		jstat1 = new JButton("�ɼ�ͳ��");
		jstat1.addActionListener(new Listener3());
		jstat2 = new JButton("����ͳ��");
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
		jp3.setBorder(new TitledBorder("��ϸ��Ϣ"));
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

	// ��JComboBox������
	class Listener0 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!jcb1.getSelectedItem().equals("�γ�")) {
				jstat1.setVisible(false);
				jstat2.setVisible(false);
			} else {
				jstat1.setVisible(true);
				jstat2.setVisible(true);
			}
		}

	}

	// �б���Ϣ
	class Listener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] row1 = {};
			String[][] data1 = {};
			String type = (String) jcb1.getSelectedItem();
			String department = (String) jcb2.getSelectedItem();
			if (type.equals("�γ�")) {
				ArrayList<CourseVO> courseVOs = jTeacherController
						.showCourseList(department);
				row1 = new String[] { "�γ̺�", "�γ���", "�γ�����", "ʱ��", "�ص�", "ѧ��", };
				data1 = new String[courseVOs.size()][9];
				for (int i = 0; i < courseVOs.size(); i++) {
					data1[i][0] = courseVOs.get(i).getID();
					data1[i][1] = courseVOs.get(i).getName();
					data1[i][2] = courseVOs.get(i).getCourseType();
					data1[i][3] = courseVOs.get(i).getTime();
					data1[i][4] = courseVOs.get(i).getClassroom();
					data1[i][5] = courseVOs.get(i).getCredit() + "";
				}
			} else if (type.equals("��ʦ")) {
				ArrayList<TeacherVO> teacherVOs = jTeacherController
						.showTeacherList(department);
				row1 = new String[] { "����", "����", "����" };
				data1 = new String[teacherVOs.size()][9];
				for (int i = 0; i < teacherVOs.size(); i++) {
					data1[i][0] = teacherVOs.get(i).getID();
					data1[i][1] = teacherVOs.get(i).getName();
					data1[i][2] = teacherVOs.get(i).getTeacherType();
				}
			} else if (type.equals("ѧ��")) {
				ArrayList<StudentVO> studentVOs = jTeacherController
						.showStudentList(department);
				row1 = new String[] { "ѧ��", "����", "�꼶" };
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
				JOptionPane.showMessageDialog(null, "==��Ϣ��ȱ==");
			}
		}

	}

	// ��ϸ��Ϣ
	class Listener2 implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			String type = (String) jcb1.getSelectedItem();
			String id = (String) table1.getValueAt(table1.getSelectedRow(), 0);
			String info = null;
			if (type.equals("�γ�")) {
				CourseVO VO = jTeacherController.showCourseInfo(id);
				String l1 = "�γ̺ţ�" + VO.getID() + "\t" + "�γ�����" + VO.getName();
				String l2 = "ѧ�֣�" + VO.getCredit() + "\t" + "\t" + "�γ�����:"
						+ VO.getCourseType();
				String l3 = "�Ͽ�ʱ�䣺" + "\t" + "\t" + test(VO.getTime());
				String l4 = "�Ͽεص㣺" + "\t" + "\t" + test(VO.getClassroom());
				String s1 = "�γ̴�٣�" + "\t" + "\t" + test(VO.getOutline());
				String s2 = "�ο���Ŀ��" + "\t" + "\t" + test(VO.getReferenceBook());
				String s3 = "�̿���Ŀ��" + "\t" + "\t" + test(VO.getTextbook());
				info = l1 + "\n" + l2 + "\n" + l3 + "\n" + l4 + "\n" + s1
						+ "\n" + s2 + "\n" + s3;
			} else if (type.equals("��ʦ")) {
				TeacherVO VO = jTeacherController.showTeacherInfo(id);
				String l1 = "����" + "\t" + VO.getID();
				String l2 = "����" + "\t" + VO.getName();
				String l3 = "���" + "\t" + VO.getTeacherType();
				String l4 = "��λ" + "\t" + VO.getDepartment();
				String s1 = "����" + "\t" + test(VO.getAge());
				String s2 = "����" + "\t" + test(VO.getHometown());
				String s3 = "��ϵ��ʽ" + "\t" + test(VO.getContact());
				info = l1 + "\n" + l2 + "\n" + l3 + "\n" + l4 + "\n" + s1
						+ "\n" + s2 + "\n" + s3;
			} else if (type.equals("ѧ��")) {
				StudentVO VO = jTeacherController.showStudentInfo(id);
				String l1 = "ѧ��" + "\t" + VO.getID();
				String l2 = "����" + "\t" + VO.getName();
				String l3 = "�꼶" + "\t" + VO.getGrade();
				String l4 = "Ժϵ" + "\t" + VO.getDepartment();
				String s1 = "����" + "\t" + test(VO.getAge());
				String s2 = "����" + "\t" + test(VO.getHometown());
				String s3 = "��ϵ��ʽ" + "\t" + test(VO.getContact());
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
				return "==��Ϣ��ȱ==";
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

	// ѧ��ѧ��ͳ����Ϣ
	class Listener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			CategoryDataset dataset = getDataset();

			JFreeChart chart = ChartFactory.createStackedBarChart("Ժϵ�γ̳ɼ�ͳ��",
					"�γ̾��ַ�����", "�γ�����", dataset, PlotOrientation.VERTICAL, false,
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
	
			CategoryAxis categoryaxis = plot.getDomainAxis();
			categoryaxis
					.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);

			BarRenderer renderer = (BarRenderer) plot
					.getRenderer();
			renderer.setDrawBarOutline(true);
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);

			ChartFrame chartFrame = new ChartFrame("Ժϵ���пγ̳ɼ�ͳ��", chart);
			// chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣
			chartFrame.pack(); // �Ժ��ʵĴ�Сչ��ͼ��
			chartFrame.setVisible(true);// ͼ���Ƿ�ɼ�
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
				//��һ�ſε�ƽ���ɼ����ൽ5���е�һ��
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
			// ��
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

	// ѧ��ѧ��ͳ����Ϣ
	class Listener4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			DefaultPieDataset dataset = getDataset();

			JFreeChart chart = ChartFactory.createPieChart("�γ�����ͳ��", dataset, true, true, false);

			chart.setBackgroundPaint(Color.white);
			Font font = new Font("΢���ź�", 5, 15);// �������壬�������ʾ����
			TextTitle title = chart.getTitle();
			title.setFont(font); // ���ñ�������
			PiePlot pieplot = (PiePlot) chart.getPlot(); // �õ�һ���ο�
			pieplot.setBackgroundPaint(Color.white); // ����ͼƬ�ı���ɫ
			// û�����ݵ�ʱ����ʾ������  
	        pieplot.setNoDataMessage("No data available");   
	        // ����������ʱ����Ϣ��ʾ��ɫ  
	        pieplot.setNoDataMessagePaint(Color.red);  
	        //��Lable Ϊ��Two�� ����һ�顱�ڡ�����10%  
	        pieplot.setExplodePercent("Two", 0.3D);  
	        //���ñ���͸����  
	        pieplot.setBackgroundAlpha(0.9f);  
	        //����ǰ��͸����  
	        pieplot.setForegroundAlpha(0.6f);  
	        // ָ����ͼ�����ߵ���ɫ  
	        pieplot.setBaseSectionOutlinePaint(Color.white);  
	        pieplot.setBaseSectionPaint(Color.BLACK);  
	        // ָ����ʾ�ı�ͼ��Բ��(true)����Բ��(false)  
	        pieplot.setCircular(true);  
	        //��������������ʾ  
	        pieplot.setExplodePercent("ͨʶ����", 0.2D);  
	        //���������߿򲻿ɼ�  
	        pieplot.setSectionOutlinesVisible(false);  
	        
	        //����������ǩ��ʾ��ʽ���ؼ��֣�ֵ(�ٷֱ�)  
	        pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(  
	                "{0}��{1}({2} percent)"));  
	        //����������ǩ��ɫ  
	        pieplot.setLabelBackgroundPaint(new Color(220, 220, 220));  
	        
	        pieplot.setLabelFont((new Font("����", Font.PLAIN, 12)));  
	        pieplot.setLabelFont(font);  
	        chart.getLegend().setItemFont(font);
			ChartFrame chartFrame = new ChartFrame("ѧ��ѧ��ͳ��", chart);
			// chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣
			chartFrame.pack(); // �Ժ��ʵĴ�Сչ��ͼ��
			chartFrame.setVisible(true);// ͼ���Ƿ�ɼ�
			chartFrame.setLocationRelativeTo(null);

		}

		public DefaultPieDataset getDataset() {
			DefaultPieDataset result=new DefaultPieDataset();
			int[] numbers = new int[4];
			for(int i=0;i<numbers.length;i++){
				numbers[i]=0;
			}
			for (int i = 0; i < table1.getRowCount(); i++) {
				if(table1.getValueAt(i, 2).equals("רҵָѡ��")){
					numbers[0]++;
				}else if(table1.getValueAt(i, 2).equals("רҵѡ�޿�")){
					numbers[1]++;
				}else if(table1.getValueAt(i, 2).equals("ͨʶ��")||table1.getValueAt(i, 2).equals("���ֿ�")){
					numbers[2]++;
				}else if(table1.getValueAt(i, 2).equals("�������޿�")||table1.getValueAt(i, 2).equals("������")){
					numbers[3]++;
				}
				
			}
			result.setValue("רҵָѡ", numbers[0]);
			result.setValue("רҵѡ��", numbers[1]);
			result.setValue("ͨʶ����", numbers[2]);
			result.setValue("��������", numbers[3]);
			return result;
		}

	}
}
