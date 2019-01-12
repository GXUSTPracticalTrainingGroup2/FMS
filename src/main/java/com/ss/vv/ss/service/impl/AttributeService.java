package com.ss.vv.ss.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ss.vv.common.AbstractService;
import com.ss.vv.common.IOperations;
import com.ss.vv.ss.domain.Attribute;
import com.ss.vv.ss.domain.Restaurant;
import com.ss.vv.ss.mapper.IAttributeMapper;
import com.ss.vv.ss.service.IAttributeService;

@Service("attributeService")
public class AttributeService extends AbstractService<Attribute, Attribute> implements IAttributeService {
	public AttributeService() {
		this.setTableName("attribute_form");
	}
	
	@Resource
	private IAttributeMapper attributeMapper;

	@Override
	protected IOperations<Attribute, Attribute> getMapper() {
		return attributeMapper;
	}
	@Override
	public void setTableName(String tableName){
		this.tableName = tableName;
	}

	public List<Attribute> getByRId(int RId)
	{
		List<Attribute> attribute = attributeMapper.getByRId(RId);
		return attribute;
	}
	
	public  List<Attribute> getByPrice(float price)
	{
		 List<Attribute> attribute = attributeMapper.getByPrice(price);
		return attribute;
	}
	
	public List<Attribute> getListByNameNPrice(LinkedHashMap<String, String> condition, int pageNo, int pageSize, String order, String field, String name, int price)
	{
		 List<Attribute> attribute = getListByNameNPrice(condition ,pageNo, pageSize, order, field, name, price);
		return attribute;
	}
	@Override
	public List<Attribute> getByAname(String aname) {
		 List<Attribute> attribute = attributeMapper.getByAname(aname);
		return attribute;
	}
	@Override
	public List<Attribute> getByRname(String rname) {
		 List<Attribute> attribute = attributeMapper.getByRname(rname);
			return attribute;
	}
}
