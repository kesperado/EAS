package businesslogicservice.userblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.StudentVO;
import vo.TeacherVO;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是用户逻辑操作接口
 */
public interface UserBLService extends Remote{

	public boolean RegisterTeacher(String name,String teachertype,String department)throws RemoteException;
	
	public boolean RegisterStudent(String name,String grade,String department)throws RemoteException;
	
	public boolean completeTeacherInfo(String teacherid,String age,String hometown,String contact)throws RemoteException;
	
	public boolean completeStudentInfo(String studentid,String age,String hometown,String contact)throws RemoteException;
	
	public boolean changeTeacherPassword(String teacherid,String oldpassword,String newpassword)throws RemoteException;
	
	public boolean changeStudentPassword(String studentid,String oldpassword,String newpassword)throws RemoteException;
	
	public boolean teacherLogin(String teacherid,String password,String teachertype)throws RemoteException;
	
	public boolean studentLogin(String studentid,String password)throws RemoteException;
	
	public boolean deleteTeacher(String teacherid)throws RemoteException;
	
	public boolean deleteStudent(String studentid)throws RemoteException;
	
	public TeacherVO queryTeacherByID (String teacherid)throws RemoteException;
	
	public StudentVO queryStudentByID(String studentid)throws RemoteException;
	
	public ArrayList<TeacherVO> queryTeacherByTypeAndDepartment(String teachertype,String department)throws RemoteException;
	
	public ArrayList<TeacherVO> queryTeacherByType(String teachertype)throws RemoteException;
	
	public ArrayList<TeacherVO> queryTeacherByDepartment(String department)throws RemoteException;
	
	public ArrayList<StudentVO> queryStudentByGradeAndDepartment(String grade,String department)throws RemoteException;

	public ArrayList<StudentVO> queryStudentByDepartment(String department)throws RemoteException;


}
