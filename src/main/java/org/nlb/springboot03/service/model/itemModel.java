package org.nlb.springboot03.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class itemModel {
    //商品的id

    private Integer id;
    //商品的描述
    private String desc;
    //商品销量
    private Integer sales;
    //商品名称
    @NotBlank(message = "商品名称不能为空")
    private String name;
    //商品价格
    @NotBlank(message = "商品价格不能为空")
    @Min(value = 0,message = "商品的价格不能小于0")
    private BigDecimal price;
    //商品库存
    @NotNull(message = "库存不能不填")
    private Integer storage;
    //商品的图片的url
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
