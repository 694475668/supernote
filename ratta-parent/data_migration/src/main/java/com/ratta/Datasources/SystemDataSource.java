package com.ratta.Datasources;

/*
                                           _ooOoo_
                                          o8888888o
                                          88" . "88
                                          (| -_- |)
                                          O\  =  /O
                                       ____/`---'\____
                                     .'  \\|     |//  `.
                                    /  \\|||  :  |||//  \
                                   /  _||||| -:- |||||-  \
                                   |   | \\\  -  /// |   |
                                   | \_|  ''\---/''  |   |
                                   \  .-\__  `-`  ___/-. /
                                 ___`. .'  /--.--\  `. . __
                              ."" '<  `.___\_<|>_/___.'  >'"".
                             | | :  `- \`.;`\ _ /`;.`/ - ` : | |
                             \  \ `-.   \_ __\ /__ _/   .-` /  /
                        ======`-.____`-.___\_____/___.-`____.-'======
                                           `=---='
                        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                                 佛祖保佑       永无BUG
*/
/**
* Title:程序的奥秘
* Description 目标数据源:
* Company:  
* @author: 刘 明
* @date:Created in 2019年7月22日 下午1:55:05
* @version : 
*/

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
/**
 * 注册到springboot容器中
 * @author 刘明
 */
@Configuration
@MapperScan(basePackages = "com.ratta.target.system.dao", sqlSessionFactoryRef = "test2SqlSessionFactory")
public class SystemDataSource {
	@Bean(name = "test2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.test2")
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "test2SqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		// bean.setMapperLocations(
		// new
		// PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test2/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "test2TransactionManager")
	public DataSourceTransactionManager testTransactionManager(@Qualifier("test2DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "test2SqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}