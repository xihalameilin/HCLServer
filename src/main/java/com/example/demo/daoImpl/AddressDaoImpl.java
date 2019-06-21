package com.example.demo.daoImpl;

import com.example.demo.entity.Address;
import com.example.demo.dao.AddressDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AddressDaoImpl extends BaseDaoImpl implements AddressDao {
    @Override
    public List<Address> getAllAddressesByUserID(Integer userID) {
        Session session = HibernateUtils.getSession();
        Query<Address> query = session.createQuery("from Address a where a.user.id = :n");
        query.setParameter("n",userID);
        List<Address> addresses = query.list();
        HibernateUtils.closeSession(session);
        return addresses;
    }


    @Override
    public void setDefaultAddress(Integer userID, Integer addressID) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Query<Address> query = session.createQuery("UPDATE Address a SET state = 0 WHERE a.user.id = :u AND a.state = :s");
        query.setParameter("u",userID);
        query.setParameter("s",1);
        query.executeUpdate();
        query = session.createQuery("UPDATE Address a SET state = 1 WHERE a.user.id = :u AND id = :i");
        query.setParameter("u",userID);
        query.setParameter("i",addressID);
        query.executeUpdate();
        transaction.commit();
        HibernateUtils.closeSession(session);

    }

    @Override
    public void delete(Integer addressID) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Query<Address> query = session.createQuery("DELETE from Address where id = :i");
        query.setParameter("i",addressID);
        query.executeUpdate();
        transaction.commit();
        HibernateUtils.closeSession(session);
    }
}
