package bus;

import dao.KhuyenMaiDAO;
import dto.KhuyenMaiDTO;
import java.util.ArrayList;

public class KhuyenMaiBUS {
    private ArrayList<KhuyenMaiDTO> kmList;

    public ArrayList<KhuyenMaiDTO> getKmList() {
        return kmList;
    }
    
    public void setKmList(ArrayList<KhuyenMaiDTO> kmList) {
        this.kmList = kmList;
    }
    
    public void list() {
        KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
        kmList = new ArrayList<KhuyenMaiDTO>();
        kmList = kmDAO.list();
    }
    
    public void updateKhuyenMai(KhuyenMaiDTO km) {
        for (int i = 0; i < kmList.size(); i++) {
            if (kmList.get(i).getId_km().equals(km.getId_km())) {
                kmList.set(i, km);
                KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
                kmDAO.updateDB(km);
                return;
            }
        }
    }
    public void addKhuyenMai(KhuyenMaiDTO km) {
        kmList.add(km);
        KhuyenMaiDAO khachHangDAO = new KhuyenMaiDAO();
        khachHangDAO.addDB(km);
    }

    public void deleteKhuyenMai(String id) {
        for (KhuyenMaiDTO km : kmList) {
            if (km.getId_km().equals(id)) {
                km.setEnable(false);
                KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
                khuyenMaiDAO.deleteDB(id);
                return;
            }
        }
    }
    public boolean isExisted(String id) {
        for (KhuyenMaiDTO km : kmList) {
            if (km.getId_km().equals(id)) {
                return true;
            }
        }
        return false;
    }
    public String createNewId() {
        String id = "KM";
        int new_id = kmList.size() + 1;

        if (new_id <= 9) {
            id += "00" + new_id;
        }
        else if (new_id <= 99) {
            id += "0" + new_id;
        }
        else {
            id += new_id;
        }

        return id;
    }
}