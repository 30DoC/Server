package com.thirty.api.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.thirty.api.domain.ChatRoom;
import com.thirty.api.domain.ChatVoice;
import com.thirty.api.domain.Member;
import com.thirty.api.response.ChatVoiceResponse;
import com.thirty.api.persistence.ChatRoomRepository;
import com.thirty.api.persistence.ChatVoiceRepository;
import com.thirty.api.persistence.MemberRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ByeongChan on 2018. 2. 1..
 */

@Service
public class ChatVoiceService {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    ChatVoiceRepository chatVoiceRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public PutObjectResult sendVoice(Long roomId, Long registId, MultipartFile files) throws IOException{

        // 파일 이름 읽어오기
        String filename = files.getOriginalFilename();

        ////////////////////
        // AWS S3에 파일 업로드
        ////////////////////
        PutObjectResult putObjectResult = new PutObjectResult();

        // file null 체크 ?

        try {
            putObjectResult = upload(files.getInputStream(), filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ///////////////////
        // DB 저장
        ///////////////////

        ChatRoom chatRoom = chatRoomRepository.findOne(roomId);
        ChatVoice chatVoice = ChatVoice.build(chatRoom.getRoomId(), filename, bucket, registId);

        chatVoiceRepository.save(chatVoice);

        /// 상대방에게 PUSH 알림?

        return putObjectResult;
    }

    private PutObjectResult upload(String filePath, String uploadKey) throws FileNotFoundException {
        return upload(new FileInputStream(filePath), uploadKey);
    }

    private PutObjectResult upload(InputStream inputStream, String uploadKey) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uploadKey, inputStream, new ObjectMetadata());
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);

        IOUtils.closeQuietly(inputStream);

        return putObjectResult;
    }

    public ResponseEntity<byte[]> download(String key) throws IOException {
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);

        S3Object s3Object = amazonS3Client.getObject(getObjectRequest);

        S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        String fileName = URLEncoder.encode(key, "UTF-8").replaceAll("\\+", "%20");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    @Transactional
    public ChatVoiceResponse observeChat(Long roomId, int offset){
        ChatRoom chatRoom = chatRoomRepository.findOne(roomId);
        List<ChatVoice> chatVoiceList = chatRoom.getChatVoiceList();

        List<ChatVoice> resultVoiceList = new ArrayList<>();

        for (int i = offset; i < chatVoiceList.size(); i++) {
            resultVoiceList.add(chatVoiceList.get(i));
        }

        ChatVoiceResponse chatVoiceResponse = ChatVoiceResponse.build(chatVoiceList.size(), resultVoiceList);

        return chatVoiceResponse;
    }


    public List<S3ObjectSummary> list() {
        ObjectListing objectListing = amazonS3Client.listObjects(new ListObjectsRequest().withBucketName(bucket));

        List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();

        return s3ObjectSummaries;
    }
}
