package dataservice.teachcoursedataservice;

import java.util.ArrayList;


/**
 * @author 作者 :knox
 * 类说明：
 * 这个是教师授课数据操作接口类
 */
public interface TeachCourseDataService {

	public boolean addCourserTeacher(String courseid,String teacherid);
	
	public boolean deleteCourserTeacher(String courseid,String teacherid);
	
	public ArrayList<String> showCourseList(String teacherid);
	
	public ArrayList<String> showrTeacherList(String courseid);
	
}
