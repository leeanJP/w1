package org.zerock.w1.todo.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

public enum MapperUtil {
    INSTACE;

    private ModelMapper modelMapper;

    MapperUtil(){
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(
                        Configuration.AccessLevel.PRIVATE
                ).setMatchingStrategy(MatchingStrategies.STRICT);
        //private으로 선언된 필드도 접근 가능하도록 변경
    }

    public ModelMapper get(){
        return modelMapper;
    }

}
