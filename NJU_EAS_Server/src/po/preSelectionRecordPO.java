package po;

import vo.preSelectionRecordVO;

public class preSelectionRecordPO {

	private String courseid;
	private String studentid;
	private String rank;
	private int memberlimit;
	
	public preSelectionRecordPO(){
		
	}
	
	public preSelectionRecordPO(String courseid,String studentid){
		this.courseid=courseid;
		this.studentid=studentid;
	}
	
	public preSelectionRecordPO(String courseid,String studentid,String rank, int memberlimit){
		this.courseid=courseid;
		this.studentid=studentid;
		this.rank=rank;
		this.memberlimit=memberlimit;
	}
	
	public preSelectionRecordPO(preSelectionRecordVO preselectionRecordVO){
		this.courseid=preselectionRecordVO.getCourseID();
		this.studentid=preselectionRecordVO.getStudentID();
		this.rank=preselectionRecordVO.getRank();
		this.memberlimit=preselectionRecordVO.getMemberLimit();
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
	
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getMemberLimit() {
		return memberlimit;
	}

	public void setMemberLimit(int memberlimit) {
		this.memberlimit = memberlimit;
	}


}
