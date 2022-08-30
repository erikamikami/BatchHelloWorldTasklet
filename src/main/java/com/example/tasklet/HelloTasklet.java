package com.example.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component // TaskletとChunkは、Bean登録が必要
@StepScope // Scopeをつけないと、スコープがシングルトンになる。Stepの開始から終了までインスタンスが存在し、複数のStepを実行したり、並列処理が可能になる。TascletとChunkにはStepScopeをつけよう
@Slf4j
public class HelloTasklet implements Tasklet{ // Taskletインターフェイスを実装すれば、Tascletの処理が作れる

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("Hello, World");
		return RepeatStatus.FINISHED;
	}

}
