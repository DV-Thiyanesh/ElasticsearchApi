package nic.elastic.login.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nic.elastic.login.model.Login;
@Repository
@Transactional
public class ElasticImplDao implements ElasticIDao 
{
	private static final Logger LOGGER=LoggerFactory.getLogger(ElasticImplDao.class);
	@Autowired
	private JdbcTemplate template;
	//pass - count
	@Override
	public List<Login> checkLogin(Login loginDet) 
	{
		LOGGER.info("LoginImplDao Class - checkLogin Method");
		String loginViewQuery = "SELECT user_id, pwd, user_type_code from user_login WHERE user_id = ?";
		List<Login> loginList = template.query(loginViewQuery,new PreparedStatementSetter() 
		{	   
			public void setValues(PreparedStatement prepStmt) throws SQLException 
			{
				String uname=loginDet.getUname();
				prepStmt.setString(1, uname);				
			}
		},
		new LoginMapper());
		return loginList;
	}/*
	public List<Object> viewDashboard() 
	{
		String resultViewQuery = "SELECT fsis_menu_items.menu_id, fsis_menu_items.menu_level, fsis_menu_items.menu_label, fsis_menu_items.menu_url, fsis_menu_items.menu_logo_class,fsis_menu_items_role.user_type_code\r\n" + 
				"	FROM fsis_menu_items\r\n" + 
				"		INNER JOIN fsis_menu_items_role\r\n" + 
				"			ON fsis_menu_items.menu_id = fsis_menu_items_role.menu_id\r\n" + 
				"				WHERE fsis_menu_items.is_active='Y'";
		List<Object> dashboardList = template.query(resultViewQuery, new DashboardMapper());
		return dashboardList;  
	}
	*/
	
	
}
