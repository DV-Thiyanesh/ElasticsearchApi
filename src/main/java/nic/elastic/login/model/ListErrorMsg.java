package nic.elastic.login.model;
import java.util.List;
public class ListErrorMsg 
{
	private String action;
	private String msg;
	private String FSISUserAuth;
	private List<ErrorMsg> listErrorMsg;
	public ListErrorMsg()
	{
		super();
	}
	public ListErrorMsg(String action, String msg, String FSISUserAuth, List<ErrorMsg> listErrorMsg) 
	{
		super();
		this.action = action;
		this.msg = msg;
		this.FSISUserAuth = FSISUserAuth;
		this.listErrorMsg = listErrorMsg;
	}
	public String getAction() 
	{
		return action;
	}
	public void setAction(String action) 
	{
		this.action = action;
	}
	public String getMsg() 
	{
		return msg;
	}
	public void setMsg(String msg) 
	{
		this.msg = msg;
	}
	public List<ErrorMsg> getListErrorMsg() 
	{
		return listErrorMsg;
	}
	public void setListErrorMsg(List<ErrorMsg> listErrorMsg) 
	{
		this.listErrorMsg = listErrorMsg;
	}
	public String getFSISUserAuth() 
	{
		return FSISUserAuth;
	}
	public void setFSISUserAuth(String FSISUserAuth) 
	{
		this.FSISUserAuth = FSISUserAuth;
	}
	@Override
	public String toString() 
	{
		return "ListErrorMsg [action=" + action + ", msg=" + msg + ", FSISUserAuth=" + FSISUserAuth + ", listErrorMsg="
				+ listErrorMsg + "]";
	}	
}
