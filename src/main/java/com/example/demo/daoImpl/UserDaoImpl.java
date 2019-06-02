package com.example.demo.daoImpl;

import com.example.demo.entity.User;
import com.example.demo.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {



    @Override
    public User getUserForLogin(String username, String password) {
        Session session = HibernateUtils.getSession();
        Query<User> query = session.createQuery(" from User u where u.name= :n and u.password = :p");
        query.setParameter("n",username) ;
        query.setParameter("p",password);
        List<User> users = query.list();
        HibernateUtils.closeSession(session);
        if(users.size()>0)
            return users.get(0);
        else
            return null;
    }


    @Override
    public List<User> getAllUsers() {
        Session session = HibernateUtils.getSession();
        Query<User> query = session.createQuery("from User ");
        List<User> users = query.getResultList();
        HibernateUtils.closeSession(session);
        return users;
    }

    @Override
    public User getUserByID(Integer userID) {
        Session session = HibernateUtils.getSession();
        Query<User> query = session.createQuery("from User where id = :i");
        query.setParameter("i",userID);
        User user = query.list().get(0);
        HibernateUtils.closeSession(session);
        return user;
    }


    @Override
    public void addPoint(Integer userID, double point) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Query<User> query = session.createQuery("update User set point = point+ :p where id = :i");
        query.setParameter("p",point);
        query.setParameter("i",userID);
        query.executeUpdate();
        session.getTransaction().commit();
        HibernateUtils.closeSession(session);
    }

    @Override
    public void changeBalance(Integer userID, double total) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Query<User> query = session.createQuery("update User set total = total - :t where id = :i");
        query.setParameter("t",total);
        query.setParameter("i",userID);
        query.executeUpdate();
        HibernateUtils.closeSession(session);
    }

    @Override
    public int getCount() {
        Session session = HibernateUtils.getSession();
        Query query = session.createQuery("select count(*) from User ");
        int result=((Long) query.uniqueResult()).intValue();
        HibernateUtils.closeSession(session);
        return result;
    }
}
