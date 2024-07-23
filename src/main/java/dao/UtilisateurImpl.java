package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import metier.Conversation;
import metier.Message;
import metier.Utilisateur;
import util.HibernateUtil;

public class UtilisateurImpl implements IUtilisateurdao{

	@Override
	public Utilisateur addUtilisateur(Utilisateur u) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        try {
	        	session.beginTransaction();
	            session.save(u);
	            session.getTransaction().commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Gérer l'exception ou la remonter
	        }
	        return u;
	}

	@Override
	public void DeletUtilisateur(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUtilisateur(Utilisateur u) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
        	session.beginTransaction();
            session.merge(u);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'exception ou la remonter
        }
		
	}

	@Override
	public Utilisateur getUtilisateur(Long id) {
		Utilisateur u = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
       
        try {
        	session.beginTransaction();
            u = session.find(Utilisateur.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
         
        }
        return u;
	}

	@Override
	public List<Utilisateur> ListPersonnes() {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        List<Utilisateur> utilisateurs = null;
	       
	        try {
	        	session.beginTransaction();
	            Query query = session.createQuery("Select u from Utilisateur u");
	            utilisateurs = query.getResultList();
	            session.getTransaction().commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	        return utilisateurs;
	}

	//@Override
	/*public void deleteUtilisateur(Long idUtilisateur) {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		    try {
		        session.beginTransaction();
		        
		        Utilisateur u = session.get(Utilisateur.class, idUtilisateur);

		        if (u != null) {
		            List<Conversation> conversations = u.getConversations();
		            
		            conversations.forEach(e -> {
		                e.getUtilisateurs().remove(u);
		                session.update(e);
		            });

		            session.delete(u);
		        } else {
		            throw new RuntimeException("Can not delete Personne with id: " + idUtilisateur);
		        }

		        session.getTransaction().commit();
		    } catch (Exception e) {
		        if (session.getTransaction() != null) {
		            session.getTransaction().rollback();
		        }
		        System.out.println(e.getMessage());
		    } finally {
		        // Make sure to close the session to release resources
		        if (session != null && session.isOpen()) {
		            session.close();
		        }
		    }
		
	}

	@Override
	public List<Message> listemessages(Long idu) {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        List<Message> messages = new ArrayList<>();

	        try {
	            session.beginTransaction();
	            Query query = session.createQuery("SELECT u.messages FROM Utilisateur u WHERE u.idUtilisateur = :utilisateurId");
	            query.setParameter("utilisateurId", idu);
	            messages =  query.getResultList();

	            session.getTransaction().commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return messages;
	}*/

	@Override
	
		public List<Conversation> listeConversation(Long idu) {
		    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		    List<Conversation> conversations = new ArrayList<>();

		    try {
		        session.beginTransaction();
		        // Sélectionnez les conversations de l'utilisateur avec l'information de blocage
		        Query query = session.createQuery(
		                "SELECT c FROM Utilisateur u " +
		                        "JOIN u.utilisateurConversations uc " +
		                        "JOIN uc.conversation c " +
		                        "WHERE u.idUtilisateur = :utilisateurId");
		        query.setParameter("utilisateurId", idu);
		        conversations = query.getResultList();

		        session.getTransaction().commit();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return conversations;
		}

	@Override
	public void deleteUtilisateur(Long pidPersonne) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Message> listemessages(Long idu) {
		// TODO Auto-generated method stub
		return null;
	}


		


}
