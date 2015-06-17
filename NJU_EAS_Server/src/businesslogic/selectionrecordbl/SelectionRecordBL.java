package businesslogic.selectionrecordbl;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.CoursePO;
import po.SelectionRecordPO;
import po.preSelectionRecordPO;
import vo.CourseVO;
import vo.SelectionRecordVO;
import vo.StudentVO;
import businesslogicservice.selectionrecordblservice.SelectionRecordBLService;
import data.coursedata.CourseData;
import data.selectionrecorddata.SelectionRecordData;
import data.userdata.UserData;
import dataservice.coursedataservice.CourseDataService;
import dataservice.selectionrecorddataservice.SelectionRecordDataService;
import dataservice.userdataservice.UserDataService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是选课逻辑操作类，包含了直接选择课程，预选课程，退选课程，退选预选课程，抽签，查询等等操作
 */
public class SelectionRecordBL extends UnicastRemoteObject  implements SelectionRecordBLService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
	}
	public SelectionRecordBL() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	//这个选课方法是成功选课的意思，只进行最基本的检验：课表里面有没有已经选择过的课程
	//这个选课方法一开始由 教务处老师点击开始选课按钮后，所有指选和必修课自动添加到所有目标学生课表里的时候会调用
	//体育选课直选时会调用，调用判断人数的方法在controller里面，所以不需要在这里判断
	//初步选课结束时，将预选课表里面的选课记录筛选成功时，也会调用这个方法将选课记录正式添加到正式选课列表里
	//补选时采用直选时也会调用这个方法
	public boolean selectCourse(String courseid, String studentid)
			throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		if(selectionRecordDataService.findSelectionRecord(courseid, studentid)==null){
			return selectionRecordDataService.addSelectionRecord(new SelectionRecordPO(courseid, studentid));
		}
		else{
			return false;
		}
	}

	@Override
	//预选课程
	//学生学期选课选择通识研讨和跨专业选修课的时候会调用这个方法
	public boolean preselectCourse(String courseid,String studentid,String rank,int memberlimit)throws RemoteException{
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		if(selectionRecordDataService.findpreSelectionRecord(courseid, studentid)==null){
			return selectionRecordDataService.addpreSelectionRecord(new preSelectionRecordPO(courseid, studentid,rank,memberlimit));
		}
		else{
			return false;
		}
	}
	
	//这个是直接退选，要求在界面在列出可退选的列表的时候只显示退选属性为可退选时才行
	@Override
	public boolean quitCourse(String courseid, String studentid)
			throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		CourseDataService courseDataService=new CourseData();
		CoursePO coursePO = courseDataService.queryCourseByID(courseid);
		if(coursePO.getCanQuit().equals("Yes")){
			return selectionRecordDataService.deleteSelectionRecord(courseid, studentid);
		}else{
			return false;
		}
	}
	
	//删除预选课表中的选课记录
	@Override
	public boolean quitpreCourse(String courseid,String studentid)throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		return selectionRecordDataService.deletepreSelectionRecord(courseid, studentid);
		
	}
	
	//学生无记名评价课程
	@Override
	public boolean assessCourse(String courseid, int score)
			throws RemoteException {
		// TODO Auto-generated method stub
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		return selectionRecordDataService.addCourseAssessment(courseid, score);

	}
	
	//查看课程的评价
	@Override
	public double showCourseAssess(String courseid) throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		ArrayList<Integer> assessments = selectionRecordDataService.showCourseAssessment(courseid);
		double average=0;
		for(int a: assessments){
			average+=a;
		}
		if(assessments.size()!=0){
			average/=assessments.size();
		}
		return average;
	}

	
	//结束初选课时，进行抽签
	@Override
	public boolean selectionDraw()throws RemoteException{
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = new ArrayList<CoursePO>();
		coursePOs.addAll(courseDataService.queryCourseByType("专业选修课"));
		coursePOs.addAll(courseDataService.queryCourseByType("通识课"));
		coursePOs.addAll(courseDataService.queryCourseByType("研讨课"));
		for(CoursePO coursePO :coursePOs){
			ArrayList<preSelectionRecordPO> preSelectionRecordPOs = selectionRecordDataService.showCoursepreStudents(coursePO.getID());
			if(coursePO.getMemberLimit()>=preSelectionRecordPOs.size()){  //如果课程的人数上限大于预选课列表中的选课记录也即课程现有的选课人数，那么就将所有记录加入正式列表中
				for(preSelectionRecordPO preSelectionRecordPO: preSelectionRecordPOs){
					selectionRecordDataService.addSelectionRecord(new SelectionRecordPO(preSelectionRecordPO.getCourseID(), preSelectionRecordPO.getStudentID()));
				}
			}else{  //将预选列表中的学生根据不同的优先级放入不同的列表中
				ArrayList<preSelectionRecordPO> tobeselect=new ArrayList<preSelectionRecordPO>();
				ArrayList<preSelectionRecordPO> high = new ArrayList<preSelectionRecordPO>();
				ArrayList<preSelectionRecordPO> middle = new ArrayList<preSelectionRecordPO>();
				ArrayList<preSelectionRecordPO> low = new ArrayList<preSelectionRecordPO>();
				for(preSelectionRecordPO preSelectionRecordPO: preSelectionRecordPOs){
					if(preSelectionRecordPO.getRank().equals("high")){
						high.add(preSelectionRecordPO);
					}else if(preSelectionRecordPO.getRank().equals("middle")){
						middle.add(preSelectionRecordPO);
					}else if(preSelectionRecordPO.getRank().equals("low")){
						low.add(preSelectionRecordPO);
					}
				}
				tobeselect.addAll(high);
				tobeselect.addAll(high);
				tobeselect.addAll(high);
				tobeselect.addAll(high);
				tobeselect.addAll(high);
				tobeselect.addAll(middle);
				tobeselect.addAll(middle);
				tobeselect.addAll(middle);
				tobeselect.addAll(low);                  //使被筛选数组中高优先级的中标率为5/8，中的为3/8，低的为1/8
				int count=0;
				while(count<coursePO.getMemberLimit()){
					preSelectionRecordPO preSelectionRecordPO=tobeselect.get((int)(Math.random()*(tobeselect.size())));
					if(selectionRecordDataService.findSelectionRecord(preSelectionRecordPO.getCourseID(), preSelectionRecordPO.getStudentID())==null){
						selectionRecordDataService.addSelectionRecord(new SelectionRecordPO(preSelectionRecordPO.getCourseID(), preSelectionRecordPO.getStudentID()));
						count++;
					}
				}
			}
		}
		selectionRecordDataService.emptypreSeletionRecord();//清空预选课列表  
		return true;
	}
	
	@Override
	//登记成绩
	public boolean recordScore(ArrayList<SelectionRecordVO> scores)
			throws RemoteException {
		boolean isSucceed=true;
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		for(SelectionRecordVO score: scores){
		 if(!selectionRecordDataService.updateSelectionRecord(new SelectionRecordPO(score))){
			 isSucceed = false;
			 break;
		 }
		}
		return isSucceed;
	}

	@Override
	//通过Excel登记成绩
	public boolean recordScoreByExcel(String filename,String courseid) throws RemoteException {
		// TODO Auto-generated method stub
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(filename));
			Sheet rs = rwb.getSheet(0);
			int cols=rs.getColumns();
			int rows=rs.getRows();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    //第一个是列数，第二个是行数
                    String studentid=rs.getCell(j++, i).getContents();
                    int score=Integer.parseInt(rs.getCell(j++, i).getContents());
                    System.out.println("sutdentid:"+studentid+" score:"+score);
                    selectionRecordDataService.updateSelectionRecord(new SelectionRecordPO(courseid, studentid, score));
                    //http://www.cnblogs.com/zyw-205520/p/3762954.html 参考资料
                }
            }
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	//根据课程id查询课程的学生的成绩
	public ArrayList<SelectionRecordVO> showCourseScores(String courseid)
			throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		ArrayList<SelectionRecordPO> selectionRecordPOs= selectionRecordDataService.showCourseStudents(courseid);
		ArrayList<SelectionRecordVO> selectionRecordVOs =new ArrayList<SelectionRecordVO>();
		for(SelectionRecordPO selectionRecordPO : selectionRecordPOs){
			selectionRecordVOs.add(new SelectionRecordVO(selectionRecordPO));
		}
		return selectionRecordVOs;
	}

	@Override
	//根据学生id查询学生的上过的课程的成绩
	public ArrayList<SelectionRecordVO> showStudentScores(String studentid)
			throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		ArrayList<SelectionRecordPO> selectionRecordPOs=  selectionRecordDataService.showStudentCourses(studentid);
		ArrayList<SelectionRecordVO> selectionRecordVOs =new ArrayList<SelectionRecordVO>();
		for(SelectionRecordPO selectionRecordPO : selectionRecordPOs){
			selectionRecordVOs.add(new SelectionRecordVO(selectionRecordPO));
		}
		return selectionRecordVOs;
	}

	@Override
	//根据课程id查询学生列表
	public ArrayList<StudentVO> showCourseStudents(String courseid)
			throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		UserDataService userDataService = new UserData();
		ArrayList<StudentVO> studentVOs = new ArrayList<StudentVO>();
		ArrayList<SelectionRecordPO> selectionRecordPOs =selectionRecordDataService.showCourseStudents(courseid);
		for(SelectionRecordPO selectionRecordPO:selectionRecordPOs){
			studentVOs.add(new StudentVO(userDataService.queryStudentByID(selectionRecordPO.getStudentID())));
		}
		return studentVOs;
	}

	@Override
	//根据学生id查看学生的课表
	public ArrayList<CourseVO> showStudentCourses(String studentid)
			throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		CourseDataService courseDataService = new CourseData();
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		ArrayList<SelectionRecordPO> selectionRecordPOs =selectionRecordDataService.showStudentCourses(studentid);
		for(SelectionRecordPO selectionRecordPO:selectionRecordPOs){
			courseVOs.add(new CourseVO(courseDataService.queryCourseByID(selectionRecordPO.getCourseID())));
		}
		return courseVOs;
	}
	
	//查看预选该课程的学生列表
	public ArrayList<StudentVO> showCoursepreStudents(String courseid)throws RemoteException{
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		UserDataService userDataService = new UserData();
		ArrayList<StudentVO> studentVOs = new ArrayList<StudentVO>();
		ArrayList<preSelectionRecordPO> preselectionRecordPOs =selectionRecordDataService.showCoursepreStudents(courseid);
		for(preSelectionRecordPO preselectionRecordPO:preselectionRecordPOs){
			studentVOs.add(new StudentVO(userDataService.queryStudentByID(preselectionRecordPO.getStudentID())));
		}
		return studentVOs;
	}
	
	//查看学生预选的课程列表
	public ArrayList<CourseVO> showStudentpreCourses(String studentid)throws RemoteException{
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		CourseDataService courseDataService = new CourseData();
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		ArrayList<preSelectionRecordPO> preselectionRecordPOs =selectionRecordDataService.showStudentpreCourses(studentid);
		for(preSelectionRecordPO preselectionRecordPO:preselectionRecordPOs){
			courseVOs.add(new CourseVO(courseDataService.queryCourseByID(preselectionRecordPO.getCourseID())));
		}
		return courseVOs;
	}




}
