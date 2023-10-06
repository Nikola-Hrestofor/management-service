package ru.raiffeisen.custody.example.spingdata.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.camunda.bpm.engine.RuntimeService
import org.springframework.jms.annotation.JmsListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import ru.raiffeisen.custody.example.spingdata.ManagementUtils
import ru.raiffeisen.custody.example.spingdata.dto.FactoryDto
import java.math.BigDecimal
import java.util.logging.Logger
import javax.jms.TextMessage
import kotlin.math.log

@Component
class FactoryListener(private val runtimeService: RuntimeService,
    val jmsObjectMapper: ObjectMapper) {
    companion object {
        val logger = Logger.getLogger(ProcessService::class.java.name)
    }

    @JmsListener(containerFactory = "artemisJmsListenerFactory",
        destination = "\${factory.jms.queue-in}")
    fun onMessage(@Headers messageHeaders: MessageHeaders,
                  message: TextMessage
    ){
        logger.info("message ${message.text} , headers $messageHeaders")
        val processId = messageHeaders["processId"].toString()
        val subscription = runtimeService.createEventSubscriptionQuery()
            .processInstanceId(processId)
            .eventType("message")
            .eventName("production")
            .singleResult()

        val enroll = jmsObjectMapper.readValue(message.text, FactoryDto::class.java)
        runtimeService.setVariable(processId, "enrollStock", enroll.stock)
        runtimeService.messageEventReceived(subscription.eventName, subscription.executionId)
    }

}