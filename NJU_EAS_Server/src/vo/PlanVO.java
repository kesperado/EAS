package vo;

import java.io.Serializable;

import po.PlanPO;

public class PlanVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String department;
	private String details;
	private int inadmit; // ׼��
	private int outadmit; // ׼��

	
	public PlanVO(){
		
	}
	public PlanVO(String department,String details, int inadmit, int outadmit){
		this.department=department;
		this.details=details;
		this.inadmit = inadmit;
		this.outadmit = outadmit;
    }
	
	public PlanVO(PlanPO planPO) {
		this.department = planPO.getDepartment();
		this.details = planPO.getDetails();
		this.inadmit = planPO.getInadmit();
		this.outadmit = planPO.getOutadmit();

	}

	public int getInadmit() {
		return inadmit;
	}
	public int getOutadmit() {
		return outadmit;
	}
	public String getDepartment(){
		return department;
	}
	public String getDetails(){
		return details;
	}

}