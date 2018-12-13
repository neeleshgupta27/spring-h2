package com.neel.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Import(
		DBConfig.class
)
@EnableJpaRepositories(
		basePackages = "com.neel.repository",
		transactionManagerRef ="defTx",
		entityManagerFactoryRef ="defEmf"
)
public class TransactionConfig {

	public static final String Transaction_Name = "defTx";
	
	@Bean( name = Transaction_Name)
	public PlatformTransactionManager transactionManager(@Qualifier("defEmf") EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		transactionManager.setGlobalRollbackOnParticipationFailure(true);
		return transactionManager;
	}
}
