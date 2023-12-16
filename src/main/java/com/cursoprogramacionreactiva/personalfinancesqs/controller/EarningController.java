package com.cursoprogramacionreactiva.personalfinancesqs.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursoprogramacionreactiva.personalfinancesqs.models.Earning;
import com.cursoprogramacionreactiva.personalfinancesqs.services.EarningSqsService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("earnings")
@AllArgsConstructor
public class EarningController {
  private final EarningSqsService service;

  @PostMapping("/aws")
  public Mono<String> publishEarning(@RequestBody Earning earning) {
    return Mono.just(service.publishQueueMessage(earning));
  }
}
