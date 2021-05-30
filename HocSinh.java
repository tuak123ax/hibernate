import java.io.Serializable;
import java.util.Vector;

public class HocSinh implements Serializable {
    private int MHS;
    private String TenHS;
    private float Diem;
    private String Hinhanh;
    private String DiaChi;
    private String Lop;
    Vector<Subject> listSub;
    private String username;
    private String password;
    public HocSinh() {

    }

    public HocSinh(int MHS,String TenHS,float Diem,String Hinhanh,String DiaChi,String Lop)
    {
        this.MHS=MHS;
        this.TenHS=TenHS;
        this.Diem=Diem;
        this.Hinhanh=Hinhanh;
        this.DiaChi=DiaChi;
        this.Lop=Lop;
    }
    public HocSinh(int MHS,String TenHS,float Diem,String Hinhanh,String DiaChi,String Lop,Vector<Subject>ListSub )
    {
        this.MHS=MHS;
        this.TenHS=TenHS;
        this.Diem=Diem;
        this.Hinhanh=Hinhanh;
        this.DiaChi=DiaChi;
        this.Lop=Lop;
        this.listSub=ListSub;
    }
    public int getMHS() {
        return MHS;
    }

    public void setMHS(int MHS) {
        this.MHS = MHS;
    }

    public String getTenHS() {
        return TenHS;
    }

    public void setTenHS(String tenHS) {
        TenHS = tenHS;
    }

    public float getDiem() {
        return Diem;
    }

    public void setDiem(float diem) {
        Diem = diem;
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

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Vector<Subject> getListSub() {
        return listSub;
    }

    public void setListSub(Vector<Subject> listSub) {
        this.listSub = listSub;
    }
}
