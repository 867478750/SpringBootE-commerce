package org.nlb.springboot03.service;

import org.nlb.springboot03.error.ExceptionMessage;
import org.nlb.springboot03.service.model.itemModel;

import java.util.List;

public interface itemService {
    //创建商品
    itemModel createItemModel(itemModel item) throws ExceptionMessage;
    //商品列表浏览
    List<itemModel> getItemModel();

    //商品详情浏览
    itemModel getItemDesc(Integer id);
}
