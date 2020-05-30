package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client extends Thread{
    int mwx=6,mwy=10,ewx=6,ewy=2,dpc=1,mwhp=150,ewhp=150;
    boolean firstturn=false, flag=true;
    boolean test=false;
    boolean mywtrun=true,mywac=false,enwtrun=false;
    boolean WATKflag=false,ATKflag=false;
    public void update(int mwx,int mwy,int ewx,int ewy,int dpc,int mwhp,int ewhp)
    {
        this.mwx=mwx;
        this.mwy=mwy;
        this.ewx=ewx;
        this.ewy=ewy;
        this.dpc=dpc;
        this.mwhp=mwhp;
        this.ewhp=ewhp;
    }
    public void update(boolean mywtrun)
    {
        this.mywtrun=mywtrun;
    }
    Socket client;
    PrintWriter output;
    BufferedReader input;
    public void run()
    {
        try {
            client = new Socket("localhost", 7777);
            output = new PrintWriter(client.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true) {
                while (flag==true) {
                    output.println(mwx);
                    output.println(mwy);
                    output.println(ewx);
                    output.println(ewy);
                    output.println(dpc);
                    output.println(mwhp);
                    output.println(ewhp);
                    output.println(mywtrun);
                    if(mywtrun==false) {
                        System.out.println("CHANGE2");
                        flag = false;
                    }
                }
                while (flag==false)
                {

                    mwx = Integer.parseInt(input.readLine());
                    mwy = Integer.parseInt(input.readLine());
                    ewx = Integer.parseInt(input.readLine());
                    ewy = Integer.parseInt(input.readLine());
                    dpc = Integer.parseInt(input.readLine());
                    mwhp = Integer.parseInt(input.readLine());
                    ewhp = Integer.parseInt(input.readLine());
                    mywtrun = Boolean.parseBoolean(input.readLine());
                    if(mywtrun==true) {
                        System.out.println("CHANGE");
                        flag = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
