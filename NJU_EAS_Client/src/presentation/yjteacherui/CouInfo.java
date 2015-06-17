//Ժϵ������ʦ���пγ���صĲ����������γ̡��鿴�γ̡����ƿγ������Ϣ
package presentation.yjteacherui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import presentation.mytools.myButton;
import vo.CourseVO;
import vo.TeacherVO;
import businesslogiccontroller.yjteachercontroller.yjTeacherController;

@SuppressWarnings("serial")
public class CouInfo extends JPanel {
	private yjTeacherController yjTeacherController;
	// �ϲ������γ�
	JPanel jp1;
	JPanel jp1_1, jp1_2;
	JLabel jlb1, jlb2, jlb3, jlb4, jlb6;
	JTextField jtf1;
	JComboBox<String> jcb1, jcb2, jcb3, jcb4;
	JButton jb;
	// �в��б���ʾ
	JLabel jlb5;
	JScrollPane scrollPane1;
	JTable table1;
	// �²���ť����
	JPanel jp2;
	JLabel jlb;
	JComboBox<String> jcb;
	JButton jb1, jb2, jb3, jb4;
	JPanel jp2_2;
	// ���ذ��ſγ�
	JButton right;
	JPanel improve;
	JPanel combine;

	String status;
	
	public CouInfo(yjTeacherController yjTeacherController) {
		this.yjTeacherController = yjTeacherController;
		// �ϲ������γ�
		jlb1 = new JLabel("�γ�����");
		jlb2 = new JLabel("ѧ�֣�");
		jlb3 = new JLabel("�γ����ͣ�");
		jlb4 = new JLabel("��    ����");
		jlb6 = new JLabel("Ŀ��Ժϵ��");
		jtf1 = new JTextField(10);
		jb = new JButton("ȷ�Ϸ���");
		jb.addActionListener(new Listener1());

		String[] credit = { "1", "2", "3", "4", "5" };
		String[] kind = { "רҵָѡ��", "רҵѡ�޿�", "ͨʶ��", "���ֿ�", "�������޿�", "������" };
		String[] grade = { "��","����һ�꼶   ", "���ƶ��꼶   ", "�������꼶   ", "�������꼶   " };
		String[] departments = {"��", "���ѧԺ", "��ѧԺ", "��ѧԺ", "�ִ�����ѧԺ", "��ѧϵ", "����ϵ",
				"����ϵ", "��ѧϵ" };
		jcb1 = new JComboBox<String>(credit);
		jcb2 = new JComboBox<String>(kind);
		jcb3 = new JComboBox<String>(grade);
		jcb4 = new JComboBox<String>(departments);

		jcb2.addActionListener(new typelistener());
//		jcb3.setEnabled(false);
		jcb4.setSelectedItem(yjTeacherController.getcurrentyjTeacherVO().getDepartment());
		jcb4.setEnabled(false);
		
		jp1 = new JPanel();
		jp1.setBorder(new TitledBorder("�γ���Ϣ¼��"));
		jp1_1 = new JPanel();
		jp1_1.setLayout(new BoxLayout(jp1_1, BoxLayout.X_AXIS));
		jp1_2 = new JPanel();
		jp1_2.setLayout(new BoxLayout(jp1_2, BoxLayout.X_AXIS));
		jp1_1.add(jlb1);
		jp1_1.add(jtf1);
		jp1_1.add(Box.createHorizontalStrut(120));
		jp1_1.add(jlb2);
		jp1_1.add(jcb1);
		jp1_1.add(Box.createHorizontalStrut(30));
		jp1_1.add(jlb3);
		jp1_1.add(jcb2);
		jp1_1.add(Box.createHorizontalStrut(10));
		jp1_2.add(jlb4);
		jp1_2.add(jcb3);
		jp1_2.add(Box.createHorizontalGlue());
		jp1_2.add(jlb6);
		jp1_2.add(jcb4);
		jp1_2.add(Box.createHorizontalStrut(10));
		jp1.setLayout(null);
		jp1_1.setBounds(10, 30, 600, 35);
		jp1_2.setBounds(10, 70, 600, 35);
		jb.setBounds(620, 70, 100, 35);
		jp1.add(jp1_1);
		jp1.add(jp1_2);
		jp1.add(jb);

		jlb5 = new JLabel("��Ժϵ�ѷ����Ŀγ�");
		// �²���ť����
		jlb = new JLabel("�꼶");
		jcb = new JComboBox<String>(grade);
		jb1 = new JButton("�鿴/ˢ�¿γ�");
		jb1.addActionListener(new Listener2());
		jb2 = new JButton("ɾ���γ�");
		jb2.addActionListener(new Listener3());
		jb3 = new JButton("���ſγ�");
		jb3.addActionListener(new Listener4());
		// jb4=new JButton("ɸѡδָ���γ�");
		jp2_2 = new JPanel();
		jp2_2.setLayout(new BoxLayout(jp2_2, BoxLayout.X_AXIS));
		jp2_2.add(jlb);
		jp2_2.add(jcb);
		jp2_2.add(jb1);
		jp2_2.add(Box.createHorizontalGlue());
		jp2_2.add(jb2);
		jp2_2.add(jb3);
		// jp2_2.add(jb4);
		jb2.setVisible(false);
		jb3.setVisible(false);
		// jb4.setVisible(false);
		// �в��б���ʾ
		String[] columnName = { "�γ̺�", "�γ���", "�γ�����", "��ʦ", "ʱ��", "�ص�" };
		String[][] rowData = {};
		table1 = new JTable(new DefaultTableModel(rowData, columnName));
		scrollPane1 = new JScrollPane(table1);
		scrollPane1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp2 = new JPanel();
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
		jp2.add(jlb5);
		jp2.add(scrollPane1);
		jp2.add(jp2_2);
		jp2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		jp2.setPreferredSize(new Dimension(800, 800));

		// ���ذ��ſγ�
		right = new myButton("Icons/right_1.png");
		right.setSelectedIcon(new ImageIcon("Icons/right_2.png"));
		right.addActionListener(new Right());
		improve = new improve();
		right.setBounds(0, 0, 50, 520);
		improve.setBounds(50, 0, 690, 520);
		combine = new JPanel(){
			public void paintComponent(Graphics g){
				Image im = null;
				try {
					im = ImageIO.read(new File("Images/back.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.paintComponent(g);
				g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
			}
		};
		combine.setLayout(null);
		combine.add(right);
		combine.add(improve);

		this.setLayout(null);
		jp1.setBounds(0, 10, 735, 120);
		jp2.setBounds(0, 130, 735, 360);
		combine.setBounds(745, 0, 740, 520);
		this.add(combine);
		this.add(jp1);
		this.add(jp2);
		this.setVisible(true);
		
		jp1.setOpaque(false);
		jp1_1.setOpaque(false);
		jp1_2.setOpaque(false);
		jp2.setOpaque(false);
		jp2_2.setOpaque(false);
		table1.setOpaque(false);
		scrollPane1.setOpaque(false);
		scrollPane1.getViewport().setOpaque(false);
				
		status=yjTeacherController.getSystemStatus();
		if(!status.equals("-1")){
//			jb.setEnabled(false);
			jp1.setVisible(false);
			jp2.setBounds(0,10,735,480);
		}
	}
	
	public void paintComponent(Graphics g){
		Image im = null;
		try {
			im = ImageIO.read(new File("Images/back.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
	}

	//�����γ�����
	class typelistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			//"רҵָѡ��", "רҵѡ�޿�", "ͨʶ��", "���ֿ�", "�������޿�", "������"
			if(jcb2.getSelectedIndex()==0){
				jcb3.setEnabled(true);
				jcb4.setSelectedItem(yjTeacherController.getcurrentyjTeacherVO().getDepartment());
				jcb4.setEnabled(false);
			}
			if(jcb2.getSelectedIndex()==1){
				jcb3.setEnabled(true);
				jcb4.setSelectedIndex(0);
				jcb4.setEnabled(false);
			}
			if(jcb2.getSelectedIndex()==2||jcb2.getSelectedIndex()==3||jcb2.getSelectedIndex()==5){
				jcb3.setSelectedIndex(0);
				jcb4.setSelectedIndex(0);
				jcb3.setEnabled(false);
				jcb4.setEnabled(false);
			}
			if(jcb2.getSelectedIndex()==4){
				jcb3.setEnabled(true);
				jcb4.setEnabled(true);
			}
		}
		
	}
	
	// �����γ�
	class Listener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean OK=true;
			String name = jtf1.getText();
			int credit = Integer.parseInt((String) jcb1.getSelectedItem());
			String coursetype = (String) jcb2.getSelectedItem();
			String grade = (String) jcb3.getSelectedItem();
			String targetdepartment = (String) jcb4.getSelectedItem();
			
			if(name.equals("")){
				JOptionPane.showMessageDialog(null, "������γ�����");
				OK=false;
			}
			else if((jcb2.getSelectedIndex()==0||jcb2.getSelectedIndex()==1||jcb2.getSelectedIndex()==4)&&jcb3.getSelectedIndex()==0){
				JOptionPane.showMessageDialog(null, "��ѡ���꼶��");
				OK=false;
			}
			else if(jcb2.getSelectedIndex()==4&&jcb4.getSelectedIndex()==0){
				JOptionPane.showMessageDialog(null, "��ѡ��Ŀ��Ժϵ��");
				OK=false;
			}
			
			if(OK){
				boolean isSucceed = yjTeacherController.publishCourse(name, credit,
						coursetype, grade, targetdepartment);
				if (isSucceed) {
					JOptionPane.showMessageDialog(null, "�����γ̳ɹ���");
					jtf1.setText("");
					jcb1.setSelectedIndex(0);
					jcb2.setSelectedIndex(0);
					jcb3.setSelectedIndex(0);
				} else {
					JOptionPane.showMessageDialog(null, "�����γ�ʧ�ܣ�");
					jtf1.setText("");
					jcb1.setSelectedIndex(0);
					jcb2.setSelectedIndex(0);
					jcb3.setSelectedIndex(0);
				}
			}

		}

	}

	// �鿴ˢ�¿γ�
	class Listener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<CourseVO> courseVOs = yjTeacherController.showCourseList(
					(String) jcb.getSelectedItem(), yjTeacherController
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
			table1 = new JTable(new DefaultTableModel(courseData, columnName)) {
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return false;
				}
			};
						
			scrollPane1 = new JScrollPane(table1);
			scrollPane1
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jp2.removeAll();
			jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
			jp2.add(jlb5);
			jp2.add(scrollPane1);
			jp2.add(jp2_2);
			jp2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
			jp2.repaint();
			jb2.setVisible(true);
			jb3.setVisible(true);
			// jb4.setVisible(true);
			
			table1.setOpaque(false);
			scrollPane1.setOpaque(false);
			scrollPane1.getViewport().setOpaque(false);
			
			if(courseData.length==0){
				JOptionPane.showMessageDialog(null,"==��Ϣ��ȱ==");
			}

		}

	}

	// ɾ���γ�
	class Listener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (table1.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "��ѡ���γ�");
			} else if(JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ���ÿγ�ô��")==JOptionPane.YES_OPTION){
				String courseid = table1.getValueAt(table1.getSelectedRow(), 0)
						.toString();
				if (yjTeacherController.deleteCourse(courseid)) {
					JOptionPane.showMessageDialog(null, "�ѳɹ�ɾ���ÿγ�");
					// �ɹ�ɾ������������ʾ�б�
					ArrayList<CourseVO> courseVOs = yjTeacherController
							.showCourseList((String) jcb.getSelectedItem(),
									yjTeacherController.getcurrentyjTeacherVO()
											.getDepartment());

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
					String[] columnName = { "�γ̺�", "�γ���", "�γ�����", "��ʦ", "ʱ��",
							"�ص�" };
					table1 = new JTable(new DefaultTableModel(courseData,
							columnName)) {
						public boolean isCellEditable(int rowIndex,
								int columnIndex) {
							return false;
						}
					};
					// table.getColumnModel().getColumn(4).setPreferredWidth(100);
					scrollPane1 = new JScrollPane(table1);
					scrollPane1
							.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					jp2.removeAll();
					jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
					jp2.add(jlb5);
					jp2.add(scrollPane1);
					jp2.add(jp2_2);
					jp2.setBorder(BorderFactory
							.createEmptyBorder(0, 10, 10, 10));
					jp2.repaint();
				}
				else{
					JOptionPane.showMessageDialog(null, "ɾ���γ�ʧ�ܣ�");
				}
			}
			
			table1.setOpaque(false);
			scrollPane1.setOpaque(false);
			scrollPane1.getViewport().setOpaque(false);
		}

	}

	// ���ſγ�
	class Listener4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (table1.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "��ѡ���γ�");
			} else {
				String courseid = table1.getValueAt(table1.getSelectedRow(), 0)
						.toString();
				improve = new improve(yjTeacherController,
						yjTeacherController.showCourseInfo(courseid));
				combine.removeAll();
				combine.setLayout(null);
				right.setBounds(0, 0, 50, 520);
				improve.setBounds(50, 0, 690, 520);
				combine.add(right);
				combine.add(improve);
				new MoveL().start();
			}
		}

	}

	class Show implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new MoveL().start();
		}
	}

	class MoveL extends Thread {
		public void run() {
			int x1 = combine.getX();
			int y1 = combine.getY();
			
			for (int x = 0; x <= 750; x = x + 50) {
				combine.setLocation(x1 - x, y1);
//				CouInfo.this.repaint();
				try {
					sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			jp1.setVisible(false);
			jp2.setVisible(false);

		}
	}

	class Right implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			new MoveR().start();
		}
	}

	class MoveR extends Thread {
		public void run() {
			int x1 = combine.getX();
			int y1 = combine.getY();

			if(status.equals("-1")){
				jp1.setVisible(true);
			}
			jp2.setVisible(true);
			for (int x = 0; x <= 750; x = x + 50) {
				combine.setLocation(x1 + x, y1);
				try {
					sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

}