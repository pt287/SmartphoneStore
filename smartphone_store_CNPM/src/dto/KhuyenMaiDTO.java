package dto;


import java.time.LocalDate;


public class KhuyenMaiDTO {
	private String id_km;
	private String ten_km;
	private int tiLeGiamGia;
	private LocalDate ngay_bd;
	private LocalDate ngay_kt;
        private boolean enable;

    public KhuyenMaiDTO(String id_km, String ten_km, int tiLeGiamGia, LocalDate ngay_bd, LocalDate ngay_kt, boolean enable) {
        this.id_km = id_km;
        this.ten_km = ten_km;
        this.tiLeGiamGia = tiLeGiamGia;
        this.ngay_bd = ngay_bd;
        this.ngay_kt = ngay_kt;
        this.enable = enable;
    }
	
	public String getId_km() {
		return id_km;
	}
	public void setId_km(String id_km) {
		this.id_km = id_km;
	}
	
	public String getTen_km() {
		return ten_km;
	}
	public void setTen_km(String ten_km) {
		this.ten_km = ten_km;
	}

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

	public int getTiLeGiamGia() {
		return tiLeGiamGia;
	}
	public void setTiLeGiamGia(int tiLeGiamGia) {
		this.tiLeGiamGia = tiLeGiamGia;
	}

    public LocalDate getNgay_bd() {
        return ngay_bd;
    }

    public LocalDate getNgay_kt() {
        return ngay_kt;
    }

    public void setNgay_bd(LocalDate ngay_bd) {
        this.ngay_bd = ngay_bd;
    }

    public void setNgay_kt(LocalDate ngay_kt) {
        this.ngay_kt = ngay_kt;
    }
	
	
}

