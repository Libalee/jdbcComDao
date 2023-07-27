package model.dao.impl;

import db.Db;
import db.excepetion.DbExcepetion;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("INSERT INTO department (name) VAlues (?)");
            preparedStatement.setString(1, department.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbExcepetion(e.getMessage());
        } finally {
            Db.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public void uptade(Department department) {
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("UPDATE department " +
                    "SET name = ? " +
                    "WHERE id = ?;");
            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Db.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("DELETE FROM department " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Db.closePrepareStatement(preparedStatement);
        }

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM department " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return new Department(resultSet.getInt("id"), resultSet.getString("name"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbExcepetion(e.getMessage());
        } finally {
            Db.closePrepareStatement(preparedStatement);
            Db.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM department");
            resultSet = preparedStatement.executeQuery();
            List<Department> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Department(resultSet.getInt("id"), resultSet.getString("name")));
            }
            return list;
        } catch (SQLException e) {
            throw new DbExcepetion(e.getMessage());
        } finally {
            Db.closeResultSet(resultSet);
            Db.closePrepareStatement(preparedStatement);
        }
    }
}
