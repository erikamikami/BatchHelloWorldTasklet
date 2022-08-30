package com.example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.tasklet.HelloTasklet;
import com.example.tasklet.HelloTasklet2;

@Configuration
@EnableBatchProcessing
public class HelloTaskletConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("HelloTasklet")
	private HelloTasklet helloTasklet;
	
	@Autowired
	@Qualifier("HelloTasklet2")
	private HelloTasklet2 helloTasklet2;
	
	// パラメータのバリデーションを作成
	@Bean
	public JobParametersValidator defaultValidator() {
		DefaultJobParametersValidator validator = new DefaultJobParametersValidator();
		
		// 必須入力
		String[] requiredKeys = new String[] {"run.id", "require1"};
		validator.setRequiredKeys(requiredKeys);
		
		// オプション
		String[] optionKeys = new String[] {"option1"};
		validator.setOptionalKeys(optionKeys);
		
		// 必須キーとオプションキーの間に重複がないこと確認
		validator.afterPropertiesSet();
		
		return validator;
	}
	
	// TaskletのStepを生成
	@Bean
	public Step HelloTaskletStep() {
		return stepBuilderFactory.get("HelloTaskletStep").tasklet(helloTasklet).build();
	}
	
	// TaskletのStepを生成
	@Bean
	public Step HelloTaskletStep2() {
		return stepBuilderFactory.get("HelloTaskletStep2").tasklet(helloTasklet2).build();
	}
	
	// jobの生成
	@Bean
	public Job HellotaskletJob() {
		return jobBuilderFactory.get("HelloTaskletJob")
								.incrementer(new RunIdIncrementer())
								.start(HelloTaskletStep())
								.next(HelloTaskletStep2())
								.validator(defaultValidator())
								.build();
	}

}
