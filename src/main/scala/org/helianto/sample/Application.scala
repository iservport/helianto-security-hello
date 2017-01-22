package org.helianto.sample

import org.helianto.root.RootController
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.oauth2.client.{OAuth2ClientContext, OAuth2RestOperations, OAuth2RestTemplate}
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@SpringBootApplication(scanBasePackages = Array("org.helianto.*.service", "org.helianto.*.controller"))
@EnableJpaRepositories(basePackages = Array("org.helianto.*.repository"))
@EnableOAuth2Sso
@EnableWebSecurity
class Application extends WebMvcConfigurerAdapter {

  @Configuration
  class Config extends WebSecurityConfigurerAdapter {

    override protected def configure(http: HttpSecurity) {
      http.antMatcher("/insecure/**").authorizeRequests.anyRequest.permitAll
    }

  }

  @Bean
  def oauth2RestTemplate(oauth2ClientContext: OAuth2ClientContext, details: OAuth2ProtectedResourceDetails): OAuth2RestTemplate =
    new OAuth2RestTemplate(details, oauth2ClientContext)

  @Bean def rootController(restTemplate: OAuth2RestOperations): RootController =
    new RootController(restTemplate)
}

object Application extends App { SpringApplication.run(classOf[Application]) }
