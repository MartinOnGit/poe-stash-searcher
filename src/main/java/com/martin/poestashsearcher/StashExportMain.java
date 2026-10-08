package com.martin.poestashsearcher;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.martin.poestashsearcher.poeapi.Client;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StashExportMain {

  private final static Logger log = LoggerFactory.getLogger(StashExportMain.class);

  public static void main(String[] args) {
    Properties applicationProperties = new Properties();
    try {
      applicationProperties.load(
          new FileInputStream(StashExportMain.class.getClassLoader().getResource("application.properties").getFile()));
    } catch (IOException e) {
      log.error("Could not log configuration file, shutting down.", e);
      System.exit(1);
    }
    log.info("application properties loaded");

    Client poeApiClient = new Client(applicationProperties.getProperty("poe.account"),
        applicationProperties.getProperty("poe.session.id"));
    log.info("poe api client instanciated");

    poeApiClient.getStashSize()
        .flatMap(stashSize -> Flowable.range(0, stashSize))
        .parallel()
        .runOn(Schedulers.io())
        .flatMap(tabIndex -> poeApiClient.getStashTab(tabIndex))
        .sequential()
        .blockingSubscribe(tab -> log.info(tab.toString()));

    try {
      poeApiClient.close();
    } catch (Exception e) {
      log.error("An error occured while trying to close http client, resources might have leaked.", e);
      System.exit(1);
    }
  }

}
