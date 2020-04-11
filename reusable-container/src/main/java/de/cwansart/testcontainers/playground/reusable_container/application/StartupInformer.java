package de.cwansart.testcontainers.playground.reusable_container.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Startup
@Singleton
public class StartupInformer {

    private static final Logger LOG = LoggerFactory.getLogger(StartupInformer.class);

    @PostConstruct
    public void init() {

        InetAddress localHost = null;

        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        String message = "The application has been started.";
        if (localHost != null) {
            message = String.format("The application on host %s has been started.", localHost.getHostName());
        }

        LOG.info(message);
    }
}
