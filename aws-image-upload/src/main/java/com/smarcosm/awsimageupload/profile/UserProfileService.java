package com.smarcosm.awsimageupload.profile;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserProfileService {

	private final UserProfileDataAccessService userProfileDataAccessService;
	
	
	public UserProfileService(UserProfileDataAccessService userProfileDataAccessService) {
		this.userProfileDataAccessService = userProfileDataAccessService;
		
	}
	
	List<UserProfile> getUserProfiles() {
		return userProfileDataAccessService.getUserProfiles();
	}
	
	public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
				// 1. Verifique se a imagem não está vazia
				// 2. Se o arquivo é uma imagem
				// 3. O usuário existe em nosso banco de dados
				// 4. Pegue alguns metadados do arquivo, se houver
				// 5. Armazene a imagem no s3 e atualize o banco de dados com (userProfileImageLink) link de imagem s3
	}

}
