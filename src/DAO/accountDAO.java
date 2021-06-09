package DAO;


import hibernate.Account;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.hibernateUtil;

import java.util.List;

public class accountDAO {
    public static List<Account> getAllaccounts()
    {
        Session session=hibernateUtil.getSessionFactory().openSession();
        List<Account>accounts=null;
        try{
            final String hql="select st from Account st";
            Query query= session.createQuery(hql);
            accounts=query.list();
        }
        catch(HibernateException e)
        {
            System.err.println(e);
        }
        finally
        {
            session.close();
        }
        return accounts;
    }
}
