package vo;

import java.io.Serializable;

public class PlanVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String department;
	private String details;
	private int inadmit; // 准入
	private int outadmit; // 准出

	
	public PlanVO(){
		
	}
	public PlanVO(String department,String details, int inadmit, int outadmit){
		this.department=department;
		this.details=details;
		this.inadmit = inadmit;
		this.outadmit = outadmit;
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