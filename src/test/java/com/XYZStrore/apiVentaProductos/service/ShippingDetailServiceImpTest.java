package com.XYZStrore.apiVentaProductos.service;

import com.XYZStrore.apiVentaProductos.dto.ShippingDetailDto;
import com.XYZStrore.apiVentaProductos.dto.ShippingDetailSaveDto;
import com.XYZStrore.apiVentaProductos.entities.ShippingDetail;
import com.XYZStrore.apiVentaProductos.mapper.ShippingDetailMapper;
import com.XYZStrore.apiVentaProductos.repository.ShippingDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShippingDetailServiceImpTest {
    @Mock
    private ShippingDetailRepository shippingDetailRepository;

    @Mock
    private ShippingDetailMapper shippingDetailMapper;

    @InjectMocks
    private ShippingDetailServiceImp shippingDetailService;

    ShippingDetail shippingDetail;
    ShippingDetailDto shippingDetailDto;

    @BeforeEach
    void setUp() {
        shippingDetail = new ShippingDetail();
        shippingDetail.setId(1L);
        shippingDetail.setAddress("Address");
        shippingDetail.setConveyor("Conveyor");
        shippingDetail.setNumberGuide(123);

        shippingDetailDto = new ShippingDetailDto(1L, "Address", "Conveyor", 123);
    }

    @Test
    void givenShippingDetailService_whenSaveShippingDetail_thenReturnSavedShippingDetail() {
        when(shippingDetailRepository.save(any())).thenReturn(shippingDetail);

        ShippingDetailSaveDto shippingDetailToSave = new ShippingDetailSaveDto("Address", "Conveyor", 123);

        when(shippingDetailMapper.shippingDetailToShippingDetailDto(any())).thenReturn(shippingDetailDto);

        ShippingDetailDto savedShippingDetail = shippingDetailService.saveShippingDetail(shippingDetailToSave);

        assertThat(savedShippingDetail).isNotNull();
    }

    @Test
    void givenShippingDetailService_whenFindShippingDetailById_thenReturnFoundShippingDetail() {
        when(shippingDetailRepository.findById(any())).thenReturn(Optional.of(shippingDetail));

        when(shippingDetailMapper.shippingDetailToShippingDetailDto(any())).thenReturn(shippingDetailDto);

        ShippingDetailDto foundShippingDetail = shippingDetailService.findShippingDetailById(1L);

        assertThat(foundShippingDetail).isNotNull();
    }

    @Test
    void givenShippingDetailService_whenUpdateShippingDetail_thenReturnUpdatedShippingDetail() {
        when(shippingDetailRepository.findById(any())).thenReturn(Optional.of(shippingDetail));

        ShippingDetailSaveDto shippingDetailToUpdate = new ShippingDetailSaveDto("Updated Address", "Updated Conveyor", 456);

        when(shippingDetailRepository.save(any())).thenReturn(shippingDetail);

        when(shippingDetailMapper.shippingDetailToShippingDetailDto(any())).thenReturn(shippingDetailDto);

        ShippingDetailDto updatedShippingDetail = shippingDetailService.updateShippingDetail(1L, shippingDetailToUpdate);

        assertThat(updatedShippingDetail).isNotNull();
    }

    @Test
    void givenShippingDetailService_whenDeleteShippingDetail_thenVoid() {
        Long shippingDetailId = 1L;
        when(shippingDetailRepository.findById(any())).thenReturn(Optional.of(shippingDetail));
        shippingDetailService.deleteShippingDetailById(shippingDetailId);

        verify(shippingDetailRepository, times(1)).deleteById(any());
    }

    @Test
    void givenShippingDetailService_whenGetAllShippingDetails_thenReturnListOfShippingDetail() {
        List<ShippingDetail> shippingDetails = List.of(shippingDetail);

        when(shippingDetailRepository.findAll()).thenReturn(shippingDetails);

        List<ShippingDetailDto> shippingDetailDtos = shippingDetailService.findAllShippingDetails();

        assertThat(shippingDetailDtos).isNotEmpty();
        assertThat(shippingDetailDtos).hasSize(1);
    }
}