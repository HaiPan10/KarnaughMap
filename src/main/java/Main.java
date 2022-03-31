public class Main {
    public static String pattern;
    public static void main(String[] Args){
        DaThuc daThuc = new DaThuc("a'b'c'+a'bc'+ab'c+abc'+abc");
        KarnaughMap map = new KarnaughMap(daThuc);
        map.hienThi();
        pattern = daThuc.createPattern();
        QuanLyTeBaoLon danhSachTeBaoLon = new QuanLyTeBaoLon(pattern);
        danhSachTeBaoLon.chonTeBaoLon(map.cacGiaTriMot());
        danhSachTeBaoLon.hienThi();
        //dt.hienThi();
//        dt.themTuDon(new TuDon("y'"));
//        dt.themTuDon(new TuDon("z'"));
//        dt.themTuDon(new TuDon("t"));
//        System.out.println(dt.getBinary(map.getPattern()));
    }
}
