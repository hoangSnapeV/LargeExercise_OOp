public class TimeKeeping {
    private String sName;
    private int time;
    
    public TimeKeeping(String sName, int time) {
        this.sName = sName;
        this.time = time;
    }
    public String getsName() {
        return sName;
    }
    public void setsName(String sName) {
        this.sName = sName;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return sName + "_" + time;
    }
}
