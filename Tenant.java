// Written by Robert Herrera
public class Tenant 
{
	private String aptNum, pin, name;
	
	//constructor
	public Tenant(String apt, String p, String n)
	{
		aptNum = apt;
		pin = p;
		name = n;
	}
	public String getAptNum()
	{
		return aptNum;
	}
	public String getPin()
	{
		return pin;
	}
	public String getName()
	{
		return name;
	}
}
