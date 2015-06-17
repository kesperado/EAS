package vo;

import java.io.Serializable;

import po.StudentPO;

public class StudentVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String grade;
	private String department;
	private String age;
	private String hometown;
	private String contact;


	public StudentVO(String id,String name,String grade,String department){
		this.id = id;
		this.name =name;
		this.grade =grade;
		this.department =department;
	}
	
	public StudentVO(String id,String name,String grade,String department,String age,String hometown,String contact){
		this.id = id;
		this.name =name;
		this.grade =grade;
		this.department =department;
		this.age=age;
		this.hometown=hometown;
		this.contact=contact;
	}
	
	public StudentVO(StudentPO studentPO){
		this.id =studentPO.getID();
		this.name =studentPO.getName();
		this.grade =studentPO.getGrade();
		this.department =studentPO.getDepartment();
		this.age=studentPO.getAge();
		this.hometown=studentPO.getHometown();
		this.contact=studentPO.getContact();
	}
	
	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getGrade(){
		return grade;
	}
	
	public String getDepartment(){
		return department;
	}
	
	public String getAge() {
		return age;
	}

	public String getHometown() {
		return hometown;
	}

	public String getContact() {
		return contact;
	}
}
