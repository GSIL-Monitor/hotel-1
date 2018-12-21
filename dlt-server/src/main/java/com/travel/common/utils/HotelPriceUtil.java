package com.travel.common.utils;

import com.travel.common.dto.product.request.PriceDTO;
import com.travel.common.enums.ChannelEnum;
import com.travel.common.exception.DaoException;
import com.travel.hotel.product.dao.ExchangeRatePOMapper;
import com.travel.hotel.product.entity.po.ExchangeRatePO;
import com.travel.hotel.product.entity.po.ExchangeRatePOKey;
import com.travel.hotel.product.entity.po.PricePO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 *   2018/1/24.
 */
public class HotelPriceUtil {

    private static final Logger LOG = LoggerFactory.getLogger(HotelPriceUtil.class);

    private static ExchangeRatePOMapper exchangeRatePOMapper;

    private static ExchangeRatePOMapper getExchangeRatePOMapper() {
        if (null != exchangeRatePOMapper) {
            return exchangeRatePOMapper;
        }
        return exchangeRatePOMapper = (ExchangeRatePOMapper) SpringContextUtil.getBean("exchangeRatePOMapper");
    }

    public static BigDecimal calculatePrice(String targetCurrency, String sourceCurrency, BigDecimal sourcePricce) {
        if (null == targetCurrency || null == sourceCurrency || null == sourcePricce) {
            return new BigDecimal(0.0);
        }

        ExchangeRatePOKey key = new ExchangeRatePOKey(sourceCurrency, targetCurrency);
        ExchangeRatePO exchangeRatePO = getExchangeRatePOMapper().selectByPrimaryKey(key);
        if (null == exchangeRatePO || null == exchangeRatePO.getRate()) {
            LOG.error("没有查询到对应的汇率配置，原币种: " + sourceCurrency + ", 目标币种: " + targetCurrency);
            throw new DaoException("没有查询到对应的汇率配置，原币种: " + sourceCurrency + ", 目标币种: " + targetCurrency);
        }

        if (exchangeRatePO.getRate().compareTo(new BigDecimal(0.0)) < 0) {
            LOG.error("查询到对应的汇率配置小于0，原币种: " + sourceCurrency + ", 目标币种: " + targetCurrency + ", rate: " + exchangeRatePO.getRate());
            throw new DaoException("查询到对应的汇率配置小于0，原币种: " + sourceCurrency + ", 目标币种: " + targetCurrency + ", rate: " + exchangeRatePO.getRate());
        }

        return sourcePricce.multiply(exchangeRatePO.getRate());
    }


    public static PriceDTO getChannelPrice(String channelCode, PricePO pricePO) {

        if (ChannelEnum.B2B.key.equals(channelCode)) {
            return  buildPriceDTO(pricePO.getSaleChannelCurrency(), pricePO.getSaleBPrice());
        }

        if (ChannelEnum.CTRIP.key.equals(channelCode) || ChannelEnum.QUNAR.key.equals(channelCode)) {
            return  buildPriceDTO(pricePO.getSaleChannelCurrency(), pricePO.getCtripPrice());
        }

        if (ChannelEnum.FEIZHU.key.equals(channelCode)) {
            return  buildPriceDTO(pricePO.getSaleChannelCurrency(), pricePO.getTmPrice());
        }

        if (ChannelEnum.XMD.key.equals(channelCode)) {
            return  buildPriceDTO(pricePO.getSaleChannelCurrency(), pricePO.getXmdPrice());
        }

        if (ChannelEnum.TUNIU.key.equals(channelCode)) {
            return  buildPriceDTO(pricePO.getSaleChannelCurrency(), pricePO.getTuniuPrice());
        }

        if (ChannelEnum.JD.key.equals(channelCode)) {
            return  buildPriceDTO(pricePO.getSaleChannelCurrency(), pricePO.getJdPrice());
        }

        if (ChannelEnum.TONGCHENG.key.equals(channelCode)) {
            return  buildPriceDTO(pricePO.getSaleChannelCurrency(), pricePO.getTongchengPrice());
        }

        return null;
    }

    private static PriceDTO buildPriceDTO(String saleChannelCurrency, BigDecimal saleBPrice) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setSaleBCurrency(saleChannelCurrency);
        priceDTO.setSaleBPrice(saleBPrice);
        return priceDTO;
    }

}
