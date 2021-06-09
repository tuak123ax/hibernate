package DAO;

import hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import utils.hibernateUtil;

import java.util.List;

public class sessionDAO {
    public static List<Session> getAllsessions()
    {
        org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
        List<Session>sessions=null;
        try{
            final String hql="select st from Session st";
            Query query= session.createQuery(hql);
            sessions=query.list();
        }
        catch(HibernateException e)
        {
            System.err.println(e);
        }
        finally
        {
            session.close();
        }
        return sessions;
    }
}
