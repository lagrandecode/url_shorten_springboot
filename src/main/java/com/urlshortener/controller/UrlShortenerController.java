package com.urlshortener.controller;

import com.urlshortener.model.UrlMapping;
import com.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/v1")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlMapping> shortenUrl(@RequestParam @NotBlank String url) {
        UrlMapping urlMapping = urlShortenerService.createShortUrl(url);
        return ResponseEntity.ok(urlMapping);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlMapping> getOriginalUrl(@PathVariable @NotBlank String shortCode) {
        UrlMapping urlMapping = urlShortenerService.getOriginalUrl(shortCode);
        return ResponseEntity.ok(urlMapping);
    }
} 