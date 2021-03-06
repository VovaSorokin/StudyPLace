package web.command.http.post;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.User;
import model.UserInfo;
//import service.LoginService;
import service.ProfileService;
//import utils.HashUtil;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class UpdateProfileCommand implements Command {
	
	private static Logger LOG = Logger.getLogger(LoginCommand.class);
	
	private ProfileService profileService ;
	
	public UpdateProfileCommand(ProfileService profileService) {
		super();
		this.profileService = profileService;
	}

	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		User userNow = (User) session.getAttribute("user");
		UserInfo userInfoNow = (UserInfo) session.getAttribute("thisUserInfo");
		if(userInfoNow==null) {
			userInfoNow = new UserInfo();
		}
		
		if(!"".equals(request.getParameter("username"))) {
			userNow.setLogin(request.getParameter("username"));
			
		}
		if(!"".equals(request.getParameter("name"))) {
			userNow.setName(request.getParameter("name"));
		}
		if(!"".equals(request.getParameter("surname"))) {
			userNow.setSurname(request.getParameter("surname"));
		}
		System.out.println(request.getParameter("about"));
		if(!"".equals(request.getParameter("about"))) {
			System.out.println(request.getParameter("about"));
			userInfoNow.setAbout(request.getParameter("about"));
		}
		
		if(!"".equals(request.getParameter("e-mail"))) {
			System.out.println(request.getParameter("e-mail"));
			userInfoNow.setEmail(request.getParameter("e-mail"));
		}
		if(!"".equals(request.getParameter("messanger"))) {
			userInfoNow.setMessanger(request.getParameter("messanger"));
		}
		if(!"".equals(request.getParameter("website"))) {
			userInfoNow.setWebsite(request.getParameter("website"));
		}
		
		
		if(userNow.getInfoId().equals(0)) {
			System.out.println("я здесь 1");
			userInfoNow = profileService.insertUserInfo(userInfoNow);
			profileService.updateUser(userNow, userInfoNow.getId());
		    }else {
		    	System.out.println("я здесь 2" + " " + userNow.getInfoId());
		      profileService.updateUserInfo(userInfoNow);
		      //System.out.println("я здесь 2");
		      profileService.updateUser(userNow);
		    }
		
		
		LOG.trace("Update user" );
		
		int methodProfile = 2;
		session.setAttribute("user", userNow);
		session.setAttribute("thisUserInfo", userInfoNow);
		request.getSession().setAttribute("methodProfile", methodProfile);
		CommandResult cr = new HttpCommandResult(RequestType.POST,Path.PAGE_PROFILE_POST);
		
		LOG.debug("Commands finished");
		return cr;
	}

}
