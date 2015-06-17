//界面框架
package presentation.mytools;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;

import presentation.loginui.LoginUI;

import java.util.*;

@SuppressWarnings("serial")
public class myFrame extends JFrame implements ActionListener{
	//标题
	JPanel title;
	JButton mini,close;
	//上部区域
	JPanel header;
	JLabel header1;
	protected JLabel header2;
	//下部区域
	JPanel bottom;
	JLabel bottom1;
	JLabel timeNow;
	//中部区域
	protected JPanel middle,buttons;
	protected JPanel card;
	protected CardLayout cl=new CardLayout();
		
	protected JPanel jp1,jp2,jp3,jp4;
	protected JButton jb1,jb2,jb3,jb4,logout;
	protected String s1,s2,s3,s4;
	MouseListener l1=new Listener1();
	MouseListener l2=new Listener2();
	MouseListener l3=new Listener3();
	MouseListener l4=new Listener4();
	MouseListener l5=new Listener5();
		
	javax.swing.Timer t;
		
	int xOld = 0;  
	int yOld = 0;	

	@SuppressWarnings("deprecation")
	public myFrame(){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//标题
		mini=new myButton("Icons/mini_1.png");
		mini.addActionListener(this);
		mini.setActionCommand("缩小");
		mini.setSelectedIcon(new ImageIcon("Icons/mini_2.png"));
		mini.setBorder(new TitledBorder(new EtchedBorder()));
		
		close=new myButton("Icons/close_1.png");
		close.addActionListener(this);
		close.setActionCommand("关闭");
		close.setSelectedIcon(new ImageIcon("Icons/close_2.png"));
		close.setBorder(new TitledBorder(new EtchedBorder()));
		
		title=new JPanel();
		title.setLayout(new BoxLayout(title,BoxLayout.X_AXIS));
		title.add(Box.createHorizontalGlue());
		title.add(mini);
		title.add(close);
		title.setOpaque(false);
		title.setVisible(true);
		
		//处理拖动事件  
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
                myFrame.this.setLocation(xx, yy);  
            }  
        });
		
		//上部区域
		header1=new JLabel("南京大学选课系统");
		header1.setFont(new Font("长城中行书体",Font.BOLD,30));
		header2=new JLabel("欢迎你,");
//		JLabel im=new JLabel(new ImageIcon("Images/校徽3.png"));
		header=new myPanel("Icons/header.png");
		header.setLayout(new BoxLayout(header,BoxLayout.X_AXIS));
		header.setBorder(BorderFactory.createEmptyBorder (10, 10, 10, 10));
		header.add(Box.createHorizontalStrut(10));
//		header.add(im);
//		header.add(Box.createHorizontalStrut(20));
		header.add(header1);
		header.add(Box.createHorizontalGlue ()); 
		header.add(header2);
		
		//下部区域
		bottom1=new JLabel("Powered by 萌面人");
		t=new Timer(1000,this);
		t.start();
		timeNow=new JLabel(Calendar.getInstance().getTime().toLocaleString());
		bottom=new myPanel("Icons/bottom.png");
		bottom.setLayout(new BoxLayout(bottom,BoxLayout.X_AXIS));
		bottom.add(Box.createHorizontalStrut(10)); 
		bottom.add(timeNow);
		bottom.add(Box.createHorizontalGlue ()); 
		bottom.add(bottom1);
		bottom.add(Box.createHorizontalStrut(10));
		timeNow.setForeground(Color.WHITE);
		bottom1.setForeground(Color.WHITE);
		
		setButtons();
		jb1=new myButton("Icons/"+s1+"_1.png");
		jb2=new myButton("Icons/"+s2+"_1.png");
		jb3=new myButton("Icons/"+s3+"_1.png");
		jb4=new myButton("Icons/"+s4+"_1.png");
		logout=new myButton("Icons/logout_1.png");
		jb1.addMouseListener(l1);
		jb2.addMouseListener(l2);
		jb3.addMouseListener(l3);
		jb4.addMouseListener(l4);
		logout.addMouseListener(l5);
		buttons=new JPanel();
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
		buttons.setOpaque(false);
		buttons.add(jb1);
		buttons.add(jb2);
		buttons.add(jb3);
		buttons.add(jb4);
		buttons.add(Box.createVerticalGlue());
		buttons.add(logout);
		jb1.setIcon(new ImageIcon("Icons/index_3.png"));
		jb1.removeMouseListener(l1);
		
		setCard();
		card=new JPanel();
		card.setLayout(cl);
		card.add(jp1,"1");
		card.add(jp2,"2");
		card.add(jp3,"3");
		card.add(jp4,"4");
		
		middle=new JPanel();
		middle.setOpaque(false);
		middle.setLayout(new BorderLayout());
		middle.add(buttons,BorderLayout.WEST);
		middle.add(card,BorderLayout.CENTER);
				
		this.setContentPane(new myPanel("Images/test3.jpg"));
		Container ct=this.getContentPane();
		ct.setLayout(new BoxLayout(ct,BoxLayout.Y_AXIS));
		ct.add(title);
		ct.add(header);
		ct.add(middle);
		ct.add(bottom);
		((JComponent) ct).setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY)));
		
		this.setUndecorated(true); // 去掉窗口的装饰
        this.setPreferredSize(new Dimension(800, 600));   
        this.pack();   
		this.setLocationRelativeTo(null);;
		this.setVisible(true);
		this.setIconImage(new ImageIcon("Images/图标.png").getImage());
	}
	
	protected void setButtons(){
		s1="index";
		s2="teaplan";
		s3="couinfo";
		s4="list";
	}
	
	protected void setCard(){
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.timeNow.setText(Calendar.getInstance().getTime().toLocaleString());
		
		String command=arg0.getActionCommand();
		if(command!=null){
			if(command.equals("缩小")){
				setState(this.ICONIFIED);
			}else if(command.equals("关闭")){
				System.exit(0);
			}
		}
	}
	
	//以下均为按钮之间的交互监听	
	class Listener1 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb1.setIcon(new ImageIcon("Icons/"+s1+"_3.png"));
			jb1.removeMouseListener(this);
			cl.show(card, "1");
			if(jb2.getMouseListeners().length==1){
				jb2.setIcon(new ImageIcon("Icons/"+s2+"_1.png"));
				jb2.addMouseListener(l2);
			}
			if(jb3.getMouseListeners().length==1){
				jb3.setIcon(new ImageIcon("Icons/"+s3+"_1.png"));
				jb3.addMouseListener(l3);
			}
			if(jb4.getMouseListeners().length==1){
				jb4.setIcon(new ImageIcon("Icons/"+s4+"_1.png"));
				jb4.addMouseListener(l4);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb1.setIcon(new ImageIcon("Icons/"+s1+"_2.png"));
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb1.setIcon(new ImageIcon("Icons/"+s1+"_1.png"));
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb1.setIcon(new ImageIcon("Icons/"+s1+"_3.png"));
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class Listener2 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb2.setIcon(new ImageIcon("Icons/"+s2+"_3.png"));
			jb2.removeMouseListener(this);
			cl.show(card, "2");
			if(jb1.getMouseListeners().length==1){
				jb1.setIcon(new ImageIcon("Icons/"+s1+"_1.png"));
				jb1.addMouseListener(l1);
			}
			if(jb3.getMouseListeners().length==1){
				jb3.setIcon(new ImageIcon("Icons/"+s3+"_1.png"));
				jb3.addMouseListener(l3);
			}
			if(jb4.getMouseListeners().length==1){
				jb4.setIcon(new ImageIcon("Icons/"+s4+"_1.png"));
				jb4.addMouseListener(l4);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb2.setIcon(new ImageIcon("Icons/"+s2+"_2.png"));
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb2.setIcon(new ImageIcon("Icons/"+s2+"_1.png"));
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb2.setIcon(new ImageIcon("Icons/"+s2+"_3.png"));
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class Listener3 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb3.setIcon(new ImageIcon("Icons/"+s3+"_3.png"));
			jb3.removeMouseListener(this);
			cl.show(card, "3");
			if(jb1.getMouseListeners().length==1){
				jb1.setIcon(new ImageIcon("Icons/"+s1+"_1.png"));
				jb1.addMouseListener(l1);
			}
			if(jb2.getMouseListeners().length==1){
				jb2.setIcon(new ImageIcon("Icons/"+s2+"_1.png"));
				jb2.addMouseListener(l2);
			}
			if(jb4.getMouseListeners().length==1){
				jb4.setIcon(new ImageIcon("Icons/"+s4+"_1.png"));
				jb4.addMouseListener(l4);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb3.setIcon(new ImageIcon("Icons/"+s3+"_2.png"));
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb3.setIcon(new ImageIcon("Icons/"+s3+"_1.png"));
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb3.setIcon(new ImageIcon("Icons/"+s3+"_3.png"));
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class Listener4 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb4.setIcon(new ImageIcon("Icons/"+s4+"_3.png"));
			jb4.removeMouseListener(this);
			cl.show(card, "4");
			if(jb1.getMouseListeners().length==1){
				jb1.setIcon(new ImageIcon("Icons/"+s1+"_1.png"));
				jb1.addMouseListener(l1);
			}
			if(jb2.getMouseListeners().length==1){
				jb2.setIcon(new ImageIcon("Icons/"+s2+"_1.png"));
				jb2.addMouseListener(l2);
			}
			if(jb3.getMouseListeners().length==1){
				jb3.setIcon(new ImageIcon("Icons/"+s3+"_1.png"));
				jb3.addMouseListener(l3);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb4.setIcon(new ImageIcon("Icons/"+s4+"_2.png"));
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb4.setIcon(new ImageIcon("Icons/"+s4+"_1.png"));
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jb4.setIcon(new ImageIcon("Icons/"+s4+"_3.png"));
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class Listener5 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			logout.setIcon(new ImageIcon("Icons/logout_3.png"));
			myFrame.this.dispose();
			new LoginUI();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			logout.setIcon(new ImageIcon("Icons/logout_2.png"));
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			logout.setIcon(new ImageIcon("Icons/logout_1.png"));
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			logout.setIcon(new ImageIcon("Icons/logout_3.png"));
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

}
