import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;

public class DaThuc {
    private ArrayList<DonThuc> daThuc;

    public DaThuc(){
        this.daThuc = new ArrayList<>();
    }

    public DaThuc(String daThuc){
        String[] cacDonThuc = daThuc.split("[+]");
        this.daThuc = new ArrayList<>();
        for(String donThuc : cacDonThuc){
            DonThuc dt = new DonThuc(donThuc);
            this.daThuc.add(dt);
        }
    }

    public ArrayList<DonThuc> getDaThuc() {
        return daThuc;
    }

    public void setDaThuc(ArrayList<DonThuc> daThuc) {
        this.daThuc = daThuc;
    }

    public void hienThi(){
        ArrayList<String> result = new ArrayList<>();
        for(DonThuc dt : this.daThuc){
            result.add(dt.toString());
        }
        System.out.println(String.join("+",result));
    }

    public String createPattern(){
        if(this.daThuc.isEmpty())
            return null;
        StringBuilder result = new StringBuilder();
        for(DonThuc dt : this.daThuc){
            for(TuDon tuDon : dt.getDonThuc()){
                //Trong chuoi result khong tim thay ky tu tien hanh them ky tu do vao
                char c = tuDon.getLetter();
                if(result.indexOf(Character.toString(c)) == -1){
                    result.append(c);
                }
            }
        }
        char[] temp = result.toString().toCharArray();
        Arrays.sort(temp);
        return Arrays.toString(temp).replaceAll("[^a-zA-Z]","");
    }

    public DaThuc rutGon(){
        DaThuc result = new DaThuc();
        for(DonThuc donThuc : this.daThuc){
            if(donThuc.rutGon() != null)
                result.getDaThuc().add(donThuc);
        }
        return result;
    }
}
