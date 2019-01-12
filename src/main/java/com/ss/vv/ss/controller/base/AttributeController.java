package com.ss.vv.ss.controller.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ss.vv.ss.domain.AttributeFont;
import com.ss.vv.ss.domain.Restaurant;
import com.ss.vv.ss.domain.RestaurantFont;
import com.ss.vv.ss.service.IAttributeService;

@Controller
@RequestMapping("/attribute")
public class AttributeController {

	@Autowired
	protected WebResponse webResponse;

	@Resource
	protected IAttributeService attributeService;
	
	@RequestMapping(value = "/UpdateAttribute", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	private WebResponse UpdateAttribute(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String aid, String aname, String count, String expDate, String price, String proDate, String weight) {
		
		return null;
	}
	
	@RequestMapping(value = "/attributeAdd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	private WebResponse attributeAdd(HttpServletRequest request, HttpServletResponse response, HttpSession session,String rid, String aname, String count, String expDate, String price, String proDate, String weight) {
		Object data = null;
		String statusMsg = "";
		Integer statusCode = 200;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("r_id", rid);
		paramMap.put("a_name", aname);
		paramMap.put("count", count);
		paramMap.put("price", price);
		paramMap.put("exp_Date", expDate);
		paramMap.put("pro_Date", proDate);
		paramMap.put("weight", weight);
		data=paramMap;
		if(aname==null||"".equals(aname.trim())||count==null||"".equals(count.trim())||price==null||"".equals(price.trim())||expDate==null||"".equals(expDate.trim())||proDate==null||"".equals(proDate.trim())||weight==null||"".equals(weight.trim())) {
			statusMsg = "必须全部填写";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Attribute attribute=new Attribute();
		boolean isAdd=true;
		return this.addOrEditTest(request, response, session, data, attribute, rid, aname, count, expDate, price, proDate, weight,isAdd);
	}

	private WebResponse addOrEditTest(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Object data, Attribute attribute,String rid, String aname, String count, String expDate, String price,
			String proDate, String weight, boolean isAdd) {
		String statusMsg = "";
		Integer statusCode = 200;
		if(rid!=null) {
			if(rid.length()>20) {
				statusMsg = "rid过长";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			attribute.setRid(Integer.parseInt(rid));
		}
		if(aname!=null) {
			if(aname.length()>225) {
				statusMsg = "食品过长";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			attribute.setAname(aname);
		}
		if(count!=null) {
			if(count.indexOf(" ")!=-1) {
				statusMsg = "数量存在空格，请更改";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			attribute.setCount(Integer.parseInt(count));
		}
		if(price!=null) {
			if(price.indexOf(" ")!=-1) {
				statusMsg = "价钱存在空格，请更改";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			attribute.setPrice(Float.parseFloat(price));
		}
		if(weight!=null) {
			if(weight.indexOf(" ")!=-1) {
				statusMsg = "重量存在空格，请更改";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			attribute.setWeight(Float.parseFloat(weight));
		}
		if(expDate!=null) {
			if(expDate.indexOf(" ")!=-1) {
				statusMsg = "开始时间存在空格，请更改";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			try {
				SimpleDateFormat checkDate=new SimpleDateFormat("YYYY-MM-DD");
				Date date=checkDate.parse(expDate);
				attribute.setExpDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			};
		}
		if(proDate!=null) {
			if(proDate.indexOf(" ")!=-1) {
				statusMsg = "结束时间存在空格，请更改";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			try {
				SimpleDateFormat checkDate=new SimpleDateFormat("YYYY-MM-DD");
				Date date=checkDate.parse(proDate);
				attribute.setProDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			};
		}
		if (isAdd) {
			this.attributeService.insert(attribute);
			if (attribute.getaid()>0) {
				statusMsg = "成功插入！！！";
			} else {
				statusCode = 202;
				statusMsg = "insert false";
			} 
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}

	// http://localhost:8080/auto/attribute/getAttributeById?aId=1
	@RequestMapping(value = "/getAttributeById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	
		public WebResponse getattributeById(@Param("aid")String aid) {
			Object data = aid;
			Integer statusCode = 200;
			String statusMsg = "";
			if (aid == null || aid.length() == 0 ) {
				statusMsg = "参数为空或参数过长错误！！！";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}									
			Integer aIdNumNumeri = aid.matches("^[0-9]*$") ? Integer.parseInt(aid) : 0;
			if (aIdNumNumeri == 0 ) {
				statusMsg = "参数数字型错误！！！";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			Attribute attribute = this.attributeService.getById(aIdNumNumeri);
			if (attribute != null && attribute.getaid() > 0) {
				data = attribute;
				statusMsg = "获取单条数据成功！！！";
			} else {
				statusCode = 202;
				statusMsg = "no record!!!";
			}
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
	
	// http://localhost:8080/auto/attribute/getAttributeByRestaurantId?rId=1 //attribute表中日期须有值
	@RequestMapping(value = "/getAttributeByRestaurantId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getattributeByRestaurantId(@Param("rid")String rid) {
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
		List<Attribute> attribute = this.attributeService.getByRId(rIdNumNumeri);
		if (attribute != null  ) {
			data = attribute;
			statusMsg = "获取单条数据成功！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}
	//用户进入推荐页面-通过价格查询同种食物在不同店铺的价格-主体版面显示店铺信息，用食物名字作为形参，
	// http://localhost:8080/auto/attribute/getAttributeByPrice?price=9
			@RequestMapping(value = "/getAttributeByPrice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
			@ResponseBody
			public WebResponse getAttributeByPrice(@Param("price")String price) {
				Object data = price;
				Integer statusCode = 200;
				String statusMsg = "";
				if (price == null || price.length() == 0 ) {
					statusMsg = "参数为空或参数过长错误！！！";
					statusCode = 201;
					return webResponse.getWebResponse(statusCode, statusMsg, data);
				}									
				Float priceNumNumeri = price.matches("^[0-9]*$") ? Float.parseFloat(price) : 0;
				if (priceNumNumeri == 0 ) {
					statusMsg = "参数数字型错误！！！";
					statusCode = 201;
					return webResponse.getWebResponse(statusCode, statusMsg, data);
				}
				List<Attribute> attribute = new ArrayList<Attribute>();
				attribute = this.attributeService.getByPrice(priceNumNumeri);
				Map<Object,Object> map=new HashMap<Object,Object>();
				map.put("list", attribute);
				data=map;
				if (attribute != null) {
					data = attribute;
					statusMsg = "获取单条数据成功！！！";
				} else {
					statusCode = 202;
					statusMsg = "no record!!!";
				}
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			}
			
	
			// http://localhost:8080/auto/attribute/getAttributeByRname?
					@RequestMapping(value = "/getAttributeByRname", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
					@ResponseBody
					public WebResponse getAttributeByRname(@Param("rname")String rname) {
						Object data = rname;
						Integer statusCode = 200;
						String statusMsg = "";
						if (rname == null || rname.length() == 0 ) {
							statusMsg = "参数为空或参数过长错误！！！";
							statusCode = 201;
							return webResponse.getWebResponse(statusCode, statusMsg, data);
						}					
						List<Attribute> attribute = new ArrayList<Attribute>();
						attribute = this.attributeService.getByRname(rname);
						Map<Object,Object> map=new HashMap<Object,Object>();
						map.put("list", attribute);
						data=map;
						System.out.println(rname);
						System.out.println(data);
						if (attribute != null) {
							data = attribute;
							statusMsg = "获取单条数据成功！！！";
						} else {
							statusCode = 202;
							statusMsg = "no record!!!";
						}
						return webResponse.getWebResponse(statusCode, statusMsg, data);
					}
					
					// http://localhost:8080/auto/attribute/getAttributeByAname?
					@RequestMapping(value = "/getAttributeByAname", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
					@ResponseBody
					public WebResponse getAttributeByAname(@Param("aname")String aname) {
						Object data = aname;
						Integer statusCode = 200;
						String statusMsg = "";
						if (aname == null || aname.length() == 0 ) {
							statusMsg = "参数为空或参数过长错误！！！";
							statusCode = 201;
							return webResponse.getWebResponse(statusCode, statusMsg, data);
						}					
						List<Attribute> attribute = new ArrayList<Attribute>();
						attribute = this.attributeService.getByAname(aname);
						Map<Object,Object> map=new HashMap<Object,Object>();
						map.put("list", attribute);
						data=map;
						System.out.println(aname);
						System.out.println(data);
						if (attribute != null) {
							data = attribute;
							statusMsg = "获取单条数据成功！！！";
						} else {
							statusCode = 202;
							statusMsg = "no record!!!";
						}
						return webResponse.getWebResponse(statusCode, statusMsg, data);
					}
					
			//http://localhost:8080/auto/attribute/getAttributeList?pageNo=1&pageSize=10&order=a_id&desc=desc
		 	@RequestMapping(value = "/getAttributeList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
			@ResponseBody
			public WebResponse getAttributeList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
				@RequestParam(defaultValue = "1", required = false) Integer pageNo,  
				@RequestParam(defaultValue = "10", required = false) Integer pageSize, 
				@RequestParam(defaultValue = "aId", required = false) String order,
				@RequestParam(defaultValue = "desc", required = false) String desc ) {
				Object data = null;
				String statusMsg = "";
				int statusCode = 200;
				LinkedHashMap<String, String> condition = new LinkedHashMap<String, String>();
				String field = null;
				if (condition.size() > 0) {
					condition.put(condition.entrySet().iterator().next().getKey(), "");
				}
				int count = this.attributeService.getCount(condition, field);
				if (order != null && order.length() > 0 & "desc".equals(desc)) {
					order = order + " desc";
				}
				List<Attribute> list = this.attributeService.getList(condition, pageNo, pageSize, order, field);
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("total", count);
				int size = list.size();
				if (size > 0) {
					List<AttributeFont> listFont = new ArrayList<AttributeFont>();
					Attribute re;
					AttributeFont reFont = new AttributeFont(); 
					for (int i = 0; i < size; i++) {
						re = list.get(i);
						BeanUtils.copyProperties(re, reFont);
						listFont.add(reFont);
						reFont = new AttributeFont();
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
		 	
		 	//http://localhost:8080/auto/attribute/getAttributeListByNameNPrice?pageNo=1&pageSize=10&order=a_id&desc=desc
		 	@RequestMapping(value = "/getAttributeListByNameNPrice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
			@ResponseBody
			public WebResponse getAttributeListByNameNPrice(HttpServletRequest request, HttpServletResponse response, HttpSession session,
				@RequestParam(defaultValue = "1", required = false) Integer pageNo,  
				@RequestParam(defaultValue = "10", required = false) Integer pageSize, 
				@RequestParam(defaultValue = "aId", required = false) String order,
				@RequestParam(defaultValue = "desc", required = false) String desc,
				@RequestParam(required = false) String name,
				@RequestParam(required = false) String price) {
				Object data = null;
				String statusMsg = "";
				int statusCode = 200;
				LinkedHashMap<String, String> condition = new LinkedHashMap<String, String>();
				String field = null;
				if (condition.size() > 0) {
					condition.put(condition.entrySet().iterator().next().getKey(), "");
				}
				int count = this.attributeService.getCount(condition, field);
				if (order != null && order.length() > 0 & "desc".equals(desc)) {
					order = order + " desc";
				}
				List<Attribute> list = this.attributeService.getList(condition, pageNo, pageSize, order, field);
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("total", count);
				int size = list.size();
				if (size > 0) {
					List<AttributeFont> listFont = new ArrayList<AttributeFont>();
					Attribute re;
					AttributeFont reFont = new AttributeFont(); 
					for (int i = 0; i < size; i++) {
						re = list.get(i);
						BeanUtils.copyProperties(re, reFont);
						listFont.add(reFont);
						reFont = new AttributeFont();
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
}
