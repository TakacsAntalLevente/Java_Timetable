package base;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeTableApp {
    
    private static DefaultTableModel tableModel;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Miskolci Egyetem GÉIK - Programtervező informatikus II.É");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create a panel for input form
        JPanel formPanel = createFormPanel();
        
        
        


        // Create a timetable panel (center)
        JPanel tablePanel = createTablePanel();

        // Add form panel at the top and table panel in the center
        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }

    // Method to create the input form
    private static JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 7));  // 2 rows, 6 columns

        // Create labels and fields for input
        JLabel courseLabel = new JLabel("Tantargy:");
        JTextField courseField = new JTextField(10);
        
        JLabel dayLabel = new JLabel("Nap:");
        String[] days = {"Hétfő", "Kedd", "Szerda", "Csütörtök", "Péntek"};
        JComboBox<String> dayField = new JComboBox<>(days);

        JLabel startTimeLabel = new JLabel("Kezdés:");
        JTextField startTimeField = new JTextField(5);
        
        JLabel endTimeLabel = new JLabel("Befejezés:");
        JTextField endTimeField = new JTextField(5);
        
        JLabel roomLabel = new JLabel("Terem/Előadó:");
        JTextField roomField = new JTextField(10);

        JButton addButton = new JButton("Tantargy hozzá adása");
        



        // Add components to form panel
        formPanel.add(courseLabel);
        formPanel.add(courseField);
        formPanel.add(dayLabel);
        formPanel.add(dayField);
        formPanel.add(startTimeLabel);
        formPanel.add(startTimeField);
        formPanel.add(endTimeLabel);
        formPanel.add(endTimeField);
        formPanel.add(roomLabel);
        formPanel.add(roomField);
        formPanel.add(new JLabel());  // Spacer
        formPanel.add(addButton);  // Add button to the form

        // Add functionality to add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add course to timetable table
                String course = courseField.getText();
                String day = (String) dayField.getSelectedItem();
                String startTime = startTimeField.getText();
                String endTime = endTimeField.getText();
                String room = roomField.getText();

                if (validateInput(course, startTime, endTime, room)) {
                    tableModel.addRow(new Object[]{course, day, startTime, endTime, room});
                    // Clear fields after adding
                    courseField.setText("");
                    startTimeField.setText("");
                    endTimeField.setText("");
                    roomField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Hibás bemenet!");
                }
            }
        });

        return formPanel;
    }

    
    	

    // Method to create the timetable panel with JTable
    private static JPanel createTablePanel() {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        String[] columnNames = {"Tantargy", "Nap", "Kezdés", "Befejezés", "Terem/Előadó"};
        Object[][] data = {};  

        tableModel = new DefaultTableModel(data, columnNames);
        JTable timetableTable = new JTable(tableModel);
        
        timetableTable.setBackground(Color.gray);
        timetableTable.setForeground(Color.WHITE);
        timetableTable.setGridColor(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(timetableTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.gray);

        JButton deleteButton = new JButton("Törlés");
        JButton updateButton = new JButton("Frissítés");
        
        deleteButton.setBackground(Color.gray);
        deleteButton.setForeground(Color.WHITE);
        updateButton.setBackground(Color.gray);
        updateButton.setForeground(Color.WHITE);

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        tablePanel.add(buttonPanel, BorderLayout.SOUTH);

        
        deleteButton.addActionListener(e -> {
            int selectedRow = timetableTable.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Válassz egy sort amit ki akarsz törölni.");
            }
        });

        
        updateButton.addActionListener(e -> {
            int selectedRow = timetableTable.getSelectedRow();
            if (selectedRow >= 0) {
                String course = (String) tableModel.getValueAt(selectedRow, 0);
                String day = (String) tableModel.getValueAt(selectedRow, 1);
                String startTime = (String) tableModel.getValueAt(selectedRow, 2);
                String endTime = (String) tableModel.getValueAt(selectedRow, 3);
                String room = (String) tableModel.getValueAt(selectedRow, 4);

                
                String newCourse = JOptionPane.showInputDialog(null, "Tantárgy Frissítés", course);
                if (newCourse != null) {
                    tableModel.setValueAt(newCourse, selectedRow, 0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Válassz egy sort amit frissíteni akarsz..");
            }
        });

        return tablePanel;
    }

    
    private static boolean validateInput(String course, String startTime, String endTime, String room) {
        if (course.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || room.isEmpty()) {
            return false;
        }
        
        return true;
    }
}
