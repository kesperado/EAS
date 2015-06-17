package vo;

import java.io.Serializable;

public class CourseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private int credit;
	private String time;
	private String classroom;
	private int memberlimit;
	private String outline;       //课程教学大纲
	private String textbook;       //课程教材
	private String referencebook;  //课程参考书目
	private String testtime; //考试时间
	private String grade;     
	private String targetdepartment;
	private String department;  
	private String coursetype; 
	private String canselect;
	private String canquit;
	
	public CourseVO(){
		
	}
	public CourseVO(String id, String name,int credit,String grade,String targetdepartment,String department,String coursetype) {
		this.id = id;
		this.name = name;
		this.credit = credit;
		this.grade = grade;
		this.targetdepartment = targetdepartment;
		this.department =department;
		this.coursetype=coursetype;          
	}                              //院系教务老师发布课程时要确定
	
//	public CourseVO(String id, String name,int credit,String grade,String targetdepartment,String department,String coursetype,String time,String classroom,String outline, String textbook,String referencebook){
//		this.id = id;
//		this.name = name;
//		this.credit = credit;
//		this.grade = grade;
//		this.targetdepartment = targetdepartment;
//		this.department =department;
//		this.coursetype=coursetype; 
//		this.time=time;
//		this.classroom =classroom;
//		this.outline=outline;
//		this.textbook=textbook;
//		this.referencebook=referencebook;
//	}
	
	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCredit(){
		return credit;
	}
	
	public String getTime() {
		return time;
	}

	public String getClassroom() {
		return classroom;
	}
	
	public String getOutline(){
		return outline;
	}
	
	public String getTextbook(){
		return textbook;
	}

	public String getReferenceBook() {
		return referencebook;
	}
	
	public String getTestTime(){
		return testtime;
	}
	
	public String getGrade(){
		return grade;
	}
	
	
	public String getTargetDepartment(){
		return targetdepartment;
	}
	
	public String getDepartment(){
		return department;
	}
	
	public String getCourseType(){
		return coursetype;
	}
	
	public int getMemberLimit() {
		return memberlimit;
	}
	
	public String getCanSelect() {
		return canselect;
	}

	public String getCanQuit() {
		return canquit;
	}


	public String getMainInfo(CourseVO courseVO) {
		return courseVO.getID() + " " + courseVO.getName() + " "
				+ courseVO.getCredit() + " " + courseVO.getTime() + " "
				+ courseVO.getClassroom();
	}

	public String getallInfo(CourseVO courseVO) {
		return courseVO.getID() + " " + courseVO.getName() + " "
				+ courseVO.getCredit() + " " + courseVO.getTime() + " "
				+ courseVO.getClassroom() + " " + courseVO.getOutline() + " "
				+ courseVO.getTextbook() + " " + courseVO.getReferenceBook()
				+ " " + courseVO.getGrade() + " " + courseVO.getTargetDepartment()+" " + courseVO.getDepartment()
				+ " " + courseVO.getCourseType();
	}

}
