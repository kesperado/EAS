package dataservice.userdataservice;

import java.util.ArrayList;

import po.StudentPO;
import po.TeacherPO;

/**
 * @author ���� :knox
 * ��˵����
 * ������û����ݲ����ӿ���
 */
public interface UserDataService {
	
	public String getMaxTeacherID();
	
	public String getMaxStudentID();
	
	public boolean addTeacher(TeacherPO teacherPO);
	
	public boolean deleteTeacher(String teacherid);
	
	public boolean updateTeacher(TeacherPO teacherPO);
	
	public TeacherPO queryTeacherByID(String teacherid);
	
	public ArrayList<TeacherPO> queryTeacherByTypeAndDepartment(String teachertype,String department);
	
	public ArrayList<TeacherPO> queryTeacherByType(String teachertype);
	
	public ArrayList<TeacherPO> queryTeacherByDepartment(String department);
	
	public ArrayList<TeacherPO> showAllTeachers();
	
	public boolean addStudent(StudentPO studentPO);
	
	public boolean deleteStudent(String studentid);
	
	public boolean updateStudent(StudentPO studentPO);
	
	public StudentPO queryStudentByID(String studentid);
	
	public ArrayList<StudentPO> queryStudentByDepartment(String department);
	
	public ArrayList<StudentPO> queryStudentByGradeAndDepartment(String grade,String department);
	
	public ArrayList<StudentPO> showAllStudents();
	
	


}
