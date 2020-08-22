package com.softplayer.apply.infrastructure.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("mapperUtil")
public final class MapperUtil {

    private static final ModelMapper mapper = new ModelMapper();

    public static <TSource, TResult> TResult mapTo(final TSource source, final Class<TResult> destinationType) {
        return mapper.map(source, destinationType);
    }
    public static <TSource, TResult> List<TResult> mapTo(final List<TSource> sourceList, final Class<TResult> destinationType) {

        List<TResult> resultList = new ArrayList<TResult>();
        for (TSource item : sourceList) {
            resultList.add(mapper.map(item, destinationType));
        }

        return resultList;
    }

}
