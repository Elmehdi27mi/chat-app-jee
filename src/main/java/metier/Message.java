package metier;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="MESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessage;
  
    private String contenu;
    private Date datEnvoi;
    private boolean supprime =false;
    public boolean isSupprime() {
		return supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDatEnvoi() {
		return datEnvoi;
	}

	public void setDatEnvoi(Date datEnvoi) {
		this.datEnvoi = datEnvoi;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Message( String contenu, Date datEnvoi, Utilisateur utilisateur,
			Conversation conversation) {
		
		this.contenu = contenu;
		this.datEnvoi = datEnvoi;
		this.utilisateur = utilisateur;
		this.conversation = conversation;
	}
	@ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;
    public Message() {
	}

	public Long getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Conversation getSalleDeChat() {
		return conversation;
	}
	public void setSalleDeChat(Conversation salleDeChat) {
		this.conversation = salleDeChat;
	}
	@ManyToOne
    @JoinColumn(name = "idConversation")
    private Conversation conversation;
}
