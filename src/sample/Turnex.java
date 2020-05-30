package sample;

import java.sql.*;

public class Turnex extends Thread{
    public boolean enwtrun;
    public int dpc;
    private String mysqlstring = "jdbc:mysql://localhost:80/GAME?user=root&password=123qwe" +
            "&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
    public void run()
    {
        try {
            while (enwtrun==true ) {
                Thread.sleep(500);
                turndata(2, false);
            }
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        while (enwtrun == false) {
            turndata(2, false);
        }//////QUTION

    }
    public void turndata(int a,boolean b)
    {
        PreparedStatement statement = null;
        String sql;
        try {
            Connection connection = DriverManager.getConnection(mysqlstring);
            switch (a) {
                case 1:
                    sql = "update connet set turnflag=? where playerid=? ";
                    statement = connection.prepareStatement(sql);
                    statement.setBoolean(1,b);
                    statement.setInt(2,dpc);
                    statement.executeUpdate();
                    break;
                case 2:
                    sql = "Select turnflag From connet where playerid=? ";

                    statement = connection.prepareStatement(sql);
                    // statement.setString(1,"1");

                    if(dpc==1)
                        statement.setInt(1,2);
                    else
                        statement.setInt(1,1);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next())
                    {
                        enwtrun=Boolean.parseBoolean(resultSet.getString("turnflag"));
                        //System.out.println(resultSet.getString("playerid"));
                    }
                    connection.close();
                    break;
            }
        }catch (SQLException ee)
        {
            System.out.println("test");
            ee.printStackTrace();
        }

    }
}
