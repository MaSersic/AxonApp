package fer.hr.foodapp.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fer.hr.foodapp.DTO.ItemDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderHelper {
    public static Integer LAST_ORDER_NO = -1;

    public static List<ItemDTO> fetchOrder (String orderId) {
        List<ItemDTO> orderItems;
        ObjectMapper mapper = new ObjectMapper();
        String orderString = HttpRequestHelper.getRequest("orders/listItems/", "?id=" + orderId);
        try {
            orderItems = mapper.readValue(orderString, new TypeReference<List<ItemDTO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            orderItems = new ArrayList<>();
        }

        return orderItems;
    }
}
