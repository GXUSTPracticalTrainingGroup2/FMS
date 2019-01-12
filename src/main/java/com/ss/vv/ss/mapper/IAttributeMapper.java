package com.ss.vv.ss.mapper;

import java.util.LinkedHashMap;
import java.util.List;

import com.ss.vv.common.IOperations;
import com.ss.vv.ss.domain.Attribute;


public interface IAttributeMapper extends IOperations<Attribute,Attribute> {
	public List<Attribute> getByRId(int RId);
	
	public List<Attribute> getByPrice(float price);
	
	public  List<Attribute> getByAname(String aname);
	
	public  List<Attribute> getByRname(String rname);
	
	public List<Attribute> getListByNameNPrice(LinkedHashMap<String, String> condition, int pageNo, int pageSize, String order, String field, String name, int price);
	
	
}
