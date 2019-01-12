package com.ss.vv.ss.controller.base;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.vv.common.WebResponse;
import com.ss.vv.ss.domain.Attribute;
import com.ss.vv.ss.domain.Test;
import com.ss.vv.ss.domain.User;
import com.ss.vv.ss.service.IUserService;
import com.ss.vv.ss.vo.TestVo;
import com.ss.vv.ss.vo.UserVo;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	protected WebResponse webResponse;

	@Resource
	protected IUserService userService;
	
	@RequestMapping(value = "/useraddOrEditTest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse addOrEditTest(HttpServletRequest request, HttpServletResponse response, HttpSession session, String uid,
			@RequestParam(required = false)String uname, @RequestParam(required = false) String  upassword,
			@RequestParam(required = false) String usex, @RequestParam(required = false) String uphone,
			@RequestParam(required = false)String umail, @RequestParam(required = false) String ulevel) {
		if (uid==null || uid.length()==0) {
			return this.userAdd(request, response, session, uname, upassword,usex,uphone,umail,ulevel);
		} else {
			return this.UpdateByAll(request, response, session,uname, upassword,usex,uphone,umail,ulevel);
		}
	}
/*
 * PostMan使用：
 * http://localhost:8080/auto/user/userAdd?uname=乔钻石&upassword=123456&usex=男&uphone=1122334455&umail=qiaozuanshi@qq.com&ulevel=普通用户
 * 
 * */
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	private WebResponse userAdd(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String uname, String upassword, String usex, String uphone, String umail,String ulevel) {
		Object data = null;
		String statusMsg = "";
		Integer statusCode = 200;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("u_name", uname);
		paramMap.put("u_password", upassword);
		paramMap.put("u_sex", usex);
		paramMap.put("u_phone", uphone);
		paramMap.put("u_mail", umail);
		paramMap.put("u_level", ulevel);
		data = paramMap;
		if(uname == null||"".equals(uname.trim())||upassword==null||"".equals(upassword.trim()) 
				||usex==null||"".equals(usex.trim())||umail==null||"".equals(umail.trim()) ||ulevel==null||"".equals(ulevel.trim())) {
			statusMsg = " 必须填写！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		User user=new User();
		boolean isAdd=true;
		return this.addOrEditTest(request, response, session,data,user, uname, upassword, usex, uphone, umail,ulevel, isAdd);
	}
	
    /*对文本中的内容进行格式判断*/
	private WebResponse addOrEditTest(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Object data, User user, String uname, String upassword, String usex, String uphone, String umail,String ulevel,
			boolean isAdd) {
		String statusMsg = "";
		Integer statusCode = 200;
		if(uname != null && !("".equals(uname.trim()))) {
			if(uname.length()>25) {
				statusMsg = "名字过长";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			user.setUname(uname);
		}
		if(upassword != null) {
			if(upassword.indexOf(" ") != -1) {
				statusMsg = "密码存在空格，请重新输入";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}else {
				if(upassword.length()>255) {
				statusMsg = "密码过长";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
				}
			}
			user.setUpassword(upassword);
		}
		if(usex==null) {
			statusMsg="输入错误";
			statusCode=201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		user.setUsex(usex);
		if(uphone !=null) {
			Integer phone= uphone.matches("^[0-9]*$") ? Integer.parseInt(uphone) : 0;
			if(phone==0) {
				statusMsg="电话号码格式错误";
				statusCode=201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			if(uphone.length()>50) {
				statusMsg="电话号码过长";
				statusCode=201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			user.setUphone(uphone);
		}
		if(umail!=null) {
			boolean checkmail=umail.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
			if(checkmail=false) {
				statusMsg="邮箱格式错误";
				statusCode=201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}else if(checkmail=true) {
				if(umail.length()>255) {
					statusMsg="邮箱过长";
					statusCode=201;
					return webResponse.getWebResponse(statusCode, statusMsg, data);
				}
			}
			user.setUmail(umail);
		}
		if(ulevel!=null) {
			user.setUlevel(ulevel);
		}
		if (isAdd) {
			this.userService.insert(user);
			if (user.getUid()>0) {
				statusMsg = "成功插入！！！";
			} else {
				statusCode = 202;
				statusMsg = "insert false";
			} 
			int num = this.userService.update(user);
			if (num > 0) {
				statusMsg = "成功修改！！！";
			} else {
				statusCode = 202;
				statusMsg = "update false";
			}
		}
			return webResponse.getWebResponse(statusCode, statusMsg, data);
	}
	
	/*用户信息更新，全部信息更新*/
	@RequestMapping(value = "/UpdateByAll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	private WebResponse UpdateByAll(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String uname, String upassword, String usex, String uphone, String umail,String ulevel) {
		Object data = null;
		String statusMsg = "";
		Integer statusCode = 200;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("u_name", uname);
		paramMap.put("u_password", upassword);
		paramMap.put("u_sex", usex);
		paramMap.put("u_phone", uphone);
		paramMap.put("u_mail", umail);
		paramMap.put("u_level", ulevel);
		data = paramMap;
		if(uname == null||"".equals(uname.trim())||upassword==null||"".equals(upassword.trim()) 
				||usex==null||"".equals(usex.trim())||umail==null||"".equals(umail.trim()) ||ulevel==null||"".equals(ulevel.trim())) {
			statusMsg = " 必须填写！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		User userVo = this.userService.getByName(uname, upassword);
		User user = new User();
		BeanUtils.copyProperties(userVo, user);
		boolean isAdd=true;
		return this.addOrEditTest(request, response, session,data,user, uname, upassword, usex, uphone, umail,ulevel, isAdd);
	}
	
	/*根据用户名的id去删除用户信息*/
	@RequestMapping(value = "/deleteByNameAndPassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse deleteByNameAndPassword(@RequestParam String uname,@RequestParam String upassword) {
		Object data = uname;
		String statusMsg = "";
		Integer statusCode = 200;
		User user=userService.getByName(uname, upassword);
		if(user==null) {
			statusMsg = "密码有误";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		this.userService.deleteByNP(uname);
		statusMsg = "密码正确并注销成功！！！";
		return webResponse.getWebResponse(statusCode, statusMsg, data);
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse login(@RequestParam String uname,@RequestParam String upassword) {
		Object data = null;
		String statusMsg = "";
		Integer statusCode = 200;
		User user=userService.login(uname, upassword);
		if(user==null) {
			statusMsg = "密码有误";
			statusCode = 201;
		}else {
			statusMsg = "登录成功";
			statusCode = 200;
		}
		data=user;
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}

	
//http://localhost:8080/auto/user/getUserById?uId=1
	@RequestMapping(value = "/getUserById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getUserById(HttpServletRequest request, HttpServletResponse response, HttpSession session,@Param("uid")String uid) {
		Object data = uid;
		Integer statusCode = 200;
		String statusMsg = "";
		if (uid == null || uid.length() == 0 ) {
			statusMsg = "参数为空或参数过长错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}									
		Integer uIdNumNumeri = uid.matches("^[0-9]*$") ? Integer.parseInt(uid) : 0;
		if (uIdNumNumeri == 0 ) {
			statusMsg = "参数数字型错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		User user = this.userService.getById(uIdNumNumeri);
		if (user != null && user.getUid() > 0) {
			data = user;
			statusMsg = "获取单条数据成功！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}


	//http://localhost:8080/auto/user/getOtherUserById?uId=1
		@RequestMapping(value = "/getOtherUserById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public WebResponse getOtherUserById(HttpServletRequest request, HttpServletResponse response, HttpSession session,@Param("uid")String uid) {
			Object data = uid;
			Integer statusCode = 200;
			String statusMsg = "";
			if (uid == null || uid.length() == 0 ) {
				statusMsg = "参数为空或参数过长错误！！！";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}									
			Integer uIdNumNumeri = uid.matches("^[0-9]*$") ? Integer.parseInt(uid) : 0;
			if (uIdNumNumeri == 0 ) {
				statusMsg = "参数数字型错误！！！";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			User user = this.userService.getOtherById(uIdNumNumeri);
			if (user != null && user.getUid() > 0) {
				data = user;
				statusMsg = "获取单条数据成功！！！";
			} else {
				statusCode = 202;
				statusMsg = "no record!!!";
			}
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		 @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		 @ResponseBody
		    public String update(HttpServletRequest request, HttpServletResponse response, HttpSession session, User user) {
		        int result = userService.Update(user);
		        if (result >= 1) {
		            return "修改成功";
		        } else {
		            return "修改失败";
		        }
		    
		 }
}	
