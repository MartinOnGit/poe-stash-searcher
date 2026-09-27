package com.martin.poestashsearcher;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.martin.poestashsearcher.poeapi.Client;

import io.reactivex.rxjava3.core.*;

public class StashExportMain {

  final static Logger log = LoggerFactory.getLogger(StashExportMain.class);

  static Client poeApiClient;

  public static void main(String[] args) {
      Properties applicationProperties = new Properties();
      try {
        applicationProperties.load(new FileInputStream(StashExportMain.class.getClassLoader().getResource("application.properties").getFile()));
      } catch (IOException e) {
        log.error("Could not log configuration file, shutting down.", e);
        System.exit(1);
      }
    log.info("application properties loaded");
    poeApiClient =  new Client(applicationProperties.getProperty("poe.account"), applicationProperties.getProperty("poe.session.id"));
    log.info("poe api client instanciated");
    Flowable.fromFuture(poeApiClient.getStashTab(0)).blockingSubscribe(tab -> {log.info(tab.toString());});
  }

}
