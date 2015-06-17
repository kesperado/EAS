//学生界面
package presentation.studentui;

import businesslogiccontroller.studentcontroller.StudentController;
import presentation.mytools.myFrame;

@SuppressWarnings("serial")
public class StudentFrame extends myFrame{
	private StudentController studentController;
	
	public StudentFrame(StudentController studentController){
		this.studentController = studentController;
		String status=studentController.getSystemStatus();
		if(status.equals("-1")||status.equals("2")){
			super.buttons.remove(jb2);
		}
		reSetCard();
		super.header2.setText("欢迎你,"+this.studentController.getcurrentStudent().getName());
	}
	
	protected void setButtons(){		
		s1="index";
		s2="select";
		s3="mycou";
		s4="score";
	}
	
	protected void reSetCard(){
		jp1=new Index(this.studentController);
		jp2=new Select(this.studentController);
		jp3=new Course(this.studentController);
		jp4=new Score(this.studentController);
		card.removeAll();
		card.setLayout(cl);
		card.add(jp1,"1");
		card.add(jp2,"2");
		card.add(jp3,"3");
		card.add(jp4,"4");
		card.repaint();
	}

}
