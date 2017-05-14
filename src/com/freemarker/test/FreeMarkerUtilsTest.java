package com.freemarker.test;

import java.util.Map;

import org.junit.Test;

import com.freemarker.datastrc.DataService;
import com.freemarker.datastrc.DataServiceImpl;
import com.freemarker.utils.DataBaseService;
import com.freemarker.utils.DataBaseServiceImpl;
import com.freemarker.utils.FreeMarkerUtils;

public class FreeMarkerUtilsTest {

	@Test
	public void testFreeMarkerUtils(){
		
		String tableName = null;
		char fieldSeparator = '_';
		String packageName = "com.freemarker.demo";
		String className = null;
		String templateName = "deom.ftl";
		String outFilePath = "D:\\demo\\";
		String fileName = null;
		
		DataService ds = new DataServiceImpl();
		DataBaseService dbs = new DataBaseServiceImpl();
		FreeMarkerUtils freeMarkerUtils = new FreeMarkerUtils();
		Map<String, String> tableNames = dbs.getAllTableNames(fieldSeparator);
		System.out.println(tableNames.toString());
		System.out.println("共有表的个数为："+tableNames.size());
		int i = 0;
		for(Map.Entry<String, String> entry : tableNames.entrySet()){
			i++;
			tableName = entry.getKey();
			className = entry.getValue();
			fileName = className + ".java";

			Map<String, Object> templateData = ds.getTemplateData(tableName, fieldSeparator, packageName, className);
			freeMarkerUtils.generateFile(templateName, templateData, outFilePath+fileName);
			System.out.println("当前为第"+i+"张表,表名为："+tableName);
		}
	}
}
