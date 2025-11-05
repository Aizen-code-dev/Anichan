//package com.anime.Aniflix.Controller;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@RestController
//public class ImageProxyController {
//
//    private final WebClient webClient = WebClient.create();
//
//    @GetMapping("/image-proxy")
//    public Mono<ResponseEntity<byte[]>> proxyImage(
//            @RequestParam("url") String url,
//            @RequestParam(value = "referer", required = false) String referer) {
//
//        WebClient.RequestHeadersSpec<?> request = webClient
//                .get()
//                .uri(url)
//                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
//                .header("Accept", "image/avif,image/webp,image/apng,image/*,*/*;q=0.8");
//
//        if (referer != null && !referer.isEmpty()) {
//            request = request.header("Referer", referer);
//        }
//
//        return request
//                .retrieve()
//                .bodyToMono(byte[].class)
//                .map(bytes -> ResponseEntity.ok()
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
//                        .body(bytes))
//                .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
//    }
//}
