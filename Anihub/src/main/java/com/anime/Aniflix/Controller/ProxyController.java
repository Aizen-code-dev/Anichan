package com.anime.Aniflix.Controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/stream")
    public ResponseEntity<byte[]> proxyStream(@RequestParam String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");  // optional, to mimic browser
        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity<>(headers), byte[].class);

            HttpHeaders proxyHeaders = new HttpHeaders();
            proxyHeaders.setContentType(MediaType.parseMediaType("application/vnd.apple.mpegurl"));
            proxyHeaders.setAccessControlAllowOrigin("*"); // <-- this fixes CORS
            return new ResponseEntity<>(response.getBody(), proxyHeaders, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

