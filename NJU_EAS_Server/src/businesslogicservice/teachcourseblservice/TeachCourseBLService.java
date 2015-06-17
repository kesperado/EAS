package businesslogicservice.teachcourseblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.CourseVO;
import vo.TeacherVO;
/**
 * @author ���� :knox
 * ��˵����
 * �������ʦ�ڿ��߼������ӿ�
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
