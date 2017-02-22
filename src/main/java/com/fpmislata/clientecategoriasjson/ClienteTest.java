/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.clientecategoriasjson;

import com.fpmislata.domain.Departamento;
import com.fpmislata.domain.Empleado;
import com.fpmislata.domain.PlazaAparcamiento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class ClienteTest {

    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // ***********************
        // *** LISTAR EMPLEADOS
        // ***********************     
        System.out.println("Lista de Empleados del sistema");
        System.out.println("---------------------------");
        List<Empleado> lista = getListEmpleados("http://localhost:8080/RSginerRelacionesJPA-web/api/empleados");
        for (Empleado empleado : lista) {
            System.out.println(gson.toJson(empleado, Empleado.class));
        }
        System.out.println("----------------------------\n");

        // **********************************************
        // *** RECUPERAMOS UN EMPLEADO EN CONCRETO
        // **********************************************
        System.out.println("Recuperando un empleado en concrero del sistema");
        Empleado p = getEmpleado("http://localhost:8080/RSginerRelacionesJPA-web/api/empleados/1");
        System.out.println("El empleado es: " + p.toString());
        System.out.println("----------------------------\n");

        // **********************************************
        // *** AÑADIMOS UN EMPLEADO AL SISTEMA
        // **********************************************        
        Empleado nuevoEmpleado = new Empleado();
        Departamento departamento = new Departamento();
        PlazaAparcamiento plazaAparcamiento = new PlazaAparcamiento();
        plazaAparcamiento.setId(3);
        departamento.setId(1);
        nuevoEmpleado.setDepartamento(departamento);
        nuevoEmpleado.setPlazaAparcamiento(plazaAparcamiento);
        nuevoEmpleado.setNombre("EmpleadoPrueba");

        System.out.println("Insertando un empleado en el sistema");
        Empleado e2 = addEmpleado("http://localhost:8080/RSginerRelacionesJPA-web/api/empleados/", nuevoEmpleado);
        System.out.println("El empleado insertado es: " + e2.getNombre());
        System.out.println("----------------------------\n");

        // **********************************************
        // *** ACTUALIZAMOS UN EMPLEADO EN EL SISTEMA
        // **********************************************        
        Empleado empleadoExistente = new Empleado();
        empleadoExistente.setNombre("empleado existente2");
        System.out.println("Modificando un empleado en el sistema");
        Empleado p3 = updateEmpleado("http://localhost:8080/RSginerRelacionesJPA-web/api/empleados/3", empleadoExistente);
        System.out.println("El empleado modificado es ahora: " + p3.toString());
        System.out.println("----------------------------\n");

        // **********************************************
        // *** BORRAMOS UN EMPLEADO EN EL SISTEMA
        // **********************************************         
        System.out.println("Recuperando una categoria en concrero del sistema");
        deleteCategoria("http://localhost:8080/RSginerRelacionesJPA-web/api/empleados/3");
        System.out.println("La ha sido borrado el empleado 3");
        System.out.println("----------------------------\n");
    }

// Obtenemos la lista de categorias
private static List<Empleado> getListEmpleados(String url) throws IOException {
        // Crea el cliente para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpGet httpGet = new HttpGet(url);
        // Añade las cabeceras al metodo
        httpGet.addHeader("accept", "application/json; charset=UTF-8");
        httpGet.addHeader("Content-type", "application/json; charset=UTF-8");
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpGet);
        // Obtenemos un objeto String como respuesta del response
        String lista = readObject(response);
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Creamos el tipo generico que nos va a permitir devolver la lista a partir del JSON que esta en el String
        Type type = new TypeToken<List<Empleado>>() {
        }.getType();
        // Parseamos el String lista a un objeto con el gson, devolviendo así un objeto List<Categoria>
        return gson.fromJson(lista, type);
    }

//    // Obtenemos una categoria en concreto
    private static Empleado getEmpleado(String url) throws IOException {
        // Crea el cliente para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpGet httpGet = new HttpGet(url);
        // Añade las cabeceras al metodo
        httpGet.addHeader("accept", "application/json; charset=UTF-8");
        httpGet.addHeader("Content-type", "application/json; charset=UTF-8");
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpGet);
        // Obtenemos un objeto String como respuesta del response
        String categoriaString = readObject(response);
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el String categoria a un objeto con el gson, devolviendo así un objeto Categoria
        

return gson.fromJson(categoriaString, Empleado.class
);
    }

    // Añadimos una categoria al sistema
    private static Empleado addEmpleado(String url, Empleado empleado) throws IOException {
        // Crea el cliente para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpPost httpPost = new HttpPost(url);
        // Añade las cabeceras al metodo
        httpPost.addHeader("accept", "application/json; charset=UTF-8");
        httpPost.addHeader("Content-type", "application/json; charset=UTF-8");
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el objeto a String
        String jsonString = gson.toJson(empleado);
        // Construimos el objeto StringEntity indicando que su juego de caracteres es UTF-8
        StringEntity input = new StringEntity(jsonString, "UTF-8");
        // Indicamos que su tipo MIME es JSON
        input.setContentType("application/json");
        // Asignamos la entidad al metodo con el que trabajamos
        httpPost.setEntity(input);
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpPost);

        // Comprobamos si ha fallado
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        String empleadoResult = readObject(response);
        

return gson.fromJson(empleadoResult, Empleado.class
);
    }
//    
//    // Borramos una categoria al sistema
    private static void deleteCategoria(String url) {
        try {
            // Crea el cliente para realizar la conexion
            DefaultHttpClient httpClient = new DefaultHttpClient();
            // Crea el método con el que va a realizar la operacion
            HttpDelete delete = new HttpDelete(url);
            // Añade las cabeceras al metodo
            delete.addHeader("accept", "application/json; charset=UTF-8");
            delete.addHeader("Content-type", "application/json; charset=UTF-8");
            // Invocamos el servicio rest y obtenemos la respuesta
            HttpResponse response = httpClient.execute(delete);
            String status = response.getStatusLine().toString();

            // Comprobamos si ha fallado
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
            }else{
                System.out.println("Se ha eliminado la categoria correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
//    
    private static Empleado updateEmpleado(String url, Empleado empleado) throws IOException {
        // Crea el cliente para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpPut httpPut = new HttpPut(url);
        // Añade las cabeceras al metodo
        httpPut.addHeader("accept", "application/json; charset=UTF-8");
        httpPut.addHeader("Content-type", "application/json; charset=UTF-8");
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el objeto a String
        String jsonString = gson.toJson(empleado);
        // Construimos el objeto StringEntity indicando que su juego de caracteres es UTF-8
        StringEntity input = new StringEntity(jsonString, "UTF-8");
        // Indicamos que su tipo MIME es JSON
        input.setContentType("application/json");
        // Asignamos la entidad al metodo con el que trabajamos
        httpPut.setEntity(input);
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpPut);

        // Comprobamos si ha fallado
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }else{
            System.out.println("La actualización ha ido correcta.");
        }

        // Devolvemos el resultado
        String categoriaResult = readObject(response);
        

return gson.fromJson(categoriaResult, Empleado.class
);
    }

    // Método que nos sirve para la lectura de los JSON
    private static String readObject(HttpResponse httpResponse) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            char[] dataLength = new char[1024];
            int read;
            while ((read = bufferedReader.read(dataLength)) != -1) {
                buffer.append(dataLength, 0, read);
            }
            System.out.println(buffer.toString());
            return buffer.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }    
}
