//完善课程信息
package presentation.rteacherui;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import businesslogiccontroller.rteachercontroller.rTeacherController;
import vo.CourseVO;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Improve extends JPanel {
	private rTeacherController rTeacherController;
	JLabel jlb1;
	JButton jb1, jb2, jb3;
	JPanel jp1, jp2, jp3;
	JTable table;
	JScrollPane scrollPane;
	JTextField jtf1, jtf2, jtf3, jtf4;

	public Improve(rTeacherController rTeacherController) {
		this.rTeacherController = rTeacherController;
		jlb1 = new JLabel("我的课程：");

		jp1 = new JPanel();
		setTabel();
		jp1.setLayout(new BorderLayout());
		jp1.add(jlb1, BorderLayout.NORTH);
		jp1.add(scrollPane, BorderLayout.CENTER);
		jp1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		jb1 = new JButton("开始完善");
		jb2 = new JButton("保存并提交");
		jb3 = new JButton("取消");
		jb1.addActionListener(new Listener1());
		jb2.addActionListener(new Listener2());
		jb3.addActionListener(new Listener3());
		jp2 = new JPanel();
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(Box.createHorizontalGlue());
		jp2.add(jb1);
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(jb2);
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(jb3);
		jp2.add(Box.createHorizontalStrut(10));
		jb2.setVisible(false);
		jb3.setVisible(false);

		jp3 = new JPanel();
		jp3.setBorder(new TitledBorder("课程详细信息"));
		jp3.setPreferredSize(new Dimension(800, 300));

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		jp3.setVisible(false);
		this.add(Box.createVerticalStrut(10));
		this.setVisible(true);
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		table.setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
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
	
	//列表显示课程
	void setTabel() {
		String[] columnName = { "课程号", "课程名", "学分", "时间", "地点","人数上限" };
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
			courseData[i][5] = courseVOs.get(i).getMemberLimit()+"";
		}

		table = new JTable(courseData, columnName){
			public boolean isCellEditable(int rowIndex,int columnIndex) {
				 return false;
			}
		};
		
		if(table.getRowCount()==0){
			JOptionPane.showMessageDialog(null, "暂时尚未收到课程安排");
		}
		
		scrollPane = new JScrollPane(table);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table.setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		
//		if(table.getRowCount()==0){
//			JOptionPane.showMessageDialog(null, "暂时尚未收到课程安排");
//		}
	}

	class Listener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "请选择一门您要完善的课程");
			} else {
				String courseid = (String)table.getValueAt(table.getSelectedRow(), 0);
				CourseVO courseVO=rTeacherController.showCourseInfo(courseid);
				jb2.setVisible(true);
				jb3.setVisible(true);
				jp3.setVisible(true);
				jp3.removeAll();

				JLabel lb1 = new JLabel("大纲：        ");
				JLabel lb2 = new JLabel("教科书：    ");
				JLabel lb3 = new JLabel("参考书：    ");
				JLabel lb4 = new JLabel("考试时间：");

				jtf1 = new JTextField(50);
				jtf2 = new JTextField(50);
				jtf3 = new JTextField(50);
				jtf4 = new JTextField(50);
				
				jtf1.setText(courseVO.getOutline());
				jtf2.setText(courseVO.getTextbook());
				jtf3.setText(courseVO.getReferenceBook());
				jtf4.setText(courseVO.getTestTime());

				JPanel jp3_1 = new JPanel();
				jp3_1.add(lb1);
				jp3_1.add(jtf1);
				JPanel jp3_2 = new JPanel();
				jp3_2.add(lb2);
				jp3_2.add(jtf2);
				JPanel jp3_3 = new JPanel();
				jp3_3.add(lb3);
				jp3_3.add(jtf3);
				JPanel jp3_4 = new JPanel();
				jp3_4.add(lb4);
				jp3_4.add(jtf4);

				jp3.setLayout(new BoxLayout(jp3, BoxLayout.Y_AXIS));
				jp3.add(Box.createVerticalStrut(10));
				jp3.add(jp3_1);
				jp3.add(jp3_2);
				jp3.add(jp3_3);
				jp3.add(jp3_4);
				jp3.add(Box.createVerticalStrut(10));
				
				jp3_1.setOpaque(false);
				jp3_2.setOpaque(false);
				jp3_3.setOpaque(false);
				jp3_4.setOpaque(false);
			}
		}

	}

	//课程信息录入
	class Listener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String courseid = table.getValueAt(table.getSelectedRow(), 0)
					.toString();
			String outline = jtf1.getText(); // 这三个东西都要分别建JtextField然后get出来
			String textbook = jtf2.getText();
			String referencebook = jtf3.getText();
			String testtime = jtf4.getText();
			if(rTeacherController.completeCourseInfo(courseid, outline, textbook,
					referencebook, testtime)){
				JOptionPane.showMessageDialog(null, "课程信息修改成功！");
				jb2.setVisible(false);
				jb3.setVisible(false);
				jp3.setVisible(false);
				jp3.removeAll();
			}else{
				JOptionPane.showMessageDialog(null, "完善课程信息失败！");
			}

			
		}

	}

	class Listener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jb2.setVisible(false);
			jb3.setVisible(false);
			jp3.setVisible(false);
		}

	}

}
