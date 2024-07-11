package com.example.shortUrl.demo.service;

import com.example.shortUrl.demo.Repository.UrlRepository;
import com.example.shortUrl.demo.domain.Url;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    public Url insertUrl(String url) {
        Url originUrl = Url.builder()
                .originLink(url)
                .build();
        return urlRepository.save(originUrl);
    }

    public Optional<Url> findByOriginLink(String url) {
        return urlRepository.findByOriginLink(url);
    }
}
