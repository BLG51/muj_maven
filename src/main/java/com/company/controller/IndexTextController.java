package com.company.controller;

import com.company.model.User;
import com.company.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/gettext")
public class IndexTextController extends HttpServlet {
    Gson gson = new Gson();

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject json = new Gson().fromJson(req.getReader(), JsonObject.class);
        String text = json.get("text").getAsString();
        text+= " changed";
        PrintWriter pw = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, String> data = new HashMap<>();
        data.put("text",text);
        String str = new Gson().toJson(data);
        pw.print(str);
        pw.flush(); pw.close();
    }
}

