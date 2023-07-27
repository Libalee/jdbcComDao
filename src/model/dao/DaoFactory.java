package model.dao;

import db.Db;
import model.dao.impl.SellerDaoJdbc;

public class DaoFactory {
    public static SellerDao createSellerDao() {
        return new SellerDaoJdbc(Db.getConnection());
    }
}
