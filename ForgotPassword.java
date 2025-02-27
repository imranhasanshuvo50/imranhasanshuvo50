import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;



public class ForgotPassword extends JFrame implements ActionListener
{
	private String generatedOTP;
	JLabel userLabel, passLabel, emailLabel,otpLabel,passLabel1,userLabel2,userLabel3;
	JTextField userTF, emailTF,otpTF;
	JPasswordField passPF,passPF1;
	JButton resBtn, clearBtn, verifyBtn,backBtn;
	JPanel panel;
	JComboBox combo;
	
	
	
	public ForgotPassword()
	{
		super("Forgotte Password");
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
		
		//userLabel = new JLabel("Username : ");
		//userLabel.setBounds(50, 115, 160, 30);
		//panel.add(userLabel);
		
		//userTF = new JTextField();
		//userTF.setBounds(150, 115, 200, 30);
		//panel.add(userTF);
		
		
		
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
		
		passLabel1 = new JLabel("Re enter pass: ");
		passLabel1.setBounds(50, 315, 100, 30);
		panel.add(passLabel1);
		
		passPF1 = new JPasswordField();
		passPF1.setBounds(150, 315, 200, 30);
		panel.add(passPF1);
		
		resBtn = new JButton("Reset pass");
		resBtn.setBounds(220, 380, 100, 30);
		resBtn.addActionListener(this);
		panel.add(resBtn);
		
		clearBtn = new JButton("Clear");
		clearBtn.setBounds(125, 380, 80, 30);
		clearBtn.addActionListener(this);
		panel.add(clearBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(150, 430, 160, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		
		//String items[] = {"Admin", "Sales"};
		//combo = new JComboBox(items);
		//combo.setBounds(150, 315, 100, 30);
		//panel.add(combo);
				
		
		this.add(panel);
		this.setLocationRelativeTo(null);
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		String email = emailTF.getText().trim();
		if(resBtn.getText().equals(command))
		{
			resetPassword();
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
	
	
	private void resetPassword()
	{
		String email = emailTF.getText().trim();
		String otp = otpTF.getText();
		String newPassword = new String(passPF.getPassword());
		String confirmPassword = new String(passPF1.getPassword());

		if(email.isEmpty() || otp.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please fill all the fields");
			return;
		}
		if(!otp.equals(generatedOTP))
		{
			JOptionPane.showMessageDialog(this, "OTP is not correct");
			return;
		}
		if(!newPassword.equals(confirmPassword))
		{
			JOptionPane.showMessageDialog(this, "Passwords do not match");
			return;
		}
    
		try
		{
        File file = new File("Userdata.txt");
        File tempFile = new File("temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String line;
        boolean found = false;

        while((line = reader.readLine()) != null)
        {
            String[] parts = line.split(",");
            if(parts[1].equals(email))
            {
                line = parts[0] + "," + parts[1] + "," + newPassword + "," + parts[3];
                found = true;
            }
            writer.write(line + "\n");
        }
        reader.close();
        writer.close();

        if(found)
        {
            file.delete();
            tempFile.renameTo(file);
            JOptionPane.showMessageDialog(this, "Password Reset Successful");
            loginpage log = new loginpage();
            log.setVisible(true);
            this.setVisible(false);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Email not found");
        }
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}	
	
	
	private void clearFields()
	{
		passPF1.setText("");
		passPF.setText("");
		otpTF.setText("");
		emailTF.setText("");
	}
}