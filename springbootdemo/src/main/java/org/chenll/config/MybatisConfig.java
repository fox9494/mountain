package org.chenll.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * Created by chenlile on 17-3-16.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = MybatisConfig.PACKAGE,sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MybatisConfig  {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "org.chenll.dao.master";
    static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

    @Autowired
    private Environment env;


    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        config.setJdbcUrl(env.getProperty("spring.datasource.url"));
        config.setUsername(env.getProperty("spring.datasource.username"));
        config.setPassword(env.getProperty("spring.datasource.password"));

        HikariDataSource ds = new HikariDataSource(config);
        return ds;

    }



    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(masterDataSource());

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/master/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
//
//    @Bean   使用@MapperScan可以不用配置这个
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

    @Bean
    @Primary
    public PlatformTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

}
