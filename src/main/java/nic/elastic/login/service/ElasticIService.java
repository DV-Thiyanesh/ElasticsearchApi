package nic.elastic.login.service;
import java.util.List;

import nic.elastic.login.model.Login;
public interface ElasticIService 
{
	//check login
	public List<Login> checkLogin(Login loginDet);
	//view menuItem

}
