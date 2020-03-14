package nic.elastic.login.service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nic.elastic.login.dao.ElasticImplDao;
import nic.elastic.login.model.ErrorMsg;
import nic.elastic.login.model.Login;
@Service
public class ElasticImplService implements ElasticIService
{
	private static final Logger LOGGER=LoggerFactory.getLogger(ElasticImplService.class);
	@Autowired
	private ElasticImplDao elasticDao;
	public static List<ErrorMsg> listmsg;
	@Override
	public List<Login> checkLogin(Login loginDet)
	{
		LOGGER.info("Inside LoginImplService Class- checkLogin Method");
	    return elasticDao.checkLogin(loginDet);
	}
	
}