package com.personal.portfolio_api.exceptionHandle;

import com.personal.portfolio_api.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class HandleText {

    public void HandleText(String text) {
        if (StringUtils.hasText(text)){
            return;
        }
        throw new BadRequestException("Invalid text");
    }
}
