package es.omarall.validation.constraints.tests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class TestConfiguration {

    @Bean
    public LocalValidatorFactoryBean getValidatorFactory() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.getValidationPropertyMap()
                .put("hibernate.validator.fail_fast", "true");
        return localValidatorFactoryBean;
    }
}