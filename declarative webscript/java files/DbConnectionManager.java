package funds;



import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DbConnectionManager
{
  private static ClassPathXmlApplicationContext ctx;
  private static BasicDataSource dmdatasource = null;
  private static DbConnectionManager connMngr = null;
  private static Logger logger = Logger.getLogger(DbConnectionManager.class);
  
  public static synchronized DbConnectionManager getInstance()
  {
    if (connMngr == null)
    {
      connMngr = new DbConnectionManager();
      ctx = new ClassPathXmlApplicationContext("spring-datasource.xml");
      dmdatasource = (BasicDataSource)ctx.getBean("dataSource", BasicDataSource.class);
      logger.error("context is " + ctx + "datasource is " + dmdatasource);
    }
    return connMngr;
  }
  
  public Connection getConnection()
  {
    Connection conn = null;
    try
    {
      conn = dmdatasource.getConnection();
      logger.error("connected to database, connection is " + conn);
    }
    catch (SQLException e)
    {
      logger.fatal("Failed to get connection to database");
    }
    return conn;
  }
}
