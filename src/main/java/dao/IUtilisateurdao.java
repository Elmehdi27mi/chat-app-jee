package dao;

import java.util.List;

import metier.Conversation;
import metier.Message;
import metier.Utilisateur;

public interface IUtilisateurdao {
	public Utilisateur addUtilisateur(Utilisateur u);
	public void DeletUtilisateur(Long id);
	public void updateUtilisateur(Utilisateur u);
	public Utilisateur getUtilisateur(Long id);
	public  List<Utilisateur> ListPersonnes();
	void deleteUtilisateur(Long pidPersonne);
	public List<Message>listemessages(Long idu);
	public List<Conversation>listeConversation(Long idu);
	//public void ajouterPersonneaReunion(Long id, Long idr);
	

}
