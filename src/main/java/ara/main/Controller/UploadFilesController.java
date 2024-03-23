package ara.main.Controller;

import ara.main.Dto.Asset;
import ara.main.Service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/images")
@RequiredArgsConstructor
@RestController
public class UploadFilesController {
    @Autowired
    private S3Service s3Services;

    @PostMapping(value = "/Upload")
    public Map<String, String> upload(@RequestParam("file") MultipartFile File){
        String key=s3Services.putObject(File);
        Map<String,String> result=new HashMap<>();
        result.put("key",key);
        result.put("url",s3Services.getObjectUrl(key));
        return result;
    }
    @GetMapping(value = "Get-Object", params = "key")
    ResponseEntity<ByteArrayResource>getObject(@RequestParam String key){
        Asset asset=s3Services.GetObject(key);
        ByteArrayResource resource=new ByteArrayResource(asset.getContent());
        return ResponseEntity.ok()
                .header("Content-Type", asset.getContentType())
                .contentLength(asset.getContent().length)
                .body(resource);
    }
    @DeleteMapping(value = "delete-object", params = "key")
    void deleteObject(@RequestParam String key){
        s3Services.deleteObject(key);
    }
}
