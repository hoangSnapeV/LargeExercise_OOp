public class TimeKeeping {
    private String sID;
    private int time;

    public TimeKeeping(String sName, int time) {
        this.sID = sName;
        this.time = time;
    }
    
    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return sID + "_" + time;
    }

}
