//�鿴ѧ�ڳɼ��Լ�ѧ��ͳ��
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
		// jlb1=new JLabel("��ѡ��ѧ�ڣ�");
		// String[] grades={"��һ��","��һ��","�����","�����","������","������","������","������",};
		// jcb=new JComboBox<String>(grades);
		// jb1=new JButton("�鿴��ϸ��Ϣ");
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

		String[] columnName = { "�γ̺�", "�γ���", "ѧ��", "�ɼ�", "����" };
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

		jsc = new JButton("Ϊ�ÿγ̴��");
		jsc.addActionListener(new Listener3());

		jstat = new JButton("�鿴�ɼ�ͳ����Ϣ");
		jstat.addActionListener(new Listener2());

		jb = new JButton("ˢ��");
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
		// ��ʾ�ɼ��б�
		ArrayList<SelectionRecordVO> scores = studentController
				.showMyScores(studentController.getcurrentStudent().getID());
		String[][] scoreData = new String[scores.size()][5];
		String[] columnName = { "�γ̺�", "�γ���", "ѧ��", "�ɼ�","����" };
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
			//����
			ArrayList<SelectionRecordVO> selectionRecordVOs = studentController.showCourseScores(courseVO.getID());
			int rank=1;
			for (SelectionRecordVO srvo:selectionRecordVOs){
				if (srvo.getScore()<currentscore)
					rank++;
			}
			scoreData[i][4] = ""+rank+"/"+selectionRecordVOs.size();
			
			//GPA���ּ���
			GPA+=currentscore*courseVO.getCredit();
			allCredits+=currentcredit;
		}
		GPA=GPA/allCredits;
		// ������ǽ���Щ������ʾ���б��ϾͿ�����
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

		// ѧ��ͳ����Ϣ
		ArrayList<Integer> credits = studentController
				.showMyCredits(studentController.getcurrentStudent().getID());
		JLabel lb1 = new JLabel("רҵ�Σ�                " + credits.get(0));
		JLabel lb2 = new JLabel("��רҵѡ�޿Σ�    " + credits.get(1));
		JLabel lb3 = new JLabel("14ѧ�ֿγ̣�       " + credits.get(2));
		JLabel lb4 = new JLabel("�������޿Σ�        " + credits.get(3));
		JLabel lb5 = new JLabel("ѧ�ּ���		" + GPA);

		jp3 = new JPanel();
		jp3.setBorder(new TitledBorder("ѧ��ͳ����Ϣ"));
		jp3.setPreferredSize(new Dimension(800, 160));

		jp3.setLayout(new BoxLayout(jp3, BoxLayout.Y_AXIS));
		jp3.add(lb1);
		jp3.add(lb2);
		jp3.add(lb3);
		jp3.add(lb4);
		jp3.add(lb5);
		jp3.repaint();
	}


	// ˢ��
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

	// ͳ����Ϣ
	class Listener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			JFreeChart chart =ChartFactory.createBarChart3D("�ɼ�ͳ��", "�ɼ�", "����",
					getDataset(), PlotOrientation.VERTICAL, false, false, false);
			chart.setBackgroundPaint(Color.white);
			Font font = new Font("΢���ź�", 5, 15);// �������壬�������ʾ����
			TextTitle title = chart.getTitle();
			title.setFont(font);			// ���ñ�������
			CategoryPlot plot = (CategoryPlot) chart.getPlot();			// �õ�һ���ο�
			plot.setBackgroundPaint(Color.white);		// ����ͼƬ�ı���ɫ
			plot.setRangeGridlinePaint(Color.BLACK);			// ���ߵ���ɫ
			plot.getDomainAxis().setTickLabelFont(font);			// �̶�����
			plot.getDomainAxis().setLabelFont(font);			// X����������
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();		// ������ʾ����
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setUpperMargin(0.15);// �����ϲ��հ�
			rangeAxis.setLabelFont(font);// ����y����������
//			chart.getLegend().setItemFont(font);
			ChartFrame chartFrame = new ChartFrame("�ɼ�ͳ��", chart);
			// chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣
			chartFrame.pack(); // �Ժ��ʵĴ�Сչ��ͼ��
			chartFrame.setVisible(true);// ͼ���Ƿ�ɼ�
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
			String series1 = "����";
//			String series2 = "ѡ��";

			// ��
			String category1 = "<60";
			String category2 = "60~69";
			String category3 = "70~79";
			String category4 = "80~89";
			String category5 = "90~100";

			// ��������Դ
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			// ��������

			dataset.addValue(numbers[0], series1, category1);
			dataset.addValue(numbers[1], series1, category2);
			dataset.addValue(numbers[2], series1, category3);
			dataset.addValue(numbers[3], series1, category4);
			dataset.addValue(numbers[4], series1, category5);

			//http://www.open-open.com/lib/view/open1365997415828.html�ο�


			return dataset;
		}

	}
	class Listener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "��ѡ��һ�ſ�");
			} else {
				String courseid = (String) table.getValueAt(table.getSelectedRow(), 0);
				String resultString=JOptionPane.showInputDialog("��Ϊ��"+table.getValueAt(table.getSelectedRow(), 1)+"���γ̴�֣�");
				int score=Integer.parseInt(resultString);
				if(score<=5&&score>=0){
					studentController.assessCourse(courseid,score);
				}else{
					System.out.println("�����Ч");
				}
				
			}
		}
	}

}
