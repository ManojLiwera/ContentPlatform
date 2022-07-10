package com.example.demo.api;

import com.example.demo.kafka.Article;
import com.example.demo.kafka.ContentProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("api/v1/myarticles")
public class ContentController {

    @PostMapping
    public void publish (@RequestBody Article article){
        log.info("###########ContentController record:{}", article);
        ContentProducer producer = new ContentProducer();
        producer.initialize();
        producer.send(article);
    }
}
