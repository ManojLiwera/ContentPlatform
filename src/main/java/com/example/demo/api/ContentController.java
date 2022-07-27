package com.example.demo.api;

import com.example.demo.kafka.Article;
import com.example.demo.kafka.ContentProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/myarticles")
public class ContentController {

    @PostMapping
    public void publish (@RequestBody Article article){
        log.info("###########ContentController record:{}", article);
        article.setStatus("Create");
        ContentProducer producer = new ContentProducer();
        producer.initialize();
        producer.send(article);
    }

    @PostMapping("/{id}")
    public void update(@RequestBody Article article, @PathVariable String id) {
        ContentProducer producer = new ContentProducer();
        producer.initialize();
        producer.send(article);

    }

//    @DeleteMapping("/{id}")
//    void deleteArticle(@PathVariable String id) {
//        repository.deleteById(id);
//    }

}
