import java.util.ArrayList;

public class Main {
    public static String pattern;
    public static void main(String[] Args){
        DaThuc daThuc = new DaThuc("A'B+AB+A'B'");
        KarnaughMap map = new KarnaughMap(daThuc);
        map.hienThi();
        pattern = daThuc.createPattern();
        QuanLyTeBaoLon danhSachTeBaoLon = new QuanLyTeBaoLon(pattern);
        danhSachTeBaoLon.chonTeBaoLon(map.cacGiaTriMot());
        danhSachTeBaoLon.hienThi();
        QuanLyTeBaoLon danhSachKetQua = new QuanLyTeBaoLon(pattern);
        danhSachKetQua.setDanhSachTeBaoLon(danhSachTeBaoLon.cacTeBaoNhatThietChon());
        System.out.println(danhSachKetQua.taoDaThuc(danhSachKetQua.getDanhSachTeBaoLon()));
    }
}