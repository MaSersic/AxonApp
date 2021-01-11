package backend.services.impl;

import backend.DTO.ItemDTO;
import backend.event.ItemAddedEvent;
import backend.event.ItemRemovedEvent;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderQueryServiceImpl {
    private final EventStore eventStore;

    @Autowired
    public OrderQueryServiceImpl(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public List<ItemDTO> listItemsInOrder (String id) {
       ArrayList<ItemDTO> items = new ArrayList<>();
       List events = eventStore.readEvents("order" + id).asStream()
               .map( s -> s.getPayload()).collect(Collectors.toList());

        for (Object event : events) {
            if(event.getClass().equals(ItemAddedEvent.class)) {
                ItemAddedEvent ev = (ItemAddedEvent) event;
                items.add(new ItemDTO(ev.getItemId(), ev.getName(), ev.getPrice(), ev.getIngredients()));
            } else if (event.getClass().equals(ItemRemovedEvent.class)) {
                ItemRemovedEvent ev = (ItemRemovedEvent) event;
                for (ItemDTO item : items){
                    if (ev.getItemId().equals(item.getItemId())){
                        items.remove(item);
                        break;
                    }
                }
            }
        }

        return items;
    }
}
