package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import javax.naming.spi.InitialContextFactory;
import java.net.URL;
import java.nio.file.Watchable;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button BT11,BT12,BT13,BT14,BT15,BT16,BT17,BT18,BT19,BT110,BT111,BT112;
    public Button BT21,BT22,BT23,BT24,BT25,BT26,BT27,BT28,BT29,BT210,BT211,BT212;
    public Button BT31,BT32,BT33,BT34,BT35,BT36,BT37,BT38,BT39,BT310,BT311,BT312;
    public Button BT41,BT42,BT43,BT44,BT45,BT46,BT47,BT48,BT49,BT410,BT411,BT412;
    public Button BT51,BT52,BT53,BT54,BT55,BT56,BT57,BT58,BT59,BT510,BT511,BT512;
    public Button BT61,BT62,BT63,BT64,BT65,BT66,BT67,BT68,BT69,BT610,BT611,BT612;
    public Button BT71,BT72,BT73,BT74,BT75,BT76,BT77,BT78,BT79,BT710,BT711,BT712;
    public Button BT81,BT82,BT83,BT84,BT85,BT86,BT87,BT88,BT89,BT810,BT811,BT812;
    public Button BT91,BT92,BT93,BT94,BT95,BT96,BT97,BT98,BT99,BT910,BT911,BT912;
    public Button BT101,BT102,BT103,BT104,BT105,BT106,BT107,BT108,BT109,BT1010,BT1011,BT1012;

    public Button turnend;
    public Button testturn;
    public Button ATTACK;
    public Button WAIT;
    public Label enwhp;
    public Label mywhp;
    private client client=new client();
    int mwx=6,mwy=10,ewx=6,ewy=3,dpc=1;
    int a,b,c=0,d=0;
    boolean firstturn=true,test=false;
    boolean mywtrun=true,mywac=false,enwtrun=false;
    boolean WATKflag=false,ATKflag=false;
    WAR mwar=new WAR();
    WAR ewar=new WAR();
    private String mysqlstring = "jdbc:mysql://localhost:80/GAME?user=root&password=123qwe" +
            "&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
    private Turnex turnex=new Turnex();
    private AnimationTimer animationTimer=new AnimationTimer() {
        @Override
        public void handle(long l) {

            ewar.hp = client.ewhp;
            enwhp.setText(Integer.toString(ewar.hp));
            mwar.hp = client.mwhp;
            mywhp.setText(Integer.toString(mwar.hp));
            //ewy=13-ewy;
            if(client.deathflag==true)
            {
                GAN(mwx,mwy,2," ");
                mwx=0;mwy=0;
            }
            if(client.loseflag==true)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Gamemessage");
                alert.setHeaderText("遊戲失敗");
                alert.show();
                client.shotdown();
                animationTimer.stop();
            }
           // winlose();
            GAN(ewx, ewy, 2, " ");
            ewx = client.ewx;///GANNNNNNNNNNNNNNNN;
            ewy = 13-client.ewy;
            GAN(ewx, ewy, 2, "敵戰");
            if(client.mywtrun==true) {
                enwtrun=false;
                mywtrun=true;
                mywac=false;
                turnend.setDisable(false);
                animationTimer.stop();
                GAN(mwx,mwy,0," ");
            }
        }
    };
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        boolean flag = false;
        BT63.setText("敵戰");
        BT610.setText("我戰");
        BT610.setDisable(false);
        BT612.setText(" ");
        //    mywor.setOnMouseClicked(e -> {
        //  range(mwx,mwy);
                /*mywor.setPadding(new Insets(0,0,0,30));
                BT512.setDisable(true);
                BT512.setDisable(false);*/
        //    System.out.println("TEST");
        //
        client.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mywtrun=client.mywtrun;
        OnAction();
        turn();
        ATK();
        if (mywtrun) {//?????????????????????????????????????????

        }else
            end();



    }
    public void winlose()
    {
        if(client.deathflag==true)
        {
            client.shotdown();
        }
    }
    public void end(){
        enwtrun=true;
        textclear(1);
        mywac=false;
        mywtrun=false;
        ATKflag=false;//??????
        WATKflag=false;
        ATTACK.setDisable(true);
        WAIT.setDisable(true);
        client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
        client.update(mywtrun);
        turnend.setDisable(true);
        ATTACK.setDisable(true);
        WAIT.setDisable(true);
        textclear(1);
        turnend.setDisable(true);
        animationTimer.start();
    }
    public void ATK()
    {
        ATTACK.setOnAction(e->{
                ATKflag=true;
                WATKflag=true;
                range(mwx,mwy,1);
                mywac=false;
        });
        WAIT.setOnAction(e->{
            ATTACK.setDisable(true);
            WAIT.setDisable(true);
            mywac=false;
            GAN(mwx,mwy,1," ");
        });
    }
    public  void turn()
    {
        turnend.setOnAction(e->{
            end();
        });

        testturn.setOnAction(e->{
            enwtrun=false;
            mywtrun=true;
            turnend.setDisable(false);
            textclear(0);
            textclear(mwx,mwy,1);
        });
    }
    public void atkenw()
    {
        if(WATKflag==true) {
            WATKflag = false;
            ewar.hp-=mwar.atk;
            if(ewar.hp<=0) {
                GAN(ewx,ewy,2," ");
                ewx=0;ewy=0;
                ewar.hp = 0;
            }
            client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            enwhp.setText(Integer.toString(ewar.hp));
            if(ewar.hp==0)
            {
                client.winflag=true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Gamemessage");
                alert.setHeaderText("遊戲勝利");
                alert.showAndWait();;
                client.shotdown();
            }
            textclear(mwx,mwy,1);
            ATTACK.setDisable(true);
            WAIT.setDisable(true);
            GAN(mwx,mwy,1," ");
        }
    }
    public void range(int x,int y,int z)
    {
        System.out.println("TTTT");
        mwx=x; mwy=y;
        for (a = 1; a <= 10; a++) {
            for (b = 1; b <= 12; b++) {
                if (mwx > a)
                    c += mwx - a;
                else
                    c += a - mwx;
                if (mwy > b)
                    c += mwy - b;
                else
                    c += b-mwy;
                if (c <= z)
                    GAN(a, b,0," ");
                c = 0;
            }
        }
    }
    public  void textclear(int z)//control z=2clearText z=1button disable z=0 button enable
    {
        int a,b;
        for(a=1;a<=10;a++)
        {
            for(b=1;b<=12;b++)
            {
                GAN(a,b,z," ");
            }
        }
    }
    public  void textclear(int c,int d,int z)//Exception handling (例外處理)
    {
        int a,b;
        if(z!=22) {
            for (a = 1; a <= 10; a++) {
                for (b = 1; b <= 12; b++) {
                    if (a != c || b != d)
                        GAN(a, b, z," ");
                }
            }
        }
        else
        {
            GAN(c,d,2," ");
        }
    }

    public void OnAction()
    {
        BT11.setOnAction(e->{
            //ssss2
            if(BT11.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT11.getText()=="我戰" && mywtrun==true && mywac==false)//equal("我戰") 若有空需替換
            {
                mywac=true;
                range(1,1,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 1;
                    mwy = 1;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT11.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT12.setOnAction(e->{
            //ssss2
            if(BT12.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT12.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(1,2,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 1;
                    mwy = 2;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT12.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT13.setOnAction(e->{
            //ssss2
            if(BT13.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT13.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(1,3,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 1;
                    mwy = 3;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT13.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT14.setOnAction(e->{
            //ssss2
            if(BT14.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT14.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(1,4,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 1;
                    mwy = 4;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT14.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT15.setOnAction(e->{
            //ssss2
            if(BT15.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT15.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(1,5,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 1;
                    mwy = 5;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT15.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT16.setOnAction(e->{
            //ssss2
            if(BT16.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT16.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(1,6,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 1;
                    mwy = 6;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT16.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT17.setOnAction(e->{
            //ssss2
            if(BT17.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT17.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(1,7,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 1;
                    mwy = 7;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT17.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT18.setOnAction(e->{
            //ssss2
            if(BT18.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT18.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(1,8,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 1;
                    mwy = 8;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT18.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT19.setOnAction(e->{
            //ssss2
            if(BT19.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT19.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(1,9,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 1;
                    mwy = 9;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT19.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT110.setOnAction(e->{
            //ssss2
            if(BT110.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT110.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(1,10,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 1;
                    mwy = 10;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT110.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT111.setOnAction(e->{
            //ssss2
            if(BT111.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT111.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(1,11,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 1;
                    mwy = 11;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT111.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT112.setOnAction(e->{
            //ssss2
            if(BT112.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT112.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(1,12,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 1;
                    mwy = 12;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT112.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT21.setOnAction(e->{
            //ssss2
            if(BT21.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT21.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(2,1,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 2;
                    mwy = 1;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT21.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT22.setOnAction(e->{
            //ssss2
            if(BT22.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT22.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(2,2,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 2;
                    mwy = 2;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT22.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT23.setOnAction(e->{
            //ssss2
            if(BT23.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT23.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(2,3,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 2;
                    mwy = 3;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT23.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT24.setOnAction(e->{
            //ssss2
            if(BT24.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT24.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(2,4,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 2;
                    mwy = 4;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT24.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT25.setOnAction(e->{
            //ssss2
            if(BT25.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT25.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(2,5,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 2;
                    mwy = 5;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT25.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT26.setOnAction(e->{
            //ssss2
            if(BT26.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT26.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(2,6,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 2;
                    mwy = 6;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT26.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT27.setOnAction(e->{
            //ssss2
            if(BT27.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT27.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(2,7,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 2;
                    mwy = 7;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT27.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT28.setOnAction(e->{
            //ssss2
            if(BT28.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT28.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(2,8,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 2;
                    mwy = 8;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT28.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT29.setOnAction(e->{
            //ssss2
            if(BT29.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT29.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(2,9,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 2;
                    mwy = 9;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT29.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT210.setOnAction(e->{
            //ssss2
            if(BT210.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT210.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(2,10,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 2;
                    mwy = 10;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT210.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT211.setOnAction(e->{
            //ssss2
            if(BT211.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT211.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(2,11,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 2;
                    mwy = 11;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT211.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT212.setOnAction(e->{
            //ssss2
            if(BT212.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT212.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(2,12,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 2;
                    mwy = 12;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT212.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT31.setOnAction(e->{
            //ssss2
            if(BT31.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT31.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(3,1,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 3;
                    mwy = 1;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT31.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT32.setOnAction(e->{
            //ssss2
            if(BT32.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT32.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(3,2,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 3;
                    mwy = 2;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT32.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT33.setOnAction(e->{
            //ssss2
            if(BT33.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT33.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(3,3,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 3;
                    mwy = 3;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT33.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT34.setOnAction(e->{
            //ssss2
            if(BT34.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT34.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(3,4,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 3;
                    mwy = 4;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT34.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT35.setOnAction(e->{
            //ssss2
            if(BT35.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT35.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(3,5,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 3;
                    mwy = 5;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT35.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT36.setOnAction(e->{
            //ssss2
            if(BT36.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT36.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(3,6,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 3;
                    mwy = 6;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT36.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT37.setOnAction(e->{
            //ssss2
            if(BT37.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT37.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(3,7,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 3;
                    mwy = 7;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT37.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT38.setOnAction(e->{
            //ssss2
            if(BT38.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT38.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(3,8,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 3;
                    mwy = 8;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT38.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT39.setOnAction(e->{
            //ssss2
            if(BT39.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT39.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(3,9,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 3;
                    mwy = 9;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT39.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT310.setOnAction(e->{
            //ssss2
            if(BT310.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT310.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(3,10,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 3;
                    mwy = 10;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT310.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT311.setOnAction(e->{
            //ssss2
            if(BT311.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT311.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(3,11,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 3;
                    mwy = 11;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT311.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT312.setOnAction(e->{
            //ssss2
            if(BT312.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT312.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(3,12,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 3;
                    mwy = 12;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT312.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT41.setOnAction(e->{
            //ssss2
            if(BT41.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT41.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(4,1,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 4;
                    mwy = 1;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT41.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT42.setOnAction(e->{
            //ssss2
            if(BT42.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT42.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(4,2,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 4;
                    mwy = 2;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT42.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT43.setOnAction(e->{
            //ssss2
            if(BT43.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT43.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(4,3,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 4;
                    mwy = 3;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT43.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT44.setOnAction(e->{
            //ssss2
            if(BT44.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT44.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(4,4,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 4;
                    mwy = 4;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT44.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT45.setOnAction(e->{
            //ssss2
            if(BT45.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT45.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(4,5,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 4;
                    mwy = 5;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT45.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT46.setOnAction(e->{
            //ssss2
            if(BT46.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT46.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(4,6,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 4;
                    mwy = 6;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT46.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT47.setOnAction(e->{
            //ssss2
            if(BT47.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT47.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(4,7,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 4;
                    mwy = 7;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT47.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT48.setOnAction(e->{
            //ssss2
            if(BT48.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT48.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(4,8,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 4;
                    mwy = 8;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT48.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT49.setOnAction(e->{
            //ssss2
            if(BT49.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT49.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(4,9,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 4;
                    mwy = 9;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT49.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT410.setOnAction(e->{
            //ssss2
            if(BT410.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT410.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(4,10,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 4;
                    mwy = 10;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT410.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT411.setOnAction(e->{
            //ssss2
            if(BT411.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT411.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(4,11,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 4;
                    mwy = 11;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT411.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT412.setOnAction(e->{
            //ssss2
            if(BT412.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT412.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(4,12,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 4;
                    mwy = 12;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT412.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT51.setOnAction(e->{
            //ssss2
            if(BT51.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT51.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(5,1,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 5;
                    mwy = 1;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT51.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT52.setOnAction(e->{
            //ssss2
            if(BT52.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT52.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(5,2,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 5;
                    mwy = 2;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT52.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT53.setOnAction(e->{
            //ssss2
            if(BT53.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT53.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(5,3,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 5;
                    mwy = 3;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT53.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT54.setOnAction(e->{
            //ssss2
            if(BT54.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT54.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(5,4,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 5;
                    mwy = 4;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT54.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT55.setOnAction(e->{
            //ssss2
            if(BT55.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT55.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(5,5,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 5;
                    mwy = 5;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT55.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT56.setOnAction(e->{
            //ssss2
            if(BT56.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT56.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(5,6,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 5;
                    mwy = 6;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT56.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT57.setOnAction(e->{
            //ssss2
            if(BT57.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT57.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(5,7,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 5;
                    mwy = 7;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT57.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT58.setOnAction(e->{
            //ssss2
            if(BT58.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT58.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(5,8,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 5;
                    mwy = 8;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT58.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT59.setOnAction(e->{
            //ssss2
            if(BT59.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT59.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(5,9,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 5;
                    mwy = 9;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT59.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT510.setOnAction(e->{
            //ssss2
            if(BT510.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT510.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(5,10,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 5;
                    mwy = 10;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT510.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT511.setOnAction(e->{
            //ssss2
            if(BT511.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT511.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(5,11,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 5;
                    mwy = 11;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT511.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT512.setOnAction(e->{
            //ssss2
            if(BT512.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT512.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(5,12,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 5;
                    mwy = 12;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT512.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT61.setOnAction(e->{
            //ssss2
            if(BT61.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT61.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(6,1,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 6;
                    mwy = 1;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT61.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT62.setOnAction(e->{
            //ssss2
            if(BT62.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT62.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(6,2,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 6;
                    mwy = 2;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT62.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT63.setOnAction(e->{
            //ssss2
            if(BT63.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT63.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(6,3,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 6;
                    mwy = 3;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT63.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT64.setOnAction(e->{
            //ssss2
            if(BT64.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT64.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(6,4,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 6;
                    mwy = 4;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT64.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT65.setOnAction(e->{
            //ssss2
            if(BT65.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT65.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(6,5,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 6;
                    mwy = 5;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT65.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT66.setOnAction(e->{
            //ssss2
            if(BT66.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT66.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(6,6,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 6;
                    mwy = 6;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT66.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT67.setOnAction(e->{
            //ssss2
            if(BT67.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT67.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(6,7,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 6;
                    mwy = 7;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT67.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT68.setOnAction(e->{
            //ssss2
            if(BT68.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT68.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(6,8,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 6;
                    mwy = 8;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT68.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT69.setOnAction(e->{
            //ssss2
            if(BT69.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT69.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(6,9,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 6;
                    mwy = 9;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT69.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT610.setOnAction(e->{
            //ssss2
            if(BT610.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT610.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(6,10,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 6;
                    mwy = 10;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT610.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT611.setOnAction(e->{
            //ssss2
            if(BT611.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT611.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(6,11,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 6;
                    mwy = 11;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT611.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT612.setOnAction(e->{
            //ssss2
            if(BT612.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT612.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(6,12,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 6;
                    mwy = 12;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT612.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT71.setOnAction(e->{
            //ssss2
            if(BT71.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT71.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(7,1,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 7;
                    mwy = 1;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT71.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT72.setOnAction(e->{
            //ssss2
            if(BT72.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT72.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(7,2,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 7;
                    mwy = 2;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT72.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT73.setOnAction(e->{
            //ssss2
            if(BT73.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT73.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(7,3,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 7;
                    mwy = 3;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT73.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT74.setOnAction(e->{
            //ssss2
            if(BT74.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT74.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(7,4,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 7;
                    mwy = 4;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT74.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT75.setOnAction(e->{
            //ssss2
            if(BT75.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT75.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(7,5,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 7;
                    mwy = 5;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT75.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT76.setOnAction(e->{
            //ssss2
            if(BT76.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT76.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(7,6,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 7;
                    mwy = 6;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT76.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT77.setOnAction(e->{
            //ssss2
            if(BT77.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT77.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(7,7,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 7;
                    mwy = 7;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT77.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT78.setOnAction(e->{
            //ssss2
            if(BT78.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT78.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(7,8,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 7;
                    mwy = 8;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT78.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT79.setOnAction(e->{
            //ssss2
            if(BT79.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT79.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(7,9,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 7;
                    mwy = 9;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT79.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT710.setOnAction(e->{
            //ssss2
            if(BT710.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT710.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(7,10,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 7;
                    mwy = 10;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT710.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT711.setOnAction(e->{
            //ssss2
            if(BT711.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT711.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(7,11,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 7;
                    mwy = 11;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT711.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT712.setOnAction(e->{
            //ssss2
            if(BT712.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT712.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(7,12,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 7;
                    mwy = 12;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT712.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT81.setOnAction(e->{
            //ssss2
            if(BT81.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT81.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(8,1,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 8;
                    mwy = 1;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT81.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT82.setOnAction(e->{
            //ssss2
            if(BT82.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT82.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(8,2,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 8;
                    mwy = 2;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT82.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT83.setOnAction(e->{
            //ssss2
            if(BT83.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT83.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(8,3,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 8;
                    mwy = 3;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT83.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT84.setOnAction(e->{
            //ssss2
            if(BT84.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT84.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(8,4,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 8;
                    mwy = 4;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT84.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT85.setOnAction(e->{
            //ssss2
            if(BT85.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT85.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(8,5,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 8;
                    mwy = 5;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT85.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT86.setOnAction(e->{
            //ssss2
            if(BT86.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT86.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(8,6,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 8;
                    mwy = 6;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT86.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT87.setOnAction(e->{
            //ssss2
            if(BT87.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT87.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(8,7,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 8;
                    mwy = 7;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT87.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT88.setOnAction(e->{
            //ssss2
            if(BT88.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT88.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(8,8,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 8;
                    mwy = 8;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT88.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT89.setOnAction(e->{
            //ssss2
            if(BT89.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT89.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(8,9,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 8;
                    mwy = 9;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT89.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT810.setOnAction(e->{
            //ssss2
            if(BT810.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT810.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(8,10,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 8;
                    mwy = 10;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT810.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT811.setOnAction(e->{
            //ssss2
            if(BT811.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT811.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(8,11,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 8;
                    mwy = 11;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT811.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT812.setOnAction(e->{
            //ssss2
            if(BT812.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT812.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(8,12,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 8;
                    mwy = 12;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT812.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT91.setOnAction(e->{
            //ssss2
            if(BT91.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT91.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(9,1,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 9;
                    mwy = 1;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT91.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT92.setOnAction(e->{
            //ssss2
            if(BT92.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT92.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(9,2,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 9;
                    mwy = 2;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT92.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT93.setOnAction(e->{
            //ssss2
            if(BT93.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT93.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(9,3,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 9;
                    mwy = 3;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT93.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT94.setOnAction(e->{
            //ssss2
            if(BT94.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT94.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(9,4,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 9;
                    mwy = 4;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT94.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT95.setOnAction(e->{
            //ssss2
            if(BT95.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT95.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(9,5,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 9;
                    mwy = 5;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT95.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT96.setOnAction(e->{
            //ssss2
            if(BT96.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT96.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(9,6,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 9;
                    mwy = 6;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT96.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT97.setOnAction(e->{
            //ssss2
            if(BT97.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT97.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(9,7,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 9;
                    mwy = 7;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT97.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT98.setOnAction(e->{
            //ssss2
            if(BT98.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT98.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(9,8,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 9;
                    mwy = 8;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT98.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT99.setOnAction(e->{
            //ssss2
            if(BT99.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT99.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(9,9,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 9;
                    mwy = 9;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT99.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT910.setOnAction(e->{
            //ssss2
            if(BT910.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT910.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(9,10,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 9;
                    mwy = 10;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT910.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT911.setOnAction(e->{
            //ssss2
            if(BT911.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT911.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(9,11,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 9;
                    mwy = 11;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT911.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT912.setOnAction(e->{
            //ssss2
            if(BT912.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT912.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(9,12,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 9;
                    mwy = 12;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT912.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT101.setOnAction(e->{
            //ssss2
            if(BT101.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT101.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(10,1,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 10;
                    mwy = 1;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT101.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT102.setOnAction(e->{
            //ssss2
            if(BT102.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT102.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(10,2,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 10;
                    mwy = 2;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT102.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT103.setOnAction(e->{
            //ssss2
            if(BT103.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT103.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(10,3,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 10;
                    mwy = 3;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT103.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT104.setOnAction(e->{
            //ssss2
            if(BT104.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT104.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(10,4,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 10;
                    mwy = 4;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT104.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT105.setOnAction(e->{
            //ssss2
            if(BT105.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT105.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(10,5,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 10;
                    mwy = 5;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT105.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT106.setOnAction(e->{
            //ssss2
            if(BT106.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT106.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(10,6,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 10;
                    mwy = 6;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT106.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT107.setOnAction(e->{
            //ssss2
            if(BT107.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT107.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(10,7,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 10;
                    mwy = 7;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT107.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT108.setOnAction(e->{
            //ssss2
            if(BT108.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT108.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(10,8,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 10;
                    mwy = 8;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT108.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT109.setOnAction(e->{
            //ssss2
            if(BT109.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT109.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(10,9,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 10;
                    mwy = 9;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT109.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT1010.setOnAction(e->{
            //ssss2
            if(BT1010.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT1010.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(10,10,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 10;
                    mwy = 10;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT1010.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT1011.setOnAction(e->{
            //ssss2
            if(BT1011.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT1011.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(10,11,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 10;
                    mwy = 11;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT1011.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
        BT1012.setOnAction(e->{
            //ssss2
            if(BT1012.getText()=="敵戰")
            {
                atkenw();
                client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
            }
            else if(BT1012.getText()=="我戰" && mywtrun==true && mywac==false)
            {
                mywac=true;
                range(10,12,2);

            }
            else
            {
                if(mywac==true) {
                    textclear(mwx,mwy,22);
                    mwx = 10;
                    mwy = 12;
                    client.update(mwx,mwy,ewx,ewy,dpc,mwar.hp,ewar.hp);
                    ATTACK.setDisable(false);
                    WAIT.setDisable(false);
                    textclear(mwx,mwy,1);
                    BT1012.setText("我戰");
                    mywac=false;
                    //mywtrun=false;
                }
            }
        });
    }
    public void GAN(int x,int y,int z,String a)
    {
        switch (x)
        {
            case 1:
                switch (y) {
                    case 1:

                        //selectflag1
                        if(z==2)
                            BT11.setText(a);
                        else if(z==1)
                            BT11.setDisable(true);
                        else if(z==0)
                            BT11.setDisable(false);
                        break;
                    case 2:

                        //selectflag1
                        if(z==2)
                            BT12.setText(a);
                        else if(z==1)
                            BT12.setDisable(true);
                        else if(z==0)
                            BT12.setDisable(false);
                        break;
                    case 3:

                        //selectflag1
                        if(z==2)
                            BT13.setText(a);
                        else if(z==1)
                            BT13.setDisable(true);
                        else if(z==0)
                            BT13.setDisable(false);
                        break;
                    case 4:

                        //selectflag1
                        if(z==2)
                            BT14.setText(a);
                        else if(z==1)
                            BT14.setDisable(true);
                        else if(z==0)
                            BT14.setDisable(false);
                        break;
                    case 5:

                        //selectflag1
                        if(z==2)
                            BT15.setText(a);
                        else if(z==1)
                            BT15.setDisable(true);
                        else if(z==0)
                            BT15.setDisable(false);
                        break;
                    case 6:

                        //selectflag1
                        if(z==2)
                            BT16.setText(a);
                        else if(z==1)
                            BT16.setDisable(true);
                        else if(z==0)
                            BT16.setDisable(false);
                        break;
                    case 7:

                        //selectflag1
                        if(z==2)
                            BT17.setText(a);
                        else if(z==1)
                            BT17.setDisable(true);
                        else if(z==0)
                            BT17.setDisable(false);
                        break;
                    case 8:

                        //selectflag1
                        if(z==2)
                            BT18.setText(a);
                        else if(z==1)
                            BT18.setDisable(true);
                        else if(z==0)
                            BT18.setDisable(false);
                        break;
                    case 9:

                        //selectflag1
                        if(z==2)
                            BT19.setText(a);
                        else if(z==1)
                            BT19.setDisable(true);
                        else if(z==0)
                            BT19.setDisable(false);
                        break;
                    case 10:
                        //selectflag1
                        if(z==2)
                            BT110.setText(a);
                        else if(z==1)
                            BT110.setDisable(true);
                        else if(z==0)
                            BT110.setDisable(false);

                        break;
                    case 11:
                        //selectflag1
                        if(z==2)
                            BT111.setText(a);
                        else if(z==1)
                            BT111.setDisable(true);
                        else if(z==0)
                            BT111.setDisable(false);

                        break;
                    case 12:
                        //selectflag1
                        if(z==2)
                            BT112.setText(a);
                        else if(z==1)
                            BT112.setDisable(true);
                        else if(z==0)
                            BT112.setDisable(false);

                        break;
                }
                break;
            case 2:
                switch (y) {
                    case 1:

                        //selectflag2
                        if(z==2)
                            BT21.setText(a);
                        else if(z==1)
                            BT21.setDisable(true);
                        else if(z==0)
                            BT21.setDisable(false);
                        break;
                    case 2:

                        //selectflag2
                        if(z==2)
                            BT22.setText(a);
                        else if(z==1)
                            BT22.setDisable(true);
                        else if(z==0)
                            BT22.setDisable(false);
                        break;
                    case 3:

                        //selectflag2
                        if(z==2)
                            BT23.setText(a);
                        else if(z==1)
                            BT23.setDisable(true);
                        else if(z==0)
                            BT23.setDisable(false);
                        break;
                    case 4:

                        //selectflag2
                        if(z==2)
                            BT24.setText(a);
                        else if(z==1)
                            BT24.setDisable(true);
                        else if(z==0)
                            BT24.setDisable(false);
                        break;
                    case 5:

                        //selectflag2
                        if(z==2)
                            BT25.setText(a);
                        else if(z==1)
                            BT25.setDisable(true);
                        else if(z==0)
                            BT25.setDisable(false);
                        break;
                    case 6:

                        //selectflag2
                        if(z==2)
                            BT26.setText(a);
                        else if(z==1)
                            BT26.setDisable(true);
                        else if(z==0)
                            BT26.setDisable(false);
                        break;
                    case 7:

                        //selectflag2
                        if(z==2)
                            BT27.setText(a);
                        else if(z==1)
                            BT27.setDisable(true);
                        else if(z==0)
                            BT27.setDisable(false);
                        break;
                    case 8:

                        //selectflag2
                        if(z==2)
                            BT28.setText(a);
                        else if(z==1)
                            BT28.setDisable(true);
                        else if(z==0)
                            BT28.setDisable(false);
                        break;
                    case 9:

                        //selectflag2
                        if(z==2)
                            BT29.setText(a);
                        else if(z==1)
                            BT29.setDisable(true);
                        else if(z==0)
                            BT29.setDisable(false);
                        break;
                    case 10:
                        //selectflag2
                        if(z==2)
                            BT210.setText(a);
                        else if(z==1)
                            BT210.setDisable(true);
                        else if(z==0)
                            BT210.setDisable(false);

                        break;
                    case 11:
                        //selectflag2
                        if(z==2)
                            BT211.setText(a);
                        else if(z==1)
                            BT211.setDisable(true);
                        else if(z==0)
                            BT211.setDisable(false);

                        break;
                    case 12:
                        //selectflag2
                        if(z==2)
                            BT212.setText(a);
                        else if(z==1)
                            BT212.setDisable(true);
                        else if(z==0)
                            BT212.setDisable(false);

                        break;
                }
                break;
            case 3:
                switch (y) {
                    case 1:

                        //selectflag3
                        if(z==2)
                            BT31.setText(a);
                        else if(z==1)
                            BT31.setDisable(true);
                        else if(z==0)
                            BT31.setDisable(false);
                        break;
                    case 2:

                        //selectflag3
                        if(z==2)
                            BT32.setText(a);
                        else if(z==1)
                            BT32.setDisable(true);
                        else if(z==0)
                            BT32.setDisable(false);
                        break;
                    case 3:

                        //selectflag3
                        if(z==2)
                            BT33.setText(a);
                        else if(z==1)
                            BT33.setDisable(true);
                        else if(z==0)
                            BT33.setDisable(false);
                        break;
                    case 4:

                        //selectflag3
                        if(z==2)
                            BT34.setText(a);
                        else if(z==1)
                            BT34.setDisable(true);
                        else if(z==0)
                            BT34.setDisable(false);
                        break;
                    case 5:

                        //selectflag3
                        if(z==2)
                            BT35.setText(a);
                        else if(z==1)
                            BT35.setDisable(true);
                        else if(z==0)
                            BT35.setDisable(false);
                        break;
                    case 6:

                        //selectflag3
                        if(z==2)
                            BT36.setText(a);
                        else if(z==1)
                            BT36.setDisable(true);
                        else if(z==0)
                            BT36.setDisable(false);
                        break;
                    case 7:

                        //selectflag3
                        if(z==2)
                            BT37.setText(a);
                        else if(z==1)
                            BT37.setDisable(true);
                        else if(z==0)
                            BT37.setDisable(false);
                        break;
                    case 8:

                        //selectflag3
                        if(z==2)
                            BT38.setText(a);
                        else if(z==1)
                            BT38.setDisable(true);
                        else if(z==0)
                            BT38.setDisable(false);
                        break;
                    case 9:

                        //selectflag3
                        if(z==2)
                            BT39.setText(a);
                        else if(z==1)
                            BT39.setDisable(true);
                        else if(z==0)
                            BT39.setDisable(false);
                        break;
                    case 10:
                        //selectflag3
                        if(z==2)
                            BT310.setText(a);
                        else if(z==1)
                            BT310.setDisable(true);
                        else if(z==0)
                            BT310.setDisable(false);

                        break;
                    case 11:
                        //selectflag3
                        if(z==2)
                            BT311.setText(a);
                        else if(z==1)
                            BT311.setDisable(true);
                        else if(z==0)
                            BT311.setDisable(false);

                        break;
                    case 12:
                        //selectflag3
                        if(z==2)
                            BT312.setText(a);
                        else if(z==1)
                            BT312.setDisable(true);
                        else if(z==0)
                            BT312.setDisable(false);

                        break;
                }
                break;
            case 4:
                switch (y) {
                    case 1:

                        //selectflag4
                        if(z==2)
                            BT41.setText(a);
                        else if(z==1)
                            BT41.setDisable(true);
                        else if(z==0)
                            BT41.setDisable(false);
                        break;
                    case 2:

                        //selectflag4
                        if(z==2)
                            BT42.setText(a);
                        else if(z==1)
                            BT42.setDisable(true);
                        else if(z==0)
                            BT42.setDisable(false);
                        break;
                    case 3:

                        //selectflag4
                        if(z==2)
                            BT43.setText(a);
                        else if(z==1)
                            BT43.setDisable(true);
                        else if(z==0)
                            BT43.setDisable(false);
                        break;
                    case 4:

                        //selectflag4
                        if(z==2)
                            BT44.setText(a);
                        else if(z==1)
                            BT44.setDisable(true);
                        else if(z==0)
                            BT44.setDisable(false);
                        break;
                    case 5:

                        //selectflag4
                        if(z==2)
                            BT45.setText(a);
                        else if(z==1)
                            BT45.setDisable(true);
                        else if(z==0)
                            BT45.setDisable(false);
                        break;
                    case 6:

                        //selectflag4
                        if(z==2)
                            BT46.setText(a);
                        else if(z==1)
                            BT46.setDisable(true);
                        else if(z==0)
                            BT46.setDisable(false);
                        break;
                    case 7:

                        //selectflag4
                        if(z==2)
                            BT47.setText(a);
                        else if(z==1)
                            BT47.setDisable(true);
                        else if(z==0)
                            BT47.setDisable(false);
                        break;
                    case 8:

                        //selectflag4
                        if(z==2)
                            BT48.setText(a);
                        else if(z==1)
                            BT48.setDisable(true);
                        else if(z==0)
                            BT48.setDisable(false);
                        break;
                    case 9:

                        //selectflag4
                        if(z==2)
                            BT49.setText(a);
                        else if(z==1)
                            BT49.setDisable(true);
                        else if(z==0)
                            BT49.setDisable(false);
                        break;
                    case 10:
                        //selectflag4
                        if(z==2)
                            BT410.setText(a);
                        else if(z==1)
                            BT410.setDisable(true);
                        else if(z==0)
                            BT410.setDisable(false);

                        break;
                    case 11:
                        //selectflag4
                        if(z==2)
                            BT411.setText(a);
                        else if(z==1)
                            BT411.setDisable(true);
                        else if(z==0)
                            BT411.setDisable(false);

                        break;
                    case 12:
                        //selectflag4
                        if(z==2)
                            BT412.setText(a);
                        else if(z==1)
                            BT412.setDisable(true);
                        else if(z==0)
                            BT412.setDisable(false);

                        break;
                }
                break;
            case 5:
                switch (y) {
                    case 1:

                        //selectflag5
                        if(z==2)
                            BT51.setText(a);
                        else if(z==1)
                            BT51.setDisable(true);
                        else if(z==0)
                            BT51.setDisable(false);
                        break;
                    case 2:

                        //selectflag5
                        if(z==2)
                            BT52.setText(a);
                        else if(z==1)
                            BT52.setDisable(true);
                        else if(z==0)
                            BT52.setDisable(false);
                        break;
                    case 3:

                        //selectflag5
                        if(z==2)
                            BT53.setText(a);
                        else if(z==1)
                            BT53.setDisable(true);
                        else if(z==0)
                            BT53.setDisable(false);
                        break;
                    case 4:

                        //selectflag5
                        if(z==2)
                            BT54.setText(a);
                        else if(z==1)
                            BT54.setDisable(true);
                        else if(z==0)
                            BT54.setDisable(false);
                        break;
                    case 5:

                        //selectflag5
                        if(z==2)
                            BT55.setText(a);
                        else if(z==1)
                            BT55.setDisable(true);
                        else if(z==0)
                            BT55.setDisable(false);
                        break;
                    case 6:

                        //selectflag5
                        if(z==2)
                            BT56.setText(a);
                        else if(z==1)
                            BT56.setDisable(true);
                        else if(z==0)
                            BT56.setDisable(false);
                        break;
                    case 7:

                        //selectflag5
                        if(z==2)
                            BT57.setText(a);
                        else if(z==1)
                            BT57.setDisable(true);
                        else if(z==0)
                            BT57.setDisable(false);
                        break;
                    case 8:

                        //selectflag5
                        if(z==2)
                            BT58.setText(a);
                        else if(z==1)
                            BT58.setDisable(true);
                        else if(z==0)
                            BT58.setDisable(false);
                        break;
                    case 9:

                        //selectflag5
                        if(z==2)
                            BT59.setText(a);
                        else if(z==1)
                            BT59.setDisable(true);
                        else if(z==0)
                            BT59.setDisable(false);
                        break;
                    case 10:
                        //selectflag5
                        if(z==2)
                            BT510.setText(a);
                        else if(z==1)
                            BT510.setDisable(true);
                        else if(z==0)
                            BT510.setDisable(false);

                        break;
                    case 11:
                        //selectflag5
                        if(z==2)
                            BT511.setText(a);
                        else if(z==1)
                            BT511.setDisable(true);
                        else if(z==0)
                            BT511.setDisable(false);

                        break;
                    case 12:
                        //selectflag5
                        if(z==2)
                            BT512.setText(a);
                        else if(z==1)
                            BT512.setDisable(true);
                        else if(z==0)
                            BT512.setDisable(false);

                        break;
                }
                break;
            case 6:
                switch (y) {
                    case 1:

                        //selectflag6
                        if(z==2)
                            BT61.setText(a);
                        else if(z==1)
                            BT61.setDisable(true);
                        else if(z==0)
                            BT61.setDisable(false);
                        break;
                    case 2:

                        //selectflag6
                        if(z==2)
                            BT62.setText(a);
                        else if(z==1)
                            BT62.setDisable(true);
                        else if(z==0)
                            BT62.setDisable(false);
                        break;
                    case 3:

                        //selectflag6
                        if(z==2)
                            BT63.setText(a);
                        else if(z==1)
                            BT63.setDisable(true);
                        else if(z==0)
                            BT63.setDisable(false);
                        break;
                    case 4:

                        //selectflag6
                        if(z==2)
                            BT64.setText(a);
                        else if(z==1)
                            BT64.setDisable(true);
                        else if(z==0)
                            BT64.setDisable(false);
                        break;
                    case 5:

                        //selectflag6
                        if(z==2)
                            BT65.setText(a);
                        else if(z==1)
                            BT65.setDisable(true);
                        else if(z==0)
                            BT65.setDisable(false);
                        break;
                    case 6:

                        //selectflag6
                        if(z==2)
                            BT66.setText(a);
                        else if(z==1)
                            BT66.setDisable(true);
                        else if(z==0)
                            BT66.setDisable(false);
                        break;
                    case 7:

                        //selectflag6
                        if(z==2)
                            BT67.setText(a);
                        else if(z==1)
                            BT67.setDisable(true);
                        else if(z==0)
                            BT67.setDisable(false);
                        break;
                    case 8:

                        //selectflag6
                        if(z==2)
                            BT68.setText(a);
                        else if(z==1)
                            BT68.setDisable(true);
                        else if(z==0)
                            BT68.setDisable(false);
                        break;
                    case 9:

                        //selectflag6
                        if(z==2)
                            BT69.setText(a);
                        else if(z==1)
                            BT69.setDisable(true);
                        else if(z==0)
                            BT69.setDisable(false);
                        break;
                    case 10:
                        //selectflag6
                        if(z==2)
                            BT610.setText(a);
                        else if(z==1)
                            BT610.setDisable(true);
                        else if(z==0)
                            BT610.setDisable(false);

                        break;
                    case 11:
                        //selectflag6
                        if(z==2)
                            BT611.setText(a);
                        else if(z==1)
                            BT611.setDisable(true);
                        else if(z==0)
                            BT611.setDisable(false);

                        break;
                    case 12:
                        //selectflag6
                        if(z==2)
                            BT612.setText(a);
                        else if(z==1)
                            BT612.setDisable(true);
                        else if(z==0)
                            BT612.setDisable(false);

                        break;
                }
                break;
            case 7:
                switch (y) {
                    case 1:

                        //selectflag7
                        if(z==2)
                            BT71.setText(a);
                        else if(z==1)
                            BT71.setDisable(true);
                        else if(z==0)
                            BT71.setDisable(false);
                        break;
                    case 2:

                        //selectflag7
                        if(z==2)
                            BT72.setText(a);
                        else if(z==1)
                            BT72.setDisable(true);
                        else if(z==0)
                            BT72.setDisable(false);
                        break;
                    case 3:

                        //selectflag7
                        if(z==2)
                            BT73.setText(a);
                        else if(z==1)
                            BT73.setDisable(true);
                        else if(z==0)
                            BT73.setDisable(false);
                        break;
                    case 4:

                        //selectflag7
                        if(z==2)
                            BT74.setText(a);
                        else if(z==1)
                            BT74.setDisable(true);
                        else if(z==0)
                            BT74.setDisable(false);
                        break;
                    case 5:

                        //selectflag7
                        if(z==2)
                            BT75.setText(a);
                        else if(z==1)
                            BT75.setDisable(true);
                        else if(z==0)
                            BT75.setDisable(false);
                        break;
                    case 6:

                        //selectflag7
                        if(z==2)
                            BT76.setText(a);
                        else if(z==1)
                            BT76.setDisable(true);
                        else if(z==0)
                            BT76.setDisable(false);
                        break;
                    case 7:

                        //selectflag7
                        if(z==2)
                            BT77.setText(a);
                        else if(z==1)
                            BT77.setDisable(true);
                        else if(z==0)
                            BT77.setDisable(false);
                        break;
                    case 8:

                        //selectflag7
                        if(z==2)
                            BT78.setText(a);
                        else if(z==1)
                            BT78.setDisable(true);
                        else if(z==0)
                            BT78.setDisable(false);
                        break;
                    case 9:

                        //selectflag7
                        if(z==2)
                            BT79.setText(a);
                        else if(z==1)
                            BT79.setDisable(true);
                        else if(z==0)
                            BT79.setDisable(false);
                        break;
                    case 10:
                        //selectflag7
                        if(z==2)
                            BT710.setText(a);
                        else if(z==1)
                            BT710.setDisable(true);
                        else if(z==0)
                            BT710.setDisable(false);

                        break;
                    case 11:
                        //selectflag7
                        if(z==2)
                            BT711.setText(a);
                        else if(z==1)
                            BT711.setDisable(true);
                        else if(z==0)
                            BT711.setDisable(false);

                        break;
                    case 12:
                        //selectflag7
                        if(z==2)
                            BT712.setText(a);
                        else if(z==1)
                            BT712.setDisable(true);
                        else if(z==0)
                            BT712.setDisable(false);

                        break;
                }
                break;
            case 8:
                switch (y) {
                    case 1:

                        //selectflag8
                        if(z==2)
                            BT81.setText(a);
                        else if(z==1)
                            BT81.setDisable(true);
                        else if(z==0)
                            BT81.setDisable(false);
                        break;
                    case 2:

                        //selectflag8
                        if(z==2)
                            BT82.setText(a);
                        else if(z==1)
                            BT82.setDisable(true);
                        else if(z==0)
                            BT82.setDisable(false);
                        break;
                    case 3:

                        //selectflag8
                        if(z==2)
                            BT83.setText(a);
                        else if(z==1)
                            BT83.setDisable(true);
                        else if(z==0)
                            BT83.setDisable(false);
                        break;
                    case 4:

                        //selectflag8
                        if(z==2)
                            BT84.setText(a);
                        else if(z==1)
                            BT84.setDisable(true);
                        else if(z==0)
                            BT84.setDisable(false);
                        break;
                    case 5:

                        //selectflag8
                        if(z==2)
                            BT85.setText(a);
                        else if(z==1)
                            BT85.setDisable(true);
                        else if(z==0)
                            BT85.setDisable(false);
                        break;
                    case 6:

                        //selectflag8
                        if(z==2)
                            BT86.setText(a);
                        else if(z==1)
                            BT86.setDisable(true);
                        else if(z==0)
                            BT86.setDisable(false);
                        break;
                    case 7:

                        //selectflag8
                        if(z==2)
                            BT87.setText(a);
                        else if(z==1)
                            BT87.setDisable(true);
                        else if(z==0)
                            BT87.setDisable(false);
                        break;
                    case 8:

                        //selectflag8
                        if(z==2)
                            BT88.setText(a);
                        else if(z==1)
                            BT88.setDisable(true);
                        else if(z==0)
                            BT88.setDisable(false);
                        break;
                    case 9:

                        //selectflag8
                        if(z==2)
                            BT89.setText(a);
                        else if(z==1)
                            BT89.setDisable(true);
                        else if(z==0)
                            BT89.setDisable(false);
                        break;
                    case 10:
                        //selectflag8
                        if(z==2)
                            BT810.setText(a);
                        else if(z==1)
                            BT810.setDisable(true);
                        else if(z==0)
                            BT810.setDisable(false);

                        break;
                    case 11:
                        //selectflag8
                        if(z==2)
                            BT811.setText(a);
                        else if(z==1)
                            BT811.setDisable(true);
                        else if(z==0)
                            BT811.setDisable(false);

                        break;
                    case 12:
                        //selectflag88
                        if(z==2)
                            BT812.setText(a);
                        else  if(z==1)
                            BT812.setDisable(true);
                        else if(z==0)
                            BT812.setDisable(false);

                        break;
                }
                break;
            case 9:
                switch (y) {
                    case 1:

                        //selectflag9
                        if(z==2)
                            BT91.setText(a);
                        else if(z==1)
                            BT91.setDisable(true);
                        else if(z==0)
                            BT91.setDisable(false);
                        break;
                    case 2:

                        //selectflag9
                        if(z==2)
                            BT92.setText(a);
                        else if(z==1)
                            BT92.setDisable(true);
                        else if(z==0)
                            BT92.setDisable(false);
                        break;
                    case 3:

                        //selectflag9
                        if(z==2)
                            BT93.setText(a);
                        else if(z==1)
                            BT93.setDisable(true);
                        else if(z==0)
                            BT93.setDisable(false);
                        break;
                    case 4:

                        //selectflag9
                        if(z==2)
                            BT94.setText(a);
                        else if(z==1)
                            BT94.setDisable(true);
                        else if(z==0)
                            BT94.setDisable(false);
                        break;
                    case 5:

                        //selectflag9
                        if(z==2)
                            BT95.setText(a);
                        else if(z==1)
                            BT95.setDisable(true);
                        else if(z==0)
                            BT95.setDisable(false);
                        break;
                    case 6:

                        //selectflag9
                        if(z==2)
                            BT96.setText(a);
                        else if(z==1)
                            BT96.setDisable(true);
                        else if(z==0)
                            BT96.setDisable(false);
                        break;
                    case 7:

                        //selectflag9
                        if(z==2)
                            BT97.setText(a);
                        else if(z==1)
                            BT97.setDisable(true);
                        else if(z==0)
                            BT97.setDisable(false);
                        break;
                    case 8:

                        //selectflag9
                        if(z==2)
                            BT98.setText(a);
                        else if(z==1)
                            BT98.setDisable(true);
                        else if(z==0)
                            BT98.setDisable(false);
                        break;
                    case 9:

                        //selectflag9
                        if(z==2)
                            BT99.setText(a);
                        else if(z==1)
                            BT99.setDisable(true);
                        else if(z==0)
                            BT99.setDisable(false);
                        break;
                    case 10:
                        //selectflag9
                        if(z==2)
                            BT910.setText(a);
                        else if(z==1)
                            BT910.setDisable(true);
                        else if(z==0)
                            BT910.setDisable(false);

                        break;
                    case 11:
                        //selectflag9
                        if(z==2)
                            BT911.setText(a);
                        else if(z==1)
                            BT911.setDisable(true);
                        else if(z==0)
                            BT911.setDisable(false);

                        break;
                    case 12:
                        //selectflag9
                        if(z==2)
                            BT912.setText(a);
                        else if(z==1)
                            BT912.setDisable(true);
                        else if(z==0)
                            BT912.setDisable(false);

                        break;
                }
                break;
            case 10:
                switch (y) {
                    case 1:

                        //selectflag0
                        if(z==2)
                            BT101.setText(a);
                        else if(z==1)
                            BT101.setDisable(true);
                        else if(z==0)
                            BT101.setDisable(false);
                        break;
                    case 2:

                        //selectflag0
                        if(z==2)
                            BT102.setText(a);
                        else if(z==1)
                            BT102.setDisable(true);
                        else if(z==0)
                            BT102.setDisable(false);
                        break;
                    case 3:

                        //selectflag0
                        if(z==2)
                            BT103.setText(a);
                        else if(z==1)
                            BT103.setDisable(true);
                        else if(z==0)
                            BT103.setDisable(false);
                        break;
                    case 4:

                        //selectflag0
                        if(z==2)
                            BT104.setText(a);
                        else if(z==1)
                            BT104.setDisable(true);
                        else if(z==0)
                            BT104.setDisable(false);
                        break;
                    case 5:

                        //selectflag0
                        if(z==2)
                            BT105.setText(a);
                        else if(z==1)
                            BT105.setDisable(true);
                        else if(z==0)
                            BT105.setDisable(false);
                        break;
                    case 6:

                        //selectflag0
                        if(z==2)
                            BT106.setText(a);
                        else if(z==1)
                            BT106.setDisable(true);
                        else if(z==0)
                            BT106.setDisable(false);
                        break;
                    case 7:

                        //selectflag0
                        if(z==2)
                            BT107.setText(a);
                        else if(z==1)
                            BT107.setDisable(true);
                        else if(z==0)
                            BT107.setDisable(false);
                        break;
                    case 8:

                        //selectflag0
                        if(z==2)
                            BT108.setText(a);
                        else if(z==1)
                            BT108.setDisable(true);
                        else if(z==0)
                            BT108.setDisable(false);
                        break;
                    case 9:

                        //selectflag0
                        if(z==2)
                            BT109.setText(a);
                        else if(z==1)
                            BT109.setDisable(true);
                        else if(z==0)
                            BT109.setDisable(false);
                        break;
                    case 10:
                        //selectflag0
                        if(z==2)
                            BT1010.setText(a);
                        else if(z==1)
                            BT1010.setDisable(true);
                        else if(z==0)
                            BT1010.setDisable(false);

                        break;
                    case 11:
                        //selectflag0
                        if(z==2)
                            BT1011.setText(a);
                        else if(z==1)
                            BT1011.setDisable(true);
                        else if(z==0)
                            BT1011.setDisable(false);

                        break;
                    case 12:
                        //selectflag0
                        if(z==2)
                            BT1012.setText(a);
                        else if(z==1)
                            BT1012.setDisable(true);
                        else if(z==0)
                            BT1012.setDisable(false);

                        break;
                }
                break;
            default:
                break;
        }
    }
}
