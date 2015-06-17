package vo;

import java.io.Serializable;

import po.SelectionRecordPO;

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
	
	public SelectionRecordVO(SelectionRecordPO selectionRecordPO){
		this.courseid = selectionRecordPO.getCourseID();
		this.studentid = selectionRecordPO.getStudentID();
		this.score = selectionRecordPO.getScore();
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
