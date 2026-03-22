package com.zemar_api.produto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.UUID;


@Service
public class SupabaseStorageService {
    @Value("${supabase.url}")
    private String SUPABASE_URL;

    @Value("${supabase.key}")
    private String SERVICE_KEY;

    public String uploadImagem(MultipartFile arquivo) throws Exception {
        String nomeAquivo = UUID.randomUUID() + "-" + arquivo.getOriginalFilename();

        String url = SUPABASE_URL + "/storage/v1/object/produtosImagens/" + nomeAquivo;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(SERVICE_KEY);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        HttpEntity<byte[]> request = new HttpEntity<>(arquivo.getBytes(), headers);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return SUPABASE_URL + "/storage/v1/object/public/produtosImagens/" + nomeAquivo;
    }
}

