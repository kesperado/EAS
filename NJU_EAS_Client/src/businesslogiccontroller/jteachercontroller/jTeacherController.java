package businesslogiccontroller.jteachercontroller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.planblservice.PlanBLService;
import businesslogicservice.selectionrecordblservice.SelectionRecordBLService;
import businesslogicservice.systeminfoblservice.SystemInfoBLService;
import businesslogicservice.userblservice.UserBLService;
import client.ClientRunner;
import vo.CourseVO;
import vo.PlanVO;
import vo.SelectionRecordVO;
import vo.StudentVO;
import vo.TeacherVO;

/**
 * @author 作者 :knox
 * 类说明：
 * 这个是教务处老师的操作控制类，用来调用bl层的不同接口以实现特定操作
 */
public class jTeacherController {

	private TeacherVO currentjTeacher = null;
	
	public jTeacherController(TeacherVO teacherVO){
		this.currentjTeacher = teacherVO;
	}
	
	public boolean changeMyPassword(String oldpassword,String newpassword){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.changeTeacherPassword(this.currentjTeacher.getID(), oldpassword, newpassword);
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
			return userBLService.completeTeacherInfo(this.currentjTeacher.getID(), age,hometown,contact);
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
	//系统状态为“0”:开始选课
	public boolean startSelectCourse(){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			CourseBLService courseBLService = (CourseBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			ArrayList<StudentVO> studentVOs = new ArrayList<StudentVO>();
			ArrayList<CourseVO> courseVOs1 = courseBLService.queryCourseByType("专业指选课");
			ArrayList<CourseVO> courseVOs2 = courseBLService.queryCourseByType("公共必修课");
			//对于专业指选课和公共必修课直接按照课程当初规定的年级和院系进行自行安排，而不需要学生进行选择
			for(CourseVO courseVO:courseVOs1){
				studentVOs = userBLService.queryStudentByGradeAndDepartment(courseVO.getGrade(), courseVO.getTargetDepartment());
				for(StudentVO studentVO:studentVOs){
					selectionRecordBLService.selectCourse(courseVO.getID(), studentVO.getID());
				}
			}
			for(CourseVO courseVO:courseVOs2){
				studentVOs = userBLService.queryStudentByGradeAndDepartment(courseVO.getGrade(), courseVO.getTargetDepartment());
				for(StudentVO studentVO:studentVOs){
					selectionRecordBLService.selectCourse(courseVO.getID(), studentVO.getID());
				}
			}
			//对于其他课程，当教务处老师将系统的状态设置为可以开始选课时，就把这些课程的可选性设置为可选
			return courseBLService.startSelectCourse()&&systemInfoBLService.setSystemStatus("0")&&systemInfoBLService.emptyNoticeList()&&systemInfoBLService.addNotice("选课已经开始！");
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
	
	//系统状态为“1”:结束选课，并开始补退选,这个操作将会引发对预选选课记录列表中的记录进行抽签筛选，并添加到正式选课记录列表中
	public boolean startQuitAndReselectCourse(){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			CourseBLService courseBLService = (CourseBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return courseBLService.startQuitAndReselectCourse()&&selectionRecordBLService.selectionDraw()&&systemInfoBLService.setSystemStatus("1")&&systemInfoBLService.emptyNoticeList()&&systemInfoBLService.addNotice("选课已经结束，补选和退选已开始！");
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
	
	//系统状态为“2”:结束选课和退选
	public boolean finishSelectAndQuitCourse(){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			CourseBLService courseBLService = (CourseBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			return courseBLService.finishSelectAndQuitCourse()&&systemInfoBLService.setSystemStatus("2")&&systemInfoBLService.emptyNoticeList()&&systemInfoBLService.addNotice("选课结束，如若对课程还有意见请咨询教务处老师");
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
	
	public boolean RegisterTeacher(String name,String teachertype,String department){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.RegisterTeacher(name,teachertype,department);
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
	
	public boolean RegisterStudent(String name,String grade,String department){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.RegisterStudent( name, grade, department);
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
	
	public boolean deleteTeacher(String teacherid){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.deleteTeacher(teacherid);
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
	
	public boolean deleteStudent(String studentid){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.deleteStudent(studentid);
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
	
	public ArrayList<TeacherVO> showTeacherList(String department){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.queryTeacherByDepartment(department);
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
	
	public ArrayList<CourseVO> showCourseList(String department){
		try {
			CourseBLService courseBLService = (CourseBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			return courseBLService.queryCourseByDepartment(department);
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
	
	public ArrayList<StudentVO> showStudentList(String department){
		try {
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.queryStudentByDepartment(department);
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
	
	public TeacherVO showTeacherInfo (String teacherid){
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
	
	public CourseVO  showCourseInfo(String courseid){
		try {
			CourseBLService courseBLService = (CourseBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			return(courseBLService.queryCourseByID(courseid));
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
	
	public StudentVO showStudentInfo(String studentid){
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
	
	public boolean inputStructure(String structure){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			return systemInfoBLService.inputStructure(structure);
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
	
	public String showStructure(){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			return systemInfoBLService.showStructure();
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
	
	public PlanVO showDepartmentPlan(String department){
		try {
			PlanBLService planBLService = (PlanBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/planBLService");
			return planBLService.findPlan(department);
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
	
	//增加系统通知
	public boolean addNotice(String notice){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			return systemInfoBLService.addNotice(notice);
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
	
	//得到自己的通知
	public ArrayList<String> getMyNotices(){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			return systemInfoBLService.getMyNotices(currentjTeacher.getID());
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
	
	public TeacherVO getcurrentjTeacherVO(){
		return currentjTeacher;
	}
}
