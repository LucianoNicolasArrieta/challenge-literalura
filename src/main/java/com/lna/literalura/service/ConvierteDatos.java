package com.lna.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lna.literalura.model.DatosLibro;

public class ConvierteDatos {
    private ObjectMapper mapper = new ObjectMapper();

    public DatosLibro obtenerDatos(String resultadosBusqueda) {
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
}
