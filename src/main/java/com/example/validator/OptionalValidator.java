package com.example.validator;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class OptionalValidator implements JobParametersValidator{

	@Override
	public void validate(JobParameters parameters) throws JobParametersInvalidException {
		// パラメータを取得
		String key = "option1";
		String option1 = parameters.getString(key);
		
		// 数値型かどうかチェック
		try {
			Integer.parseInt(option1);
		} catch (NumberFormatException e) {
			String errorMessage = "Not Number:value=" + option1;
			throw new JobParametersInvalidException(errorMessage);
		}
		
	}

}
