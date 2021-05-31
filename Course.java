import java.io.Serializable;

public class Course implements Serializable {
    private String maMon;
    private String tenMon;
    private int soTinChi;
    private String GV;
    private String phongHoc;
    private String dayofWeek;
    private String Ca;
    private int maxSlot;
    public Course(){}
    public Course(String maMon,String tenMon,int soTinChi,String GV,String phongHoc,String dayofWeek,String Ca,int maxSlot)
    {
        this.maMon=maMon;
        this.tenMon=tenMon;
        this.soTinChi=soTinChi;
        this.GV=GV;
        this.phongHoc=phongHoc;
        this.dayofWeek=dayofWeek;
        this.Ca=Ca;
        this.maxSlot=maxSlot;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getGV() {
        return GV;
    }

    public void setGV(String GV) {
        this.GV = GV;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public String getDayofWeek() {
        return dayofWeek;
    }

    public void setDayofWeek(String dayofWeek) {
        this.dayofWeek = dayofWeek;
    }

    public String getCa() {
        return Ca;
    }

    public void setCa(String ca) {
        Ca = ca;
    }

    public int getMaxSlot() {
        return maxSlot;
    }

    public void setMaxSlot(int maxSlot) {
        this.maxSlot = maxSlot;
    }
}
