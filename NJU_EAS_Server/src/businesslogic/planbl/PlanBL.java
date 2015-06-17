package businesslogic.planbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.PlanPO;
import vo.PlanVO;

import businesslogicservice.planblservice.PlanBLService;
import data.plandata.PlanData;
import dataservice.plandataservice.PlanDataService;

/**
 * @author 作者 :knox
 * 类说明：
 * 这个是教学计划逻辑操作类，包含了更新、查看教学计划这些操作
 */
public class PlanBL extends UnicastRemoteObject implements PlanBLService{
	
	
	private static final long serialVersionUID = 1L;

	public PlanBL() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	//更新教学计划
	public boolean updatePlan(String department, String plan, int inadmit,
			int outadmit) throws RemoteException {
		PlanDataService planDataService = new PlanData();
		PlanPO planPO=new PlanPO(department, plan, inadmit, outadmit);
		return planDataService.updatePlan(planPO);
	}

	@Override
	//根据院系查询教学计划
	public PlanVO findPlan(String department) {
		PlanDataService planDataService = new PlanData();
		return new PlanVO(planDataService.findPlan(department));
	}

	@Override
	//显示所有院系的教学计划
	public ArrayList<PlanVO> showPlans() throws RemoteException {
		PlanDataService planDataService = new PlanData();
		ArrayList<PlanPO> planPOs = planDataService.showPlans();
		ArrayList<PlanVO> planVOs = new ArrayList<PlanVO>();
		for(PlanPO planPO:planPOs){
			planVOs.add(new PlanVO(planPO));
		}
		return planVOs;
	}
}
