package org.nlb.springboot03.service;

import org.nlb.springboot03.error.ExceptionMessage;
import org.nlb.springboot03.service.model.userModel;

public interface userService {
    userModel getid(int i);

    void register(userModel userModel) throws ExceptionMessage;
}
