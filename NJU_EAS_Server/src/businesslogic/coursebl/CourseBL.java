package businesslogic.coursebl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.CoursePO;
import vo.CourseVO;
import businesslogicservice.courseblservice.CourseBLService;
import data.coursedata.CourseData;
import dataservice.coursedataservice.CourseDataService;

/**
 * @author 作者 :knox
 * 类说明：
 * 这个是 课程的具体逻辑操作类，包含了发布课程，安排课程，完善课程信息以及查询课程等等操作
 */
public class CourseBL extends UnicastRemoteObject implements CourseBLService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseBL() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	//发布课程
	public boolean publishCourse(String name, int credit, String coursetype,
			String grade, String targetdepartment, String department)
			throws RemoteException {
		CoursePO coursePO = new CoursePO(createCourseID(), name, credit, grade,
				targetdepartment, department, coursetype);
		CourseDataService courseDataService = new CourseData();
		return courseDataService.publishCoures(coursePO);
	}

	@Override
	//安排课程
	public boolean arrangeCourse(String courseid, String time,
			String classroom, int memberlimit) throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		CoursePO coursePO = courseDataService.queryCourseByID(courseid);
		ArrayList<CoursePO> coursePOs = courseDataService.showAllCourses();
		for (CoursePO somecoursePO : coursePOs) {
			if (somecoursePO.getTime() != null
					&& somecoursePO.getClassroom() != null) {
				if (time.equals(somecoursePO.getTime())
						&& classroom.equals(
								somecoursePO.getClassroom())&&(!somecoursePO.getID().equals(courseid))) {
					return false;
				}
			}
		}//如果安排的课程与已有的课程冲突
		coursePO.setTime(time);
		coursePO.setClassroom(classroom);
		coursePO.setMemberLimit(memberlimit);
		return courseDataService.updateCoures(coursePO);
	}

	@Override
	//完善课程信息
	public boolean completerCourseInfo(String courseid, String outline,
			String textbook, String referencebook, String testtime)
			throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		CoursePO coursePO = courseDataService.queryCourseByID(courseid);
		coursePO.setOutline(outline);
		coursePO.setTextbook(textbook);
		coursePO.setReferenceBook(referencebook);
		coursePO.setTestTime(testtime);
		return courseDataService.updateCoures(coursePO);
	}

	@Override
	//教务处老师更改系统状态为开始选课之后就会将相对应的一些课程改为可选的
	public boolean startSelectCourse() {
		boolean issucceed = true;
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = new ArrayList<CoursePO>();
		coursePOs.addAll(courseDataService.queryCourseByType("专业选修课"));
		coursePOs.addAll(courseDataService.queryCourseByType("通识课"));
		coursePOs.addAll(courseDataService.queryCourseByType("研讨课"));
		coursePOs.addAll(courseDataService.queryCourseByType("体育课"));
		for (CoursePO coursePO : coursePOs) {
			coursePO.setCanSelect("Yes");
			if (!courseDataService.updateCoures(coursePO)) {
				issucceed = false;
				break;
			}
		}
		return issucceed;

	}

	@Override
	// 就将这三种选修课的可退性设置为yes,意味着这三种课可以退选，也可以补选
	public boolean startQuitAndReselectCourse() {
		boolean issucceed = true;
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = new ArrayList<CoursePO>();
		coursePOs.addAll(courseDataService.queryCourseByType("专业选修课"));
		coursePOs.addAll(courseDataService.queryCourseByType("通识课"));
		coursePOs.addAll(courseDataService.queryCourseByType("研讨课"));
		for (CoursePO coursePO : coursePOs) {
			coursePO.setCanQuit("Yes");
			if (!courseDataService.updateCoures(coursePO)) {
				issucceed = false;
				break;
			}
		}
		return issucceed;
	}

	@Override
	//结束选课且结束退选课程
	public boolean finishSelectAndQuitCourse() {
		boolean issucceed = true;
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = new ArrayList<CoursePO>();
		coursePOs.addAll(courseDataService.queryCourseByType("专业选修课"));
		coursePOs.addAll(courseDataService.queryCourseByType("通识课"));
		coursePOs.addAll(courseDataService.queryCourseByType("研讨课"));
		coursePOs.addAll(courseDataService.queryCourseByType("体育课"));
		for (CoursePO coursePO : coursePOs) {
			coursePO.setCanSelect("No");
			coursePO.setCanQuit("No");
			if (!courseDataService.updateCoures(coursePO)) {
				issucceed = false;
				break;
			}
		}
		return issucceed;

	}

	@Override
	//删除课程
	public boolean deleteCourse(String courseid) throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		return courseDataService.deleteCoures(courseid);
	}

	@Override
	//根据课程类型查询课程
	public ArrayList<CourseVO> queryCourseByType(String coursetype) {
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = courseDataService
				.queryCourseByType(coursetype);
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		for (CoursePO coursePO : coursePOs) {
			courseVOs.add(new CourseVO(coursePO));
		}
		return courseVOs;
	}

	@Override
	//根据课程开设院系查询课程
	public ArrayList<CourseVO> queryCourseByDepartment(String department)
			throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = courseDataService.queryCourseByDepartment(department);
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		for (CoursePO coursePO : coursePOs) {
			courseVOs.add(new CourseVO(coursePO));
		}
		return courseVOs;
	}
	
	@Override
	//根据年级和开设院系查询课程
	public ArrayList<CourseVO> queryCourseByGradeAndDepartment(String grade,
			String department) throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = courseDataService
				.queryCourseByGradeAndDepartment(grade, department);
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		for (CoursePO coursePO : coursePOs) {
			courseVOs.add(new CourseVO(coursePO));
		}
		return courseVOs;
	}

	@Override
	//根据年级，开设院系和课程类型查询课程
	public ArrayList<CourseVO> queryCourseByGradeDepartmentAndType(
			String grade, String department, String coursetype)
			throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = courseDataService
				.queryCourseByGradeDepartmentAndType(grade, department,
						coursetype);
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		for (CoursePO coursePO : coursePOs) {
			courseVOs.add(new CourseVO(coursePO));
		}
		return courseVOs;
	}

	@Override
	//根据课程号查询课程详细信息
	public CourseVO queryCourseByID(String courseid) throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		return new CourseVO(courseDataService.queryCourseByID(courseid));
	}

	//系统自动生成课程号，为系统中最大课程号+1
	private String createCourseID() {
		CourseDataService courseDataService = new CourseData();
		String maxID = courseDataService.getMaxCourseID();
		int newCourseID = Integer.parseInt(maxID) + 1;
		return "" + newCourseID;
	}



}
