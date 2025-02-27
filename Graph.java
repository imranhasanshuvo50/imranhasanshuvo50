import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Graph extends JPanel {

    private String[] xLabels = new String[100];  
    private int[] yData = new int[100];         
    private int dataSize = 0;              

    public Graph() {
        readDataFromFile("graphdata.txt");
    }

    private void readDataFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String date = parts[0].trim();
                    double sale = Double.parseDouble(parts[parts.length - 1].trim());

                    
                    boolean found = false;
                    for (int i = 0; i < dataSize; i++) {
                        if (xLabels[i].equals(date)) {
                          
                            yData[i] += (int) sale;
                            found = true;
                            break;
                        }
                    }

                    
                    if (!found) {
                        xLabels[dataSize] = date;
                        yData[dataSize] = (int) sale;
                        dataSize++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int padding = 50;
        int labelPadding = 25;
        int width = getWidth() - 2 * padding;
        int height = getHeight() - 2 * padding;

        int maxY = Arrays.stream(yData, 0, dataSize).max().orElse(1);

        // Draw axes
        g2d.drawLine(padding, padding, padding, height + padding);
        g2d.drawLine(padding, height + padding, width + padding, height + padding);

        // Y-axis labels
        for (int i = 0; i <= 10; i++) {
            int yPosition = height + padding - (i * height / 10);
            int yValue = maxY * i / 10;
            g2d.drawLine(padding - 5, yPosition, padding, yPosition);
            g2d.drawString(String.valueOf(yValue), padding - labelPadding, yPosition + 5);
        }

        // X-axis labels
        for (int i = 0; i < dataSize; i++) {
            int xPosition = padding + (i * width / (dataSize - 1));
            g2d.drawLine(xPosition, height + padding, xPosition, height + padding + 5);
            g2d.drawString(xLabels[i], xPosition - 20, height + padding + labelPadding);
        }

        if (dataSize == 0) return;

        int prevX = -1, prevY = -1;
        for (int i = 0; i < dataSize; i++) {
            int x = padding + (i * width / (dataSize - 1));
            int y = padding + height - (yData[i] * height / maxY);

            g2d.fillOval(x - 3, y - 3, 6, 6);
            if (prevX != -1 && prevY != -1) {
                g2d.drawLine(prevX, prevY, x, y);
            }

            prevX = x;
            prevY = y;
        }
    }

    public static void main(String[] args) {
       
            JFrame frame = new JFrame("Sales Progress Graph");
            Graph graph = new Graph();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(graph);
            frame.setSize(800, 600);
            frame.setVisible(true);
            }
}
