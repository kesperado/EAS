package businesslogicservice.teachcourseblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.CourseVO;
import vo.TeacherVO;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是老师授课逻辑操作接口
 */
public interface TeachCourseBLService extends Remote{
	
	public boolean addCourserTeacher(String courseid, String rteacherid)
			throws RemoteException;

	public boolean deleteCourserTeacher(String courseid, String rteacherid)
			throws RemoteException;
	
	public ArrayList<TeacherVO> showCourserTeachers(String courseid)
			throws RemoteException;
	
	public ArrayList<CourseVO> showrTeacherCourses(String teacherid)
			throws RemoteException;

	
}
