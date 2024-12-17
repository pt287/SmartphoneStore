package gui;

import bus.NhaCungCapBUS;
import dto.NhaCungCapDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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


public class NhaCungCapGUI extends JPanel {
    
    private int width, height;
    private Color colorBackground = Color.decode("#FFFFFF");
     private Color color1 = Color.decode("#800000");
    private Color color2 = Color.decode("#B22222");
    private Color color3 = Color.decode("#FF6347");
    private JPanel pnInfor, pnFilter, pnTable;
    private ArrayList<JPanel> arrPnInfor;
    private ArrayList<JLabel> arrLbInfor;
    private ArrayList<JTextField> arrTfInfor;
    private JButton btnThem, btnSua, btnXoa;


    private JTable table;
    private TableRowSorter<TableModel> rowSorter;
    private DefaultTableModel model;
    private NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
    
    private boolean quyenThem, quyenSua, quyenXoa;
    
    private boolean isEditing = false; // true = đang trong chế độ sửa, false = đang trong chế độ thêm hoặc khác    
    
    public NhaCungCapGUI(int width, int height, boolean quyenThem, boolean quyenSua, boolean quyenXoa) {
        this.width = width;
        this.height = height;
        this.quyenThem = quyenThem;
        this.quyenSua = quyenSua;
        this.quyenXoa = quyenXoa;
        this.init();
    }
    
    public void init() {
        this.setSize(this.width, this.height);
        this.setBackground(this.colorBackground);
        
        this.pnInfor = this.createPnInfor();
        this.pnFilter = this.createPnFilter();
        this.pnTable = this.createPnTable();
        
        this.setLayout(new BorderLayout());
        this.add(this.pnInfor, BorderLayout.NORTH);
        this.add(this.pnFilter, BorderLayout.CENTER);
        this.add(this.pnTable, BorderLayout.SOUTH);
        
        loadFirstNCCInfo(); // Sang Chỉnh
    }
    //Sang CHỉnh
    public void loadFirstNCCInfo() {
        // Lấy nhà cung cấp đầu tiên từ NhaCungCapBUS
        NhaCungCapDTO firstNCCList = nhaCungCapBUS.getNccList().get(0); 

        // Đặt các JTextFields với thông tin nhà cung cấp
        if (firstNCCList != null) {
            this.arrTfInfor.get(0).setText(firstNCCList.getIdNhaCungCap());
            this.arrTfInfor.get(1).setText(firstNCCList.getTenNhaCungCap()); 
            this.arrTfInfor.get(3).setText(firstNCCList.getDiachi()); 
            this.arrTfInfor.get(2).setText(firstNCCList.getSdt());
        }
        if (isEditing) {
            lockInfor(false);
        }
        else lockInfor(true);
    }
    
    public JPanel createPnInfor() {
        JPanel result = new JPanel(new FlowLayout(1, 0, 25));
        result.setPreferredSize(new Dimension(this.width, 300));
        
        JPanel pn_infor = new JPanel(new BorderLayout());
        pn_infor.setPreferredSize(new Dimension(this.width - 100, 230));
        pn_infor.setBackground(this.color1);
        pn_infor.setBorder(BorderFactory.createLineBorder(color1, 2));

        
        // create panel description
        JPanel pn_desc = new JPanel(new FlowLayout(1, 20, 20));

        String[] thuoc_tinh = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ"};
        int len = thuoc_tinh.length;
        this.arrPnInfor = new ArrayList<>();
        this.arrLbInfor = new ArrayList<>();
        this.arrTfInfor = new ArrayList<>();
        
        Dimension d_pn = new Dimension(530, 30);
        Dimension d_lb = new Dimension(130, 30);
        Dimension d_tf = new Dimension(400, 30);
        Color color_font = this.color1;
        Font font_infor = new Font("Segoe UI", Font.PLAIN, 15);
        for (int i = 0; i < len; i++) {
            this.arrPnInfor.add(new JPanel(new FlowLayout(0, 0, 0)));
            this.arrPnInfor.get(i).setPreferredSize(d_pn);
            
            this.arrLbInfor.add(new JLabel(thuoc_tinh[i]));
            this.arrLbInfor.get(i).setPreferredSize(d_lb);
            this.arrTfInfor.add(new JTextField());
            this.arrTfInfor.get(i).setPreferredSize(d_tf);

            this.arrLbInfor.get(i).setForeground(color_font);
            this.arrLbInfor.get(i).setFont(font_infor);
            this.arrTfInfor.get(i).setForeground(color_font);
            this.arrTfInfor.get(i).setFont(font_infor);
            
            this.arrPnInfor.get(i).add(this.arrLbInfor.get(i));
            this.arrPnInfor.get(i).add(this.arrTfInfor.get(i));
            pn_desc.add(this.arrPnInfor.get(i));
        }
        this.arrTfInfor.get(0).setEditable(false); // khóa luôn khả năng chỉnh sửa mã sản phẩm
        
        // create panel button
        JPanel pn_btn = new JPanel(new FlowLayout(1, 70, 10));
        pn_btn.setPreferredSize(new Dimension(250, 250));
        
        // các nút chức năng mặc định
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        
        showCN();
        
        // các nút chức năng phụ
        JButton btn_hoan_thanh = new JButton("Hoàn thành");
        JButton btn_tro_ve = new JButton("Trở về");
        
        // Thiết kế giao diện nút
        Dimension d_btn = new Dimension(150, 30);
        btnThem.setPreferredSize(d_btn);
        btnSua.setPreferredSize(d_btn);
        btnXoa.setPreferredSize(d_btn);

        
        btn_hoan_thanh.setPreferredSize(d_btn);
        btn_tro_ve.setPreferredSize(d_btn);
        btn_hoan_thanh.setVisible(false);
        btn_tro_ve.setVisible(false);
        
        Color color_button = this.color2;
        btnThem.setBackground(color_button);
        btnSua.setBackground(color_button);
        btnXoa.setBackground(color_button);


        btn_hoan_thanh.setBackground(color_button);
        btn_tro_ve.setBackground(color_button);
        
        Color color_font_btn = this.colorBackground;
        btnThem.setForeground(color_font_btn);
        btnSua.setForeground(color_font_btn);
        btnXoa.setForeground(color_font_btn);


        btn_hoan_thanh.setForeground(color_font_btn);
        btn_tro_ve.setForeground(color_font_btn);
        
        Font font_btn = new Font("Segoe UI", Font.BOLD, 13);
        btnThem.setFont(font_btn);
        btnSua.setFont(font_btn);
        btnXoa.setFont(font_btn);


        btn_hoan_thanh.setFont(font_btn);
        btn_tro_ve.setFont(font_btn);
        
        // thêm các nút
        pn_btn.add(btnThem);
        pn_btn.add(btnSua);
        pn_btn.add(btnXoa);

        pn_btn.add(btn_hoan_thanh);
        pn_btn.add(btn_tro_ve);
        
        // khi ấn nút thêm
       btnThem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                blankInfor();
                isEditing = false;
                lockInfor(false);
                
                arrTfInfor.get(0).setText(nhaCungCapBUS.createNewId());
                
                btnThem.setVisible(false);
                btnSua.setVisible(false);
                btnXoa.setVisible(false);

                
                btn_hoan_thanh.setVisible(true);
                btn_tro_ve.setVisible(true);
                
                table.clearSelection();
                table.setEnabled(false);
            }
        });
        
        // khi ấn nút sửa
        btnSua.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (arrTfInfor.get(0).getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần sửa!");
                    return;
                }
                isEditing = true;
                
                lockInfor(false);
                
                btnThem.setVisible(false);
                btnSua.setVisible(false);
                btnXoa.setVisible(false);

                
                btn_hoan_thanh.setVisible(true);
                btn_tro_ve.setVisible(true);
                
                table.setEnabled(false);
            }
        });
        
        // khi ấn nút xóa
        btnXoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (arrTfInfor.get(0).getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần xóa!");
                    return;
                }
                
                int confirmed = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (confirmed == 0) { // xác nhận xóa
                    nhaCungCapBUS.deleteNhaCungCap(arrTfInfor.get(0).getText());
                    blankInfor();
                    table.clearSelection();
                    reloadNCC(nhaCungCapBUS.getNccList());
                }
            }
        });
        
        
        
        // khi ấn nút hoàn thành
        btn_hoan_thanh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirmed;
                if (isEditing) { // đang trong chế độ sửa Sang Chỉnh
                    confirmed = JOptionPane.showConfirmDialog(null, "Xác nhận sửa nhà cung cấp", "", JOptionPane.YES_NO_OPTION);
                    if (confirmed == 0) { // xác nhận sửa
                        String idNCC = arrTfInfor.get(0).getText();
                        String tenNCC = arrTfInfor.get(1).getText();
                        String sdt = arrTfInfor.get(2).getText();
                        String diachi = arrTfInfor.get(3).getText();
                        
                        
                        if (tenNCC.isEmpty() && diachi.isEmpty() && sdt.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
                            return;
                        }
                        if (tenNCC.isEmpty() || tenNCC.isBlank() ) {
                            JOptionPane.showMessageDialog(null, "Điền tên nhà cung cấp!");
                            return;
                        }
                        if (diachi.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Điền địa chỉ  !");
                            return;
                        }
                        if (sdt.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Điền số điẹn thoại!");
                            return;
                        }
                        // ràng buộc tên nhà cung cấp
                        if(tenNCC.matches("[^a-zA-Z0-9 ]")) {
                            JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không hợp lệ");
                            return;
                        }
                        // ràng buộc Địa chỉ
                        if(diachi.matches("[^a-zA-Z0-9]")) {
                            JOptionPane.showMessageDialog(null, "Địa chỉ không hợp lệ");
                            return;
                        }
                        // ràng buộc sdt
                        if (!sdt.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Số điện thoại chỉ có thể điền số!");
                            return;
                        }
                        if (!sdt.matches("^0\\d{9}$")) {
                            JOptionPane.showMessageDialog(null, "Số điện thoại phải là 10 chữ số và bắt đầu từ số 0");
                            return;
                        }
                        nhaCungCapBUS.list();
                        ArrayList<NhaCungCapDTO> nccList = nhaCungCapBUS.getNccList();
                        for (NhaCungCapDTO ncc : nccList) { 
                            if (tenNCC.equals(ncc.getTenNhaCungCap() )&& !idNCC.equals(ncc.getIdNhaCungCap())) {
                                JOptionPane.showMessageDialog(null, "Nhà cung cấp đã tồn tại!");
                            return;
                            }
                            if (sdt.equals(ncc.getSdt() )&& !idNCC.equals(ncc.getIdNhaCungCap())) {
                                JOptionPane.showMessageDialog(null, "số điện thoại đã tồn tại!");
                            return;
                            }
                        }
                        
                        NhaCungCapDTO ncc = new NhaCungCapDTO(idNCC, tenNCC, sdt, diachi, true);
                        nhaCungCapBUS.updateNhaCungCap(ncc);
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "OK", JOptionPane.INFORMATION_MESSAGE);
                        reloadNCC(nhaCungCapBUS.getNccList());
                    }
                }
                else { // đang trong chế độ thêm Sang Chỉnh
                    confirmed = JOptionPane.showConfirmDialog(null, "Xác nhận thêm nhà cung cấp", "", JOptionPane.YES_NO_OPTION);
                    if (confirmed == 0) { // xác nhận thêm
                        String idNCC = arrTfInfor.get(0).getText();
                        String tenNCC = arrTfInfor.get(1).getText();                      
                        String sdt = arrTfInfor.get(2).getText();
                        String diachi = arrTfInfor.get(3).getText();
                       
                        if (nhaCungCapBUS.isExisted(idNCC)) {
                            JOptionPane.showMessageDialog(null, "Mã nhà cung cấp đă tồn tại!");
                            return;
                        }
                        if (tenNCC.isEmpty() && diachi.isEmpty() && sdt.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
                            return;
                        }
                        if (tenNCC.isEmpty() || tenNCC.isBlank() ) {
                            JOptionPane.showMessageDialog(null, "Điền tên nhà cung cấp!");
                            return;
                        }
                        if (diachi.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Điền địa chỉ  !");
                            return;
                        }
                        if (sdt.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Điền số điẹn thoại!");
                            return;
                        }
                        // ràng buộc tên nhà cung cấp
                        if(tenNCC.matches("[^a-zA-Z0-9]")) {
                            JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không hợp lệ");
                            return;
                        }
                        // ràng buộc Địa chỉ
                        if(diachi.matches(".*" + "[^a-zA-Z0-9]" + ".*")) {
                            JOptionPane.showMessageDialog(null, "Địa chỉ không hợp lệ");
                            return;
                        }
                        // ràng buộc sdt
                        if (!sdt.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Số điện thoại chỉ có thể điền số!");
                            return;
                        }
                        if (!sdt.matches("^0\\d{9}$")) {
                            JOptionPane.showMessageDialog(null, "Số điện thoại phải là 10 chữ số và bắt đầu từ số 0");
                            return;
                        }
                        ArrayList<NhaCungCapDTO> nccList = nhaCungCapBUS.getNccList();
                        for (NhaCungCapDTO us : nccList) {
                            if (tenNCC.equals(us.getTenNhaCungCap())) {
                                JOptionPane.showMessageDialog(null, "Nhà cung cấp đã tồn tại!");
                            return;
                            }
                        
                            if (sdt.equals(us.getSdt())) {
                                JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!");
                            return;
                            }
                        }
                        
                        NhaCungCapDTO ncc = new NhaCungCapDTO(idNCC, tenNCC, sdt, diachi, true);
                        nhaCungCapBUS.addNhaCungCap(ncc);
                        
                        reloadNCC(nhaCungCapBUS.getNccList());
                        blankInfor();
                    }
                }
                 btn_hoan_thanh.setVisible(false);
                 lockInfor(true);
            }
        });
        
        // khi ấn nút trở về
        btn_tro_ve.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                blankInfor();
                
                // nếu đang trong chế độ sửa khi thoát ra chỉnh isEditing = false
                if (isEditing) isEditing = false;
                
                btnThem.setVisible(true);
                btnSua.setVisible(true);
                btnXoa.setVisible(true);

                
                showCN();
                
                btn_hoan_thanh.setVisible(false);
                btn_tro_ve.setVisible(false);
                
                table.setEnabled(true);
            }
        });
            
        // add components
        pn_infor.add(pn_desc, BorderLayout.CENTER);
        pn_infor.add(pn_btn, BorderLayout.EAST);
        
        result.add(pn_infor);
        
        return result;
    }

    public JPanel createPnFilter() {
        JPanel pn_filter = new JPanel(new FlowLayout(1, 20, 10));
        
        Font font_filter = new Font("Segoe UI", Font.BOLD, 13);
        JLabel lb_tim_kiem = new JLabel("Tìm kiếm");
        lb_tim_kiem.setFont(font_filter);
        lb_tim_kiem.setForeground(color1);
        
        JPanel pn_tim_kiem = new JPanel(new FlowLayout(1, 0, 0));
        pn_tim_kiem.setPreferredSize(new Dimension(500, 30));
        JComboBox cb_tim_kiem = new JComboBox();
        cb_tim_kiem.setPreferredSize(new Dimension(140, 30));
        cb_tim_kiem.addItem("Mã nhà cung cấp");
        cb_tim_kiem.addItem("Tên nhà cung cấp");
        cb_tim_kiem.addItem("Địa chỉ");
        cb_tim_kiem.addItem("Số điện thoại");
        cb_tim_kiem.setForeground(color1);
        cb_tim_kiem.setBackground(colorBackground);
        cb_tim_kiem.setFont(font_filter);
        
        JTextField tf_tim_kiem = new JTextField();
        tf_tim_kiem.setPreferredSize(new Dimension(350, 30));
        tf_tim_kiem.setFont(font_filter);
        tf_tim_kiem.setForeground(color1);
        
        tf_tim_kiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = tf_tim_kiem.getText();
                int choice = cb_tim_kiem.getSelectedIndex();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                }
                else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + "", choice)); 
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = tf_tim_kiem.getText();
                int choice = cb_tim_kiem.getSelectedIndex();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                }
                else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + "", choice)); 
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        
        pn_tim_kiem.add(cb_tim_kiem);
        pn_tim_kiem.add(tf_tim_kiem);
        
        pn_filter.add(lb_tim_kiem);
        pn_filter.add(pn_tim_kiem);
        
        return pn_filter;
    }
    
    public JPanel createPnTable() {
        JPanel pn_table = new JPanel(new FlowLayout(1, 0, 0));
        pn_table.setPreferredSize(new Dimension(this.width, 300));
        
        String[] col = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ"};

        this.model = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Tất cả các ô đều không thể chỉnh sửa
                return false;
            }
        };
        this.table = new JTable();
        rowSorter = new TableRowSorter<TableModel>(model);
        this.table.setModel(model);
        this.table.setRowSorter(rowSorter);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(900, 250));
        
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(170);
        table.getColumnModel().getColumn(3).setPreferredWidth(15);
       
        
        this.loadNCC();
        
        pn_table.add(scroll);
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (table.getRowSorter() != null) {
                    row = table.getRowSorter().convertRowIndexToModel(row);
                }               
                // set thông tin cho sản phẩm
                arrTfInfor.get(0).setText(table.getModel().getValueAt(row, 0).toString());
                arrTfInfor.get(1).setText(table.getModel().getValueAt(row, 1).toString());
                arrTfInfor.get(2).setText(table.getModel().getValueAt(row, 2).toString());
                arrTfInfor.get(3).setText(table.getModel().getValueAt(row, 3).toString());              
                
                if (isEditing) {
                    lockInfor(false);
                }
                else lockInfor(true);
            }
        });
        
        // giao diện table
        Font font_table = new Font("Segoe UI", Font.BOLD, 13);
        table.getTableHeader().setBackground(color1);
        table.getTableHeader().setFont(font_table);
        table.getTableHeader().setForeground(this.colorBackground);
        table.getTableHeader().setOpaque(false); 
        table.getTableHeader().setBorder(BorderFactory.createLineBorder(this.color1));
        
        // căn giữa các chữ trong ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < col.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        table.setFocusable(false);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);
        table.setSelectionBackground(color3);
        table.setRowHeight(30);
        table.setBorder(BorderFactory.createLineBorder(this.color1));
        
        return pn_table;
    }
    
    public void loadNCC() {
        if (nhaCungCapBUS.getNccList() == null) {
           nhaCungCapBUS.list();
        }
        ArrayList<NhaCungCapDTO> nccList = nhaCungCapBUS.getNccList();
        model.setRowCount(0);
        reloadNCC(nccList);
    }
    
    public void reloadNCC(ArrayList<NhaCungCapDTO> nccList) {
        model.setRowCount(0);
        for (NhaCungCapDTO ncc : nccList) {
            if (ncc.isEnable()) {
                model.addRow(new Object[]{
                    ncc.getIdNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSdt(),ncc.getDiachi()
                });
            }
        }
    }
    
    // khóa khả năng thao tác với thông tin
    public void lockInfor(boolean lock) {
    //    arrTfInfor.get(0).setEditable(!lock);
        arrTfInfor.get(1).setEditable(!lock);
        arrTfInfor.get(2).setEditable(!lock);
        arrTfInfor.get(3).setEditable(!lock);    

    }
    
    public void blankInfor() {
        arrTfInfor.get(0).setText("");
        arrTfInfor.get(1).setText("");
        arrTfInfor.get(2).setText("");
        arrTfInfor.get(3).setText("");
    }
    
    public void showCN() {
        this.btnThem.setVisible(quyenThem);
        this.btnSua.setVisible(quyenSua);
        this.btnXoa.setVisible(quyenXoa);

    }
    
    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}