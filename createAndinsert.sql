create database hibernate;
use hibernate;
create table teacher(
MGV int primary key,
TenGV varchar(50),
Tuoi int,
Diachi varchar(50),
Ghichu varchar(50),
Hinhanh varchar(50)
);

create table student(
MHS int primary key,
TenHS varchar(50),
Diem float,
Diachi varchar(50),
Lop varchar(10),
Hinhanh varchar(50)
);

create table subject(
maMH varchar(10) primary key,
tenMH varchar(50),
tinchi int
);

create table semester(
maHK varchar(10) primary key,
tenHK varchar(50),
year int,
ngayBD date,
ngayKT date
);

create table session(
name varchar(50) primary key,
start date,
end date
);

create table class(
tenLop varchar(10) primary key,
SLSV int,
SLNam int,
SLNu int
);

create table course(
maMon varchar(10) primary key,
tenMon varchar(50),
soTinChi int,
GV varchar(50),
phongHoc varchar(10),
dayofWeek varchar(10),
Ca int,
maxSlot int
);

create table account(
username varchar(50) primary key,
password varchar(50),
type varchar(10)
);

insert into class(tenLop,SLSV,SLNam,SLNu)
values('19CTT2',150,120,30),
('19CTT1',140,130,10),
('19CTT3',130,110,20);

insert into course(maMon,tenMon,soTinChi,GV,phongHoc,dayofWeek,Ca,maxSlot)
values('JV001','Lập trình ứng dụng Java',4,'Nguyễn Văn Khiết','F302','Wednesday',1,150),
('PHY002','Lý 1',3,'Lê Văn Luyện','F201','Monday',2,150),
('BI001','Sinh 1',3,'Bạch Thái Bưởi','F205','Monday',2,150),
('JV002','Lập trình ứng dụng Java 2',4,'Nguyễn Văn Khiết','E105','Thursday',3,100),
('PHY003','Lý 3',3,'Nguyễn Văn Hùng','F101','Friday',4,120),
('BI002','Sinh 2',3,'Bạch Thái Công','F202','Tuesday',3,110),
('JV003','Lập trình ứng dụng Java 3',4,'Nguyễn Văn Nghĩa','C102','Saturday',1,150),
('MA003','Vi tích phân 3B',2,'Lê Văn Chánh','D203','Wednesday',4,150),
('MA001','Vi tích phân 1B',1,'Lê Thị Kiều','G101','Monday',1,140),
('PRO001','Nhập môn lập trình',3,'Thái Hùng Văn','G104','Saturday',4,130);

insert into semester(maHK,tenHK,year,ngayBD,ngayKT)
values('HK01','Học kỳ 1',2021,'2021-1-1','2021-3-31'),
('HK02','Học kỳ 2',2021,'2021-4-1','2021-6-30'),
('HK03','Học kỳ 3',2021,'2021-7-1','2021-9-30');

insert into session(name,start,end)
values('Kỳ đăng ký 1','2021-1-1','2021-1-10'),
('Kỳ đăng ký 2','2021-6-1','2021-6-10'),
('Kỳ đăng ký 3','2021-7-1','2021-7-10');

insert into student(MHS,TenHS,Diem,Diachi,Lop,Hinhanh)
values(111,'Tuấn',8,'150 Quang Trung','19CTT2','Hình tuấn'),
(112,'Hưng',5,'100 Trần Phú','19CTT2','Hình hưng'),
(113,'Hậu',9,'15 Đỗ Trạc','19CTT1','Hình hậu');

insert into subject(maMH,tenMH,tinchi)
values('M001','Toán tổ hợp',4),
('PHY001','Lý 1',3),
('PR001','Kỹ thuật lập trình',4);

insert into teacher(MGV,TenGV,Tuoi,Diachi,Ghichu,Hinhanh)
values('111','admin','30','135 Điện Biên Phủ','Không có','Không có');

insert into account(username,password,type)
values('admin','admin','Teacher'),
('hung','hung','Student')





