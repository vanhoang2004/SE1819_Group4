package com.example.demo;

import com.example.demo.controller.ManagerController;
import com.example.demo.entity.Chapter;
import com.example.demo.entity.Subject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {
			SpringApplication.run(DemoApplication.class, args);
//		Path path = Paths.get("src/main/resources/static/questionTemplate.xlsx");
//
//		// Mở file Excel có sẵn
//		FileInputStream fileIn = new FileInputStream(path.toFile());
//		XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
//		XSSFSheet sheet = workbook.getSheetAt(0);
//		// Ghi các giá trị drop-down vào một vùng riêng biệt
//		String[] dropDownValues = {"Option1", "Option2", "Option3"};
//		int rowIndex = 0;
//
//
//		// Tạo Data Validation cho cột A
//		DataValidationHelper validationHelper = sheet.getDataValidationHelper();
//		CellRangeAddressList addressList = new CellRangeAddressList(1,100 , 9, 9); // Tạo drop-down cho cột B từ hàng 1 đến hàng 1001
//		DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(new String[]{"Option1", "Option2", "Option3"});
//		DataValidation dataValidation = validationHelper.createValidation(constraint, addressList);
//		dataValidation.setSuppressDropDownArrow(true);
//		sheet.addValidationData(dataValidation);
//
//		fileIn.close();
//
//		// Ghi workbook vào file có sẵn trong thư mục static
//		try (FileOutputStream fileOut = new FileOutputStream(path.toFile())) {
//			workbook.write(fileOut);
//		}
//		workbook.close();


//		MockQuestionRepository mockquestion = null;
//		List<MockQuestion> mockQuestions = mockquestion.mockDetails(5);
//		MockQuestionKey key = new MockQuestionKey();
//		List<Integer> listID = new ArrayList<>();
//		for (MockQuestion mockQuestion : mockQuestions) {
//			Integer qID= mockQuestion.getQuestion().getId();
//			listID.add(qID);
//			key.setMocktestid(mockQuestion.getMockTest().getId());
//			key.setQuestionid(mockQuestion.getQuestion().getId());
//			System.out.println("+++++"+key.toString()+"++++++");
//			//mockquestion.deleteById(key);
//		}
//
//		for(Integer i: listID){
//			System.out.println("Questionid: "+i+"\n");

	//	createExcel();
}
	public static  void createExcel() throws IOException {
		Path path = Paths.get("src/main/resources/static/questionTemplate.xlsx");
		Path path1=Paths.get("/target/classes/static/questionTemplate.xlsx");
		// Mở file Excel có sẵn
		FileInputStream fileIn = new FileInputStream(path1.toFile());
		XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
		// lấy sheet đầu tiên
		XSSFSheet sheet = workbook.getSheetAt(0);
//    List<Chapter> listChapter = chapter.findChapterBySubject(sub.getSubjectId());
		List<Chapter> listChapter = new ArrayList<>();

//    String[] listChapterArr = new String[listChapter.size()];
		String[] listChapterArr = new String[5];
		listChapterArr[0]= "ID1";
		listChapterArr[1]= "ID2";
		listChapterArr[2]= "ID3";
		listChapterArr[3]= "ID4";

//    for(int i=0;i< listChapter.size();i++){
////       listChapterArr[i]=listChapter.get(i).getName();
//
//   }

		// tao data
		DataValidationHelper validationHelper = sheet.getDataValidationHelper();
		// tạo dropdown
		// firstRow: hàng bắt đầu dropdown
		//firstCol: ghi cột nào để cột đấy
		// lastCol: giống firstCol
		int maxRows = 1048576;
		CellRangeAddressList addressList = new CellRangeAddressList(0, 10000, 9, 9);

		// taọ ràng buộc cho cột dropdown
		DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(listChapterArr);
		DataValidation dataValidation = validationHelper.createValidation(constraint, addressList);
		dataValidation.setSuppressDropDownArrow(true);
		sheet.addValidationData(dataValidation);

		fileIn.close();

		// Ghi workbook vào file có sẵn trong thư mục static
		try (FileOutputStream fileOut = new FileOutputStream(path1.toFile())) {
			workbook.write(fileOut);
		}
		workbook.close();
		System.out.println(listChapterArr.length);
	}
}

