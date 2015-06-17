package dataservice.teachcoursedataservice;

import java.util.ArrayList;


/**
 * @author ���� :knox
 * ��˵����
 * ����ǽ�ʦ�ڿ����ݲ����ӿ���
 */
public interface TeachCourseDataService {

	public boolean addCourserTeacher(String courseid,String teacherid);
	
	public boolean deleteCourserTeacher(String courseid,String teacherid);
	
	public ArrayList<String> showCourseList(String teacherid);
	
	public ArrayList<String> showrTeacherList(String courseid);
	
}
