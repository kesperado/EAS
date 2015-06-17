package dataservice.coursedataservice;


import java.util.ArrayList;

import po.CoursePO;

/**
 * @author 作者 :knox
 * 类说明：
 * 这个是课程数据操作接口类
 */
public interface CourseDataService {

	public String getMaxCourseID();

	public boolean publishCoures(CoursePO coursePO);

	public boolean deleteCoures(String courseid);
	
	public boolean updateCoures(CoursePO coursePO);
	
	public CoursePO queryCourseByID(String courseid);

	public ArrayList<CoursePO> queryCourseByType(String coursetype) ;
	
	public ArrayList<CoursePO> queryCourseByDepartment(String department);
	
	public ArrayList<CoursePO> queryCourseByGradeAndDepartment(String grade, String department);	
	
	public ArrayList<CoursePO> queryCourseByGradeDepartmentAndType(String grade,String department,String coursetype);
	
	public ArrayList<CoursePO> showAllCourses();
}
