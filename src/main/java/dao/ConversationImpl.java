package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import metier.Conversation;
import metier.Message;
import metier.Utilisateur;
import util.HibernateUtil;

public class ConversationImpl implements IConversationDao {

	@Override
	public Conversation addConversation(Conversation c) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
        	session.beginTransaction();
            session.save(c);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
	}

	@Override
	public void DeletConversation(Long idc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateConversation(Conversation c) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
        	session.beginTransaction();
            session.merge(c);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
           
        }
		
	}

	@Override
	public Conversation getConversation(Long idc) {
		Conversation c = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
       
        try {
        	session.beginTransaction();
            c = session.find(Conversation.class, idc);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
         
        }
        return c;
	}

	@Override
	public List<Conversation> ListConversation() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Conversation> conversations = null;
       
        try {
        	session.beginTransaction();
            Query query = session.createQuery("Select c from Conversation c");
            conversations = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conversations;
	}

	@Override
	public void deleteConversation(Long idConversation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Utilisateur> listeUtilisateurs(Long idc) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    List<Utilisateur> utilisateurs = new ArrayList<>();

	    try {
	        session.beginTransaction();
	        // Sélectionnez tous les utilisateurs de la conversation
	        Query query = session.createQuery(
	                "SELECT DISTINCT u FROM Conversation c " +
	                        "JOIN c.utilisateurConversations uc " +
	                        "JOIN uc.utilisateur u " +
	                        "WHERE c.idConversation = :idConversation");
	        query.setParameter("idConversation", idc);
	        utilisateurs = query.getResultList();

	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return utilisateurs;
	}

	@Override
	public List<Message> getMessagesBetweenUsersInConversation(Long utilisateurId1, Long utilisateurId2, Long conversationId) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

	    try {
	        session.beginTransaction();

	        String hql = "FROM Message M " +
	                     "WHERE ((M.utilisateur.idUtilisateur = :utilisateurId1 AND M.conversation.idConversation = :conversationId) " +
	                     "OR (M.utilisateur.idUtilisateur = :utilisateurId2 AND M.conversation.idConversation = :conversationId)) " +
	                     "ORDER BY M.datEnvoi ASC";

	        List<Message> messages = session.createQuery(hql, Message.class)
	                .setParameter("utilisateurId1", utilisateurId1)
	                .setParameter("utilisateurId2", utilisateurId2)
	                .setParameter("conversationId", conversationId)
	                .list();

	        session.getTransaction().commit();
	        return messages;
	    } catch (Exception e) {
	        if (session.getTransaction() != null && session.getTransaction().isActive()) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        // Gérer l'exception ou la remonter
	    }
	    return Collections.emptyList();
	}

	
	
	@Override
	public List<Message> getMessagesBetweenSpecificUsersInConversation(Long conversationId, List<Long> userIds) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

	    try {
	        session.beginTransaction();

	        String hql = "SELECT M FROM Message M " +
	                "JOIN M.utilisateur U " +
	                "WHERE M.conversation.idConversation = :conversationId " +
	                "AND U.idUtilisateur IN :userIds " +
	                "ORDER BY M.datEnvoi ASC";

	        List<Message> messages = session.createQuery(hql, Message.class)
	                .setParameter("conversationId", conversationId)
	                .setParameterList("userIds", userIds)
	                .list();

	        session.getTransaction().commit();
	        return messages;
	    } catch (Exception e) {
	        if (session.getTransaction() != null && session.getTransaction().isActive()) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        // Gérer l'exception ou la remonter
	    }
	    return Collections.emptyList();
	}



	
	
	
	
	
	
	
	
	
	
	

}
