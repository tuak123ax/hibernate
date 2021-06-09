package DAO;


import hibernate.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.hibernateUtil;

import java.util.List;

public class studentDAO {
    public static List<Student> getAllstudents()
    {
        Session session=hibernateUtil.getSessionFactory().openSession();
        List<Student>students=null;
        try{
            final String hql="select st from Student st";
            Query query= session.createQuery(hql);
            students=query.list();
        }
        catch(HibernateException e)
        {
            System.err.println(e);
        }
        finally
        {
            session.close();
        }
        return students;
    }
}
