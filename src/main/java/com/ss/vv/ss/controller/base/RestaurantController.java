package com.ss.vv.ss.controller.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.ss.vv.ss.domain.Restaurant;
import com.ss.vv.ss.domain.RestaurantFont;
import com.ss.vv.ss.domain.User;
import com.ss.vv.ss.service.IRestaurantService;


@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	protected WebResponse webResponse;

	@Resource
	protected IRestaurantService restaurantService;
	
	@RequestMapping(value = "/restaurantAddOrEditTest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse addOrEditTest(HttpServletRequest request, HttpServletResponse response, HttpSession session, String rid,
			@RequestParam(required = false)String uid, @RequestParam(required = false) String  address,
			@RequestParam(required = false) String rphone,@RequestParam(required = false)String rname,@RequestParam(required = false)String stars) {
		if (rid==null || rid.length()==0) {
			return this.restaurantAdd(request, response, session, uid, address, rphone, rname);
		} else {
			return this.UpdateStars(request, response, session, uid, rid, stars);
		}
	}

	@RequestMapping(value = "/UpdateStars", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
private WebResponse UpdateStars(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String uid, String rid, String stars) {
	
	return null;
	}


private WebResponse addOrEditTest(HttpServletRequest request, HttpServletResponse response, HttpSession session,
		Object data, Restaurant restaurant, String uid, String address, String rphone, String rname, boolean isAdd) {
	String statusMsg = "";
	Integer statusCode = 200;
	if(uid!=null) {
		if(uid.length()>20) {
			statusMsg = " uid过长";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		if(uid.indexOf(" ")!=-1) {
			statusMsg = "uid存在空格，请重新输入";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		restaurant.setuid(Integer.parseInt(uid));
	}
	if(address!=null) {
		if(address.length()>225) {
			statusMsg = " 详细地址过长";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		restaurant.setAddress(address);
	}
	if(rphone!=null) {
		if(rphone.indexOf(" ")!=-1) {
			statusMsg = "rPhone存在空格，请重新输入";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Integer phone= rphone.matches("^[0-9]*$") ? Integer.parseInt(rphone) : 0;
		if(phone==0) {
			statusMsg="电话号码格式错误";
			statusCode=201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		restaurant.setRphone(rphone);
	}
	if(rname!=null) {
		if(rname.length()>225) {
			statusMsg = " 店铺名字过长";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		restaurant.setrname(rname);
	}
	if (isAdd) {
		this.restaurantService.insert(restaurant);
		if (restaurant.getuid()>0) {
			statusMsg = "成功插入！！！";
		} else {
			statusCode = 202;
			statusMsg = "insert false";
		} 
	}
	return webResponse.getWebResponse(statusCode, statusMsg, data);
}
/*
 * Postman使用方法：
 *  http://localhost:8080/auto/restaurant/restaurantAdd?uId=2&address=广西科技大学&rPhone=123456&rName=有味道的编程
 * 
 * */
    @RequestMapping(value = "/restaurantAdd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
	private WebResponse restaurantAdd(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String uid, String address, String rphone, String rname) {
		Object data = null;
		String statusMsg = "";
		Integer statusCode = 200;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("uId", uid);
		paramMap.put("address", address);
		paramMap.put("rPhone", rphone);
		paramMap.put("rName", rname);
		data = paramMap;
		if (uid == null || "".equals(uid.trim()) || address == null || "".equals(address.trim()) 
				|| rphone == null || "".equals(rphone.trim())|| rname == null || "".equals(rname.trim())) {
			statusMsg = " 参数为空错误！！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Restaurant restaurant = new Restaurant();
		boolean isAdd = true;
		return this.addOrEditTest(request, response, session, data, restaurant, uid, address, rphone, rname, isAdd);
	}
    
   //http://localhost:8080/auto/restaurant/getrestauRantByName?name=有味道的编程
    @RequestMapping(value = "/getRestaurantByName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getRestaurantByName(HttpServletRequest request, HttpServletResponse response, HttpSession session,@Param("name")String name) {
		Object data = name;
		Integer statusCode = 200;
		String statusMsg = "";
		//|| name.length() > 11
		System.out.println(name);
		if (name == null || name.length() == 0) {
			statusMsg = "参数为空！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Integer nameNumNumeri = name.matches("^[0-9]*$") ? 0 : 1;
		if (nameNumNumeri == 0 ) {
			statusMsg = "参数数字型错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}

		List<Restaurant> restaurant = new ArrayList<Restaurant>();//此处 改成getone
		restaurant = this.restaurantService.getByName(name);
		Map<Object,Object> map=new HashMap<Object,Object>();
		map.put("list", restaurant);
		data=map;
		if ( map != null && map.get(restaurant) != null) {
			data = map;
			statusMsg = "获取单条数据成功！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}
    
    //http://localhost:8080/auto/restaurant/getrestauRantByUname?name=有味道的编程
    @RequestMapping(value = "/getRestaurantByUname", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getRestaurantByUname(HttpServletRequest request, HttpServletResponse response, HttpSession session,@Param("uname")String uname) {
		Object data = uname;
		Integer statusCode = 200;
		String statusMsg = "";
		//|| name.length() > 11
		System.out.println(uname);
		if (uname == null || uname.length() == 0) {
			statusMsg = "参数为空！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Integer nameNumNumeri = uname.matches("^[0-9]*$") ? 0 : 1;
		if (nameNumNumeri == 0 ) {
			statusMsg = "参数数字型错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		List<Restaurant> restaurant = new ArrayList<Restaurant>();//此处 改成getone
		restaurant = this.restaurantService.getByUname(uname);
		Map<Object,Object> map=new HashMap<Object,Object>();
		map.put("list", restaurant);
		data=map;
		/*if ( map != null && map.get(restaurant) != null) {
			data = map;
			statusMsg = "获取单条数据成功！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}*/
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}

    
 // http://localhost:8080/auto/restaurant/getRestaurantById?rId=1
    
 	@RequestMapping(value = "/getRestaurantById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
 	@ResponseBody
 	
 		public WebResponse getRestaurantById(HttpServletRequest request, HttpServletResponse response, HttpSession session,@Param("rid")String rid) {
 			Object data = rid;
 			Integer statusCode = 200;
 			String statusMsg = "";
 			if (rid == null || rid.length() == 0 ) {
 				statusMsg = "参数为空或参数过长错误！！！";
 				statusCode = 201;
 				return webResponse.getWebResponse(statusCode, statusMsg, data);
 			}									
 			Integer rIdNumNumeri = rid.matches("^[0-9]*$") ? Integer.parseInt(rid) : 0;
 			if (rIdNumNumeri == 0 ) {
 				statusMsg = "参数数字型错误！！！";
 				statusCode = 201;
 				return webResponse.getWebResponse(statusCode, statusMsg, data);
 			}
 			Restaurant restaurant = this.restaurantService.getById(rIdNumNumeri);
 			if (restaurant != null && restaurant.getrid() > 0) {
 				data = restaurant;
 				statusMsg = "获取单条数据成功！！！";
 			} else {
 				statusCode = 202;	
 				statusMsg = "no record!!!";
 			}
 			return webResponse.getWebResponse(statusCode, statusMsg, data);
 		}
   /*  
	 // http://localhost:8080/auto/restaurant/getRestaurantByStar?star=5
	 	@RequestMapping(value = "/getRestaurantByStar", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	 	@ResponseBody
	 		public WebResponse getRestaurantByStar(HttpServletRequest request, HttpServletResponse response, HttpSession session,String star) {
	 			Object data = star;
	 			Integer statusCode = 200;
	 			String statusMsg = "";
	 			if (star == null || star.length() == 0 ) {
	 				statusMsg = "参数为空或参数过长错误！！！";
	 				statusCode = 201;
	 				return webResponse.getWebResponse(statusCode, statusMsg, data);
	 			}									
	 			Integer starNumNumeri = star.matches("^[0-9]*$") ? Integer.parseInt(star) : 0;
	 			if (starNumNumeri == 0 ) {
	 				statusMsg = "参数数字型错误！！！";
	 				statusCode = 201;
	 				return webResponse.getWebResponse(statusCode, statusMsg, data);
	 			}
	 			if (starNumNumeri > 5) {
	 				statusMsg = "参数过大！！！";
	 				statusCode = 201;
	 				return webResponse.getWebResponse(statusCode, statusMsg, data);
	 			}
	 			Restaurant restaurant = this.restaurantService.getByStar(starNumNumeri);
	 			if (restaurant != null && restaurant.getStars() > 0) {
	 				data = restaurant;
	 				statusMsg = "获取单条数据成功！！！";
	 			} else {
	 				statusCode = 202;
	 				statusMsg = "no record!!!";
	 			}
	 			return webResponse.getWebResponse(statusCode, statusMsg, data);
	 		}*/
	 	
	 	//http://localhost:8080/auto/restaurant/getRestaurantList?pageNo=1&pageSize=10&order=r_id&desc=desc
	 	@RequestMapping(value = "/getRestaurantList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public WebResponse getRestaurantList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(defaultValue = "1", required = false) Integer pageNo,  
			@RequestParam(defaultValue = "10", required = false) Integer pageSize, 
			@RequestParam(defaultValue = "r_id", required = false) String order,
			@RequestParam(defaultValue = "desc", required = false) String desc ) {
			Object data = null;
			String statusMsg = "";
			int statusCode = 200;
			LinkedHashMap<String, String> condition = new LinkedHashMap<String, String>();
			String field = null;
			if (condition.size() > 0) {
				condition.put(condition.entrySet().iterator().next().getKey(), "");
			}
			int count = this.restaurantService.getCount(condition, field);
			if (order != null && order.length() > 0 & "desc".equals(desc)) {
				order = order + " desc";
			}
			List<Restaurant> list = this.restaurantService.getList(condition, pageNo, pageSize, order, field);
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("total", count);
			int size = list.size();
			if (size > 0) {
				List<RestaurantFont> listFont = new ArrayList<RestaurantFont>();
				Restaurant re;
				RestaurantFont reFont = new RestaurantFont(); 
				for (int i = 0; i < size; i++) {
					re = list.get(i);
					BeanUtils.copyProperties(re, reFont);
					listFont.add(reFont);
					reFont = new RestaurantFont();
				}
				map.put("list", listFont);
				data = map;
				statusMsg = "根据条件获取分页数据成功！！！";
			} else {
				map.put("list", list);
				data = map;
				statusCode = 202;
				statusMsg = "no record!!!";
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
	 	
	 	//http://localhost:8080/auto/restaurant/getRestaurantListByStar?pageNo=1&pageSize=10&order=stars&desc=desc&star=5
	 	@RequestMapping(value = "/getRestaurantListByStar", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public WebResponse getRestaurantListByStar(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(defaultValue = "1", required = false) Integer pageNo,  
			@RequestParam(defaultValue = "10", required = false) Integer pageSize, 
			@RequestParam(defaultValue = "stars", required = false) String order,
			@RequestParam(defaultValue = "desc", required = false) String desc,
			@RequestParam(required = false) String star) {
			Object data = null;
			String statusMsg = "";
			int statusCode = 200;
			LinkedHashMap<String, String> condition = new LinkedHashMap<String, String>();
			String field = null;
			if (condition.size() > 0) {
				condition.put(condition.entrySet().iterator().next().getKey(), "");
			}
			int count = this.restaurantService.getCount(condition, field);
			if (order != null && order.length() > 0 & "desc".equals(desc)) {
				order = order + " desc";
			}
			
			if (star == null || star.length() == 0 ) {
 				statusMsg = "参数为空或参数过长错误！！！";
 				statusCode = 201;
 				return webResponse.getWebResponse(statusCode, statusMsg, data);
 			}									
 			Integer starNumNumeri = star.matches("^[0-9]*$") ? Integer.parseInt(star) : 0;
 			if (starNumNumeri == 0 ) {
 				statusMsg = "参数数字型错误！！！";
 				statusCode = 201;
 				return webResponse.getWebResponse(statusCode, statusMsg, data);
 			}
 			Restaurant restaurant = this.restaurantService.getById(starNumNumeri);
 			if (restaurant != null) {
 				data = restaurant;
 				statusMsg = "获取单条数据成功！！！";
 			} else {
 				statusCode = 202;	
 				statusMsg = "no record!!!";
 			}
 			
			List<Restaurant> list = this.restaurantService.getListByStar(condition, pageNo, pageSize, order, field, starNumNumeri);
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("total", count);
			int size = list.size();
			if (size > 0) {
				List<RestaurantFont> listFont = new ArrayList<RestaurantFont>();
				Restaurant re;
				RestaurantFont reFont = new RestaurantFont(); 
				for (int i = 0; i < size; i++) {
					re = list.get(i);
					BeanUtils.copyProperties(re, reFont);
					listFont.add(reFont);
					reFont = new RestaurantFont();
				}
				map.put("list", listFont);
				data = map;
				statusMsg = "根据条件获取分页数据成功！！！";
			} else {
				map.put("list", list);
				data = map;
				statusCode = 202;
				statusMsg = "no record!!!";
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
	 	@RequestMapping(value = "/deleteByAId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	 	@ResponseBody
	 	public WebResponse deleteByAId(HttpServletRequest request, HttpServletResponse response, HttpSession session,String aid) {
	 		Object data = aid;
	 		String statusMsg = "";
	 		Integer statusCode = 200;
	 		if (aid == null || "".equals(aid.trim())) {
	 			statusMsg = "请输入食品ID！！！";
	 			statusCode = 201;
	 			return webResponse.getWebResponse(statusCode, statusMsg, data);
	 		 } 
	 		Integer a_idNumeri = aid.matches("^[0-9]*$") ? Integer.parseInt(aid) : 0;
	 		if (a_idNumeri == 0) {
	 			statusMsg = "ID必须为整数！！！";
	 			statusCode = 201;
	 			return webResponse.getWebResponse(statusCode, statusMsg, data);
	 		}
	 		this.restaurantService.deleteByAId(aid);
	 		statusMsg = "删除食品信息成功！！！";
	 		return webResponse.getWebResponse(statusCode, statusMsg, data);
	 		
	 	    }
	 	@RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	 	@ResponseBody
	 	public WebResponse deleteById(HttpServletRequest request, HttpServletResponse response, HttpSession session,String rid) {
	 		Object data = rid;
	 		String statusMsg = "";
	 		Integer statusCode = 200;
	 		if (rid == null || "".equals(rid.trim())) {
	 			statusMsg = "请输入店铺ID！！！";
	 			statusCode = 201;
	 			return webResponse.getWebResponse(statusCode, statusMsg, data);
	 		 } 
	 		Integer r_idNumeri = rid.matches("^[0-9]*$") ? Integer.parseInt(rid) : 0;
	 		if (r_idNumeri == 0) {
	 			statusMsg = "ID必须为整数！！！";
	 			statusCode = 201;
	 			return webResponse.getWebResponse(statusCode, statusMsg, data);
	 		}
	 		this.restaurantService.deleteById(rid);
	 		statusMsg = "注销成功！！！";
	 		return webResponse.getWebResponse(statusCode, statusMsg, data);
	 		
	 	    }
	 	
	 	//http://localhost:8080/auto/restaurant/update?rname=nihao&address=666&rphone=666&uid=0&rid=2
	 	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	 	@ResponseBody
	    public WebResponse update(HttpServletRequest request, HttpServletResponse response, HttpSession session, Restaurant restaurant) {
	 		Object data = null;
	 		String statusMsg = "";
	 		Integer statusCode = 200;
	        int result = restaurantService.Update(restaurant);
	        if (result >= 1) {
	        	statusMsg = "修改成功";
	 			statusCode = 200;
	            return  webResponse.getWebResponse(statusCode, statusMsg, data);
	        } else {
	        	statusMsg = "修改失败";
	 			statusCode = 201;
	            return  webResponse.getWebResponse(statusCode, statusMsg, data);
	        }
	    
	     }
	 	@RequestMapping(value = "/foodupdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	 	@ResponseBody
	    public String foodupdate(HttpServletRequest request, HttpServletResponse response, HttpSession session, Attribute attribute) {
	        int result = restaurantService.foodUpdate(attribute);
	        if (result >= 1) {
	            return "修改成功";
	        } else {
	            return "修改失败";
	        }
	    
	     }
}
