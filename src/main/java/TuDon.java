public class TuDon {
    private String bien;

    public TuDon(String tuDon){
        this.bien = tuDon;
    }

    public void hienThi(){
        System.out.print(this.bien);
    }

    public char getLetter(){
        return this.bien.charAt(0);
    }

    public String getBien() {
        return rutGon();
    }

    public void setBien(String bien) {
        this.bien = bien;
    }

    public String rutGon(){
        return this.bien.replaceAll("''","");
    }

    public char getBit(){
        if(rutGon().contains("'"))
            return '0';
        else
            return '1';
    }
}
