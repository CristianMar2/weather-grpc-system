package br.ifg.urutai.weathergrpcsystem.service;

import com.weather.grpc.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherGrpcClient {
    @GrpcClient("weather")
    private WeatherServiceGrpc.WeatherServiceBlockingStub stub;

    public double getTemperature(String city) {
        return stub.getCurrentTemperature(
                CityRequest.newBuilder().setName(city).build()
        ).getTemperature();
    }

    public List<Double> getForecast(String city) {
        return stub.getFiveDayForecast(
                CityRequest.newBuilder().setName(city).build()
        ).getTemperaturesList();
    }

    public List<String> listCities() {
        return stub.listCities(Empty.newBuilder().build())
                .getCitiesList();
    }

    public String addCity(String name, double temp) {
        return stub.addCity(
                AddCityRequest.newBuilder()
                        .setName(name)
                        .setTemperature(temp)
                        .build()
        ).getMessage();
    }

    public Map<String, Double> getStatistics(String city) {

        StatisticsResponse res = stub.getStatistics(
                CityRequest.newBuilder().setName(city).build()
        );

        Map<String, Double> map = new HashMap<>();
        map.put("media", res.getAverage());
        map.put("minima", res.getMin());
        map.put("maxima", res.getMax());

        return map;
    }
}
