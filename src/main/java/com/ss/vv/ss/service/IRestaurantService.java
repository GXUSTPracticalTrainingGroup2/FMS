package com.ss.vv.ss.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.ss.vv.common.IServiceOperations;
import com.ss.vv.ss.domain.Attribute;
import com.ss.vv.ss.domain.Restaurant;


public interface IRestaurantService extends IServiceOperations<Restaurant,Restaurant> {
	public List<Restaurant> getByName(String name);
	
	public Restaurant getByStar(int star);
	
	public List<Restaurant> getListByStar(LinkedHashMap<String, String> condition, int pageNo, int pageSize, String order, String field, int star);

	int deleteByAId(String aId);

	int deleteById(String rId);

	public int Update(Restaurant restaurant);

	public int foodUpdate(Attribute attribute);
	
	public List<Restaurant> getByUname(String uname);
	

}
