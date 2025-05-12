package com.urlshortener.service;

import com.urlshortener.model.UrlMapping;
import com.urlshortener.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
public class UrlShortenerService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    public UrlMapping createShortUrl(String originalUrl) {
        String shortCode = generateShortCode();
        LocalDateTime now = LocalDateTime.now();
        
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortCode(shortCode);
        urlMapping.setCreatedAt(now);
        urlMapping.setExpiresAt(now.plusDays(30)); // URLs expire after 30 days
        
        return urlMappingRepository.save(urlMapping);
    }

    public UrlMapping getOriginalUrl(String shortCode) {
        return urlMappingRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }

    private String generateShortCode() {
        String uuid = UUID.randomUUID().toString();
        String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(uuid.getBytes());
        return encoded.substring(0, 8); // Using first 8 characters as short code
    }
} 