//任课老师界面
package presentation.rteacherui;

import businesslogiccontroller.rteachercontroller.rTeacherController;
import presentation.mytools.myFrame;

@SuppressWarnings("serial")
public class rTeacherFrame extends myFrame{
	private rTeacherController rTeacherController;
	
	public rTeacherFrame(rTeacherController rTeacherController){
		this.rTeacherController = rTeacherController;
		reSetCard();
		super.header2.setText("欢迎你,"+this.rTeacherController.getcurrentrTeacherVO().getName());
	}
	
	protected void setButtons(){
		s1="index";
		s2="improve";
		s3="mycou";
		s4="";
	}
	
	protected void reSetCard(){
		jp1=new Index(this.rTeacherController);
		jp2=new Improve(this.rTeacherController);
		jp3=new Mycourse(this.rTeacherController);
//		jp4=new Mark(this.rTeacherController);
		card.removeAll();
		card.setLayout(cl);
		card.add(jp1,"1");
		card.add(jp2,"2");
		card.add(jp3,"3");
//		card.add(jp4,"4");
		card.repaint();
	}
}
