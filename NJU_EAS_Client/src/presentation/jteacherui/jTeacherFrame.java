//教务处老师界面
package presentation.jteacherui;

import businesslogiccontroller.jteachercontroller.jTeacherController;
import presentation.mytools.myFrame;

@SuppressWarnings("serial")
public class jTeacherFrame extends myFrame{
	private jTeacherController jTeacherController;
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new jTeacherFrame();
//	}
	
	public jTeacherFrame(jTeacherController jTeacherController){

		this.jTeacherController=jTeacherController;
		reSetCard();
		super.header2.setText("欢迎你，"+this.jTeacherController.getcurrentjTeacherVO().getName());
	}
	
	protected void setButtons(){
		s1="index";
		s2="outline";
		s3="list";
		s4="admin";
	}
	
	protected void reSetCard(){
		jp1=new Index(this.jTeacherController);
		jp2=new combine(this.jTeacherController);
		jp3=new Lists(this.jTeacherController);
		jp4=new Admin(this.jTeacherController);
		card.removeAll();
		card.setLayout(cl);
		card.add(jp1,"1");
		card.add(jp2,"2");
		card.add(jp3,"3");
		card.add(jp4,"4");
		card.repaint();
	}
}
