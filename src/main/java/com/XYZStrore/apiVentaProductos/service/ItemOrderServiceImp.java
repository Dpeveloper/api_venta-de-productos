package com.XYZStrore.apiVentaProductos.service;

import com.XYZStrore.apiVentaProductos.dto.ItemOrderDto;
import com.XYZStrore.apiVentaProductos.dto.ItemOrderSaveDto;
import com.XYZStrore.apiVentaProductos.entities.ItemOrder;
import com.XYZStrore.apiVentaProductos.exception.ItemOrderNotFoundException;
import com.XYZStrore.apiVentaProductos.mapper.ItemOrderMapper;
import com.XYZStrore.apiVentaProductos.repository.ItemOrderRepository;
import com.XYZStrore.apiVentaProductos.service.interfaces.ItemOrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemOrderServiceImp implements ItemOrderService {

    private final ItemOrderRepository itemOrderRepository;

    private final ItemOrderMapper itemOrderMapper;

    public ItemOrderServiceImp(ItemOrderRepository itemOrderRepository, ItemOrderMapper itemOrderMapper) {
        this.itemOrderRepository = itemOrderRepository;
        this.itemOrderMapper = itemOrderMapper;
    }

    @Override
        public ItemOrderDto saveItemOrder(ItemOrderSaveDto itemOrderSaveDto) {
            ItemOrder itemOrder = itemOrderMapper.itemOrderSaveDtoToItemOrder(itemOrderSaveDto);
            ItemOrder savedItemOrder = itemOrderRepository.save(itemOrder);
            return itemOrderMapper.itemOrderToItemOrderDto(savedItemOrder);
        }

    @Override
    public ItemOrderDto findItemOrderById(Long id) {
        ItemOrder itemOrder = itemOrderRepository.findById(id).orElseThrow(ItemOrderNotFoundException::new);
        return itemOrderMapper.itemOrderToItemOrderDto(itemOrder);
    }

    @Override
    public ItemOrderDto updateItemOrder(Long id, ItemOrderSaveDto itemOrderSaveDto) {
        Optional<ItemOrder> optionalItemOrder = itemOrderRepository.findById(id);

        if (optionalItemOrder.isPresent()) {
            ItemOrder itemOrder = optionalItemOrder.get();

            itemOrder.setQuantity(itemOrderSaveDto.quantity());
            itemOrder.setUnitPrice(itemOrderSaveDto.unitPrice());

            ItemOrder updatedItemOrder = itemOrderRepository.save(itemOrder);
            return itemOrderMapper.itemOrderToItemOrderDto(updatedItemOrder);
        } else {
            throw new ItemOrderNotFoundException("No se encontró el item de la orden con el ID: " + id);
        }
    }
    @Override
    public void deleteItemOrder(Long id) {
        itemOrderRepository.deleteById(id);
    }

    @Override
    public List<ItemOrderDto> findByOrderId(Long orderId) {
        List<ItemOrder> itemOrders = itemOrderRepository.findByOrderId(orderId);
        return itemOrders.stream()
                .map(itemOrderMapper::itemOrderToItemOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemOrderDto> findByProductId(Long productId) {
        List<ItemOrder> itemOrders = itemOrderRepository.findByProductId(productId);
        return itemOrders.stream()
                .map(itemOrderMapper::itemOrderToItemOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getTotalSalesAmountForProduct(Long productId) {
        return itemOrderRepository.getTotalSalesAmountForProduct(productId);
    }
}
