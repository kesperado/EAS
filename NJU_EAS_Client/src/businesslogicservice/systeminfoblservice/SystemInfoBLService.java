package businesslogicservice.systeminfoblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是系统信息逻辑操作接口
 */
public interface SystemInfoBLService extends Remote{

	public boolean inputStructure(String structure)throws RemoteException;
	
	public String showStructure()throws RemoteException;
	
	public boolean setSystemStatus(String status)throws RemoteException;
	
	public String getSystemStatus()throws RemoteException;
	
	public boolean addNotice(String notice)throws RemoteException;
	
	public boolean deleteNotice(String userid,String notice)throws RemoteException;
	
	public boolean emptyNoticeList()throws RemoteException;
	
	public ArrayList<String> getMyNotices(String userid)throws RemoteException;
}
