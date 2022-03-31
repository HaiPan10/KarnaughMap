import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class KarnaughMap {
    private static final char ONE = 9640;
    private static final char ZERO = 9744;
    private String pattern;
    private int [][]map;
    private int type;
    private final int index;

    private KarnaughMap(int type){
        setType(type);
        if(this.type == 3 || this.type == 4){
            index = 2;
        }
        else if(this.type == 2)
            index = 1;
        else
            index = 0;
        if(getType() == 4)
            setMap(new int[4][4]);
        else if(getType() == 3)
            setMap(new int[4][2]);
        else if(getType() == 2)
            setMap(new int[2][2]);
        else
            setMap(new int[1][2]);
    }

    public KarnaughMap(DaThuc daThuc){
        this(daThuc.createPattern().length());
        this.pattern = daThuc.createPattern();
        for(DonThuc dt : daThuc.getDaThuc()){
            nhap(dt,1);
        }
    }

    public KarnaughMap(String pattern){
        this(pattern.length());
        setPattern(pattern);
    }

    public KarnaughMap(DonThuc dt, String pattern){
        this(pattern);
        nhap(dt,1);
    }

    public static ArrayList<String> locBinary(ArrayList<String> binaryStrings, String binary){
        ArrayList<String> filter = new ArrayList<>();
        String regex = binary.replaceAll("x",".");
        for(String temp : binaryStrings){
            if(Pattern.matches(regex,temp)){
                filter.add(temp);
            }
        }
        return filter;
    }

    public static ArrayList<String> taoCacChuoiNhiPhan(int n){
        ArrayList<String> binaryStrings = new ArrayList<>();
        for(int i = 0; i < Math.pow(2.0, n * 1.0); i++){
            String temp;
            temp = String.format("%" + n + "s", Integer.toBinaryString(i)).replaceAll(" ","0");
            binaryStrings.add(temp);
        }
        return binaryStrings;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public static String grayToBinary(String gray){
        if(gray.length() == 1){
            return gray;
        }
        else{
            return switch (gray) {
                case "00" -> "00";
                case "01" -> "01";
                case "11" -> "10";
                default -> "11";
            };
        }
    }

    public static String binaryToGray(String binary){
        if(binary.length() == 1)
            return binary;
        else{
            return switch (binary) {
                case "00" -> "00";
                case "01" -> "01";
                case "10" -> "11";
                default -> "10";
            };
        }
    }

    public void nhap(DonThuc dt,int bit){
        String binary = dt.getBinary(pattern);
        int n = this.type;
        if(this.type == 1) {
            int j = Integer.parseInt(dt.getBinary(pattern),2);
            map[0][j] = bit;
            return;
        }
        ArrayList<String> binaryStrings = taoCacChuoiNhiPhan(n);
        ArrayList<String> filter = locBinary(binaryStrings,binary);
        for(String temp : filter){
            String left = temp.substring(0,this.index);
            String right = temp.substring(this.index,n);
            int i = Integer.parseInt(grayToBinary(left),2);
            int j = Integer.parseInt(grayToBinary(right),2);
            map[i][j] = bit;
        }
    }

    public void hienThi(){
        for(int[]i : getMap())
        {
            for(int j : i)
            {
                if(j == 1)
                    System.out.printf("\t%c\t",ONE);
                else
                    System.out.printf("\t%c\t",ZERO);
            }
            System.out.println();
        }
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<String> cacGiaTriMot(){
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i < map.length; i++){
            String temp = String.format("%"+this.index+"s",Integer.toBinaryString(i)).replace(" ","0");
            StringBuilder sb = new StringBuilder(binaryToGray(temp));
            for(int j = 0; j < map[i].length;j++){
                if(map[i][j] == 1){
                    String temp2;
                    if(this.type == 3){
                        temp2 = Integer.toBinaryString(j);
                    }
                    else
                        temp2 = String.format("%"+this.index+"s",Integer.toBinaryString(j)).replace(" ","0");
                    String temp3 = sb.toString() + binaryToGray(temp2);
                    result.add(temp3);
                }
            }
        }
        return result;
    }
}
