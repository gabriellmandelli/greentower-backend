package com.greentower.api.core.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component("mapperUtil")
public final class MapperUtil {

    private static final ModelMapper mapper = new ModelMapper();

    public <TSource, TResult> TResult mapTo(final TSource source, final Class<TResult> destinationType) {
        return mapper.map(source, destinationType);
    }

    public <TSource, TResult> List<TResult> mapTo(final List<TSource> sourceList, final Class<TResult> destinationType) {
        List<TResult> resultList = new ArrayList<>();
        if (Objects.isNull(sourceList)){
            resultList = Collections.emptyList();
        }else{
            for (TSource item : sourceList) {
                resultList.add(mapper.map(item, destinationType));
            }
        }
        return resultList;
    }
}
