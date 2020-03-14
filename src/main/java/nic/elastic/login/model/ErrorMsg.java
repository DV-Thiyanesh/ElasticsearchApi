package nic.elastic.login.model;
public class ErrorMsg 
{
	private String msgCode;
	private String msg;
	public ErrorMsg()
	{
		super();
	}
	public ErrorMsg(String msgCode, String msg) 
	{
		super();
		this.msgCode = msgCode;
		this.msg = msg;
	}
	public String getMsgCode() 
	{
		return msgCode;
	}
	public void setMsgCode(String msgCode) 
	{
		this.msgCode = msgCode;
	}
	public String getMsg() 
	{
		return msg;
	}
	public void setMsg(String msg) 
	{
		this.msg = msg;
	}
	@Override
	public String toString() 
	{
		return "LoginErrorMsg [msgCode=" + msgCode + ", msg=" + msg + "]";
	}
}
