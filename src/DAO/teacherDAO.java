package DAO;


import hibernate.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.hibernateUtil;

import java.util.List;

public class teacherDAO {
    public static List<Teacher> getAllteachers()
    {
        Session session=hibernateUtil.getSessionFactory().openSession();
        List<Teacher>teachers=null;
        try{
            final String hql="select st from Teacher st";
            Query query= session.createQuery(hql);
            teachers=query.list();
        }
        catch(HibernateException e)
        {
            System.err.println(e);
        }
        finally
        {
            session.close();
        }
        return teachers;
    }
}
