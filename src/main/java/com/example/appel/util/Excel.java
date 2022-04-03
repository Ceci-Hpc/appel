package com.example.appel.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public class Excel {

    public static HSSFWorkbook createExcel(String sheetName, List<String> cellNameList) {

        HSSFWorkbook excel = new HSSFWorkbook();
        HSSFSheet sheet = excel.createSheet(sheetName);
        HSSFRow row = sheet.createRow(0);
        int cellIndex = 0;
        for (String cellName : cellNameList) {
            HSSFCell cell = row.createCell(cellIndex);
            cell.setCellValue(cellName);
            cellIndex++;
        }
        return excel;
    }

    public static HSSFWorkbook createExcelData(HSSFWorkbook excel, List<String> excelData, int rowIndex){
        HSSFRow row=excel.getSheetAt(0).createRow(rowIndex);
        for(int i = 0; i < excelData.size(); i++){
            row.createCell(i).setCellValue(excelData.get(i));
        }
        return excel;
    }


}
