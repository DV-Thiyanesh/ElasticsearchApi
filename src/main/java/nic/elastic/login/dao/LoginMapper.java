package nic.elastic.login.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import nic.elastic.login.model.Login;
public class LoginMapper implements RowMapper<Login>
{
	//@Autowired 
	//private HttpSession session;
	private static final Logger LOGGER=LoggerFactory.getLogger(LoginMapper.class);
	public Login mapRow(ResultSet rs, int rowNum) throws SQLException 
    {
		LOGGER.info("Inside Employee Row Mapper DAO class");
		Login loginDet = new Login();
		/*
		String uname = rs.getString(1);
		String pwd = rs.getString(2);
		session.setAttribute("name", uname);
		session.setAttribute("pwd", pwd);
		loginDet.setUname(uname);
		loginDet.setPwd(pwd); 
		*/
		loginDet.setUname(rs.getString(1));
		loginDet.setPwd(rs.getString(2));
		loginDet.setRole(rs.getString(3));
		LOGGER.info(loginDet.toString());
		return loginDet;  
    }
}
