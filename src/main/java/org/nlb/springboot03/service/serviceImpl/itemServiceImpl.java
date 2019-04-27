package org.nlb.springboot03.service.serviceImpl;

import org.nlb.springboot03.error.ExceptionMessage;
import org.nlb.springboot03.error.enumError;
import org.nlb.springboot03.object.sales;
import org.nlb.springboot03.service.itemService;
import org.nlb.springboot03.service.model.itemModel;
import org.nlb.springboot03.validata.validataImpl;
import org.nlb.springboot03.validata.validataResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class itemServiceImpl implements itemService {
    @Autowired
    private validataImpl validata;
    @Override
    @Transactional
    public itemModel createItemModel(itemModel item) throws ExceptionMessage {
        //校验入参
        validataResult result=validata.validate(item);
        if(result.isHasError()){
            throw new ExceptionMessage(enumError.PARAMETERS,result.getErrorMessage());
        }
        //itemModel->dataObject
        sales sales = itemModelConvert(item);
        return null;
    }
    //转化的方法
    private sales itemModelConvert(itemModel itemModel){
        if(itemModel==null){
            return null;
        }
        sales sales = new sales();
        BeanUtils.copyProperties(itemModel,sales);
        sales.setPrice(itemModel.getPrice().doubleValue());
        return sales;
    }
    @Override
    public List<itemModel> getItemModel() {
        return null;
    }

    @Override
    public itemModel getItemDesc(Integer id) {
        return null;
    }
}
