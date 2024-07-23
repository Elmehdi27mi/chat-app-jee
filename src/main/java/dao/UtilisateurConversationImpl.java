package dao;

import org.hibernate.Session;

import metier.Conversation;
import metier.Utilisateur;
import metier.UtilisateurConversation;
import util.HibernateUtil;

public class UtilisateurConversationImpl implements IUtilisateurConversationDao {

	@Override
	public UtilisateurConversation addUtilsateurConversation(UtilisateurConversation uc) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
        	session.beginTransaction();
            session.save(uc);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uc;
	}
	@Override
	public void ajouterUtilisateursAConversation(Long idConversation, Long idUtilisateur) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

	    try {
	        session.beginTransaction();

	        // Récupérer l'entité de la conversation
	        Conversation conversation = session.find(Conversation.class, idConversation);

	        // Récupérer les entités des utilisateurs
	        Utilisateur utilisateur = session.find(Utilisateur.class, idUtilisateur);
	     

	        // Créer une nouvelle instance de UtilisateurConversation
	        UtilisateurConversation uc1 = new UtilisateurConversation(utilisateur, conversation, false);
	      

	        // Ajouter les utilisateurs à la liste des utilisateurs de la conversation
	        conversation.getUtilisateurConversations().add(uc1);


	        // Mettre à jour les deux côtés de la relation bidirectionnelle
	        utilisateur.getUtilisateurConversations().add(uc1);
	        

	        // Mettre à jour la conversation
	        session.merge(conversation);
	        
	        session.persist(uc1);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        if (session.getTransaction() != null && session.getTransaction().isActive()) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        // Gérer l'exception ou la remonter
	    }
	}
	
	
	
	@Override
	public UtilisateurConversation getUtilisateurConversationByConversationAndUtilisateur(Long idConversation, Long idUtilisateur) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

	    try {
	        session.beginTransaction();

	        // Requête HQL pour récupérer l'UtilisateurConversation
	        String hql = "SELECT uc FROM UtilisateurConversation uc " +
	                     "WHERE uc.utilisateur.idUtilisateur = :idUtilisateur " +
	                     "AND uc.conversation.idConversation = :idConversation";

	        UtilisateurConversation utilisateurConversation = session.createQuery(hql, UtilisateurConversation.class)
	                .setParameter("idUtilisateur", idUtilisateur)
	                .setParameter("idConversation", idConversation)
	                .uniqueResult();

	        session.getTransaction().commit();

	        return utilisateurConversation;
	    } catch (Exception e) {
	        if (session.getTransaction() != null && session.getTransaction().isActive()) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        // Gérer l'exception ou la remonter
	        return null;
	    }
	}
	@Override
	public void updateUtilisateurConversation(UtilisateurConversation uc) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
        	session.beginTransaction();
            session.merge(uc);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
           
        }
		
	}

	
	
	
	
	
	
	
	


}
