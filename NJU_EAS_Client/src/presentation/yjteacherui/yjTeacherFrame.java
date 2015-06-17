//院系教务老师界面
package presentation.yjteacherui;

import businesslogiccontroller.yjteachercontroller.yjTeacherController;
import presentation.mytools.myFrame;

@SuppressWarnings("serial")
public class yjTeacherFrame extends myFrame{
	private yjTeacherController yjTeacherController;
	
	public yjTeacherFrame( yjTeacherController yjTeacherController){
		this.yjTeacherController = yjTeacherController;
		reSetCard();
		super.header2.setText("欢迎你,"+this.yjTeacherController.getcurrentyjTeacherVO().getName());
	}
	
	protected void setButtons(){
		s1="index";
		s2="teaplan";
		s3="couinfo";
		s4="list";
	}
	
	protected void reSetCard(){
		jp1=new Index(this.yjTeacherController);
		jp2=new TeaPlan(this.yjTeacherController);
		jp3=new CouInfo(this.yjTeacherController);
		jp4=new List(this.yjTeacherController);
		card.removeAll();
		card.setLayout(cl);
		card.add(jp1,"1");
		card.add(jp2,"2");
		card.add(jp3,"3");
		card.add(jp4,"4");
		card.repaint();
	}
}
