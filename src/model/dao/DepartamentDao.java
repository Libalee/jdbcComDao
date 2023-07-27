package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartamentDao {
    void insert(Department department);
    void uptade(Department department);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();
}
