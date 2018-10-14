package funds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

public class CreateFundingTypeWebscript extends DeclarativeWebScript {

	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
		Map<String, Object> model = new HashMap<>(0);
		Logger logger = Logger.getLogger(CreateFundingTypeWebscript.class);
		String returnVal = "";
		List<CreateFundingTypeDTO> fundList=new ArrayList<>();
		//String[] instList;
		CreateFundingTypeDTO fund=new CreateFundingTypeDTO();
		Connection connection = null;
		PreparedStatement stmt = null;
		String fundCode=req.getParameter("ft_code");
		String fundName=req.getParameter("ft_name");
		String fundDesc=req.getParameter("ft_desc");
		String[] instList=req.getParameterValues("ft_inst[]");
		if(instList != null) {
			int len = instList.length;
			logger.error("Institution list Len >>> " + len);
		}
		logger.error("Institution list >>> " + instList);
		//logger.error("inst code ==="+instList[0]);
		//logger.error("inst code ==="+instList[1]);
		logger.error("fund code ==="+fundCode);
		logger.error("ft successfully");
		
		try {
			connection = DbConnectionManager.getInstance().getConnection();
			logger.error("ft Conneted successfully");
		} catch (Exception exception) {
			
			returnVal = "Funding Type creation is Failed.";
			exception.printStackTrace();
			
		}
		try {
		
		//String fundInst="INST1";
		logger.error("ft Entered try...........");
		stmt=connection.prepareStatement("INSERT INTO fundingtype_master(funding_type_code, funding_type_name, funding_type_desc)  VALUES (?, ?, ?) ");
		stmt.setString(1, fundCode);
		stmt.setString(2, fundName);
		stmt.setString(3, fundDesc);
		
		stmt.executeUpdate();
		
		logger.error("ft Executed query......");
		String instCode = "";
		
		stmt=connection.prepareStatement("INSERT INTO fundingtype_institution_map(funding_type_code, institution_code)VALUES (?, ?)");
		connection.setAutoCommit(false);
		if(instList != null && instList.length > 0) {
			for(int i = 0; i < instList.length; ++i) {
				instCode = instList[i];
				stmt.setString(1, fundCode);
				stmt.setString(2, instCode);
				stmt.addBatch();
			}
			
		}
		stmt.executeBatch();
		connection.commit();
		returnVal = "Funding Type creation is successful.";
	
		}
		catch(Exception e) {
			returnVal = "Funding Type creation is Failed.";
			e.printStackTrace();
		}
		
		finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				returnVal = "Funding Type creation is Failed.";
				e.printStackTrace();
			}
			
		}
		
		
		
//		instw.setInstCode("tst1");
//		instw.setInstName("test 1");
//		instList.add(instw);
		model.put("fundStatus", returnVal);
		
		//System.out.println(instList);
		return model;
		
	}
	
	
}
