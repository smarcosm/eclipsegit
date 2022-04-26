package com.smarcosm.awsimageupload.bucket;

public enum BucketName {
	
	
	PROFILE_IMAGE("smendes-imagem-upload");
	
	private final String bucketName;
	
	
	BucketName(String bucketName) {
		this.bucketName = bucketName;
	}


	public String getBucketName() {
		return bucketName;
	}
	
}
