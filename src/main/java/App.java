import Dao.*;
import com.google.gson.Gson;
import exceptions.ApiException;
import model.Departments;
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
        Sql2oNews newsDao;
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

            if (departmentsFound == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", request.params("id")));
            }

            response.status(200);
            return gson.toJson(departmentsFound);
        });

        delete("/departments/:id", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            Departments departmentsFound = departmentDao.findDepartmentById(departmentId);
            departmentDao.deleteDepartmentById(departmentId);

            if (departmentsFound == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", request.params("id")));
            }

            return gson.toJson(departmentDao.getAllDepartments());
        });

        delete("/departments", "application/json", (request, response) -> {

            departmentDao.clearAllDepartments();

            return "All departments deleted";
        });

        post("/user/new", "application/json", (req, res) -> {
            User user  = gson.fromJson(req.body(), User.class);
            userDao.saveUser(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
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
