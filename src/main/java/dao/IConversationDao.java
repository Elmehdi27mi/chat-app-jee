package dao;

import java.util.List;

import metier.Conversation;
import metier.Message;
import metier.Utilisateur;

public interface IConversationDao {
	public Conversation addConversation(Conversation c);
	public void DeletConversation(Long idc);
	public void updateConversation(Conversation c);
	public Conversation getConversation(Long idc);
	public  List<Conversation> ListConversation();
	void deleteConversation(Long idConversation);
	//public List<Message>listemessages(Long idc);
	public List<Utilisateur>listeUtilisateurs(Long idc);
	public List<Message> getMessagesBetweenUsersInConversation(Long utilisateurId1, Long utilisateurId2, Long conversationId);
	public List<Message> getMessagesBetweenSpecificUsersInConversation(Long conversationId, List<Long> userIds);

}
