package com.alver.fatefall.api.server.local;

import com.alver.fatefall.api.CardCollectionApi;
import com.alver.fatefall.api.server.repositories.jpa.CardCollectionRepository;
import com.alver.fatefall.api.server.services.CardCollectionService;
import com.alver.fatefall.api.models.CardCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("card_collection")
public class CardCollectionApiServer implements CardCollectionApi {

    @Autowired
    protected CardCollectionRepository cardCollectionRepository;

    @Autowired
    protected CardCollectionService cardCollectionService;

    @PutMapping
    public CardCollection save(@RequestBody CardCollection cardCollection) {
        return cardCollectionRepository.save(cardCollection);
    }

    @GetMapping
    public List<CardCollection> findAll() {
        return cardCollectionRepository.findAll();
    }

    @GetMapping("/{id}")
    public CardCollection findById(@PathVariable Long id) {
        return cardCollectionRepository.findById(id).get();
    }

    @PostMapping("/importFromMse")
    public CardCollection importFromMse(
            @RequestParam("name") String name,
            @RequestParam("filename") String filename,
            @RequestParam("file") MultipartFile file) {
        File mseFile = Path.of("imported")
                .resolve(filename)
                .toAbsolutePath()
                .toFile();
        mseFile.mkdirs();
        try {
            file.transferTo(mseFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return importFromMse(name, mseFile);
    }

    @Override
    public CardCollection importFromMse(String name, File mseFile) {
        return cardCollectionService.importFromMse(name, mseFile);
    }


}
