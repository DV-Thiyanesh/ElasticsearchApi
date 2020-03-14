package nic.elastic.login.dao;

import java.util.List;

import nic.elastic.login.model.Login;

public interface ElasticIDao 
{
	//check login
	public List<Login> checkLogin(Login loginDet);	
	//view menuItem

}
