package metier;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity @Table(name="CONVERSATION")
public class Conversation {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idConversation;
	 	private String description;
	    public Conversation(String description, List<Message> messages, List<UtilisateurConversation> utilisateurConversations) {
			super();
			this.description = description;
			this.messages = messages;
			this.utilisateurConversations = utilisateurConversations;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@OneToMany(mappedBy = "conversation")
	    private List<Message> messages;
		@OneToMany(mappedBy = "conversation")
	    private List<UtilisateurConversation> utilisateurConversations;

		public Long getIdSalleDeChat() {
			return idConversation;
		}

		public void setIdSalleDeChat(Long idSalleDeChat) {
			this.idConversation = idSalleDeChat;
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

	

		public Long getIdConversation() {
			return idConversation;
		}

		public void setIdConversation(Long idConversation) {
			this.idConversation = idConversation;
		}

		public Conversation() {
			
		}

	    

}
