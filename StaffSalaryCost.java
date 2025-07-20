public class StaffSalaryCost {
    public static double calculateCost(Staff staff) {
        return staff.getMinSalary() + (staff.getOtSalary() * staff.getOtHours());
    }
}