import Dao.*;
import com.google.gson.Gson;
import exceptions.ApiException;
import model.Departments;
import model.GeneralNews;
import model.User;
import spark.Spark;
import spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        Sql2oDepartment departmentDao = new Sql2oDepartment();
        Sql2oUser userDao = new Sql2oUser();
        Sql2oDepartmentNews departmentNewsDao = new Sql2oDepartmentNews();
        Sql2oGeneralNews generalNewsDao = new Sql2oGeneralNews();
        Sql2oNews newsDao = new Sql2oNews();
        Gson gson = new Gson();

        post("/departments/new", "application/json", (req, res) -> {
            Departments departments = gson.fromJson(req.body(), Departments.class);
            departmentDao.saveDepartment(departments);
            res.status(201);
            return gson.toJson(departments);
        });

        get("/departments", "application/json", (request, response) -> {
            response.status(200);
            return gson.toJson(departmentDao.getAllDepartments());
        });

        get("/departments/:id", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            Departments departmentsFound = departmentDao.findDepartmentById(departmentId);

            if (departmentsFound == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", request.params("id")));
            }

            response.status(200);
            return gson.toJson(departmentsFound);
        });

        delete("/departments/:id", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            Departments departmentsFound = departmentDao.findDepartmentById(departmentId);

            if (departmentsFound == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", request.params("id")));
            }

            departmentDao.deleteDepartmentById(departmentId);
            return gson.toJson(departmentDao.getAllDepartments());
        });

        delete("/departments", "application/json", (request, response) -> {

            departmentDao.clearAllDepartments();

            return "All departments deleted";
        });

        post("/users/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            Departments departmentsFound = departmentDao.findDepartmentById(user.getDepartmentId());

            if (departmentsFound == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }

            userDao.saveUser(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
        });

        get("/users", "application/json", (request, response) -> {
            response.status(200);
            return gson.toJson(userDao.getAllUsers());
        });

        get("/users/:id", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            User userFound = userDao.findUserById(userId);

            if (userFound == null) {
                throw new ApiException(404, String.format("No user with the id: \"%s\" exists", request.params("id")));
            }

            response.status(200);
            return gson.toJson(userFound);
        });

        delete("/user/:id", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            User userFound = userDao.findUserById(userId);

            if (userFound == null) {
                throw new ApiException(404, String.format("No user with the id: \"%s\" exists", request.params("id")));
            }

            userDao.deleteUserById(userId);
            return gson.toJson(departmentDao.getAllDepartments());
        });

        delete("/users", "application/json", (request, response) -> {
            userDao.clearUsers();

            return "All users deleted";
        });

        post("/generalNews/new", "application/json", (req, res) -> {
            GeneralNews generalNews = gson.fromJson(req.body(), GeneralNews.class);
            generalNewsDao.saveGeneralNews(generalNews);
            res.status(201);
            return gson.toJson(generalNews);
        });

        get("/generalNews", "application/json", (request, response) -> {
            response.status(200);
            return gson.toJson(generalNewsDao.getAllGeneralNews());
        });

        get("/generalNews/:id", "application/json", (request, response) -> {
            int generalNewsId = Integer.parseInt(request.params("id"));
            GeneralNews generalNewsFound = generalNewsDao.getGeneralNewsById(generalNewsId);

            if (generalNewsFound == null) {
                throw new ApiException(404, String.format("No general news with the id: \"%s\" exists", request.params("id")));
            }

            response.status(200);
            return gson.toJson(generalNewsFound);
        });

        delete("/generalNews/:id", "application/json", (request, response) -> {
            int generalNewsId = Integer.parseInt(request.params("id"));
            GeneralNews generalNewsFound = generalNewsDao.getGeneralNewsById(generalNewsId);

            if (generalNewsFound == null) {
                throw new ApiException(404, String.format("No general news with the id: \"%s\" exists", request.params("id")));
            }

            newsDao.deleteNewsById(generalNewsId);
            return gson.toJson(generalNewsDao.getAllGeneralNews());
        });

        delete("/generalNews", "application/json", (request, response) -> {
            generalNewsDao.clearAllGeneralNews();
            return "All general news was deleted deleted";
        });


        after(((request, response) -> {
            response.type("application/json");
        }));

        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });
    }
}
