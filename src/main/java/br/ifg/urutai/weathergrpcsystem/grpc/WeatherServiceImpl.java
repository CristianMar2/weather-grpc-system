package br.ifg.urutai.weathergrpcsystem.grpc;

import br.ifg.urutai.weathergrpcsystem.data.WeatherData;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import com.weather.grpc.*;
import io.grpc.stub.StreamObserver;

import java.util.List;

@GrpcService
public class WeatherServiceImpl extends WeatherServiceGrpc.WeatherServiceImplBase {

    @Autowired
    private WeatherData weatherData;

    @Override
    public void getCurrentTemperature(CityRequest request,
                                      StreamObserver<TemperatureResponse> responseObserver) {

        double temp = weatherData.getData()
                .getOrDefault(request.getName(), List.of(0.0))
                .get(0);

        responseObserver.onNext(
                TemperatureResponse.newBuilder().setTemperature(temp).build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void getFiveDayForecast(CityRequest request,
                                   StreamObserver<ForecastResponse> responseObserver) {

        List<Double> temps = weatherData.getData().get(request.getName());

        responseObserver.onNext(
                ForecastResponse.newBuilder().addAllTemperatures(temps).build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void listCities(Empty request,
                           StreamObserver<CityListResponse> responseObserver) {

        responseObserver.onNext(
                CityListResponse.newBuilder()
                        .addAllCities(weatherData.getData().keySet())
                        .build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void addCity(AddCityRequest request,
                        StreamObserver<GenericResponse> responseObserver) {

        weatherData.getData().put(
                request.getName(),
                List.of(request.getTemperature())
        );

        responseObserver.onNext(
                GenericResponse.newBuilder()
                        .setMessage("Cidade adicionada")
                        .build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void getStatistics(CityRequest request,
                              StreamObserver<StatisticsResponse> responseObserver) {

        List<Double> temps = weatherData.getData().get(request.getName());

        double avg = temps.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double min = temps.stream().mapToDouble(Double::doubleValue).min().orElse(0);
        double max = temps.stream().mapToDouble(Double::doubleValue).max().orElse(0);

        responseObserver.onNext(
                StatisticsResponse.newBuilder()
                        .setAverage(avg)
                        .setMin(min)
                        .setMax(max)
                        .build()
        );
        responseObserver.onCompleted();
    }
}