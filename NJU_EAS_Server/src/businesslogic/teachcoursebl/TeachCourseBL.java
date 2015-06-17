package businesslogic.teachcoursebl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import vo.CourseVO;
import vo.TeacherVO;
import businesslogicservice.teachcourseblservice.TeachCourseBLService;
import data.coursedata.CourseData;
import data.userdata.UserData;
import data.teachcoursedata.TeachCourseData;

import dataservice.coursedataservice.CourseDataService;
import dataservice.userdataservice.UserDataService;
import dataservice.teachcoursedataservice.TeachCourseDataService;

/**
 * @author ���� :knox
 * ��˵����
 * �������ʦ�ڿ��߼������࣬����������ɾ�����鿴�γ̽�ʦ�Ͳ鿴��ʦ�γ���Щ����
 */
public class TeachCourseBL extends UnicastRemoteObject implements TeachCourseBLService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeachCourseBL() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	//����γ̽�ʦ
	public boolean addCourserTeacher(String courseid, String rteacherid)
			throws RemoteException {
		TeachCourseDataService teachCourseDataService = new TeachCourseData();
		ArrayList<TeacherVO> teacherVOs = showCourserTeachers(courseid);
		for(TeacherVO teacherVO:teacherVOs){
			if(teacherVO.getID().equals(rteacherid)){
				return false;
			}
		}
		return teachCourseDataService.addCourserTeacher(courseid, rteacherid);
	}

	@Override
	//ɾ���γ̽�ʦ
	public boolean deleteCourserTeacher(String courseid, String rteacherid)
			throws RemoteException {
		TeachCourseDataService teachCourseDataService = new TeachCourseData();
		return teachCourseDataService.deleteCourserTeacher(courseid, rteacherid);
	}

	@Override
	//�鿴�γ̽�ʦ
	public ArrayList<TeacherVO> showCourserTeachers(String courseid)
			throws RemoteException {
		TeachCourseDataService teachCourseDataService = new TeachCourseData();
		UserDataService userDataService = new UserData();
		ArrayList<String> teacheridList = teachCourseDataService.showrTeacherList(courseid);
		ArrayList<TeacherVO> teacherVOs = new ArrayList<TeacherVO>();
		for(String teacherid:teacheridList){
			teacherVOs.add(new TeacherVO(userDataService.queryTeacherByID(teacherid)));
		}
		return teacherVOs;
	}

	@Override
	//�鿴��ʦ�γ�
	public ArrayList<CourseVO> showrTeacherCourses(String teacherid)
			throws RemoteException {
		TeachCourseDataService teachCourseDataService = new TeachCourseData();
		CourseDataService courseDataService = new CourseData();
		ArrayList<String> courseidList = teachCourseDataService.showCourseList(teacherid);
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		for(String courseid:courseidList){
			courseVOs.add(new CourseVO(courseDataService.queryCourseByID(courseid)));
		}
		return courseVOs;
	}

}
