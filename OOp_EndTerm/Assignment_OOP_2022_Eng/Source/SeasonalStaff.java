public class SeasonalStaff extends Staff{
    private int hourlyWage;

    public SeasonalStaff(String sID, String sName, int hourlyWage) {
        super(sID, sName);
        this.hourlyWage = hourlyWage;
    }

    

    public int getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(int hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    @Override
    public double paySalary(int workingTime) {
        
        return hourlyWage * workingTime * 1.0;
    }
    @Override
    public String toString() {
        return sID + "_" + sName + "_" + hourlyWage;
    }
    
}
