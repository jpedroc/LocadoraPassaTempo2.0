package com.jpedro.locadora.resource;

import com.jpedro.locadora.service.AtorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/atores")
@RequiredArgsConstructor
@Slf4j
public class AtorResource {

    private final AtorService atorService;

}
