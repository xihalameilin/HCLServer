package com.example.demo.daoImpl;

import com.example.demo.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDaoImpl implements BaseDao{
    public void insert(Object object){
        Session session = HibernateUtils.getSession();
        session.save(object);
        HibernateUtils.closeSession(session);
    }

    public void update(Object object){
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        HibernateUtils.closeSession(session);
    }
}
