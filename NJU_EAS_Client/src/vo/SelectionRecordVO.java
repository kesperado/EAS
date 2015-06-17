package vo;

import java.io.Serializable;

public class SelectionRecordVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseid;
	private String studentid;
	private int score;

	public SelectionRecordVO(String courseid,String studentid){
		this.courseid=courseid;
		this.studentid=studentid;
	}
	
	public  SelectionRecordVO(String courseid,String studentid,int score){
		this.courseid=courseid;
		this.studentid=studentid;
		this.score = score;
	}
	
	public String getCourseID() {
		return courseid;
	}
	public String getStudentID() {
		return studentid;
	}
	public int getScore() {
		return score;
	}
	
	

}
