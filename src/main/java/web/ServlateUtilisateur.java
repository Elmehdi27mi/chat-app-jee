package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.ConversationImpl;
import dao.MessageImpl;
import dao.UtilisateurConversationImpl;
import dao.UtilisateurImpl;
import metier.Conversation;
import metier.Message;
import metier.Utilisateur;
import metier.UtilisateurConversation;
@WebServlet(name="cs" , urlPatterns = "*.do")
public class ServlateUtilisateur extends HttpServlet {
	UtilisateurImpl ui;
	ConversationImpl ci;
	MessageImpl mi;
	UtilisateurConversationImpl uci;
	SimpleDateFormat dateFormat ;

	@Override
	public void init() throws ServletException {
		ui=new UtilisateurImpl();
		ci=new ConversationImpl();
		mi=new MessageImpl();
		uci=new UtilisateurConversationImpl();
		 dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		if(path.equals("/*.do")) {
			req.getRequestDispatcher("AccPage.jsp").forward(req, resp);
			
		}
		else if(path.equals("/verification.do")){
			
			Long id=Long.parseLong(req.getParameter("id"));
			String motpass=req.getParameter("motpass");
			
			boolean authentificationReussie = false;

			List<Utilisateur> utilisateurs = ui.ListPersonnes();
			for (Utilisateur u : utilisateurs) {
			    if ((u.getIdUtilisateur() == id) && (u.getMotDePasse().equals(motpass))) {
			        HttpSession session = req.getSession();
			        session.setAttribute("UtilisateurId1", id);
			       
			        //req.getRequestDispatcher("conexion.do").forward(req, resp);
			        req.getRequestDispatcher("PagePrincipale.jsp").forward(req, resp);
			        authentificationReussie = true;
			        break; // Sortir de la boucle une fois l'authentification réussie
			    }
			}

			if (!authentificationReussie) {
			    req.setAttribute("mess", "L'id ou le mot de passe incorrecte !");
			    req.getRequestDispatcher("AccPage.jsp").forward(req, resp);
			}

			
		}
		else if(path.equals("/conexion.do")) {
			HttpSession session = req.getSession();
	    	 Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
			List<Conversation>conversation =ui.listeConversation(UtilisateurId1);
			req.setAttribute("model", conversation);
			//Utilisateur user =ui.getUtilisateur(UtilisateurId1);
			// Mettez à jour la date de la dernière connexion
			//user.setLastConnectionTime(new Date()) ;
			//ui.updateUtilisateur(user);
			
			
			Utilisateur user =ui.getUtilisateur(UtilisateurId1);
			// Mettez à jour la date de la dernière connexion
			user.setEtat("away") ;
			ui.updateUtilisateur(user);
			
			
			
			req.getRequestDispatcher("/utilisateur.jsp").forward(req,resp);
		}else if(path.equals("/out.do")) {
			HttpSession session = req.getSession();
	    	 Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
			
			Utilisateur user =ui.getUtilisateur(UtilisateurId1);
			// Mettez à jour la date de la dernière connexion
			user.setLastConnectionTime(new Date()) ;
			user.setEtat("offline");
			ui.updateUtilisateur(user);
			req.getRequestDispatcher("*.do").forward(req,resp);
		}
		
		else if (path.equals("/message.do")) {
		    HttpSession session = req.getSession();
		    Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
		    String conversationIdParam = req.getParameter("idc");
		    Long conversationId = null;
		    if (conversationIdParam != null && !conversationIdParam.isEmpty()) {
		    	conversationId = Long.parseLong(conversationIdParam);
		    	session.setAttribute("conversationId", conversationId);
		    	
		    	
		    	
		    	
		    	Utilisateur user =ui.getUtilisateur(UtilisateurId1);
				// Mettez à jour la date de la dernière connexion
				user.setEtat("online") ;
				ui.updateUtilisateur(user);
		    	
		    	
		    	
		    	
		    	
		    	
		    }else {
		    	 //UtilisateurId2 = Long.parseLong(utilisateurId2Param);
		    	conversationId = (Long) session.getAttribute("conversationId");
		    }
		    
		    
		   // Long conversationId = Long.parseLong(req.getParameter("idc"));
	        
	        //session.setAttribute("conversationId", conversationId);
			List<Utilisateur>utilisateurs =ci.listeUtilisateurs(conversationId);
		    List<Long>ids=new ArrayList<>();
		    for(int i=0;i<utilisateurs.size();i++) {
		    	ids.add(utilisateurs.get(i).getIdUtilisateur());
		    }


		  if (conversationId != null) {
		        List<Message> messages = ci.getMessagesBetweenSpecificUsersInConversation(conversationId, ids);
		        req.setAttribute("model", messages);
		        
		        
		    	UtilisateurConversation uc=uci.getUtilisateurConversationByConversationAndUtilisateur(conversationId, UtilisateurId1);

		    	 req.setAttribute("uc", uc);
		        

		        req.getRequestDispatcher("message.jsp").forward(req, resp);
		    } else {
		    	resp.sendRedirect("pageErreur.jsp");
		    }
				
		}

		else if(path.equals("/envoyerMessage.do")) {
			 HttpSession session = req.getSession();
	    	 Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	    Long conversationId = (Long) session.getAttribute("conversationId");
	    	 if (UtilisateurId1 != null) {
			        String m=req.getParameter("message");
			        Utilisateur u=ui.getUtilisateur(UtilisateurId1);
			        Conversation c=ci.getConversation(conversationId);
			        Message message=new Message(m, new Date(), u, c);
			        mi.addMessage(message);
			        //List<Message>messages =ci.getMessagesBetweenUsersInConversation(1L, UtilisateurId2,conversationId );
					//req.setAttribute("model", messages);
			       // req.getRequestDispatcher("message.do").forward(req, resp);
			        //resp.sendRedirect("message.do");
			        resp.sendRedirect("message.do");
			        
			    }
			    else {
			        // Rediriger l'utilisateur vers une page d'erreur ou effectuer une autre action appropriée
			        resp.sendRedirect("pageErreur.jsp");
			    }
			
		}
		else if (path.equals("/registration.do")){
			String firstname=req.getParameter("firstName");
			String lastname=req.getParameter("lastName");
			String dobStr=req.getParameter("dob");
			String password=req.getParameter("password");
			Date date = null;
			try {
			    date = dateFormat.parse(dobStr);
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			ui.addUtilisateur(new Utilisateur(firstname,  password,lastname,"regular", date, null, null));
			
			req.setAttribute("firstname", firstname);
			req.setAttribute("lastname", lastname);
			
			req.getRequestDispatcher("Home.jsp").forward(req,resp);
			
		}
		else if(path.equals("/supprimerMessageD.do")) {
			Long messageId = Long.parseLong(req.getParameter("idm"));
			
			MessageImpl mi=new MessageImpl();
			Message m=mi.getMessage(messageId);
			mi.deletMessage(m);
			resp.sendRedirect("message.do");
	    	
	    }
		else if(path.equals("/supprimerMessageR.do")) {
			Long messageId = Long.parseLong(req.getParameter("idm"));
			
			MessageImpl mi=new MessageImpl();
			Message m=mi.getMessage(messageId);
			m.setSupprime(true);
			mi.updateMessage(m);
			resp.sendRedirect("message.do");
	    	
	    }else if(path.equals("/AjouterUtilisateur.do")) {
			
	    	List<Utilisateur>utilisateurs=ui.ListPersonnes();
	    	req.setAttribute("model", utilisateurs);
	    	
	    	req.getRequestDispatcher("AjouterUtilisateur.jsp").forward(req, resp);
	    	
	    	
	    }
	    else if(path.equals("/chat.do")) {
			HttpSession session = req.getSession();

	    	 Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	Long id = Long.parseLong(req.getParameter("id"));
	    	
	    	Utilisateur u1=ui.getUtilisateur(id);
	    	Utilisateur u2=ui.getUtilisateur(UtilisateurId1);
	    	String description=u1.getNom()+u2.getNom();
	    	List<Utilisateur>us=new ArrayList<>();
	    	us.add(u1);
	    	us.add(u2);
	    	//public Conversation(String description, List<Message> messages, List<Utilisateur> utilisateurs)
	    	Conversation c=new Conversation(description,null,null);
	    	ci.addConversation(c);
	 //içi on doit récup"rer le nom et le prenom pour construire le conversation
	    	
	    	req.getRequestDispatcher("conexion.do").forward(req, resp);
	    	
	    	
	    }else if(path.equals("/group.do")) {
	    	HttpSession session=req.getSession();
	    	Long conversationId = (Long) session.getAttribute("conversationId");
	    	 Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	List<Utilisateur>utilisateurs =ci.listeUtilisateurs(conversationId);
	    	
		   Utilisateur u=ui.getUtilisateur(UtilisateurId1);
		   
		  if (conversationId != null) {
		        //List<Message> messages = ci.getMessagesBetweenSpecificUsersInConversation(conversationId, ids);
		        req.setAttribute("model", utilisateurs);
		        req.setAttribute("user", u);

		        req.getRequestDispatcher("group.jsp").forward(req, resp);
		    } else {
		    	resp.sendRedirect("pageErreur.jsp");
		    }
				
	    }else if(path.equals("/bloc.do")) {
	    	HttpSession session = req.getSession();
	    	// Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	 Long conversationId = (Long) session.getAttribute("conversationId");
	    	Long id = Long.parseLong(req.getParameter("id"));
	    	UtilisateurConversation uc=uci.getUtilisateurConversationByConversationAndUtilisateur(conversationId, id);
	    	uc.setBloque(true);
	    	uci.updateUtilisateurConversation(uc);
	    	req.getRequestDispatcher("group.do").forward(req, resp);
	    }else if(path.equals("/unbloc.do")) {
	    	HttpSession session = req.getSession();
	    	// Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	 Long conversationId = (Long) session.getAttribute("conversationId");
	    	Long id = Long.parseLong(req.getParameter("id"));
	    	UtilisateurConversation uc=uci.getUtilisateurConversationByConversationAndUtilisateur(conversationId, id);
	    	uc.setBloque(false);
	    	uci.updateUtilisateurConversation(uc);
	    	req.getRequestDispatcher("group.do").forward(req, resp);
	    }else if(path.equals("/mode.do")) {
	    	Long id = Long.parseLong(req.getParameter("id"));
	    	Utilisateur u=ui.getUtilisateur(id);
	    	u.setRole("mode");
	    	ui.updateUtilisateur(u);
	    	req.getRequestDispatcher("group.do").forward(req, resp);
	    }else if(path.equals("/unmode.do")) {
	    	Long id = Long.parseLong(req.getParameter("id"));
	    	Utilisateur u=ui.getUtilisateur(id);
	    	u.setRole("classique");
	    	ui.updateUtilisateur(u);
	    	req.getRequestDispatcher("group.do").forward(req, resp);
	    }
	    else if(path.equals("/status.do")) {
	    	 HttpSession session = req.getSession();
			Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	Utilisateur u=ui.getUtilisateur(UtilisateurId1);
	    	req.setAttribute("user", u);
	    	req.getRequestDispatcher("status.jsp").forward(req, resp);
	    }
	    /*else if(path.equals("/modifierstatus.do")) {
	    	 HttpSession session = req.getSession();
			Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	Utilisateur u=ui.getUtilisateur(UtilisateurId1);
	    	req.setAttribute("user", u);
	    	req.getRequestDispatcher("status.jsp").forward(req, resp);
	    }*/
		
		
		else  {
			resp.sendError(resp.SC_NOT_FOUND);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}



}



























































/*
 package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.ConversationImpl;
import dao.MessageImpl;
import dao.UtilisateurConversationImpl;
import dao.UtilisateurImpl;
import metier.Conversation;
import metier.Message;
import metier.Utilisateur;
import metier.UtilisateurConversation;
@WebServlet(name="cs" , urlPatterns = "*.do")
public class ServlateUtilisateur extends HttpServlet {
	UtilisateurImpl ui;
	ConversationImpl ci;
	MessageImpl mi;
	UtilisateurConversationImpl uci;
	SimpleDateFormat dateFormat ;

	@Override
	public void init() throws ServletException {
		ui=new UtilisateurImpl();
		ci=new ConversationImpl();
		mi=new MessageImpl();
		uci=new UtilisateurConversationImpl();
		 dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		if(path.equals("/*.do")) {
			req.getRequestDispatcher("AccPage.jsp").forward(req, resp);
			
		}
		else if(path.equals("/verification.do")){
			
			Long id=Long.parseLong(req.getParameter("id"));
			String motpass=req.getParameter("motpass");
			
			boolean authentificationReussie = false;

			List<Utilisateur> utilisateurs = ui.ListPersonnes();
			for (Utilisateur u : utilisateurs) {
			    if ((u.getIdUtilisateur() == id) && (u.getMotDePasse().equals(motpass))) {
			        HttpSession session = req.getSession();
			        session.setAttribute("UtilisateurId1", id);

			        //req.getRequestDispatcher("conexion.do").forward(req, resp);
			        req.getRequestDispatcher("PagePrincipale.jsp").forward(req, resp);
			        authentificationReussie = true;
			        break; // Sortir de la boucle une fois l'authentification réussie
			    }
			}

			if (!authentificationReussie) {
			    req.setAttribute("mess", "L'id ou le mot de passe incorrecte !");
			    req.getRequestDispatcher("AccPage.jsp").forward(req, resp);
			}

			
		}
		else if(path.equals("/conexion.do")) {
			HttpSession session = req.getSession();
	    	 Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
			List<Conversation>conversation =ui.listeConversation(UtilisateurId1);
			req.setAttribute("model", conversation);
			Utilisateur user =ui.getUtilisateur(UtilisateurId1);
			// Mettez à jour la date de la dernière connexion
			user.setLastConnectionTime(new Date()) ;
			ui.updateUtilisateur(user);
			req.getRequestDispatcher("/utilisateur.jsp").forward(req,resp);
		}
		
		else if (path.equals("/message.do")) {
		    HttpSession session = req.getSession();
		    Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
		    String conversationIdParam = req.getParameter("idc");
		    Long conversationId = null;
		    if (conversationIdParam != null && !conversationIdParam.isEmpty()) {
		    	conversationId = Long.parseLong(conversationIdParam);
		    	session.setAttribute("conversationId", conversationId);
		    }else {
		    	 //UtilisateurId2 = Long.parseLong(utilisateurId2Param);
		    	conversationId = (Long) session.getAttribute("conversationId");
		    }
		    
		    
		   // Long conversationId = Long.parseLong(req.getParameter("idc"));
	        
	        //session.setAttribute("conversationId", conversationId);
			List<Utilisateur>utilisateurs =ci.listeUtilisateurs(conversationId);
		    List<Long>ids=new ArrayList<>();
		    for(int i=0;i<utilisateurs.size();i++) {
		    	ids.add(utilisateurs.get(i).getIdUtilisateur());
		    }


		  if (conversationId != null) {
		        List<Message> messages = ci.getMessagesBetweenSpecificUsersInConversation(conversationId, ids);
		        req.setAttribute("model", messages);
		        
		        
		    	UtilisateurConversation uc=uci.getUtilisateurConversationByConversationAndUtilisateur(conversationId, UtilisateurId1);

		    	 req.setAttribute("uc", uc);
		        

		        req.getRequestDispatcher("message.jsp").forward(req, resp);
		    } else {
		    	resp.sendRedirect("pageErreur.jsp");
		    }
				
		}

		else if(path.equals("/envoyerMessage.do")) {
			 HttpSession session = req.getSession();
	    	 Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	    Long conversationId = (Long) session.getAttribute("conversationId");
	    	 if (UtilisateurId1 != null) {
			        String m=req.getParameter("message");
			        Utilisateur u=ui.getUtilisateur(UtilisateurId1);
			        Conversation c=ci.getConversation(conversationId);
			        Message message=new Message(m, new Date(), u, c);
			        mi.addMessage(message);
			        //List<Message>messages =ci.getMessagesBetweenUsersInConversation(1L, UtilisateurId2,conversationId );
					//req.setAttribute("model", messages);
			       // req.getRequestDispatcher("message.do").forward(req, resp);
			        //resp.sendRedirect("message.do");
			        resp.sendRedirect("message.do");
			        
			    }
			    else {
			        // Rediriger l'utilisateur vers une page d'erreur ou effectuer une autre action appropriée
			        resp.sendRedirect("pageErreur.jsp");
			    }
			
		}
		else if (path.equals("/registration.do")){
			String firstname=req.getParameter("firstName");
			String lastname=req.getParameter("lastName");
			String dobStr=req.getParameter("dob");
			String password=req.getParameter("password");
			Date date = null;
			try {
			    date = dateFormat.parse(dobStr);
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			ui.addUtilisateur(new Utilisateur(firstname,  password,lastname,"regular", date, null, null));
			
			req.setAttribute("firstname", firstname);
			req.setAttribute("lastname", lastname);
			
			req.getRequestDispatcher("Home.jsp").forward(req,resp);
			
		}
		else if(path.equals("/supprimerMessageD.do")) {
			Long messageId = Long.parseLong(req.getParameter("idm"));
			
			MessageImpl mi=new MessageImpl();
			Message m=mi.getMessage(messageId);
			mi.deletMessage(m);
			resp.sendRedirect("message.do");
	    	
	    }
		else if(path.equals("/supprimerMessageR.do")) {
			Long messageId = Long.parseLong(req.getParameter("idm"));
			
			MessageImpl mi=new MessageImpl();
			Message m=mi.getMessage(messageId);
			m.setSupprime(true);
			mi.updateMessage(m);
			resp.sendRedirect("message.do");
	    	
	    }else if(path.equals("/AjouterUtilisateur.do")) {
			
	    	List<Utilisateur>utilisateurs=ui.ListPersonnes();
	    	req.setAttribute("model", utilisateurs);
	    	
	    	req.getRequestDispatcher("AjouterUtilisateur.jsp").forward(req, resp);
	    	
	    	
	    }
	    else if(path.equals("/chat.do")) {
			HttpSession session = req.getSession();

	    	 Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	Long id = Long.parseLong(req.getParameter("id"));
	    	
	    	Utilisateur u1=ui.getUtilisateur(id);
	    	Utilisateur u2=ui.getUtilisateur(UtilisateurId1);
	    	String description=u1.getNom()+u2.getNom();
	    	List<Utilisateur>us=new ArrayList<>();
	    	us.add(u1);
	    	us.add(u2);
	    	//public Conversation(String description, List<Message> messages, List<Utilisateur> utilisateurs)
	    	Conversation c=new Conversation(description,null,null);
	    	ci.addConversation(c);
	 //içi on doit récup"rer le nom et le prenom pour construire le conversation
	    	
	    	req.getRequestDispatcher("conexion.do").forward(req, resp);
	    	
	    	
	    }else if(path.equals("/group.do")) {
	    	HttpSession session=req.getSession();
	    	Long conversationId = (Long) session.getAttribute("conversationId");
	    	 Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	List<Utilisateur>utilisateurs =ci.listeUtilisateurs(conversationId);
	    	
		   Utilisateur u=ui.getUtilisateur(UtilisateurId1);
		   
		  if (conversationId != null) {
		        //List<Message> messages = ci.getMessagesBetweenSpecificUsersInConversation(conversationId, ids);
		        req.setAttribute("model", utilisateurs);
		        req.setAttribute("user", u);

		        req.getRequestDispatcher("group.jsp").forward(req, resp);
		    } else {
		    	resp.sendRedirect("pageErreur.jsp");
		    }
				
	    }else if(path.equals("/bloc.do")) {
	    	HttpSession session = req.getSession();
	    	// Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	 Long conversationId = (Long) session.getAttribute("conversationId");
	    	Long id = Long.parseLong(req.getParameter("id"));
	    	UtilisateurConversation uc=uci.getUtilisateurConversationByConversationAndUtilisateur(conversationId, id);
	    	uc.setBloque(true);
	    	uci.updateUtilisateurConversation(uc);
	    	req.getRequestDispatcher("group.do").forward(req, resp);
	    }else if(path.equals("/unbloc.do")) {
	    	HttpSession session = req.getSession();
	    	// Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	 Long conversationId = (Long) session.getAttribute("conversationId");
	    	Long id = Long.parseLong(req.getParameter("id"));
	    	UtilisateurConversation uc=uci.getUtilisateurConversationByConversationAndUtilisateur(conversationId, id);
	    	uc.setBloque(false);
	    	uci.updateUtilisateurConversation(uc);
	    	req.getRequestDispatcher("group.do").forward(req, resp);
	    }else if(path.equals("/mode.do")) {
	    	Long id = Long.parseLong(req.getParameter("id"));
	    	Utilisateur u=ui.getUtilisateur(id);
	    	u.setRole("mode");
	    	ui.updateUtilisateur(u);
	    	req.getRequestDispatcher("group.do").forward(req, resp);
	    }else if(path.equals("/unmode.do")) {
	    	Long id = Long.parseLong(req.getParameter("id"));
	    	Utilisateur u=ui.getUtilisateur(id);
	    	u.setRole("clas");
	    	ui.updateUtilisateur(u);
	    	req.getRequestDispatcher("group.do").forward(req, resp);
	    }
	    else if(path.equals("/status.do")) {
	    	 HttpSession session = req.getSession();
			Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	Utilisateur u=ui.getUtilisateur(UtilisateurId1);
	    	req.setAttribute("user", u);
	    	req.getRequestDispatcher("status.jsp").forward(req, resp);
	    }
	    else if(path.equals("/modifierstatus.do")) {
	    	 HttpSession session = req.getSession();
			Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	Utilisateur u=ui.getUtilisateur(UtilisateurId1);
	    	req.setAttribute("user", u);
	    	req.getRequestDispatcher("status.jsp").forward(req, resp);
	    }
		
		
		else  {
			resp.sendError(resp.SC_NOT_FOUND);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}



}







 */








































//utilisateur à utilisateur

/*package web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.ConversationImpl;
import dao.MessageImpl;
import dao.UtilisateurImpl;
import metier.Conversation;
import metier.Message;
import metier.Utilisateur;
@WebServlet(name="cs" , urlPatterns = "*.do")
public class ServlateUtilisateur extends HttpServlet {
	UtilisateurImpl ui;
	ConversationImpl ci;
	MessageImpl mi;

	@Override
	public void init() throws ServletException {
		ui=new UtilisateurImpl();
		ci=new ConversationImpl();
		mi=new MessageImpl();
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		if(path.equals("/*.do")) {
			req.getRequestDispatcher("AccPage.jsp").forward(req, resp);
			
		}
		else if(path.equals("/verification.do")){
			
			Long id=Long.parseLong(req.getParameter("id"));
			String motpass=req.getParameter("motpass");
			
			boolean authentificationReussie = false;

			List<Utilisateur> utilisateurs = ui.ListPersonnes();
			for (Utilisateur u : utilisateurs) {
			    if ((u.getIdUtilisateur() == id) && (u.getMotDePasse().equals(motpass))) {
			        HttpSession session = req.getSession();
			        session.setAttribute("UtilisateurId1", id);

			        req.getRequestDispatcher("conexion.do").forward(req, resp);
			        authentificationReussie = true;
			        break; // Sortir de la boucle une fois l'authentification réussie
			    }
			}

			if (!authentificationReussie) {
			    req.setAttribute("mess", "L'id ou le mot de passe incorrecte !");
			    req.getRequestDispatcher("AccPage.jsp").forward(req, resp);
			}

			
		}
		else if(path.equals("/conexion.do")) {
			//Long uId1=1L;
			HttpSession session = req.getSession();
	    	 Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
			List<Conversation>conversation =ui.listeConversation(UtilisateurId1);/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////httpS
			// HttpSession session = req.getSession();
			 //session.setAttribute("UtilisateurId1", uId1);
			req.setAttribute("model", conversation);
			req.getRequestDispatcher("/utilisateur.jsp").forward(req,resp);
		}
		else if(path.equals("/listUtilisateurs.do")) {
			Long idc = Long.parseLong(req.getParameter("idc"));
	        // Stocker l'ID dans la session
	        HttpSession session = req.getSession();
	        session.setAttribute("conversationId", idc);
			List<Utilisateur>utilisateur =ci.listeUtilisateurs(idc);
			req.setAttribute("model", utilisateur);
			req.getRequestDispatcher("ListUtilisateurs.jsp").forward(req, resp);
		}
		else if (path.equals("/message.do")) {
		    HttpSession session = req.getSession();
		    Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");

		    // Ajoutez cette partie pour récupérer UtilisateurId2
		    String utilisateurId2Param = req.getParameter("UtilisateurId2");
		    Long UtilisateurId2 = null;

		    if (utilisateurId2Param != null && !utilisateurId2Param.isEmpty()) {
		        UtilisateurId2 = Long.parseLong(utilisateurId2Param);
		        session.setAttribute("UtilisateurId2", UtilisateurId2);
		    }else {
		    	 //UtilisateurId2 = Long.parseLong(utilisateurId2Param);
		    	  UtilisateurId2 = (Long) session.getAttribute("UtilisateurId2");
		    }
		   

		    Long conversationId = (Long) session.getAttribute("conversationId");

		    if (conversationId != null) {
		        List<Message> messages = ci.getMessagesBetweenUsersInConversation(UtilisateurId1, UtilisateurId2, conversationId);
		        req.setAttribute("model", messages);

		        req.getRequestDispatcher("message.jsp").forward(req, resp);
		    } else {
		        resp.sendRedirect("pageErreur.jsp");
		    }
		}

		else if(path.equals("/envoyerMessage.do")) {
			 HttpSession session = req.getSession();
	    	 Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
	    	    Long conversationId = (Long) session.getAttribute("conversationId");
	    	 if (UtilisateurId1 != null) {
			        String m=req.getParameter("message");
			        Utilisateur u=ui.getUtilisateur(UtilisateurId1);
			        Conversation c=ci.getConversation(conversationId);
			        Message message=new Message(m, new Date(), u, c);
			        mi.addMessage(message);
			        //List<Message>messages =ci.getMessagesBetweenUsersInConversation(1L, UtilisateurId2,conversationId );
					//req.setAttribute("model", messages);
			       // req.getRequestDispatcher("message.do").forward(req, resp);
			        //resp.sendRedirect("message.do");
			        resp.sendRedirect("message.do");
			    }
			    else {
			        // Rediriger l'utilisateur vers une page d'erreur ou effectuer une autre action appropriée
			        resp.sendRedirect("pageErreur.jsp");
			    }
			
		}
		else  {
			resp.sendError(resp.SC_NOT_FOUND);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}



}

*/






























































































































//web socket
/*package web;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;

import dao.ConversationImpl;
import dao.MessageImpl;
import dao.UtilisateurImpl;
import metier.Conversation;
import metier.Message;
import metier.Utilisateur;

@WebServlet(name = "cs", urlPatterns = "*.do")
public class ServlateUtilisateur extends HttpServlet {
    private UtilisateurImpl ui;
    private ConversationImpl ci;
    private MessageImpl mi;

    @Override
    public void init() throws ServletException {
        ui = new UtilisateurImpl();
        ci = new ConversationImpl();
        mi = new MessageImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/*.do")) {
            req.getRequestDispatcher("AccPage.jsp").forward(req, resp);
        } else if (path.equals("/verification.do")) {
            Long id = Long.parseLong(req.getParameter("id"));
            String motDePasse = req.getParameter("motpass");

            boolean authentificationReussie = false;

            List<Utilisateur> utilisateurs = ui.ListPersonnes();
            for (Utilisateur u : utilisateurs) {
                if ((u.getIdUtilisateur() == id) && (u.getMotDePasse().equals(motDePasse))) {
                    HttpSession session = req.getSession();
                    session.setAttribute("UtilisateurId1", id);

                    req.getRequestDispatcher("conexion.do").forward(req, resp);
                    authentificationReussie = true;
                    break;
                }
            }

            if (!authentificationReussie) {
                req.setAttribute("mess", "L'id ou le mot de passe incorrecte !");
                req.getRequestDispatcher("AccPage.jsp").forward(req, resp);
            }
        } else if (path.equals("/conexion.do")) {
            HttpSession session = req.getSession();
            Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
            List<Conversation> conversation = ui.listeConversation(UtilisateurId1);
            req.setAttribute("model", conversation);
            req.getRequestDispatcher("/utilisateur.jsp").forward(req, resp);
        } else if (path.equals("/listUtilisateurs.do")) {
            Long idc = Long.parseLong(req.getParameter("idc"));
            HttpSession session = req.getSession();
            session.setAttribute("conversationId", idc);
            List<Utilisateur> utilisateur = ci.listeUtilisateurs(idc);
            req.setAttribute("model", utilisateur);
            req.getRequestDispatcher("ListUtilisateurs.jsp").forward(req, resp);
        } else if (path.equals("/message.do")) {
            HttpSession session = req.getSession();
            Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");

            String utilisateurId2Param = req.getParameter("UtilisateurId2");
            Long UtilisateurId2 = null;

            if (utilisateurId2Param != null && !utilisateurId2Param.isEmpty()) {
                UtilisateurId2 = Long.parseLong(utilisateurId2Param);
                session.setAttribute("UtilisateurId2", UtilisateurId2);
            } else {
                UtilisateurId2 = (Long) session.getAttribute("UtilisateurId2");
            }

            Long conversationId = (Long) session.getAttribute("conversationId");

            if (conversationId != null) {
                List<Message> messages = ci.getMessagesBetweenUsersInConversation(UtilisateurId1, UtilisateurId2,
                        conversationId);
                req.setAttribute("model", messages);
                req.getRequestDispatcher("message.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("pageErreur.jsp");
            }
        } else if (path.equals("/envoyerMessage.do")) {
            envoyerMessage(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void envoyerMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long UtilisateurId1 = (Long) session.getAttribute("UtilisateurId1");
        Long conversationId = (Long) session.getAttribute("conversationId");

        if (UtilisateurId1 != null) {
            String messageText = req.getParameter("message");
            Utilisateur expediteur = ui.getUtilisateur(UtilisateurId1);
            Conversation conversation = ci.getConversation(conversationId);
            Message message = new Message(messageText, new Date(), expediteur, conversation);
            mi.addMessage(message);

            String websocketMessage = "newMessage," + message.getContenu();
            sendMessageToConversation(websocketMessage, conversationId, UtilisateurId1);

            resp.sendRedirect("message.do");
        } else {
            resp.sendRedirect("pageErreur.jsp");
        }
    }

    private void sendMessageToConversation(String message, Long conversationId, Long userId) {
        try {
            String endpointURL = "ws://localhost:8080/TPCHAT/websocket/" + conversationId + "/" + userId;
            Session session = ContainerProvider.getWebSocketContainer().connectToServer(ChatWebSocket.class,
                    URI.create(endpointURL));
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}



*/










