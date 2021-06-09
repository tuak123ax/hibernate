package DAO;


import hibernate.Course;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.hibernateUtil;

import java.util.List;

public class courseDAO {
    public static List<Course> getAllcourses()
    {
        Session session=hibernateUtil.getSessionFactory().openSession();
        List<Course>courses=null;
        try{
            final String hql="select st from Course st";
            Query query= session.createQuery(hql);
            courses=query.list();
        }
        catch(HibernateException e)
        {
            System.err.println(e);
        }
        finally
        {
            session.close();
        }
        return courses;
    }
}
