package com.test.integration;

import com.test.schemas.Event;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by syerrami
 * For each JSON push to consumer is called for each event
 */
@Component
public class Consumersvc {

    static Logger log = Logger.getLogger(Consumersvc.class.getName());

    public  void pushEvent(Event event) throws Exception
    {
        log.info("Consumer event push invoked");
        if (event != null) {
            final String uri = "http://localhost:8080/consumer/createevent";

            RestTemplate restTemplate = new RestTemplate();
            Event result = null;
            try {
                result = restTemplate.postForObject(uri, event, Event.class);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error while calling Consumer",e);
                throw e;

            }
            log.info("Event sent to Consumer >> "+ result);
        }
    }
}
