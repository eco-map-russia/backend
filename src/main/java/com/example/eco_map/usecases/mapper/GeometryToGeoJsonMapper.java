package com.example.eco_map.usecases.mapper;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class GeometryToGeoJsonMapper {

    @Named("toGeoJson")
    public String toGeoJson(Geometry geometry) {
        if (geometry == null) return null;
        GeoJsonWriter writer = new GeoJsonWriter();
        return writer.write(geometry);
    }
}
