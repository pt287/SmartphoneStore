package bus;

import dao.BaoHanhDAO;
import dto.BaoHanhDTO;
import java.time.LocalDate;
import java.util.ArrayList;

public class BaoHanhBUS {
    private ArrayList<BaoHanhDTO> bhList;

    public ArrayList<BaoHanhDTO> getBhList() {
        return bhList;
    }

    public void setBhList(ArrayList<BaoHanhDTO> bhList) {
        this.bhList = bhList;
    }
    
    public void list() {
        BaoHanhDAO bhDAO = new BaoHanhDAO();
        bhList = new ArrayList<>();
        bhList = bhDAO.list();
    }
    
    public void addBH(BaoHanhDTO ctsp) {
        bhList.add(ctsp);
        BaoHanhDAO bhDAO = new BaoHanhDAO();
        bhDAO.addDB(ctsp);
    }
    
    public boolean isExisted(String id) {
        for (BaoHanhDTO bh : bhList) {
            if (bh.getSerial().equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<BaoHanhDTO> filter(LocalDate dateFrom, LocalDate dateTo) {
        ArrayList<BaoHanhDTO> res = new ArrayList<>();
        
        for (BaoHanhDTO bh : bhList) {
            if ((bh.getNgayMua().isAfter(dateFrom) || bh.getNgayMua().isEqual(dateFrom)) && (bh.getNgayMua().isBefore(dateTo) || bh.getNgayMua().isEqual(dateTo))) {
                res.add(bh);
            }
        }
        
        return res;
        
    }

}
