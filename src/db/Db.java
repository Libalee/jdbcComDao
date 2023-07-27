package db;

import db.excepetion.DbExcepetion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Db {

    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null) {
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e) {
                throw new DbExcepetion(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DbExcepetion(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Libale\\IdeaProjects" +
                "\\jdbcComDao\\.idea\\db.properties"))) {
            Properties properties = new Properties();

            properties.load(bufferedReader);
            return properties;

        } catch (FileNotFoundException e) {
            throw new DbExcepetion(e.getMessage());
        } catch (IOException e) {
            throw new DbExcepetion(e.getMessage());
        }
    }

    public static void closeStatement(Statement statement) {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbExcepetion(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DbExcepetion(e.getMessage());
            }
        }
    }

    public static void closePrepareStatement(PreparedStatement preparedStatement) {
        if(preparedStatement != null) {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DbExcepetion(e.getMessage());
            }
        }
    }

}
