package businesslogic.systeminfobl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.StudentPO;
import po.TeacherPO;

import businesslogicservice.systeminfoblservice.SystemInfoBLService;
import data.systeminfodata.SystemInfoData;
import data.userdata.UserData;
import dataservice.systeminfodataservice.SystemInfoDataService;
import dataservice.userdataservice.UserDataService;
/**
 * @author ���� :knox
 * ��˵����
 * �����ϵͳ��Ϣ�߼������࣬�����˸���ϵͳ״̬����Ӻ�ɾ��ϵͳ֪ͨ��������ʾ�����ѧ�����Щ����
 */
public class SystemInfoBL extends UnicastRemoteObject implements SystemInfoBLService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemInfoBL() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	//����������
	public boolean inputStructure(String structure) throws RemoteException {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.inputStructure(structure);
	}

	@Override
	//�鿴������
	public String showStructure() throws RemoteException {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.showStructure();
	}

	@Override
	//����ϵͳ״̬
	public boolean setSystemStatus(String status) throws RemoteException {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.setSystemStatus(status);
	}

	@Override
	//��ѯϵͳ״̬
	public String getSystemStatus() throws RemoteException {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.getSystemStatus();
	}

	@Override
	//������Ϣ
	public boolean addNotice(String notice) {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		UserDataService userDataService = new UserData();
		ArrayList<TeacherPO> teacherPOs= userDataService.showAllTeachers();
		ArrayList<StudentPO> studentPOs=userDataService.showAllStudents();
		for(TeacherPO teacherPO:teacherPOs){
			systemInfoDataService.addNotice(teacherPO.getID(),notice);
		}
		for(StudentPO studentPO:studentPOs){
			systemInfoDataService.addNotice(studentPO.getID(),notice);
		}
		return true;
	}

	@Override
	//ɾ��ϵͳ��Ϣ
	public boolean deleteNotice(String userid, String notice) {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.deleteNotice(userid, notice);
	}
	
	@Override
	//����ϵͳ״̬ʱ���ϵͳ��Ϣ�б�
	public boolean emptyNoticeList(){
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.emptyNoticeList();
		
	}

	@Override
	//�õ�ĳ���û��Ŀγ���Ϣ
	public ArrayList<String> getMyNotices(String userid) {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.getUserNotices(userid);
	}

}
