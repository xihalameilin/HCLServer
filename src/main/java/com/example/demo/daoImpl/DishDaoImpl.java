package com.example.demo.daoImpl;

import com.example.demo.entity.Dish;
import com.example.demo.dao.DishDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DishDaoImpl implements DishDao {
    @Override
    public List<Dish> getAllDishByShopID(Integer shopID) {
        Session session = HibernateUtils.getSession();
        Query<Dish> query = session.createQuery(" from Dish d where d.shop.shopID = :s");
        query.setParameter("s",shopID);
        List<Dish> list = query.getResultList();
        HibernateUtils.closeSession(session);
        return list;
    }
}
