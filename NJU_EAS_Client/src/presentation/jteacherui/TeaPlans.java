//教学计划
package presentation.jteacherui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import vo.PlanVO;
import businesslogiccontroller.jteachercontroller.jTeacherController;

@SuppressWarnings("serial")
public class TeaPlans extends JPanel{
	private jTeacherController jTeacherController;
	JPanel jp1,jp2,jp3,jp4;
	JTable table1;
	DefaultTableModel model1;
	JScrollPane scrollpane1;
	
	JLabel jlb1,jlb2,jlb3;
	JComboBox<String> jcb1,jcb2,jcb3;
	JButton jb1;
	
	JTextArea jta;
	public TeaPlans(jTeacherController jTeacherController){
		this.jTeacherController=jTeacherController;
		jlb1=new JLabel("选择院系:");
		String[] departments={"无","软件学院","商学院","文学院","现代工程学院","数学系","物理系","天文系","化学系"};
		jcb1=new JComboBox<String>(departments);
		jb1=new JButton("查看");
		jb1.addActionListener(new Listener());
		jp1=new JPanel();
		jp1.setLayout(new BoxLayout(jp1,BoxLayout.X_AXIS));
		jp1.add(jlb1);
		jp1.add(jcb1);
		jp1.add(Box.createHorizontalGlue());
		jp1.add(jb1);
		jp1.setBorder(BorderFactory.createEmptyBorder (0, 10, 10, 10));
		
		jp2=new JPanel();
		jp2.setBorder(new TitledBorder("详细信息"));
		jp2.setPreferredSize(new Dimension(800,500));
		jta=new JTextArea();
		jta.setEditable(false);
		JScrollPane jsp=new JScrollPane(jta);
		jsp.setPreferredSize(new Dimension(700,350));
		jp2.add(jsp,BorderLayout.CENTER);
		
		jp3=new JPanel();
//		jlb2=new JLabel("目前提交计划的院系有：");
		String[] row={"目前提交计划的院系有"};
		String[][] data={};
		model1=new DefaultTableModel(data,row);
		table1=new JTable(model1){
			public boolean isCellEditable(int rowIndex,int columnIndex) {
				return false;
			}
		};
		scrollpane1=new JScrollPane(table1);
		jp3.setLayout(new BorderLayout());
//		jp3.add(jlb2,BorderLayout.NORTH);
		jp3.add(scrollpane1,BorderLayout.CENTER);
		jp3.setBorder(BorderFactory.createEmptyBorder (10, 0, 5, 10));
		jp3.setPreferredSize(new Dimension(150,500));
		
		jp4=new JPanel();
		jp4.setLayout(new BorderLayout());
		jp4.add(jp2,BorderLayout.CENTER);
//		jp4.add(jp3,BorderLayout.EAST);
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(Box.createVerticalStrut(10));
		this.add(jp1);
		this.add(Box.createVerticalStrut(10));
		this.add(jp4);
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
	}
	class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			PlanVO planVO=jTeacherController.showDepartmentPlan((String)jcb1.getSelectedItem());
			if(jcb1.getSelectedIndex()!=0){
				if(planVO.getDetails()==null){
					JOptionPane.showMessageDialog(null,"==信息暂缺==");
				}else if(planVO.getDetails().equals("")){
					JOptionPane.showMessageDialog(null,"==信息暂缺==");
				}
				jta.setText(planVO.getDetails());
			}
		}
		
	}
}

