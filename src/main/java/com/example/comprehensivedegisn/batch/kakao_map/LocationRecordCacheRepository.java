package com.example.comprehensivedegisn.batch.kakao_map;


import com.example.comprehensivedegisn.batch.CacheRepository;
import com.example.comprehensivedegisn.batch.kakao_map.dto.LocationRecord;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Slf4j
public class LocationRecordCacheRepository implements CacheRepository<String, LocationRecord> {

    private final Map<String, LocationRecord> locationRecordMap = new ConcurrentHashMap<>();

    @Override
    public void save(String location, LocationRecord apartmentGeoRecord) {

        if(location == null) return;
        locationRecordMap.put(location, apartmentGeoRecord);
    }

    @Override
    public Optional<LocationRecord> findByKey(String location) {
        return location != null && locationRecordMap.containsKey(location) ? Optional.of(locationRecordMap.get(location)) : Optional.empty();
    }

    @Override
    public LocationRecord computeIfAbsent(String location, Function<String, LocationRecord> function) {
        return locationRecordMap.computeIfAbsent(location, function);
    }
}