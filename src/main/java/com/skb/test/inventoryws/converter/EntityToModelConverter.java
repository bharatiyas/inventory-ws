package com.skb.test.inventoryws.converter;

public interface EntityToModelConverter<E, M> {

    M convert(E entity);
}
