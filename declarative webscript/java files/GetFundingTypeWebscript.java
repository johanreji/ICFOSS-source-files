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

public class GetFundingTypeWebscript extends DeclarativeWebScript {
	 protected Map<String,Object> executeImpl(WebScriptRequest req, Status status, Cache cache){
		
		 	Map<String, Object> model = new HashMap<>(0);
			Logger logger = Logger.getLogger(GetFundingTypeWebscript.class);
			List<GetFundingTypeDTO> fundList=new ArrayList<>();
			String fundCode="";
			String fundName="";
			String fundDesc="";
			String sql="";
			String instCode=req.getParameter("instCode");
			GetFundingTypeDTO fund = null;
			logger.error("get ft -- inst code ============"+instCode);
			Connection connection = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				connection=DbConnectionManager.getInstance().getConnection();
				logger.error("get ft connection success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				sql=" select fundingtype_master.funding_type_code,funding_type_name,funding_type_desc from fundingtype_institution_map inner join fundingtype_master on  fundingtype_master.funding_type_code=fundingtype_institution_map.funding_type_code where fundingtype_institution_map.institution_code= ? ";
				
				logger.error("entered get ft try");
				stmt=connection.prepareStatement(sql);
				stmt.setString(1,instCode);
				rs=stmt.executeQuery();
				logger.error("get ft Executed query......");
				while(rs.next()) {
					fund=new GetFundingTypeDTO();
					fundCode=rs.getString(1);
					fundName=rs.getString(2);
					fundDesc=rs.getString(3);
					logger.error("get ft fund details ---- "+fundCode+"***"+fundName+"***"+fundDesc);
					fund.setFundCode(fundCode);
					fund.setFundName(fundName);
					fund.setFundDesc(fundDesc);
					fundList.add(fund);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
			model.put("fundTypes", fundList);
			//model.put("fundTypes", "succes get ft");
			return model;
		 
		 
	 }

}
