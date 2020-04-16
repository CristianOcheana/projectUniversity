package com.project.university.userCheck;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

@Configuration
public class CheckEmailValidator implements CheckEmail {

//    @Override
//    public boolean checkEmailIfExists(String input) {
//
//        Path path = Paths.get("D:\\projectUniversity\\src\\main\\resources\\config\\emailValidation");
//
//        List<String> line = null;
//        try {
//            line = Files.readAllLines(path);
//        } catch (IOException e) {
//            System.out.println("no read");
//            e.printStackTrace();
//        }
//
//        for (String index : line) {
//            if (input.equals(index)) {
//                return true;
//            }
//        }
//        return false;
//
//    }

    @Override
    public boolean checkEmailIfExists(String input) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(getClass().getClassLoader().getResource("config/StudentList.xlsx").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            Cell cell = row.getCell(2);
            if (cell.getStringCellValue().equals(input)) {
                return true;
            }
        }
        return false;
    }

//    @Override
//    public boolean checkEmailIfExists(String input) {
//
//
//        Connection con = null;
//        try {
//            con = DriverManager.getConnection("jdbc:h2:file:./DB/testDB", "root", "");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        Statement statement = null;
//        try {
//            statement = con.createStatement();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        ResultSet resultSet = null;
//        try {
//            resultSet = statement.executeQuery("SELECT EMAIL FROM STUDENT ");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        while (true) {
//            try {
//                if (!resultSet.next()) break;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (resultSet.getString(1).equals(input)) {
//                    return true;
//
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        try {
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
