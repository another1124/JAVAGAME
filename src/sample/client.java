package sample;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client extends Thread{
    int mwx=6,mwy=10,ewx=6,ewy=10,max=7,may=10,eax=7,eay=10,dpc=1,mwhp=150,ewhp=150,mahp=100,eahp=100,mwmp=40,mamp=50,ewmp=40,eamp=50;
    boolean firstturn=false, flag=true;
    boolean stflag=false;
    boolean mywtrun=true,mywac=false,enwtrun=false;
    boolean WATKflag=false,ATKflag=false;
    boolean deathflag=false,deathflaga=false;
    boolean winflag=false,loseflag=false;
    public void update(int mwx,int mwy,int mahp,int eahp,int max,int may,int ewx,int ewy,int dpc,int mwhp,int ewhp,int mamp,int eamp,int ewmp,int mwmp)
    {
        this.mahp=mahp;
        this.eahp=eahp;
        this.mwx=mwx;
        this.mwy=mwy;
        this.max=max;
        this.may=may;
        //this.ewx=ewx;
        //this.ewy=ewy;
        this.dpc=dpc;
        this.mwhp=mwhp;
        this.ewhp=ewhp;
        this.mwmp=mwmp;
        this.mamp=mamp;
        this.eamp=eamp;
        this.ewmp=ewmp;
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
            mywtrun=Boolean.parseBoolean(input.readLine());
            stflag=true;
            while (true) {
                while (flag==true) {
                    output.println(mwx);
                    output.println(mwy);
                    output.println(ewx);
                    output.println(ewy);
                    output.println(max);
                    output.println(may);
                    output.println(eax);
                    output.println(eay);
                    //output.println(dpc);
                    output.println(mahp);
                    output.println(mamp);
                    output.println(eahp);
                    output.println(eamp);
                    output.println(mwhp);
                    output.println(mwmp);
                    output.println(ewhp);
                    output.println(ewmp);
                    output.println(mywtrun);
                    output.println(winflag);
                    output.println(loseflag);
                    if(mywtrun==false) {
                        System.out.println("CHANGE2");
                        flag = false;
                    }
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (flag==false)
                {
                    mwx = Integer.parseInt(input.readLine());
                    mwy = Integer.parseInt(input.readLine());
                    ewx = Integer.parseInt(input.readLine());
                    ewy = Integer.parseInt(input.readLine());
                    max =Integer.parseInt(input.readLine());
                    may =Integer.parseInt(input.readLine());
                    eax =Integer.parseInt(input.readLine());
                    eay =Integer.parseInt(input.readLine());
                    dpc = Integer.parseInt(input.readLine());
                    mahp =Integer.parseInt(input.readLine());
                    if(mahp==0)
                    {
                        deathflaga=true;
                    }
                    mamp =Integer.parseInt(input.readLine());
                    eahp=Integer.parseInt(input.readLine());
                    eamp=Integer.parseInt(input.readLine());
                    mwhp = Integer.parseInt(input.readLine());
                    if(mwhp==0)
                    {
                        deathflag=true;
                    }
                    mwmp =Integer.parseInt(input.readLine());
                    ewhp = Integer.parseInt(input.readLine());
                    ewmp=Integer.parseInt(input.readLine());
                    mywtrun = Boolean.parseBoolean(input.readLine());
                    loseflag=Boolean.parseBoolean(input.readLine());
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

    public void shotdown()
    {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
