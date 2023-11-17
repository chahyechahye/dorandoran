package com.doran.bucket;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;

@SpringBootTest
public class BucketTest {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucket;

    @Autowired
    Storage storage;

    @Test
    @DisplayName("버킷 삭제 테스트")
    public void delete() {
        List<BlobId> list = new ArrayList<>();
        list.add(BlobId.of(bucket, "0105619a-7f66-4af3-a6b5-debcfbb0c0e8"));
        list.add(BlobId.of(bucket, "00cbff5b-b5a5-4ac8-bdb6-ddcb6f63b7fe"));

        storage.delete(list);
    }
}
