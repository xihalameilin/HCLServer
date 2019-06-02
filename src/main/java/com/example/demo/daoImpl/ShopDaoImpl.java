package com.example.demo.daoImpl;

import com.example.demo.entity.Shop;
import com.example.demo.dao.ShopDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ShopDaoImpl extends BaseDaoImpl implements ShopDao {
    @Override
    public List<Shop> getAllShops() {
        Session session = HibernateUtils.getSession();
        Query<Shop> query = session.createQuery("from Shop");
        List<Shop> shops = query.list();
        HibernateUtils.closeSession(session);
        return shops;
    }

//    @Override
//    public void insertShop(Shop shop) {
//        Session session = HibernateUtils.getSession();
//        session.save(shop);
//        HibernateUtils.closeSession(session);
//    }

    @Override
    public Shop getShopByShopID(Integer shopID) {
        Session session = HibernateUtils.getSession();
        Query<Shop> query = session.createQuery("select Shop from Shop where shopID =:n ");
        query.setParameter("n",shopID);
        List<Shop> shops = query.getResultList();
        HibernateUtils.closeSession(session);
        return shops.get(0);
    }





    @Override
    public int getCount() {
        Session session = HibernateUtils.getSession();
        Query query = session.createQuery("select count(*) from Shop ");
        int result=((Long) query.uniqueResult()).intValue();
        HibernateUtils.closeSession(session);
        return result;
    }

    @Override
    public double getTotal() {
        Session session = HibernateUtils.getSession();
        Query query = session.createQuery("select sum(total) from Order where state = 1");
        double result=((double) query.uniqueResult());
        HibernateUtils.closeSession(session);
        return result;
    }
}
