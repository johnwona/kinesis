package com.br.notificacao.api.kinesis;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import software.amazon.kinesis.coordinator.Scheduler;

import com.br.notificacao.api.kinesis.consumer.SchedulerBuilder;



@SpringBootApplication
@ComponentScan("com.br.notificacao.api.kinesis.consumer")
public class KinesisApplication {
    
	@Value("${aws.config.accessKey}")
	private static String accessKey;
    
	@Value("${aws.config.secretKey}")
	private static String secretKey;

	@Autowired
	private Environment env;
	
	@Autowired(required=true)
	SchedulerBuilder schedulerBuilder;

	public static void main(String[] args) {
		SpringApplication.run(KinesisApplication.class, args);

		SchedulerBuilder schedulerBuilder = new SchedulerBuilder();
		
		Scheduler scheduler = schedulerBuilder.buildScheduler(accessKey, secretKey);

		try {
			scheduler.run();
		} catch (Exception e) {
			System.out.println("KCL Scheduler app  encountered error: " + e);
		}
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void iniciaScheduler() {
//		System.setProperty("aws.config.accessKey", env.getProperty("aws.config.accessKey"));
//		System.setProperty("aws.config.secretKey}", env.getProperty("aws.config.secretKey}"));

		System.out.println("iniciando scheduler ...");
		
		Scheduler scheduler = this.schedulerBuilder.buildScheduler(accessKey, secretKey);
	
		System.out.println("iniciando a leitura na stream : "+ scheduler.streamName());	
	    
		try {
			scheduler.run();
		}catch(Exception e) {
			System.out.println("Erro ao inicializar KCL ..: " + e.getMessage());
		}
	}
		

	@PostConstruct
	public void setaCredenciais() {

		accessKey = env.getProperty("aws.config.accessKey");
		secretKey = env.getProperty("aws.config.secretKey");

	}
}
