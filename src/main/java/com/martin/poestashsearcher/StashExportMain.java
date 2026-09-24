package com.martin.poestashsearcher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.martin.poestashsearcher.poeapi.Client;

import io.reactivex.rxjava3.core.*;

public class StashExportMain {

  final static Client poeApiClient = new Client();

  final static Logger log = LoggerFactory.getLogger(StashExportMain.class);

  public static void main(String[] args) {
    Flowable.fromFuture(poeApiClient.getStashList()).blockingSubscribe(log::info);
  }

}
