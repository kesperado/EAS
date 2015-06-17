package businesslogiccontroller.logincontroller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import vo.StudentVO;
import vo.TeacherVO;
import businesslogicservice.userblservice.UserBLService;
import client.ClientRunner;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是登陆操作控制类，用来调用bl层的不同接口以实现特定操作
 */
public class LoginController {
	
	public LoginController(){
		
	}

	public boolean teacherLogin(String teacherid,String password,String teachertype){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.teacherLogin(teacherid, password, teachertype);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public TeacherVO getTeacherInfo(String teacherid){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.queryTeacherByID(teacherid);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public boolean studentLogin(String studentid,String password){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.studentLogin(studentid, password);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public StudentVO getStudentInfo(String studentid){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.queryStudentByID(studentid);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
