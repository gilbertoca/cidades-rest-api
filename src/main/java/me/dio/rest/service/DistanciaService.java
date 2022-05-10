package me.dio.rest.service;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;

import java.util.Arrays;
import java.util.List;
import me.dio.rest.entity.Cidade;
import me.dio.rest.repository.CidadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanciaService {

    Logger log = LoggerFactory.getLogger(DistanciaService.class);
    private final CidadeRepository cidadeRepository;

    public DistanciaService(final CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    /**
     * 1st option
     *
     * @param cidade1
     * @param cidade2
     * @param unit
     * @return
     */
    public Double distanciaUsingMath(final Long cidade1, final Long cidade2, final EarthRadius unit) {
        log.info("distanciaUsingMath({}, {}, {})", cidade1, cidade2, unit);
        final List<Cidade> cities = cidadeRepository.findAllById((Arrays.asList(cidade1, cidade2)));

        final Double[] location1 = DistanciaService.transform(cities.get(0).getGeolocation());
        final Double[] location2 = DistanciaService.transform(cities.get(1).getGeolocation());

        return doCalculation(location1[0], location1[1], location2[0], location2[1], unit);
    }

    /**
     * 2nd option
     *
     * @param cidade1
     * @param cidade2
     * @return
     */
    public Double distanciaByPointsInMiles(final Long cidade1, final Long cidade2) {
        log.info("nativePostgresInMiles({}, {})", cidade1, cidade2);
        return cidadeRepository.distanceByPoints(cidade1, cidade2);
    }

    /**
     * 3rd option
     *
     * @param cidade1
     * @param cidade2
     * @param unit
     * @return
     */
    public Double distanciaUsingPoints(final Long cidade1, final Long cidade2, final EarthRadius unit) {
        log.info("distanciaUsingPoints({}, {}, {})", cidade1, cidade2, unit);
        final List<Cidade> cities = cidadeRepository.findAllById((Arrays.asList(cidade1, cidade2)));

        Point p1 = cities.get(0).getLocation();
        Point p2 = cities.get(1).getLocation();

        return doCalculation(p1.getX(), p1.getY(), p2.getX(), p2.getY(), unit);
    }

    /**
     * 4th option
     *
     * @param cidade1
     * @param cidade2
     * @return
     */
    public Double distanciaByCubeInMeters(Long cidade1, Long cidade2) {
        log.info("distanciaByCubeInMeters({}, {})", cidade1, cidade2);
        final List<Cidade> cities = cidadeRepository.findAllById((Arrays.asList(cidade1, cidade2)));

        Point p1 = cities.get(0).getLocation();
        Point p2 = cities.get(1).getLocation();

        return cidadeRepository.distanceByCube(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    private double doCalculation(final double lat1, final double lon1, final double lat2,
            final double lng2, final EarthRadius earthRadius) {
        double lat = toRadians(lat2 - lat1);
        double lon = toRadians(lng2 - lon1);
        double a = sin(lat / 2) * sin(lat / 2)
                + cos(toRadians(lat1)) * cos(toRadians(lat2)) * sin(lon / 2) * sin(lon / 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));

        return earthRadius.getValue() * c;
    }

    public static Double[] transform(String geolocation) {
        String result = geolocation.replace("(", "").replace(")", "");
        String[] strings = result.trim().split(",");
        return new Double[]{Double.valueOf(strings[0]), Double.valueOf(strings[1])};
    }
}
