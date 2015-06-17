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
 * @author ���� :knox
 * ��˵����
 * ����ǽ�ѧ�ƻ��߼������࣬�����˸��¡��鿴��ѧ�ƻ���Щ����
 */
public class PlanBL extends UnicastRemoteObject implements PlanBLService{
	
	
	private static final long serialVersionUID = 1L;

	public PlanBL() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	//���½�ѧ�ƻ�
	public boolean updatePlan(String department, String plan, int inadmit,
			int outadmit) throws RemoteException {
		PlanDataService planDataService = new PlanData();
		PlanPO planPO=new PlanPO(department, plan, inadmit, outadmit);
		return planDataService.updatePlan(planPO);
	}

	@Override
	//����Ժϵ��ѯ��ѧ�ƻ�
	public PlanVO findPlan(String department) {
		PlanDataService planDataService = new PlanData();
		return new PlanVO(planDataService.findPlan(department));
	}

	@Override
	//��ʾ����Ժϵ�Ľ�ѧ�ƻ�
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
