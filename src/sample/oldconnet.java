package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class oldconnet {

}
    /*public  void connet()
    {

        PreparedStatement statement = null;
        try{
            Connection connection = DriverManager.getConnection(mysqlstring);
            String sql = "update connet set wx=?,wy=?,whp=?,enwhp=? where playerid=? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, mwx);
            statement.setInt(2, mwy);
            statement.setInt(3, mwar.hp);
            statement.setInt(4, ewar.hp);
            statement.setInt(5, dpc);
            statement.executeUpdate();
        }catch (SQLException ee)
        {
            ee.printStackTrace();
        }
    }
    public void ini()
    {

        Connection connection = null;
       PreparedStatement statement = null;
        try {
           connection = DriverManager.getConnection(mysqlstring);
            System.out.println("Connected");

            String sql = "Select wx,wy,playerid,enwhp,whp From connet ";


            statement = connection.prepareStatement(sql);
           // statement.setString(1,"1");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                dpc+=Integer.parseInt(resultSet.getString("playerid"));
                System.out.println(resultSet.getString("playerid"));
            }
        }catch (SQLException ex)
        {
            System.out.println("Cannot connect");
            ex.printStackTrace();
        }
    }
     public  void getid()
    {

        try {

            Connection connection = DriverManager.getConnection(mysqlstring);
            String sql = "insert into connet (wx,wy,playerid,enwhp,whp) values ('"+
                    Integer.toString(mwx) + "','" +
                    Integer.toString(mwy) + "','" +
                    Integer.toString(dpc) + "','" +
                    Integer.toString(ewar.hp) + "','" +
                    Integer.toString(mwar.hp) +
                    "');";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException ee)
        {
                System.out.println("ss");
                ee.printStackTrace();
        }

    }*/