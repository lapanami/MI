/**
 * @author loc.mh
 */
package vn.credit.home.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.credit.home.service.IUserService;
import vn.credit.home.util.param.Result;

/**
 * @author loc.mh
 *
 */
@Controller
@RequestMapping(value = "/user")
public class PutUserController {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/put", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Result putUser(@RequestParam String userId, @RequestParam String firstName, @RequestParam String lastName,
			HttpSession session) {
		Result result = new Result();
		validateUserPut(result, userId, firstName, lastName);
		if (result.getError() == 0) {
			vn.credit.home.entity.mssql.SecUser user = userService.getUserByUserId(userId);
			if (user == null) {
				result.setError(-3);
				result.setMessage("Error: User is not exist in system, cannot update user!");
			} else {
				user.setFirstName(firstName);
				user.setLastName(lastName);
				userService.putUser(user);
			}
		}
		return result;
	}

	private void validateUserPut(Result result, String userId, String firstName, String lastName) {
		if (StringUtils.isEmpty(userId)) {
			result.setError(-2);
			result.setMessage("Wrong input parameter, cannot find user identity in request, please request the page!");
			return;
		}
		if (StringUtils.isEmpty(firstName)) {
			result.setError(-2);
			result.setMessage("Wrong input parameter, First Name cannot be empty!");
			return;
		}
		if (StringUtils.isEmpty(lastName)) {
			result.setError(-2);
			result.setMessage("Wrong input parameter, Last Name cannot be empty!");
			return;
		}
		result.setError(0);
	}

}
