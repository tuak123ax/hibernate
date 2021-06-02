import java.io.Serializable;

public class Teacher implements Serializable {
    private int MGV;
    private String TenGV;
    private int Tuoi;
    private String Hinhanh;
    private String DiaChi;
    private String GhiChu;
    private Account account;
    public Teacher() {

    }

    public Teacher(int MGV,String TenGV,int Tuoi,String Hinhanh,String DiaChi,String GhiChu)
    {
        this.MGV=MGV;
        this.TenGV=TenGV;
        this.Tuoi=Tuoi;
        this.Hinhanh=Hinhanh;
        this.DiaChi=DiaChi;
        this.GhiChu=GhiChu;
    }
    public Teacher(int MGV,String TenGV,int Tuoi,String Hinhanh,String DiaChi,String GhiChu,Account account)
    {
        this.MGV=MGV;
        this.TenGV=TenGV;
        this.Tuoi=Tuoi;
        this.Hinhanh=Hinhanh;
        this.DiaChi=DiaChi;
        this.GhiChu=GhiChu;
        this.account=account;
    }
    public int getMGV() {
        return MGV;
    }

    public void setMGV(int MGV) {
        this.MGV = MGV;
    }

    public String getTenGV() {
        return TenGV;
    }

    public void setTenGV(String tenGV) {
        TenGV = tenGV;
    }

    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int tuoi) {
        Tuoi = tuoi;
    }

    public String getHinhanh() {
        return Hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        Hinhanh = hinhanh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
