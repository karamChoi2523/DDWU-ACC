package com.example.shortUrl.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "after_service_seq")
    //@SequenceGenerator(name = "url_seq", sequenceName = "URL_SEQ", allocationSize = 1)
    private Integer id;
    private String originLink;

    public static Url insertUrl(String originLink) {
        return Url.builder().originLink(originLink).build();
    }
}
