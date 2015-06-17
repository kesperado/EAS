//��¼����
package presentation.loginui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import businesslogiccontroller.logincontroller.LoginController;
import businesslogiccontroller.studentcontroller.StudentController;
import businesslogiccontroller.jteachercontroller.jTeacherController;
import businesslogiccontroller.rteachercontroller.rTeacherController;
import businesslogiccontroller.yjteachercontroller.yjTeacherController;
import presentation.studentui.StudentFrame;
import presentation.jteacherui.jTeacherFrame;
import presentation.mytools.myPanel;
import presentation.rteacherui.rTeacherFrame;
import presentation.yjteacherui.yjTeacherFrame;

@SuppressWarnings("serial")
public class LoginUI extends JFrame implements ActionListener,Runnable {

	// �������
	JPanel title,main;
	JButton mini,close;
	// ��������
	JLabel Header;
	// �ϲ�����
	JPanel Buttons;
	JButton jb1, jb2;
	// �в�����
	JPanel Info;
	JPanel box,text,pass;
	JPanel jp1;
	JLabel ID,Key,Role,Picture;
	JTextField jtf;
	JPasswordField jpf;
	JComboBox<String> jcb;
	
	int xOld = 0;  
    int yOld = 0;

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new LoginUI();
//	}

	public LoginUI() {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		ImageIcon miniIm = new ImageIcon("Icons/mini_1.png");
		mini=new JButton(miniIm);
		mini.setContentAreaFilled(false);
		mini.setBorderPainted(false);
		mini.setFocusPainted(false);
		mini.addActionListener(this);
		mini.setActionCommand("��С");
		mini.setSelectedIcon(new ImageIcon("Icons/mini_2.png"));
		
		ImageIcon closeIm = new ImageIcon("Icons/close_1.png");
		close=new JButton(closeIm);
		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		close.setFocusPainted(false);
		close.addActionListener(this);
		close.setActionCommand("�ر�");
		close.setSelectedIcon(new ImageIcon("Icons/close_2.png"));
		
		mini.setBorder(new TitledBorder(new EtchedBorder()));
		close.setBorder(new TitledBorder(new EtchedBorder()));
		title=new JPanel();
		title.setLayout(new BoxLayout(title,BoxLayout.X_AXIS));
		title.add(Box.createHorizontalGlue());
		title.add(mini);
		title.add(close);
		title.setBackground(new Color(185,0,255));
		title.setVisible(true);
		
		//�����϶��¼�  
        title.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mousePressed(MouseEvent e) {  
                xOld = e.getX();  
                yOld = e.getY();  
            }  
        });  
        title.addMouseMotionListener(new MouseMotionAdapter() {  
            @Override  
            public void mouseDragged(MouseEvent e) {  
                int xOnScreen = e.getXOnScreen();  
                int yOnScreen = e.getYOnScreen();  
                int xx = xOnScreen - xOld;  
                int yy = yOnScreen - yOld;  
                LoginUI.this.setLocation(xx, yy);  
            }  
        });
		
		Header = new JLabel(new ImageIcon("Images/Header2.png"));
		Picture = new JLabel(new ImageIcon("Images/У��.png"));

		Info = new JPanel();
		Role = new JLabel("  ��ѡ�����  ", JLabel.CENTER);
		ID = new JLabel("  �������˺�  ", JLabel.CENTER);
		Key = new JLabel("  ����������  ", JLabel.CENTER);
		jtf = new JTextField(10);
		jpf = new JPasswordField(10);
		String[] roles = { "������ʦ          ", "Ժϵ������ʦ", "�ο���ʦ", "ѧ��" };
		jcb = new JComboBox<String>(roles);

		box = new JPanel();
		box.add(Role);
		box.add(jcb);
		text = new JPanel();
		text.add(ID);
		text.add(jtf);
		pass = new JPanel();
		pass.add(Key);
		pass.add(jpf);
		Info.add(box);
		Info.add(text);
		Info.add(pass);

		jp1 = new JPanel();
		Picture.setBounds(10, 0, 100, 137);
//		Picture.setBorder(new TitledBorder(new EtchedBorder()));
		Info.setBounds(115, 0, 250, 137);
//		Info.setBorder(new TitledBorder(new EtchedBorder()));

		jp1.setLayout(null);
		jp1.add(Info);
		jp1.add(Picture);

		jp1.setVisible(true);

		Buttons = new JPanel();
		jb1 = new JButton("        ��     ¼        ");
		jb2 = new JButton("     ȡ��      ");
		Buttons.add(jb1);
//		Buttons.add(jb2);

		jb1.addActionListener(this);
		jb1.setActionCommand("��¼");
		jb2.addActionListener(this);
		jb2.setActionCommand("ȡ��");
		
		main=new JPanel();
		main=new myPanel("Images/main.png");
		main.setLayout(new BorderLayout());
		main.add(Header,BorderLayout.NORTH);
		main.add(jp1,BorderLayout.CENTER);
		main.add(Buttons,BorderLayout.SOUTH);
		
		Picture.setOpaque(false);
		box.setOpaque(false);
		text.setOpaque(false);
		pass.setOpaque(false);
		Info.setOpaque(false);
		jp1.setOpaque(false);
		Buttons.setOpaque(false);
		Header.setBorder(new TitledBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY)));
		Buttons.setBorder(new TitledBorder(new MatteBorder(1, 0, 0, 0, Color.GRAY)));
		
		this.setLayout(new BorderLayout());
		this.add(title,BorderLayout.NORTH);
		this.add(main,BorderLayout.CENTER);
		
		this.getRootPane().setDefaultButton(jb1);
		this.setUndecorated(true); // ȥ�����ڵ�װ��
		((JComponent) this.getContentPane()).setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY)));
		this.setSize(380, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setIconImage(new ImageIcon("Images/ͼ��.png").getImage());
	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// �ж����ĸ���ť�����
		if (e.getActionCommand().equals("��¼")) {
			Info.setVisible(false);
			Picture.setLocation(10, 0);
			jb1.removeActionListener(this);
			Thread th = new Thread(this);
			th.start();
		}else if(e.getActionCommand().equals("ȡ��")){
			System.exit(0);
		}
		
		if(e.getActionCommand().equals("��С")){
			setState(this.ICONIFIED);
		}else if(e.getActionCommand().equals("�ر�")){
			System.exit(0);
		}

	}

	public void run() {
		int x = 0;
		while (x < 130) {
			Picture.setLocation(10 + x, 0);
			x += 1;
			try {
				Thread.sleep(2);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Picture.repaint();
		}
		
		LoginController loginController = new LoginController();
		String role = (String) jcb.getSelectedItem();
		String id = jtf.getText();
		@SuppressWarnings("deprecation")
		String password = jpf.getText();
		boolean issucceed =false;
		//����Ϊ��¼��Ϣ����
		if(role.equals("������ʦ          ")){
			if(loginController.teacherLogin(id, password, "������ʦ")){
				issucceed=true;
				new jTeacherFrame(new jTeacherController(loginController.getTeacherInfo(id)));
			}
			
		}else if(role.equals("Ժϵ������ʦ")){
			if(loginController.teacherLogin(id, password, "Ժϵ������ʦ")){
				issucceed=true;
				new yjTeacherFrame(new yjTeacherController(loginController.getTeacherInfo(id)));
			}
		}else if(role.equals("�ο���ʦ")){
			if(loginController.teacherLogin(id, password, "�ο���ʦ")){
				issucceed=true;
				new rTeacherFrame(new rTeacherController(loginController.getTeacherInfo(id)));
			}
		}else if(role.equals("ѧ��")){
			if(loginController.studentLogin(id, password)){
				issucceed=true;
				new StudentFrame(new StudentController(loginController.getStudentInfo(id)));
			}
		}
		
		//��¼ʧ��
		if(!issucceed){
			JOptionPane.showMessageDialog(null,"��¼��Ϣ��������");
			new LoginUI();
		}
		this.dispose();
		
	}
	
}
