package com.example.demo.helpers;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    //read data in type of object but still unusable
    @SuppressWarnings("unchecked")
    public <T> List<T> readData(String classname) throws Exception {
        initializeForRead();
        Sheet sheet = getSheetWithName(classname);
        Class clazz = Class.forName(workbook.getSheetName(0));

        setupFieldsForClass(clazz);
        List<T> result = new ArrayList<T>();
        Row row;
        for (int rowCount = 1; rowCount < sheet.getPhysicalNumberOfRows(); rowCount++) {
            T one = (T) clazz.getDeclaredConstructor().newInstance();
            row = sheet.getRow(rowCount);
            int colCount = 0;
            result.add(one);
            for (Cell cell : row) {
                String fieldName = fieldNames.get(colCount++);
                Method method = constructMethod(clazz, fieldName);
                CellType cellType = cell.getCellTypeEnum();
                if (cellType == CellType.STRING) {
                    String value = cell.getStringCellValue();
                    Object[] values = new Object[1];
                    values[0] = value;
                    method.invoke(one, value);
                } else if (cellType == CellType.NUMERIC) {
                    Double num = cell.getNumericCellValue();
                    Class<?> returnType = getGetterReturnClass(clazz, fieldName);
                    if (returnType == int.class || returnType == Integer.class) {
                        method.invoke(one, num.intValue());
                    } else if (returnType == double.class || returnType == Double.class) {
                        method.invoke(one, num);
                    } else if (returnType == float.class || returnType == Float.class) {
                        method.invoke(one, num.floatValue());
                    } else if (returnType == long.class || returnType == Long.class) {
                        method.invoke(one, num.longValue());
                    } else if (returnType == Date.class) {
                        Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                        method.invoke(one, date);
                    }
                } else if (cellType == CellType.BOOLEAN) {
                    boolean boolVal = cell.getBooleanCellValue();
                    Object[] values = new Object[1];
                    values[0] = boolVal;
                    method.invoke(one, values);
                }
            }
        }
        return result;
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
    }

    private void setWorkbook(Workbook workbook) {
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
                Map<String, String> dataMap = new HashMap<>();

                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i);
                    if (cell != null) {
                        dataMap.put(headers.get(i), cell.toString());
                    } else {
                        dataMap.put(headers.get(i), "");
                    }
                }
                dataList.add(dataMap);
            }
        }
        return dataList;
    }






}
