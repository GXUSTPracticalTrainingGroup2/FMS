/** 
* 
* @author bingoWu 
* @data 2018年12月23日 00:03:48  
*/  

package com.ss.vv.ss.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ss.vv.common.AbstractService;
import com.ss.vv.common.IOperations;
import com.ss.vv.ss.mapper.ITestMapper;
import com.ss.vv.ss.domain.Test;
import com.ss.vv.ss.service.ITestService;
import com.ss.vv.ss.vo.TestVo;
@Service("testService")
public class TestService extends AbstractService<Test, TestVo> implements ITestService {

	public TestService() {
		this.setTableName("test");
	}
	@Resource
	private ITestMapper testMapper;

	@Override
	protected IOperations<Test, TestVo> getMapper() {
		return testMapper;
	}
	@Override
	public void setTableName(String tableName){
		this.tableName = tableName;;
	}
}

