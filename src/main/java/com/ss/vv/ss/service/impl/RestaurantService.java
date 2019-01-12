package com.ss.vv.ss.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ss.vv.common.AbstractService;
import com.ss.vv.common.IOperations;
import com.ss.vv.ss.domain.Attribute;
import com.ss.vv.ss.domain.Restaurant;
import com.ss.vv.ss.domain.User;
import com.ss.vv.ss.mapper.IRestaurantMapper;
import com.ss.vv.ss.service.IRestaurantService;

@Service("restaurantService")
public class RestaurantService extends AbstractService<Restaurant, Restaurant> implements IRestaurantService {
	public RestaurantService() {
		this.setTableName("restaurant_form");
	}
	
	@Resource
	private IRestaurantMapper restaurantMapper;

	@Override
	protected IOperations<Restaurant, Restaurant> getMapper() {
		return restaurantMapper;
	}
	@Override
	public void setTableName(String tableName){
		this.tableName = tableName;
	}
	
	public Restaurant getByStar(int star)
	{
		Restaurant restaurant = restaurantMapper.getByStar(star);
		return restaurant;
	}
	
	public List<Restaurant> getListByStar(LinkedHashMap<String, String> condition, int pageNo, int pageSize, String order, String field, int star)
	{
		 List<Restaurant> restaurant = getListByStar(condition ,pageNo, pageSize, order, field, star);
		return restaurant;
	}
	@Override
	public int deleteByAId(String aId) {
		int del = restaurantMapper.deleteByAId(aId);
		return del;
    }
	@Override
	public int deleteById(String rId) {
		int del = restaurantMapper.deleteById(rId);
		return del;
    }
	public int Update(Restaurant restaurant){
        return restaurantMapper.Update(restaurant);
    }  
	public int foodUpdate(Attribute attribute){
        return restaurantMapper.foodUpdate(attribute);
    }
	@Override
	public List<Restaurant> getByName(String name) {
		List<Restaurant> restaurant = restaurantMapper.getByName(name);
		return restaurant;
	}
	@Override
	public List<Restaurant> getByUname(String uname) {
		List<Restaurant> restaurant = restaurantMapper.getByUname(uname);
		return restaurant;
	}
}
