package com.urlshortener.controller;

import com.urlshortener.model.UrlMapping;
import com.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlMapping> createShortUrl(@RequestBody CreateUrlRequest request) {
        UrlMapping urlMapping = urlService.createShortUrl(request.getOriginalUrl(), request.getExpiryDays());
        return ResponseEntity.ok(urlMapping);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlMapping> getUrlByShortCode(@PathVariable String shortCode) {
        UrlMapping urlMapping = urlService.getUrlByShortCode(shortCode);
        return ResponseEntity.ok(urlMapping);
    }

    @GetMapping
    public ResponseEntity<List<UrlMapping>> getAllUrls() {
        List<UrlMapping> urls = urlService.getAllUrls();
        return ResponseEntity.ok(urls);
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortCode) {
        urlService.deleteUrl(shortCode);
        return ResponseEntity.noContent().build();
    }

    // New endpoint for redirection
    @GetMapping("/r/{shortCode}")
    public RedirectView redirectToOriginalUrl(@PathVariable String shortCode) {
        UrlMapping urlMapping = urlService.getUrlByShortCode(shortCode);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(urlMapping.getOriginalUrl());
        return redirectView;
    }
}

class CreateUrlRequest {
    private String originalUrl;
    private int expiryDays;

    // Getters and setters
    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public int getExpiryDays() {
        return expiryDays;
    }

    public void setExpiryDays(int expiryDays) {
        this.expiryDays = expiryDays;
    }
}



