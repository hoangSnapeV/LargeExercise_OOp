import java.util.*;
public class Test1 {
    public static void main(String[] args) {
        StoreManagement manager = new StoreManagement("./input/Staffs.txt", "./input/Timekeeping.txt", "./input/Invoices.txt", "./input/InvoiceDetails.txt", "./input/Drinks.txt");

        ArrayList<Drink> r1 = manager.loadDrinks("./input/Drinks.txt");

        // for (int i = 0; i < r1.size(); i++) {
        //     System.out.println(r1.get(i));

        // }
        ArrayList<Staff> r3 = manager.loadStaffs("./input/Staffs.txt");
        
        
        

        // for (int i = 0; i < r2.size(); i++) {
        //     System.out.println(r2.get(i));

        // }
        //manager.sortPartFullTime();
        //manager.getTopFiveSeasonalStaffsHighSalary();
        //manager.getFullTimeStaffsHaveSalaryGreaterThan(15000000);
        //manager.totalBillMonth();
        

        //manager.billStaffMonth(6);
        manager.getStaffHighestBillInMonth(6);

    }
}
