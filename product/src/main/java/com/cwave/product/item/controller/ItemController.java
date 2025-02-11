package com.cwave.product.item.controller;

import com.cwave.product.common.template.BaseResponse;
import com.cwave.product.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllItemList() {
        return BaseResponse.ofSuccess(itemService.getAllItemList());
    }

}
