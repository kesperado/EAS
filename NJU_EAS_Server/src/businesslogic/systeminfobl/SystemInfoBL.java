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
 * @author 作者 :knox
 * 类说明：
 * 这个是系统信息逻辑操作类，包含了更改系统状态，添加和删除系统通知，输入显示整体教学框架这些操作
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
	//输入整体框架
	public boolean inputStructure(String structure) throws RemoteException {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.inputStructure(structure);
	}

	@Override
	//查看整体框架
	public String showStructure() throws RemoteException {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.showStructure();
	}

	@Override
	//更改系统状态
	public boolean setSystemStatus(String status) throws RemoteException {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.setSystemStatus(status);
	}

	@Override
	//查询系统状态
	public String getSystemStatus() throws RemoteException {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.getSystemStatus();
	}

	@Override
	//增加消息
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
	//删除系统消息
	public boolean deleteNotice(String userid, String notice) {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.deleteNotice(userid, notice);
	}
	
	@Override
	//更改系统状态时清空系统消息列表
	public boolean emptyNoticeList(){
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.emptyNoticeList();
		
	}

	@Override
	//得到某个用户的课程信息
	public ArrayList<String> getMyNotices(String userid) {
		SystemInfoDataService systemInfoDataService = new SystemInfoData();
		return systemInfoDataService.getUserNotices(userid);
	}

}
