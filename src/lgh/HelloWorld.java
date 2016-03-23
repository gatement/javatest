package lgh;

import org.json.JSONObject;
import org.json.JSONArray;

public class HelloWorld
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("==start");

        String jsonStr = " { \"event_type\": \"device_status_kv\", \"did\": \"FrAqGn2LDJrBwHQTHJJ8nm\", \"created_at\": 1458604290.718, \"product_key\": \"e702032d839122344555551d9bd3f264\", \"mac\": \"accf236c6c42\", \"group_id\": null, \"data\": { \"str\": \"johnson\", \"number\": 12.5, \"integer\": 23, \"boolean\": true, \"null\": null } }";
        JSONObject json = new JSONObject(jsonStr);

        String typeName = json.getString("event_type"); // event_type as elasticsearch type name
        System.out.println(typeName);

        // transform data field
        JSONObject dataObj = json.getJSONObject("data");
        json.remove("data");
        System.out.println(json.toString());

        JSONArray dataArray = new JSONArray();
        String[] keys = JSONObject.getNames(dataObj);
        for(String key : keys)
        {
            Object value = dataObj.get(key);

            if (dataObj.isNull(key))
            {
                dataArray.put(new JSONObject().put("key", key).put("value_null", value));
            }
            else
            {
                String type = getType(value);
                switch (type)
                {
                    case "str":
                        dataArray.put(new JSONObject().put("key", key).put("value_str", value));
                        break;
                    case "num":
                        dataArray.put(new JSONObject().put("key", key).put("value_num", value));
                        break;
                    case "bool":
                        dataArray.put(new JSONObject().put("key", key).put("value_bool", value));
                        break;
                    default:
                        System.out.println("unknown type");
                        break;
                }
            }
        }

        json.put("data", dataArray);
        System.out.println(json.toString());

        System.out.println("==end");
    }

    public static <T> String getType(T t)
    {
        if(t instanceof String)
        { 
            return "str"; 
        }
        else if(t instanceof Number)
        { 
            return "num"; 
        }
        else if(t instanceof Boolean)
        { 
            return "bool"; 
        }
        else
        { 
            return "unknown"; 
        }
    }
}
