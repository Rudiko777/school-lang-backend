package Rudiko.schoollanguages.controller;


import Rudiko.schoollanguages.model.TargetAudience;
import Rudiko.schoollanguages.service.TargetAudienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {
    private final TargetAudienceService targetAudienceService;

    @GetMapping("/unsecured")
    public String unsecuredData(){
        return "Unsecured Data";
    }

    @GetMapping("/secured")
    public String securedData(){
        return "Secured Data";
    }

    @GetMapping("/info")
    public String getInfo(Principal principal){
        return principal.getName();
    }

    @PostMapping("/save/targetAudience")
    public String handleFileUpload(TargetAudience targetAudience){
       targetAudienceService.saveTargetAudience(targetAudience);
       return "redirect:/";
    }

    @GetMapping("/getTargetAudience")
    public List<TargetAudience> products() {
        return targetAudienceService.listProducts();
    }

    @PostMapping("/image/{id}")
    public ResponseEntity<Resource> uploadImage(@PathVariable Long id) {
        try {
            String base64Image = targetAudienceService.getProductById(id).getImage();
            String extension = base64Image.substring(base64Image.indexOf("/") + 1, base64Image.length() - 1);
            String fileName = UUID.randomUUID() + "." + extension;
            byte[] imageBytes = Base64.getDecoder().decode(base64Image.getBytes());
            File file = File.createTempFile(fileName, "." + extension);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(imageBytes);
            fos.close();
            Resource resource = new UrlResource(file.toURI());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
