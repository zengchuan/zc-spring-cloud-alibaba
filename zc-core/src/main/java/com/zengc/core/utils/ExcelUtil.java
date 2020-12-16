package com.zengc.core.utils;

import com.zengc.core.exception.ZCException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EagleStrike on 2017/11/11.
 */
@Description("Excel文件读取操作工具类")
public class ExcelUtil {

    // 日志记录对象
    private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    public static List<List<String>>  parseExcel(MultipartFile file) throws Exception {
        checkFile(file);
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<List<String>> list = new ArrayList<>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    //默认添加最后一列导入状态
                    //lastCellNum++;
                    List<String> cells = new ArrayList<>();
                    //循环当前行
                    for(int cellNum = firstCellNum; cellNum < 5;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        cells.add( getCellValue(cell));
                    }
                    list.add(cells);
                }
            }
            workbook.close();
        }
        return list;
    }
    public static List<List<String>>  parseExcel(InputStream file) throws Exception {
        //checkFile(file);
        Workbook  workbook = new XSSFWorkbook(file);;
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<List<String>> list = new ArrayList<>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getLastCellNum();
                    //默认添加最后一列导入状态

                    List<String> cells = new ArrayList<>();
                    //循环当前行
                    for (int i = firstCellNum;i<lastCellNum;i++){
                        Cell cell = row.getCell(i);
                        cells.add( getCellValue(cell));
                    }
                    list.add(cells);
                }
            }
            workbook.close();
        }
        return list;
    }

    public static void chinese2pinyin(List<List<String>> list){
        if (list !=null){
            final int num = list.size();
            for (int i =0;i<num;i++){
                System.out.println(i);
                List<String> list1 = list.get(i);
                String s1 = list1.get(0);
                String s2 = list1.get(1);
                String s3 = list1.get(2);
                String s3s [] = s3.split("\\n");
                if (s3s!=null){
                    String s3s0[] = s3s [0].trim().split("-");
                    for (int j = 0;j<s3s0.length;j++){
                        list1.add(s3s0[j]);
                    }
                    list1.remove(2);
                    for (int j = 1;j<s3s.length;j++){
                        List<String> list2 = new ArrayList<>();
                        String s3sj[] = s3s [j].trim().split("-");
                        list2.add(s1);
                        list2.add(s2);
                        for (int k = 0;k<s3sj.length;k++){
                            list2.add(s3sj[k]);
                        }
                        list.add(list2);
                    }
                }
                /*String s2=Chinese2PinyinUtil.cn2Spell(s1);
                String s3=Chinese2PinyinUtil.cn2FirstSpell(s1);
                list1.add(s2);
                list1.add(s3);*/
            }
        }
    }

    private static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == CellType.NUMERIC){
            cell.setCellType(CellType.STRING);
        }
        //判断数据的类型
        switch (cell.getCellType().getCode()){
            case 0: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case 1: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case 4: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case 2: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case 3: //空值
                cellValue = "";
                break;
            case 5: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
    private static void checkFile(MultipartFile file) throws ZCException {
        //判断文件是否存在
        if(null == file){
            log.error("文件不存在！");
            throw new ZCException(2,"文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if( !fileName.endsWith(".xls")&&!fileName.endsWith(".xlsx")){
            log.error(fileName + "不是excel文件");
            throw new ZCException(3,fileName + "不是excel文件");
        }
    }
    private static void checkFile(File file) throws ZCException {
        //判断文件是否存在
        if(null == file){
            log.error("文件不存在！");
            throw new ZCException(2,"文件不存在！");
        }
        //获得文件名
        String fileName = file.getName();
        //判断文件是否是excel文件
        if( !fileName.endsWith(".xls")&&!fileName.endsWith(".xlsx")){
            log.error(fileName + "不是excel文件");
            throw new ZCException(3,fileName + "不是excel文件");
        }
    }
    private static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            String fileSuffix = file.getOriginalFilename();
            if (fileName.endsWith(".xls")){
                workbook = new HSSFWorkbook(is);
            }else if (fileName.endsWith(".xlsx")){
                workbook = new XSSFWorkbook(is);
            }

        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return workbook;
    }
    private static Workbook getWorkBook(File file) {
        //获得文件名
        String fileName = file.getName();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            //InputStream is = file();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            String fileSuffix = file.getName();
            if (fileName.endsWith(".xls")){
                workbook = new HSSFWorkbook(new FileInputStream(file));
            }else if (fileName.endsWith(".xlsx")){
                workbook = new XSSFWorkbook(file);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return workbook;
    }
    /**
     * @description: 生成Excel HSSFWorkbook
     * @author dingzd
     * @date 2019/5/21 10:27
     */
    public static XSSFWorkbook getXSSFWorkbook(String sheetName,String []title,List<List<String>> values){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件

        XSSFWorkbook wb = new XSSFWorkbook();

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        XSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

        //声明列对象
        XSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //创建内容
        for(int i=0;i<values.size();i++){
            row = sheet.createRow(i + 1);
            List<String> value = values.get(i);
            for(int j=0;j<value.size();j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(value.get(j));
            }
        }
        return wb;
    }
    /**
     * @description 生成Excel文件，并提供下载地址（导入、导出功能）
     * @author dingzd
     * @date 2019/8/3
     * @param wb
     * @param userid
     * @param resultInfo
     * @remark
     * @version V1.0
    */
    public static  void createExcelFile(Workbook wb, int userid, ResultInfo resultInfo) throws Exception{

        String baseAddress = System.getProperty("java.io.tmpdir");;
        String excelPath = baseAddress + "excel/";
        File file = new File(excelPath);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        String fileName = IDGenerator.idCreated(4, userid) + ".xlsx";
        String files = excelPath + fileName;
        FileOutputStream fout = new FileOutputStream(files);
        wb.write(fout);
        fout.close();
        resultInfo.setData("fileUrl",  fileName);
    }
    public static  void createExcelFile(Workbook wb, int userid, HttpServletResponse response) throws Exception{

        String fileName = IDGenerator.idCreated(4, userid) + ".xlsx";
        OutputStream outputStream = response.getOutputStream();
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
