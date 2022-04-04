package com.alver.fatefall.api.controllers;

import com.alver.fatefall.api.repositories.CardCollectionRepository;
import com.alver.fatefall.api.services.CardCollectionService;
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
public class CardCollectionController {

    protected CardCollectionRepository cardCollectionRepository;
    protected CardCollectionService cardCollectionService;

    @Autowired
    public CardCollectionController(
            CardCollectionRepository cardCollectionRepository,
            CardCollectionService cardCollectionService) {
        this.cardCollectionRepository = cardCollectionRepository;
        this.cardCollectionService = cardCollectionService;
    }

    @PutMapping()
    public CardCollection save(@RequestBody CardCollection cardCollection) {
        return cardCollectionRepository.save(cardCollection);
    }


    @GetMapping()
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
            @RequestParam("file") MultipartFile file) throws IOException {
        File mseFile = Path.of("imported")
                .resolve(filename)
                .toAbsolutePath()
                .toFile();
        mseFile.mkdirs();
        file.transferTo(mseFile);
        return cardCollectionService.importFromMse(name, mseFile);
    }

}
