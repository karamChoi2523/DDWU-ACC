package com.example.shortUrl.demo.Repository;

import com.example.shortUrl.demo.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Integer> {
    Optional<Url> findByOriginLink(String url);
}
