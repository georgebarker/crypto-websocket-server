package dev.georgebarker.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * I am a @Configuration class that hooks into the end of the context initialization process of the server in order to perform dependency injection.
 */
@Slf4j
@WebListener
@ComponentScan(basePackages = "dev.georgebarker")
@Configuration
public class SpringConfiguration implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Received ServletContextEvent={}, creating dev.georgebarker.config.SpringConfiguration...", sce);

        var applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        log.info("dev.georgebarker.config.SpringConfiguration created with applicationContext={}.", applicationContext);
    }
}
