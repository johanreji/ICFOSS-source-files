package funds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

public class DeleteFundingType extends DeclarativeWebScript{
	protected Map<String,Object> executeImpl(WebScriptRequest req,Status status,Cache cache){
		Map<String,Object> model=new HashMap<>(0);
		Logger logger=Logger.getLogger(DeleteFundingType.class);
		String[] ftArray;
		String ftCode="";
		String instCode="";
		Integer i,count=0;
		String returnStatus;
		Connection connection=null,con=null;
		PreparedStatement stmt = null,pstmt=null,cstmt=null;
		//ResultSet rs=null;
		ftArray=req.getParameterValues("ftCode[]");
		//instCode=req.getParameter("instCode");
		logger.error("list is delete to be is :"+ftArray);
		
		try {
			connection=DbConnectionManager.getInstance().getConnection();
		//	con=DbConnectionManager.getInstance().getConnection();
			logger.error("deleetion connection established");
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			returnStatus="Funding Type Deletion is Failed";
			e.printStackTrace();
		}
			
		try {
			logger.error("entered try in deleetion with length"+ftArray.length);
			
			stmt=connection.prepareStatement("DELETE FROM fundingtype_institution_map WHERE funding_type_code=? ");
			//and institution_code=?
			pstmt=connection.prepareStatement("DELETE FROM fundingtype_master WHERE funding_type_code=?");
			

			connection.setAutoCommit(false);
			
			for(i=0;i<ftArray.length;i++) {
				//count=0;
				logger.error("map delete >>>>>>>>>"+ftArray[i]);
				//cstmt=con.prepareStatement("select count(*) from fundingtype_institution_map where funding_type_code=? and institution_code!=?",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ftCode=ftArray[i];
				//cstmt.setString(1, ftCode);
				//logger.error("after first par");
				//cstmt.setString(2, instCode);
				//logger.error("after second par");
				//rs=cstmt.executeQuery();
				//rs.first();
				//count=rs.getInt(1);
				//logger.error("deletion count"+count);
				stmt.setString(1,ftCode);
				//stmt.setString(2, instCode);
				pstmt.setString(1, ftCode);
				
				stmt.addBatch();
//				if(count<1) {
				pstmt.addBatch();
//				}
				logger.error("after batch adding");
				
			}
		
			stmt.executeBatch();
			
				pstmt.executeBatch();
			
			connection.commit();
			
			
			returnStatus="Funding Type Deletion is successful";
			logger.error("ecexuted deletion");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("deletion of erooorrrr ====== "+e.toString());
			returnStatus="Funding Type Deletion is Failed";
			
		}
		
		
		finally {
			
			try {
				stmt.close();
				pstmt.close();
			//	cstmt.close();
			//	con.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				returnStatus="Funding Type Deletion is Failed";
				e.printStackTrace();
			}
		}
		
		model.put("Status",returnStatus);
		return model;
		
		
	}
	

}
