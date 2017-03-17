package com.eureka.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class SeqGeneratorUtil {
	
	private static final Logger logger =Logger.getLogger(SeqGeneratorUtil.class);
	public static String getSeqBySql(String tableName)throws SQLException{

		/*String url = "jdbc:mysql://localhost:3306/test" ;    
	     String username = "root" ;   
	     String password = "" ;  */ 
		String url = PropertyUtil.getProperty("druid.url");    
	     String username =  PropertyUtil.getProperty("druid.username");     
	     String password = PropertyUtil.getProperty("druid.password"); 
	     Connection  conn  =null;
	     PreparedStatement stat =null;
	     PreparedStatement stat2 =null;
	     String  returnString = null;
	     int returnInt=555;
		try{
			 Class.forName("com.mysql.jdbc.Driver") ;   
			  conn = DriverManager.getConnection(url, username, password);
			  stat = conn.prepareStatement("UPDATE seq SET val=last_insert_id(val+1) WHERE name='"+tableName+"';");
			  stat.executeUpdate();
			  stat2 = conn.prepareStatement("select last_insert_id();");
			 ResultSet resultSet = stat2.executeQuery();
			 while(resultSet.next()){
				 returnInt =resultSet.getInt(1);
			 }
			 logger.error(returnInt);
			conn.close();//关闭连接
		
		}catch(Exception e){
			logger.error(e);
		}
		logger.error("returnString========================="+returnString);
		return returnString;
	
	}
}
