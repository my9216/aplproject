/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.apl.sha.ers.struts.form;

import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;

import com.apl.sha.ers.util.Util;

/** 
 * MyEclipse Struts
 * Creation date: 03-02-2007
 * 
 * XDoclet definition:
 * @struts.form name="setBookingCtnForm"
 */
public class SetBookingCtnForm extends MapForm {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);
		MessageResources mResources = (MessageResources) request
				.getAttribute(Globals.MESSAGES_KEY);
		Locale locale = RequestUtils.getUserLocale(request, null);
		for (Iterator it = getRowIdList().iterator(); it.hasNext();) {
			String key = (String) it.next();
			if (getAttribute(key + "_checked") != null) {
				/*if (!DateUtil.isValidDate((String) getAttribute(key
						+ "_initalDate"))) {
					errors.add(key, new ActionMessage("errors.date", 
							mResources.getMessage(locale,"depot.container.initaldate.displayname"),
							mResources.getMessage(locale,"format.date.standard")));

				}*/
				if (!Util.isInteger((String) getAttribute(key + "_ctnQty"))) {
					errors.add(key, new ActionMessage("errors.integer",
							mResources.getMessage(locale,"booking.container.ctnqty.displayname")));
				}else if(Integer.parseInt((String)getAttribute(key + "_ctnQty"))<=0) {
					errors.add(key, new ActionMessage("errors.integer",
							mResources.getMessage(locale,"booking.container.ctnqty.displayname")));
				}
			}
		}
		return errors;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}
}