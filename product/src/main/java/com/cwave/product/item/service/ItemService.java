package com.cwave.product.item.service;

import com.cwave.product.item.entity.Item;
import java.util.List;

public interface ItemService {

    /**
     * 모든 아이템의 목록을 반환
     * @return
     */
    List<Item> getAllItemList();
}
