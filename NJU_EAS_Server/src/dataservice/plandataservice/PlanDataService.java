package dataservice.plandataservice;

import java.util.ArrayList;

import po.PlanPO;

/**
 * @author ���� :knox
 * ��˵����
 * ����ǽ�ѧ�ƻ����ݲ����ӿ���
 */
public interface PlanDataService {

	public boolean updatePlan(PlanPO planPO);

	public PlanPO findPlan(String department);

	public ArrayList<PlanPO> showPlans();


}
