package businesslogiccontroller.rteachercontroller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.selectionrecordblservice.SelectionRecordBLService;
import businesslogicservice.systeminfoblservice.SystemInfoBLService;
import businesslogicservice.userblservice.UserBLService;
import businesslogicservice.teachcourseblservice.TeachCourseBLService;
import client.ClientRunner;
import vo.CourseVO;
import vo.SelectionRecordVO;
import vo.StudentVO;
import vo.TeacherVO;
/**
 * @author ���� :knox
 * ��˵����
 * ������ο���ʦ�Ĳ��������࣬��������bl��Ĳ�ͬ�ӿ���ʵ���ض�����
 */
public class rTeacherController {

	private TeacherVO currentrTeacher = null;
	
	public rTeacherController(TeacherVO teacherVO){
		this.currentrTeacher = teacherVO;
	}
	
	public boolean changeMyPassword(String oldpassword,String newpassword){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.changeTeacherPassword(this.currentrTeacher.getID(), oldpassword, newpassword);
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
	
	public boolean completeMyInfo(String age,String hometown,String contact){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.completeTeacherInfo(this.currentrTeacher.getID(), age,hometown,contact);
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
	
	//��ȡϵͳ״̬�Գ�ʼ�����ֽ���
		public String getSystemStatus(){
			try {
				SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
				return systemInfoBLService.getSystemStatus();
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
	
	//��ʾ�ý�ʦ������Ҫ���ڵĿγ�
	public ArrayList<CourseVO> showMyCourseList(String teacherid){
		try {
			TeachCourseBLService teachCourseBLService =(TeachCourseBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/teachCourseBLService");
			return teachCourseBLService.showrTeacherCourses(teacherid);
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
	
	public CourseVO showCourseInfo(String courseid) {
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			return (courseBLService.queryCourseByID(courseid));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}
	
	//���ƿγ���Ϣ�������γ̽�ѧ��٣��̲ģ��ο��飬����ʱ��
	public boolean completeCourseInfo(String courseid,String outline,String textbook,String referencebook,String testtime){
		try {
			CourseBLService courseBLService = (CourseBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			return(courseBLService.completerCourseInfo(courseid, outline, textbook, referencebook,testtime));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
		
	}
	
	//��ʾ�γ̵�ѧ��
	public ArrayList<StudentVO> showCourseStudents(String courseid){
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return(selectionRecordBLService.showCourseStudents(courseid));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}
	
	public ArrayList<SelectionRecordVO> showCourseScores(String courseid){
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return(selectionRecordBLService.showCourseScores(courseid));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}
	
	//�Ǽǳɼ�
	public boolean recordScore(ArrayList<SelectionRecordVO> scores){
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return(selectionRecordBLService.recordScore(scores));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
		
	}
	
	//��Excel�Ǽǳɼ�
	public boolean recordScoreByExcel(String filename,String courseid){
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return(selectionRecordBLService.recordScoreByExcel(filename, courseid));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
		
	}
	
	public ArrayList<String> getMyNotices(){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			return systemInfoBLService.getMyNotices(currentrTeacher.getID());
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
	
	public TeacherVO getcurrentrTeacherVO(){
		return currentrTeacher;
	}
}
