package businesslogicservice.selectionrecordblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.CourseVO;
import vo.SelectionRecordVO;
import vo.StudentVO;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是选课逻辑操作接口
 */
public interface SelectionRecordBLService extends Remote{

	public boolean selectCourse(String courseid,String studentid)throws RemoteException;
	
	public boolean preselectCourse(String courseid,String studentid,String rank,int memberlimit)throws RemoteException;
	
	public boolean quitCourse(String courseid,String studentid)throws RemoteException;
	
	public boolean quitpreCourse(String courseid,String studentid)throws RemoteException;
	
	public boolean assessCourse(String courseid,int score)throws RemoteException;
	
	public double showCourseAssess(String courseid)throws RemoteException;
	
	public boolean selectionDraw()throws RemoteException;
	
	public boolean recordScore(ArrayList<SelectionRecordVO> scores)throws RemoteException;
	
	public boolean recordScoreByExcel(String filename,String courseid)throws RemoteException;
	
	public ArrayList<SelectionRecordVO> showCourseScores(String courseid)throws RemoteException;
	
	public ArrayList<SelectionRecordVO> showStudentScores(String studentid)throws RemoteException;
	
	public ArrayList<StudentVO> showCourseStudents(String courseid)throws RemoteException;
	
	public ArrayList<CourseVO> showStudentCourses(String studentid)throws RemoteException;
	
	public ArrayList<StudentVO> showCoursepreStudents(String courseid)throws RemoteException;
	
	public ArrayList<CourseVO> showStudentpreCourses(String studentid)throws RemoteException;
}
