package sample;

public class WAR {
    public int hp=150,atk=20;
    int skidmg=0;
    int skillrange;
    public int[] skillrangelist={1};
    public int[] skilldmglist={50};
    public int mp=40;
    public int skillcount=1;
    public String[] skillarry={"會心一擊"};
    public int[] skillmp={20};
    public int mpold=50;
    public WAR() {
    }
    public void skilluse(String skillname)
    {
        for(int i =0 ; i< skillcount;i++) {
            if (skillname == skillarry[i]) {
                 skidmg = skilldmglist[i];
                 mpold=mp;
                mp-=skillmp[i];
                skillrange=skillrangelist[i];
            }
        }
    }
}
