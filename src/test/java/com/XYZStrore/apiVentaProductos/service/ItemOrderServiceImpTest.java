package com.XYZStrore.apiVentaProductos.service;

import com.XYZStrore.apiVentaProductos.dto.ItemOrderDto;
import com.XYZStrore.apiVentaProductos.dto.ItemOrderSaveDto;
import com.XYZStrore.apiVentaProductos.entities.ItemOrder;
import com.XYZStrore.apiVentaProductos.mapper.ItemOrderMapper;
import com.XYZStrore.apiVentaProductos.repository.ItemOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemOrderServiceImpTest {
    @Mock
    private ItemOrderRepository itemOrderRepository;

    @Mock
    private ItemOrderMapper itemOrderMapper;

    @InjectMocks
    private ItemOrderServiceImp itemOrderService;

    ItemOrder itemOrder;
    ItemOrderDto itemOrderDto;

    @BeforeEach
    void setUp() {
        itemOrder = new ItemOrder();
        itemOrder.setId(1L);
        itemOrder.setQuantity(10);
        itemOrder.setUnitPrice(new BigDecimal("100.00"));

        itemOrderDto = new ItemOrderDto(1L, 10, new BigDecimal("100.00"));
    }

    @Test
    void givenItemOrderService_whenSaveItemOrder_thenReturnSavedItemOrder() {
        when(itemOrderRepository.save(any())).thenReturn(itemOrder);

        ItemOrderSaveDto itemOrderToSave = new ItemOrderSaveDto(1L, 10, new BigDecimal("100.00"));

        when(itemOrderMapper.itemOrderToItemOrderDto(any())).thenReturn(itemOrderDto);

        ItemOrderDto savedItemOrder = itemOrderService.saveItemOrder(itemOrderToSave);

        assertThat(savedItemOrder).isNotNull();
    }

    @Test
    void givenItemOrderService_whenFindItemOrderById_thenReturnFoundItemOrder() {
        when(itemOrderRepository.findById(any())).thenReturn(Optional.of(itemOrder));

        when(itemOrderMapper.itemOrderToItemOrderDto(any())).thenReturn(itemOrderDto);

        ItemOrderDto foundItemOrder = itemOrderService.findItemOrderById(1L);

        assertThat(foundItemOrder).isNotNull();
    }

    @Test
    void givenItemOrderService_whenUpdateItemOrder_thenReturnUpdatedItemOrder() {
        when(itemOrderRepository.findById(any())).thenReturn(Optional.of(itemOrder));

        ItemOrderSaveDto itemOrderToUpdate = new ItemOrderSaveDto(1L, 20, new BigDecimal("200.00"));

        when(itemOrderRepository.save(any())).thenReturn(itemOrder);

        when(itemOrderMapper.itemOrderToItemOrderDto(any())).thenReturn(itemOrderDto);

        ItemOrderDto updatedItemOrder = itemOrderService.updateItemOrder(1L, itemOrderToUpdate);

        assertThat(updatedItemOrder).isNotNull();
    }

    @Test
    void givenItemOrderService_whenDeleteItemOrder_thenVoid() {
        Long itemId = 1L;
        when(itemOrderRepository.findById(any())).thenReturn(Optional.of(itemOrder));
        itemOrderService.deleteItemOrder(itemId);

        verify(itemOrderRepository, times(1)).deleteById(any());
    }

    @Test
    void givenItemOrderService_whenGetAllItemOrders_thenReturnListOfItemOrder() {
        List<ItemOrder> itemOrders = List.of(itemOrder);

        when(itemOrderRepository.findAll()).thenReturn(itemOrders);

        List<ItemOrderDto> itemOrderDtos = itemOrderService.findAllItemOrders();

        assertThat(itemOrderDtos).isNotEmpty();
        assertThat(itemOrderDtos).hasSize(1);
    }
    @Test
    void givenItemOrderService_whenFindByOrderId_thenReturnListOfItemOrder() {
        List<ItemOrder> itemOrders = List.of(itemOrder);

        when(itemOrderRepository.findByOrderId(any())).thenReturn(itemOrders);

        List<ItemOrderDto> itemOrderDtos = itemOrderService.findByOrderId(1L);

        assertThat(itemOrderDtos).isNotEmpty();
        assertThat(itemOrderDtos).hasSize(1);
    }

    @Test
    void givenItemOrderService_whenFindByProductId_thenReturnListOfItemOrder() {
        List<ItemOrder> itemOrders = List.of(itemOrder);

        when(itemOrderRepository.findByProductId(any())).thenReturn(itemOrders);

        List<ItemOrderDto> itemOrderDtos = itemOrderService.findByProductId(1L);

        assertThat(itemOrderDtos).isNotEmpty();
        assertThat(itemOrderDtos).hasSize(1);
    }

    @Test
    void givenItemOrderService_whenGetTotalSalesAmountForProduct_thenReturnTotalSalesAmount() {
        BigDecimal totalSalesAmount = new BigDecimal("1000.00");

        when(itemOrderRepository.getTotalSalesAmountForProduct(any())).thenReturn(totalSalesAmount);

        BigDecimal result = itemOrderService.getTotalSalesAmountForProduct(1L);

        assertThat(result).isEqualByComparingTo(totalSalesAmount);
    }
}

