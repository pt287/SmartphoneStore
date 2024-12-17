-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 30, 2024 lúc 11:45 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `smartphonestorecnpm`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `baohanh`
--

CREATE TABLE `baohanh` (
  `serial` varchar(15) NOT NULL,
  `ten_sp` varchar(50) NOT NULL,
  `id_hd` varchar(10) NOT NULL,
  `id_kh` varchar(10) NOT NULL,
  `ngay_mua` date NOT NULL,
  `ngay_het_han` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `baohanh`
--

INSERT INTO `baohanh` (`serial`, `ten_sp`, `id_hd`, `id_kh`, `ngay_mua`, `ngay_het_han`) VALUES
('KH001', 'SP001', 'HD004', 'SP001001', '2024-11-30', '2025-11-30'),
('SP001004', 'iPhone 15 Pro Max', 'HD001', 'KH001', '2024-09-08', '2025-09-08'),
('SP001005', 'iPhone 15 Pro Max', 'HD001', 'KH001', '2024-09-08', '2025-09-08'),
('SP001006', 'iPhone 15 Pro Max', 'HD001', 'KH003', '2024-09-11', '2025-09-11'),
('SP001007', 'iPhone 15 Pro Max', 'HD001', 'KH002', '2024-09-11', '2025-09-11'),
('SP002003', 'iPhone 14 Pro', 'HD001', 'KH003', '2024-09-11', '2025-09-11'),
('SP002004', 'iPhone 14 Pro', 'HD001', 'KH002', '2024-09-11', '2025-09-11'),
('SP002005', 'iPhone 14 Pro', 'HD001', 'KH002', '2024-09-11', '2025-09-11'),
('SP003003', 'iPhone 13', 'HD001', 'KH001', '2024-09-08', '2025-09-08'),
('SP003004', 'iPhone 13', 'HD001', 'KH001', '2024-09-08', '2025-09-08'),
('SP003005', 'iPhone 13', 'HD001', 'KH001', '2024-09-08', '2025-09-08'),
('SP003006', 'iPhone 13', 'HD001', 'KH001', '2024-09-08', '2025-09-08'),
('SP006004', 'Xiaomi Redmi 14C', 'HD001', 'KH003', '2024-09-11', '2025-09-11');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chucnang`
--

CREATE TABLE `chucnang` (
  `id_chucnang` varchar(10) NOT NULL,
  `ten` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chucnang`
--

INSERT INTO `chucnang` (`id_chucnang`, `ten`) VALUES
('NV0', 'Xem nhân viên'),
('NV1', 'Thêm nhân viên'),
('NV2', 'Sửa nhân viên'),
('NV3', 'Xóa nhân viên');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctbaohanh`
--

CREATE TABLE `ctbaohanh` (
  `id_ct_bh` varchar(15) NOT NULL,
  `serial` varchar(15) NOT NULL,
  `id_user` varchar(10) NOT NULL,
  `tinhtrang_may` varchar(100) NOT NULL,
  `chiphi` int(11) DEFAULT NULL,
  `ngaynhan` date DEFAULT NULL,
  `ngaytra` date DEFAULT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ctbaohanh`
--

INSERT INTO `ctbaohanh` (`id_ct_bh`, `serial`, `id_user`, `tinhtrang_may`, `chiphi`, `ngaynhan`, `ngaytra`, `enable`) VALUES
('BH001', 'SP001004', 'US001', 'hư main', 2000000, '2024-11-11', '2024-11-18', 1),
('BH002', 'BH002', 'US001', 'hư quạt', 2000000, '2024-11-30', '2024-12-07', 1),
('BH003', 'SP003004', 'US001', 'rqrqrq', 431313, '2024-11-26', '2024-12-03', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cthoadon`
--

CREATE TABLE `cthoadon` (
  `id_hd` varchar(6) NOT NULL,
  `id_sp` varchar(6) NOT NULL,
  `ten_sp` varchar(50) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `don_gia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cthoadon`
--

INSERT INTO `cthoadon` (`id_hd`, `id_sp`, `ten_sp`, `so_luong`, `don_gia`) VALUES
('HD001', 'SP001', 'iPhone 15 Pro Max', 2, 15000),
('HD001', 'SP003', 'iPhone 13', 4, 20000),
('HD002', 'SP001', 'iPhone 15 Pro Max', 1, 15000),
('HD002', 'SP002', 'iPhone 14 Pro', 1, 12000),
('HD002', 'SP006', 'Xiaomi Redmi 14C', 1, 13000),
('HD003', 'SP001', 'iPhone 15 Pro Max', 1, 15000),
('HD003', 'SP002', 'iPhone 14 Pro', 2, 12000),
('HD004', 'SP001', 'iPhone 15 Pro Max', 1, 55000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctphieunhap`
--

CREATE TABLE `ctphieunhap` (
  `id_pn` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id_sp` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ten_sp` varchar(50) NOT NULL,
  `so_luong` int(5) NOT NULL,
  `don_gia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ctphieunhap`
--

INSERT INTO `ctphieunhap` (`id_pn`, `id_sp`, `ten_sp`, `so_luong`, `don_gia`) VALUES
('PN001', 'SP001', 'iPhone 15 Pro Max', 5, 12000),
('PN001', 'SP002', 'iPhone 14 Pro', 5, 12000),
('PN001', 'SP003', 'iPhone 13', 5, 20000),
('PN001', 'SP004', 'Samsung Galaxy Z Flip6', 5, 20000),
('PN001', 'SP005', 'Samsung Galaxy Z Fold6', 5, 50000),
('PN001', 'SP006', 'Xiaomi Redmi 14C', 5, 13000),
('PN001', 'SP007', 'realme C67', 5, 14000),
('PN001', 'SP008', 'vivo Y36', 5, 27000),
('PN001', 'SP009', 'vivo Y16', 5, 30990),
('PN001', 'SP010', 'Xiaomi POCO X6 Pro', 5, 39999),
('PN001', 'SP013', 'iPhone 11', 10, 12990),
('PN002', 'SP001', 'iPhone 15 Pro Max', 3, 15000),
('PN002', 'SP003', 'iPhone 13', 3, 20000),
('PN002', 'SP005', 'Samsung Galaxy Z Fold6', 3, 50000),
('PN002', 'SP007', 'realme C67', 4, 14000),
('PN002', 'SP008', 'vivo Y36', 2, 27000),
('PN002', 'SP009', 'vivo Y16', 4, 30990),
('PN002', 'SP010', 'Xiaomi POCO X6 Pro', 4, 39999),
('PN003', 'SP010', 'Xiaomi POCO X6 Pro', 1, 39999),
('PN004', 'SP006', 'Xiaomi Redmi 14C', 1, 13000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctquyenchucnang`
--

CREATE TABLE `ctquyenchucnang` (
  `id_quyen` varchar(10) NOT NULL,
  `id_chuc_nang` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ctquyenchucnang`
--

INSERT INTO `ctquyenchucnang` (`id_quyen`, `id_chuc_nang`) VALUES
('QU002', '01'),
('QU002', '03'),
('QU002', '05'),
('QU002', '07'),
('QU002', '09'),
('QU002', '11'),
('QU002', '15'),
('QU002', '21'),
('QU003', '02'),
('QU003', '03'),
('QU003', '06'),
('QU003', '08'),
('QU003', '12'),
('QU003', '16'),
('QU003', '22'),
('QU004', '00'),
('QU004', '04'),
('QU004', '10'),
('QU004', '14'),
('QU004', '20'),
('QU004', '24'),
('QU004', '30'),
('QU004', '34'),
('QU005', '03'),
('QU006', '01'),
('QU006', '02'),
('QU006', '03'),
('QU006', '05'),
('QU006', '06'),
('QU006', '07'),
('QU006', '08'),
('QU006', '09'),
('QU001', '00'),
('QU001', '01'),
('QU001', '02'),
('QU001', '03'),
('QU001', '04'),
('QU001', '05'),
('QU001', '06'),
('QU001', '07'),
('QU001', '08'),
('QU001', '09'),
('QU001', '010'),
('QU001', '10'),
('QU001', '11'),
('QU001', '12'),
('QU001', '13'),
('QU001', '14'),
('QU001', '15'),
('QU001', '16'),
('QU001', '17'),
('QU001', '18'),
('QU001', '20'),
('QU001', '21'),
('QU001', '22'),
('QU001', '23'),
('QU001', '24'),
('QU001', '25'),
('QU001', '26'),
('QU001', '30'),
('QU001', '31'),
('QU001', '32'),
('QU001', '33'),
('QU001', '34'),
('QU001', '35'),
('QU001', '36'),
('QU008', '00'),
('QU008', '01'),
('QU008', '02'),
('QU008', '03'),
('QU008', '04'),
('QU008', '05'),
('QU008', '06'),
('QU008', '07'),
('QU008', '08'),
('QU008', '09'),
('QU008', '10'),
('QU008', '11'),
('QU008', '12'),
('QU008', '13'),
('QU008', '14'),
('QU008', '15'),
('QU008', '16'),
('QU008', '17'),
('QU008', '18'),
('QU008', '20'),
('QU008', '21'),
('QU008', '22'),
('QU008', '23'),
('QU008', '24'),
('QU008', '25'),
('QU008', '26'),
('QU008', '30'),
('QU008', '31'),
('QU008', '32'),
('QU008', '33'),
('QU008', '34'),
('QU008', '35'),
('QU008', '36');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctsanpham`
--

CREATE TABLE `ctsanpham` (
  `id_sp` varchar(10) DEFAULT NULL,
  `serial` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ctsanpham`
--

INSERT INTO `ctsanpham` (`id_sp`, `serial`) VALUES
('SP001', 'SP001001'),
('SP001', 'SP001002');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `id_hd` varchar(10) NOT NULL,
  `id_kh` varchar(10) DEFAULT NULL,
  `id_km` varchar(10) DEFAULT NULL,
  `id_user` varchar(10) DEFAULT NULL,
  `ngay_xuat` date DEFAULT NULL,
  `tong_tien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`id_hd`, `id_kh`, `id_km`, `id_user`, `ngay_xuat`, `tong_tien`) VALUES
('HD001', 'KH001', 'KM001', 'US002', '2024-09-08', 110000),
('HD002', 'KH003', 'KM001', 'US002', '2024-09-11', 40000),
('HD003', 'KH002', 'KM001', 'US001', '2024-09-11', 39000),
('HD004', 'KH001', 'KM001', 'US001', '2024-11-30', 54990);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `id` varchar(10) NOT NULL,
  `ten` varchar(50) NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `dia_chi` varchar(100) NOT NULL,
  `enable` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`id`, `ten`, `sdt`, `dia_chi`, `enable`) VALUES
('KH001', 'Sang', 'abc, 123, ', '09221101923', 1),
('KH002', 'VIệt', 'USA', '023746934', 1),
('KH003', 'Khang', 'Italy', '091116267', 1),
('KH004', 'hảhahr', 'ehdfhzft', '42462462', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `id` varchar(10) NOT NULL,
  `ten` varchar(50) NOT NULL,
  `ti_le_giam_gia` int(11) DEFAULT NULL,
  `ngay_bd` date DEFAULT NULL,
  `ngay_kt` date DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`id`, `ten`, `ti_le_giam_gia`, `ngay_bd`, `ngay_kt`, `enable`) VALUES
('KM001', 'black friday', 10, '2024-11-11', '2024-12-10', 1),
('KM002', '12345', 11, '2024-11-11', '2024-11-12', 1),
('KM003', 'oooo', 40, '2024-11-15', '2024-11-20', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `id` varchar(10) NOT NULL,
  `ten` varchar(50) NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `dia_chi` varchar(100) NOT NULL,
  `enable` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`id`, `ten`, `sdt`, `dia_chi`, `enable`) VALUES
('CC001', 'Công Ty TNHH abcd', ' TP. Hồ Ch', '02222222', 1),
('CC002', 'Công Ty TNHH Thương Mại Dịch Vụ agsgv', 'TP HCM', '02838115345', 1),
('CC003', 'Công Ty Cổ Phần  FPT', 'Q4, TP. Hồ', '025346572675', 1),
('CC004', 'Công ty Cổ phần  SDFHE', 'Hà Nội', '0900321903', 1),
('CC005', 'Công Ty TNHH Thương Mại Hoàng Hải ', 'TP. Hải Ph', '02253250888', 1),
('CC006', 'Công ti việt á', ' đồng nai', '03805484', 0),
('CC007', '141414', '124141', '0123401234', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `id_pn` varchar(10) NOT NULL,
  `id_ncc` varchar(10) DEFAULT NULL,
  `id_user` varchar(10) DEFAULT NULL,
  `ngay_nhap` date NOT NULL,
  `tong_tien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`id_pn`, `id_ncc`, `id_user`, `ngay_nhap`, `tong_tien`) VALUES
('PN001', 'CC003', 'US001', '2024-09-08', 1324845),
('PN002', 'CC001', 'US004', '2024-09-08', 648956),
('PN003', 'CC001', 'US001', '2024-09-08', 39999),
('PN004', 'CC002', 'US001', '2024-09-11', 13000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quyen`
--

CREATE TABLE `quyen` (
  `id` varchar(10) NOT NULL,
  `ten` varchar(50) NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `quyen`
--

INSERT INTO `quyen` (`id`, `ten`, `enable`) VALUES
('QU001', 'Admin', 1),
('QU002', 'Nhân viên bán hàng', 1),
('QU003', 'Nhân viên nhập hàng', 1),
('QU004', 'Quản lý nhân viên', 1),
('QU005', 'test', 0),
('QU006', 'chỉ xem', 1),
('QU007', 'admin1', 1),
('QU008', 'admin2', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` varchar(10) NOT NULL,
  `ten` varchar(50) DEFAULT NULL,
  `so_luong` int(11) DEFAULT NULL,
  `gia_nhap` int(11) DEFAULT NULL,
  `gia_ban` int(11) DEFAULT NULL,
  `hang` varchar(10) NOT NULL,
  `img` varchar(50) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `ten`, `so_luong`, `gia_nhap`, `gia_ban`, `hang`, `img`, `enable`) VALUES
('SP001', 'iPhone 15 Pro Max', 3, 50000, 55000, 'iPhone', 'SP001.png', 1),
('SP002', 'iPhone 14 Pro', 2, 0, 12000, 'iPhone', 'SP002.png', 1),
('SP003', 'iPhone 13', 4, 0, 20000, 'iPhone', 'SP003.png', 1),
('SP004', 'Samsung Galaxy Z Flip6', 5, 0, 20000, 'Samsung', 'SP004.png', 1),
('SP005', 'Samsung Galaxy Z Fold6', 8, 0, 50000, 'Samsung', 'SP005.png', 1),
('SP006', 'Xiaomi Redmi 14C', 5, 0, 13000, 'Xiaomi', 'SP006.png', 1),
('SP007', 'realme C67', 9, 0, 14000, 'realme', 'SP007.png', 1),
('SP008', 'vivo Y36', 7, 0, 27000, 'vivo', 'SP008.png', 1),
('SP009', 'vivo Y16', 9, 0, 30990, 'vivo', 'SP009.png', 1),
('SP010', 'Xiaomi POCO X6 Pro', 10, 0, 39999, 'Xiaomi', 'SP010.png', 1),
('SP011', 'abc', 0, 0, 3, 'iPhone', 'SP011.png', 0),
('SP012', '123', 0, 0, 1233, 'Samsung', 'SP012.png', 0),
('SP013', 'iPhone 11', 10, 0, 12990, 'iPhone', 'SP013.png', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` varchar(10) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `ten` varchar(50) NOT NULL,
  `gioi_tinh` varchar(5) NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `dia_chi` varchar(100) NOT NULL,
  `quyen` varchar(10) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `password`, `ten`, `gioi_tinh`, `sdt`, `dia_chi`, `quyen`, `img`, `enable`) VALUES
('US001', 'admin', 'Admin', 'Nam', '911', 'hcm', 'QU001', 'US001.png', 1),
('US002', '123', 'nhan vien 1', 'Nữ', '0123456789', 'hcm', 'QU008', 'US002.png', 1),
('US003', '123', 'nv2', 'Nam', '12345', 'hcm', 'QU001', 'US003.png', 1),
('US004', '123', 'abc', 'Nữ', '11', 'hcm', 'QU003', 'US004.png', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `baohanh`
--
ALTER TABLE `baohanh`
  ADD PRIMARY KEY (`serial`);

--
-- Chỉ mục cho bảng `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`id_chucnang`);

--
-- Chỉ mục cho bảng `ctbaohanh`
--
ALTER TABLE `ctbaohanh`
  ADD PRIMARY KEY (`id_ct_bh`);

--
-- Chỉ mục cho bảng `cthoadon`
--
ALTER TABLE `cthoadon`
  ADD PRIMARY KEY (`id_hd`,`id_sp`),
  ADD KEY `id_hd` (`id_hd`,`id_sp`);

--
-- Chỉ mục cho bảng `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD PRIMARY KEY (`id_pn`,`id_sp`),
  ADD KEY `id_pn` (`id_pn`,`id_sp`);

--
-- Chỉ mục cho bảng `ctsanpham`
--
ALTER TABLE `ctsanpham`
  ADD PRIMARY KEY (`serial`),
  ADD KEY `id_sp` (`id_sp`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`id_hd`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`id_pn`);

--
-- Chỉ mục cho bảng `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_quyen` (`quyen`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `ctsanpham`
--
ALTER TABLE `ctsanpham`
  ADD CONSTRAINT `ctsanpham_ibfk_1` FOREIGN KEY (`id_sp`) REFERENCES `sanpham` (`id`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`id_kh`) REFERENCES `khachhang` (`id`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`id_km`) REFERENCES `khuyenmai` (`id`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`id_ncc`) REFERENCES `nhacungcap` (`id`);

--
-- Các ràng buộc cho bảng `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`quyen`) REFERENCES `quyen` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
