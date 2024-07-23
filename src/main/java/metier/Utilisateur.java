package metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity @Table(name="UTILISATEUR")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    private String nom;
    private String motDePasse;
    private String prenom;
    private String role;
    private Date dateDeNaissance;
    private String etat;
  

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_CONNECTION_TIME")
    private Date lastConnectionTime;
    @OneToMany(mappedBy = "utilisateur")
    private List<Message> messages;

    public Date getLastConnectionTime() {
		return lastConnectionTime;
	}

	public void setLastConnectionTime(Date lastConnectionTime) {
		this.lastConnectionTime = lastConnectionTime;
	}

	@OneToMany(mappedBy = "utilisateur")
    @Fetch(FetchMode.JOIN)
    private List<UtilisateurConversation> utilisateurConversations ;

	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<UtilisateurConversation> getUtilisateurConversations() {
		return utilisateurConversations;
	}

	public void setUtilisateurConversations(List<UtilisateurConversation> utilisateurConversations) {
		this.utilisateurConversations = utilisateurConversations;
	}

	

	public Utilisateur(String nom, String motDePasse, String prenom,String role, Date dateDeNaissance, List<Message> messages,
			List<UtilisateurConversation> utilisateurConversations) {
		super();
		this.nom = nom;
		this.motDePasse = motDePasse;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.messages = messages;
		this.utilisateurConversations = utilisateurConversations;
		this.role=role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Utilisateur() {
	}
}
