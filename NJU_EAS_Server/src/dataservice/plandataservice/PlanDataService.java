package dataservice.plandataservice;

import java.util.ArrayList;

import po.PlanPO;

/**
 * @author 作者 :knox
 * 类说明：
 * 这个是教学计划数据操作接口类
 */
public interface PlanDataService {

	public boolean updatePlan(PlanPO planPO);

	public PlanPO findPlan(String department);

	public ArrayList<PlanPO> showPlans();


}
