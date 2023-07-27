import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJdbc;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(10);

//        sellerDao.deleteById(11);
//        sellerDao.deleteById(12);

        System.out.println(seller);

//        sellerDao.insert(new Seller(null, "John Doe", "john@gmail.com", 3000.0,
//                new Department(1, "Computers")));

        sellerDao.uptade(new Seller(13, "Carla Doe", "carla@gmail.com", 3000.0,
                    new Department(1, "Computers")));

        List<Seller> list = sellerDao.findAll();

        for(Seller seller1 : list) {
            System.out.println(seller1);
        }
    }
}
