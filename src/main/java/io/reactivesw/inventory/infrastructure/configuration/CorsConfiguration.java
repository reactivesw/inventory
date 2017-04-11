package io.reactivesw.inventory.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * CORS config.
 */
@Configuration
public class CorsConfiguration {

  /**
   * Cors configure web mvc configure.
   *
   * @return the web mvc configure
   */
  @Bean
  public WebMvcConfigurer corsConfigure() {
    return new WebMvcConfigurerAdapter() {
      /**
       * add Cors rule.
       * @param registry resigtry
       */
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
      }
    };
  }
}
