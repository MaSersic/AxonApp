package backend.services.impl;

import backend.DTO.ItemDTO;
import backend.event.ItemCreatedEvent;
import backend.event.ItemDeletedEvent;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuQueryServiceImpl {

    private final EventStore eventStore;

    @Autowired
    public MenuQueryServiceImpl(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public List<ItemDTO> listItems (String id) {
        ArrayList<ItemDTO> items = new ArrayList<>();
        List events = eventStore.readEvents("menu" + id).asStream()
                .map( s -> s.getPayload()).collect(Collectors.toList());

        for (Object event : events) {
            if(event.getClass().equals(ItemCreatedEvent.class)) {
                ItemCreatedEvent ev = (ItemCreatedEvent) event;
                items.add(new ItemDTO(ev.getItemId(), ev.getName(),
                        ev.getPrice(), ev.getIngredients()));
            } else if (event.getClass().equals(ItemDeletedEvent.class)) {
                ItemDeletedEvent ev = (ItemDeletedEvent) event;
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
