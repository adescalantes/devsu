package com.devsu.clientservice.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerIngrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testSaveClient_Ok() throws Exception {
        String jsonClient = "{\n" +
                "    \"nombre\": \"Jose Lema\",\n" +
                "    \"genero\": \"M\",\n" +
                "    \"edad\": 25,\n" +
                "    \"identificacion\": \"1234567ID\",\n" +
                "    \"direccion\": \"Otavalo sn y principal\",\n" +
                "    \"telefono\": \"098254785\",\n" +
                "    \"contrasenia\": \"1234\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonClient))
                .andExpect(status().isCreated());
    }
    
    @Test
    void testSaveClient_BadRequest() throws Exception {
        String jsonClient = "{\n" +
                "    \"nombre\": \"Jose Lema\",\n" +
                "    \"genero\": \"M\",\n" +
                "    \"edad\": 25,\n" +
                "    \"identificacion\": \"1234567ID\",\n" +
                "    \"direccion\": \"Otavalo sn y principal\",\n" +
                "    \"telefono\": \"098254785\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonClient))
                .andExpect(status().isBadRequest());
    }
}