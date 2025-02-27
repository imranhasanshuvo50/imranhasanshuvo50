import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;
import java.io.IOException;

public class Dashboard extends JFrame implements ActionListener
{
	JLabel label;
	JTextField DateTF;
    JButton logoutBtn, addProductBtn, deleteProductBtn, editProductBtn, saveBtn,BackBtn,salesgrowthBtn;
    JPanel panel;
    JTable productTable;
    DefaultTableModel tablemodel;
    private User user; 
	
	
	public Dashboard(User user)
	{
		super("Dashboard");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.user=user;
		//panel = new JPanel();
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
		
		
	
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(450, 50, 100, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		addProductBtn = new JButton("Add Row");
        addProductBtn.setBounds(600, 100, 120, 30);
        addProductBtn.addActionListener(this);
        panel.add(addProductBtn);

        deleteProductBtn = new JButton("Delete Product");
        deleteProductBtn.setBounds(600, 200, 120, 30);
        deleteProductBtn.addActionListener(this);
        panel.add(deleteProductBtn);

        

        saveBtn = new JButton("Save Products");
        saveBtn.setBounds(600, 300, 120, 30);
        saveBtn.addActionListener(this);
        panel.add(saveBtn);
		
		BackBtn = new JButton("Back");
        BackBtn.setBounds(600, 350, 120, 30);
        BackBtn.addActionListener(this);
        panel.add(BackBtn);
		
		salesgrowthBtn = new JButton("Sales growth");
        salesgrowthBtn.setBounds(560, 450, 200, 30);
        salesgrowthBtn.addActionListener(this);
        panel.add(salesgrowthBtn);
		
		String[] columnNames = {"Product ID", "Product Name", "Price"};
        tablemodel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tablemodel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(50, 100, 500, 600); 
		
        panel.add(scrollPane);
		readproductdata();
		
		this.add(panel);
		this.setLocationRelativeTo(null);
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(logoutBtn.getText().equals(command))
		{
			loginpage log = new loginpage();
			log.setVisible(true);
			this.setVisible(false);
		}
		else if (command.equals("Add Row")) 
		{  String[] data = {"", "", ""};
            tablemodel.addRow(data);
        } else if (command.equals("Delete Product")) {  
            deleteProduct();
        } else if (command.equals("Save Products")) {  
            saveProducts();
        }
		else if (command.equals("Back")) {  
				dash Dash = new dash(user);				
				Dash.setVisible(true);
				this.setVisible(false);
        }
		else if (command.equals("Sales growth")) {  
			 JFrame frame = new JFrame("Sales Progress Graph");
            Graph graph = new Graph();
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(graph);
            frame.setSize(800, 600);
			this.setVisible(true);
            frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			
        }
	}
	
	public void readproductdata()
	
	{
		File file = new File("products.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    tablemodel.addRow(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
		
		
	}
	
	private void deleteProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            tablemodel.removeRow(selectedRow);
			saveProducts();
           
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to delete.");
        }
    }
	
	private void saveProducts() {
    if (productTable.isEditing()) {
        productTable.getCellEditor().stopCellEditing();
    }

    try {
        FileWriter writer = new FileWriter("products.txt", false);
        int rowCount = tablemodel.getRowCount();
        int columnCount = tablemodel.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            Object[] rowdata = new Object[columnCount];

            for (int col = 0; col < columnCount; col++) {
                rowdata[col] = tablemodel.getValueAt(row, col);
            }            
            if (isRowEmpty(rowdata)) {
                JOptionPane.showMessageDialog(this, "Fill all the cells. Row " + (row + 1) + " is incomplete.");
                writer.close();
                return; // Stop saving
            }            
            if (!isValidPrice(rowdata[2])) {
                JOptionPane.showMessageDialog(this, "Invalid price at row " + (row + 1));
                writer.close();
                return; // Stop saving
            }            
            String productID = rowdata[0].toString().trim();
            for (int prevRow = 0; prevRow < row; prevRow++) {
                String prevProductID = tablemodel.getValueAt(prevRow, 0).toString().trim();
                if (productID.equals(prevProductID)) {
                    JOptionPane.showMessageDialog(this, "Duplicate Product ID at row " + (row + 1));
                    writer.close();
                    return; 
                }
            }            
            writer.write(rowdata[0] + "," + rowdata[1] + "," + rowdata[2] + "\n");
        }

        writer.close();
        JOptionPane.showMessageDialog(this, "Products saved successfully.");
    } catch (IOException ex) 
			{
				ex.printStackTrace();
			}
}


	private boolean isValidPrice(Object price) {
    if (price instanceof Integer || price instanceof Float || price instanceof Double) 
	{
        return true;
    }
    try 
	{
        
        Double.parseDouble(price.toString());
        return true;
    } 
	catch (NumberFormatException e) 
	{
        return false; 
    }
	}
	
	
	private boolean isRowEmpty(Object[] rowdata) {
    for (Object cell : rowdata) {
        if (cell == null || cell.toString().trim().isEmpty()) {
            return true;
        }
    }
    return false;
}

	
	
}