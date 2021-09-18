use
    QuanLyThuVien


Create
proc sp_KiemTraDangNhap
@TaiKhoan nvarchar(50),
@MatKhau nvarchar(50)
as
select MaNhanVien, HoTenNhanVien
from NhanVien a
where a.taikhoan = @TaiKhoan
  and PWDCOMPARE(@MatKhau, a.MatKhau) = 1

create
proc sp_DanhSachTacGia
as
Select MaTacGia, HoTenTacGia, DiaChi
from TacGia



create
proc sp_DanhSachLoaiSach
as
Select MaLoai, LoaiSach, KieuSach
from LoaiSach


create
proc sp_LuuTacGia
@HoTenTacGia nvarchar(100),
@DiaChi nvarchar(100)
as
insert TacGia (HoTenTacGia, DiaChi)
values (@HoTenTacGia, @DiaChi)


create
proc sp_CapNhatTacGia
@MaTacGia int,
@HoTenTacGia nvarchar(100),
@DiaChi nvarchar(100)
as
update TacGia
Set HoTenTacGia = @HoTenTacGia,
    DiaChi      = @DiaChi
where MaTacGia = @MaTacGia

create
proc sp_LuuLoaiSach
@LoaiSach nvarchar(100),
@KieuSach nvarchar(100)
as
insert LoaiSach (LoaiSach, KieuSach)
values (@LoaiSach, @KieuSach)


create
proc sp_CapNhatLoaiSach
@MaLoai int,
@LoaiSach nvarchar(100),
@KieuSach nvarchar(100)
as
Update LoaiSach
Set LoaiSach = @LoaiSach,
    KieuSach = @KieuSach
where MaLoai = @MaLoai

create
proc sp_LayThongTinSach
as
Select a.MaSach, a.TenSach, a.SoLuong, b.MaTacGia, b.HoTenTacGia, c.MaLoai, c.LoaiSach
from Sach a,
     TacGia b,
     LoaiSach c
where a.MaTacGia = b.MaTacGia
  and a.MaLoai = c.MaLoai


create
proc sp_ThemSach
@TenSach nvarchar(100),
@SoLuong int,
@MaTacGia int,
@MaLoai int
as
insert into Sach(TenSach, SoLuong, MaTacGia, MaLoai)
values (@TenSach, @SoLuong, @MaTacGia, @MaLoai)



create
proc sp_CapNhatSach
@MaSach int,
@TenSach nvarchar(100),
@SoLuong int,
@MaTacGia int,
@MaLoai int
as
update Sach
set TenSach  = @TenSach,
    SoLuong  =@SoLuong,
    MaTacGia = @MaTacGia,
    MaLoai   = @MaLoai
where MaSach = @MaSach


create
proc sp_rptThongKeSach
as
Select a.MaTacGia,
       a.HoTenTacGia,
       a.DiaChi,
       b.MaSach,
       b.TenSach,
       b.SoLuong,
       c.MaLoai,
       c.LoaiSach,
       c.KieuSach
from TacGia a,
     Sach b,
     LoaiSach c
where a.MaTacGia = b.MaTacGia
  and b.MaLoai = c.MaLoai
		  