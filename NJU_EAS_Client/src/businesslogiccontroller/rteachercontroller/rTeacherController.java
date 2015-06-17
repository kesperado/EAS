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
 * @author 作者 :knox
 * 类说明：
 * 这个是任课老师的操作控制类，用来调用bl层的不同接口以实现特定操作
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
	
	//获取系统状态以初始化部分界面
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
	
	//显示该教师的所需要教授的课程
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
	
	//完善课程信息：包括课程教学大纲，教材，参考书，考试时间
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
	
	//显示课程的学生
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
	
	//登记成绩
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
	
	//从Excel登记成绩
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
