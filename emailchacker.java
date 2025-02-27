import java.io.*;

public class emailchacker {
    public static boolean isEmailExists(String email)
	{
		try
		{
			File file = new File("Userdata.txt");
			if(!file.exists())
			{
				return false;
			}
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			
			while((line = reader.readLine()) != null)
			{
				String[] parts = line.split(",");
				if(parts[1].equals(email))
				{
					reader.close();
					return true;
				}
			}
			reader.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
}
