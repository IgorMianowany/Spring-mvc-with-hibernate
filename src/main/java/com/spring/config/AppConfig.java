package com.spring.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.spring")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

//
//    @Bean
//    public ComboPooledDataSource dataSource() throws PropertyVetoException {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC");
//        dataSource.setUser("springstudent");
//        dataSource.setPassword("springstudent");
//        dataSource.setMinPoolSize(5);
//        dataSource.setMaxPoolSize(20);
//        dataSource.setMaxIdleTime(30000);
//        return dataSource;
//    }

//    @Bean
//    public DataSource myDataSource() {
//        ComboPooledDataSource myDataSource = new ComboPooledDataSource();
//        try {
//            myDataSource.setDriverClass("com.mysql.jdbc.Driver");
//        }
//        catch (PropertyVetoException exc) {
//            throw new RuntimeException(exc);
//        }
//        logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
//        logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
//        myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
//        myDataSource.setUser(env.getProperty("jdbc.user"));
//        myDataSource.setPassword(env.getProperty("jdbc.password"));
//        myDataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
//        myDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
//        myDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
//        myDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
//        return myDataSource;
//    }

//    @Bean
//    public DataSource securityDataSource() {
//        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
//        try {
//            securityDataSource.setDriverClass(env.getProperty("security.jdbc.driver"));
//        } catch (PropertyVetoException exc) {
//            throw new RuntimeException(exc);
//        }
//        logger.info(">>> security.jdbc.url=" + env.getProperty("security.jdbc.url"));
//        logger.info(">>> security.jdbc.user=" + env.getProperty("security.jdbc.user"));
//        securityDataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
//        securityDataSource.setUser(env.getProperty("security.jdbc.user"));
//        securityDataSource.setPassword(env.getProperty("security.jdbc.password"));
//        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
//        securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
//        securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
//        securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
//        return securityDataSource;
//    }

    @Bean
    public DataSource securityDataSource() {

        // create connection pool
        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        // set the jdbc driver
        try {
            securityDataSource.setDriverClass("com.mysql.jdbc.Driver");
        }
        catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        // for sanity's sake, let's log url and user ... just to make sure we are reading the data
        logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
        logger.info("jdbc.user=" + env.getProperty("jdbc.user"));

        // set database connection props
        securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        securityDataSource.setUser(env.getProperty("jdbc.user"));
        securityDataSource.setPassword(env.getProperty("jdbc.password"));

        // set connection pool props
        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));

        securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));

        securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));

        securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return securityDataSource;
    }

    private int getIntProperty(String propName) {

        String propVal = env.getProperty(propName);

        // now convert to int

        return Integer.parseInt(propVal);
    }


    private Properties getHibernateProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        return props;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new
                LocalSessionFactoryBean();
        sessionFactory.setDataSource(securityDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("com.spring.entity");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//
//        return sessionFactory;
//    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new
                HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }



//    private Properties hibernateProperties() {
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty(
//                "hibernate.show_sql", "true");
//        hibernateProperties.setProperty(
//                "hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//
//        return hibernateProperties;
//    }




}
