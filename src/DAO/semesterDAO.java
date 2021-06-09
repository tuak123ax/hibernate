package DAO;


import hibernate.Semester;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.hibernateUtil;

import java.util.List;

public class semesterDAO {
    public static List<Semester> getAllsemesters()
    {
        Session session=hibernateUtil.getSessionFactory().openSession();
        List<Semester>semesters=null;
        try{
            final String hql="select st from Semester st";
            Query query= session.createQuery(hql);
            semesters=query.list();
        }
        catch(HibernateException e)
        {
            System.err.println(e);
        }
        finally
        {
            session.close();
        }
        return semesters;
    }
}
