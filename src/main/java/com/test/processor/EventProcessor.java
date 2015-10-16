package com.test.processor;


import com.test.integration.Consumersvc;
import com.test.schemas.Event;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * Created by syerrami
 * Event Process read the file and call Consumer for each request
 */

@Component
public class EventProcessor {


    @Resource(name = "consumersvc")
    private Consumersvc consumersvc;

    static Logger log = Logger.getLogger(EventProcessor.class.getName());

    public void process(String filePath) throws Exception{

        log.info("Event Process Started");
        ObjectMapper mapper = new ObjectMapper();

        log.info("File to Read >> " + filePath);
        File file = new File(filePath);

        Event event = null;

        try {
            List<String> lines = FileUtils.readLines(file);

            log.info("Read to loop data ");
            for(String element:lines)
            {
                JsonNode node = mapper.readTree(element);
                event = mapper.treeToValue(node, Event.class);
                consumersvc.pushEvent(event);
                log.info("Event Sent to Consumer "+ event );
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error During file Process",e);
            throw e;
        }
    }
}
