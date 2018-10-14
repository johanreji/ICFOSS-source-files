package funds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;



public class GetInstitutionWebscript extends DeclarativeWebScript {

	
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
		Map<String, Object> model = new HashMap<>(0);
		Logger logger = Logger.getLogger(GetInstitutionWebscript.class);
		List<GetInstitutionDTO> instList=new ArrayList<>();
	
		GetInstitutionDTO inst = null;
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = DbConnectionManager.getInstance().getConnection();
			logger.error("Conneted successfully");
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
		try {
		String instCode="";
		String instName="";
		logger.error("Entered try...........");
		stmt=connection.prepareStatement("SELECT institution_code,institution_name FROM institutions");
		rs=stmt.executeQuery();
		logger.error("Executed query......");
		while(rs.next()) {
			inst=new GetInstitutionDTO();
			instCode=rs.getString(1);
			instName=rs.getString(2);
			logger.error("Institution code is>>>>>>>."+ instCode); 
			logger.error("Institution name is>>>>>>>>>."+ instName);
			inst.setInstCode(instCode);
			inst.setInstName(instName);
			instList.add(inst);
			//inst.setInstCode("tst2");
//			inst.setInstName("test 2");
//			instList.add(inst);
			
		}
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
//		instw.setInstCode("tst1");
//		instw.setInstName("test 1");
//		instList.add(instw);
		model.put("instValues", instList);
		
		//System.out.println(instList);
		return model;
		
	}
	
	
	
}
