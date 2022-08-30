package com.example.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component("HelloTasklet")
@StepScope
@Slf4j
public class HelloTasklet implements Tasklet{
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("Hello Tasklet");
		
		// jobExecutionContextの取得
		ExecutionContext jobExecutionContext = contribution.getStepExecution()
															.getJobExecution()
															.getExecutionContext();
		
		// jobの値をMapに登録
		jobExecutionContext.put("jobKey", "jobValue");
		
		// stepExecutionContextの取得
		ExecutionContext stepExecutionContext = contribution.getStepExecution()
															.getExecutionContext();
		
		// Stepの値をMapに登録
		stepExecutionContext.put("stepKey", "stepValue");
		
		return RepeatStatus.FINISHED;
	}

}
