package com.smarcosm.awsimageupload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonConfig {
	
	@Bean
	public AmazonS3 s3() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(
<<<<<<< HEAD
			"AKIAQMKCMAN55KA2HWGA",
			"anU0R/DLC7iQ1oDECQ+oI2NPfTxvC0dbl5W9b1cd"
=======
			AWS_ACCESS_KEY_ID;
			AWS_SECRET_KEY;
		
>>>>>>> branch 'master' of https://github.com/SMarcosM/eclipsegit.git
		);
		return AmazonS3ClientBuilder
			.standard()
			.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
			.build();
	}

}
