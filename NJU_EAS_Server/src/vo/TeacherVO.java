package vo;

import java.io.Serializable;

import po.TeacherPO;

public class TeacherVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String teachertype;
	private String department;
	private String age;
	private String hometown;
	private String contact;

	public TeacherVO(String id, String name, String teachertype,
			String department) {
		this.id = id;
		this.name = name;
		this.teachertype = teachertype;
		this.department = department;
	}
	
	public TeacherVO(String id, String name, String teachertype,
			String department,String age,String hometown,String contact) {
		this.id = id;
		this.name = name;
		this.teachertype = teachertype;
		this.department = department;
		this.age=age;
		this.hometown=hometown;
		this.contact=contact;
	}
	
	public TeacherVO(TeacherPO teacherPO){
		this.id = teacherPO.getID();
		this.name = teacherPO.getName();
		this.teachertype = teacherPO.getTeacherType();
		this.department = teacherPO.getDepartment();
		this.age=teacherPO.getAge();
		this.hometown=teacherPO.getHometown();
		this.contact=teacherPO.getContact();
	}

	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTeacherType() {
		return teachertype;
	}

	public String getDepartment() {
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
