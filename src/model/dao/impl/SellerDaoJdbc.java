package model.dao.impl;

import db.Db;
import db.excepetion.DbExcepetion;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJdbc implements SellerDao {

    private Connection connection;

    public SellerDaoJdbc(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insert(Seller seller) {
        PreparedStatement preparedStatement = null;

        try {
           preparedStatement = connection.prepareStatement("INSERT INTO seller " +
                   "(name, email, basesalary, department_ID) values " +
                   "(?, ?, ?, ?);");
           preparedStatement.setString(1, seller.getName());
           preparedStatement.setString(2, seller.getEmail());
           preparedStatement.setDouble(3, seller.getBasesalary());
           preparedStatement.setInt(4, seller.getDepartment().getId());
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbExcepetion(e.getMessage());
        } finally {
            Db.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public void uptade(Seller seller) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(" UPDATE seller " +
                    "SET name = ?, email = ?, basesalary = ?, department_ID = ? " +
                    "WHERE id = ?;");
            preparedStatement.setString(1, seller.getName());
            preparedStatement.setString(2, seller.getEmail());
            preparedStatement.setDouble(3, seller.getBasesalary());
            preparedStatement.setInt(4, seller.getDepartment().getId());
            preparedStatement.setInt(5,seller.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbExcepetion(e.getMessage());
        } finally {
            Db.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("DELETE FROM seller WHERE id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbExcepetion(e.getMessage());
        } finally {
            Db.closePrepareStatement(preparedStatement);
        }

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM seller WHERE id = ?;");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("department_id"));
                // Não da pra iniciar o departamento com o nome pq as minhas colunas no banco de dados não estão
                // com dependencia.
                // O cara no video não explicou como fazer dependencias entre tables no mysql, então pesquisar dps
                return new Seller(resultSet.getInt("id"),
                                  resultSet.getString("name"),
                                  resultSet.getString("email"),
                                  resultSet.getDouble("basesalary"),
                                  department);
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
    public List<Seller> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM seller;");
            resultSet = preparedStatement.executeQuery();
            List<Seller> list = new ArrayList<>();
            while(resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("department_id"));
                list.add(new Seller(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getDouble("basesalary"),
                        department));
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
