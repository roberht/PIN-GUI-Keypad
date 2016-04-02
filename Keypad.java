//Written by Robert Herrera
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Keypad 
{
	private static ArrayList tenantContainer;
	public static void setUpTenantDatabase() throws FileNotFoundException
	{
		String fileName = "tenants.txt";			//change as needed
		FileReader file = new FileReader(fileName);
		Scanner fileScanner = new Scanner(file);
		String line;
		
		tenantContainer = new ArrayList();	//tenantContainer aka an apartment?

		while (fileScanner.hasNext())	//create Tenant objects for each line in textfile
		{
			line = fileScanner.nextLine();				
			String[] tenants = line.split(" ");	// apt#, pin, name
			tenantContainer.add(new Tenant(tenants[0], tenants[1], tenants[2]));
		}	
	}
	public static Boolean tenantSearch(String str1, String str2)
	{
		String tempAptNum = str1;
		String tempPin = str2;
		int index = 0;
		Boolean status = false;
		
		for (int i=0; i<tenantContainer.size(); i++)
		{
			if(tempAptNum.equals(((Tenant) tenantContainer.get(i)).getAptNum()))
			{
				index = i;		
			}
		}
		if (tempPin.equals(((Tenant) tenantContainer.get(index)).getPin()))
		{
			status = true;
		    String name = ((Tenant) tenantContainer.get(index)).getName();
		    KeypadDemo.textDisplay.setStyle(" -fx-base: chartreuse; -fx-font: 48 arial;");
			KeypadDemo.textDisplay.setText("Hello, "+name);
		}
		else{
			KeypadDemo.textDisplay.setStyle(" -fx-base: chartreuse; -fx-font: 30 arial;");
			KeypadDemo.textDisplay.setText("Invalid Apt # or PIN");
		}
		return status;
	}
}
