import java.io.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

public class StoreManagement {
    private ArrayList<Staff> staffs;
    private ArrayList<String> workingTime;
    private ArrayList<Invoice> invoices;
    private ArrayList<InvoiceDetails> invoiceDetails;
    private ArrayList<Drink> drinks;

    public StoreManagement(String staffPath, String workingTimePath, String invoicesPath, String detailsPath, String drinksPath) {
        this.staffs = loadStaffs(staffPath);
        this.workingTime = loadFile(workingTimePath);
        this.invoices = loadInvoices(invoicesPath);
        this.invoiceDetails = loadInvoicesDetails(detailsPath);
        this.drinks = loadDrinks(drinksPath);
    }

    public ArrayList<Staff> getStaffs() {
        return this.staffs;
    }

    public void setStaffs(ArrayList<Staff> staffs){
        this.staffs = staffs;
    }
    
    public ArrayList<Drink> loadDrinks(String filePath) {
        ArrayList<Drink> drinksResult = new ArrayList<Drink>();
        ArrayList<String> drinks = loadFile(filePath);

        for (String drink : drinks) {
            String[] information = drink.split(",");
            drinksResult.add(new Drink(information[0], Integer.parseInt(information[1])));
        }

        return drinksResult;
    }

    public ArrayList<Invoice> loadInvoices(String filePath) {
        ArrayList<Invoice> invoiceResult = new ArrayList<Invoice>();
        ArrayList<String> invoices = loadFile(filePath);

        for (String invoice : invoices) {
            String[] information = invoice.split(",");
            invoiceResult.add(new Invoice(information[0], information[1], information[2]));
        }

        return invoiceResult;
    }

    public ArrayList<InvoiceDetails> loadInvoicesDetails(String filePath) {
        ArrayList<InvoiceDetails> invoiceResult = new ArrayList<InvoiceDetails>();
        ArrayList<String> invoices = loadFile(filePath);

        for (String invoice : invoices) {
            String[] information = invoice.split(",");
            invoiceResult.add(new InvoiceDetails(information[0], information[1], Integer.parseInt(information[2])));
        }

        return invoiceResult;
    }

    // requirement 1
    public ArrayList<Staff> loadStaffs(String filePath) {
        staffs = new ArrayList<Staff>();
        ArrayList<String> listStaff = loadFile(filePath);

        for (String st : listStaff) {
            String[] information = st.split(",");
            int x = information.length;
            if (x == 3) {
                staffs.add(new SeasonalStaff(information[0], information[1], Integer.parseInt(information[2])));
            } else if (x == 4) {
                staffs.add(new FullTimeStaff(information[0], information[1], Integer.parseInt(information[2]), Double.parseDouble(information[3])  ));
            } else {
                staffs.add(new Manager(information[0], information[1], Integer.parseInt(information[2]), Double.parseDouble(information[3]), Integer.parseInt(information[4])));
            }
            
        }
        return staffs;
    }

    // requirement 2
    private ArrayList<TimeKeeping> timeKepResult = loadTimekeeping("./input/Timekeeping.txt");
    private ArrayList<TimeKeeping> listPartTime;
    private ArrayList<SeasonalStaff> listPartStaff;
    //
    private ArrayList<FullTimeStaff> listFull_Staff = new ArrayList<FullTimeStaff>();
    private ArrayList<TimeKeeping> listFullTime = new ArrayList<TimeKeeping>();


    public ArrayList<TimeKeeping> loadTimekeeping(String filePath) {
        timeKepResult = new ArrayList<TimeKeeping>();
        ArrayList<String> times = loadFile(filePath);

        for (String t : times) {
            String[] information = t.split(",");
            timeKepResult.add(new TimeKeeping(information[0], Integer.parseInt(information[1]) ));
        }

        return timeKepResult;
    }

    public void sortPartTime() {
        
        listPartStaff = new ArrayList<SeasonalStaff>();
        listPartTime = new ArrayList<TimeKeeping>();

        for (Staff st : staffs) {
            String x = st.sID.charAt(0) + "" + st.sID.charAt(1);
            if(x.equals("TV")) {
                listPartStaff.add((SeasonalStaff) st);
            }
        }
        
        for (TimeKeeping t : timeKepResult) {
            String x = t.getsID().charAt(0) + "" + t.getsID().charAt(1);
            if(x.equals("TV")) {
                listPartTime.add(t);
            }
        }
    }

    //
    
    public void sortFullTime() {

        for (Staff st : staffs) {
            String x = st.sID.charAt(0) + "" + st.sID.charAt(1);
            if(!x.equals("TV")) {
                listFull_Staff.add((FullTimeStaff) st);
            } 
        }
        
        for (TimeKeeping t : timeKepResult) {
            String x = t.getsID().charAt(0) + "" + t.getsID().charAt(1);
            if(!x.equals("TV")) {
                listFullTime.add(t);
            } 
        }
    }


    public ArrayList<SeasonalStaff> getTopFiveSeasonalStaffsHighSalary() {
        sortPartTime();
        ArrayList<SeasonalStaff> top5 = new ArrayList<SeasonalStaff>();
        ArrayList<Double> kq = new ArrayList<Double>();

        for (int i = 0; i < listPartStaff.size(); i++) {
            for (int j = 0; j < listPartTime.size(); j++) {
                String x = listPartStaff.get(i).sID;
                String y = listPartTime.get(j).getsID();

                if (x.equals(y)) {
                    
                    double money = listPartStaff.get(i).paySalary(listPartTime.get(j).getTime() );
                    kq.add(money);
                    break;
                }
            }
        }

        for (int i = 0; i < kq.size() - 1; i++) {
            for (int j = i + 1; j < kq.size(); j++) {
                if (kq.get(i) < kq.get(j)) {
                    double temp = kq.get(i);
                    kq.set(i, kq.get(j));
                    kq.set(j, temp);

                    SeasonalStaff temp1 = listPartStaff.get(i);
                    listPartStaff.set(i, listPartStaff.get(j));
                    listPartStaff.set(j, temp1);

                }
            }
        }

        for (int i = 0; i < 5; i++) {
            top5.add(listPartStaff.get(i));
        
        }
        return top5;
    }

    // requirement 3
    public ArrayList<FullTimeStaff> getFullTimeStaffsHaveSalaryGreaterThan(int lowerBound) {
        sortFullTime();
        // for (int i = 0; i < listFull_Staff.size(); i++) {
        //     System.out.println(listFull_Staff.get(i));
        // }
        // for (int i = 0; i < listFullTime.size(); i++) {
        //     System.out.println(listFullTime.get(i));
        // }
        // ArrayList<Double> kq1 = new ArrayList<Double>();
        ArrayList<FullTimeStaff> listHigh = new ArrayList<FullTimeStaff>();
        
        for (int i = 0; i < listFull_Staff.size(); i++) {
            for (int j = 0; j < listFullTime.size(); j++) {
                String x = listFull_Staff.get(i).sID;
                String y = listFullTime.get(j).getsID();

                if (x.equals(y)) {
                    
                    double money = listFull_Staff.get(i).paySalary(listFullTime.get(j).getTime());
                    if (money > lowerBound) {
                        listHigh.add(listFull_Staff.get(i));
                    }
                    break;
                }
            }
        }
        return listHigh;
    }

    // requirement 4
    ArrayList<Double> totalBill = new ArrayList<Double>();

    public void totalBillMonth() {
        
        double money = 0;
        for (int i = 0; i < invoices.size(); i++) {
            for (int j = 0; j < invoiceDetails.size(); j++) {
                String x = invoices.get(i).getInvoiceID();
                String y = invoiceDetails.get(j).getInvoiceID();

                if (x.equals(y)) {
                    String nameDrink = invoiceDetails.get(j).getDName();  // name of details

                    for (int k = 0; k < drinks.size(); k++) {
                        String z = drinks.get(k).getdName(); //name of drinks
                        if (nameDrink.equals(z) ) {
                            money = money + invoiceDetails.get(j).getAmount() * drinks.get(k).getPrice();
                            break;
                        }
                    }
                }
            }
            totalBill.add(money);
            money = 0.0;

        }
        // for (int i = 0; i < totalBill.size(); i++) {
        //     System.out.println(i + ", " +totalBill.get(i));
        // }
    }
    
    public double totalInQuarter(int quarter) {
        double total = 0;
        
        totalBillMonth();

        for (int i = 0; i < invoices.size(); i++) {
            String date = invoices.get(i).getDate();
            String[] information = date.split("/");
            int month = Integer.parseInt(information[1]) ;

            if (quarter == 1 && month <= 3 && month > 0) {
               total = total + totalBill.get(i);
            } else if (quarter == 2 && month <= 6 && month >= 4) {
                total = total + totalBill.get(i);
            } else if (quarter == 3 && month <= 9 && month >= 7) {
                total = total + totalBill.get(i);
            } else if (quarter == 4 && month <= 12 && month >= 10) {
                total = total + totalBill.get(i);
            } 
        }   
        

        return total;
    }

    // requirement 5
    public Staff getStaffHighestBillInMonth(int month) {
        Staff maxStaff = null;
        //code here
        return maxStaff;
    }

    // load file as list
    public static ArrayList<String> loadFile(String filePath) {
        String data = "";
        ArrayList<String> list = new ArrayList<String>();

        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader fReader = new BufferedReader(reader);

            while ((data = fReader.readLine()) != null) {
                list.add(data);
            }

            fReader.close();
            reader.close();

        } catch (Exception e) {
            System.out.println("Cannot load file");
        }
        return list;
    }

    public void displayStaffs() {
        for (Staff staff : this.staffs) {
            System.out.println(staff);
        }
    }

    public <E> boolean writeFile(String path, ArrayList<E> list) {
        try {
            FileWriter writer = new FileWriter(path);
            for (E tmp : list) {
                writer.write(tmp.toString());
                writer.write("\r\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error.");
            return false;
        }

        return true;
    }

    public <E> boolean writeFile(String path, E object) {
        try {
            FileWriter writer = new FileWriter(path);

            writer.write(object.toString());
            writer.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error.");
            return false;
        }

        return true;
    }
}