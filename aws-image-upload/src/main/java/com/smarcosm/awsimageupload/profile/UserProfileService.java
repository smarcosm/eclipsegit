package com.smarcosm.awsimageupload.profile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smarcosm.awsimageupload.bucket.BucketName;
import com.smarcosm.awsimageupload.filestore.FileStore;

@Service
public class UserProfileService {

	private final UserProfileDataAccessService userProfileDataAccessService;
	private final FileStore fileStore;
	
	public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
		this.userProfileDataAccessService = userProfileDataAccessService;
		this.fileStore = fileStore;
	}
	
	List<UserProfile> getUserProfiles() {
		return userProfileDataAccessService.getUserProfiles();
	}
	
	public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
				// 1. Verifique se a imagem não está vazia
				if (file.isEmpty()) {
					throw new IllegalStateException("Cannot upload empty file [ "+ file.getSize() + "]");
				}
				
				// 2. Se o arquivo é uma imagem
				if (!Arrays.asList(ContentType.IMAGE_JPEG, ContentType.IMAGE_PNG, ContentType.IMAGE_GIF).contains(file.getContentType())) {
					throw new IllegalStateException("File must be an image");
				}
				
				// 3. O usuário existe em nosso banco de dados
				UserProfile user = userProfileDataAccessService
					.getUserProfiles()
					.stream()
					.filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
					.findFirst()
					.orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
				
				// 4. Pegue alguns metadados do arquivo, se houver
				Map<String, String> metadata = new HashMap<>();
				metadata.put("Content-Type", file.getContentType());
				metadata.put("Content-Length", String.valueOf(file.getSize()));
				
				// 5. Armazene a imagem no s3 e atualize o banco de dados com (userProfileImageLink) link de imagem s3
				String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
				String filename = String.format("%s-%s", file.getName(), UUID.randomUUID());
				
				try {
					fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
	}

}
