package com.urlshortener.service;

import com.urlshortener.model.UrlMapping;
import java.util.List;

public interface UrlService {
    UrlMapping createShortUrl(String originalUrl, int expiryDays);
    UrlMapping getUrlByShortCode(String shortCode);
    List<UrlMapping> getAllUrls();
    void deleteUrl(String shortCode);
} 