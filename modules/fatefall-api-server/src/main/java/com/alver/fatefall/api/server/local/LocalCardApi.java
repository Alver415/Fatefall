package com.alver.fatefall.api.server.local;

import com.alver.fatefall.api.CardApi;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.server.repositories.CardRepository;
import com.alver.fatefall.api.server.services.CardService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("card")
@ResponseBody
public class LocalCardApi implements CardApi {

    protected CardRepository cardRepository;
    protected CardService cardService;

    @Autowired
    public LocalCardApi(
            CardRepository cardRepository,
            CardService cardService) {
        this.cardRepository = cardRepository;
        this.cardService = cardService;
    }

    @PutMapping()
    public Card save(@RequestBody Card card) {
        return cardRepository.save(card);
    }

    @GetMapping()
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @GetMapping("/{id}")
    public Card findById(@PathVariable Long id) {
        return cardRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cardRepository.deleteById(id);
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@RequestParam("location") String location) throws IOException {
        return IOUtils.toByteArray(Files.newInputStream(Path.of(location.substring(5))));
    }

    @PostMapping("/generateImage")
    public Card generateImage(@RequestBody Card card) throws IOException {
        return cardService.generateImage(card);
    }

}
