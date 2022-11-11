package dev.georgebarker.endpoint;

import jakarta.websocket.server.ServerEndpointConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SpringWebSocketConfigurator
        extends ServerEndpointConfig.Configurator
        implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringWebSocketConfigurator.applicationContext = applicationContext;
    }
}
