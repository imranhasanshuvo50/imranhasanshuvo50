import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class dash extends JFrame implements ActionListener {
    JLabel label, datelabel, idlabel, unitlabel;
    JTextField DateTF, idTF, unitTF;
    JButton logoutBtn, addProductBtn, deleteProductBtn, CheckoutBtn,calculateProductBtn,adminpannelBtn;
    JPanel panel;
    JTable productTable,productTable1;
    DefaultTableModel tablemodel1,tablemodel;
	double Grandtotal =0 ;
	private User user;

    public dash(User user) {
        super("Dashboard");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.user=user;
        panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("pic/dash.jpg");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        label = new JLabel(" Welcome to dashboard " + user.getName());
        label.setBounds(150, 50, 300, 30);
        label.setOpaque(true);
        label.setBackground(Color.RED);
        label.setForeground(Color.WHITE);
        panel.add(label);

        datelabel = new JLabel("Date");
        datelabel.setBounds(550, 50, 30, 30);
        datelabel.setOpaque(true);
        panel.add(datelabel);
		
		
		DateTF = new JTextField();
        DateTF.setBounds(580, 50, 100, 29);
        DateTF.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        panel.add(DateTF);

        idlabel = new JLabel("Product ID");
        idlabel.setBounds(580, 100, 70, 30);
        idlabel.setOpaque(true);
        panel.add(idlabel);

        idTF = new JTextField();
        idTF.setBounds(650, 100, 80, 29);
        panel.add(idTF);

        unitlabel = new JLabel("Units");
        unitlabel.setBounds(580, 150, 70, 30);
        unitlabel.setOpaque(true);
        panel.add(unitlabel);

        unitTF = new JTextField();
        unitTF.setBounds(650, 150, 80, 29);
        panel.add(unitTF);

        

        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(450, 50, 100, 30);
        logoutBtn.addActionListener(this);
        panel.add(logoutBtn);

        addProductBtn = new JButton("Add Product");
        addProductBtn.setBounds(600, 220, 120, 30);
        addProductBtn.addActionListener(this);
        panel.add(addProductBtn);
		
		
		calculateProductBtn = new JButton("calculate Product");
        calculateProductBtn.setBounds(600, 245, 120, 30);
        calculateProductBtn.addActionListener(this);
        panel.add(calculateProductBtn);



        deleteProductBtn = new JButton("Delete Product");
        deleteProductBtn.setBounds(600, 300, 120, 30);
        deleteProductBtn.addActionListener(this);
        panel.add(deleteProductBtn);
		
		

        CheckoutBtn = new JButton("Checkout");
        CheckoutBtn.setBounds(600, 350, 120, 30);
        CheckoutBtn.addActionListener(this);
        panel.add(CheckoutBtn);
		
		adminpannelBtn = new JButton("👨🏻‍💻 Admin Pannel 👨🏻‍💻");
        adminpannelBtn.setBounds(560, 650, 200, 30);
        adminpannelBtn.addActionListener(this);
        panel.add(adminpannelBtn);
		

        String[] columnNames = {"Product Name", "Unit Price", "Units", "Total Price"};
        tablemodel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tablemodel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(50, 100, 490, 600);
        panel.add(scrollPane);
		
		String[] columnnames = {"", "", "", ""};
        tablemodel1 = new DefaultTableModel(columnnames, 0);
        productTable1 = new JTable(tablemodel1);
        JScrollPane scrollPane1 = new JScrollPane(productTable1);
        scrollPane1.setBounds(50, 700, 490, 25);
        panel.add(scrollPane1);

      

        this.add(panel);
        this.setLocationRelativeTo(null);
    }
	
	public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == logoutBtn) {
            new loginpage().setVisible(true);
            this.setVisible(false);
        } else if (ae.getSource() == deleteProductBtn) {
            deleteProduct();
        } else if (ae.getSource() == CheckoutBtn) {
			calculateProduct();
			if (Grandtotal!=0)
			{
            checkoutProducts();
			dash Dash = new dash(user);				
			Dash.setVisible(true);
			this.setVisible(false);
			}
			else{JOptionPane.showMessageDialog(this, "Add at least one product" );}
        }
		
		 else if (ae.getSource() == calculateProductBtn) {
			 
            calculateProduct();
        }
		 else if (ae.getSource() == adminpannelBtn) {
			 if(user.getAtype().equals("Admin"))
			 {
				Dashboard dashbord = new Dashboard(user);
				dashbord.setVisible(true);
				this.setVisible(false);
			 }
			 else { JOptionPane.showMessageDialog(this, "You are not an admin" );}
        }
		
		else if (ae.getSource() == addProductBtn) {
			
			if (unitTF.getText().trim().isEmpty() ||unitTF.getText().trim().isEmpty() ) {
			JOptionPane.showMessageDialog(this, "Fill all the fild" );
			}
			else{			
            searchAndAddProduct();
			unitTF.setText("");
			idTF.setText("");
		
			}
        }
    }

    private void searchAndAddProduct() {
        String productId = idTF.getText().trim();
        String unitsStr = unitTF.getText().trim();
        int units;
		if (unitsStr.isEmpty()) {
			units = 0;
			} 
		else {
			units = Integer.parseInt(unitsStr);
			}

        try (BufferedReader br = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3 && data[0].trim().equals(productId)) {
                    String productName = data[1].trim();
                    double unitPrice = Double.parseDouble(data[2].trim());
                    double totalPrice = unitPrice * units;
                    tablemodel.addRow(new Object[]{productName, unitPrice, units, totalPrice});
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Product ID not found.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
        }
    }

    

    private void deleteProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            tablemodel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to delete.");
        }
    }

    private void checkoutProducts() {
       // double grandTotal = 0;
       // for (int i = 0; i < tablemodel.getRowCount(); i++) {
       //     grandTotal += (double) tablemodel.getValueAt(i, 3);
       // }

        try (FileWriter writer = new FileWriter("graphdata.txt", true)) {
			String date = DateTF.getText();
             writer.write(date + "," + Grandtotal + "\n");
            JOptionPane.showMessageDialog(this, "Checkout successful! Grand Total: " + Grandtotal);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage());
        }
    }
	
	
	private void calculateProduct() {
		double totalupdateprice;
		double tGrandtotal=0;
		
		if (productTable.isEditing()) {
        productTable.getCellEditor().stopCellEditing();
		}

		for (int i = 0; i < tablemodel.getRowCount(); i++) {
        try {
            double unitPrice = Double.parseDouble(tablemodel.getValueAt(i, 1).toString());
            int units = Integer.parseInt(tablemodel.getValueAt(i, 2).toString());

            totalupdateprice = unitPrice * units;
            tablemodel.setValueAt(totalupdateprice, i, 3); 

            tGrandtotal += totalupdateprice;
        } 
		catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input in Units column or unit price ,Please enter a number.");
            return;
        }
		}
		Grandtotal=tGrandtotal;
		if (tablemodel1.getRowCount() == 0) 
		{
			tablemodel1.addRow(new Object[]{"", "", "Grand Total", Grandtotal});
		} 
		else 
		{
        tablemodel1.setValueAt(Grandtotal, 0, 3);  // Update the existing row
			}

        
    }

    
}