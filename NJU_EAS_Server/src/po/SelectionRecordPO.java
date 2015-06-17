package po;

import vo.SelectionRecordVO;

public class SelectionRecordPO {

	private String courseid;
	private String studentid;
	private int score;
	
	public SelectionRecordPO(){
		
	}
	
	public SelectionRecordPO(String courseid,String studentid){
		this.courseid=courseid;
		this.studentid=studentid;
	}
	
	public SelectionRecordPO(String courseid,String studentid,int score){
		this.courseid=courseid;
		this.studentid=studentid;
		this.score = score;
	}
	
	public SelectionRecordPO(SelectionRecordVO selectionRecordVO){
		this.courseid=selectionRecordVO.getCourseID();
		this.studentid=selectionRecordVO.getStudentID();
		this.score = selectionRecordVO.getScore();
	}
	
	public String getCourseID() {
		return courseid;
	}
	public void setCourseID(String courseid) {
		this.courseid = courseid;
	}
	public String getStudentID() {
		return studentid;
	}
	public void setStudentID(String studentid) {
		this.studentid = studentid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
