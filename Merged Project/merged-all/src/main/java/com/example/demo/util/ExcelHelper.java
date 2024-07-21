package com.example.demo.util;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class ExcelHelper {
    private List<String> fieldNames = new ArrayList<>();
    private Workbook workbook = null;
    private String workbookName;
    private String filePath;

    public ExcelHelper(String filePath) {
        this.filePath = filePath;
        initialize();
    }

    private void initialize() {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new IllegalArgumentException("Invalid file type. Only .xls and .xlsx are supported.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean setupFieldsForClass(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return true;
    }

    private Sheet getSheetWithName(String name) {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            if (name.equals(workbook.getSheetName(i))) {
                return workbook.getSheetAt(i);
            }
        }
        return null;
    }

    private void initializeForRead() throws InvalidFormatException, IOException {
        try (InputStream inp = new FileInputStream(filePath)) {
            workbook = WorkbookFactory.create(inp);
        }
    }


    //maybe unused
    private Class<?> getGetterReturnClass(Class<?> clazz, String fieldName) {
        String methodName = "get" + capitalize(fieldName);
        String methodIsName = "is" + capitalize(fieldName);
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName) || method.getName().equals(methodIsName)) {
                return method.getReturnType();
            }
        }
        return null;
    }

    //unused
    @SuppressWarnings("unchecked")
    private Method constructMethod(Class<?> clazz, String fieldName) throws NoSuchMethodException {
        Class<?> fieldClass = getGetterReturnClass(clazz, fieldName);
        return clazz.getMethod("set" + capitalize(fieldName), fieldClass);
    }

    private String capitalize(String string) {
        if (string == null || string.isEmpty()) {
            return string;
        }
        String capital = string.substring(0, 1).toUpperCase();
        return capital + string.substring(1);
    }

    //declare
    public String getWorkbookName() {
        return workbookName;
    }

    public void setWorkbookName(String workbookName) {
        this.workbookName = workbookName;
    }private void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    //take data duoi dang list of map of string
    public List<Map<String, String>> readData() throws IOException {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            List<String> headers = new ArrayList<>();

            if (rowIterator.hasNext()) {
                Row headerRow = rowIterator.next();
                for (Cell cell : headerRow) {
                    headers.add(cell.getStringCellValue());
                }
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                boolean isEmptyRow = true;
                Map<String, String> dataMap = new HashMap<>();

                for (int i = 0; i < headers.size(); i++) {
                    if (fieldNames.contains(headers.get(i))) {
                        Cell cell = row.getCell(i);
                        if (cell != null && cell.getCellType() != CellType.BLANK) {
                            isEmptyRow = false;
                            dataMap.put(headers.get(i), cell.toString());
                        } else {
                            dataMap.put(headers.get(i), "");
                        }
                    }
                }

                if (!isEmptyRow) {
                    dataList.add(dataMap);
                }
            }
        }
        return dataList;
    }

    public static boolean isExcelFile(MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }

        // Check file extension
        String fileName = file.getOriginalFilename();
        if (fileName != null && (fileName.endsWith(".xls") || fileName.endsWith(".xlsx"))) {
            // Check MIME type
            String mimeType = file.getContentType();
            return mimeType != null &&
                    (mimeType.equals("application/vnd.ms-excel") ||
                            mimeType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        }

        return false;
    }
}