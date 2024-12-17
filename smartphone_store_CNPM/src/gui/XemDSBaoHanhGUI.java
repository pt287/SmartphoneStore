package gui;

import bus.BaoHanhBUS;
import dto.BaoHanhDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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




public class XemDSBaoHanhGUI extends JDialog implements ActionListener {
    private Color colorBackground = Color.decode("#FFFFFF");
     private Color color1 = Color.decode("#800000");
    private Color color2 = Color.decode("#B22222");
    private Color color3 = Color.decode("#FF6347");
    
    private JButton btnQuayVe;
    private TableRowSorter<TableModel> rowSorter;
    private JTable tableBH;
    private DefaultTableModel modelBH;
    private BaoHanhBUS baoHanhBUS = new BaoHanhBUS();

    
    public XemDSBaoHanhGUI() {
        this.setModal(true);
        this.init();
    }
    
    public void init() {
        this.setSize(800, 500);
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
                else if (txt.trim().length() >= 2 && txt.trim().substring(0, 2).toUpperCase().equals("SP")) {
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
                else if (txt.trim().length() >= 2 && txt.trim().substring(0, 2).toUpperCase().equals("SP")) {
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
            "Serial","Tên sản phẩm","Mã hóa đơn","Mã khách hàng","ngày mua","Ngày hết hạn"
        };
       modelBH = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Tất cả các ô đều không thể chỉnh sửa
                return false;
            }
        };
        tableBH = new JTable();
        rowSorter = new TableRowSorter<TableModel>(modelBH);
        tableBH.setModel(modelBH);
        tableBH.setRowSorter(rowSorter);
        JScrollPane scroll = new JScrollPane(tableBH);
        scroll.setPreferredSize(new Dimension(700, 350));
        
        tableBH.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableBH.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableBH.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableBH.getColumnModel().getColumn(3).setPreferredWidth(100);
        tableBH.getColumnModel().getColumn(4).setPreferredWidth(100);
        tableBH.getColumnModel().getColumn(5).setPreferredWidth(100);
        loadBH();
        
        pn_table.add(pn_tim_kiem);
        pn_table.add(scroll);
        
        Font font_table = new Font("Segoe UI", Font.BOLD, 13);
        tableBH.getTableHeader().setBackground(color1);
        tableBH.getTableHeader().setFont(font_table);
        tableBH.getTableHeader().setForeground(this.colorBackground);
        tableBH.getTableHeader().setOpaque(false); 
        tableBH.getTableHeader().setBorder(BorderFactory.createLineBorder(this.color1));
        
        // căn giữa các chữ trong ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < col.length; i++) {
            tableBH.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        tableBH.setFocusable(false);
        tableBH.setShowVerticalLines(false);
        tableBH.setIntercellSpacing(new Dimension(0, 0));
        tableBH.setFillsViewportHeight(true);
        tableBH.setSelectionBackground(color3);
        tableBH.setRowHeight(30);
        tableBH.setBorder(BorderFactory.createLineBorder(this.color1));
        
        JPanel pn_btn = new JPanel(new FlowLayout(1));
        pn_btn.setPreferredSize(new Dimension(800, 50));
        

        btnQuayVe = new JButton("Quay về");
        
 
        btnQuayVe.setPreferredSize(new Dimension(150, 30));

        btnQuayVe.setBackground(color2);

        btnQuayVe.setForeground(this.colorBackground);
        

        btnQuayVe.addActionListener(this);
        

        pn_btn.add(btnQuayVe);
                
        this.add(pn_tim_kiem, BorderLayout.NORTH);
        this.add(pn_table, BorderLayout.CENTER);
        this.add(pn_btn, BorderLayout.SOUTH);
        
        this.setVisible(true);
    }
    
    public void loadBH() {
        if (baoHanhBUS.getBhList() == null) {
            baoHanhBUS.list();
        }
        ArrayList<BaoHanhDTO> bhList = baoHanhBUS.getBhList();
       modelBH.setRowCount(0);
        reloadBH(bhList);
    }
    
    public void reloadBH(ArrayList<BaoHanhDTO> bhList) {
       modelBH.setRowCount(0);
        for (BaoHanhDTO bh : bhList) {           
               modelBH.addRow(new Object[]{
                    bh.getSerial(), bh.getTenSanPham(), bh.getIdHoaDon(), bh.getIdKhachHang(), bh.getNgayMua(), bh.getNgayHetHan()
                });           
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.btnQuayVe)) {
            dispose();
        }
    }


}
