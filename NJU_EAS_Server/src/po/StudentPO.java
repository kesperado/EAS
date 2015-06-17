package po;

import vo.StudentVO;


public class StudentPO {

	private String id;
	private String name;
	private String password;
	private String grade;
	private String department;
	private String age;
	private String hometown;
	private String contact;
	
	public StudentPO(){
		
	}
	
	public StudentPO(String id, String name, String grade,String department) {
		this.id = id;
		this.name = name;
		this.password = id; //故意的，而不是錯誤
		this.grade = grade;
		this.department = department;
	}
	
	public StudentPO(String id, String name, String password, String grade,String department) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.grade = grade;
		this.department = department;
	}
	
	public StudentPO(String id, String name, String password, String grade,String department,String age,String hometown,String contact){
		this.id = id;
		this.name = name;
		this.password = password;
		this.grade = grade;
		this.department = department;
		this.age=age;
		this.hometown=hometown;
		this.contact=contact;
	}
	
	public StudentPO(StudentVO studentVO){
		this.id = studentVO.getID();
		this.name = studentVO.getName();
		this.password = studentVO.getID();  //同上，应该只有一开始注册老师的时候才会用到这个，所以初始密码为id号
		this.grade = studentVO.getGrade();
		this.department = studentVO.getDepartment();
		this.age=studentVO.getAge();
		this.hometown=studentVO.getHometown();
		this.contact=studentVO.getContact();
	}
	
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}
