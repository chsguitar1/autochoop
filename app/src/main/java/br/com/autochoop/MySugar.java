package br.com.autochoop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.orm.SugarDb;
import com.orm.SugarRecord;
import com.orm.util.NamingHelper;

/**
 * Created by cristiano on 10/11/17.
 */

public class MySugar extends SugarRecord {

    public static <T> Double sum(Class<T> type, String field, Context ctx) {
        SugarDb sugarDb = new SugarDb(ctx);
        SQLiteDatabase database = sugarDb.getDB();
        SQLiteStatement query = database.compileStatement("SELECT SUM("+field+") FROM "+ NamingHelper.toSQLName(type));
        try {
            return  Double.parseDouble(query.simpleQueryForString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            query.close();
        }
        return Double.valueOf(0);
    }
    public static SQLiteDatabase getDataBase(Context ctx){
        return  new SugarDb(ctx).getDB();
    }
    public static <T> Double sum(Class<T> type, String field, String whereArgs, Context ctx) {
        SugarDb sugarDb = new SugarDb(ctx);
        SQLiteDatabase database = sugarDb.getDB();



        SQLiteStatement query = database.compileStatement("SELECT coalesce(SUM("+field+"),0) FROM "+ NamingHelper.toSQLName(type)+" where "+whereArgs);
        try {
            return  Double.parseDouble(query.simpleQueryForString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            query.close();
        }
        return Double.valueOf(0);
    }

    public static <T> long queryMax(Class<T> type, String field, Context ctx) {
        SugarDb sugarDb = new SugarDb(ctx);
        SQLiteDatabase database = sugarDb.getDB();
        SQLiteStatement query = database.compileStatement("SELECT coalesce(MAX("+field+"),0) FROM "+ NamingHelper.toSQLName(type));
        try {
            return query.simpleQueryForLong();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            query.close();
        }
        return 0;
    }

    // colocar and nos where
    public static <T> long queryMax(Class<T> type, String field, Context ctx, String[] whereArgs) {
        SugarDb sugarDb = new SugarDb(ctx);
        SQLiteDatabase database = sugarDb.getDB();
        StringBuilder builder = new StringBuilder();
        for(String s : whereArgs) {
            builder.append(s);
        }
        String where = builder.toString();
        SQLiteStatement query = database.compileStatement("SELECT coalesce(MAX("+field+"),0) FROM "+ NamingHelper.toSQLName(type)+" where "+where);
        try {
            return query.simpleQueryForLong();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            query.close();
        }
        return 0;
    }
    public static <T> int queryNext(Class<T> type, String field, Context ctx, String[] whereArgs) {
        SugarDb sugarDb = new SugarDb(ctx);
        SQLiteDatabase database = sugarDb.getDB();
        StringBuilder builder = new StringBuilder();
        for(String s : whereArgs) {
            builder.append(s);
        }
        String where = builder.toString();
        SQLiteStatement query = database.compileStatement("SELECT coalesce(MAX("+field+"),0) FROM "+ NamingHelper.toSQLName(type)+" where "+where);
        try {
            return (int) (query.simpleQueryForLong()+1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            query.close();
        }
        return 0;
    }
    public static <T> int queryMaxInteger(Class<T> type, String field, Context ctx, String[] whereArgs) {
        SugarDb sugarDb = new SugarDb(ctx);
        SQLiteDatabase database = sugarDb.getDB();
        String where = "";
        if(whereArgs != null){
            StringBuilder builder = new StringBuilder();
            for(String s : whereArgs) {
                builder.append(s);
            }
            where = "where = "+builder.toString();
        }
        SQLiteStatement query = database.compileStatement("SELECT coalesce(MAX("+field+"),0) FROM "+ NamingHelper.toSQLName(type)+" "+where);
        try {
            return (int) query.simpleQueryForLong();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            query.close();
        }
        return 0;
    }
    public static <T> long count(Class<T> type, String field, Context ctx) {
        SugarDb sugarDb = new SugarDb(ctx);
        SQLiteDatabase database = sugarDb.getDB();
        SQLiteStatement query = database.compileStatement("SELECT coalesce(count("+field+"),0) FROM "+ NamingHelper.toSQLName(type));
        try {
            return query.simpleQueryForLong();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            query.close();
        }
        return 0;
    }
    public static <T> long countSql(String sql, Context ctx) {
        SugarDb sugarDb = new SugarDb(ctx);
        SQLiteDatabase database = sugarDb.getDB();
        SQLiteStatement query = database.compileStatement(sql);
        try {
            return query.simpleQueryForLong();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            query.close();
        }
        return 0;
    }
    public static <T> Cursor getCursor(String sql, Context ctx ) {
        SugarDb sugarDb = new SugarDb(ctx);
        SQLiteDatabase database = sugarDb.getDB();
        Cursor cursor = database.rawQuery(sql,null);
        return cursor;
    }




}
