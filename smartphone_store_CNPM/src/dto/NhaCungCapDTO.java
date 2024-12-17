package dto;

public class NhaCungCapDTO {
    private String idNhaCungCap;
    private String tenNhaCungCap; 
    private String Sdt;
    private String Diachi;
    private boolean enable;

    public NhaCungCapDTO() {

    }

    public NhaCungCapDTO(String idNhaCungCap, String tenNhaCungCap, String Sdt, String Diachi, boolean enable) {
        this.idNhaCungCap = idNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.Sdt = Sdt;
        this.Diachi = Diachi;
        this.enable = enable;
    }

    public String getIdNhaCungCap() {
        return idNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public String getSdt() {
        return Sdt;
    }

    public String getDiachi() {
        return Diachi;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setIdNhaCungCap(String idNhaCungCap) {
        this.idNhaCungCap = idNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    
}    
