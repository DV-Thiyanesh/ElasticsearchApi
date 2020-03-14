package nic.elastic.login.model;
public class Login 
{
	private String uname;
	private String pwd;
	private String role;
	public Login()
	{
		super();
	}
	public Login(String uname, String pwd, String role) 
	{
		super();
		this.uname = uname;
		this.pwd = pwd;
		this.role = role;
	}
	
	public String getUname() 
	{
		return uname;
	}
	public void setUname(String uname) 
	{
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() 
	{
		return "Login [uname=" + uname + ", pwd=" + pwd + ", role=" + role + "]";
	}
}
