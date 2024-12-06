package uz.retail.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import uz.retail.core.repository.slice.SliceBaseRepositoryFactoryBean;

@Configuration
@EnableJpaRepositories(value = {"uz.retail.core.repository"}, repositoryFactoryBeanClass = SliceBaseRepositoryFactoryBean.class)
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class DatabaseConfiguration {

}
