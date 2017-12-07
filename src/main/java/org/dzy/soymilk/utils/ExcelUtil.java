package org.dzy.soymilk.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class ExcelUtil {
	
	private static final int DEFAULT_COLUMN_SIZE = 17;

	@SuppressWarnings("deprecation")
	public static void createExcel(String title, JSONArray data, OutputStream out){
		XSSFWorkbook xs = new XSSFWorkbook();   //工作簿
		XSSFSheet sheet = xs.createSheet(title);   //sheet
		
		 /**创建单元格，并设置值表头 设置表头居中 */
		XSSFCellStyle headstyle = createCellHeadStyle(xs);
		XSSFCellStyle cellstyle = createCellStyle(xs);
		XSSFCellStyle titleStyle = xs.createCellStyle();
	    titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	    Font titleFont = xs.createFont();
	    titleFont.setFontHeightInPoints((short) 20);
        titleFont.setBoldweight((short) 700);
        titleStyle.setFont(titleFont);
		
		JSONObject first = data.getJSONObject(0); //第一行
		Set<String> set = first.keySet();
		// 设置表格默认列宽度
        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_SIZE);
        // 合并单元格,标题
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, set.size() - 1));
        // 产生表格标题行
        XSSFRow rowh = sheet.createRow(0);   //表格标题行
        XSSFCell mergedCell = rowh.createCell(0);
        mergedCell.setCellStyle(titleStyle);
        mergedCell.setCellValue(new XSSFRichTextString(title));

		XSSFCell cell;
		
		int rowNum = 1;
		int colNum = 0;
		XSSFRow row1 = sheet.createRow(rowNum);   //第一行
		String[] keys = new String[set.size()];
		//第一行
        for(String key:set){
        	cell = row1.createCell(colNum);
        	cell.setCellValue(key);
        	cell.setCellStyle(headstyle);
        	keys[colNum] = key;
        	colNum++;
        }
        rowNum++;
        //数据行
        for(int i=0; i<data.size();i++){
        	JSONObject json = data.getJSONObject(i);
        	XSSFRow dataRow = sheet.createRow(rowNum);
        	for(int j=0; j<set.size(); j++){
        		XSSFCell newCell = dataRow.createCell(j);
        		newCell.setCellValue(json.getString(keys[j]));
        		newCell.setCellStyle(cellstyle);
        	}
        	rowNum++;
        }
        
        try {
			xs.write(out);
			xs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@SuppressWarnings("deprecation")
	private static XSSFCellStyle createCellHeadStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置边框样式
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        //设置对齐样式
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 生成字体
        Font font = workbook.createFont();
        // 表头样式
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

	
	@SuppressWarnings("deprecation")
	private static XSSFCellStyle createCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置边框样式
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        //设置对齐样式
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 生成字体
        Font font = workbook.createFont();
        // 表头样式
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }
	
	 public static void downloadExcelFile(String title,JSONArray ja,HttpServletResponse response){
	        try {
	            ByteArrayOutputStream os = new ByteArrayOutputStream();
	            createExcel(title,ja,os);
	            byte[] content = os.toByteArray();
	            InputStream is = new ByteArrayInputStream(content);
	            // 设置response参数，可以打开下载页面
	            response.reset();

	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"); 
	            response.setHeader("Content-Disposition", "attachment;filename="+ new String((title).getBytes("gbk"), "iso-8859-1")+ ".xlsx");
	            response.setContentLength(content.length);
	            ServletOutputStream outputStream = response.getOutputStream();
	            BufferedInputStream bis = new BufferedInputStream(is);
	            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
	            byte[] buff = new byte[8192];
	            int bytesRead;
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                bos.write(buff, 0, bytesRead);

	            }
	            bis.close();
	            bos.close();
	            outputStream.flush();
	            outputStream.close();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
}
