import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;

public class Program2 {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

//        departmentDao.insert(new Department(5, "Food"));

        List<Department> list = departmentDao.findAll();

//        departmentDao.uptade(new Department(5, "Food"));
//        departmentDao.uptade(new Department(6, "Packages"));

        departmentDao.deleteById(8);

        for(Department department: list) {
            System.out.println(department);
        }

        Department department = departmentDao.findById(3);

        System.out.println(department);
    }
}
