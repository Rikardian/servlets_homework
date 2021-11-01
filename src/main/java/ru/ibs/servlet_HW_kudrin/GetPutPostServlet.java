package ru.ibs.servlet_HW_kudrin;

import classes.Pet;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet ("/get-put-post-servlet")
public class GetPutPostServlet extends HttpServlet {

    private final Gson gson = new Gson();
    private final static String filePath = "D:\\IBS projects\\servlet_HW_kudrin\\pet.json";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            Pet pet = gson.fromJson(reader, Pet.class);
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            reader.close();
            out.print(pet.toString());
            out.flush();
        } catch (FileNotFoundException ex) {
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            out.print("file not found");
            out.flush();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Pet pet = new Pet("Alex", 12, "Dog");

        try {
            Writer writer = new FileWriter(filePath);
            new Gson().toJson(pet, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Pet pet = new Pet("Sofia", 8, "Cat");

        try {
            Writer writer = new FileWriter(filePath);
            new Gson().toJson(pet, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new File("D:\\IBS projects\\servlet_HW_kudrin\\pet.json");
        file.delete();
    }
}
