package com.example.gestiondestock.services.impl;

import com.example.gestiondestock.services.FlickrService;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;

@Slf4j
@Service
public class FlickrServiceImpl implements FlickrService {

    //Kan jyt nhb nestaaml fazet l injection lazem naaml fonction trutzrni nafess e type o nzid'ha @Bean
    // o lenna lazem nzid
    @Autowired
    private Flickr flickr;


    @Override
    public String savePhoto(InputStream photo, String title) throws FlickrException {

        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(title);

        String photoId = flickr.getUploader().upload(photo, uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }


}
