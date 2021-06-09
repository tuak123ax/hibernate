package DAO;


import hibernate.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.hibernateUtil;

import java.util.List;

public class subjectDAO {
    public static List<Subject> getAllsubjects()
    {
        Session session=hibernateUtil.getSessionFactory().openSession();
        List<Subject>subjects=null;
        try{
            final String hql="select st from Subject st";
            Query query= session.createQuery(hql);
            subjects=query.list();
        }
        catch(HibernateException e)
        {
            System.err.println(e);
        }
        finally
        {
            session.close();
        }
        return subjects;
    }
}
