package com.example.axon.core

import org.axonframework.config.ConfigurationScopeAwareProvider
import org.axonframework.deadline.DeadlineManager
import org.axonframework.deadline.SimpleDeadlineManager
import org.axonframework.eventhandling.tokenstore.jdbc.TokenSchema
import org.axonframework.modelling.saga.repository.jdbc.SagaSchema
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonConfiguration {

    @Bean
    fun deadlineManager(
        configuration: org.axonframework.config.Configuration,
        transactionManager: org.axonframework.common.transaction.TransactionManager
    ): DeadlineManager {
        return SimpleDeadlineManager.builder()
            .scopeAwareProvider(ConfigurationScopeAwareProvider(configuration))
            .transactionManager(transactionManager)
            .spanFactory(configuration.spanFactory())
            .build()
    }

    @Bean
    fun tokenSchema(): TokenSchema {
        // See https://github.com/AxonFramework/AxonFramework/issues/2074
        return TokenSchema.builder()
            .setTokenTable("token_entry")
            .setProcessorNameColumn("processor_name")
            .setTokenTypeColumn("token_type")
            .build()
    }

    @Bean
    fun sagaSchema(): SagaSchema {
        // See https://github.com/AxonFramework/AxonFramework/issues/2074
        return SagaSchema("saga_entry", "association_value_entry")
    }


}
