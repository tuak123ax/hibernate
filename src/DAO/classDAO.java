package DAO;


import hibernate.Clazz;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.hibernateUtil;

import java.util.List;

public class classDAO {
    public static List<Clazz> getAllclasses()
    {
        Session session=hibernateUtil.getSessionFactory().openSession();
        List<Clazz>clazzes=null;
        try{
            final String hql="select st from Clazz st";
            Query query= session.createQuery(hql);
            clazzes=query.list();
        }
        catch(HibernateException e)
        {
            System.err.println(e);
        }
        finally
        {
            session.close();
        }
        return clazzes;
    }
}
