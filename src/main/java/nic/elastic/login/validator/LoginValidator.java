package nic.elastic.login.validator;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nic.elastic.login.model.ErrorMsg;
import nic.elastic.login.model.Login;
public class LoginValidator 
{
	private final static Logger LOGGER=LoggerFactory.getLogger(LoginValidator.class);
	public static List<ErrorMsg> loginValidation(Login loginDet)
	{
		List<ErrorMsg> listErrorMsg=new ArrayList<ErrorMsg>();
		LOGGER.info("Inside Login Validator");
		LOGGER.info(loginDet.getUname());
		if(loginDet.getUname().isEmpty())
		{
			LOGGER.info(loginDet.getUname());
			ErrorMsg nameErrorMsgEmpty=new ErrorMsg();
			nameErrorMsgEmpty.setMsgCode("NAMFILL");
			nameErrorMsgEmpty.setMsg("Please Enter the UserName.");
			listErrorMsg.add(nameErrorMsgEmpty);
		}
		else if(!loginDet.getUname().matches("^[A-Z]{4}[0-9]{3}$"))
		{
			ErrorMsg nameErrorMsgVal=new ErrorMsg();
			nameErrorMsgVal.setMsgCode("NAMFILL");
			nameErrorMsgVal.setMsg("Please Enter a valid UserName");
			listErrorMsg.add(nameErrorMsgVal);
		}
		if(loginDet.getPwd().isEmpty())
		{
			ErrorMsg pwdErrorMsgEmpty=new ErrorMsg();
			pwdErrorMsgEmpty.setMsgCode("KEYFILL");
			pwdErrorMsgEmpty.setMsg("Please Enter the Password.");
			listErrorMsg.add(pwdErrorMsgEmpty);
		}
		/*
		LoginCodeGen loginCap = new LoginCodeGen();
		LoginErrorMsg captchaErrorMsgEmpty=new LoginErrorMsg();
		if(loginCap.getCaptcha().isEmpty())
		{
			captchaErrorMsgEmpty.setMsgCode("CPTFILL");
			captchaErrorMsgEmpty.setMsg("Please Enter the Captcha.");
			listErrorMsg.add(captchaErrorMsgEmpty);
		}*/
		System.out.println(listErrorMsg.size());
		return listErrorMsg;
	}
}
