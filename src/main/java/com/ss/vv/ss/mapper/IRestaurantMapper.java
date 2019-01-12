package com.ss.vv.ss.mapper;

import java.util.LinkedHashMap;
import java.util.List;

import com.ss.vv.common.IOperations;
import com.ss.vv.ss.domain.Attribute;
import com.ss.vv.ss.domain.Restaurant;

public interface IRestaurantMapper extends IOperations<Restaurant,Restaurant> {
	
	public List<Restaurant> getByName(String name);

	public Restaurant getByStar(int star);
	
	public List<Restaurant> getListByStar(LinkedHashMap<String, String> condition, int pageNo, int pageSize, String order, String field, int star);

	public int deleteByAId(String aId);

	public int deleteById(String rId);

	public int Update(Restaurant restaurant);

	public int foodUpdate(Attribute attribute);
	
	public List<Restaurant> getByUname(String uname);
}
