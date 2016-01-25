/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.apl.sha.ers.struts.action;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.apl.sha.ers.exception.DataException;
import com.apl.sha.ers.exception.LoginException;
import com.apl.sha.ers.model.CtnType;
import com.apl.sha.ers.model.User;
import com.apl.sha.ers.struts.form.UserLoginForm;

/** 
 * MyEclipse Struts
 * Creation date: 12-25-2006
 * 
 * XDoclet definition:
 * @struts.action path="/userLogin" name="userLoginForm" input="/userLogin.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/main.jsp"
 */
public class UserLoginAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserLoginForm userLoginForm = (UserLoginForm) form;
		HttpSession session=request.getSession();
		User user=null;
		try {
			user=process.getUser(userLoginForm.getUsername().toLowerCase(), userLoginForm.getPassword(),true);
			session.setAttribute("user",user);
		} catch (LoginException e) {
			ActionErrors errors = new ActionErrors();
			int errCode=e.getStatus();
			switch(errCode) {
				case -1:
					errors.add("loginfail", new ActionMessage("error.login.nouser"));
					break;
				case -2:
					errors.add("loginfail", new ActionMessage("error.login.wrongpwd"));
					Secure(session, userLoginForm.getUsername().toLowerCase());
					break;
				case 1:
					errors.add("loginfail", new ActionMessage("error.login.deleted"));
					break;
				case 2:
					errors.add("loginfail", new ActionMessage("error.login.locked"));
					break;
				default:
					errors.add("loginfail", new ActionMessage("error.login.failure"));
					break;
			}
			this.addErrors(request, errors);
			return mapping.getInputForward();
		}
/*		if(user.getLocale()!=null) {
			Locale locale=new Locale(user.getLocale().substring(0, 2),user.getLocale().substring(3));
			setLocale(request, locale);
		}*/
		//Add application object
        //  if(session.getServletContext().getAttribute("vessels")==null) {
			session.getServletContext().setAttribute("vessels", process.getVessels());
			Map ctnTypes=process.getCtnTypes();
			session.getServletContext().setAttribute("ctntypes", ctnTypes);
			Map ctnCodes=new TreeMap();
			for(Iterator it=ctnTypes.keySet().iterator();it.hasNext();) {
				CtnType ctype=(CtnType) ctnTypes.get(it.next());
				ctnCodes.put(ctype.getCtncode(), ctype.getCtncode());
			}
			session.getServletContext().setAttribute("ctncodes", ctnCodes);
			try {
				session.getServletContext().setAttribute("alldepots", process.getDepots(""));
				session.getServletContext().setAttribute("users", process.getUsers());
			} catch (DataException e) {
			}
		//}
		session.setAttribute("newses", process.getNewses(user));
		session.setAttribute("bulletins", process.getBulletins(user));
		try {
			session.setAttribute("localdepots", process.getDepots(user.getLocation()));
		} catch (DataException e) {
		}
		session.setAttribute("ctntypes", process.getCtnTypes());
		if(user.getUsergroup().getGroupid()==1) {
			process.purgeFolder(session.getServletContext().getRealPath("report"));
		}
		return mapping.findForward(success);
	}
	
	private void Secure(HttpSession session,String username) {
		String tmp_username=session.getAttribute("tryUsername")!=null?(String) session.getAttribute("tryUsername"):"";
		if(tmp_username.equalsIgnoreCase(username)) {
			Integer retryTimes=(Integer)session.getAttribute("retryTimes");
			retryTimes++;
			if(retryTimes>5) {
				process.setUser(username,2);
//				return false;
			}
			session.setAttribute("retryTimes", retryTimes);
		}else {
			session.setAttribute("tryUsername",username);
			session.setAttribute("retryTimes", 1);
		}
	}
}