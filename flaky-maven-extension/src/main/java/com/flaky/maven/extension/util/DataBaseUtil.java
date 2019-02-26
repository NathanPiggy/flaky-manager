package com.flaky.maven.extension.util;

import com.flaky.maven.extension.mojo.FlakyBean;

import java.sql.*;
import java.util.Date;

public class DataBaseUtil {

    public static Connection getDbConnection() throws ClassNotFoundException, SQLException {
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.getDbConnection start");
        Class.forName("com.mysql.jdbc.Driver");

        String url="jdbc:mysql://test.carfunny.com.cn:3306/flaky";

        String user="pzyf";

        String password="pzyf@2018";

        Connection cn=DriverManager.getConnection(url, user, password);

        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.getDbConnection end");

        return cn;
    }

    public static Statement getDbStatement(Connection cn) throws ClassNotFoundException, SQLException {
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.getDbStatement start");

        Statement st=cn.createStatement();
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.getDbStatement end");
        return st;
    }

    public static boolean ifLastTestPassed(String className, String unitTestName, Statement statement){
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.ifLastTestPassed start");

        StringBuilder sql= new StringBuilder("select * from flaky where status=0 " );
        sql.append(" and class_name = ").append(className);
        sql.append(" and unit_test_name = ").append(unitTestName);
        ResultSet rs= null;
        try {
            rs = statement.executeQuery(sql.toString());
            rs.last();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs   !=   null){
                try{
                    rs.close();
                    rs = null;
                }catch(Exception   e){}
            }
        }
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.ifLastTestPassed end");
        return false;
    }

    public static FlakyBean getFlakyRecord(String className, String unitTestName, Statement statement){
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.getFlakyRecord start");

        FlakyBean flakyBean = null;
        StringBuilder sql= new StringBuilder("select * from flaky where 1 = 1 " );
        sql.append(" and class_name = ").append(className);
        sql.append(" and unit_test_name = ").append(unitTestName);
        ResultSet rs= null;
        try {
            rs = statement.executeQuery(sql.toString());
            rs.last();
            if(rs.next()){
                flakyBean = new FlakyBean();
                flakyBean.setFlakyId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs   !=   null){
                try{
                    rs.close();
                    rs = null;
                }catch(Exception   e){}
            }
        }
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.getFlakyRecord end");
        return flakyBean;
    }

    public static boolean addFlakyRecord(int flakyStatus, Date lastDetectTime, String lastSha1, String className,
                                         String unitTestName, Integer detectCount, String projectId, Statement statement)  {
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.addFlakyRecord start");

        StringBuilder sql= new StringBuilder("INSERT INTO `flaky` (flaky_status,last_detect_time,last_sha_1,class_name,unit_test_name,detect_count,project_id) VALUES ( " );
        sql.append("'").append(flakyStatus).append("',");
        sql.append("'").append(lastDetectTime).append("',");
        sql.append("'").append(lastSha1).append("',");
        sql.append("'").append(className).append("',");
        sql.append("'").append(unitTestName).append("',");
        sql.append("'").append(detectCount).append("',");
        sql.append("'").append(projectId).append("'");

        sql.append(" );");
        ResultSet rs= null;
        try {
            rs = statement.executeQuery(sql.toString());
            rs.last();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs   !=   null){
                try{
                    rs.close();
                    rs = null;
                }catch(Exception   e){}
            }
        }
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.addFlakyRecord end");
        return false;
    }

    public static boolean updateFlakyRecord(int flakyId,int flakyStatus, Date lastDetectTime, String lastSha1,Integer detectCount, Statement statement)  {
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.updateFlakyRecord start");

        StringBuilder sql= new StringBuilder("UPDATE `flaky` set " );
        sql.append("flaky_status=").append(flakyStatus).append(",");
        sql.append("last_detect_time=").append(lastDetectTime).append(",");
        sql.append("last_sha_1=").append(lastSha1).append(",");
        sql.append("detect_count=").append(detectCount);

        sql.append("where flaky_id=").append(flakyId);

        ResultSet rs= null;
        try {
            rs = statement.executeQuery(sql.toString());
            rs.last();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs   !=   null){
                try{
                    rs.close();
                    rs = null;
                }catch(Exception   e){}
            }
        }
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.updateFlakyRecord end");
        return false;
    }

    public static boolean addFlakyHistoryRecord(Integer flakyId, Integer flakyStatus, Date detectTime, String sha1,
                                                String className, String unitTestName, Integer detectCount, String projectId,
                                                String environmentDetail, Statement statement){
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.addFlakyHistoryRecord start");

        StringBuilder sql= new StringBuilder("INSERT INTO `flaky_history` (flaky_id,flaky_status,detect_time,sha_1,class_name,unit_test_name,detect_count,project_id,environment_detail) VALUES ( " );
        sql.append("'").append(flakyId).append("',");
        sql.append("'").append(flakyStatus).append("',");
        sql.append("'").append(detectTime).append("',");
        sql.append("'").append(sha1).append("',");
        sql.append("'").append(environmentDetail).append("',");
        sql.append("'").append(unitTestName).append("',");
        sql.append("'").append(detectCount).append("',");
        sql.append("'").append(projectId).append("',");
        sql.append("'").append(environmentDetail).append("'");

        ResultSet rs= null;
        try {
            rs = statement.executeQuery(sql.toString());
            rs.last();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs   !=   null){
                try{
                    rs.close();
                    rs = null;
                }catch(Exception   e){}
            }
        }
        System.out.println("!!!!!!!!!!!!!!came DataBaseUtil.addFlakyHistoryRecord end");
        return false;
    }
}
