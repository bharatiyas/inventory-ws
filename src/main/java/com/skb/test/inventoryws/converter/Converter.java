package com.skb.test.inventoryws.converter;

public interface Converter<E, M> {

    M convertEntityToModel(E entity);

    E convertModelToEntity(M model);
}
