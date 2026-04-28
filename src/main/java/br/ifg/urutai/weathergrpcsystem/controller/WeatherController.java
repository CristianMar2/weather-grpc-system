package br.ifg.urutai.weathergrpcsystem.controller;

import br.ifg.urutai.weathergrpcsystem.service.WeatherGrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class WeatherController {
    @Autowired
    private WeatherGrpcClient client;

    @PostMapping("/cidade")
    public String add(@RequestBody Map<String, Object> body) {
        return client.addCity(
                body.get("nome").toString(),
                Double.parseDouble(body.get("temperatura").toString())
        );
    }

    @GetMapping("/cidades")
    public List<String> list() {
        return client.listCities();
    }

    @GetMapping("/temperatura")
    public double temp(@RequestParam String cidade) {
        return client.getTemperature(cidade);
    }

    @GetMapping("/previsao")
    public List<Double> prev(@RequestParam String cidade) {
        return client.getForecast(cidade);
    }

    @GetMapping("/estatisticas")
    public Map<String, Double> stats(@RequestParam String cidade) {
        return client.getStatistics(cidade);
    }
}