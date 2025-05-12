package com.urlshortener.service;

import com.urlshortener.model.UrlMapping;
import com.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;

    private static final String BASE_URL = "https://www";

    @Override
    public UrlMapping createShortUrl(String originalUrl, int expiryDays) {
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortCode(generateShortCode());
        urlMapping.setCreatedAt(LocalDateTime.now());
        urlMapping.setExpiresAt(LocalDateTime.now().plusDays(expiryDays));
        return urlRepository.save(urlMapping);
    }

    @Override
    public UrlMapping getUrlByShortCode(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }

    @Override
    public List<UrlMapping> getAllUrls() {
        return urlRepository.findAll();
    }

    @Override
    public void deleteUrl(String shortCode) {
        UrlMapping urlMapping = getUrlByShortCode(shortCode);
        urlRepository.delete(urlMapping);
    }

    private String generateShortCode() {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        return BASE_URL + "." + uniqueId + ".com";
    }
} 