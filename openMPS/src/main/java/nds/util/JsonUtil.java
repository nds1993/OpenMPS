package nds.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;



 

/*
//  클래스  명 : JsonUtil
//  클래스설명 : JsonUtil 클래스
//  최초작성일 : 2015-06-10
//  최종수정일 : 2015-06-10
//  Programmer : 박상민
*/ 
public class JsonUtil
{
    // 생성자
    public JsonUtil()
    {
        // 생성자 Code
    }


    /**
     * FuncName : JsonToMap()
     * FuncDesc : Json String -> Map 형태 변환
     * Param    : param : Json String
     * Return   : Map<String, Object>
     * Author   : 박상민
     * History  : 2015-06-10
    */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> JsonToMap(String param)
    {
        Gson gson = new Gson();

        // 테스트 코드
        /*
        Map<String, Object> map = gson.fromJson(param, new HashMap<String,Object>().getClass());

        Set<Entry<String, Object>> ms = map.entrySet();


        for (Entry<String, Object> e:ms)
        {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
        */

        return gson.fromJson(param, new HashMap<String,Object>().getClass());
    }


    /**
     * FuncName : ListToJson()
     * FuncDesc : List -> Json String 변환
     * Param    : res : Json String
     * Return   : Json String
     * Author   : 박상민
     * History  : 2015-06-10
    */
    @SuppressWarnings("unchecked")
    public static String ListToJson(List<Map<String, Object>> res)
    {
        Gson gson = new Gson();

        // 테스트 코드
        /*
        Map<String, Object> param = gson.fromJson(param, new HashMap<String,Object>().getClass());

        Set<Entry<String, Object>> ms = param.entrySet();

        for (Entry<String, Object> e:ms)
        {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
        */

        return gson.toJson(res);
    }


    /**
     * FuncName : JsonToLinkedHashMap()
     * FuncDesc : Json String -> LinkedHashMap 형태 변환(들어온 순서대로)
     * Param    : param : Json String
     * Return   : Map<String, Object>
     * Author   : 박상민
     * History  : 2015-06-10
    */
    @SuppressWarnings("unchecked")
    public static LinkedHashMap<String, Object> JsonToLinkedHashMap(String param)
    {
        Gson gson = new Gson();

        // 테스트 코드
        /*
        Map<String, Object> map = gson.fromJson(param, new HashMap<String,Object>().getClass());

        Set<Entry<String, Object>> ms = map.entrySet();


        for (Entry<String, Object> e:ms)
        {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
        */

        return gson.fromJson(param, new LinkedHashMap<String,Object>().getClass());
    }


    /**
     * FuncName : OneStringToJson()
     * FuncDesc : Json String 변환
     * Param    : sData : String
     * Return   : String
     * Author   : 박상민
     * History  : 2015-06-10
    */
    @SuppressWarnings("unchecked")
    public static String OneStringToJson(String sData)
    {
        Gson gson = new Gson();

        return gson.toJson(sData);
    }

    /**
     * FuncName : HashMapToJson()
     * FuncDesc : Json String 변환
     * Param    : sData : String
     * Return   : String
     * Author   : 박상민
     * History  : 2015-06-10
    */
    @SuppressWarnings("unchecked")
    public static String HashMapToJson(HashMap<String, Object> map)
    {
        Gson gson = new Gson();

        return gson.toJson(map);
    }
    
    /**
     * FuncName : MapToJson()
     * FuncDesc : Json String 변환
     * Param    : sData : String
     * Return   : String
     * Author   : 박상민
     * History  : 2015-06-10
     */
    @SuppressWarnings("unchecked")
    public static String MapToJson(Map<String, Object> map)
    {
        Gson gson = new Gson();
        
        return gson.toJson(map);
    }
}
