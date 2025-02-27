public class User
{
	private String name;
	private String email;
	private String password;
	private String Atype; 
	
	public User(String name, String email, String password ,String Atype)
	{
		this.name = name;
		this.email = email;
		this.password = password;
		this.Atype=Atype;
	}
	
	public String getName(){return name;}
	public String getEmail(){return email;}
	public String getPassword(){return password;}
	public String getAtype(){return Atype;}
}