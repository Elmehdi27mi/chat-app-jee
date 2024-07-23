package dao;

import metier.Conversation;
import metier.UtilisateurConversation;

public interface IUtilisateurConversationDao {
	public UtilisateurConversation addUtilsateurConversation(UtilisateurConversation uc);
	public void updateUtilisateurConversation(UtilisateurConversation uc);
	public void ajouterUtilisateursAConversation(Long idConversation, Long idUtilisateur);
	public UtilisateurConversation getUtilisateurConversationByConversationAndUtilisateur(Long idConversation, Long idUtilisateur) ;
}
