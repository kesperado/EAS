package po;

import vo.PlanVO;

public class PlanPO {

	private String department;
	private String details;
	private int inadmit; // 准入
	private int outadmit; // 准出

	public PlanPO() {

	}

	public PlanPO(String department, String details, int inadmit, int outadmit) {
		this.department = department;
		this.details = details;
		this.inadmit = inadmit;
		this.outadmit = outadmit;
	}

	public PlanPO(PlanVO planVO) {
		this.department = planVO.getDepartment();
		this.details = planVO.getDetails();
		this.inadmit = planVO.getInadmit();
		this.outadmit = planVO.getOutadmit();

	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getInadmit() {
		return inadmit;
	}

	public void setInadmit(int inadmit) {
		this.inadmit = inadmit;
	}

	public int getOutadmit() {
		return outadmit;
	}

	public void setOutadmit(int outadmit) {
		this.outadmit = outadmit;
	}

}
