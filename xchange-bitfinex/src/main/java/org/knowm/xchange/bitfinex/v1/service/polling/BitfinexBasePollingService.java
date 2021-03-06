package org.knowm.xchange.bitfinex.v1.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitfinex.v1.BitfinexAuthenticated;
import org.knowm.xchange.bitfinex.v1.service.BitfinexHmacPostBodyDigest;
import org.knowm.xchange.bitfinex.v1.service.BitfinexPayloadDigest;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class BitfinexBasePollingService extends BaseExchangeService implements BasePollingService {

  protected final String apiKey;
  protected final BitfinexAuthenticated bitfinex;
  protected final ParamsDigest signatureCreator;
  protected final ParamsDigest payloadCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  public BitfinexBasePollingService(Exchange exchange) {

    super(exchange);

    this.bitfinex = RestProxyFactory.createProxy(BitfinexAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = BitfinexHmacPostBodyDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    this.payloadCreator = new BitfinexPayloadDigest();
  }

}
