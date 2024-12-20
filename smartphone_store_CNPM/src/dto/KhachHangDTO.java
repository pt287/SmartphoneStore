package dto;

public class KhachHangDTO {


    private String idKhachHang;
    private String tenKhachHang;    
    private String Sdt;
    private String Diachi;
    private boolean enable;

    public KhachHangDTO() {

    }

    public KhachHangDTO(String idKhachHang, String tenKhachHang,  String Sdt, String Diachi, boolean enable) {
        this.idKhachHang = idKhachHang;
        this.tenKhachHang = tenKhachHang;       
        this.Sdt = Sdt;
        this.Diachi = Diachi; 
        this.enable = enable;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getDiachi() {
        return Diachi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}