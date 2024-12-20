package com.store.back.models.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record SaleDto(
                Integer id,
                LocalDateTime saleDate,
                Integer quantity,
                Double total,
                List<SaleItemDto> saleItems) {
}
