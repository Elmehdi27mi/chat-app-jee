package dao;

import org.hibernate.Session;

import metier.Message;
import metier.Utilisateur;
import util.HibernateUtil;

public class MessageImpl implements IMessageDao {

	@Override
	public Message addMessage(Message m) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
        	session.beginTransaction();
            session.save(m);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'exception ou la remonter
        }
        return m;
	}

	@Override
	public void deletMessage(Message m) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
        	session.beginTransaction();
            session.remove(m);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'exception ou la remonter
        }
	}

	@Override
	public void updateMessage(Message m) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
        	session.beginTransaction();
            session.merge(m);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'exception ou la remonter
        }		
	}

	@Override
	public Message getMessage(Long idm) {
		Message m = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
       
        try {
        	session.beginTransaction();
            m = session.find(Message.class, idm);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
         
        }
        return m;
	}

}
