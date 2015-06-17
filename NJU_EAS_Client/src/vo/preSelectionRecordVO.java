package vo;



public class preSelectionRecordVO {

	private String courseid;
	private String studentid;
	private String rank;
	private int memberlimit;
	
	public preSelectionRecordVO(String courseid,String studentid){
		this.courseid=courseid;
		this.studentid=studentid;
	}
	
	public String getRank() {
		return rank;
	}

	public int getMemberLimit() {
		return memberlimit;
	}

	public String getCourseID() {
		return courseid;
	}
	public String getStudentID() {
		return studentid;
	}
}
