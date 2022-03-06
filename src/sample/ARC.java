package sample;
//...
public class ARC {
    public int hp=100,atk=30,mp=50,skidmg=0,skillrange=0;
    public int skillcount=1;
    public int[] skillrangelist={4};
    public int[] skilldmglist={40};
    public String[] skillarry={"狙擊"};
    public int[] skillmp={20};
    public int mpold=50;
    public ARC()
    {

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
