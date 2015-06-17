package dataservice.systeminfodataservice;

import java.util.ArrayList;

/**
 * @author ���� :knox
 * ��˵����
 * �����ϵͳ��Ϣ���ݲ����ӿ���
 */
public interface SystemInfoDataService {

	public boolean inputStructure(String structure);
	
	public String showStructure();
	
	public boolean setSystemStatus(String status);
	
	public String getSystemStatus();
	
	public boolean addNotice(String userid, String notice);
	
	public boolean deleteNotice(String userid, String notice);
	
	public boolean emptyNoticeList();
	
	public ArrayList<String> getUserNotices(String userid);
	
}
