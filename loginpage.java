import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;



public class loginpage extends JFrame implements MouseListener, ActionListener
{
	ImageIcon img;
	JLabel userLabel,userLabel1,userLabel2,useremail, passLabel;
	JTextField emailTF;
	JPasswordField passPF;
	JButton loginbutton, regBtn,viewButton,ForgottenBtn ;	
	JPanel panel;
	Color myColor,tcolor,neonGreen;
	Font myFont,myFont1,myFont2,myFont3;



	public loginpage()
	{
		super("Friends For Ever Pharma");
		this.setSize(800, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		myColor = new Color(210, 230, 255);
		neonGreen = new Color(6,57,112);
		tcolor = new Color(0, 0, 0, 0);
		myFont2 = new Font("Cambria", Font.PLAIN, 28);
		myFont1 = new Font("Cambria", Font.PLAIN, 17);
		myFont = new Font("Lucida Calligraphy", Font.BOLD, 35);
		myFont3 = new Font("Arial", Font.BOLD, 25);
		  panel = new JPanel() {
            
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("pic/login.jpg");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
		panel.setLayout(null);
		

		this.add(panel);

		userLabel1 = new JLabel("Friends For Ever");
		userLabel1.setBounds(255, 10, 350, 50);
		userLabel1.setOpaque(false);
		userLabel1.setForeground(neonGreen);
		userLabel1.setFont(myFont);
		panel.add(userLabel1);
		
		userLabel2 = new JLabel("Pharma");
		userLabel2.setBounds(320, 60, 160, 50);
		userLabel2.setOpaque(false);
		userLabel2.setForeground(neonGreen);
		userLabel2.setFont(myFont);
		panel.add(userLabel2);

		userLabel = new JLabel("Login");
		userLabel.setBounds(365, 130, 160, 50);
		userLabel.setOpaque(false);
		userLabel.setForeground(Color.BLACK);
		userLabel.setFont(myFont3);
		panel.add(userLabel);
		
		useremail = new JLabel("Email:");
		useremail.setBounds(265, 202, 90, 20);
		useremail.setOpaque(false);
		useremail.setForeground(Color.BLACK);
		useremail.setFont(myFont1);
		panel.add(useremail);
		
		passLabel = new JLabel("Password:");
		passLabel.setBounds(265, 228, 90, 20);
		//passLabel.setBackground(Color.white);
		passLabel.setOpaque(false);
		passLabel.setForeground(Color.BLACK);
		passLabel.setFont(myFont1);
		panel.add(passLabel);
		
		loginbutton = new JButton("Login");
		loginbutton.setBounds(315, 262, 160, 30);
		loginbutton.setFont(new Font("Arial", Font.BOLD, 16));		
		loginbutton.setBackground(tcolor);
        loginbutton.setBorderPainted(false); 
        loginbutton.setFocusPainted(false); 
        loginbutton.setContentAreaFilled(false);
		loginbutton.addMouseListener(this);
		loginbutton.addActionListener(this);
		panel.add(loginbutton);
		
			
		emailTF = new JTextField();
		emailTF.setBounds(315, 200, 210, 29);
		emailTF.setOpaque(false);
		emailTF.setBorder(null);
		emailTF.setBackground(tcolor);
		
		panel.add(emailTF);
		
		passPF = new JPasswordField();
		passPF.setBounds(340, 226, 175, 30);
		passPF.setBackground(tcolor);
		passPF.setOpaque(false);
		passPF.setBorder(null);
		passPF.setEchoChar('*');
		panel.add(passPF);

		viewButton = new JButton("👁");
        viewButton.setBounds(500, 226, 50, 30);
		viewButton.setBackground(tcolor);
        viewButton.setBorderPainted(false); // Remove border 
        viewButton.setFocusPainted(false); // Remove focus highlight
        viewButton.setContentAreaFilled(false);
		viewButton.addMouseListener(this);
		viewButton.addActionListener(this);	
		panel.add(viewButton);
		
		regBtn = new JButton("New Registation");
		regBtn.setBounds(240, 280, 160, 30);
		regBtn.setFont(new Font("Cambria", Font.BOLD, 12));		
		regBtn.setBackground(tcolor);
        regBtn.setBorderPainted(false); // Remove border 
        regBtn.setFocusPainted(false); // Remove focus highlight
        regBtn.setContentAreaFilled(false);
		regBtn.addMouseListener(this);
		regBtn.addActionListener(this);
		panel.add(regBtn);
		
		ForgottenBtn = new JButton("Forgotten Pass");
		ForgottenBtn.setBounds(400, 280, 160, 30);
		ForgottenBtn.setFont(new Font("Cambria", Font.BOLD, 12));		
		ForgottenBtn.setBackground(tcolor);
        ForgottenBtn.setBorderPainted(false); // Remove border 
        ForgottenBtn.setFocusPainted(false); // Remove focus highlight
        ForgottenBtn.setContentAreaFilled(false);
		ForgottenBtn.addMouseListener(this);
		ForgottenBtn.addActionListener(this);
		panel.add(ForgottenBtn);
		
		
	}
	
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me)
	{
		if(me.getSource() == viewButton)
		{
			
			 passPF.setEchoChar((char) 0);
		}
		else if(me.getSource() == loginbutton)
		{
			
			loginbutton.setForeground(Color.BLACK);
		}
		else if(me.getSource() == regBtn)
		{
			
			regBtn.setForeground(Color.BLACK);
		}
		else if(me.getSource() == ForgottenBtn)
		{
			
			ForgottenBtn.setForeground(Color.BLACK);
		}
		
	}
	public void mouseReleased(MouseEvent me)
	{
		if(me.getSource() == viewButton)
		{
			
			 passPF.setEchoChar('*');
		}
		else if(me.getSource() == loginbutton)
		{
			
			loginbutton.setForeground(Color.RED);
		}
		else if(me.getSource() == regBtn)
		{
			
			regBtn.setForeground(Color.RED);
		}
		else if(me.getSource() == ForgottenBtn)
		{
			
			ForgottenBtn.setForeground(Color.RED);
		}
		
	}
	
	
	
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == loginbutton)
		{
			
			loginbutton.setForeground(Color.RED);
		}
		else if(me.getSource() == regBtn)
		{
			
			regBtn.setForeground(Color.RED);
		}
		else if(me.getSource() == ForgottenBtn)
		{
			
			ForgottenBtn.setForeground(Color.RED);
		}
	}
	
	
	
	public void mouseExited(MouseEvent me)
	{
		if(me.getSource() == loginbutton)
		{
			
			loginbutton.setForeground(Color.BLACK);
		}
		else if(me.getSource() == regBtn)
		{
			
			regBtn.setForeground(Color.BLACK);
		}
		else if(me.getSource() == ForgottenBtn)
		{
			
			ForgottenBtn.setForeground(Color.BLACK);
		}
	}
	
	
	
	public void actionPerformed(ActionEvent ae)
	{
		
		String command = ae.getActionCommand();
		
		if(loginbutton.getText().equals(command))
		{
			
			
			//JOptionPane.showMessageDialog(this, "hi");
			login();
		}
		else if(regBtn.getText().equals(command))
		{
			Registration reg = new Registration();
			reg.setVisible(true);
			this.setVisible(false);
		}
		else if(ForgottenBtn.getText().equals(command))
		{
			ForgotPassword forgotten = new ForgotPassword();
			forgotten.setVisible(true);
			this.setVisible(false);
			
			
		}
		
	}
	
		private void login()
	{
		String email = emailTF.getText();
		String password = new String(passPF.getPassword());
		User user = null;
		if( !email.isEmpty() && !password.isEmpty()){
			
			
		try 
		{
			File file = new File("Userdata.txt");
			if(!file.exists())
			{
				JOptionPane.showMessageDialog(this, "No user regsitered yet");
				return;
			}
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			boolean loggedIn = false;
			
			while((line = reader.readLine()) != null)
			{
				String[] parts = line.split(",");
				if(parts[1].equals(email) &&parts[2].equals(password))
				{
					loggedIn = true;
					user = new User(parts[0], parts[1], parts[2],parts[3]);
					break;
				}
			}
			reader.close();
			
			if(loggedIn)
			{
				JOptionPane.showMessageDialog(this, "Login successful");
				dash Dash = new dash(user);
				//Dashboard dash = new Dashboard(user);
				Dash.setVisible(true);
				this.setVisible(false);
			}
			else{JOptionPane.showMessageDialog(this, "Password or Email is incorrect");}
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		}
		else{JOptionPane.showMessageDialog(this, "Fill all the information");}
	}	
}