package businesslogiccontroller.studentcontroller;

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
 * 这个是学生的操作控制类，用来调用bl层的不同接口以实现特定操作
 */
public class StudentController {

	private StudentVO currentStudent = null;

	public StudentController(StudentVO studentVO) {
		this.currentStudent = studentVO;
	}

	public boolean changeMyPassword(String oldpassword, String newpassword) {
		try {
			UserBLService userBLService = (UserBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.changeStudentPassword(
					this.currentStudent.getID(), oldpassword, newpassword);
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

	public boolean completeMyInfo(String age, String hometown, String contact) {
		try {
			UserBLService userBLService = (UserBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.completeStudentInfo(
					this.currentStudent.getID(), age, hometown, contact);
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

	// 获取系统状态以初始化部分界面
	public String getSystemStatus() {
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
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

	// 学生选择体育课，当人数小于限制人数时，直接选择。
	// 有一点还要想：如何让界面在学生选择了一门体育课以后就一直显示他已经选择了该体育课，而不再显示选课界面之类的。。
	// 这应该是showCourseList的事情吧，得适应界面。
	public boolean selectSportsCourse(String courseid, String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			CourseVO courseVO = courseBLService.queryCourseByID(courseid);
			int currentMember = selectionRecordBLService.showCourseStudents(
					courseid).size();
			if (currentMember < courseVO.getMemberLimit()) {
				return (selectionRecordBLService.selectCourse(courseid,
						studentid));
			}
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

	// 学生选择选修课，先进入预选队列。
	public boolean selectOptionalCourse(String courseid, String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			CourseVO courseVO = courseBLService.queryCourseByID(courseid);
			return selectionRecordBLService.preselectCourse(courseid,
					studentid, getMySelectRank(), courseVO.getMemberLimit());

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

	// 只要满足人数少于人数上限这个条件，就可以即时选中
	public boolean reselectOptionalCourse(String courseid, String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			CourseVO courseVO = courseBLService.queryCourseByID(courseid);
			if (courseVO.getMemberLimit() > selectionRecordBLService
					.showCourseStudents(courseid).size())
				return selectionRecordBLService.selectCourse(courseid,
						studentid);

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

	// 成功选中某门课之后退选 应该只能列出能够被退选的课程
	public boolean quitCourse(String courseid, String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.quitCourse(courseid, studentid));
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

	// 退选预选课表中的课
	public boolean quitpreCourse(String courseid, String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.quitpreCourse(courseid, studentid));
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
	
	// 评价某门课程
	public boolean assessCourse(String courseid, int score) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.assessCourse(courseid, score));
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

	// 选跨专业课时需要通过这个方法显示可以选择的课的列表
	public ArrayList<CourseVO> showCourseList(String grade, String department,
			String coursetype) {
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			return (courseBLService.queryCourseByGradeDepartmentAndType(grade,
					department, coursetype));
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

	// 选通识、研讨和体育课时刻调用这个方法，（没有年级和院系之分）
	public ArrayList<CourseVO> showCourseList(String coursetype) {
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			return courseBLService.queryCourseByType(coursetype);
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

	// 显示课程详细信息
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

	//显示课程的任课教师
	public ArrayList<TeacherVO> showCourserTeachers(String courseid) {
		try {
			TeachCourseBLService teachCourseBLService = (TeachCourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/teachCourseBLService");
			return (teachCourseBLService.showCourserTeachers(courseid));
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

	//显示课程的学生
	public ArrayList<StudentVO> showCourseStudents(String courseid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return selectionRecordBLService.showCourseStudents(courseid);
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
	
	//根据课程号查询课程所有成绩
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

	//显示预选了某课程的学生
	public ArrayList<StudentVO> showCoursepreStudents(String courseid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return selectionRecordBLService.showCoursepreStudents(courseid);
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

	// 显示学生已选择的课程列表
	public ArrayList<CourseVO> showMyCourseList(String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.showStudentCourses(studentid));
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

	//显示自己预选的选课列表
	public ArrayList<CourseVO> showMypreCourseList(String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.showStudentpreCourses(studentid));
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

	// 显示学生的成绩，通过返回这个链表，并通过selectionrecord里的每个课程的id得到其名字，那么就可以将id,name和score显示在列表上了
	public ArrayList<SelectionRecordVO> showMyScores(String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.showStudentScores(studentid));
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

	// Arraylist里的学分按照专业指选课，专业选修课，通识课+研讨课，公共必修课（包含体育课）这四大块来算。
	public ArrayList<Integer> showMyCredits(String studentid) {
		ArrayList<Integer> creditList = new ArrayList<Integer>();
		int credit1 = 0;
		int credit2 = 0;
		int credit3 = 0;
		int credit4 = 0;
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			ArrayList<SelectionRecordVO> selectionRecordVOs = selectionRecordBLService
					.showStudentScores(studentid);
			for (SelectionRecordVO selectionRecordVO : selectionRecordVOs) {
				if (selectionRecordVO.getScore() >= 60) {
					CourseVO courseVO = courseBLService
							.queryCourseByID(selectionRecordVO.getCourseID());
					if (courseVO.getCourseType().equals("专业指选课")) {
						credit1 += courseVO.getCredit();
					} else if (courseVO.getCourseType().equals("专业选修课")) {
						credit2 += courseVO.getCredit();
					} else if (courseVO.getCourseType().equals("通识课")
							|| courseVO.getCourseType().equals("研讨课")) {
						credit3 += courseVO.getCredit();
					} else {
						credit4 += courseVO.getCredit();
					}
				}
			}
			creditList.add(credit1);
			creditList.add(credit2);
			creditList.add(credit3);
			creditList.add(credit4);
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
		return creditList;
	}


	// 学生内置一个private的获得他的选修课选课优先级的方法，用于上面的selectOptionalCourse方法中。private是为了保护这个属性的隐秘性
	private String getMySelectRank() {
		ArrayList<Integer> creditList = showMyCredits(getcurrentStudent()
				.getID());
		int optionalcredit = creditList.get(2);
		if (getcurrentStudent().getGrade().trim().equals("本科一年级")) {
			return "middle";
		} else if (getcurrentStudent().getGrade().trim().equals("本科二年级")) {
			if (optionalcredit < 4) {
				return "high";
			} else if (optionalcredit > 6) {
				return "low";
			} else {
				return "middle";
			}
		} else if (getcurrentStudent().getGrade().trim().equals("本科三年级")) {
			if (optionalcredit < 9) {
				return "high";
			} else if (optionalcredit > 11) {
				return "low";
			} else {
				return "middle";
			}
		} else  if (getcurrentStudent().getGrade().trim().equals("本科四年级")){ 
			if (optionalcredit < 14) {
				return "high";
			} else {
				return "low";
			}
		}else{
			return "middle";
		}
	}
	
	public ArrayList<String> getMyNotices(){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			return systemInfoBLService.getMyNotices(currentStudent.getID());
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

	public StudentVO getcurrentStudent() {
		return currentStudent;
	}

}
