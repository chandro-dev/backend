package ara.main.Config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement

public class DatabaseConfig {

    @Primary
    @Bean(name = "firstDataSource")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().url("jdbc:postgresql://10.0.0.11:5432/personas")
                .username("root").password("root")
                .driverClassName("org.postgresql.Driver").build();
    }

        @Primary
        @Bean(name = "firstEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(
                @Qualifier("firstDataSource") DataSource dataSource) {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource);
            em.setPackagesToScan("ara.main.Entity.personas");
            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            em.setJpaProperties(additionalProperties());
            return em;
        }

        @Primary
        @Bean(name = "firstTransactionManager")
        public PlatformTransactionManager firstTransactionManager(
                @Qualifier("firstEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory);
        }

        // Métodos para configurar la segunda base de datos
        @Bean(name = "secondDataSource")
        public DataSource secondDataSource() {
            return DataSourceBuilder.create().url("jdbc:postgresql://10.0.0.11:5432/inventory")
                    .username("root").password("root")
                    .driverClassName("org.postgresql.Driver").build();
        }

        @Bean(name = "secondEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
                @Qualifier("secondDataSource") DataSource dataSource) {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource);
            em.setPackagesToScan("ara.main.Entity.inventory");
            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            em.setJpaProperties(additionalProperties());
            return em;
        }

        @Bean(name = "secondTransactionManager")
        public PlatformTransactionManager secondTransactionManager(
                @Qualifier("secondEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory);
        }

        // Método ficticio para propiedades adicionales de JPA
        private Properties additionalProperties() {
            Properties properties = new Properties();
            // Aquí puedes agregar propiedades adicionales si es necesario
            return properties;
        }
    }


