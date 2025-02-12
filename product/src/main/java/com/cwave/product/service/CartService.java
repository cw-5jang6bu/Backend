package com.cwave.product.service;

import com.cwave.product.domain.entity.ProductEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CartService {
    List<ProductEntity> getAllCartItemListByMemberId(@PathVariable("memberId") Long memberId);
}
