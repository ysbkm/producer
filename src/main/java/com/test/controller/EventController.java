package com.test.controller;

import com.test.Response;
import com.test.processor.EventProcessor;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by syerrami.
 * Entry point to file processing
 */

@RestController
public class EventController {


    private static final String template = "File Processed, %s";
    private final AtomicLong counter = new AtomicLong();
    static Logger log = Logger.getLogger(EventController.class.getName());

    @Resource(name = "eventProcessor")
    private EventProcessor eventProcessor;


    @RequestMapping("producer/events")
    public Response loadEvents(@RequestParam(value = "filePath", defaultValue = "/Users/syerrami/code/producer/src/main/resources/source.txt") String filePath) {

        try {
            eventProcessor.process(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error While processing file >>"+ filePath);
            throw new RuntimeException(e);
        }
        return new Response(counter.incrementAndGet(),
                String.format(template, filePath));

    }

}
