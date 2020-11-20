package datafileTablas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLconnection {


    private String getOperatorUserName(String batchID) throws Exception {

        // Importante para entornos de tomcat
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        String ip = null;

        switch (System.getenv("SYNERENV")) {
        case "prod":
            ip = "172.0.0.18";
            break;
        case "dev":
        case "danvm":
            ip = "localhost";
            break;
        case "danlinux":
            ip = "192.168.0.100";
            break;

        default:
            break;
        }

        Connection connection = DriverManager.getConnection(
                "jdbc:sqlserver://" + ip + ":1433;databaseName=Synergetics;user=Synergetics;password=Password##1;");

        String sqlQuery = "select validation_operator_user_name from batch_instance where identifier = '" + batchID
                + "'";

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sqlQuery);

        // it will return synergetics if no user worked on it
        String user = rs.next() && rs.getString(1) != null ? rs.getString(1) : "synergetics";

        connection.close();

        return user;
    }
    
}
