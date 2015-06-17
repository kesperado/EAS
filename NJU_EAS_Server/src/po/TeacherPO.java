package po;

import vo.TeacherVO;

public class TeacherPO {

	private String id;
	private String name;
	private String password;
	private String teachertype;
	private String department;
	private String age;
	private String hometown;
	private String contact;
	//һ�_ʼ�]�Եĕr������ǽ̄�̎�ώ������]�Եģ������ܴa��Ĭ�J�ܴa��Ĭ�J�ܴa��id
	public TeacherPO(){
		
	}
	
	public TeacherPO(String id, String name, String teachertype,String department) {
		this.id = id;
		this.name = name;
		this.password = id; //����ģ��������e�`
		this.teachertype = teachertype;
		this.department = department;
	}
	
	public TeacherPO(String id, String name, String password,String teachertype,String department) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.teachertype = teachertype;
		this.department = department;
	}
	
	public TeacherPO(String id, String name, String password,String teachertype,String department,String age,String hometown,String contact) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.teachertype = teachertype;
		this.department = department;
		this.age=age;
		this.hometown=hometown;
		this.contact=contact;
	}
	
	public TeacherPO(TeacherVO teacherVO){
		this.id = teacherVO.getID();
		this.name = teacherVO.getName();
		this.password = teacherVO.getID();  //ͬ�ϣ�Ӧ��ֻ��һ��ʼע����ʦ��ʱ��Ż��õ���������Գ�ʼ����Ϊid��
		this.teachertype = teacherVO.getTeacherType();
		this.department = teacherVO.getDepartment();
		this.age=teacherVO.getAge();
		this.hometown=teacherVO.getHometown();
		this.contact=teacherVO.getContact();
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

	public String getTeacherType() {
		return teachertype;
	}

	public void setTeacherType(String teachertype) {
		this.teachertype = teachertype;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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


}
