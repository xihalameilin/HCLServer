package com.example.demo.daoImpl;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.dao.OrderDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OrderDaoImpl implements OrderDao {

    @Override
    public Integer insertOrder(Order order) {
        Session session = HibernateUtils.getSession();
        session.save(order);
        HibernateUtils.closeSession(session);
        return order.getId();
    }

    @Override
    public void changeState(Integer orderID, Integer state) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Query<Order> query = session.createQuery("UPDATE Order SET state = :s where id = :i");
        query.setParameter("s",state);
        query.setParameter("i",orderID);
        query.executeUpdate();
        session.getTransaction().commit();
        HibernateUtils.closeSession(session);

    }

    @Override
    public List<Order> getAllOrdersByState(Integer userID, Integer state) {
        Session session = HibernateUtils.getSession();
        Query<Order> query = session.createQuery("from Order o where o.userID = :i and o.state= :s");
        query.setParameter("i",userID);
        query.setParameter("s",state);
        List<Order> orders = query.getResultList();
        HibernateUtils.closeSession(session);
        return orders;
    }

    @Override
    public List<OrderItem> getAllOrderItems(Integer ID) {
        Session session = HibernateUtils.getSession();
        Query<OrderItem> query = session.createQuery("from OrderItem where order.id = :i");
        query.setParameter("i",ID);
        List<OrderItem> orderItems = query.getResultList();
        HibernateUtils.closeSession(session);
        return orderItems;
    }

    @Override
    public Order getOrderByOrderID(Integer orderID) {
        Session session = HibernateUtils.getSession();
        Query<Order> query = session.createQuery("from Order where id=:i");
        query.setParameter("i",orderID);
        List<Order> orders = query.getResultList();
        return orders.get(0);
    }

    @Override
    public List<Order> getAllOrdersByShopID(Integer shopID) {
        Session session = HibernateUtils.getSession();
        Query<Order> query = session.createQuery("from Order where shopID = :s and state = 2");
        query.setParameter("s",shopID);
        List<Order> orders = query.getResultList();
        HibernateUtils.closeSession(session);
        return orders;
    }
}
