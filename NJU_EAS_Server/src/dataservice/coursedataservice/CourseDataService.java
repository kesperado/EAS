package dataservice.coursedataservice;


import java.util.ArrayList;

import po.CoursePO;

/**
 * @author ���� :knox
 * ��˵����
 * ����ǿγ����ݲ����ӿ���
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
