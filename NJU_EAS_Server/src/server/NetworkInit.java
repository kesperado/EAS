package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import businesslogic.coursebl.CourseBL;
import businesslogic.planbl.PlanBL;
import businesslogic.selectionrecordbl.SelectionRecordBL;
import businesslogic.systeminfobl.SystemInfoBL;
import businesslogic.userbl.UserBL;
import businesslogic.teachcoursebl.TeachCourseBL;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.planblservice.PlanBLService;
import businesslogicservice.selectionrecordblservice.SelectionRecordBLService;
import businesslogicservice.systeminfoblservice.SystemInfoBLService;
import businesslogicservice.userblservice.UserBLService;
import businesslogicservice.teachcourseblservice.TeachCourseBLService;

/**
 * @author 作者 :knox
 * 类说明：
 * 这个类用来作为服务器端RMI网络的初始化类
 */
public class NetworkInit {
	
	public static void init(){
		try {			
			CourseBLService courseBLService = new CourseBL();
			UserBLService userBLService = new UserBL();
			TeachCourseBLService teachCourseBLService = new TeachCourseBL();
			SelectionRecordBLService selectionRecordBLService = new SelectionRecordBL();
			PlanBLService planBLService = new PlanBL();
			SystemInfoBLService systemInfoBLService =  new SystemInfoBL();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("courseBLService", courseBLService);
			Naming.rebind("userBLService", userBLService);
			Naming.rebind("teachCourseBLService", teachCourseBLService);
			Naming.rebind("selectionRecordBLService", selectionRecordBLService);
			Naming.rebind("planBLService", planBLService);
			Naming.rebind("systemInfoBLService", systemInfoBLService);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("服务器已启动");
	}

}
