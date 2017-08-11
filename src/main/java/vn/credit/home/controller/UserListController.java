/**
 * @author loc.mh
 */
package vn.credit.home.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.credit.home.service.IUserService;
import vn.credit.home.util.param.DataTablesModel;
import vn.credit.home.util.param.DataTablesResult;

/**
 * @author loc.mh
 *
 */
@Controller
@RequestMapping(value = "/Information")
public class UserListController {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/Week", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public DataTablesResult search(@RequestBody DataTablesModel data, HttpSession session) {
		DataTablesResult result = new DataTablesResult();
		result.setDraw(data.getDraw());
		result.setRecordsTotal(userService.countUser());
		Map<String, Object> dataResult = userService.searchUser(data.getStart(), data.getLength(),
				data.getSearch().getValue(), data.getOrder());
		result.setRecordsFiltered(Long.parseLong(String.valueOf(dataResult.get("total"))));
		result.setData(dataResult.get("data"));
		return result;
	}
}
