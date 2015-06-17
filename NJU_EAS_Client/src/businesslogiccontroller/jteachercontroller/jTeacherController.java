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
 * @author ���� :knox
 * ��˵����
 * ����ǽ�����ʦ�Ĳ��������࣬��������bl��Ĳ�ͬ�ӿ���ʵ���ض�����
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
	//ϵͳ״̬Ϊ��0��:��ʼѡ��
	public boolean startSelectCourse(){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			CourseBLService courseBLService = (CourseBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			UserBLService userBLService = (UserBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			ArrayList<StudentVO> studentVOs = new ArrayList<StudentVO>();
			ArrayList<CourseVO> courseVOs1 = courseBLService.queryCourseByType("רҵָѡ��");
			ArrayList<CourseVO> courseVOs2 = courseBLService.queryCourseByType("�������޿�");
			//����רҵָѡ�κ͹������޿�ֱ�Ӱ��տγ̵����涨���꼶��Ժϵ�������а��ţ�������Ҫѧ������ѡ��
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
			//���������γ̣���������ʦ��ϵͳ��״̬����Ϊ���Կ�ʼѡ��ʱ���Ͱ���Щ�γ̵Ŀ�ѡ������Ϊ��ѡ
			return courseBLService.startSelectCourse()&&systemInfoBLService.setSystemStatus("0")&&systemInfoBLService.emptyNoticeList()&&systemInfoBLService.addNotice("ѡ���Ѿ���ʼ��");
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
	
	//ϵͳ״̬Ϊ��1��:����ѡ�Σ�����ʼ����ѡ,�����������������Ԥѡѡ�μ�¼�б��еļ�¼���г�ǩɸѡ������ӵ���ʽѡ�μ�¼�б���
	public boolean startQuitAndReselectCourse(){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			CourseBLService courseBLService = (CourseBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return courseBLService.startQuitAndReselectCourse()&&selectionRecordBLService.selectionDraw()&&systemInfoBLService.setSystemStatus("1")&&systemInfoBLService.emptyNoticeList()&&systemInfoBLService.addNotice("ѡ���Ѿ���������ѡ����ѡ�ѿ�ʼ��");
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
	
	//ϵͳ״̬Ϊ��2��:����ѡ�κ���ѡ
	public boolean finishSelectAndQuitCourse(){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			CourseBLService courseBLService = (CourseBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			return courseBLService.finishSelectAndQuitCourse()&&systemInfoBLService.setSystemStatus("2")&&systemInfoBLService.emptyNoticeList()&&systemInfoBLService.addNotice("ѡ�ν����������Կγ̻����������ѯ������ʦ");
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
	
	//����ϵͳ֪ͨ
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
	
	//�õ��Լ���֪ͨ
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
