package com.wicc.bookrental.components;

import com.wicc.bookrental.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Component
public class FileStorageComponent {
    public ResponseDto storeFile(MultipartFile multipartFile) throws IOException {
        String directoryPath=System.getProperty("/home/Chhatra/")+File.separator+"WICC_book_rental_system";
        File directoryFile=new File(directoryPath);
        if(!directoryFile.exists())
        {
            directoryFile.mkdirs();
        }else
        {
            log.info("already exits");
        }
       String ext= FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if(ext.equalsIgnoreCase("jpg")
        || ext.equalsIgnoreCase("png")
        ||ext.equalsIgnoreCase("gif")
        ||ext.equalsIgnoreCase("eps")
        ||ext.equalsIgnoreCase("jpeg"))
        {
            UUID uuid=UUID.randomUUID();
            String filePath="directoryPath +\n" + " File.separator" +
                    " +uuid + \" \" + multipartFile.getOriginalFilename()";
            File fileToStore=new File(filePath);
            multipartFile.transferTo(fileToStore);
            return ResponseDto.builder()
                    .status(true)
                    .message(filePath)
                    .build();
        }else
        {
            return ResponseDto.builder()
                    .status(false)
                    .message("invalid extension")
                    .build();
        }
    }


public String returnFile(String filePath) throws IOException {
    File file=new File(filePath);
    byte[] bytes= Files.readAllBytes(file.toPath());
    String base64EncodedImage= "data:image/png;base64,"+Base64.getEncoder().encodeToString(bytes);
 return base64EncodedImage;
}
}
