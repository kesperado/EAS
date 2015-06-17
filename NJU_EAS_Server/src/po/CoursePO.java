package po;

import vo.CourseVO;


public class CoursePO {
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
	
	public CoursePO(){
		
	}
	public CoursePO(String id, String name,int credit,String grade,String targetdepartment,String department,String coursetype) {
		this.id = id;
		this.name = name;
		this.credit = credit;
		this.grade = grade;
		this.targetdepartment = targetdepartment;
		this.department =department;
		this.coursetype=coursetype;          
	}                           
	
//	public CoursePO(String id, String name,int credit,String grade,String targetdepartment,String department,String coursetype,String outline, String textbook,String referencebook){
//		this.id = id;
//		this.name = name;
//		this.credit = credit;
//		this.grade = grade;
//		this.targetdepartment = targetdepartment;
//		this.department =department;
//		this.coursetype=coursetype; 
//		this.outline=outline;
//		this.textbook=textbook;
//		this.referencebook=referencebook;
//	}
	
	public CoursePO(CourseVO courseVO){
		this.id=courseVO.getID();
		this.name=courseVO.getName();
		this.credit=courseVO.getCredit();
		this.grade=courseVO.getGrade();
		this.targetdepartment = courseVO.getTargetDepartment();
		this.department=courseVO.getDepartment();
		this.coursetype=courseVO.getCourseType();
		this.time=courseVO.getTime();
		this.classroom=courseVO.getClassroom();
		this.memberlimit=courseVO.getMemberLimit();
		this.outline=courseVO.getOutline();
		this.textbook=courseVO.getTextbook();
		this.referencebook=courseVO.getReferenceBook();
		this.testtime=courseVO.getTestTime();
		this.canselect = courseVO.getCanSelect();
		this.canquit= courseVO.getCanQuit();
	}

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

	public void setID(String id){
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	
	public void setCredit(int credit){
		this.credit=credit;
	}
	
	public void setOutline(String outline){
		this.outline=outline;
	}
	
	public void setTextbook(String textbook){
		this.textbook=textbook;
	}

	public void setReferenceBook(String referencebook) {
		this.referencebook = referencebook;
	}
	
	public void setTestTime(String testtime){
		this.testtime=testtime;
	}
	
	public void setGrade(String grade){
		this.grade =grade;
	}
	
	public void setTargetDepartment(String targetdepartment){
		this.targetdepartment=targetdepartment;
	}
	
	public void setDepartment(String department){
		this.department =department;
	}
	
	public void setCourseType(String coursetype){
		this.coursetype = coursetype;
	}
	
	public void setMemberLimit(int memberlimit) {
		this.memberlimit = memberlimit;
	}

	public void setCanSelect(String canselect) {
		this.canselect = canselect;
	}
	
	public void setCanQuit(String canquit) {
		this.canquit = canquit;
	}

}
