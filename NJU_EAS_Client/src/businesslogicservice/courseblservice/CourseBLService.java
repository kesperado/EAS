package businesslogicservice.courseblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.CourseVO;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是课程逻辑操作接口
 */
public interface CourseBLService extends Remote{

	public boolean publishCourse(String name, int credit, String coursetype,String grade,String targetdepartment,String department) throws RemoteException;
	
	public boolean arrangeCourse(String courseid,String time,String classroom,int memberlimit)throws RemoteException;

	public boolean completerCourseInfo(String courseid,String outline,String textbook,String referencebook,String testtime)throws RemoteException;
	
	public boolean startSelectCourse()throws RemoteException;
	
	public boolean startQuitAndReselectCourse()throws RemoteException;
	
	public boolean finishSelectAndQuitCourse()throws RemoteException;
	
	public boolean deleteCourse(String courseid)throws RemoteException;
	
	public ArrayList<CourseVO> queryCourseByType(String coursetype) throws RemoteException;
	
	public ArrayList<CourseVO> queryCourseByDepartment(String department) throws RemoteException;
	
	public ArrayList<CourseVO> queryCourseByGradeAndDepartment(String grade,String department)throws RemoteException;
	
	public ArrayList<CourseVO> queryCourseByGradeDepartmentAndType(String grade,String department,String coursetype)throws RemoteException;
	
	public CourseVO queryCourseByID(String courseid)throws RemoteException;
	
}
