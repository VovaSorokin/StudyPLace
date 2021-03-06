package web.command.http.get;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class GetSignInCommand implements Command{

private static final Logger LOG = Logger.getLogger(GetRegistrationCommand.class);
	
	public GetSignInCommand() {
		super();
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		LOG.debug("Command starts");
		
		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_LOGIN);
		LOG.debug("Command finished");
		return cr;
	}

}
