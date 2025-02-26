package com.mycompany.student_info.webclient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class StudentWebclient {
    private final WebClient webClient;

    public StudentWebclient(@Qualifier("webClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public String getStudentsAttendance(Long id) {
        return webClient.get().uri("/students/{id}", id)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                .bodyToMono(String.class)
                .block();

    }
}