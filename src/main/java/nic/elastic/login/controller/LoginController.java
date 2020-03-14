package nic.elastic.login.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.util.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import nic.elastic.login.config.LoginRole;
import nic.elastic.login.model.ErrorMsg;
import nic.elastic.login.model.ListErrorMsg;
import nic.elastic.login.model.Login;
import nic.elastic.login.model.RandValueMsg;
import nic.elastic.login.service.ElasticImplService;
import nic.elastic.login.utility.CaptchaImageGeneration;
import nic.elastic.login.utility.HashingValue;
import nic.elastic.login.utility.JotOperations;
import nic.elastic.login.utility.RandomSaltGeneration;
import nic.elastic.login.validator.LoginValidator;
@Controller
public class LoginController 
{
	private static final Logger LOGGER=LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private ElasticImplService sformService;
	public int totalAttempts= 3;
	@RequestMapping("/elastic/login")
	public String loginPage
		(HttpServletRequest request
			, HttpSession session
				, Model model)
	{
		LOGGER.info("Inside Login Controller - loginPage Method");
		RandValueMsg loginViewMsg = new RandValueMsg();
		String captcha = CaptchaImageGeneration.captchaGen(request, session);
		loginViewMsg.setCaptcha(captcha);
		String randSaltValue=RandomSaltGeneration.randCode(request, session);
		loginViewMsg.setRandCode(randSaltValue);
		model.addAttribute("Login", loginViewMsg);
		return "login";
	}
	@PostMapping("/elastic/index")
	public String loginCheck
	//@ModelAttribute("SFormLogin")Login loginDet,@ModelAttribute("SFormLogin")RandValueMsg randAndCaptcha
		(@RequestParam String uname
			, @RequestParam String pwd
				, @RequestParam String captchaVal
					, HttpServletRequest request
						, HttpServletResponse response
							, HttpSession session
								, Model model) 
	{
		LOGGER.info("Inside Login Controller - loginCheck Method");
		Login loginDet=new Login();
		loginDet.setUname(uname);
		loginDet.setPwd(pwd);
		List<ErrorMsg> loginMsgVd=new ArrayList<ErrorMsg>();
		LOGGER.info("Login Controller - Before Validation");
		loginMsgVd=LoginValidator.loginValidation(loginDet);
		LOGGER.info("Login Controller - After Validation");
		if(loginMsgVd.isEmpty())
		{
			String captchaValue = session.getAttribute("genCaptcha").toString();
			LOGGER.info("SESSION CAPTCHA - "+captchaValue);
			LOGGER.info("CAPTCHA VALUE - " +captchaVal);
			if(captchaValue.equals(captchaVal))
			{
				LOGGER.info("Login Controller - Before DAO");
				List<Login> loginMsgDb = sformService.checkLogin(loginDet);
				if(!loginMsgDb.isEmpty())
				{
					String hashedPwd = loginMsgDb.get(0).getPwd();
					String dbRole = loginMsgDb.get(0).getRole();
					String role = LoginRole.getRole(dbRole);
					LOGGER.info("DB ROLE - "+dbRole);
					LOGGER.info("ROLE - "+role);
					LOGGER.info("Login Controller - After DAO");
					String randCode = session.getAttribute("genRandNo").toString();
					LOGGER.info(randCode);
					LOGGER.info("Encoded Random String - "+  randCode);
					String hashedRandCode = HashingValue.hash(randCode);
					LOGGER.info("Hashed Random String - "+  hashedRandCode);
					String joinRandPwd = hashedRandCode.concat(hashedPwd);  
					LOGGER.info("Concatenate Random String and Hashed Pwd - "+  joinRandPwd);
					String hashedJoinRandPwd = HashingValue.hash(joinRandPwd);
					LOGGER.info("Hash Concatenate Random String and Hashed Pwd - "+  hashedJoinRandPwd);
					LOGGER.info("SERVER PASSWORD - "+hashedJoinRandPwd);
					LOGGER.info("CLIENT PASSWORD - "+pwd);
					if (totalAttempts != 0) 
					{
						if(hashedJoinRandPwd.equals(pwd))
						{
							String userName = uname;
							String generateAuth;
							try
							{
								generateAuth = JotOperations.createJWT(userName,role);
							}
							catch(NullPointerException e)
							{
								LOGGER.warn("JWT value is null");
								model.addAttribute("ErrorMsg", "JWT value is null");
								return "redirect:/elastic/login";
							}
							catch(ClaimJwtException e)
							{
								LOGGER.warn("JWT claim failed");
								model.addAttribute("ErrorMsg", "JWT claim failed");
								return "redirect:/elastic/login";
							}
							catch(MalformedJwtException e)
							{
								LOGGER.warn("JWT is not correctly constructed and should be rejected");
								model.addAttribute("ErrorMsg", "JWT is not correctly constructed and should be rejected");
								return "redirect:/elastic/login";
							}
							catch(SignatureException e)
							{
								LOGGER.warn("JWT Signature doesn't match or invalid signature");
								model.addAttribute("ErrorMsg", "JWT Signature doesn't match or invalid signature");
								return "redirect:/elastic/login";
							}
							catch(UnsupportedJwtException e)
							{
								LOGGER.warn("JWT format is not supported");
								model.addAttribute("ErrorMsg", "JWT format is not supported");
								return "redirect:/elastic/login";
							}
							if(generateAuth.equals("INVALID"))
							{
								LOGGER.warn("JWT is invalid");
								model.addAttribute("ErrorMsg", "JWT is invalid");
								return "redirect:/elastic/login";
							}
							else
							{
								response.addHeader("FSISUserAuth", "bearer "+generateAuth);
								session.invalidate();
								LOGGER.info("JWT - "+generateAuth);
								return "display";
							}
						}
						else
						{
							totalAttempts--;
							model.addAttribute("ErrorMsg", "Invalid Credential. Please Try Again.");
							return "redirect:/elastic/login";
						}
					}
					else
					{
						model.addAttribute("ErrorMsg", "Invalid Credential. Authentication Attempt Limit Exceeded.");
						return "redirect:/elastic/login";
					}
				}
				else
				{
					model.addAttribute("ErrorMsg", "Data Not Found");
					return "redirect:/elastic/login";
				}
			}
			else
			{
				model.addAttribute("ErrorMsg", "Invalid Captcha. Please Try Again.");
				return "redirect:/elastic/login";
			}
		}
		else
		{
			ListErrorMsg loginViewMsg=new ListErrorMsg();	
			loginViewMsg.setListErrorMsg(loginMsgVd);
			model.addAttribute("ErrorMsg", loginViewMsg);
			return "redirect:/elastic/login";
		}	
	}
	@PostMapping(value="/elastic/refreshCaptcha", produces="application/json")
	public @ResponseBody RandValueMsg refreshCaptcha
		(HttpServletRequest request
			, HttpSession session)  
	{
		LOGGER.info("Inside Login Controller - refreshCaptcha Method");
		RandValueMsg loginViewMsg=new RandValueMsg();
		String captcha=CaptchaImageGeneration.captchaGen(request, session);
		loginViewMsg.setCaptcha(captcha);
		String randSaltValue=RandomSaltGeneration.randCode(request, session);
		loginViewMsg.setRandCode(randSaltValue);
		return loginViewMsg;
	}
	@PostMapping("/error")
	public String error(Model model) 
	{
		model.addAttribute("ErrorMsg", "Error. Please Login Again.");
		return "login";
	}
	@PostMapping("/elastic/logout")
	public String tokenInvalidate
		(@CookieValue("FSISUserAuth") String requestAuthCookie
			, @RequestHeader("FSISUserAuth") String requestAuthHeader
				, HttpSession session) 
	{
		session.invalidate();
		requestAuthCookie.replace(requestAuthCookie, "NULL");
		requestAuthHeader.replace(requestAuthHeader, "NULL");
		return "login";	
	}
}
