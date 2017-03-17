package org.jeff.tool.mybatis;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动生成MyBatis的实体类、实体映射XML文件、Mapper
 * <p/>
 * Created by weijianfu on 2017/2/17.
 */
public class MybatisEntityUtil {


    /**
     * 以下内容需根据自身情况改动
     * ∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨
     */
    private String root_path = "d:/tooltest";//文件生成根目录
    private String bean_package = "com.caih.erp.domain.entity";//表实体所属的package
    private String mapper_package = "com.caih.erp.domain.mapper";//Mapper所属的package

    private String driverName = "oracle.jdbc.driver.OracleDriver";
    private String user = "DxInPTST";
    private String password = "DxInPTST_789";
    private String url = "jdbc:oracle:thin:@132.37.5.151:8895:UATEPRDB";


    private String bean_path = root_path + "/entity";
    private String mapper_path = root_path + "/mapper";
    private String xml_path = root_path + "/mapper/xml";

    /**
     * ∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧∧
     * 以上内容需根据自身情况改动
     */


    public void build(String root_path, String bean_package, String mapper_package, String driverName, String user,
                      String password, String url) {
        this.root_path = root_path;
        this.bean_package = bean_package;
        this.mapper_package = mapper_package;
        this.driverName = driverName;
        this.user = user;
        this.password = password;
        this.url = url;

        this.bean_path = root_path + "/entity";
        this.mapper_path = root_path + "/mapper";
        this.xml_path = root_path + "/mapper/xml";

        try {
            this.generate();
            // 自动打开生成文件的目录
            Runtime.getRuntime().exec("cmd /c start explorer " + root_path.replace("/", "\\"));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    private static final String column_type_nvarchar2 = "NVARCHAR2";
    private static final String column_type_varchar2 = "VARCHAR2";
    private static final String column_type_char = "CHAR";
    private static final String column_type_date = "DATE";
    private static final String column_type_timestamp = "TIMESTAMP";
    private static final String column_type_int = "INTEGER";
    private static final String column_type_long = "LONG";
    private static final String column_type_number = "NUMBER";
    private static final String column_type_blob = "BLOB";


    private static final String column_type_clob = "CLOB";
    private String tableName = null;
    private String beanName = null;
    private String mapperName = null;


    private Connection conn = null;


    private void init() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);

        File file = new File(this.root_path);
        if (!file.exists()) {
            file.mkdir();
        }
    }


    /**
     * 获取所有的表
     *
     * @return
     * @throws java.sql.SQLException
     */
    private List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<String>();
        PreparedStatement pstate = conn.prepareStatement("SELECT  table_name FROM user_tables");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString(1);
            tables.add(tableName);
        }
        return tables;
    }


    private void processTable(String table) {
        StringBuffer sb = new StringBuffer(table.length());
        String tableNew = table.toLowerCase();
        String[] tables = tableNew.split("_");
        String temp = null;
        for (int i = 0; i < tables.length; i++) {
            temp = tables[i].trim();
            if (temp != null && "T".equals(temp.toUpperCase())) {
                continue;
            }
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }

        beanName = sb.toString();
        if (beanName == null || beanName.length() <= 0) {
            System.out.println("!!!");
        }
        mapperName = beanName + "Mapper";
    }


    private String processType(String type) {
        if (type.indexOf(column_type_nvarchar2) > -1) {
            return "String";
        } else if (type.indexOf(column_type_char) > -1) {
            return "String";
        } else if (type.indexOf(column_type_varchar2) > -1) {
            return "String";
        } else if (type.indexOf(column_type_number) > -1) {
            return "Long";
        } else if (type.indexOf(column_type_int) > -1) {
            return "Integer";
        } else if (type.indexOf(column_type_long) > -1) {
            return "Long";
        } else if (type.indexOf(column_type_date) > -1) {
            return "java.util.Date";
        } else if (type.indexOf(column_type_timestamp) > -1) {
            return "java.util.Date";
        } else if (type.indexOf(column_type_blob) > -1) {
            return "byte[]";
        } else if (type.indexOf(column_type_clob) > -1) {
            return "java.sql.Clob";
        }
        return "无法解析该类型";
    }


    private String processField(String field) {
        StringBuffer sb = new StringBuffer(field.length());
        field = field.toLowerCase();
        String[] fields = field.split("_");
        String temp = null;
        sb.append(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            temp = fields[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }
        return sb == null ? "" : sb.toString();
    }


    /**
     * 将实体类名首字母改为小写
     *
     * @param beanName
     * @return
     */
    private String processResultMapId(String beanName) {
        return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
    }


    /**
     * 构建类上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws java.io.IOException
     */
    private BufferedWriter buildClassComment(BufferedWriter bw, String text) throws IOException {
        if (text == null || text.length() <= 0) {
            return bw;
        }
        bw.newLine();
        bw.newLine();
        bw.write("/**");
        bw.newLine();
        bw.write(" * " + text);
        bw.newLine();
        bw.write(" **/");
        return bw;
    }


    /**
     * 构建方法上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws java.io.IOException
     */
    private BufferedWriter buildMethodComment(BufferedWriter bw, String text) throws IOException {
        bw.newLine();
        bw.write("\t/**");
        bw.newLine();
        bw.write("\t * " + text);
        bw.newLine();
        bw.write("\t **/");
        return bw;
    }


    /**
     * 生成实体类
     *
     * @param columns
     * @param types
     * @param comments
     * @throws java.io.IOException
     */
    private void buildEntityBean(List<String> columns, List<String> types, List<String> comments, String tableComment)
            throws IOException {
        File folder = new File(bean_path);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File beanFile = new File(bean_path, beanName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
        bw.write("package " + bean_package + ";");
        bw.newLine();
        bw.write("import java.io.Serializable;");
        bw.newLine();
        bw = buildClassComment(bw, tableComment);
        bw.newLine();
        bw.write("@SuppressWarnings(\"serial\")");
        bw.newLine();
        bw.write("public class " + beanName + " implements Serializable {");
        bw.newLine();
        bw.newLine();
        int size = columns.size();
        for (int i = 0; i < size; i++) {
            String curComment = comments.get(i);
            if (curComment != null && curComment.length() > 0) {
                bw.write("\t/**" + comments.get(i) + "**/");
                bw.newLine();
            }
            bw.write("\tprivate " + processType(types.get(i)) + " " + processField(columns.get(i)) + ";");
            bw.newLine();
            bw.newLine();
        }
        bw.newLine();
        // 生成get 和 set方法
        String tempField = null;
        String _tempField = null;
        String tempType = null;
        for (int i = 0; i < size; i++) {
            tempType = processType(types.get(i));
            _tempField = processField(columns.get(i));
            tempField = _tempField.substring(0, 1).toUpperCase() + _tempField.substring(1);
            bw.newLine();
            bw.write("\tpublic void set" + tempField + "(" + tempType + " " + _tempField + "){");
            bw.newLine();
            bw.write("\t\tthis." + _tempField + " = " + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();
            bw.write("\tpublic " + tempType + " get" + tempField + "(){");
            bw.newLine();
            bw.write("\t\treturn this." + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
        }
        bw.newLine();
        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }


    /**
     * 构建Mapper文件
     *
     * @throws java.io.IOException
     */
    private void buildMapper() throws IOException {
        File folder = new File(mapper_path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(mapper_path, mapperName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + mapper_package + ";");
        bw.newLine();
        bw.newLine();
        bw.write("import " + bean_package + "." + beanName + ";");
        bw.newLine();
        bw.write("import org.apache.ibatis.annotations.Param;");
        bw = buildClassComment(bw, mapperName + "数据库操作接口类");
        bw.newLine();
        bw.newLine();
        bw.write("public interface " + mapperName + "{");
        bw.newLine();
        bw.newLine();
        // ----------定义Mapper中的方法Begin----------
        bw = buildMethodComment(bw, "查询（根据主键ID查询）");
        bw.newLine();
        bw.write("\t" + beanName + "  selectByPrimaryKey ( @Param(\"id\") Long id );");
        bw.newLine();
        bw = buildMethodComment(bw, "删除（根据主键ID删除）");
        bw.newLine();
        bw.write("\t" + "int deleteByPrimaryKey ( @Param(\"id\") Long id );");
        bw.newLine();
        bw = buildMethodComment(bw, "添加");
        bw.newLine();
        bw.write("\t" + "int insert( " + beanName + " record );");
        bw.newLine();
        bw = buildMethodComment(bw, "添加 （匹配有值的字段）");
        bw.newLine();
        bw.write("\t" + "int insertSelective( " + beanName + " record );");
        bw.newLine();
        bw = buildMethodComment(bw, "修改 （匹配有值的字段）");
        bw.newLine();
        bw.write("\t" + "int updateByPrimaryKeySelective( " + beanName + " record );");
        bw.newLine();
        bw = buildMethodComment(bw, "修改（根据主键ID修改）");
        bw.newLine();
        bw.write("\t" + "int updateByPrimaryKey ( " + beanName + " record );");
        bw.newLine();

        // ----------定义Mapper中的方法End----------
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();
    }


    /**
     * 构建实体类映射XML文件
     *
     * @param columns
     * @param types
     * @param comments
     * @throws java.io.IOException
     */
    private void buildMapperXml(List<String> columns, List<String> types, List<String> comments) throws IOException {
        File folder = new File(xml_path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperXmlFile = new File(xml_path, mapperName + ".xml");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        bw.newLine();
        bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
        bw.newLine();
        bw.write("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        bw.newLine();
        bw.write("<mapper namespace=\"" + mapper_package + "." + mapperName + "\">");
        bw.newLine();
        bw.newLine();

        bw.write("\t<!--实体映射-->");
        bw.newLine();
        bw.write("\t<resultMap id=\"" + this.processResultMapId(beanName) + "ResultMap\" type=\"" + beanName + "\">");

        String curMethodComment = comments.get(0);
        if (curMethodComment != null && curMethodComment.length() > 0) {
            bw.newLine();
            bw.write("\t\t<!--" + curMethodComment + "-->");
        }
        bw.newLine();
        bw.write("\t\t<id property=\"" + this.processField(columns.get(0)) + "\" column=\"" + columns.get(0) + "\" />");
        bw.newLine();
        int size = columns.size();
        for (int i = 1; i < size; i++) {
            String curPropertyComment = comments.get(i);
            if (curPropertyComment != null && curPropertyComment.length() > 0) {
                bw.write("\t\t<!--" + curPropertyComment + "-->");
                bw.newLine();
            }
            bw.write("\t\t<result property=\""
                    + this.processField(columns.get(i)) + "\" column=\"" + columns.get(i) + "\" />");
            bw.newLine();
        }
        bw.write("\t</resultMap>");

        bw.newLine();
        bw.newLine();
        bw.newLine();

        // 下面开始写SqlMapper中的方法
        // this.outputSqlMapperMethod(bw, columns, types);
        buildSQL(bw, columns, types);

        bw.write("</mapper>");
        bw.flush();
        bw.close();
    }


    private void buildSQL(BufferedWriter bw, List<String> columns, List<String> types) throws IOException {
        int size = columns.size();
        // 通用结果列
        bw.write("\t<!-- 通用查询结果列-->");
        bw.newLine();
        bw.write("\t<sql id=\"Base_Column_List\">");
        bw.newLine();

        bw.write("\t\t id,");
        for (int i = 1; i < size; i++) {
            bw.write("\t" + columns.get(i));
            if (i != size - 1) {
                bw.write(",");
            }
        }

        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
        bw.newLine();


        // 查询（根据主键ID查询）
        bw.write("\t<!-- 查询（根据主键ID查询） -->");
        bw.newLine();
        bw.write("\t<select id=\"selectByPrimaryKey\" resultType=\""
                + processResultMapId(beanName) + "\" parameterType=\"java.lang." + processType(types.get(0)) + "\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t <include refid=\"Base_Column_List\" />");
        bw.newLine();
        bw.write("\t\t FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();
        // 查询完


        // 删除（根据主键ID删除）
        bw.write("\t<!--删除：根据主键ID删除-->");
        bw.newLine();
        bw.write("\t<delete id=\"deleteByPrimaryKey\" parameterType=\"java.lang." + processType(types.get(0)) + "\">");
        bw.newLine();
        bw.write("\t\t DELETE FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</delete>");
        bw.newLine();
        bw.newLine();
        // 删除完


        // 添加insert方法
        bw.write("\t<!-- 添加 -->");
        bw.newLine();
        bw.write("\t<insert id=\"insert\" parameterType=\"" + processResultMapId(beanName) + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + tableName);
        bw.newLine();
        bw.write(" \t\t(");
        for (int i = 0; i < size; i++) {
            bw.write(columns.get(i));
            if (i != size - 1) {
                bw.write(",");
            }
        }
        bw.write(") ");
        bw.newLine();
        bw.write("\t\t VALUES ");
        bw.newLine();
        bw.write(" \t\t(");
        for (int i = 0; i < size; i++) {
            bw.write("#{" + processField(columns.get(i)) + "}");
            if (i != size - 1) {
                bw.write(",");
            }
        }
        bw.write(") ");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        // 添加insert完


        //---------------  insert方法（匹配有值的字段）
        bw.write("\t<!-- 添加 （匹配有值的字段）-->");
        bw.newLine();
        bw.write("\t<insert id=\"insertSelective\" parameterType=\"" + processResultMapId(beanName) + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + tableName);
        bw.newLine();
        bw.write("\t\t <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
        bw.newLine();

        String tempField = null;
        for (int i = 0; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.newLine();
            bw.write("\t\t\t\t " + columns.get(i) + ",");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }

        bw.newLine();
        bw.write("\t\t </trim>");
        bw.newLine();

        bw.write("\t\t <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");
        bw.newLine();

        tempField = null;
        for (int i = 0; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + "!=null\">");
            bw.newLine();
            bw.write("\t\t\t\t #{" + tempField + "},");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }

        bw.write("\t\t </trim>");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        //---------------  完毕


        // 修改update方法
        bw.write("\t<!-- 修 改-->");
        bw.newLine();
        bw.write("\t<update id=\"updateByPrimaryKeySelective\" parameterType=\"" + processResultMapId(beanName) + "\">");
        bw.newLine();
        bw.write("\t\t UPDATE " + tableName);
        bw.newLine();
        bw.write(" \t\t <set> ");
        bw.newLine();

        tempField = null;
        for (int i = 1; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.newLine();
            bw.write("\t\t\t\t " + columns.get(i) + " = #{" + tempField + "},");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }

        bw.newLine();
        bw.write(" \t\t </set>");
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
        bw.newLine();
        // update方法完毕

        // ----- 修改（匹配有值的字段）
        bw.write("\t<!-- 修 改-->");
        bw.newLine();
        bw.write("\t<update id=\"updateByPrimaryKey\" parameterType=\"" + processResultMapId(beanName) + "\">");
        bw.newLine();
        bw.write("\t\t UPDATE " + tableName);
        bw.newLine();
        bw.write("\t\t SET ");

        bw.newLine();
        tempField = null;
        for (int i = 1; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t " + columns.get(i) + " = #{" + tempField + "}");
            if (i != size - 1) {
                bw.write(",");
            }
            bw.newLine();
        }

        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
        bw.newLine();
    }


    /**
     * 获取所有的数据库表注释
     *
     * @return
     * @throws java.sql.SQLException
     */
    private Map<String, String> getTableComment() throws SQLException {
        Map<String, String> maps = new HashMap<String, String>();
        PreparedStatement pstate = conn.prepareStatement("select * from user_tab_comments");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString("TABLE_NAME");
            String comment = results.getString("COMMENTS");
            maps.put(tableName, comment);
        }
        return maps;
    }


    public void generate() throws ClassNotFoundException, SQLException, IOException {
        init();
        String prefix = " select t1.TABLE_NAME, t1.COLUMN_NAME, t1.DATA_TYPE, t2.COMMENTS from user_tab_columns t1 \n" +
                " inner join user_col_comments t2 on t1.TABLE_NAME = t2.TABLE_NAME and t1.COLUMN_NAME = t2.COLUMN_NAME\n" +
                " where t1.table_name =  ";
        List<String> columns = null;
        List<String> types = null;
        List<String> comments = null;
        PreparedStatement pstate = null;
        List<String> tables = getTables();
        Map<String, String> tableComments = getTableComment();
        for (String table : tables) {
            columns = new ArrayList<>();
            types = new ArrayList<>();
            comments = new ArrayList<>();
            pstate = conn.prepareStatement(prefix + "'" + table + "'");
            ResultSet results = pstate.executeQuery();
            while (results.next()) {
                columns.add(results.getString("COLUMN_NAME"));
                types.add(results.getString("DATA_TYPE"));
                comments.add(results.getString("COMMENTS"));
            }
            tableName = table;
            processTable(table);
            //          this.outputBaseBean();
            String tableComment = tableComments.get(tableName);
            buildEntityBean(columns, types, comments, tableComment);
            buildMapper();
            buildMapperXml(columns, types, comments);
        }
        conn.close();
    }
}
