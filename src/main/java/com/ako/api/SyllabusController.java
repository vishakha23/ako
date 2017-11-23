package com.ako.api;

import java.io.File;
import java.lang.Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;

import com.ako.service.S3Service;

/**
 * Syllabus Controller
 * @author Tim
 */

@Controller
@RequestMapping(value="/syllabus", produces=MediaType.APPLICATION_PDF_VALUE)
public class SyllabusController {

    @Autowired
    S3Service s3Service;


    /* File DOWNLOAD */
    @RequestMapping(method=RequestMethod.GET, value="download")
	public
    @ResponseBody
    void downloadSyllabus() {

		s3Service.downloadFile("syllabus.pdf");

	}


    /* File UPLOAD */
    /* TODO: may need to do user-type checking to make sure only the
       instructor is uploading */
    @RequestMapping(method=RequestMethod.POST, value="upload")
    public
    @ResponseBody
    void uploadSyllabus(@RequestParam("syllabus") MultipartFile file) {

        try {
            s3Service.uploadFile("syllabus.pdf", file);

        } catch (Exception ace) {
            System.err.println("SyllabusController ==> UploadSyllabus Error");
        }
    }

}
