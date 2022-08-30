package com.example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing // SpringBatchの設定クラスであること明示
public class BatchConfig {
	
	// jobを生成する
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	// stepを生成する
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private Tasklet helloTasklet;
	
	// stepを生成する
	@Bean
	public Step taskletStep1() {
		return stepBuilderFactory.get("HelloTaskletStep1") // 引数にstepの名前を設定。これがDBに登録され、いつどのstepが実行されたのか確認できるようになる
								.tasklet(helloTasklet) // stepをtaskletに設定する。Taskletインターフェイスを実装したクラスを引数に。
								.build();
	}
	
	@Bean
	public Job taskletJob() {
		return jobBuilderFactory.get("HelloWorldTaskletJob") // 引数にjobの名前を設定。これがDBに登録され、いつどのjobが実行されたのか確認できるようになる
								.incrementer(new RunIdIncrementer()) // jobのIDをインクリメントさせるクラスを指定。主キーとなるjobIDを重複させないためにインクリメンターが必要
								.start(taskletStep1()) // 最初に実行するStepを指定する
								.build();
	}
	
}
