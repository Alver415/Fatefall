package com.alver.fatefall.api.server.local;

import com.alver.fatefall.api.CardApi;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.server.repositories.CardRepository;
import com.alver.fatefall.api.server.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/{pk}")
    public Card findById(@PathVariable Long pk) {
        return cardRepository.findById(pk).get();
    }

    @DeleteMapping("/{pk}")
    public void delete(@PathVariable Long pk) {
        cardRepository.deleteById(pk);
    }

    @PostMapping("generateImage")
    public Card generateImage(@RequestBody Card card) throws IOException {
        return cardService.generateImage(card);
    }

}
