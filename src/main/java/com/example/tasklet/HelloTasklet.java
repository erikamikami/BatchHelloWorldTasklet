package com.example.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component("HelloTasklet") // 同じインターフェイスを実装するBeanを複数用意する場合、区別できるように名前を付ける
@StepScope
@Slf4j
public class HelloTasklet implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("Hello, Tasklet!!!!!!!");
		
		// jobExecutionContextの取得
		ExecutionContext jobContext = contribution.getStepExecution()
													.getJobExecution()
													.getExecutionContext();
		
		// Mapに値を登録
		jobContext.put("jobKey", "jobValue");
		
		// stepExecutionContextの取得
		ExecutionContext stepContext = contribution.getStepExecution()
													.getExecutionContext();
		
		// Mapに値を登録
		stepContext.put("stepKey", "stepValue");
		
		return RepeatStatus.FINISHED;
	}

}
