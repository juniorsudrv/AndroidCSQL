package com.conexao.csql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TesteMysql {



    public TesteMysql()throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException{

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        conn =
                DriverManager.getConnection("jdbc:mysql://remotemysql.com/UDC8K1EOBQ?" +
                        "user=UDC8K1EOBQ&password=aAlkOYMgqG");
        conn.close();

    }


}
