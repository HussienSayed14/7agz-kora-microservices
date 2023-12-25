package com.accountmicroservice.config;

import com.accountmicroservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
@RequiredArgsConstructor
public class AppConfig{

    private final UserRepository userRepository;
    private final Environment environment;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmailOptional(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of("us-east-1"))
                .build();
    }

    /** Amazon S3 Bucket Config*/
    @Bean
    public S3Presigner s3Presigner() {
        String accessKey = environment.getProperty("aws.access.key");
        String secretKey = environment.getProperty("aws.secret.key");
        AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        return S3Presigner.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(()->credentials)
                .build();
    }

}
