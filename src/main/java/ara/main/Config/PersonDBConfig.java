package ara.main.Config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "personEntityManagerFactory",
        transactionManagerRef = "personTransactionManager",
        basePackages = {"ara.main.Repositories.UserRepository.PersonRepository"}
)
public class PersonDBConfig {
    @Primary
    @Bean(name = "personDataSource")
    @ConfigurationProperties(prefix = "spring.person.datasource")
    public DataSource personDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "personEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean inventoryEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                                @Qualifier("personDataSource") DataSource personDataSource) {
        return builder
                .dataSource(personDataSource)
                .packages("ara.main.Entity")
                .build();
    }

    @Bean(name = "personTransactionManager")
    public PlatformTransactionManager inventoryTransactionManager(
            @Qualifier("personEntityManagerFactory") EntityManagerFactory personEntityManagerFactory) {
        return new JpaTransactionManager(personEntityManagerFactory);
    }
}
