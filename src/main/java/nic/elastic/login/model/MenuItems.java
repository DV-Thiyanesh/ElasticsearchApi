package nic.elastic.login.model;
import java.util.List;
public class MenuItems 
{
	private List<Object> menulist;
	public MenuItems() 
	{
		super();
	}
	public MenuItems(List<Object> menulist) 
	{
		super();
		this.menulist = menulist;
	}
	public List<Object> getMenulist() 
	{
		return menulist;
	}
	public void setMenulist(List<Object> menulist) 
	{
		this.menulist = menulist;
	}
}
