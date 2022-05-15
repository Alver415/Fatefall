package com.alver.fatefall.api.server.services;

import com.alver.fatefall.api.models.CardImage;
import com.alver.fatefall.api.server.repositories.CardImageRepository;
import com.alver.fatefall.api.server.repositories.FileSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class FileLocationService {

    @Autowired
    protected CardImageRepository cardImageRepository;

    @Autowired
    protected FileSystemRepository fileSystemRepository;

    public Long save(byte[] content, String name) throws IOException {
        String location = fileSystemRepository.save(content, name);

        CardImage cardImage = new CardImage();
        cardImage.setName(name);
        cardImage.setLocation(location);
        return cardImageRepository.save(cardImage).getPk();
    }
    public FileSystemResource find(Long id) {
        CardImage cardImage = cardImageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(cardImage.getLocation());
    }
}