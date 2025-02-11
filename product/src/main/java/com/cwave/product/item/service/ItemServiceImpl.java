package com.cwave.product.item.service;

import com.cwave.product.item.entity.Item;
import com.cwave.product.item.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public List<Item> getAllItemList() {
        return null;
    }
}
