package ru.example.spingdata.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

@Configuration
public class ActiveMqConfig {

    @Bean
    public ConnectionFactory artemisConnectionFactory(
            @Value("${factory.jms.url}")
            String url,
            @Value("${factory.jms.user}")
            String user,
            @Value("${factory.jms.password}")
            String password
    ) {
        return new ActiveMQConnectionFactory(url, user, password);
    }

    @Bean
    public JmsTemplate jmsTemplate(@Qualifier("artemisConnectionFactory") ConnectionFactory activeMQConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public DefaultJmsListenerContainerFactory artemisJmsListenerFactory(@Qualifier("artemisConnectionFactory") ConnectionFactory activeMQConnectionFactory,
                                                                        @Qualifier("artemisTransactionManager") JmsTransactionManager artemisTransactionManager) {
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setTransactionManager(artemisTransactionManager);
        defaultJmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory);
        defaultJmsListenerContainerFactory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        defaultJmsListenerContainerFactory.setSessionTransacted(true);
        return defaultJmsListenerContainerFactory;
    }

    @Bean
    public JmsTransactionManager artemisTransactionManager(@Qualifier("artemisConnectionFactory") ConnectionFactory activeMQConnectionFactory) {
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(activeMQConnectionFactory);
        return jmsTransactionManager;
    }
}
