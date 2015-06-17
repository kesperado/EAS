package businesslogicservice.planblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.PlanVO;
/**
 * @author ���� :knox
 * ��˵����
 * ����ǽ�ѧ�ƻ��߼������ӿ�
 */
public interface PlanBLService extends Remote{
	
	public boolean updatePlan(String department,String plan,int inadmit,int outadmit)throws RemoteException;

	public PlanVO findPlan(String department)throws RemoteException;;
	
	public ArrayList<PlanVO> showPlans()throws RemoteException;
	
}
