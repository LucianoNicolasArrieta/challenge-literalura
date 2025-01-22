package com.lna.literalura.service.gutendexapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lna.literalura.model.libro.DatosLibro;
import java.util.ArrayList;
import java.util.List;

public class ConvierteDatos {
    private ObjectMapper mapper = new ObjectMapper();

    public DatosLibro obtenerDatosLibro(String resultadosBusqueda) {
        DatosLibro datosLibro = null;

        try {
            JsonNode resultados = mapper.readTree(resultadosBusqueda);
            JsonNode resultado = resultados.get("results");
            JsonNode primerResultado = resultado.get(0);

            datosLibro = mapper.treeToValue(primerResultado, DatosLibro.class);
            //System.out.println(datosLibro);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return datosLibro;
    }

    public List<DatosLibro> obtenerDatosTop10Libros(String resultadosBusqueda) {
        List<DatosLibro> datosLibroTop10 = new ArrayList<>();

        try {
            JsonNode resultados = mapper.readTree(resultadosBusqueda);
            JsonNode resultado = resultados.get("results");
            for (int i = 0; i <= 9; i++) {
                JsonNode resultadoLibro = resultado.get(i);
                datosLibroTop10.add(mapper.treeToValue(resultadoLibro, DatosLibro.class));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return datosLibroTop10;
    }
}
