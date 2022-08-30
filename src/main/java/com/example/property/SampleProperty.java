package com.example.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * プロパティファイルの値を持つクラス
 * 
 * 任意のプロパティファイルからプロパティを読み取るためには、@PropertySourceと@Valueを使用する
 * 
 * @author mikami
 *
 */
@Component
@PropertySource("classpath:property/sample.properties")
@Getter
@ToString
public class SampleProperty {
	
	@Value("${sample.property}")
	private String sampleProperty;
	
	

}
