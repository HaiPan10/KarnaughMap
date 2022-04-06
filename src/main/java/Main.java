import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static String pattern;
    public static void main(String[] Args){
        menu();
    }

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        while(true){
            String choose;
            System.out.println("\t\tKet thuc chuong trinh nhap 0");
            System.out.print("\t\tNhap da thuc: ");
            choose = sc.nextLine();
            DaThuc daThuc = new DaThuc(choose);
            pattern = daThuc.createPattern();
            if(!choose.equals("0") && choose.length() > 0 && pattern.length() > 1){
                String result = "";
                System.out.println();
                System.out.print("Da thuc ban dau: ");
                daThuc.hienThi();
                if(!daThuc.rutGon().getDaThuc().isEmpty()){
                    KarnaughMap map = new KarnaughMap(daThuc.rutGon());
                    map.hienThi();
                    if(map.cacGiaTriMot().size() < Math.pow(2.0,(double)pattern.length())){
                        QuanLyTeBaoLon danhSachTeBao = new QuanLyTeBaoLon(pattern);
                        danhSachTeBao.chonTeBaoLon(map.cacGiaTriMot());
                        danhSachTeBao.hienThi();
                        danhSachTeBao.cacTeBaoNhatThietChon(map.cacGiaTriMot(),danhSachTeBao.getDanhSachTeBaoLon(), new ArrayList<>());
                        result = QuanLyTeBaoLon.danhSachDuocChon;
                    }
                    else
                        result = "1";
                }
                else
                    result = "0";
                System.out.printf("\t\tKet qua da thuc toi thieu = %s\n",result);
            }
            else if(choose.equals("0"))
                break;
            else
                System.out.println("\t\tNhap sai nhap lai");
            System.out.println("===========================================================");
        }
    }
}