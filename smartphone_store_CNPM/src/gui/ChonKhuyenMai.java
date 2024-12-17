package gui;

import bus.KhachHangBUS;
import bus.KhuyenMaiBUS;
import dto.KhachHangDTO;
import dto.KhuyenMaiDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ChonKhuyenMai extends JDialog implements ActionListener {
    private Color colorBackground = Color.decode("#FFFFFF");
    private Color color1 = Color.decode("#800000");
    private Color color2 = Color.decode("#B22222");
    private Color color3 = Color.decode("#FF6347");
    
    private String idKM = "";
    private JButton btnChon, btnQuayVe;
    private TableRowSorter<TableModel> rowSorter;
    private JTable tableKM;
    private DefaultTableModel modelKM;
    private KhuyenMaiBUS khuyenmaiBUS = new KhuyenMaiBUS();

    
    public ChonKhuyenMai() {
        this.setModal(true);
        this.init();
    }
    
    public void init() {
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());        
        
        // tim kiem
        JPanel pn_tim_kiem = new JPanel(new FlowLayout(1, 10, 10));
        pn_tim_kiem.setPreferredSize(new Dimension(700, 50));
        JLabel lb_tim_kiem = new JLabel("Tìm kiếm", JLabel.CENTER);
        JTextField tf_tim_kiem = new JTextField();
        tf_tim_kiem.setPreferredSize(new Dimension(200, 30));
        tf_tim_kiem.getDocument().addDocumentListener(new DocumentListener() { 
            @Override
            public void insertUpdate(DocumentEvent e) {
                String txt = tf_tim_kiem.getText();
                if (txt.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                }
                else if (txt.trim().length() >= 2 && txt.trim().substring(0, 2).toUpperCase().equals("KH")) {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^" + txt, 0));                    
                } 
                else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^"+ txt +".*", 1));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String txt = tf_tim_kiem.getText();
                if (txt.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                }
                else if (txt.trim().length() >= 2 && txt.trim().substring(0, 2).toUpperCase().equals("KH")) {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^" + txt, 0));                    
                } 
                else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^"+ txt +".*", 1));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               
            }
        });
        
        Font font_filter = new Font("Segoe UI", Font.BOLD, 13);
        
        lb_tim_kiem.setFont(font_filter);
        tf_tim_kiem.setFont(font_filter);
        
        lb_tim_kiem.setForeground(color1);
        tf_tim_kiem.setForeground(color1);  
 
        pn_tim_kiem.add(lb_tim_kiem);
        pn_tim_kiem.add(tf_tim_kiem);
        
        // table
        JPanel pn_table = new JPanel(new FlowLayout(1, 25, 10));
        String[] col = {
            "Mã khuyến mãi", "Tên khuyến mãi", "Tỉ lệ giảm giá","Ngày kết thúc"
        };
        modelKM = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Tất cả các ô đều không thể chỉnh sửa
                return false;
            }
        };
        tableKM = new JTable();
        rowSorter = new TableRowSorter<TableModel>(modelKM);
        tableKM.setModel(modelKM);
        tableKM.setRowSorter(rowSorter);
        JScrollPane scroll = new JScrollPane(tableKM);
        scroll.setPreferredSize(new Dimension(550, 350));
        
        tableKM.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableKM.getColumnModel().getColumn(1).setPreferredWidth(250);
        tableKM.getColumnModel().getColumn(2).setPreferredWidth(200);
        
        loadKM();
        
        pn_table.add(pn_tim_kiem);
        pn_table.add(scroll);
        
        Font font_table = new Font("Segoe UI", Font.BOLD, 13);
        tableKM.getTableHeader().setBackground(color1);
        tableKM.getTableHeader().setFont(font_table);
        tableKM.getTableHeader().setForeground(this.colorBackground);
        tableKM.getTableHeader().setOpaque(false); 
        tableKM.getTableHeader().setBorder(BorderFactory.createLineBorder(this.color1));
        
        // căn giữa các chữ trong ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < col.length; i++) {
            tableKM.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        tableKM.setFocusable(false);
        tableKM.setShowVerticalLines(false);
        tableKM.setIntercellSpacing(new Dimension(0, 0));
        tableKM.setFillsViewportHeight(true);
        tableKM.setSelectionBackground(color3);
        tableKM.setRowHeight(30);
        tableKM.setBorder(BorderFactory.createLineBorder(this.color1));
        
        JPanel pn_btn = new JPanel(new FlowLayout(1));
        pn_btn.setPreferredSize(new Dimension(700, 50));
        
        btnChon = new JButton("Chọn");
        btnQuayVe = new JButton("Quay về");
        
        btnChon.setPreferredSize(new Dimension(150, 30));
        btnQuayVe.setPreferredSize(new Dimension(150, 30));
        btnChon.setBackground(color2);
        btnQuayVe.setBackground(color2);
        btnChon.setForeground(this.colorBackground);
        btnQuayVe.setForeground(this.colorBackground);
        
        btnChon.addActionListener(this);
        btnQuayVe.addActionListener(this);
        
        pn_btn.add(btnChon);
        pn_btn.add(btnQuayVe);
                
        this.add(pn_tim_kiem, BorderLayout.NORTH);
        this.add(pn_table, BorderLayout.CENTER);
        this.add(pn_btn, BorderLayout.SOUTH);
        
        this.setVisible(true);
    }
    
    public void loadKM() {
        if (khuyenmaiBUS.getKmList() == null) {
            khuyenmaiBUS.list();
        }
        ArrayList<KhuyenMaiDTO> kmList = khuyenmaiBUS.getKmList();
        modelKM.setRowCount(0);
        reloadKM(kmList);
    }
    
    public static boolean isPromotionActive(LocalDate startDate, LocalDate endDate) {
        LocalDate today = LocalDate.now();

        // Kiểm tra ngày hiện tại có nằm trong khoảng ngày khuyến mãi
        return (today.isEqual(startDate) || today.isAfter(startDate)) 
                && (today.isEqual(endDate) || today.isBefore(endDate));
    }
    
    public void reloadKM(ArrayList<KhuyenMaiDTO> kmList) {
        modelKM.setRowCount(0);
        boolean hasData=false;
        for (KhuyenMaiDTO km : kmList) {
            if (km.isEnable() && isPromotionActive(km.getNgay_bd(), km.getNgay_kt())) {
                modelKM.addRow(new Object[]{
                    km.getId_km(),km.getTen_km(),km.getTiLeGiamGia(),km.getNgay_kt()
                });
                hasData=true;
            }
        }
        
        if (!hasData) {
        modelKM.addRow(new Object[]{"No", "", ""});
        }
    }
    public String getIdKM() {
        return idKM;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.btnChon)) {
            int row = tableKM.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Chưa chọn khuyến mãi !"); 
            }
            idKM = tableKM.getModel().getValueAt(row, 0).toString();
            dispose();
        }
        else if (e.getSource().equals(this.btnQuayVe)) {
            dispose();
        }
    }
}
