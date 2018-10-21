package bstu.yuki.db.operations;

import bstu.yuki.db.models.User;

import java.sql.*;

public class dbOperations {
    private  static   String sqlConn = "jdbc:sqlite:E:/University/3k1s/Java/JSF/src/main/resources/db/db.sqlite";

    public  static User GetUser (String name, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(sqlConn);

        String sql = "SELECT * FROM card where name = ? and password = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        User user = null;

        while (rs.next())
        {
            user = new User();
            user.setName(rs.getString("name"));
            user.setInfo(rs.getString("info"));
            user.setPassword(rs.getString("password"));
            user.setMoney(rs.getInt("money"));
        }
        conn.close();
        return user;
    }

    public  static boolean  reduceMoney(String user ,int cost){
        try {
            Connection conn = DriverManager.getConnection(sqlConn);

            String sql = "update card set money = money - ? where name = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, cost);
            preparedStatement.setString(2, user);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }
}
