package metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ConversationImpl;
import dao.IConversationDao;
import dao.MessageImpl;
import dao.UtilisateurConversationImpl;
import dao.UtilisateurImpl;
import util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		//public Conversation(String description, List<Message> messages, List<UtilisateurConversation> utilisateurConversations)
		ConversationImpl ci=new ConversationImpl();
		//UtilisateurImpl ui=new UtilisateurImpl();
		//UtilisateurConversationImpl uci=new UtilisateurConversationImpl();
		HibernateUtil.getSessionFactory();	
		
		/*Conversation conversation = new Conversation("C1",null,null);
		ci.addConversation(conversation);
		
		Utilisateur u1= new Utilisateur("mehdi","123456","ben","admin",new Date(),null,null);
		Utilisateur u2= new Utilisateur("amine","123456","ben1","admin",new Date(),null,null);
		Utilisateur u3= new Utilisateur("ismail","123456","ben2","admin",new Date(),null,null);
		Utilisateur u4= new Utilisateur("ayoub","123456","ben3","admin",new Date(),null,null);
		Utilisateur u5= new Utilisateur("othmane","123456","ben4","admin",new Date(),null,null);
		Utilisateur u6= new Utilisateur("hassan","123456","ben5","admin",new Date(),null,null);
		Utilisateur u7= new Utilisateur("abdrahim","123456","ben6","admin",new Date(),null,null);
		ui.addUtilisateur(u1);
		ui.addUtilisateur(u2);
		ui.addUtilisateur(u3);
		ui.addUtilisateur(u4);
		ui.addUtilisateur(u5);
		ui.addUtilisateur(u6);
		ui.addUtilisateur(u7);*/
		
		/*uci.ajouterUtilisateursAConversation(1L, 2L);
		uci.ajouterUtilisateursAConversation(1L, 3L);
		uci.ajouterUtilisateursAConversation(1L, 4L);
		uci.ajouterUtilisateursAConversation(1L, 5L);
		uci.ajouterUtilisateursAConversation(1L, 6L);
		uci.ajouterUtilisateursAConversation(1L, 7L);*/
		//UtilisateurConversation uc=uci.getUtilisateurConversationByConversationAndUtilisateur(1L, 2L);
		/*System.out.println(uc.getId());
		System.out.println(uc.isBloque());
		System.out.println(uc.getConversation().getDescription());
		System.out.println(uc.getUtilisateur().getNom());*/
		
		
		
		
       
		
		
	}

}
