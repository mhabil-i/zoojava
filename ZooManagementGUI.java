import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ZooManagementGUI extends JFrame {
    // Core data managers (unchanged from your console version)
    private AnimalInfo animalInfo = new AnimalInfo();
    private StaffInfo staffInfo = new StaffInfo();
    
    private DefaultListModel<Animal> animalListModel = new DefaultListModel<>();
    private DefaultListModel<Staff> staffListModel = new DefaultListModel<>();
    
    // GUI Components
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextArea dashboardArea = new JTextArea(15, 60);
    private JList<Animal> animalList = new JList<>(animalListModel);
    private JList<Staff> staffList = new JList<>(staffListModel);
    private JTextArea budgetArea = new JTextArea(20, 60);
    
    public ZooManagementGUI() {
        super("Jempol Zoo Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize data
        animalInfo = new AnimalInfo();
        staffInfo = new StaffInfo();
        
        // Build interface
        createDashboardTab();
        createAnimalsTab();
        createStaffTab();
        createBudgetTab();
        
        add(tabbedPane);
        refreshAllData();
    }
    
    private void createDashboardTab() {
        JPanel panel = new JPanel(new BorderLayout());
        dashboardArea.setEditable(false);
        panel.add(new JScrollPane(dashboardArea), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1,3));
        
        JButton refreshBtn = new JButton("Refresh Data");
        refreshBtn.addActionListener(e -> refreshAllData());
        
        JButton saveBtn = new JButton("Save All Data");
        saveBtn.addActionListener(e -> saveAllData() );
        
        JButton restoreBtn = new JButton("Restore From GitHub");
        restoreBtn.addActionListener(e -> restoreAllData() );
        
        buttonPanel.add(refreshBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(restoreBtn);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        tabbedPane.addTab("Dashboard", panel);
    }
    
    private void saveAllData() {
        try {
            // Save animals and staff
            animalInfo.saveAnimalFile("Animal_Data.txt");
            staffInfo.saveStaffFile("Staff_Data.txt");
            
            JOptionPane.showMessageDialog(this, 
                "All data saved successfully!\n" +
                "Animals: " + animalInfo.getAnimals().size() + " records\n" +
                "Staff: " + staffInfo.getStaffList().size() + " records",
                "Save Successful",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error saving data: " + ex.getMessage(),
                "Save Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void restoreAllData() {
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Restore from GitHub? This will overwrite current data.",
            "Confirm Restore",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Restore both datasets
                animalInfo.restoreAnimalData();
                staffInfo.restoreStaffData();
                
                // Refresh UI with new data
                refreshAllData();
                
                JOptionPane.showMessageDialog(this, 
                    "Data restored successfully!\n" +
                    "Animals: " + animalInfo.getAnimals().size() + " records\n" +
                    "Staff: " + staffInfo.getStaffList().size() + " records",
                    "Restore Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Restore failed: " + ex.getMessage(),
                    "Restore Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    private void createAnimalsTab() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Animal list
        
        animalList.setCellRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                   boolean isSelected, boolean cellHasFocus) {
                Animal a = (Animal)value;
                return super.getListCellRendererComponent(list, 
                        a.getName() + " (" + a.getDietType() + ")", index, isSelected, cellHasFocus);
            }
        });
        panel.add(new JScrollPane(animalList), BorderLayout.CENTER);
        
        // Control buttons
        JPanel btnPanel = new JPanel();
        
        JButton addBtn = new JButton("Add Animal");
        addBtn.addActionListener(e -> addAnimal());
        btnPanel.add(addBtn);
        
        JButton removeBtn = new JButton("Remove Selected");
        removeBtn.addActionListener(e -> removeAnimal());
        btnPanel.add(removeBtn);
        
        panel.add(btnPanel, BorderLayout.SOUTH);
        tabbedPane.addTab("Animals", panel);
    }
    
    private void createStaffTab() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Staff list
        
        staffList.setCellRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                   boolean isSelected, boolean cellHasFocus) {
                Staff s = (Staff)value;
                return super.getListCellRendererComponent(list, 
                        s.getName() + " (" + s.getStaffPosition() + ")", index, isSelected, cellHasFocus);
            }
        });
        panel.add(new JScrollPane(staffList), BorderLayout.CENTER);
        
        // Control buttons
        JPanel btnPanel = new JPanel();
        
        JButton addBtn = new JButton("Add Staff");
        addBtn.addActionListener(e -> addStaff());
        btnPanel.add(addBtn);
        
        JButton removeBtn = new JButton("Remove Selected");
        removeBtn.addActionListener(e -> removeStaff());
        btnPanel.add(removeBtn);
        
        panel.add(btnPanel, BorderLayout.SOUTH);
        tabbedPane.addTab("Staff", panel);
    }
    
    private void createBudgetTab() {
        JPanel panel = new JPanel(new BorderLayout());
        budgetArea.setEditable(false);
        panel.add(new JScrollPane(budgetArea), BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel();
        
        JButton generateBtn = new JButton("Generate Report");
        generateBtn.addActionListener(e -> generateBudgetReport());
        btnPanel.add(generateBtn);
        
        JButton exportBtn = new JButton("Export to File");
        exportBtn.addActionListener(e -> new BudgetDraft(animalInfo, staffInfo).exportBudgetFile("zoo_budget.txt"));
        btnPanel.add(exportBtn);
        
        panel.add(btnPanel, BorderLayout.SOUTH);
        tabbedPane.addTab("Budget", panel);
    }
    
    // Action methods
    private void refreshAllData() {
        SwingUtilities.invokeLater(() -> {
        // Update animal list
        animalListModel.clear();
        for (Animal animal : animalInfo.getAnimals()) {
            animalListModel.addElement(animal);
        }
        
        // Update staff list
        staffListModel.clear();
        for (Staff staff : staffInfo.getStaffList()) {
            staffListModel.addElement(staff);
        }
        
        // Update dashboard text
        dashboardArea.setText(SystemInfo.getWelcomeMessage(animalInfo, staffInfo));
        });
    }
    
    private void addAnimal() {
        JPanel form = new JPanel(new GridLayout(4, 2));
        
        JTextField nameField = new JTextField();
        JComboBox<DietType> dietBox = new JComboBox<>(DietType.values());
        JTextField weightField = new JTextField();
        JTextField soundField = new JTextField();
        
        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Diet Type:"));
        form.add(dietBox);
        form.add(new JLabel("Food Weight (kg):"));
        form.add(weightField);
        form.add(new JLabel("Sound:"));
        form.add(soundField);
        
        int result = JOptionPane.showConfirmDialog(this, form, "Add Animal", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Animal animal = new Animal(
                    nameField.getText(),
                    (DietType)dietBox.getSelectedItem(),
                    Double.parseDouble(weightField.getText()),
                    soundField.getText()
                );
                animalInfo.getAnimals().add(animal);
                refreshAllData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format!");
            }
        }
    }
    
    private void removeAnimal() {
        int index = animalList.getSelectedIndex();
        if (index >= 0) {
            animalInfo.getAnimals().remove(index);
            refreshAllData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an animal first!");
        }
    }
    
    private void addStaff() {
        JPanel form = new JPanel(new GridLayout(5, 2));
        
        JTextField nameField = new JTextField();
        JComboBox<StaffPosition> positionBox = new JComboBox<>(StaffPosition.values());
        JTextField salaryField = new JTextField();
        JTextField otRateField = new JTextField();
        JTextField otHoursField = new JTextField();
        
        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Position:"));
        form.add(positionBox);
        form.add(new JLabel("Base Salary:"));
        form.add(salaryField);
        form.add(new JLabel("OT Rate:"));
        form.add(otRateField);
        form.add(new JLabel("OT Hours:"));
        form.add(otHoursField);
        
        int result = JOptionPane.showConfirmDialog(this, form, "Add Staff", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Staff staff = new Staff(
                    nameField.getText(),
                    (StaffPosition)positionBox.getSelectedItem(),
                    Double.parseDouble(salaryField.getText()),
                    Double.parseDouble(otRateField.getText()),
                    Integer.parseInt(otHoursField.getText())
                );
                staff.validateSalary();
                
                staffInfo.getStaffList().add(staff);
                refreshAllData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format!");
            } catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
    
    private void removeStaff() {
        int index = staffList.getSelectedIndex();
        if (index >= 0) {
            staffInfo.getStaffList().remove(index);
            refreshAllData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a staff member first!");
        }
    }
    
    private void generateBudgetReport() {
        BudgetDraft draft = new BudgetDraft(animalInfo, staffInfo);
        budgetArea.setText("==== JEMPOL ZOO BUDGET REPORT ====\n\n");
        
        // Animal costs
        budgetArea.append("ANIMAL FOOD COSTS:\n");
        for (Animal a : animalInfo.getAnimals()) {
            budgetArea.append("• " + a.getName() + ": RM" + 
                            AnimalFoodCost.calculateCost(a) + "\n");
        }
        
        // Staff costs
        budgetArea.append("\nSTAFF SALARIES:\n");
        for (Staff s : staffInfo.getStaffList()) {
            budgetArea.append("• " + s.getName() + ": RM" + s.totalSalary() + "\n");
        }
        
        // Totals
        budgetArea.append("\nTOTALS:\n");
        budgetArea.append("Animal Food Total: RM" + draft.getTotalAnimalFoodCost() + "\n");
        budgetArea.append("Staff Salary Total: RM" + draft.getTotalStaffCost() + "\n");
        budgetArea.append("GRAND TOTAL: RM" + (draft.getTotalAnimalFoodCost() + draft.getTotalStaffCost()));
    }
    
    // Entry point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ZooManagementGUI().setVisible(true);
        });
    }
}
