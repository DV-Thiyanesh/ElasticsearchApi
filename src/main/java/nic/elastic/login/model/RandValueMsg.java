package nic.elastic.login.model;
public class RandValueMsg 
{
	private String captcha;
	private String randCode;
	public RandValueMsg()
	{	
		super();
	}
	public RandValueMsg(String captcha, String randCode) 
	{
		super();
		this.captcha = captcha;
		this.randCode = randCode;
	}
	public String getCaptcha() 
	{
		return captcha;
	}
	public void setCaptcha(String captcha) 
	{
		this.captcha = captcha;
	}
	public String getRandCode() 
	{
		return randCode;
	}
	public void setRandCode(String randCode) 
	{
		this.randCode = randCode;
	}
	@Override
	public String toString() 
	{
		return "LoginCodeGen [captcha=" + captcha + ", randCode=" + randCode + "]";
	}
}
