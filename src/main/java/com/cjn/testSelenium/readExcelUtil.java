package com.cjn.testSelenium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.thread.myTest;

import sun.net.util.IPAddressUtil;

public class readExcelUtil {

	private static String path;
	
	@SuppressWarnings("deprecation")
	public static String[][] readExcel(int sheetIdx){
		
		path ="C:\\Users\\13995\\Desktop\\10月计划及可用账号.xlsx";
		//path ="C:\\Users\\13995\\Desktop\\1.xls";
		String[][] content =null;
		Workbook workbook = null;
		try {
			File file = new File(path);
			FileInputStream in = new FileInputStream(file);
			if (file.exists()) {
				if (file.getName().endsWith("xlsx")) {
					workbook = new XSSFWorkbook(in);
				}else if (file.getName().endsWith("xls")) {
					workbook = new HSSFWorkbook(new NPOIFSFileSystem(in));
				}else
					throw new RuntimeException("该文件不是excel文件");
			}else
				throw new RuntimeException("该文件不存在");
			//获取第n个工作簿
			Sheet sheet = workbook.getSheetAt(sheetIdx);
			//获取第一行
			Row header = sheet.getRow(0);
			content = new String[sheet.getLastRowNum()+1][header.getLastCellNum()];
			for (int rowNum = 1; rowNum <=sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);
				if (row==null)
					continue;
				String value = "";
				for (int cellNum = 0; cellNum <=row.getLastCellNum(); cellNum++) {
					Cell cell = row.getCell(cellNum);
					if(cell==null)
						continue;
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						value = cell.getRichStringCellValue().getString();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							value = cell.getDateCellValue().toString();
						}else {
							value = (long)cell.getNumericCellValue()+"";  //Double.toString((int) cell.getNumericCellValue())
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						value = Boolean.toString(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						value = cell.getCellFormula().toLowerCase();
						break;
					default:
						value = " ";
						System.out.println();
					}
					System.out.println("value:"+value);
					content[rowNum][cellNum] = value;
				}
			}
			//System.out.println("content="+content[1][0]);
			in.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	static boolean isFinish = false;
	private static synchronized String readLine(FileReader reader) throws IOException {
		String line = new BufferedReader(reader).readLine();
		if (line == null) {
			isFinish = true;
		}
		return line;
	}
	
	public static void readSingleLine(int idx) {
		final String path = "C:\\Users\\13995\\Desktop\\new.txt";
		String[] arr = null;
		FileReader fileReader =null;
		myTest.taskThread[idx] =new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				try {
					File file = new File(path);
					FileReader fileReader = new FileReader(file);
					BufferedReader br = new BufferedReader(fileReader);
					while(!isFinish) {
						String line = readLine(fileReader);
						if(line!=null) {
							System.out.println("line"+line);
						}
					}
					br.close();
					fileReader.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		myTest.taskThread[idx].start();
	}
}
