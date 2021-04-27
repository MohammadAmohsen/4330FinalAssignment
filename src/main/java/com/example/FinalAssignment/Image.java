package com.example.FinalAssignment;

public class Image {
     private String id;
    private Long createdDate;
    private String s3Url;
    private String fileName;
    private String uploader;

    public Image(String id, Long createdDate, String s3Url, String fileName, String uploader) {
        this.id = id;
        this.createdDate = createdDate;
        this.s3Url = s3Url;
        this.fileName = fileName;
        this.uploader = uploader;

    }


    public String getID() {
        return id;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public String getS3URL() {
        return s3Url;
    }
    public String getFileName() {
        return fileName;
    }

    public String getUploader() {
        return uploader;
    }
}
