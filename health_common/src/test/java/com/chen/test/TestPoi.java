package com.chen.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class TestPoi {

    @Test
    public void readExcel() throws Exception {
        //1.创建工作薄
        Workbook wk = new XSSFWorkbook("E:\\test\\read.xlsx");
        //2.获取工作表
        Sheet sht = wk.getSheetAt(0);
        //3.遍历工作表获取行对象
        for (Row row : sht) {
            //4.遍历对象获取单元格
            for (Cell cell : row) {
                //5.获取单元格的类型
                int cellType = cell.getCellType();
                if (cellType == Cell.CELL_TYPE_NUMERIC){
                    //读取数值
                    System.out.println(cell.getNumericCellValue());
                }else {
                    //6.不同类型读取的方法不同，读取值
                    System.out.println(cell.getStringCellValue());
                }
                System.out.println(",");
            }
            System.out.println();
        }
        //7.关闭工作薄
        wk.close();

    }


    @Test
    public void readExcel2() throws IOException {
        //创建工作薄
        XSSFWorkbook workbook = new XSSFWorkbook("E:\\test\\read.xlsx");
        //获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
        XSSFSheet sheetAt = workbook.getSheetAt(0);
        //获取当前工作表的最后一行的行号，行号从0开始
        int lastRowNum = sheetAt.getLastRowNum();
        for (int i = 0; i < lastRowNum; i++) {
            //根据行号获取对象
            XSSFRow row = sheetAt.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for (short j=0; j<lastCellNum;j++){
                String value = row.getCell(j).getStringCellValue();
                System.out.println(value);
            }
        }
        workbook.close();
    }


    @Test
    public void writeExcel() throws IOException {
        //1.创建工作薄，不需要指定文件名
        Workbook workbook = new XSSFWorkbook();
        //2.创建工作表
        Sheet sheet = workbook.createSheet("测试");
        //3.创建行对象,工作表，行，单元格的下标都是从0开始
        Row row = sheet.createRow(0);
        //4.创建单元格
        //5.给单元格赋值
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("年龄");
        row.createCell(2).setCellValue("地址");

        row = sheet.createRow(1);
        row.createCell(0).setCellValue("小明");
        row.createCell(1).setCellValue(20);
        row.createCell(2).setCellValue("贵阳");

        row = sheet.createRow(2);
        row.createCell(0).setCellValue("小花");
        row.createCell(1).setCellValue(30);
        row.createCell(2).setCellValue("北京");

        //6.保存到本地文件
        workbook.write(new FileOutputStream(new File("E:\\test\\write.xlsx")));

        //7.关闭工作薄
        workbook.close();

    }

}
