import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;



public class Registration extends JFrame implements ActionListener
{
	private String generatedOTP;
	JLabel userLabel, passLabel, emailLabel,otpLabel,CatagoryLabel,userLabel2,userLabel3;
	JTextField userTF, emailTF,otpTF;
	JPasswordField passPF;
	JButton regBtn, clearBtn, verifyBtn,backBtn;
	JPanel panel;
	JComboBox combo;
	
	
	
	public Registration()
	{
		super("Registration");
		this.setSize(450, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		panel = new JPanel() {
            
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("pic/reg.jpg");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
		
		panel.setLayout(null);
		
		userLabel2 = new JLabel("Friends For Ever Fharma");
		userLabel2.setBounds(50, 10, 350, 50);
		userLabel2.setOpaque(false);
		userLabel2.setForeground(new Color(6,57,112));
		userLabel2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 25));
		panel.add(userLabel2);
		
		userLabel3 = new JLabel("Register Account");
		userLabel3.setBounds(120, 40, 350, 50);		
		userLabel3.setOpaque(false);
		userLabel3.setForeground(Color.BLACK);
		userLabel3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		panel.add(userLabel3);
		
		userLabel = new JLabel("Username : ");
		userLabel.setBounds(50, 115, 160, 30);
		panel.add(userLabel);
		
		userTF = new JTextField();
		userTF.setBounds(150, 115, 200, 30);
		panel.add(userTF);
		
		
		
		emailLabel = new JLabel("Email : ");
		emailLabel.setBounds(50, 165, 160, 30);
		panel.add(emailLabel);
		
		emailTF = new JTextField();
		emailTF.setBounds(150, 165, 200, 30);
		panel.add(emailTF);
		
		verifyBtn = new JButton("Get OTP");
		verifyBtn.setBounds(350, 165, 80, 30);
		verifyBtn.addActionListener(this);
		panel.add(verifyBtn);
		
		otpLabel = new JLabel("OTP : ");
		otpLabel.setBounds(50, 215, 160, 30);
		panel.add(otpLabel);
		
		otpTF = new JTextField();
		otpTF.setBounds(150, 215, 200, 30);
		panel.add(otpTF);
		
		passLabel = new JLabel("Password : ");
		passLabel.setBounds(50, 265, 100, 30);
		panel.add(passLabel);
		
		passPF = new JPasswordField();
		passPF.setBounds(150, 265, 200, 30);
		panel.add(passPF);
		
		
		
		regBtn = new JButton("Register");
		regBtn.setBounds(220, 380, 100, 30);
		regBtn.addActionListener(this);
		panel.add(regBtn);
		
		clearBtn = new JButton("Clear");
		clearBtn.setBounds(125, 380, 80, 30);
		clearBtn.addActionListener(this);
		panel.add(clearBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(150, 430, 160, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		CatagoryLabel = new JLabel("Account type : ");
		CatagoryLabel.setBounds(50, 315, 100, 30);
		panel.add(CatagoryLabel);
		
		String items[] = {"Admin", "Sales"};
		combo = new JComboBox(items);
		combo.setBounds(150, 315, 100, 30);
		panel.add(combo);
				
		
		this.add(panel);
		this.setLocationRelativeTo(null);
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		String email = emailTF.getText().trim();
		if(regBtn.getText().equals(command))
		{
			register();
		}
		else if(clearBtn.getText().equals(command))
		{
			clearFields();
		}
		else if(verifyBtn.getText().equals(command))
		{
			if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter your email");
                    return;
                }
			if(!checkInternet.isInternetAvailable())
				{JOptionPane.showMessageDialog(this, "No Internet");
                    return;
				}
			else {
                    generatedOTP = OTPGenerator.generateOTP(6);
                    EmailSender.sendEmail(email, generatedOTP);
                    JOptionPane.showMessageDialog(this,"OTP Sent to " + email);
					return;
                }
		}
		
		else if(backBtn.getText().equals(command))
		{
			loginpage log = new loginpage();
			log.setVisible(true);
			this.setVisible(false);
		}
	}
	
	emailchacker boolemail = new emailchacker();
	
	
	private void register()
	{
		String name = userTF.getText();
		String email = emailTF.getText().trim();
		String password = new String(passPF.getPassword());
		String selected = (String) combo.getSelectedItem();
		String otp = otpTF.getText();
		if(name.isEmpty() || email.isEmpty() || password.isEmpty() || otp.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please fill all the fields");
			return;
		}
		
		if(emailchacker.isEmailExists(email))
		{
			JOptionPane.showMessageDialog(this, "This email is already taken");
			return;
		}
		if(!otp.equals(generatedOTP))
		{
			JOptionPane.showMessageDialog(this, "OTP is not correct");
			return;
		}
		User newUser = new User(name, email, password ,selected);
		
		try
		{
			FileWriter writer = new FileWriter("Userdata.txt", true);
			writer.write(newUser.getName() + "," + newUser.getEmail() + "," + newUser.getPassword() + "," + newUser.getAtype() +"\n");
			writer.close();
			
			JOptionPane.showMessageDialog(this, "Registration Successful");
			
			loginpage log = new loginpage();
			log.setVisible(true);
			this.setVisible(false);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	
	
	/*private boolean isEmailExists(String email)
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
	*/
	
	
	private void clearFields()
	{
		userTF.setText("");
		emailTF.setText("");
		passPF.setText("");
		otpTF.setText("");
	}
}