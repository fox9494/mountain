package org.chenll.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by chenlile on 17-3-27.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = org.chenll.config.MybatisSencondConfig.PACKAGE,sqlSessionFactoryRef = "sencondSqlSessionFactory")
public class MybatisSencondConfig {

        // 精确到 master 目录，以便跟其他数据源隔离
        static final String PACKAGE = "org.chenll.dao.sencond";
        static final String MAPPER_LOCATION = "classpath:mapper/sencond/*.xml";

        @Autowired
        private Environment env;


        @Bean(name = "sencondDataSource")
        public DataSource sencondDataSource(){
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
            config.setJdbcUrl(env.getProperty("spring.datasource.url2"));
            config.setUsername(env.getProperty("spring.datasource.username"));
            config.setPassword(env.getProperty("spring.datasource.password"));

            HikariDataSource ds = new HikariDataSource(config);
            return ds;

        }



        @Bean(name = "sencondSqlSessionFactory")
        public SqlSessionFactory sencondSqlSessionFactory(){
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(sencondDataSource());

            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            try {
                bean.setMapperLocations(resolver.getResources("classpath:mapper/sencond/*.xml"));
                return bean.getObject();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

//        @Bean
//        public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//            return new SqlSessionTemplate(sqlSessionFactory);
//        }


        @Bean(value = "sencondTransaction")
        public PlatformTransactionManager sencondTransaction() {
            return new DataSourceTransactionManager(sencondDataSource());
        }


    }
