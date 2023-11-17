package com.doran.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class StorageConfig {

	@Bean
	public Storage storage() throws IOException {
		ClassPathResource resource = new ClassPathResource("rd-ssafy-project-ebd0eea46d3e.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
		String projectId = "rd-ssafy-project";

		return StorageOptions.newBuilder()
			.setProjectId(projectId)
			.setCredentials(credentials)
			.build().getService();
	}
}
