package com.XYZStrore.apiVentaProductos.service;

import com.XYZStrore.apiVentaProductos.dto.ShippingDetailDto;
import com.XYZStrore.apiVentaProductos.dto.ShippingDetailSaveDto;
import com.XYZStrore.apiVentaProductos.entities.ShippingDetail;
import com.XYZStrore.apiVentaProductos.enumdetail.Status;
import com.XYZStrore.apiVentaProductos.exception.ShippingDetailNotFoundException;
import com.XYZStrore.apiVentaProductos.mapper.ShippingDetailMapper;
import com.XYZStrore.apiVentaProductos.repository.ShippingDetailRepository;
import com.XYZStrore.apiVentaProductos.service.interfaces.ShippingDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShippingDetailServiceImp implements ShippingDetailService {
    private final ShippingDetailRepository shippingDetailRepository;
    private final ShippingDetailMapper shippingDetailMapper;

    public ShippingDetailServiceImp(ShippingDetailRepository shippingDetailRepository, ShippingDetailMapper shippingDetailMapper) {
        this.shippingDetailRepository = shippingDetailRepository;
        this.shippingDetailMapper = shippingDetailMapper;
    }

    @Override
    public ShippingDetailDto saveShippingDetail(ShippingDetailSaveDto shippingDetailSaveDto) {
        ShippingDetail shippingDetail = shippingDetailMapper.shippingDetailSaveDtoToShippingDetail(shippingDetailSaveDto);
        ShippingDetail savedShippingDetail = shippingDetailRepository.save(shippingDetail);
        return shippingDetailMapper.shippingDetailToShippingDetailDto(savedShippingDetail);
    }

    @Override
    public ShippingDetailDto findShippingDetailById(Long id) {
        ShippingDetail shippingDetail = shippingDetailRepository.findById(id).orElseThrow(ShippingDetailNotFoundException::new);
        return shippingDetailMapper.shippingDetailToShippingDetailDto(shippingDetail);
    }

    @Override
    public ShippingDetailDto updateShippingDetail(Long id, ShippingDetailSaveDto shippingDetailSaveDto) {
        Optional<ShippingDetail> optionalShippingDetail = shippingDetailRepository.findById(id);

        if (optionalShippingDetail.isPresent()) {
            ShippingDetail shippingDetail = optionalShippingDetail.get();

            shippingDetail.setAddress(shippingDetailSaveDto.address());
            shippingDetail.setConveyor(shippingDetailSaveDto.conveyor());
            shippingDetail.setNumberGuide(shippingDetailSaveDto.numberGuide());

            ShippingDetail updatedShippingDetail = shippingDetailRepository.save(shippingDetail);
            return shippingDetailMapper.shippingDetailToShippingDetailDto(updatedShippingDetail);
        } else {
            throw new ShippingDetailNotFoundException("No se encontró el detalle de envío con el ID: " + id);
        }
    }


    @Override
    public void deleteShippingDetailById(Long id) {
        shippingDetailRepository.findById(id).orElseThrow(ShippingDetailNotFoundException::new);
        shippingDetailRepository.deleteById(id);
    }

    @Override
    public List<ShippingDetailDto> findAllShippingDetails() {
        List<ShippingDetail> shippingDetails = shippingDetailRepository.findAll();
        return shippingDetails.stream()
                .map(shippingDetailMapper::shippingDetailToShippingDetailDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShippingDetailDto findByOrderId(Long orderId) {
        ShippingDetail shippingDetail = shippingDetailRepository.findByOrderId(orderId);
        return shippingDetailMapper.shippingDetailToShippingDetailDto(shippingDetail);
    }

    @Override
    public ShippingDetailDto findByConveyor(String conveyor) {
        ShippingDetail shippingDetail = shippingDetailRepository.findByConveyor(conveyor);
        return shippingDetailMapper.shippingDetailToShippingDetailDto(shippingDetail);
    }

    @Override
    public ShippingDetailDto findByOrderStatus(Status status) {
        ShippingDetail shippingDetail = shippingDetailRepository.findByOrderStatus(status);
        return shippingDetailMapper.shippingDetailToShippingDetailDto(shippingDetail);
    }
}
