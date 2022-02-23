package org.example.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Request {
    private static HttpURLConnection con;

    public static StringBuilder get(String endpoint) throws IOException {
        var url = "http://localhost:8080/api/v1/" + endpoint;
        StringBuilder content;

        try {

            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");


            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {

                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());
        } finally {

            con.disconnect();
        }
        return content;
    }

    public static StringBuilder post(String endpoint, Object payload) throws IOException {
        var url = "http://localhost:8080/api/v1/"+endpoint;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //converto da oggetto a stringa
        String postData = objectMapper.writeValueAsString(payload);
        StringBuilder content;
        System.out.println(postData);

        try {

            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");

            //setto il payload richiesta HTTP
            OutputStream outputStream = con.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write(postData);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            outputStream.close();

            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());

        } finally {

            con.disconnect();
        }
        return content;
    }

    public static StringBuilder put(String endpoint) throws IOException {
        var url = "http://localhost:8080/api/v1/" + endpoint;
        StringBuilder content;

        try {

            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("PUT");


            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {

                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());
        } finally {

            con.disconnect();
        }
        return content;
    }

    public static StringBuilder put(String endpoint, Object payload) throws IOException {
        var url = "http://localhost:8080/api/v1/"+endpoint;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //converto da oggetto a stringa
        String postData = objectMapper.writeValueAsString(payload);
        StringBuilder content;
        System.out.println(postData);

        try {

            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");

            //setto il payload richiesta HTTP
            OutputStream outputStream = con.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write(postData);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            outputStream.close();

            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());

        } finally {

            con.disconnect();
        }
        return content;
    }
}


