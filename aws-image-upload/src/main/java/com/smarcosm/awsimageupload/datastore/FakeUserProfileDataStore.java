package com.smarcosm.awsimageupload.datastore;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import com.smarcosm.awsimageupload.profile.UserProfile;

@Repository
public class FakeUserProfileDataStore {
	
	private static final List<UserProfile> USER_PROFILES = new ArrayList<>();
	
	static {
		USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Isadora", null));
		USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Isadora", null));
		
	}

	public List<UserProfile> getUserProfiles() {
		return USER_PROFILES;
	}
}
