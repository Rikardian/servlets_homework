package ru.ibs.servlet_HW_kudrin;

import classes.Pet;
import com.google.gson.Gson;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/helloServlet")
public class HelloServlet extends HttpServlet {

    private final Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        Pet pet = new Pet(
                "Alex",
                12,
                "Dog");
        String petJsonString = this.gson.toJson(pet);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(petJsonString);
        out.flush();
    }

}