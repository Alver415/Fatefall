package com.alver.fatefall.api.server.local;

import com.alver.fatefall.api.CardApi;
import com.alver.fatefall.api.models.Card;
import com.alver.fatefall.api.server.repositories.CardRepository;
import com.alver.fatefall.api.server.services.CardService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@DependsOn("database")
@RestController
@RequestMapping("card")
@ResponseBody
public class CardApiServer implements CardApi {

    protected CardRepository cardRepository;
    protected CardService cardService;

    @Autowired
    public CardApiServer(
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

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@RequestParam("location") String location) throws IOException {
        if (location.startsWith("http")){
            URL imageURL = new URL(location);
             BufferedImage originalImage= ImageIO.read(imageURL);
            ByteArrayOutputStream baos =new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos );
            return baos.toByteArray();
        }
        return IOUtils.toByteArray(Files.newInputStream(Path.of(location.substring(6))));
    }

    @PostMapping("/generateImage")
    public Card generateImage(@RequestBody Card card) throws IOException {
        return cardService.generateImage(card);
    }

}
