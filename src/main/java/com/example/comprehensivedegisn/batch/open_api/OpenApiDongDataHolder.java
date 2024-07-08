package com.example.comprehensivedegisn.batch.open_api;


import com.example.comprehensivedegisn.domain.DongEntity;
import com.example.comprehensivedegisn.domain.repository.DongRepository;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OpenApiDongDataHolder {

    private final DongRepository dongRepository;
    private Map<String, Map<String, DongEntity>> dongMap;


    public void init(){
        if(isDataHolderReady()) return;

        dongMap = dongRepository.findAll().stream()
                .collect(Collectors.groupingBy(dongEntity -> dongEntity.getGu().getRegionalCode(),
                        Collectors.toMap(DongEntity::getDongCode, dongEntity -> dongEntity)));
    }

    public boolean isDataHolderReady(){
        return dongMap != null;
    }

    public DongEntity getDongEntity(String regionalCode, String dongCode){
        return dongMap.get(regionalCode).get(dongCode);
    }

}