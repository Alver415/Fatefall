package com.alver.fatefall.api.server.local;

import com.alver.fatefall.api.CardApi;
import com.alver.fatefall.api.CardImageApi;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.models.CardImage;
import com.alver.fatefall.api.server.repositories.CardImageRepository;
import com.alver.fatefall.api.server.repositories.CardRepository;
import com.alver.fatefall.api.server.repositories.FileSystemRepository;
import com.alver.fatefall.api.server.services.CardService;
import com.alver.fatefall.api.server.services.FileLocationService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("card_image")
@ResponseBody
public class LocalCardImageApi implements CardImageApi {

    @Autowired
    protected FileLocationService fileLocationService;

    @PostMapping
    public Long uploadImageFromMultipartFile(@RequestParam MultipartFile multipartImage) throws Exception {
        return fileLocationService.save(multipartImage.getBytes(), multipartImage.getOriginalFilename());
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource downloadImageAsResource(@PathVariable Long id) {
        return fileLocationService.find(id);
    }

    public byte[] downloadImage(Long id) throws IOException {
        return downloadImageAsResource(id).getInputStream().readAllBytes();
    }

}
