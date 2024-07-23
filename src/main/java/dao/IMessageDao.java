package dao;
import metier.Message;
import metier.Utilisateur;

public interface IMessageDao {
	public Message addMessage(Message m);
	public void deletMessage(Message m);
	public void updateMessage(Message m);
	public Message getMessage(Long idm);
}
