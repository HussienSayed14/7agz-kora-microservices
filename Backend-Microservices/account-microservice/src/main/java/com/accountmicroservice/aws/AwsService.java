package com.accountmicroservice.aws;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.nio.ByteBuffer;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AwsService {

    private final S3Presigner s3Presigner;
    private final S3Client s3Client;

    public String getUserPhoto(String userEmail){
        try {
            final String bucketName = "7agz-kora";
            final String objectPath = "users/" + userEmail +"/ProfilePic.jpg";



            // Create a GetObjectRequest to be pre-signed
            GetObjectRequest getObjectRequest =
                    GetObjectRequest.builder()
                            .bucket(bucketName)
                            .key(objectPath)
                            .build();

            // Create a GetObjectPresignRequest to specify the signature duration
            GetObjectPresignRequest getObjectPresignRequest =
                    GetObjectPresignRequest.builder()
                            .signatureDuration(Duration.ofDays(7))
                            .getObjectRequest(getObjectRequest)
                            .build();

            // Generate the presigned request
            PresignedGetObjectRequest presignedGetObjectRequest =
                    s3Presigner.presignGetObject(getObjectPresignRequest);

            // Log the presigned URL, for example.
            return presignedGetObjectRequest.url().toString();
        } catch (Exception e){
            return null;
        }

    }

    public String uploadUserPhoto(MultipartFile imageFile, String userEmail) {
        try {
            final String bucketName = "7agz-kora";
            final String objectPath = "users/" + userEmail + "/ProfilePic.jpg";

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectPath)
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromByteBuffer(ByteBuffer.wrap(imageFile.getBytes())));
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
