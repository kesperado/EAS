package dataservice.selectionrecorddataservice;

import java.util.ArrayList;

import po.SelectionRecordPO;
import po.preSelectionRecordPO;

/**
 * @author 作者 :knox
 * 类说明：
 * 这个是选课记录数据操作接口类
 */
public interface SelectionRecordDataService {

	public boolean addSelectionRecord(SelectionRecordPO selectionRecordPO);
	
	public boolean deleteSelectionRecord(String courseid,String studentid);
	
	public boolean updateSelectionRecord(SelectionRecordPO selectionRecordPO);
	
	public SelectionRecordPO findSelectionRecord(String courseid,String studentid);
	
	public ArrayList<SelectionRecordPO> showCourseStudents(String courseid);
	
	public ArrayList<SelectionRecordPO> showStudentCourses(String studentid);
	
	public boolean addpreSelectionRecord(preSelectionRecordPO preselectionRecordPO);
	
	public boolean deletepreSelectionRecord(String courseid,String studentid);
	
	public boolean emptypreSeletionRecord();
	
	public preSelectionRecordPO findpreSelectionRecord(String courseid,String studentid);
	
	public ArrayList<preSelectionRecordPO> showCoursepreStudents(String courseid);
	
	public ArrayList<preSelectionRecordPO> showStudentpreCourses(String studentid);
	
	public boolean addCourseAssessment(String courseid,int score);
	
	public ArrayList<Integer> showCourseAssessment(String courseid);

}
