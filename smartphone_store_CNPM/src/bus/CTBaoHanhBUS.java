package bus;

import dao.CTBaoHanhDAO;
import dto.CTBaoHanhDTO;
import java.time.LocalDate;
import java.util.ArrayList;

public class CTBaoHanhBUS {
    private ArrayList<CTBaoHanhDTO> ctbhList;

    public CTBaoHanhBUS() {
    }
    

    public ArrayList<CTBaoHanhDTO> getCtbhList() {
        return ctbhList;
    }

    public void setCtbhList(ArrayList<CTBaoHanhDTO> ctbhList) {
        this.ctbhList = ctbhList;
    }
    
    public void list() {
        CTBaoHanhDAO ctbhDAO = new CTBaoHanhDAO(); 
        ctbhList = new ArrayList<>();
        ctbhList = ctbhDAO.list();
    }
    
     public void updateCTBaoHanh(CTBaoHanhDTO ctbh) {
        for (int i = 0; i < ctbhList.size(); i++) {
            if (ctbhList.get(i).getId_Ct_Bh().equals(ctbh.getId_Ct_Bh())) {
                ctbhList.set(i, ctbh);
                CTBaoHanhDAO ctBaoHanhDAO = new CTBaoHanhDAO();
                ctBaoHanhDAO.updateDB(ctbh);
                return;
            }
        }
    }
  
    public void addCTBaoHanh(CTBaoHanhDTO ctbh) {
        ctbhList.add(ctbh);
        CTBaoHanhDAO ctBaoHanhDAO = new CTBaoHanhDAO();
        ctBaoHanhDAO.addDB(ctbh);
    }
    

     public void deleteCTBaoHanh(String id) {
        for (CTBaoHanhDTO ctbh : ctbhList) {
            if (ctbh.getId_Ct_Bh().equals(id)) {
                ctbh.setEnable(false);
                CTBaoHanhDAO  ctBaoHanhDAO = new  CTBaoHanhDAO();
                 ctBaoHanhDAO.deleteDB(id);
                return;
            }
        }
    }

    
    public boolean isExisted(String id) {
        for (CTBaoHanhDTO ctbh : ctbhList) {
            if (ctbh.getId_Ct_Bh().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String createNewId() {
        String id = "BH";
        int new_id = ctbhList.size() + 1;

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
     public int tinhTienNhap(LocalDate dateBegin, LocalDate dateEnd) {
        int sum = 0;
        
        for (CTBaoHanhDTO ctbh : ctbhList) {
            if ((ctbh.getNgayNhan().isAfter(dateBegin) || ctbh.getNgayNhan().isEqual(dateBegin)) && (ctbh.getNgayNhan().isBefore(dateEnd) || ctbh.getNgayNhan().isEqual(dateEnd))) {
                sum += ctbh.getChiPhi();
            }
        }
        
        return sum;
    }
}
