package nic.elastic.login.config;
public enum LoginRole 
{
	INTN("5"), 
	FROA("4"), 
	MHAA("8"); 
	private final String Code;
	LoginRole(String code) 
	{
		this.Code = code;
	}
	public String getLoginRoleCode() 
	{
		return this.Code;
	}
	public static String getRole(String role)
	{
		if(role.equals(LoginRole.FROA.getLoginRoleCode()))
		{
			return "FROA";	
		}
		else if(role.equals(LoginRole.INTN.getLoginRoleCode()))
		{
			return "INTN";
		}
		else if(role.equals(LoginRole.MHAA.getLoginRoleCode())) 
		{
			return "MHAA";
		}
		else
		{
			return null;
		}	
	}
}
