package com.elkin.commons.utils;

public abstract class Mapper<E, D> {

    public abstract E fromDto(D dto);

    public abstract D fromEntity(E entity);
}
