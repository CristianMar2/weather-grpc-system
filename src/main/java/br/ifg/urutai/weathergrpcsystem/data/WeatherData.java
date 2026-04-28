package br.ifg.urutai.weathergrpcsystem.data;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherData {
    private final Map<String, List<Double>> data = new HashMap<>();

    public WeatherData() {
        // Goiás
        data.put("Urutai", List.of(25.0, 26.0, 27.0, 28.0, 29.0));
        data.put("Goiânia", List.of(28.0, 29.0, 30.0, 31.0, 32.0));
        data.put("Anápolis", List.of(24.0, 25.0, 26.0, 27.0, 28.0));
        data.put("Pires do Rio", List.of(24.0, 26.0, 26.0, 30.0, 28.0));

        // Sudeste
        data.put("São Paulo", List.of(20.0, 21.0, 22.0, 23.0, 24.0));
        data.put("Rio de Janeiro", List.of(26.0, 27.0, 28.0, 29.0, 30.0));
        data.put("Belo Horizonte", List.of(22.0, 23.0, 24.0, 25.0, 26.0));

        // Sul
        data.put("Curitiba", List.of(18.0, 19.0, 20.0, 21.0, 22.0));
        data.put("Porto Alegre", List.of(19.0, 20.0, 21.0, 22.0, 23.0));

        // Nordeste
        data.put("Salvador", List.of(27.0, 28.0, 29.0, 30.0, 31.0));
        data.put("Fortaleza", List.of(29.0, 30.0, 31.0, 32.0, 33.0));
        data.put("Recife", List.of(28.0, 29.0, 30.0, 31.0, 32.0));

        // Norte
        data.put("Manaus", List.of(30.0, 31.0, 32.0, 33.0, 34.0));
        data.put("Belém", List.of(29.0, 30.0, 31.0, 32.0, 33.0));
    }

    public Map<String, List<Double>> getData() {
        return data;
    }
}
