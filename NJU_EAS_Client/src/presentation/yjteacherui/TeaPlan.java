//Ժϵ��ѧ�ƻ�
package presentation.yjteacherui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import vo.PlanVO;
import businesslogiccontroller.yjteachercontroller.yjTeacherController;

@SuppressWarnings("serial")
public class TeaPlan extends JPanel {
	private yjTeacherController yjTeacherController;
	JPanel jp1, jp2;
	JTextArea jta;
	JPanel jp3;
	JLabel jlb1, jlb2;
	JTextField jtf1, jtf2;

	JButton jb1, jb2, jb3;

	public TeaPlan(yjTeacherController yjTeacherController) {
		this.yjTeacherController = yjTeacherController;
		jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);

		jlb1 = new JLabel("׼������ѧ�֣�");
		jlb2 = new JLabel("׼������ѧ�֣�");
		jtf1 = new JTextField(10);
		jtf2 = new JTextField(10);
		jp3 = new JPanel();
		jp3.setLayout(new BoxLayout(jp3, BoxLayout.X_AXIS));
		jp3.add(Box.createHorizontalStrut(10));
		jp3.add(jlb1);
		jp3.add(jtf1);
		jp3.add(Box.createHorizontalStrut(200));
		jp3.add(jlb2);
		jp3.add(jtf2);
		jp3.add(Box.createHorizontalStrut(10));

		jp1 = new JPanel();
		jp1.setBorder(new TitledBorder("��ѧ�ƻ�����"));
		jp1.setPreferredSize(new Dimension(800, 600));
		jsp.setBounds(20, 35, 700, 340);
		jp3.setBounds(20, 380, 700, 35);
		jp1.setLayout(null);
		jp1.add(jsp);
		jp1.add(jp3);
		jta.setEditable(false);
		jtf1.setEditable(false);
		jtf2.setEditable(false);

		jb1 = new JButton("��ʼ����/�޸�");
		jb1.addActionListener(new Listener1());
		jb2 = new JButton("ȡ��");
		jb3 = new JButton("���沢�ύ�޸�");
		jb3.addActionListener(new Listener2());
		jp2 = new JPanel();
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
		jp2.add(Box.createHorizontalGlue());
		jp2.add(jb1);
		// jp2.add(Box.createHorizontalStrut(10));
		// jp2.add(jb2);
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(jb3);
		jp2.add(Box.createHorizontalStrut(10));

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createVerticalStrut(10));
		this.add(jp1);
		this.add(Box.createVerticalStrut(10));
		this.add(jp2);
		this.add(Box.createVerticalStrut(10));
		this.setVisible(true);

		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		
		init();
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
	
	//��������Ϣ
	public void init() {
		PlanVO planVO = null;
		if ((planVO = yjTeacherController.showPlan()) != null) {
			if (planVO.getDetails() != null) {
				jta.setText(planVO.getDetails());
			}
//			if (planVO.getInadmit() != 0) {
				jtf1.setText(planVO.getInadmit() + "");
//			}
//			if (planVO.getOutadmit() != 0) {
				jtf2.setText(planVO.getOutadmit() + "");
//			}
		}
	}

	//��ʼ�޸Ľ�ѧ�ƻ�
	class Listener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			jta.setEditable(true);
			jtf1.setEditable(true);
			jtf2.setEditable(true);
		}

	}

	//ȷ���޸Ľ�ѧ�ƻ�
	class Listener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean OK=true;
			try {
				@SuppressWarnings("unused")
				int a = Integer.parseInt(jtf1.getText());
			} catch (Exception e1) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,"׼��ѧ������������");
				OK=false;
			}
			try {
				@SuppressWarnings("unused")
				int a = Integer.parseInt(jtf2.getText());
			} catch (Exception e1) {
				// TODO: handle exception
				if(OK){JOptionPane.showMessageDialog(null,"׼��ѧ������������");}
				OK=false;
			}
			
			if(OK){
				if (jta.getText() != null &&!jta.getText().equals("") && !jtf1.getText().equals("")
						&& !jtf2.getText().equals("")) {
					if (yjTeacherController.modifyPlan(jta.getText(),
							Integer.parseInt(jtf1.getText()),
							Integer.parseInt(jtf2.getText()))) {
						JOptionPane.showMessageDialog(null, "�޸Ľ�ѧ�ƻ��ɹ�");
						jta.setEditable(false);
						jtf1.setEditable(false);
						jtf2.setEditable(false);
					} else {
						JOptionPane.showMessageDialog(null, "�޸Ľ�ѧ�ƻ�ʧ��");
					}
				} else {
					JOptionPane.showMessageDialog(null, "�뽫��ѧ�ƻ���д�������ύ");
				}
			}

		}

	}

}